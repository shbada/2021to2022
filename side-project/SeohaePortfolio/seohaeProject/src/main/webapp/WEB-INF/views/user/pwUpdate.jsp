<%    
/**
 * 비밀번호 변경 페이지
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
$(document).ready(function(){
	/** [뒤로가기] 버튼 클릭 시 */
    $("#editUser").click(function(){
        location.href="/editUser.do";
    });
	
    /** 입력 후 [확인] 버튼 클릭 시 */
    $("#pwUpdateOk").click(function(){
        var nowPw = $("#nowPw").val(); //수정된 값을 받아와 변수에 저장
        var userPw = $("#userPw").val(); //수정된 값을 받아와 변수에 저장
        var userPw2 = $("#userPw2").val(); //수정된 값을 받아와 변수에 저장
        // 비밀번호 입력값 공백처리
        if(nowPw == "") {
            alert("현재 비밀번호를 입력해주세요.");
            nowPw.foucs();
        } else if (userPw == "") {
            alert("변경할 비밀번호를 입력해주세요");
            userPw.focus();
        } else if (userPw2 == "") {
            alert("변경할 비밀번호 확인을 입력해주세요.");
            userPw2.focus();
        }
    	
  		var formData = $("form[name=pwFrm]").serialize(); //값을 다가지고와서 변수에 담는다 (리스트형식으로 값이 들어감)
  		
  		var validate = ValidationCheck(); //함수호출하여 return값을 validate에 담음!
		if(validate == false) return; //false-> 회원가입이 되지 않음.
  		
		$.ajax({ //Ajax 사용
			type: "POST", //post 방식
			url : "<c:url value='/pwUpdateOk.do'/>", //아이디,이메일 형식체크 -> 컨트롤러로 이동.
			data: "nowPw="+encodeURIComponent($('#nowPw').val())+"&userPw="+encodeURIComponent($('#userPw').val()),
			success:function(data){
				if(data == "ok"){ //컨트롤러에서 ok가 반환됬을때 실행되는 부분 
					if(validate == true){
						alert("비밀번호 변경이 완료되었습니다.");
						location.href="/main.do";
					}
				}
				if(data == "fal"){ //중복됬음을 알려주는 문구가 있었음에도 회원가입 버튼 클릭 시 막음을 위해
					alert("현재 비밀번호가 틀립니다.");
				}
			},
			error: function(data){ //안써도 되는부분인데 써주면 에러발생시 alert 실행
		           alert('에러가 발생하였습니다.');
		           return;
			}
		});
  	});
});

/** input [Password 재확인]에 함수호출 */
function onblur_passwordCheck(){ //패스워드 onblur 값
    if($("#userPw").val() != "" || $("#userPw2").val() != ""){ //패스워드, 패스워드 재확인이 널이 아니면
       if($("#userPw").val() != $("#userPw2").val()){ //패스워드, 패스워드 재확인이 같지 않으면
          $(".passwordCheck").append('<span class="createPasswordSpan" id="createPasswordSpan"></span>'); //지정
          $("#createPasswordSpan").css("color","red").css("font-size", "10px").css("margin-left","10px"); //스타일 지정
          document.getElementById("createPasswordSpan").innerHTML = "비밀번호가 일치하지 않습니다."; //일치하지않다는 문구 출력- 스타일 지정되어
          $("#userPw").val(""); //입력 값을 지움 -> 재확인과 맞지 않으니까 다시 입력하라고 다 지워버림
          $("#userPw2").val(""); //입력 값을 지움
          $("#userPw").focus(); //다시 입력하라고 마우스 커서를 패스워드 입력폼에 자동으로 갖다줌
       }else{ //패스워드, 패스워드 재확인 값이 같으면
    	  $(".passwordCheck").append('<span class="createPasswordSpan" id="createPasswordSpan"></span>'); //지정
          $("#createPasswordSpan").css("color","blue").css("font-size", "10px").css("margin-left","10px"); //스타일 지정
          document.getElementById("createPasswordSpan").innerHTML = "비밀번호가 일치합니다."; //일치하다는 문구 출력 - 스타일이 지정되어 출력
       }
    }
 };

 /** 전체 입력값 총 조건검사 */
function ValidationCheck() { //전체
    var validate = true;
    //아이디 + 전체
	if (!$("#userPw").val()) {
			alert("비밀번호를 입력하세요.");
			$("#userPw").focus();
			return false;
		} else if ($("#userPw").val().length > 16 || $("#userPw").val().length < 8) {
			alert("비밀번호는  8 ~ 16 자리로 입력해주세요.");
			return false;
		} else if (!$("#userPw2").val()) {
			alert("비밀번호 확인을 입력하세요.");
			$("#userPw2").focus();
			return false;
		} else if ($("#userPw").val() != $("#userPw2").val()) {
			alert("비밀번호가 일치 하지 않습니다.");
			$("#userPw2").focus();
			return false;
		} else {
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
                            <h2>EDIT PASSWORD</h2>
                        </div>
                        <p> 비밀번호 변경 페이지입니다. 현재 회원님의 비밀번호와 새롭게 변경할 비밀번호를 정확하게 입력해주세요.</p>

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
                        <h2>EDIT PASSWORD</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                                <form name="pwFrm" onsubmit="return false;">
                                    <div class="col-lg-8 col-md-8 col-sm-10 col-lg-offset-2 col-md-offset-2 col-sm-offset-1">
                                    	<!-- 아이디 -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                	<span style="color:#f00; float:left; margin-right:4px;">*</span><label class="control-label">현재 비밀번호</label>
                                                    <input type="password" class="form-control" placeholder="현재 비밀번호를 입력해주세요." id="nowPw" name="nowPw" autocomplete="off" required />
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 비밀번호 -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                	<span style="color:#f00; float:left; margin-right:4px;">*</span><label class="control-label">Password</label>
                                                    <input type="password" class="form-control" placeholder="새로운 비밀번호를 입력해주세요." id="userPw" name="userPw" autofocus autocomplete="off" required />
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <!-- 비밀번호 확인 -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                	<span style="color:#f00; float:left; margin-right:4px;">*</span><label class="control-label passwordCheck">Password 재확인</label>
                                                    <input type="password" class="form-control" placeholder="새로운 비밀번호를 다시한번 입력해주세요." id="userPw2" name="userPw2" autofocus autocomplete="off" onblur="onblur_passwordCheck();" />&nbsp;
                                                </div>
                                            </div>
                                        </div>
                                    </div> 
                                </form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                        	<button type="button" id="pwUpdateOk" class="btn btn-lg m_t_10">확인</button>
							<button type="button" id="editUser" class="btn btn-lg m_t_10">취소</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>