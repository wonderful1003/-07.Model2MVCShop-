package  com.model2.mvc.service.domain;

import java.sql.Date;


public class Product {
	
	private String fileName;
	private String manuDate;
	private int price;
	private String prodDetail;
	private String prodName;
	private int prodNo;
	private Date regDate;
	private String proTranCode;
	
	public Product(){
		System.out.println("product 디폴트 생성자");
	}
	
	public Product( String fileName, String manuDate, Integer price, String prodDetail, 
								String prodName,	Integer prodNo){
		
		this.fileName = fileName;
		this.manuDate = manuDate;
		this.price = price;
		this.prodDetail = prodDetail;
		this.prodName = prodName;
		this.prodNo = prodNo;
	}
/*
	<result property="fileName" column="IMAGE_FILE" jdbcType="VARCHAR"/>
	<result property="manuDate" column="MANUFACTURE_DAY" jdbcType="VARCHAR"/>
	<result property="price" column="PRICE" jdbcType="INTEGER"/>
	<result property="prodDetail" column="PROD_DETAIL" jdbcType="VARCHAR"/>
	<result property="prodName" column="PROD_NAME" jdbcType="VARCHAR"/>
	<result property="prodNo" column="PROD_NO" jdbcType="INTEGER"/>
	<result property="regDate" column="REG_DATE" jdbcType="DATE"/>
*/
	public String getProTranCode() {
		return proTranCode;
	}
	public void setProTranCode(String proTranCode) {
		this.proTranCode = proTranCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	// Override
	public String toString() {
		return "Product : [fileName]" + fileName
				+ "[manuDate]" + manuDate+ "[price]" + price + "[prodDetail]" + prodDetail
				+ "[prodName]" + prodName + "[prodNo]" + prodNo+" [regDate] "+regDate;
	}

	public String getImageFile() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setImageFile(String string) {
		// TODO Auto-generated method stub
		
	}

}