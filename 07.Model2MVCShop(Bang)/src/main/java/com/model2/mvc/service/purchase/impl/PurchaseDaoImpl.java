package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao{

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
		public void setSqlSession(SqlSession sqlSession) {
		System.out.println("::"+getClass()+".sqlSqlSession() 콜");
		this.sqlSession = sqlSession;
	}

	public PurchaseDaoImpl() {
		System.out.println("::"+getClass()+"purchaseDaoImpl() 생성자 콜");
		// TODO Auto-generated constructor stub
	}
	
	///Method TTL : 7EA
	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

	@Override
	public Purchase getPurchaseByProd(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("여기는 daoimpl에 getPurchaseByProd");
		return sqlSession.selectOne("PurchaseMapper.getPurchaseByProd", prodNo);
	}

	@Override
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("purchaseDaoImpl :" + map);
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("여기는DAOIMPL"+ purchase);
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updatePurchase", purchase );
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updateTranCode", purchase );
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", search);
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
/*	// 구매가 완료되서 더 이상 팔지 않는 품목들 : 나중에 구현
	public Map<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/
}

