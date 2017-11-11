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
<jsp:include page="/mainTop.do" />
<style>
.btn-outline-warning {
    color: #f39c12;
    background-color: #fff;
    border-color: #f39c12;
}
.btn-outline-warning:hover {
    color: #fff;
    background-color: #f1c40f;
    border-color: #f1c40f;
}
.btn-outline-danger {
    color: #c0392b;
    background-color: #fff;
    border-color: #c0392b;
}
.btn-outline-danger:hover {
    color: #fff;
    background-color: #e74c3c;
    border-color: #e74c3c;
}
</style>
<script>
$(document).ready(function(){
    $("#goback").click(function(){
        location.href="/allMemberList.do";
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
                            <h2>ADMIN</h2>
                        </div>
                        <p> 관리자 전용 페이지입니다.</p>

                        <a href="/oneAdminList.do" class="btn btn-lg">1:1 문의게시판</a>
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
                        <h2>ALL MEMBER LIST</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                               <form method="post" action="#">
			                   <table cellspacing="0" class="table table-striped b-t text-sm">
			                       <tbody>
			                           <tr class="cart-subtotal">
			                               <th>회원 아이디</th>
			                               <td><span class="amount">${UserVo.userId }</span></td>
			                           </tr>
			                           
			                           <tr class="cart-subtotal">
			                               <th>회원 이름</th>
			                               <td><span class="amount">${UserVo.userNm }</span></td>
			                           </tr>
			                           
			                           <tr class="cart-subtotal">
			                               <th>회원 이메일</th>
			                               <td><span class="amount">${UserVo.userEmail }</span></td>
			                           </tr>
			                           
			                           <tr class="cart-subtotal">
			                               <th>회원 연락처</th>
			                               <td><span class="amount">${UserVo.userPhone }</span></td>
			                           </tr>
			                           
			                           <tr class="cart-subtotal">
			                               <th>회원 주소</th>
			                               <td><span class="amount">${UserVo.userZipCode } ${UserVo.userFirstAddr } ${UserVo.userSecondAddr }</span></td>
			                           </tr>
			                           
			                           <tr class="cart-subtotal">
			                               <th>가입날짜</th>
			                               <td><span class="amount">${UserVo.regDe }</span></td>
			                           </tr>
			                          
			                       </tbody>
			                   </table>
			               </form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                          <button type="button" class="btn btn-lg btn-primary btn-block" id="goback">목록으로 돌아가기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->


<%@ include file="/WEB-INF/include/include-footer.jsp" %>