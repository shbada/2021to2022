<%    
/**
 * 비밀번호 찾기
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
<jsp:include page="/mainTop.do" />
<script>
Lpad = function(str, len) {
    str = str + "";
    while (str.length < len) {
        str = "0" + str;
    }
    return str;
}

function joinClearTime() {
	joinSecond = 180;       // 3분
}

joinTimer = function(){
	var timer = document.getElementById("joinEmTime");
	rHour = parseInt(joinSecond / 3600);
	rHour = rHour % 60;
	rMinute = parseInt(joinSecond / 60);
	rMinute = rMinute % 60;
	
	rSecond = joinSecond % 60;
	if(joinSecond > 0){
		timer.innerHTML = Lpad(rMinute, 2) + "분" + Lpad(rSecond, 2) + "초";
		joinSecond--;
		timerchecker = setTimeout("joinTimer()",1000);  //1초 간격으로 체크
	}else{
		//재인증
		$("#joinCode").remove();
		$(".joinSpan").remove();
		$(".joinTime").remove();
		$("#joinCheck").remove();
		$(".falseSpan").remove();
		var str = "<span class='falseSpan'> 인증시간이 초과하였습니다.<br> 인증코드를 다시 발급 받아 재인증 해주세요.";
		$(".joinCodeInsert").append(str);
		return;
	}
}

$(function(){
	$(".category").click(function(){
	    var n = $(".category").index(this);
		var pdCg= $(".category:eq("+n+")").attr('value');
		location.href="/category.do?pdCg="+pdCg;
	})
	
	$("#join").click(function(){
		location.href="/join.do";
	});
	
	$("#mypage").click(function(){
		location.href="/myPage.do"
	});
	
	$("#manage").click(function(){
		location.href="/userManage.do";
	});
	$("#item").click(function(){
		location.href="/itemManage.do";
	});
   $("#emailIdBtn").click(function(){
	      var regExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;      // email 유효성검사
	           
	      if(!$("#email").val()){
			  alert("이메일을 입력하세요.");
			  $("#email").focus();
		      return;
		  }
	      if($("#email").val()) {
		  	if(!regExp.test($("#email").val())){
         	alert("이메일 주소가 유효하지 않습니다"); 
             $("#email").focus();
             return false;
		  	}
		  }
	      var obj = document.emailFrm;
	      obj.email.value = $("#email").val().replace(/\s/gi,"");
	      obj.action = "<c:url value='/sendMail.do' />";
	      obj.method = "post";
	      obj.submit();
	      lodingBarStart();
	});
   
   $("#emailJoinBtn").click(function(){
		var regExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;      // email 유효성검사
		if(!$("#joinEmail").val()){
		 	alert("이메일을 입력하세요.");
			$("#joinEmail").focus();
		    return;
		}
	    if($("#joinEmail").val()) {
		 	if(!regExp.test($("#joinEmail").val())){
         		alert("이메일 주소가 유효하지 않습니다"); 
             	$("#joinEmail").focus();
              	return false;
		  	}
		}
		$("#joinCode").remove();
		$(".joinSpan").remove();
		$(".joinTime").remove();
		$("#joinCheck").remove();
		$(".falseSpan").remove();
		$.ajax({
			type : "post",
			url : "<c:url value='/joinCode.do?joinEmail="+$("#joinEmail").val().replace(/\s/gi,"")+"' />",
			success : function(data){
				if(data){
					var str = "<input type='text' class='form-control' name='joinCode' id='joinCode' placeholder='인증번호를 입력해주세요.' style='margin-top: 10px;' />&nbsp;<span class='joinSpan'>남은 시간 :</span><span class='joinTime'><em id='joinEmTime'></em></span>";
	                str += "&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-sm btn-danger' id='joinCheck'>확인</button>";
	                $('.joinCodeInsert').append(str);
	                joinClearTime();
	                joinTimer();
				}else{
					alert("잘못된 이메일 입니다.");
					return;
				}
			}
		});
	});
	
	$(".joinCodeInsert").on("click","button",function(){  //버튼찾아서 클릭되면 실행
		$.ajax({
			type : "post",
			url : "<c:url value='/joinCodeCheck.do' />",
			data : {joinCode : $("#joinCode").val()},
			dataType : "text",
			success : function(result){
				if(result == "certification"){
					var obj = document.emailPwFrm;
					obj.joinEmail.value = $("#joinEmail").val().replace(/\s/gi,"");
					obj.action = "<c:url value='/resetPassword.do' />";
			        obj.method = "post";
			        obj.submit();
					//var str = "<span class='successSpan'>초기화된 비밀번호가 메일로 전송되었습니다.</span>";
					$("#joinCode").remove();
					$(".joinSpan").remove();
					$(".joinTime").remove();
					$("#joinCheck").remove();
					$(".falseSpan").remove();
					$("#emailJoinBtn").remove();
					$(".joinCodeInsert").append(str);
				}else{
					$(".falseSpan").remove();
					var str = "<span class='falseSpan'>인증코드를 다시 확인해주세요.</span>";
					$('.joinCodeInsert').append(str);
				}
			}
		});
	});
});
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
                            <h2>FIND PASSWORD</h2>
                        </div>
                        <p> 비밀번호를 잊어버리셨나요? 회원님의 잃어버린 비밀번호를 찾기위해서 가입하실때 입력하신 이메일 입력이 필요합니다.
                        	가입하실때 입력하신 회원님의 이메일을 정확히 입력해주세요.
                        	아이디를 찾고싶으시면, <a href="/idFind.do" style="color: red">아이디 찾기</a> 게시판으로 이동해주세요.</p>

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
                        <h2>FIND PASSWORD</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                                <form name="emailPwFrm" onsubmit="return false;"> 
                                    <div class="col-lg-8 col-md-8 col-sm-10 col-lg-offset-2 col-md-offset-2 col-sm-offset-1">
                                    	<!-- 아이디 -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                     <input type="email" class="form-control"  placeholder="이메일을 입력해주세요." id="joinEmail" name="joinEmail" autofocus autocomplete="off" required />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                	<div class="joinCodeInsert" style="float: left;"></div>
                                                    <div style="margin-top: 10px">${resultPwMsg}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> 
                                </form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                           <button type="button" id="emailJoinBtn" class="btn btn-lg m_t_10">비번 초기화</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>