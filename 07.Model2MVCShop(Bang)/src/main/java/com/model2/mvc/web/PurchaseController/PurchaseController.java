package com.model2.mvc.web.PurchaseController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 Controller
@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	//setter Method 구현 않음
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
	
	///Constructor
	public PurchaseController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	//@RequestMapping("/addUserView.do")
	//public String addUserView() throws Exception {
	@RequestMapping( value="addPurchase", method=RequestMethod.GET )
	public String addPurchase(@RequestParam("prodNo") String prodNo, 
													Model model) throws Exception{
	
		System.out.println("/purchase/addPurchase : GET");
		
		Product product = productService.getProduct(Integer.parseInt(prodNo));
		model.addAttribute("product", product);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	//@RequestMapping("/addUser.do")
	@RequestMapping( value="addPurchase", method=RequestMethod.POST )
	public String addPurchase( @RequestParam("prodNo") String prodNo,
												@RequestParam("buyerId") String buyerId,
												@ModelAttribute("purchase") Purchase purchase,
												Model model) throws Exception {

		System.out.println("/purchase/addPurchase : POST");
		//Business Logic
		
		purchase.setBuyer(userService.getUser(buyerId));
		int prodNoInt = Integer.parseInt(prodNo);
		purchase.setPurchaseProd(productService.getProduct(prodNoInt));
		purchase.setTranCode("1");
		purchaseService.addPurchase(purchase);
		
		model.addAttribute("prodNo",prodNo);
		model.addAttribute("purchase", purchase);
		model.addAttribute("buyerId", buyerId);
	
		return "forward:/purchase/addPurchase.jsp";
	}
	
	//@RequestMapping("/getUser.do")
	//@RequestMapping( value="getPurchase", method=RequestMethod.GET )
	public String getPurchase( @RequestParam("userId") String userId , Model model ) throws Exception {
		
		System.out.println("/user/getPurchase : GET");
		//Business Logic
		User user = userService.getUser(userId);
		// Model 과 View 연결
		model.addAttribute("user", user);
		
		return "forward:/purchase/getUser.jsp";
	}
	
	//@RequestMapping("/updateUserView.do")
	//public String updateUserView( @RequestParam("userId") String userId , Model model ) throws Exception{
	//@RequestMapping( value="updatePurchase", method=RequestMethod.GET )
	public String updatePurchase( @RequestParam("userId") String userId , Model model ) throws Exception{

		System.out.println("/purchase/updatePurchase : GET");
		//Business Logic
		User user = userService.getUser(userId);
		// Model 과 View 연결
		model.addAttribute("user", user);
		
		return "forward:/purchase/updateUser.jsp";
	}
	
	//@RequestMapping("/updateUser.do")
	//@RequestMapping( value="updatePurchase", method=RequestMethod.POST )
	public String updatePurchase( @ModelAttribute("purchase") Purchase purchase , Model model , HttpSession session) throws Exception{

		System.out.println("/purchase/updatePurchase : POST");
		//Business Logic
		purchaseService.updatePurchase(purchase);
		
		String sessionId=((User)session.getAttribute("purchase")).getUserId();
		if(sessionId.equals(purchase.getTranCode())){
			session.setAttribute("purchase", purchase);
		}
		
		//return "redirect:/getUser.do?userId="+user.getUserId();
		return "redirect:/purchase/getUser?userId="+purchase.getTranCode();
	}
	
	//@RequestMapping("/loginView.do")
	//public String loginView() throws Exception{
	//@RequestMapping( value="updateTranCode", method=RequestMethod.GET )
	public String updateTranCode() throws Exception{
		
		System.out.println("/purchase/updateTranCode : GET");

		return "redirect:/purchase/loginView.jsp";
	}

	//@RequestMapping("/login.do")
	//@RequestMapping( value="updateTranCodeByProd", method=RequestMethod.POST )
/*	public String updateTranCodeByProd(@ModelAttribute("user") User user , HttpSession session ) throws Exception{
		
		System.out.println("/purchase/updateTranCodeByProd : POST");
		//Business Logic
		Purchase dbPurchase = purchaseService.getPurchase(purchase.getTranCode());
		
		if( purchase.getPassword().equals(dbPurchase.getPassword())){
			session.setAttribute("purchase", dbPurchase);
		}
		
		return "redirect:/index.jsp";
	}
*/	
	//@RequestMapping("/listUser.do")
	//@RequestMapping( value="listPurchase" )
	public String listPurchase( @ModelAttribute("search") Search search , 
												Model model , HttpServletRequest request) throws Exception{
		
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		User user = (User)request.getAttribute("user");
		
		// Business logic 수행
		Map<String , Object> map=purchaseService.getPurchaseList(search, user.getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("resultPage"+resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}
}
