<%    
/**
 * 회원정보 수정 페이지
 * @author seohae
 * @since 2017. 11. 04.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 04 / seohae / 최초생성)
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
<script>
$(function(){
	
	$("#update").click(function(){
		var obj = document.frm;
		obj.action = "<c:url value='editUserSave.do' />";
		obj.method = "POST";
		obj.submit();
	});
	
	 /** 회원탈퇴를 위한 [회원탈퇴] 버튼 클릭 시 */
	 $("#delete").click(function(){
			//ajax통신 끝나면 리스트 페이지 이동
			if(confirm("회원탈퇴를 하시겠습니까?") == true){
				var formData = $("form[name=frm]").serialize(); //값을 다가지고와서 변수에 담는다 (리스트형식으로 값이 들어감)
				$.ajax({
					type:"POST",
					url:"/memberDirDelete.do",
					dataType:"text",
					data: formData,
					success: function(result){
						if(result == "ok"){
							alert("회원탈퇴가 성공적으로 되셨습니다.");
							var obj = document.frm;
							obj.action = "<c:url value='/logOut.do' />"; //다시 컨트롤러로!
							obj.submit();
						}
					},
					error: function(data){ //안써도 되는부분인데 써주면 에러발생시 alert 실행
				           alert('에러가 발생하였습니다.');
				           return;
					}
				});
			} else{
				return;
			}
	});
	 
	/** [비밀번호 변경] 버튼 클릭 시 */
   $("#pwUpdate").click(function(){
       location.href="/pwUpdate.do";
   });
});

/*
  .append html에 소스 추가하는 방법 
  .innerHTML  만약 span이 있으면 그 사이에 글을 써주는 기능
*/
function onblur_passwordCheck(){
	if($("#${commandName} #userPw").val() != "" || $("#${commandName} #userPw2").val() != ""){
		if($("#${commandName} #userPw").val() != $("#${commandName} #userPw2").val()){
			$(".passwordCheck").append('<span class="createPasswordSpan" id="createPasswordSpan"></span>');
			$("#createPasswordSpan").css("color","red").css("font-size", "10px").css("margin-left","10px");
			document.getElementById("createPasswordSpan").innerHTML = "비밀번호가 일치하지 않습니다.";
		}else{
			$(".passwordCheck").append('<span class="createPasswordSpan" id="createPasswordSpan"></span>');
			$("#createPasswordSpan").css("color","blue").css("font-size", "10px").css("margin-left","10px");
			document.getElementById("createPasswordSpan").innerHTML = "비밀번호가 일치합니다.";
		}
	}
};

