<%    
/**
 * 메인페이지 상단 부분
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function sessionLogOut() {
    if (confirm('로그아웃 하시겠습니까?')) {
     	location.href="/logOut.do";
   }
}

function MessageList(){ 
	window.open("/messageList.do","쪽지함","width=650, height=600, top:100, left:100");
}
</script>
<div class='preloader'><div class='loaded'>&nbsp;</div></div>
	<div class="culmn">
	    <header id="main_menu" class="header navbar-fixed-top">            
	        <div class="main_menu_bg">
	            <div class="container">
	                <div class="row">
	                    <div class="nave_menu">
	                        <nav class="navbar navbar-default">
	                            <div class="container-fluid">
	                                <!-- Brand and toggle get grouped for better mobile display -->
	                            <div class="navbar-header">
	                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	                                    <span class="sr-only">Toggle navigation</span>
	                                    <span class="icon-bar"></span>
	                                    <span class="icon-bar"></span>
	                                    <span class="icon-bar"></span>
	                                </button>
	                                <a class="navbar-brand" href="/main.do">
	                                    <img src="assets/images/logo.png"/>
	                                </a>
	                            </div>
	
	                            <!-- Collect the nav links, forms, and other content for toggling -->
	
	                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                            	<c:if test="${sessionScope.userId  != null && sessionScope.userLevel == 'USER'}">
		                           		<ul class="nav navbar-nav navbar-left">
		                                    <li><a href="#" onclick="javacscript:MessageList();">쪽지함 (${messageCount })</a></li>
		                                    <li><a href="/editUser.do" >회원정보수정</a></li>
		                                    <li><a href="/cartList.do" >장바구니</a></li>
		                                    <li><a href="#" onclick="javascript:sessionLogOut()">로그아웃</a></li>
		                                </ul>
	                                </c:if>
	                            	<c:if test="${sessionScope.userLevel  == 'ADMIN' }">
		                           		<ul class="nav navbar-nav navbar-left">
		                                    <li><a href="#" onclick="javacscript:MessageList();">쪽지함 (${messageCount })</a></li>
		                                    <li><a href="/allMemberList.do" >모든회원관리</a></li>
		                                    <li><a href="/allBinoList.do" >bino 전체내역</a></li>
		                                    <li><a href="/cartList.do" >모든구매내역</a></li>
		                                    <li><a href="/oneAdminList.do" >1:1 문의내역</a></li>
		                                    <li><a href="#" onclick="javascript:sessionLogOut()">로그아웃</a></li>
		                                </ul>
	                                </c:if>
	                                <ul class="nav navbar-nav navbar-right">
	                                    <li><a href="/main.do">HOME</a></li>
	                                    <li><a href="/noticeList.do">공지사항</a></li>
	                                    <li><a href="/freeBoardSelectList.do">자유게시판</a></li>
	                                    <li><a href="#portfolio">자격증 정보</a></li>
	                                    <li><a href="#pricing">취업 정보</a></li>
	                                    <li><a href="/bookList.do">교재구매</a></li>
	                                    <li><a href="#team">고객센터</a></li>
	                                </ul>
	                            </div>
	                        </div>
	                    </nav>
	                </div>	
	            </div>
	        </div>
	    </div>
	</header> <!--End of header -->
	<!--home Section -->
	<section id="home" class="home">
	    <div class="overlay">
	        <div class="home_skew_border">
	            <div class="container">
	                <div class="row">
	                    <div class="col-sm-12 ">
	                        <div class="main_home_slider text-center">
	                        	<c:if test="${sessionScope.userId  == null }">
		                            <div class="single_home_slider">
		                                <div class="main_home wow fadeInUp" data-wow-duration="700ms">
		                                    <h3>Our Clients Are Our First Priority</h3>
		                                    <h1>WELCOME TO BINO</h1>
		                                    <div class="separator"></div>
		                                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's 
		                                        standard dummy text ever since the 1500s, when an unknown printer took a galley 
		                                        of type and scrambled it to make a type specimen book.</p>
		                                    <div class="home_btn">
		                                        <a href="/login.do" class="btn btn-lg m_t_10">로그인</a>
		                                        <a href="/createUser.do" class="btn btn-default">회원가입</a>
		                                    </div>
		                                </div>
		                            </div>
		                        </c:if>
		                        <!--  -->
		                        <c:if test="${sessionScope.userId  != null }">
		                            <div class="single_home_slider">
		                                <div class="main_home wow fadeInUp" data-wow-duration="700ms">
		                                	<a href="/binoList.do"><h3>${sessionScope.userId } = ${binoSum } bino</h3></a>
		                                	<div class="home_btn">
		                                    	<a href="#" class="btn btn-default">기초 개발 언어의 질문과 답변</a>
		                                    </div>
		                                    <div class="separator"></div>
		                                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's 
		                                        standard dummy text ever since the 1500s, when an unknown printer took a galley 
		                                        of type and scrambled it to make a type specimen book.</p>
		                                    <div class="home_btn">
		                                        <a href="/qJavaList.do" class="btn btn-lg m_t_10">JAVA</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">C</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Python</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Ruby</a>
		                                    </div>
		                                </div>
		                            </div>
		                        </c:if>
		                        <!--  -->
		                        <c:if test="${sessionScope.userId  != null }">
		                            <div class="single_home_slider">
		                                <div class="main_home wow fadeInUp" data-wow-duration="700ms">
		                                	<a href="/binoList.do"><h3>${sessionScope.userId } = ${binoSum } bino</h3></a>
		                                    <div class="home_btn">
		                                    	<a href="#" class="btn btn-default">개발 분야별 언어의 질문과 답변</a>
		                                    </div>
		                                    <div class="separator"></div>
		                                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's 
		                                        standard dummy text ever since the 1500s, when an unknown printer took a galley 
		                                        of type and scrambled it to make a type specimen book.</p>
		                                    <div class="home_btn">
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Web</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Android</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Network</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Database</a>
		                                    </div>
		                                </div>
		                            </div>
		                        </c:if>
		                        <!--  -->
		                        <c:if test="${sessionScope.userId  != null }">
		                            <div class="single_home_slider">
		                                <div class="main_home wow fadeInUp" data-wow-duration="700ms">
		                                	<a href="/binoList.do"><h3>${sessionScope.userId } = ${binoSum } bino</h3></a>
		                                    <div class="home_btn">
		                                    	<a href="#" class="btn btn-default">프로그래밍 언어 무료 강의</a>
		                                    </div>
		                                    <div class="separator"></div>
		                                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's 
		                                        standard dummy text ever since the 1500s, when an unknown printer took a galley 
		                                        of type and scrambled it to make a type specimen book.</p>
		                                    <div class="home_btn">
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">JAVA</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">C</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Python</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">JSP</a>
		                                        <br/>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">SPRING</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Html&Css</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Javascript</a>
		                                        <a href="/editUser.do" class="btn btn-lg m_t_10">Database</a>
		                                    </div>
		                                </div>
		                            </div>
		                        </c:if>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="scrooldown">
	                <a href="#feature"><i class="fa fa-arrow-down"></i></a>
	            </div>
	        </div>
	    </div>
	</section><!--End of home section -->
