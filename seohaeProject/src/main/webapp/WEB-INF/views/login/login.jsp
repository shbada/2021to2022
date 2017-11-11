<%    
/**
 * 로그인 페이지
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
<script>
	$(function(){
	         if ("${param.MOVE_ERR}" == "ERRORCONTECT") {
	            alert("잘못된 접근입니다. 다시 로그인해주세요.");
	            top.location="/login/login.do";
	         }
	         if ("${param.GBN}" == "TIMEOUT") {
	            top.location="/login/login.do";
	         }
	         if ("${param.GBN}" == "SESSIONOUT") {
	            alert("세션이 만료되었습니다. 다시 로그인 하여주십시요.");
	            top.location="/login/login.do";
	         }
	         if ("${param.LOGIN_ERR}" == "IDNE") {
	            alert("ID 또는 비밀번호가 일치하지 않습니다.");
	         }
	         if ("${param.LOGIN_ERR}" == "IDBL") {
	            alert("해당 아이디는 계정잠김 상태입니다.");
	         }
	         if ("${param.LOGIN_ERR}" == "PWNE") {
	            alert("ID 또는 비밀번호가 일치하지 않습니다.");
	         }
	         if ("${param.LOGIN_ERR}" == "PWNE5") {
	            alert("5회 이상 로그인 실패하였습니다. 관리자에게 초기화 요청하십시오.");
	         }
	         if ("${param.LOGIN_ERR}" == "PWCH") {
	            alert("비밀번호를 재설정 하셔야 합니다.");
	            modalObj.pageStart();
	            modalObj.modalOpen("chg");
	         }      
	         if ("${param.LOGIN_ERR}" == "PWCH90") {
	            alert("90일 동안 비밀번호를 변경하지 않았습니다. 비밀번호를 갱신 하셔야 합니다.");
	            modalObj.pageStart();
	            modalObj.modalOpen("chg");
	         }      
	         if ("${param.LOGIN_ERR}" == "60DATE") {
	            alert("60일간 사용하지 않아 사용자 계정이 정지되었습니다. \n 시스템 담당자에게 문의해주세요.");
	         }
	         if ("${param.LOGIN_ERR}" == "90DATE") {
	            alert("90일간 사용하지 않아 사용자 계정이 비활성화되었습니다. \n 시스템 담당자에게 문의해주세요.");
	         }
	         if ("${param.LOGIN_ERR}" == "IDDUP") {
	            // var goUrl = 'http://' + $(location).attr('host');
	            var goUrl = 'http://' + $(location).attr('host') + "/main.do";
	            if (confirm('이미 접속중입니다. \n 로그아웃 하시겠습니까?')) {
	               $.ajax({
	                    type:"POST",
	                    url:"/login/mainLogOut.do",
	                    dataType:"JSON",
	                    async: false,
	                    complete: function() {
	                       location.href = goUrl;
	                    },
	                    error: function(xhs, status, error) {
	                  }
	                 });
	            }else location.href = 'http://' + $(location).attr('host');
	         }
	         
	         $(".login-form").keyup(function(e){if(e.keyCode == 13)  login(); });
	         
	         $("#logout").click(function(){
	 			var goUrl = 'http://' + $(location).attr('host') ;
	 			   if (confirm('로그아웃 하시겠습니까?')) {
	 			      $.ajax({
	 			           type:"POST",
	 			           url:"/login/logOut.do",
	 			           dataType:"JSON",
	 			           async: false,
	 			           complete: function() {
	 			              location.href = goUrl;
	 			           },
	 			           error: function(xhs, status, error) {
	 			         }
	 			        });
	 			   }
	 		});
	         
	         $("#join,#userCreate").click(function(){
	    		location.href="/join.do";
	    	 }); 
	         
	         $("#idFind").click(function(){
	        	location.href="/idFind.do"; 
	         });
	         $("#pwFind").click(function(){
	        	location.href="/pwFind.do"; 
	         });
	         $("#loginChecked").click(function(){
	            if($("#userId").val() == "") {
	               alert("아이디를 입력하세요!!");
	               $("#userId").focus();
	               return;
	            }
	            if($("#userPw").val() == "") {
	               alert("패스워드를 입력하세요");
	               $("#userPw").focus();
	               return;
	            }
	            
	            var obj = document.loginFrm;
	            obj.method="post";
	            obj.action="/login/loginCheck.do";
	            obj.submit();
	     });
	});
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
                            <h2>LOGIN</h2>
                        </div>
                        <p> 가입하신 회원님의 아이디와 비밀번호를 입력해주세요. 가입하지않은 회원님께서는 <a href="/createUser.do" style="color: red">회원가입</a> 페이지를 통해 회원가입 부탁드립니다.
                        	아이디를 잊으셨으면, <a href="/idFind.do" style="color: red">아이디 찾기</a> 게시판으로 이동해주세요. 
                        	비밀번호를 잊으셨으면, <a href="/pwFind.do" style="color: red">비밀번호 찾기</a> 게시판으로 이동해주세요.</p>

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
                        <h2>LOGIN</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                                <form class="panel-body" name="loginFrm">
                                    <div class="col-lg-8 col-md-8 col-sm-10 col-lg-offset-2 col-md-offset-2 col-sm-offset-1">
                                    	<!-- 아이디 -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                     <input type="text" class="form-control" name="userId" id="userId" placeholder="ID를 입력해주세요." />
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 비밀번호 -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <input type="password" placeholder="PASSWORD를 입력해주세요." class="form-control"  name="userPw" id="userPw" autocomplete="off"/>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="checkbokx" style="text-align:right">
	                                        <label> 
												<input type="checkbox" name="useCookie">
												자동로그인
											</label>
										</div>
                                    </div> 
                                </form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                           <button type="button" class="btn btn-lg m_t_10" name="loginChecked" id="loginChecked" data-toggle="tooltip" data-placement="bottom" data-original-title="로그인!">로그인</button>&nbsp;
	                     <button type="button" class="btn btn-lg m_t_10" id="userCreate" data-toggle="tooltip" data-placement="bottom" data-original-title="회원가입!">회원가입</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>