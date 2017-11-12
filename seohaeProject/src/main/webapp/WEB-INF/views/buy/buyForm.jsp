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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<jsp:include page="/mainTop.do" />
<script>
function buyDeliveryCheck(obj){
   if(obj.checked){
   $.ajax({
      type : 'POST',
      url : '/buyDeliveryCheck.do',
       contentType: "application/x-www-form-urlencoded; charset=UTF-8",
       success : function(response){
            console.log(JSON.stringify(response.data));
            document.form.buyName.value = response.data.userNm;
            document.form.buyPhone.value = response.data.userPhone;
            document.form.buyZipCode.value = response.data.userZipCode;
            document.form.buyFirstAddr.value = response.data.userFirstAddr;
            document.form.buySecondAddr.value = response.data.userSecondAddr;
       }
   });
   } else{
         document.form.buyName.value = "";
         document.form.buyPhone.value = "";
         document.form.buyZipCode.value = "";
         document.form.buyFirstAddr.value = "";
         document.form.buySecondAddr.value = "";
   }
}

function returnCart() {
   if (confirm("확인을 누르시면 장바구니 페이지로 이동합니다.") == true) {
         document.location.href="/cartList.do";         
      } 
}

function checkPayment() {
   var name = document.form.buyName.value;
   var phone = document.form.buyPhone.value;
   var binoSum2 = document.form.binoSum.value;
   var allSum2 = document.form.buyTotalbino.value;
   var nameValid = /^[가-힣]{2,4}$/;
   var phoneValid = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/;
   
   var binoSum = parseInt(binoSum2);
   var allSum = parseInt(allSum2);
   
   if(nameValid.test(name) == false){
      alert("올바른 이름을 입력해주세요.");
   } else if(phoneValid.test(phone) == false){
      alert("올바른 연락처를 입력해주세요.");
   } else if(binoSum < allSum){
	  alert("회원님의 총 bino가 부족합니다. 장바구니 페이지로 이동합니다.");
	  document.location.href="/cartList.do";
   } else{
   
      var totalCartNo = "";  
      var cartNo = null;
      
      $(":text[name=cartNo]").each(function(i){
    	  cartNo = $(this).val(); 
          totalCartNo += cartNo+","; 
       });
      if (confirm("결제 하시겠습니까?") == true) {
         document.form.totalCartNo.value = totalCartNo;
         document.form.method="POST";         
         document.form.action="<c:url value='/payment.do' />";         
         document.form.submit();
      }
      return;   
   }
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
                            <h2>BOOK BUY</h2>
                        </div>
                        <p> 교재 구매 페이지입니다. 저희 BINO에서는 회원님들이 BINO에서 활동을 하며 꾸준히 적립해온 bino를 사용하여 구매할 수 있는
                        	교재만을 판매하고 있으며, 이 교재를 구매하는 회원님들도 오직 회원님들의 bino를 사용해서 구매가 가능합니다.</p>

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
                        <h2>BUY</h2>
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
					            <input type="hidden" name="userId" value="${map.userId }">
					            <input type="hidden" name="binoSum" value="${map.binoSum}">
					            <input type="hidden" name="allSum" value="${map.allSum}">
					            <h4><div class="separator"></div>구매 교재</h4><br/>
					            <table class="table table-striped b-t text-sm">
					               <thead>
					                  <tr>
					                     <th class="product-subtotal">번호</th>
					                     <th class="product-remove">상품명</th>
					                     <th class="product-thumbnail">상품</th>
					                     <th class="product-price">수량</th>
					                     <th class="product-quantity">상품 가격</th>
					                  </tr>
					               </thead>
					               <tbody>
					                  <c:forEach var="row" items="${map.list}" varStatus="i">
					                     <tr class="cart_item">
					                        <td class="product-remove">
					                           <input type="text" size="4" style="border: 0;" name="cartNo" value="${row.cartNo}" readonly="readonly"/> 
					                        </td>
					                        <td class="product-thumbnail">
					                           ${row.pdName }
					                        </td>
					                        <td class="product-name">
					                           <img src="${pageContext.request.contextPath }/img/${row.pdUrl}" width="145" height="145">
					                        </td>
					                        <td class="product-quantity">
					                           <div class="quantity buttons_added">
					                              <input type="number" name="pdAmount" size="1" class="input-text qty text" style="border: 0; text-align: center;" title="Qty" value="${row.pdAmount}" readonly="readonly"> 
					                              <input type="hidden" name="pdNo" value="${row.pdNo}">
					                           </div>
					                        </td>
					                        <td class="product-subtotal">
					                           	<input type="number" name="pdPrice" size="5" style="border: 0;text-align: center;" value="${row.pdPrice }" readonly="readonly"/>
					                        </td>            
					                     </tr>
					                  </c:forEach>
					               </tbody>
					            </table>
					            <hr>
					            <h4><div class="separator"></div>총 주문가격</h4><br/>
					            <table class="table table-striped b-t text-sm">
					               <tbody>
					                  <tr class="cart-subtotal">
					                     <th>주문 금액</th>
					                     <td><span class="amount">${map.sumMoney } bino</span></td>
					                  </tr>
					                  <c:if test="${map.sumMoney == 0}">
					                     <tr class="shipping">
					                        <th>배송비</th>
					                        <td>0 bino</td>
					                     </tr>
					
					                     <tr class="order-total">
					                        <th>총 결제금액</th>
					                        <td>
					                           <strong><span class="amount">0 bino</span></strong>
					                        </td>
					                     </tr>
					                  </c:if>
					
					                  <c:if test="${map.sumMoney != 0}">
					                     <tr class="shipping">
					                        <th>배송비</th>
					                        <td>${map.fee } bino</td>
					                     </tr>
					
					                     <tr class="order-total">
					                        <th>총 결제금액</th>
					                        <td><strong><span class="amount">${map.allSum } bino</span></strong>
					                        </td>
					                     </tr>
					                  </c:if>
					               </tbody>
					            </table>
					         </form>
					         <hr />
					        <h4><div class="separator"></div>배송 정보 입력</h4><br/>
					         <form name="form" id="form" action="#" method="post">
					            <input type="hidden" name="totalCartNo" value="1">
					            <input type="hidden" name="userId" value="${map.userId}">
					            <input type="hidden" name="buyTotalbino" value="${map.allSum}">
					            <input type="hidden" name="deliveryPrice" value="${map.fee }">
					            
					            &nbsp; <input type="checkbox" name="buy_delivery" id="buy_delivery" onclick="buyDeliveryCheck(this)" />
					            <label for="buy_delivery">&nbsp;구매자 정보와 동일합니다.</label><br />
					            <label for="userId"></label> <input type="text" class="form-control" name="buyName"id="buyName" style="width: 100%" placeholder="받는사람 이름을 입력해주세요" /> <span id="duplicateResult"></span><br /> <label for="userName"></label>
					            <input type="text" class="form-control" name="buyPhone" id="buyPhone"style="width: 100%" placeholder="받는사람 연락처를 입력해주세요" /><br /> 
					            <br />
					            
					            <input type="text" class="form-control" id="buyZipCode" name="buyZipCode" placeholder="클릭하세요!" onclick="openDaumPostcode('buyZipCode','buyFirstAddr','buySecondAddr'); return false;" />&nbsp; 
					            <input type="text" class="form-control" id="buyFirstAddr" name="buyFirstAddr" readonly/>&nbsp; 
					            <input type="text" class="form-control" id="buySecondAddr" name="buySecondAddr" placeholder="상세정보를 입력해주세요">&nbsp; 
		                        <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
		                           <img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
		                        </div>
					            <hr/>
					            <h4><div class="separator"></div>회원님의 총 BINO</h4><br/>
					            <input type="text" class="form-control" id="binoSum" name="binoSum" value="${map.binoSum }" readonly/><br></br>  
					            <hr />
					         </form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                           	<input type="button" class="btn btn-lg m_t_10"value="결제 하기" onclick="checkPayment()" /> 
					        <input type="button"class="btn btn-lg m_t_10" value="장바구니로 돌아가기"onclick="returnCart()" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->

<script>
/** 다음 주소 API */
var element_layer = document.getElementById('layer');
function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_layer.style.display = 'none';
}

function openDaumPostcode(buyZipCode, buyFirstAddr, buySecondAddr) {
   new daum.Postcode({
      oncomplete : function (data) {
         document.getElementById(buyZipCode).value = data.zonecode;
         document.getElementById(buyFirstAddr).value = data.address;
         document.getElementById(buySecondAddr).focus();
         
       element_layer.style.display = 'none';
      },
        width : '100%',
       height : '100%'
   }).embed(element_layer);
   element_layer.style.display = 'block';
   initLayerPosition();
}
   
function initLayerPosition(){
    var width = 300; //우편번호서비스가 들어갈 element의 width
    var height = 460; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    element_layer.style.width = width + 'px';
    element_layer.style.height = height + 'px';
    element_layer.style.border = borderWidth + 'px solid';
    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
    element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
}
</script>	
<%@ include file="/WEB-INF/include/include-footer.jsp" %>