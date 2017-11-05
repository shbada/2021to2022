<%    
/**
 * 아이디 찾기
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
$(function(){
   
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
                            <h2>FIND ID</h2>
                        </div>
                        <p> 아이디를 잊어버리셨나요? 회원님의 잃어버린 아이디를 찾기위해서 가입하실때 입력하신 이메일 입력이 필요합니다.
                        	가입하실때 입력하신 회원님의 이메일을 정확히 입력해주세요.
                        	비밀번호를 찾고싶으시면, <a href="/pwFind.do" style="color: red">비밀번호 찾기</a> 게시판으로 이동해주세요.</p>

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
                        <h2>FIND ID</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                                <form name="emailFrm" onsubmit="return false;"> 
                                    <div class="col-lg-8 col-md-8 col-sm-10 col-lg-offset-2 col-md-offset-2 col-sm-offset-1">
                                    	<!-- 아이디 -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                     <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력해주세요." autofocus autocomplete="off" required />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <div style="margin-top: 10px">${resultMsg}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> 
                                </form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                           <button type="button" id="emailIdBtn" class="btn btn-lg m_t_10">이메일 전송</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>