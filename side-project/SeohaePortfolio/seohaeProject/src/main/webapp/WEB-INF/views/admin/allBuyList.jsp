<%    
/**
 * 공지사항 리스트 페이지
 * @author seohae
 * @since 2017. 11. 03.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 03 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/mainTop.do" />
<style>
.btn-outline-warning {
    color: #f39c12;
    background-color: #fff;
    border-color: #f39c12;
}
.btn-outline-warning:hover {
    color: #fff;
    background-color: #f1c40f;
    border-color: #f1c40f;
}
.btn-outline-danger {
    color: #c0392b;
    background-color: #fff;
    border-color: #c0392b;
}
.btn-outline-danger:hover {
    color: #fff;
    background-color: #e74c3c;
    border-color: #e74c3c;
}
</style>
<script>
function buyDetail(idx){ 
	document.form1.buyNo.value = idx;
	document.form1.method="POST";   		
	document.form1.action="<c:url value='/buyDetail.do' />";   		
	document.form1.submit();
}

function listDetail(idx){ 
	document.form1.pdNo.value = idx;
	document.form1.method="POST";   		
	document.form1.action="<c:url value='/reviewList.do' />";   		
	document.form1.submit();
}
</script>
<!-- History section -->
<section id="history" class="history sections">
    <div class="container">
        <div class="row">
            <div class="main_history">
                <div class="col-sm-6">
                    <div class="single_history_img">
                        <img src="assets/images/stab1.png" alt="" />
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="single_history_content">
                        <div class="head_title">
                            <h2>ADMIN</h2>
                        </div>
                        <p>관리자만 접근할 수 있습니다.</p>

                        <a href="/oneList.do" class="btn btn-lg">1:1 문의게시판</a>
                    </div>
                </div>
            </div>
        </div><!--End of row -->
    </div><!--End of container -->
</section><!--End of history -->

<section id="contact" class="contact">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="contact_contant sections">
                    <div class="head_title text-center">
                        <h2>BUY LIST</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
	                    </div><!-- End off Head_title -->
	                    <div class="row">
	                        <div class="col-sm-12">
	                            <div class="single_contant_left padding-top-90 padding-bottom-90">
	                               <!-- *********************************************************************************** -->
                               <form name="form1" method="post" action="#">
						           <input type="hidden" name="buyNo" value="1">
						           <input type="hidden" name="pdNo" value="1">
						              <table cellspacing="0" class="table table-striped b-t text-sm">
						                  <thead>
						                      <tr>
						                         <th class="product-subtotal">구매번호</th>
						                          <th class="product-remove">상품명</th>
						                          <th class="product-thumbnail">상품</th>
						                          <th class="product-price">수량</th>
						                          <th class="product-quantity">상품 가격</th>
						                          <th class="product-quantity">주문 총 가격</th>
						                          <th class="product-quantity">주문날짜</th>
						                          <th class="product-quantity">상세보기</th>
						                      </tr>
						                  </thead>
						                  <tbody>
						                     <c:forEach var="row" items="${buyList}" varStatus="cnt">
						                       <tr class="cart_item">
						                      	 <td class="product-thumbnail">
						                               ${row.buyNo }
						                           </td>
						                           
						                           <td class="product-thumbnail">
						                           	   <a href="#" class="link" onclick="javacscript:listDetail('${row.pdNo }');">
						                               		${row.pdName }
						                               </a>
						                           </td>
						
						                           <td class="product-name">
						                               <a href="#" class="link" onclick="javacscript:listDetail('${row.pdNo }');">
						                                  <img src="${pageContext.request.contextPath }/img/${row.pdUrl}" width="145" height="145">
						                               </a>
						                           </td>
						
						                           <td class="product-quantity">
						                               ${row.pdQt }
						                           </td>
						
						                           <td class="product-subtotal">
						                          	${row.pdPrice} bino
						                           </td>
						                           
						                           <td class="product-subtotal">
						                          	${row.buyTotalbino}
						                           </td>
						                           
						                           <td class="product-subtotal">
						                          	${row.buyRegDate}
						                           </td>
						                           
						                           <td class="product-thumbnail">
						                           	 <a href="#" class="btn-outline-danger hover" onclick="javacscript:buyDetail('${row.buyNo }');">주문자 상세정보</a>
						                           </td>
						                       </tr>
						                      </c:forEach>
						                  </tbody>
						              </table>
						          </form>
						       </div>
						  </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>