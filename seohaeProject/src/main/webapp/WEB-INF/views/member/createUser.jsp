<%    
/**
 * 회원가입 페이지
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
<c:set var="commandName" value="userVo"></c:set>
<spring:message code="messageVo.messageId" var="messageId" />
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<jsp:include page="/mainTop.do" />
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
                            <h2>CREATE MEMBER</h2>
                        </div>
                        <p> 회원가입 페이지입니다. 가입하실 회원님의 올바른 정보를 기입해주세요. 이미 가입하신 회원님께서는 <a href="/login.do" style="color: red">로그인</a> 페이지를 통해 로그인해주세요.
                        	잘못된 정보 입력시, 불이익을 받을 수 있으며 그에 대한 책임은 당사자에게 있습니다.</p>

                        <a href="" class="btn btn-lg">BROWSE OUR WORK</a>
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
	                    <h2>PRIVATE INFORMATION</h2>
	                    <div class="subtitle">
	                        Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
	                    </div>
	                    <div class="separator"></div>
	                </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                                <form:form commandName="${commandName }" name="frm" cssClass="panel-body" method="post">
				                     <div class="form-group">
				                        <span style="color:#f00; float:left; margin-right:4px;">*</span>&nbsp;<label class="control-label idCheck" style="float:left;">아이디</label>
				                        <div class="createIdSpan" id="createIdSpan"></div>
				                        <form:input type="text" path="userId" placeholder="${messageId }" cssClass="form-control" maxlength="20" onKeyup="checkedId(this);" onblur="onblur_event();" />
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00;">*</span>&nbsp;<label class="control-label nameCheck">이&nbsp;&nbsp;&nbsp;름</label>
				                        <form:input type="text" path="userNm" placeholder="이름을 입력하세요" cssClass="form-control" maxlength="20" style='ime-mode: active;' />
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00;">*</span>&nbsp;<label class="control-label emailCheck">이&nbsp;메&nbsp;일</label>
				                        <form:input type="email" path="userEmail" placeholder="Example@Example.com" cssClass="form-control" onblur="onblur_emailCheck();" />
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00; float:left; margin-right:4px;">*</span>&nbsp;<label class="control-label">Password</label>
				                        <form:input type="password" path="userPw" placeholder="Password" maxlength="16" cssClass="form-control" />
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00; float:left; margin-right:4px;">*</span>&nbsp;<label class="control-label passwordCheck">Password 재확인</label>
				                        <input type="password" id="userPw2" placeholder="Password 재확인" maxlength="16" class="form-control" onblur="onblur_passwordCheck();" />
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00; float:left; margin-right:4px;">*</span>&nbsp;<label class="control-label">전화번호</label>
				                        <form:input type="tel" path="userPhone" placeholder="전화번호를 입력하세요" cssClass="form-control" maxlength="20" onKeyup="checkedPhone(this);" autocomplete="off" />
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00; float:left; margin-right:4px;">*</span>&nbsp;<label class="control-label labelAddr">주&nbsp;&nbsp;&nbsp;소</label>
				                        <div>
				                           <form:input cssClass="form-control zipCode" path="userZipCode" placeholder="클릭하세요!" onclick="openDaumPostcode('userZipCode','userFirstAddr','userSecondAddr'); return false;" />   
				                           <form:input cssClass="form-control userFirstAddr" path="userFirstAddr" readonly="true" />
				                           <form:input cssClass="form-control userSecondAddr" path="userSecondAddr" />
				                        </div>
				                        <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
				                           <img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
				                        </div>
				                     </div>
				                     <div class="loginButton" style="text-align: center">
				                        <button type="button" class="btn btn-lg m_t_10" id="create" data-toggle="tooltip" data-placement="bottom" data-original-title="회원가입">확&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;인</button>&nbsp;&nbsp;
				                        <button type="button" class="btn btn-lg m_t_10" id="formReset" data-toggle="tooltip" data-placement="bottom" data-original-title="양식 초기화"><spring:message code="meesageVo.messageReset" /></button>
				                     </div>
				                  </form:form>
	                            </div>
	                        </div>
	
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</section><!-- End of contact section -->
	

<script>
var element_layer = document.getElementById('layer');
function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_layer.style.display = 'none';
}

function openDaumPostcode(userZipCode, userFirstAddr, userSecondAddr) {
   new daum.Postcode({
      oncomplete : function (data) {
         document.getElementById(userZipCode).value = data.zonecode;
         document.getElementById(userFirstAddr).value = data.address;
         document.getElementById(userSecondAddr).focus();
         
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