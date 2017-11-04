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
<script>
$(document).ready(function(){ //버튼 클릭시 입력했던 모든 값들을 없애기위함
	
	/** 새로작성 */
	$("#formReset").click(function(){
		/* document.frm.reset(); :관리할때는 별로다. */
		$("#userId").val(""); //input값을 비운다.
		$("#userNm").val("");
		$("#userEmail").val("");
		$("#userPw").val("");
		$("#userPw2").val("");
		$("#userPhone").val("");
		$("#userZipCode").val("");
		$("#userFirstAddr").val("");
		$("#userSecondAddr").val("");
		document.getElementById("createPasswordSpan").innerHTML = ""; //비밀번호 일치여부 출력하는 문구를 없애기위함
	});
	
	/** 가입완료! */
	$("#create").click(function(){ //모두 입력하고 회원가입 완료 버튼 클릭시!
		var validate = ValidationCheck();
		if(validate == false) return;
		if(confirm("회원가입을 하시겠습니까?") == true){ //확인창이 뜬다.
			$.ajax({ //Ajax 사용
				type: "POST", //post 방식
				url : "<c:url value='/createIdAndEmailCheck.do'/>", //아이디,이메일 형식체크 -> 컨트롤러로 이동.
				data: "userId="+encodeURIComponent($('#userId').val())+"&userEmail="+encodeURIComponent($('#userEmail').val()),
				success:function(data){
					if(data == "ok"){ //컨트롤러에서 ok가 반환됬을때 실행되는 부분 
						if(validate == true){
						var obj = document.frm;
						obj.action = "<c:url value='/userCreateSuccess.do' />"; //다시 컨트롤러로!
						obj.submit();
						}
					}
					if(data == "fal"){ //중복됬음을 알려주는 문구가 있었음에도 회원가입 버튼 클릭 시 막음을 위해
						alert("중복된 ID 또는 Email이 있습니다.");
					}
				},
				error: function(data){ //안써도 되는부분인데 써주면 에러발생시 alert 실행
			           alert('에러가 발생하였습니다.');
			           return;
				}
			});
		}else{
			return;
		}
	});
});

/** 회원 아이디 - 중복검사 */
function onblur_event(){ //ID의 input 태그의 onKeyup에 해당하는 부분
	if(!$('#userId').val()){ //ID Input에 값이 있으면,
		if(!$('#userId').val()) {
		      $("#createIdSpan").css("color", "red").css("font-size", "10px").css("margin-top","5px").css("margin-left","5px").css("width","235px").css("float","left");
		   		//div id="createIdSpan", css로 출력될 문구에 스타일을 지정해줌.
		      /* document.getElementById("createIdSpan").innerHTML = "아이디를 입력 하세요.";
		      jQuery(".createIdSpan").slideDown("fast");
		      $("#createIdSpan").effect("shake", {times:15,distance:1}, 400); */

		      $(".createIdSpan").html("아이디를 입력 하세요.").promise().done(function() { //위 태그의 스타일을 지정되어 html안의 문구가 출력됨
		         jQuery(".createIdSpan").slideDown("fast");
		         $(this).effect("shake", {times:15,distance:1}, 400);
		      });
		      return;
		   }
		
		}else{
			if($("#${commandName} #userId").val()!=""){ //입력된 id값이 있다면,
			      var idChecked = /^[0-9a-zA-Z]{5,15}$/;      // 아이디 유효성검사(5자리 이상 15자리 이하)
			      if(!idChecked.test($("#${commandName} #userId").val())) { //idChecked의 유효성 검사를 만족하지 않는다면,
			         $("#createIdSpan").css("color", "red").css("font-size", "10px").css("margin-top","5px").css("margin-left","5px").css("width","235px").css("float","left");
			         document.getElementById("createIdSpan").innerHTML = "아이디는 5자 ~ 15자리 사이로 만들어주세요."; //위 스타일 지정을 받고 innerHTML="여기 안의 값"을 출력해줌
			         return;
			      }
			  }
	}
	$.ajax({
	      type:"POST", //post방식
	      url : "<c:url value='/createIdCheck.do'/>", //ID체크 - 컨트롤러로 이동
	      //data: "userId="+encodeURIComponent($('#userId').val()),
	      data: "userId=" + $('#userId').val(), //id값을 받아옴
	      success:function(data) {
	         if(data == "ok") { //중복이 없음 -> 사용가능한 아이디
	            $("#createIdSpan").css("color", "blue").css("font-size", "10px").css("margin-top","5px").css("margin-left","5px").css("width","235px").css("float","left");
	            document.getElementById("createIdSpan").innerHTML = "사용 할 수 있는 아이디입니다."; //위 스타일이 지정되어 문구 출력
	         }
	             if(data == "fal"){ //중복있음 -> 사용이 불가능한 아이디
	                $("#createIdSpan").css("color", "red").css("font-size", "10px").css("margin-top","5px").css("margin-left","5px").css("width","235px").css("float","left");
	                document.getElementById("createIdSpan").innerHTML = "중복된 ID가 있습니다. 다른 ID를 입력하여 주십시요."; // 위 스타일이 지정되어 문구 출력
	             }
	      },
	       error: function(data){ //생략 가능 , 에러 발생시 출력됨
	           alert('에러가 발생하였습니다.');
	           return;
	        },
	   });
}

