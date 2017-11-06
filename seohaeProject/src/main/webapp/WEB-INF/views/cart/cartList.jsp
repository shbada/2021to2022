<%    
/**
 * 장바구니 목록
 * @author seohae
 * @since 2017. 11. 06.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 06 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/mainTop.do" />
<script>
function CountUpdateOk(idx){ 
	document.form1.pdNo.value = idx;
	document.form1.method="POST";         
	document.form1.action="<c:url value='/cartUpdate.do' />";         
	document.form1.submit();
}

function listDetail(idx){ 
   document.form1.pdNo.value = idx;
   document.form1.method="POST";         
   document.form1.action="<c:url value='/bookList.do' />";         
   document.form1.submit();
}

function CartDel() {
	   var chkedVal = new Array(); //배열
	   var chkedObj = null;
	   
	   $(":checkbox[name='chk']:checked").each(function(i){
	       chkedObj = new Object();
	       chkedObj.cartNo = $(this).val(); 
	       chkedVal[i] = chkedObj; 
	    });
	   if(chkedVal.length == 0){
	      alert("선택된 목록이 없습니다. 삭제하시려는 목록을 체크하세요");
	      return;
	   }else {
	      if (confirm("정말 삭제하시겠습니까??") == true){    
	          $.ajax({               
	               type:"POST",
	               url:"/cartListDelete.do", 
	               dataType:"JSON",
	               data : JSON.stringify(chkedVal),
	               contentType: "application/json; charset=UTF-8",
	               async : false,
	               complete: function() {
	                  alert("삭제 되었습니다.")
	                  window.location.reload();   
	                }            
	            });
	      }else{   //취소
	          return;
	      }
	   }
	}
	</script>
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
                            <h2>CART LIST</h2>
                        </div>
                        <p> 회원님의 장바구니 목록입니다. 저희 BINO는 30000원 이하 구매시 배송비 2500원이 추가됩니다.</p>

                        <a href="" class="btn btn-lg">1:1 문의게시판</a>
                    </div>
                </div>
            </div>
        </div><!--End of row -->
    </div><!--End of container -->
</section><!--End of history -->

<!-- Pricing Section -->
<section id="pricing" class="pricing">
    <div class="container">
        <div class="row">
            <div class="main_pricing_area sections">
                <div class="head_title text-center">
                    <h2>CART LIST</h2>
                    <div class="subtitle">
                        A 30 days free trial for all. A brief story about how this process works, keep an eye till the end.
                    </div>
                    <div class="separator"></div>
                </div><!-- End off Head_title -->
                <div class="work_menu text-center">
                    <div id="filters" class="toolbar mb2 mt2">
                        <button class="btn-md fil-cat filter" data-rel="web" data-filter=".web">주문금액 ${map.sumMoney }원</button> /
                        <button class="btn-md fil-cat filter" data-rel="design" data-filter=".design">배송비 ${map.fee }원</button> /
                        <button class="btn-md fil-cat filter" data-rel="flyers" data-filter=".flyers">총 주문금액 ${map.allSum }원</button>
                    </div> 
                </div>
                <hr>
				<form name="form1" method="post" action="#">
	                <c:forEach var="row" items="${map.list}" varStatus="i">
		                <div class="col-md-4 col-sm-6">
		                    <div class="single_pricing pricing_business">
		                    	<input type="checkbox" name="chk" value="${row.cartNo }">
		                        <div class="pricing_head ">
		                        	<a href="#" class="link" onclick="javacscript:listDetail('${row.pdNo }');">
		                            	<h3>${row.pdName }</h3>
		                            </a>
		                            <div class="pricing_price">
		                            	
		                                <div class="p_r text-center">${row.pdPrice }</div>
		                                <div class="m_t text-center">${row.pdCg }</div>
		                            </div>
		                        </div>
		
		                        <div class="pricing_body">
		                            <a href="#" class="link" onclick="javacscript:listDetail('${row.pdNo }');">
	                                   <img src="${pageContext.request.contextPath }/img/${row.pdUrl}" width="145" height="145">
	                                </a>
	                                <input type="number" name="pdAmount" size="4" class="input-text qty text" title="Qty" value="${row.pdAmount}" min="1" step="1">
                                      <a href="#" class="link" onclick="javacscript:CountUpdateOk('${row.pdNo }');">
                                      	<input type="hidden" name="pdNo" value="1">
                                        <button type="button" class="">확인 </button>
                                     </a>
		                        </div>
		                    </div>
		                </div> <!-- End off col-md-4 -->
		            </c:forEach>
		         </form>
            </div>
            <div class="loginButton" style="text-align: center">
	            <button type="button" class="btn btn-lg m_t_10" id="cartDelete" onclick="CartDel();">선택삭제 </button>&nbsp;
	            <button type="button" class="btn btn-lg m_t_10" id="buyAllBtn" onclick="buyAll()">전체구매</button>
	    		<button type="button" class="btn btn-lg m_t_10" id="buyBtn" onclick="buyPart()">선택구매</button>
           </div>
        </div><!-- End off row -->
        <br/>
    </div><!-- End off container -->
</section><!-- End off Pricing Section -->    
<%@ include file="/WEB-INF/include/include-footer.jsp" %>