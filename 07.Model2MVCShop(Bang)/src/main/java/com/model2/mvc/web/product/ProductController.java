package com.model2.mvc.web.product;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


//==> 회원관리 Controller
@Controller
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addProductView.do")
	public ModelAndView addProductView() throws Exception {

		System.out.println("/addProductView.do");
				
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/product/addProductView");
				
		return modelAndView;
	}
	
	@RequestMapping("/addProduct.do")
	public ModelAndView addProduct( @ModelAttribute("product") Product product ) throws Exception {

		System.out.println("/addProduct.do");
		
		product.setManuDate(product.getManuDate().replaceAll("-",""));		
		
		//Business Logic
		productService.addProduct(product);
		
		String message = "상품을 추가하세요";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/product/addProduct.jsp");
		modelAndView.addObject("message",message);
		
		return modelAndView;
	}
	
	@RequestMapping("/getProduct.do")
	public ModelAndView getProduct( @RequestParam("prodNo") int prodNo ,
																@RequestParam(value="menu",defaultValue="") String menu ,
																Model model ) throws Exception {
		
		System.out.println("/getProduct.do");
		System.out.println("prodNo는 나야나"+prodNo);
		System.out.println("model은 나야나"+model);
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		System.out.println(product);
		
		
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(menu);
		
		if(menu!=""){
			if(menu.equals("manage")){
				modelAndView.setViewName("/product/updateProductView.jsp");
				return modelAndView;
			}
		}
		modelAndView.setViewName("/product/getProduct.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/updateProductView.do")
	public ModelAndView updateProductView( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{

		System.out.println("/updateProductView.do");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/product/updateProduct.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/updateProduct.do")
	public ModelAndView updateProduct( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{

		System.out.println("/updateProduct.do");
		//Business Logic
		System.out.println("product 나야나 "+product);
		
		productService.updateProduct(product);
		
		ModelAndView modelAndView = new ModelAndView();
				
		modelAndView.setViewName("/getProduct.do?="+product.getProdNo());
		
		return modelAndView;
	}
			
	@RequestMapping("/listProduct.do")
	public ModelAndView listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listProduct.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/product/listProduct.jsp");
	
		return modelAndView;
	}
}