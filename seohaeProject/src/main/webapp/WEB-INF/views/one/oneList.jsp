<%    
/**
 * 1:1 문의 리스트(회원)
 * @author seohae
 * @since 2017. 11. 11.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 11 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/mainTop.do" />
<script>
/** 페이징버튼 클릭시, 이동페이지 */
function fn_movePage(pageNo){ //클릭된 pageNo값을 받는다.
	$("input[name=pageNo]").val(pageNo); //pageNo값을 갖음 
	var obj = document.viewTable; //form태그의 name값 = viewTable.
	obj.method="POST";
	obj.action="<c:url value='/oneList.do' />"; //컨트롤러로 이동 -> 해당 게시글을 목록에 뿌려주기위함.
	obj.submit();         
}

/** 게시글에 보여지는 총 게시글 개수를 지정하고 [확인]버튼 클릭시 */
$(document).ready(function(){
	/** [글쓰기] 버튼 클릭시 */
	$("#btnWrite").click(function(){ //글쓰기 버튼
		document.viewTable.method="POST";   		
		document.viewTable.action="<c:url value='/oneWrite.do' />"; //컨트롤러로 이동   		
		document.viewTable.submit();
	});
});

/** 상세페이지로 이동하는 함수_회원 문의글 (게시글 제목 클릭시)*/
function listMemberDetail(idx){ 
	document.viewTable.reqNo.value = idx;
	document.viewTable.method="POST";   		
	document.viewTable.action="<c:url value='/oneDetail.do' />"; //컨트롤러로 이동!	
	document.viewTable.submit();
}

/** 상세페이지로 이동하는 함수_관리자 답변글 (게시글 제목 클릭시)*/
function listDetail(idx){ 
	document.viewTable.reqReNo.value = idx; //freeBorIdx에 받아온 게시글번호 idx값을 담음
	document.viewTable.method="POST";   		
	document.viewTable.action="<c:url value='/oneMemberAdminDetail.do' />"; //컨트롤러로 이동!	
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
                            <h2>1:1 REQUEST</h2>
                        </div>
                        <p> 1:1 문의 게시판입니다. BINO를 이용하시는 회원님들의 모든 의견과 충고를 존중하겠습니다. 
                        	BINO에게 질문 또는 의견이 있으신 분들은 1:1 문의글을 작성해주세요. </p>

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
                        <h2>1:1 REQUEST</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                               <form name="viewTable" onsubmit="return flase;">
								<input type="hidden" name="userId" value="${OneVo.userId }" /> 
								<input type="hidden" name="reqNo" value="1">
								<input type="hidden" name="reqReNo" value="1">
								<div class="mainTitle">
									<h1>
										<em><strong>1:1 문의내역 </strong></em>
									</h1>
								</div><hr />
								<!-- 페이징 개수 선택 기능 -->
								<section class="panel">
									<!-- 글 목록 구하기 -->
									<div class="table-responsive">
										<table class="table table-striped b-t text-sm" summary="자유게시판">
											<caption></caption>
											<colgroup>
												<col width="5%">
												<col width="5%">
												<col width="30%">
												<col width="20">
												<col width="20%">
												<col width="25%">					
											</colgroup>
											<thead>
												<tr>
													<th style="text-align: center;">
														<input type="checkbox" id="checkall" name="checkall" />
													</th>
													<th>번호</th>
													<th class="th-sortable" style="text-align: center" data-toggle="class">제목 </th>
													<th>작성자</th>
													<th>작성일</th>
													<th>답변여부</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="boardList" items="${boardList }">
													<tr>
														<td style="text-align:center;">
															<input type="checkbox" name="chkArry" value="${boardList.reqNo }">
														</td>
														<td style="text-align: center;">${boardList.reqNo }</td>
														<td class="tlWrap">
																<a href="#" class="link" onclick="javacscript:listMemberDetail('${boardList.reqNo }');">
																	${boardList.reqTitle}
																</a>
															</td>
														<td>${boardList.userId }</td>
														<td>${boardList.regDe }</td>
														<td>
															<c:if test="${boardList.reqReYn == 'Y' }">
																답변완료
															</c:if>
															<c:if test="${boardList.reqReYn == 'N' }">
																미완료
															</c:if>
														</td>
													</tr>
													<c:if test="${boardList.reqReNo != 0}">
														<tr>
															<td style="text-align:center;">
																<input type="checkbox" name="chkArry" value="${boardList.reqReNo }">
															</td>
															<td style="text-align: center;">${boardList.reqReNo }</td>
															<td class="tlWrap">
																<a href="#" class="link" onclick="javacscript:listDetail('${boardList.reqReNo }');">
																	<span class="writeNew"> &nbsp;&nbsp;&nbsp;&nbsp;RE: 답변글의 작성이 완료되었습니다.</span>
																</a>
															</td>
																<td>관리자</td>
																<td>${boardList.regDe }</td>
															</tr>
														</c:if>
												</c:forEach>
										
												<c:if test="${empty boardList }">
													<c:choose>
														<c:when test="${sessionScope.userLevel == 'ADMIN'}">
															<tr>
																<td colspan="7" style="text-align: center">
																<span class="noData">검색 조건에 맞는 데이터가 없습니다.</span></td>
															</tr>
														</c:when>
														<c:otherwise>
															<tr>
																<td colspan="8" style="text-align: center">
																<span class="noData">검색 조건에 맞는 데이터가 없습니다.</span></td>
															</tr>
														</c:otherwise>
													</c:choose>
												</c:if>
											</tbody>
										</table>
									</div>
								</section>
							</form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                         <button type="button" class="btn btn-lg m_t_10" id="btnWrite">1:1 문의하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>