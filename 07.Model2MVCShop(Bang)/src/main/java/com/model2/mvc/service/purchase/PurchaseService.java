package com.model2.mvc.service.purchase;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

/*
 * 구매관리를 추상화 캡슐화한 PurchaseService Interface (TTL : 7EA)
 */
///Method TTL : 7EA
public interface PurchaseService {
	
	// 구매 추가
	public void addPurchase(Purchase purchase) throws Exception;
	
	// 구매한 item tranNo로 찾기
	public  Purchase getPurchase(int tranNo) throws Exception; 
	
	// 구매한 item prodNo로 찾기
	public  Purchase getPurchaseByProd(int prodNo) throws Exception; 
	
	// 구매한 item 관련 정보 수정
	public  void updatePurchase(Purchase purchase)  throws Exception;
	
	// 구매한 item의 구매완료 -> 배송중 -> 배송완료 수정
	public  void updateTranCode(Purchase purchase)  throws Exception;
	
	// 구매 item 목록
	public  Map<String, Object> getPurchaseList(Search search, String buyer) throws Exception;
	
	//
	public  Map<String, Object> getSaleList(Search search) throws Exception; 
	
	 
	
	 
		 
}
