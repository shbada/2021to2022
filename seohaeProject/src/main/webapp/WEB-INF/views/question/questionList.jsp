<%    
/**
 * 질문과답변 목록
 * @author seohae
 * @since 2017. 11. 10.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 10 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/mainTop.do" />
<script>
$(function(){
	/** 공지글 작성 페이지로 이동 */
	$("#write").click(function(){
		location.href="questionWrite.do";	
	});
});

function listDetail(qIdx){
	document.viewTable.qIdx.value=qIdx;
	document.viewTable.method="POST";
	document.viewTable.action="<c:url value='/questionDetail.do'/>";
	document.viewTable.submit();
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
                            <h2>QUESTION AND ANSWER</h2>
                        </div>
                        <p> 질문과 답변 게시판입니다. BINO에서 가장 중요하게 생각하는 것은, 바로 회원님들의 공부 습득입니다. BINO에서는 프로그래밍에 관한
                        	회원님의 질문과 또는 다른 회원님들의 질문글에 대한 답변을 중요시 여기고있습니다. 따라서 질문글 작성시 5 bino, 답변글 작성시 10 bino가
                        	적립되며 채택된 답변글의 회원님에게는 20 bino가 적립됨을 알려드립니다.</p>

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
                        <h2>QUESTION LIST</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                               <form name="viewTable">
                                <input type="hidden" name="qIdx" value="1">
								<input type="hidden" name="pageNo" id="pageNo" value="${pageVO.pageNo}" /> <input type="hidden" name="no" id="no" value="1" />
								<!-- <input type="hidden" name="password" id="password" value="" /> -->
								<table class="table table-striped b-t text-sm">
									<colgroup>
										<c:if test="${sessionScope.userId == 'admin' }">
											<col width="20" />
										</c:if>
										<col width="30" />
										<col width="30" />
										<col width="150" />
										<col width="70" />
										<col width="70" />
										<col width="70" />
										<col width="70" />
										<col width="70" />
									</colgroup>

									<thead>
										<tr>
											<c:if test="${sessionScope.userId == 'admin' }">
												<th><input type="checkbox" id="checkall" name="checkall"></th>
											</c:if>
											<th>번호</th>
											<th>질문분야</th>
											<th>제목</th>
											<th>질문자</th>
											<th>조회수</th>
											<th>채택여부</th>
											<th>등록날짜</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach var="i" items="${questionList}" varStatus="cnt">
											<tr>
												<c:if test="${sessionScope.userId == 'admin' }">
													<td><input type="checkbox" name="chkArray" value="${i.qIdx}"></td>
												</c:if>
												<td>${i.qIdx}</td>
												<td>${i.qCg}</td>
												<td style="text-align: left;">
													<a style="margin-left: 4px;" href="#" onclick="javascript:listDetail(${i.qIdx})"> 
														${i.qTitle}
													</a>
												</td>
												<c:if test="${i.qUserId == 'admin' }">
													<td>관리자</td>
												</c:if>
												<c:if test="${i.qUserId != 'admin' }">
													<td>${i.qUserId}</td>
												</c:if>
												<td>${i.qViewCnt}</td>
												<c:if test="${i.qAnsYn == 'Y' }">
													<td>완료</td>
												</c:if>
												<c:if test="${i.qAnsYn == 'N' }">
													<td>진행중</td>
												</c:if>
												<td>${i.qRegDe}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="text-center">
									<ul class="pagination pagination">
										<c:if test="${pageVO.pageNo != 0}">
											<c:if test="${pageVO.pageNo > pageVO.pageBlock}">
												<li>
													<a href="javascript:fn_movePage(${pageVO.firstPageNo})" style="text-decoration: none;">
														<i class="fa fa-chevron-left"></i>
														<i class="fa fa-chevron-left"></i>
													</a>
												</li>
											</c:if>
											<c:if test="${pageVO.pageNo != 1}">
												<li>
													<a href="javascript:fn_movePage(${pageVO.prevPageNo})" style="text-decoration: none;">
														<i class="fa fa-chevron-left"></i>
													</a>
												</li>
											</c:if>
											<c:forEach var="i" begin="${pageVO.startPageNo}" end="${pageVO.endPageNo}" step="1">
												<li>
													<c:choose>
														<c:when test="${i eq pageVO.pageNo}">
															<a href="javascript:fn_movePage(${i})" id="pageNo" style="text-decoration: none; background-color: #428bca;">
																<font style="font-weight: bold; color: #ffffff;">${i}</font>
															</a>
														</c:when>
														<c:otherwise>
															<a href="javascript:fn_movePage(${i})" style="text-decoration: none;">${i}</a>
														</c:otherwise>
													</c:choose>
												</li>
											</c:forEach>
											<c:if test="${pageVO.pageNo != pageVO.finalPageNo }">
												<li>
													<a href="javascript:fn_movePage(${pageVO.nextPageNo})" style="text-decoration: none;">
														<i class="fa fa-chevron-right"></i>
													</a>
												</li>
											</c:if>
											<c:if test="${pageVO.endPageNo < pageVO.finalPageNo }">
												<li>
													<a href="javascript:fn_movePage(${pageVO.finalPageNo})" style="text-decoration: none;">
														<i class="fa fa-chevron-right"></i>
														<i class="fa fa-chevron-right"></i>
													</a>
												</li>
											</c:if>
										</c:if>
									</ul>
								</div>
							</form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                          <button type="button" class="btn btn-lg m_t_10" name="write" id="write" data-toggle="tooltip" data-placement="bottom">작성</button>
                       	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>