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
                            <h2>NOTICE</h2>
                        </div>
                        <p> 공지사항 게시판입니다. 공지사항 게시글을 반드시 숙지해주시고, 공지사항 게시글을 읽지 않아 불이익을 당한 회원은 당사자에게 책임이 있음을 알려드립니다.</p>

                        <a href="" class="btn btn-lg">BROWSE OUR WORK</a>
                    </div>
                </div>
            </div>
        </div><!--End of row -->
    </div><!--End of container -->
</section><!--End of history -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>