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
                        	아이디를 잊으셨으면, <a href="/createUser.do" style="color: red">아이디 찾기</a> 게시판으로 이동해주세요. 
                        	비밀번호를 잊으셨으면, <a href="/createUser.do" style="color: red">비밀번호 찾기</a> 게시판으로 이동해주세요.</p>

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
                        <h2>LOGIN</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                                <form action="#" id="formid">
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
                           <button type="button" class="btn btn-lg m_t_10" name="loginChked" id="loginChked" data-toggle="tooltip" data-placement="bottom" data-original-title="로그인!">로그인</button>&nbsp;
	                     <button type="button" class="btn btn-lg m_t_10" id="userCreate" data-toggle="tooltip" data-placement="bottom" data-original-title="회원가입!">회원가입</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>