/** input [Email]에 함수호출 */
function onblur_emailCheck(){ //email input 태그 안의 onKeyup=""의 값
	if(!$('#userEmail').val()) { //이메일 input에 입력한 값이 없으면,
	      $(".emailCheck").append('<span class="createEmailSpan" id="createEmailSpan"></span>'); //email에는 id와 달리 id지정을 안해주고 이런식으로 지정해줘서 아래 소스를 실행
	      $("#createEmailSpan").css("color", "red").css("font-size", "10px").css("margin-left","10px"); //스타일 지정
	      document.getElementById("createEmailSpan").innerHTML = "Email를 입력 하세요."; //위 스타일이 지정되어 문구 출력
	      return;
	   }
	if($("#${commandName} #userEmail").val() != "") { //이메일의 값이 있으면, 
	      var regExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;      // email 유효성검사
	      if(!regExp.test($("#${commandName} #userEmail").val())) { //유효성 검사를 만족하지 않으면,
	         $(".emailCheck").append('<span class="createEmailSpan" id="createEmailSpan"></span>'); //id 지정
	         $("#createEmailSpan").css("color", "red").css("font-size", "10px").css("margin-left","10px"); //스타일 지정
	         document.getElementById("createEmailSpan").innerHTML = "잘못된 Email 형식입니다."; //위 스타일이 설정되어 innerHTML="" 문구 출력
	         return;
	      }
	   }
	$.ajax({
	      type:"POST", //POST방식
	      url : "<c:url value='/memberEmailCheck.do'/>", //이메일 체크 - 컨트롤러로 이동
	      //data: "userEmail="+encodeURIComponent($('#userEmail').val()),
	      data: "userEmail=" + $('#userEmail').val(), //이메일 값
	      success:function(data) {
	         if(data == "ok") { //이메일의 중복이 없으면,
	            $(".emailCheck").append('<span class="createEmailSpan" id="createEmailSpan"></span>'); //지정
	            $("#createEmailSpan").css("color", "blue").css("font-size", "10px").css("margin-left","10px"); //스타일 지정
	            document.getElementById("createEmailSpan").innerHTML = "사용 할 수 있는 Email입니다."; //스타일이 지정되어 문구 출력
	         }
             if(data == "fal"){ //이메일의 중복이 있으면,
                $(".emailCheck").append('<span class="createEmailSpan" id="createEmailSpan"></span>'); //지정
                $("#createEmailSpan").css("color", "red").css("font-size", "10px").css("margin-left","10px"); //스타일 지정
                document.getElementById("createEmailSpan").innerHTML = "중복된 Email이 있습니다. 다른 Email를 입력하여 주십시요."; //스타일이 지정되어 문구 출력
             }
             if(data == "no"){
            	 $(".emailCheck").append('<span class="createEmailSpan" id="createEmailSpan"></span>'); //지정
                 $("#createEmailSpan").css("color", "black").css("font-size", "10px").css("margin-left","10px"); //스타일 지정
                 document.getElementById("createEmailSpan").innerHTML = "회원님의 현재 이메일입니다."; //스타일이 지정되어 문구 출력
             }
	      },
	       error: function(data){ //생략 가능, 에러 발생시 alert 실행
	           alert('에러가 발생하였습니다.');
	           return;
	        },
	   });
}

function checkedPhone(obj){
	str = obj.value;
	len = str.length;
	ch = str.charAt(0);
	for (i = 0; i < len; i++) {
		ch = str.charAt(i);
		if((ch >= '0' && ch<= '9' || ch =='-')){
			continue;
		}else{
			alert("숫자만 입력이 가능합니다.");
			obj.value="";
			obj.focus();
			return false;
		}
	}
	return true;
}

function ValidationCheck() {
      var validate = true;
      var regExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;      // email 유효성검사
      var hanChecked = /^[가-힝a-zA-Z]{2,15}$/;      // 한글 유효성검사(2자리 이상 15자리 이하)
      var idChecked = /^[0-9a-zA-Z]{5,15}$/;      // 아이디 유효성검사(5자리 이상 15자리 이하)
      var phoneChecked = /^[0-9-]{1,16}$/;
           
      if($("#${commandName} #userEmail").val()) {
         if(!regExp.test($("#${commandName} #userEmail").val())){
            alert("이메일 주소가 유효하지 않습니다"); 
               $("#${commandName} #userEmail").focus();
                return false;   
         }
      }
      if($("#${commandName} #userPhone").val()) {
         if(!phoneChecked.test($("#${commandName} #userPhone").val())) { 
            alert("전화번호가 잘못 되었습니다."); 
             $("#${commandName} #userPhone").focus(); 
              return false;
         }
      }
      if(!$("#${commandName} #userId").val()) {
         alert("아이디를 입력 하세요.");
         $("#${commandName} #userId").focus();
         validate = false;
      }else if(!idChecked.test($("#${commandName} #userId").val())){
         alert("아이디는 5자 ~ 15자리 사이로 만들어주세요.");
         return false;
      }else if(!$("#${commandName} #userNm").val()) {
         alert("이름을 입력 하세요.");
         $("#${commandName} #userNm").focus();
         return false;
      }else if(!hanChecked.test($("#${commandName} #userNm").val())){
         alert("이름이 잘못 되었습니다.");
         $("#${commandName} #userNm").focus();
         return false;
      }else if(!$("#${commandName} #userEmail").val()) {
         alert("이메일을 입력하세요.");
         $("#${commandName} #userEmail").focus();
         return false;
      }else if(!$("#${commandName} #userPw").val()) {
         alert("비밀번호를 입력하세요.");
         $("#${commandName} #userPw").focus();
         return false;
      }else if($("#${commandName} #userPw").val().length > 16 || $("#${commandName} #userPw").val().length < 8) {
             alert("비밀번호는  8 ~ 16 자리로 입력해주세요.");
             return false;
          }else if(!$("#${commandName} #userPw2").val()) {
         alert("비밀번호 확인을 입력하세요.");
         $("#${commandName} #userPw2").focus();
         return false;
      }else if($("#${commandName} #userPw").val() != $("#${commandName} #userPw2").val()) {
             alert("비밀번호가 일치 하지 않습니다.");
             $("#${commandName} #userPw2").focus();
             return false;
          }else if(!$("#${commandName} #userFirstAddr").val()) {
         alert("주소를 클릭하여 선택해 주세요"); 
         $("#${commandName} #userZipCode").focus(); 
          return false;
          }else if(!$("#${commandName} #userSecondAddr").val()) {
         alert("나머지 주소를 입력 해 주세요"); 
         $("#${commandName} #userSecondAddr").focus();
          return false;
          }
      else{
            return true;
         }
      return validate;
   }
	
