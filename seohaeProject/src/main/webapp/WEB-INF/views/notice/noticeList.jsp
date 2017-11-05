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
<jsp:useBean id="toDay" class="java.util.Date" scope="request" />
<fmt:parseNumber value="${toDay.time/(1000*60*60*24)}" integerOnly="true" var="nowToDay" scope="request" />
<jsp:include page="/mainTop.do" />
<!-- History section -->
<script>
$(function(){
	/** 공지글 작성 페이지로 이동 */
	$("#write").click(function(){
		location.href="noticeWrite.do";	
	});
	 
	/** 검색어 지우기  */
	 $("#reset").click(function(){
		$("#searchView").val(''); 
	 });
	
	 /** 공지글 선택 삭제 */
	 $("#delete").click(function(){
		var chkedVal = new Array();
		var chkedObj = null;
		
		$(":checkbox[name='chkArray']:checked").each(function(i){
			chkedObj = new Object();
			chkedObj.no = $(this).val();
			chkedObj.updateId = "${sessionScope.userId}";
			//chkedObj.delId = document.viewTable.delId.value;
			chkedVal[i] = chkedObj;
		});
		
		if(chkedVal.length == 0){
			alert("선택된 목록이 없습니다. 삭제하시려는 목록을 체크하세요");
			return;
		}else{
			if(confirm("정말 삭제하시겠습니까??") == true){
				$.ajax({
					type:"POST",
					url:"/noticeListDelete.do",
					dataType:"JSON",
					data : JSON.stringify(chkedVal),
					contentType : "application/json; charset=UTF-8",
					async : false,
					complete: function() {
						alert("삭제되었습니다.");
						window.location.reload(); // f5 기능
					}
				});
			}else{
				return;	
			}
		}
	 })
});

/** 검색 기능 */
function searchFormView(){
	var obj = document.frm_search;
	obj.method="POST";
	obj.action="noticeList.do";
	obj.submit();
}

/** 페이징 */
function fn_movePage(val){
	$("input[name=pageNo]").val(val); 
	document.viewTable.method="POST";
	document.viewTable.action="<c:url value='/noticeList.do'/>";
	document.viewTable.submit();
}

/** 공지글 상세 페이지로 이동 */
function listDetail(no){
	document.viewTable.no.value=no;
	document.viewTable.method="POST";
	document.viewTable.action="<c:url value='/noticeInfo.do'/>";
	document.viewTable.submit();
}
</script>
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

<section id="contact" class="contact">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="contact_contant sections">
                    <div class="head_title text-center">
                        <h2>NOTICE</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                               <div class="search">
									<form name="frm_search">
										<input type="text" size="50" class="form-control" name="searchView" id="searchView" placeholder=" 검색어를 입력하세요" value="" />
										<button type="button" class="btn btn-md" onclick="searchFormView();">검색</button>
										<button type="button" class="btn btn-md" name="" id="reset">초기화</button>
									</form>
							  </div>
							  <hr>
							  <form name="viewTable">
								<input type="hidden" name="pageNo" id="pageNo" value="${pageVO.pageNo}" /> <input type="hidden" name="no" id="no" value="1" />
								<!-- <input type="hidden" name="password" id="password" value="" /> -->
								<table class="table table-striped b-t text-sm">
									<colgroup>
										<c:if test="${sessionScope.userId == 'admin' }">
											<col width="20" />
										</c:if>
										<col width="30" />
										<col width="260" />
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
											<th>NO</th>
											<th>TITLE</th>
											<th>WRITER</th>
											<th>HITS</th>
											<th>LIKE</th>
											<th>DATE</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach var="i" items="${noticeList}" varStatus="cnt">
											<tr>
												<c:if test="${sessionScope.userId == 'admin' }">
													<td><input type="checkbox" name="chkArray" value="${i.no}"></td>
												</c:if>
												<td>${i.no}</td>
												<td style="text-align: left;">
													<fmt:parseDate value="${i.regdate }" var="regdate" pattern="yyyy-MM-dd" scope="page" /> 
													<fmt:parseNumber value="${regdate.time/(1000*60*60*24)}" integerOnly="true" var="regdate" scope="page" /> 
														<a style="margin-left: 4px;" href="#" onclick="javascript:listDetail(${i.no})"> 
															<c:if test="${regdate <= nowToDay && regdate >= nowToDay-3}">
																	<span class="replyNew" style="color:red;">[new]&nbsp;</span>
															</c:if> ${i.title}
														</a>
												</td>
												<c:if test="${i.writer == 'admin' }">
													<td>관리자</td>
												</c:if>
												<c:if test="${i.writer != 'admin' }">
													<td>${i.writer}</td>
												</c:if>
												<td>${i.viewCnt}</td>
												<td>${i.likeCnt}</td>
												<td>${i.regdate}</td>
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
                        <c:if test="${sessionScope.userId == 'admin' }">
	                        <div class="loginButton" style="text-align: center">
	                          <button type="button" class="btn btn-lg m_t_10" name="write" id="write" data-toggle="tooltip" data-placement="bottom">작성</button>&nbsp;
		                      <button type="button" class="btn btn-lg m_t_10" name="delete" id="delete" data-toggle="tooltip" data-placement="bottom">삭제</button>
                        	</div>
                       </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>