/** 회원 이메일 - 중복검사 */
function onblur_emailCheck(){ //email input 태그 안의 onKeyup=""의 값
	if(!$('#userEmail').val()) { //이메일 input에 입력한 값이 있으면,
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
	      url : "<c:url value='/createEmailCheck.do'/>", //이메일 체크 - 컨트롤러로 이동
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
	      },
	       error: function(data){ //생략 가능, 에러 발생시 alert 실행
	           alert('에러가 발생하였습니다.');
	           return;
	        },
	   });
}

/** 패스워드, 패스워드 확인 일치여부 확인 */
function onblur_passwordCheck(){ //패스워드 onblur 값
    if($("#${commandName} #userPw").val() != "" || $("#${commandName} #userPw2").val() != ""){ //패스워드, 패스워드 재확인이 널이 아니면
       if($("#${commandName} #userPw").val() != $("#${commandName} #userPw2").val()){ //패스워드, 패스워드 재확인이 같지 않으면
          $(".passwordCheck").append('<span class="createPasswordSpan" id="createPasswordSpan"></span>'); //지정
          $("#createPasswordSpan").css("color","red").css("font-size", "10px").css("margin-left","10px"); //스타일 지정
          document.getElementById("createPasswordSpan").innerHTML = "비밀번호가 일치하지 않습니다."; //일치하지않다는 문구 출력- 스타일 지정되어
          $("#userPw").val(""); //입력 값을 지움 -> 재확인과 맞지 않으니까 다시 입력하라고 다 지워버림
          $("#userPw2").val(""); //입력 값을 지움
          $("#${commandName} #userPw").focus(); //다시 입력하라고 마우스 커서를 패스워드 입력폼에 자동으로 갖다줌
       }else{ //패스워드, 패스워드 재확인 값이 같으면
    	  $(".passwordCheck").append('<span class="createPasswordSpan" id="createPasswordSpan"></span>'); //지정
          $("#createPasswordSpan").css("color","blue").css("font-size", "10px").css("margin-left","10px"); //스타일 지정
          document.getElementById("createPasswordSpan").innerHTML = "비밀번호가 일치합니다."; //일치하다는 문구 출력 - 스타일이 지정되어 출력
       }
    }
 };

/** 회원 아이디 - 형식확인  */
function checkedId(obj){ //아이디 입력할때
	str = obj.value;
	len = str.length;
	ch = str.charAt(0);
	for(i = 0; i < len; i++) {
		ch = str.charAt(i);
		if((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || ( ch >= 'A' && ch <= 'Z')){ //아이디 조건
			continue;
		}else{
			alert("영문/숫자만 입력이 가능합니다."); //영문/숫자를 입력을 안하면 -> 위 아이디 조건을 만족시키지 못했을 때 출력
			obj.value="";
			obj.focus();
			return false;
		}
	}
	return true;
}


/** 회원 연락처 - 형식확인  */
function checkedPhone(obj){ //휴대폰 번호를 입력할때 
	str = obj.value;
	len = str.length;
	ch = str.charAt(0);
	for(i = 0; i < len; i++) {
		ch = str.charAt(i);
		if((ch >= '0' && ch <= '9' || ch == '-')){ //휴대폰 번호 조건
			continue;
		}else{
			alert("숫자만 입력이 가능합니다."); //숫자를 입력 안하면 -> 위 휴대폰 번호 조건을 만족시키지 못했을때 출력
			obj.value="";ㅇ
			obj.focus();
			return false;
		}
	}
	return true;
}

/** 전체 총 확인  */
function ValidationCheck() { //전체
    var validate = true;
    var regExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;      // email 유효성검사
    var hanChecked = /^[가-힝a-zA-Z]{2,15}$/;      // 한글 유효성검사(2자리 이상 15자리 이하)
    var idChecked = /^[0-9a-zA-Z]{5,15}$/;      // 아이디 유효성검사(5자리 이상 15자리 이하)
    var phoneChecked = /^[0-9-]{1,16}$/; //휴대폰 번호 유효성 검사
         
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
    return validate; //값을 다시 전달받음 -> 확인버튼
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
                            <h2>CREATE MEMBER</h2>
                        </div>
                        <p> 회원가입 페이지입니다. 가입하실 회원님의 올바른 정보를 기입해주세요. 이미 가입하신 회원님께서는 <a href="/login.do" style="color: red">로그인</a> 페이지를 통해 로그인해주세요.
                        	잘못된 정보 입력시, 불이익을 받을 수 있으며 그에 대한 책임은 당사자에게 있습니다.</p>

                        <a href="" class="btn btn-lg">1:1 문의게시판</a>
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