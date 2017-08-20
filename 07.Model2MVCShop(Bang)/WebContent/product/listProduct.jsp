<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
    
<html>
<head>

<title>${param.menu eq 'search' ? "상품 목록조회" : "상품 관리"}</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
//검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScript 이용
function fncGetList(currentPage){
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}

</script>
</head>

		<body bgcolor="#ffffff" text="#000000">
		<div style="width:98%; margin-left:10px;">
			
			<form name="detailForm" action="/product/listProduct" method="post">
			
			<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
				<tr>
					<td width="15" height="37">
						<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
					</td>
					<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
<!-- 								<td width="93%" class="ct_ttl01">상품 목록조회	</td> -->
									<td width="93%" class="ct_ttl01">
									
										${param.menu eq 'search'? "상품 목록조회" : "상품관리" }	
									
									</td>
								</tr>
						</table>
					</td>
					<td width="12" height="37">
						<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
					</td>
				</tr>
			</table>
			 
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${ ! empty search.searchCondition && search.searchCondition eq 0 ? "selected" : "" }>상품명</option>
				<option value="1" ${ ! empty search.searchCondition && search.searchCondition eq 1 ? "selected" : "" }>상품번호</option>
				<option value="2" ${ ! empty search.searchCondition && search.searchCondition eq 2 ? "selected" : "" }>상품가격</option>
			
			</select>
			<input type="text" name="searchKeyword" 
						value="${! empty search.searchKeyword ? search.searchKeyword : ""}"  
						class="ct_input_g" style="width:200px; height:20px" > 
		</td>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >
		전체  ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage } 페이지
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:set var="i" value="0" />
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center">${ i }</td>
			<td></td>
			<td align="left">
				<a href="/product/getProduct?prodNo=${product.prodNo}&menu=${param.menu }">${product.prodName}</a></td>

			<td></td>
			<td align="Right">${ product.price } 원 </td>			
			<td></td>
			<td align="Center">${product.regDate}</td>
			<td></td>	
			<td align="left">	
				<c:if test="${empty product.proTranCode}">
						판매중
				</c:if>
				<c:if test="${!empty product.proTranCode}">
				
					<c:if test="${sessionScope.user.role=='admin'}">
						<c:choose >
							<c:when test="${product.proTranCode=='1  ' }">
								구매완료 &nbsp; 
								<a href="/purchase/updateTranCodeByProd?prodNo=${product.prodNo}&tranCode=2">배송하기</a>
							</c:when>
	
							<c:when test="${product.proTranCode=='2  ' }">
								배송중 
							</c:when>
							<c:otherwise>
								배송완료
							</c:otherwise>
						</c:choose>				
						</c:if>	
							
						<c:if test="${empty sessionScope.user || sessionScope.user.role=='user'}">
							재고없음
						</c:if>	
					</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
</table>

<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
		
			<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
			<%System.out.println("listProduct 내부 resultPage 확인출력 : "+resultPage); %>
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetProductList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %>
			/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
		
		<jsp:include page="../common/pageNavigator.jsp"/>
		
    	</td>
	</tr>
</table>
<!-- PageNavigation End... -->

</form>
</div>
</body>
</html>
