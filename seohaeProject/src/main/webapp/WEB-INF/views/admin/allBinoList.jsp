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
<script>

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

                        <a href="" class="btn btn-lg">1:1 문의게시판</a>
                    </div>
                </div>
            </div>
        </div><!--End of row -->
    </div><!--End of container -->
</section><!--End of history -->

<section id="feature" class="feature sections">
    <div class="container">
        <div class="row">
            <div class="main_feature text-center">
				 <div class="head_title text-center">
                     <h2>ALL BINO LIST</h2>
                     <div class="subtitle">
                         Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                     </div>
                     <div class="separator"></div>
                 </div><!-- End off Head_title -->
                 <c:forEach var="binoList" items="${binoList}" varStatus="cnt">
	                <div class="col-sm-3">
	                    <div class="single_feature">
	                        <div class="single_feature_icon">
	                            <i class="fa fa-clone"></i>
	                        </div>
	
	                        <h4>${binoList.userId}</h4>
	                        <div class="separator3"></div>
	                        <p>
	                        <c:if test="${binoList.binoYn == 'Y' }">
	                        	(+) 적립
	                        </c:if>
	                        <c:if test="${binoList.binoYn == 'N' }">
	                        	(-) 사용
	                        </c:if>
	                        </p>
	                        <p>${binoList.binoCg}</p>
	                        <p>${binoList.binoRegdate}</p>
	                        <p>${binoList.bino} bino</p>
	                    </div>
	                </div>
	            </c:forEach>
            </div>
        </div>
    </div><!--End of container -->
</section><!--End of feature Section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>