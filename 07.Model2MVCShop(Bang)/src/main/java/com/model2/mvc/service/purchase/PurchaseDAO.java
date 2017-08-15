package com.model2.mvc.service.purchase;

import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDAO {
	
	// ������ item ã�� by tranNo
	public  Purchase getPurchase(int tranNo) throws Exception; 
	
	// ������ item ã�� by prodNo
	public  Purchase getPurchaseByProd(int prodNo) throws Exception; 
	
	// ���� item ���
	public  List<Purchase> getPurchaseList(Search search) throws Exception;
	
	// item ���� �߰�
	public void addPurchase(Purchase purchase) throws Exception; 
	
	// ������ item ���� ���� ����
	public  void updatePurchase(Purchase purchase)  throws Exception; 
	
	// ���ſϷ� -> ����� -> ��ۿϷ� ����
	public  void updateTranCode(Purchase purchase)  throws Exception; 

	// �Խ��� ó��
	public int getTotalCount(Search search) throws Exception;

	public  HashMap<String, Object> getSaleList(Search search) throws Exception;
}
