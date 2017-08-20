package com.model2.mvc.service.product.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("::"+getClass()+".sqlSqlSession() 콜");
		this.sqlSession = sqlSession;
	}

	public ProductDaoImpl() {
		System.out.println("::"+getClass()+".ProductDaoImpl() 생성자 콜");
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addProduct(Product product) throws Exception {
		System.out.println("productdaoimpl");
		return sqlSession.insert("ProductMapper.addProduct",product);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return (Product)sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}
	
	public List<Product> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("ProductMapper.updateProduct",product);
		
	}

	@Override
	public String makeCurrentPageSql(String sql, Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount" , search);
	}

}
