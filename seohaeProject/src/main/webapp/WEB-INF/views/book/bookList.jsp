<%    
/**
 * 판매교재 목록
 * @author seohae
 * @since 2017. 11. 06.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 06 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/mainTop.do" />
<script>
$(function(){
	$("#upload").click(function(){
		location.href="bookUpload.do";
	});
	
});

function btnAdd(idx){
	if(confirm("해당 교재를 장바구니에 담겠습니까?") == true){
		$.ajax({
			type:"POST",
			url : "/cartInsert.do?pdNo="+idx,
			success: function(result){
				if(result == 'ok'){
					 alert("정상적으로 장바구니에 담았습니다.");
					 location.href="/cartList.do";
				}
				if(result == 'fal'){
					alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
					location.href="/login.do";
				}
			},
			error: function(result){
				alert('에러가 발생하였습니다.');
				return;
			},
		});
	}
}

function listUpdate(idx){ 
	document.viewTable.pdNo.value = idx;
	document.viewTable.method="POST";   		
	document.viewTable.action="<c:url value='/bookUpdate.do' />";   		
	document.viewTable.submit();
}

function reviewList(idx){
	$.ajax({
		type:"POST",
		url : "/reviewListCheck.do",
		success: function(result){
			if(result == 'ok'){
				alert("후기댓글을 작성하시면 10 bino가 적립됩니다.");
				document.viewTable.pdNo.value = idx;
				document.viewTable.method="POST";   		
				document.viewTable.action="<c:url value='/reviewList.do' />";   		
				document.viewTable.submit();
			}
			if(result == 'fal'){
				alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
				location.href="/login.do";
			}
		},
		error: function(result){
			alert('에러가 발생하였습니다.');
			return;
		},
	});
}
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
                        <button class="btn-md fil-cat filter active" data-filter="all">ALL</button>
                        <button class="btn-md fil-cat filter" data-rel="web" data-filter=".web">낮은가격</button>
                        <button class="btn-md fil-cat filter" data-rel="design" data-filter=".design">높은가격</button>
                        <button class="btn-md fil-cat filter" data-rel="flyers" data-filter=".flyers">상품명</button>
                    </div> 
                </div>
				
                <div style="clear:both;"></div>     
                <form name="viewTable" onsubmit="return flase;">
                		<input type="hidden" name="pdAmount" value="1">
		                <input type="hidden" name="pdNo" value="1">
		                <div id="portfoliowork"> 
		                	<c:forEach var="row" items="${list}">
								
			                    <div class="single_portfolio tile scale-anm web grid-item-width2 video" >
			                    	<c:if test="${sessionScope.userId == 'admin' }">
			                        	<a href="#" class="add_to_cart_button" onclick="javacscript:listUpdate('${row.pdNo }');">상품수정</a>                    
			                    	</c:if>   
									<img src="<c:url value="/img/${row.pdUrl}" />" width="430px" height="550px">
			                            <div class="grid_item_overlay">
			                                <div class="separator4"></div>
			                                <h3>[ ${row.pdCg} ] > ${row.pdName}</h3>
			                                <h4>${row.pdPrice}원</h4>
			                                <p>${row.pdInfo}</p>
											
			                                <button type="button" class="btn btn-lg m_t_10" onclick="javascript:reviewList('${row.pdNo}');">후기댓글</button>
											<button type="button" class="btn btn-lg m_t_10" onclick="javascript:btnAdd('${row.pdNo}');">장바구니</button>
			                            </div>
			                    </div>
			                </c:forEach>
		                </div>
		            </form>
		            <c:if test="${sessionScope.userId == 'admin' }">
						<div class="loginButton" style="text-align: center">
		                    <input style="margin: 10px 0 10px 0" type="button" class="btn btn-lg m_t_10" id="upload" value="상품등록" />
		                </div>
	                </c:if>
	                <div style="clear:both;"></div>  
            </div>
        </div>
    </div><!-- End off container --> 
</section> <!-- End off Work Section -->            
<%@ include file="/WEB-INF/include/include-footer.jsp" %>