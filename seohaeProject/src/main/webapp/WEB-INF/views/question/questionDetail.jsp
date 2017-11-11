<%    
/**
 * 질문과답변 상세정보
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
$(function(){
	viewCnt(); //조회수

   /** 목록으로 이동 */
   $("#list").click(function(){
	   location.href="qJavaList.do";
   });
	
   $("#answerWrite").click(function(){
	  var obj = document.noFrm;
	  obj.method = "POST";
	  obj.action = "<c:url value='answerWrite.do' />";
	  obj.submit();
   });
});
   
/** 조회수 */
function viewCnt(){
	$.ajax({
		type : "get",
		url : "<c:url value='/questionViewCnt.do?qIdx=${detail.qIdx}' />",
		success : function(result){
			$("#questionView").html(result);
		}
	});
}

function listDetail(aIdx){
	document.noFrm.aIdx.value=aIdx;
	document.noFrm.method="POST";
	document.noFrm.action="<c:url value='/answerDetail.do'/>";
	document.noFrm.submit();
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
                        <h2>QUESTION WRITE</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                              <form name="noFrm">
                              		 <c:if test="${detail.qUserId != sessionScope.userId }">
									<button type="button" class="btn btn-lg m_t_10" id="btnLike" style="text-align: right;">추천하기</button>
								</c:if>
								<hr>
				         		<input type="hidden" name="qIdx" value ="${detail.qIdx}" />
				         		<input type="hidden" name="aIdx" value ="1" />
				         		<input type="hidden" name="qUserId" value ="${detail.qUserId}" />
				         		<input type="hidden" name="qDesc" value ="${detail.qDesc}" />
				         		<input type="hidden" name="secretReply" />
									<table class="table table-striped b-t text-sm">
									<colgroup>
										<col width="20%">
										<col width="30%">
										<col width="20%">
										<col width="20%">						
										<col width="20%">						
									</colgroup>
									<thead>
										<tr>
											<th scope="row">글번호</th>
											<td>
												${detail.qIdx}
												<input type="hidden" id="no" name="no" value="${detail.qIdx }">
											</td>
											<th scope="row">조회수</th>
											<td id="questionView"></td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th scope="row">작성자</th>
											<td>${detail.qUserId}</td>
											<th scope="row">등록날짜</th>
											<td  colspan="4">${detail.qRegDe}</td>
										</tr>
										<tr>
											<th scope="row">제목</th>
											<td colspan="5">
												${detail.qTitle}
											</td>
										</tr>
										<tr>
											<th>질문내용</th>
											<td colspan="5">
												<textarea name="contents" rows="20" cols="110" readonly>${detail.qDesc}</textarea>
											</td>
										</tr>
									</tbody>
								</table>
								<div class="loginButton" style="text-align: center">
		                          	 <button type="button" class="btn btn-lg m_t_10" name="list" id="list" data-toggle="tooltip" data-placement="bottom" >목록</button>
		                          	  <c:if test="${detail.qUserId != sessionScope.userId }">
			                          	 <button type="button" class="btn btn-lg m_t_10" name="answerWrite" id="answerWrite" data-toggle="tooltip" data-placement="bottom" >답변글 작성</button>
			                     	 </c:if>
		                       	</div>
		                       	<br />
		                       	<hr>
		                       	<h4><div class="separator"></div>채택된 답글</h4><br/>
		                       	<table class="table table-striped b-t text-sm">
		                       		<c:if test="${answerPickCheck != 0 }">
										<tbody>
											<tr>
												<th scope="row">작성자</th>
												<td>${pickDetail.aUserId}</td>
												<th scope="row">등록날짜</th>
												<td  colspan="4">${pickDetail.aRegDe}</td>
											</tr>
											<tr>
												<th>답변내용</th>
												<td colspan="5">
													${pickDetail.aDesc}
												</td>
											</tr>
											<tr>
												<th>상세보기</th>
												<td colspan="5">
													<a style="margin-left: 4px;" href="#" onclick="javascript:listDetail(${pickDetail.aIdx})"> 
														<button type="button" class="btn-outline-danger hover" name="save" id="save" >상세보기</button>
													</a>
												</td>
											</tr>
										</tbody>
										</c:if>
										<c:if test="${answerPickCheck == 0 }">
											&nbsp;답변 채택이 진행중입니다. 답변글을 작성하시면 10 bino가 적립됩니다.
										</c:if>
								</table>
								<br/>
								<hr>
								<h4><div class="separator"></div>답글 목록</h4><br/>
		                       	<table class="table table-striped b-t text-sm">
									<thead>
										<tr>
											<c:if test="${sessionScope.userLevel == 'ADMIN' }">
												<th><input type="checkbox" id="checkall" name="checkall"></th>
											</c:if>
											<th>채택여부</th>
											<th>작성자</th>
											<th>조회수</th>
											<th>등록날짜</th>
											<th>답변</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach var="i" items="${answerList}" varStatus="cnt">
											<tr>
												<c:if test="${sessionScope.userLevel == 'ADMIN' }">
													<td><input type="checkbox" name="chkArray" value="${i.qIdx}"></td>
												</c:if>
												<c:if test="${i.aYn == 'Y' }">
													<td>완료</td>
												</c:if>
												<c:if test="${i.aYn == 'N' }">
													<td>대기중</td>
												</c:if>
												<td>
													${i.aUserId}
												</td>
												<td style="text-align: left;">
													${i.aViewCnt}
												</td>
												<td>${i.aRegDe}</td>
												<td>
													<a style="margin-left: 4px;" href="#" onclick="javascript:listDetail(${i.aIdx})"> 
														<button type="button" class="btn-outline-warning hover" name="save" id="save" >답글보기</button>
													</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
				               </form>
				               
								</div>
			      			</div>
				   		</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>