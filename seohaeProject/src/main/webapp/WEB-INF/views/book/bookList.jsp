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
                            <h2>BOOK BUY</h2>
                        </div>
                        <p> 프로그래밍 관련 교재 판매 게시판입니다. BINO는 프로그래밍 관련 질문과 답변 및 자료 공유 등에 대해 정해진 포인트만큼
                        	적립을 도와드리고 있습니다. 교재 구매시, 현재까지 적립된 포인트를 사용할 수 있습니다. 현재 회원님의 포인트 내역을 알고싶으시면,
                        	<a href="#" style="color: red">포인트 내역</a> 게시판으로 이동해주세요.</p>

                        <a href="" class="btn btn-lg">1:1 문의게시판</a>
                    </div>
                </div>
            </div>
        </div><!--End of row -->
    </div><!--End of container -->
</section><!--End of history -->

<section id="portfolio" class="portfolio sections">
    <div class="container-fluid">
        <div class="row">
            <div class="main_portfolio">
                <div class="col-sm-12">
                    <div class="head_title text-center">
                        <h2>BOOK BUY</h2>
                        <div class="subtitle">
                            It has survived not only five centuries, but also the leap scrambled it to make a type.
                        </div>
                        <div class="separator"></div>
                    </div>
                </div>
                <div class="work_menu text-center">
                    <div id="filters" class="toolbar mb2 mt2">
                        <a href="/productList.do" class="btn-md fil-cat filter active" data-filter="all">ALL</a>
                        <a href="/viewLowPrice" class="btn-md fil-cat filter" data-rel="web" data-filter=".web">낮은가격</a>
                        <a href="/viewHighPrice" class="btn-md fil-cat filter" data-rel="design" data-filter=".design">높은가격</a>
                        <a href="/viewName.do" class="btn-md fil-cat filter" data-rel="flyers" data-filter=".flyers">상품명</a>
                    </div> 

                </div>

                <div style="clear:both;"></div>     
                <div id="portfoliowork"> 
                	<c:forEach var="row" items="${list}">
	                    <div class="single_portfolio tile scale-anm web grid-item-width2 video" >
	                       <a href="#" class="link" onclick="javacscript:listDetail('${row.product_no }');">
								<img src="<c:url value="/img/${row.product_url}" />" width="430px" height="550px">
							</a>
	                        <a href="/img/${row.product_url}" class="portfolio-img">
	                            <div class="grid_item_overlay">
	                                <div class="separator4"></div>
	                                <h3>${row.product_name}</h3>
	                                <p>${row.product_price}원</p>
	                            </div>
	                        </a>
	                    </div>
	                </c:forEach>
                </div>

                <div style="clear:both;"></div>  
            </div>
        </div>
    </div><!-- End off container --> 
</section> <!-- End off Work Section -->            
<%@ include file="/WEB-INF/include/include-footer.jsp" %>