</script>

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
                            <h2>EDIT MEMBER</h2>
                        </div>
                        <p> 회원정보 수정 페이지입니다. 변경하실 회원님의 올바른 정보를 입력해주세요. 잘못된 정보 입력시, 불이익을 받을 수 있으며 그에 대한 책임은 당사자에게 있습니다.</p>

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
	                    <h2>EDIT MEMBER</h2>
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
				                        <form:input type="text" path="userId" name="userId" cssClass="form-control" maxlength="20" value="${userVo.userId}" readonly="true" />
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00;">*</span>&nbsp;<label class="control-label nameCheck">이&nbsp;&nbsp;&nbsp;름</label>
				                        <form:input type="text" path="userNm" cssClass="form-control" maxlength="20" style='ime-mode: active;' value="${userVo.userNm}" readonly="true"/>
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00;">*</span>&nbsp;<label class="control-label emailCheck">이&nbsp;메&nbsp;일</label>
				                        <form:input type="email" path="userEmail" cssClass="form-control" onblur="onblur_emailCheck();" value="${userVo.userEmail}" />
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00; float:left; margin-right:4px;">*</span>&nbsp;<label class="control-label">전화번호</label>
				                        <form:input type="tel" path="userPhone" cssClass="form-control" maxlength="20" onKeyup="checkedPhone(this);" autocomplete="off" valeu="${userVo.userPhone}" />
				                     </div>
				                     <div class="form-group">
				                        <span style="color:#f00; float:left; margin-right:4px;">*</span>&nbsp;<label class="control-label labelAddr">주&nbsp;&nbsp;&nbsp;소</label>
				                        <div>
				                           <form:input cssClass="form-control zipCode" path="userZipCode" value="${userVo.userZipCode }" placeholder="클릭하세요!" onclick="openDaumPostcode('userZipCode','userFirstAddr','userSecondAddr'); return false;" />   
				                           <form:input cssClass="form-control userFirstAddr" path="userFirstAddr" readonly="true" value="${userVo.userFirstAddr}"/>
				                           <form:input cssClass="form-control userSecondAddr" path="userSecondAddr" value="${userVo.userSecondAddr}" />
				                        </div>
				                        <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
				                           <img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
				                        </div>
				                     </div>
				                     <div class="loginButton" style="text-align: center">
				                        <a href="#" id="update" class="btn btn-lg m_t_10">회원정보수정</a>
				                        <button type="button" class="btn btn-lg m_t_10" id="pwUpdate" data-toggle="tooltip" data-placement="bottom">비밀번호 변경</button>
                        				<button type="button" class="btn btn-lg m_t_10" id="delete" data-toggle="tooltip" data-placement="bottom" data-original-title="회원탈퇴">회원탈퇴</button>
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
/** 다음 주소 API */
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