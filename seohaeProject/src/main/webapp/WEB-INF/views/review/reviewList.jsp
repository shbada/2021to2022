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
<jsp:useBean id="toDay" class="java.util.Date" scope="request"/>               <!-- 현재날짜 구하기 -->
<fmt:parseNumber value="${toDay.time/(1000*60*60*24)}" integerOnly="true" var="nowToDay" scope="request" />
<script>
$(function(){
	$("#reset").click(function(){
		$("#searchView").val(''); 
	});
	
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
						url:"/reviewDelete.do",
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
	 });
	 
});

function searchFormView(){
	var obj = document.frm_search;
	obj.method="POST";
	obj.action="reviewList.do";
	obj.submit();
}

function fn_movePage(val){
	$("input[name=pageNo]").val(val); 
	document.viewTable.method="POST";
	document.viewTable.action="<c:url value='/reviewList.do'/>";
	document.viewTable.submit();
}
function listDetail(no){
	document.viewTable.no.value=no;
	document.viewTable.method="POST";
	document.viewTable.action="<c:url value='/reviewInfo.do'/>";
	document.viewTable.submit();
}

function reviewWrite(no){
	document.form1.pdNo.value=no;
	document.form1.method="POST";
	document.form1.action="<c:url value='/reviewWrite.do'/>";
	document.form1.submit();
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
                            <h2>REVIEW</h2>
                        </div>
                        <p> 상품 후기댓글 입니다. 저희 BINO에서는 많은 분들의 책의 평가와 리뷰를 담기위해 이 교재를 구압하신 회원들 뿐만 아니라, 해당 교재로
                        	공부해보신 회원님들 모두에게 후기댓글을 남길 수 있는 권한이 주어집니다. 후기댓글 작성시, 10 bino만큼 적립되며 비방&욕설 등 불편함을 주시는
                        	회원분들은 영구제명 및 삭제처리 됩니다.</p>

                        <a href="" class="btn btn-lg">BROWSE OUR WORK</a>
                    </div>
                </div>
            </div>
        </div><!--End of row -->
    </div><!--End of container -->
</section><!--End of history -->

<!-- Study Section --> 
<section id="study" class="study text-center wow fadeIn"  data-wow-duration="2s" data-wow-dealy="1.5s">
    <div class="container">
        <div class="row">
            <div class="main_study_area sections">
                <div class="head_title text-center">
                    <h2>CASE STUDY</h2>
                    <div class="subtitle">
                        A brief story about how this process works, keep an eye till the end.
                    </div>
                    <div class="separator"></div>
                </div>
                <div class="single_study_content">
                	<form name="form1" onsubmit="return flase;">
                		<input type="hidden" name="pdNo" id="pdNo" value="1" /> 
	                    <div class="col-sm-6">
	                        <div class="single_study_slid_area">
							
	                            <div class="single_study_text">
	                                <div class="study_slider"> 
	                                    <div class="item">
	                                        <div class="s_study_icon">
	                                            <i class="fa fa-lightbulb-o"></i>  
	                                        </div>
	                                        <h4>${book.pdName}</h4>
	                                        <div class="separator3"></div>
	                                        <p>${book.pdInfo}</p>
	
	                                        <button type="button" class="btn btn-lg m_t_10" onclick="javascript:reviewWrite('${book.pdNo}');">후기댓글 작성</button>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                    </form>
					<form class="form-horizontal" name="frm">
						<input type="hidden" name="pdName" value="${book.pdName}">
						<input type="hidden" name="pdUrl" value="${book.pdUrl}">
						<input type="hidden" name="pdInfo" value="${book.pdInfo}">
					</form>
                    <div class="single_study_right_img">
                        <div class="col-sm-6">
                            <div class="single_study_img">
                                <img src="<c:url value="/img/${book.pdUrl}" />" width="600px" height="650px">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- End off row --> 
    </div><!-- End off Container --> 
</section><!-- End off Study Section --> 

<section id="contact" class="contact">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="contact_contant sections">
                    <div class="head_title text-center">
                        <h2>BOOK REVIEW</h2>
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
								<input type="hidden" name="pageNo" id="pageNo" value="${pageVO.pageNo}" /> 
								<input type="hidden" name="no" id="no" value="1" />
								<!-- <input type="hidden" name="password" id="password" value="" /> -->
								<table class="table table-striped b-t text-sm">
									<colgroup>
										<c:if test="${sessionScope.userId == 'admin' }">
											<col width="5%" />
										</c:if>
										<col width="5%" />
										<col width="5%" />
										<col width="10%" />
										<col width="30%" />
										<col width="70%" />
										<col width="20%" />
									</colgroup>
									<c:if test="${sessionScope.userId == 'admin' }">
										<tfoot>
											<tr>
												<c:choose>
													<c:when test="${sessionScope.userId == 'admin' }">
														<td colspan="7">
													</c:when>
													<c:otherwise>
														<td colspan="6">
													</c:otherwise>
												</c:choose>
												<button type="button" class="btn btn-lg" name="delete" id="delete" >삭제</button>
											</tr>
										</tfoot>
									</c:if>
									<thead>
										<tr>
											<c:if test="${sessionScope.userId == 'admin' }">
												<th><input type="checkbox" id="checkall"
													name="checkall"></th>
											</c:if>
											<th>번호</th>
											<th>평가</th>
											<th>업로드</th>
											<th>제목</th>
											<th>내용</th>
											<th>작성자</th>
										</tr>
									</thead>
		
									<tbody>
										<c:forEach var="i" items="${reviewList}" varStatus="cnt">
											<tr>
												<c:if test="${sessionScope.userId == 'admin' }">
													<td><input type="checkbox" name="chkArray" value="${i.no}"></td>
												</c:if>
												<td>${i.rm}</td>
												<td>
													<c:if test="${i.reviewLevel == 5}">
													★★★★★ 
													</c:if>
													<c:if test="${i.reviewLevel == 4}">
													★★★★☆
													</c:if>
													<c:if test="${i.reviewLevel == 3}">
													★★★☆☆ 
													</c:if>
													<c:if test="${i.reviewLevel == 2}">
													★★☆☆☆ 
													</c:if>
													<c:if test="${i.reviewLevel == 1}">
													★☆☆☆☆ 
													</c:if>
												</td>
												<td>
													<c:if test="${i.url != null }">
														<img src="/img/${i.url}" style="width: 46px; height: 46px" >
													</c:if>
													<c:if test="${i.url == null }">
														
													</c:if>
												</td>
												<fmt:parseDate value="${i.regDe}" var="regdate" pattern="yyyy-MM-dd" scope="page" />
												<fmt:parseNumber value="${regdate.time/(1000*60*60*24)}" integerOnly="true" var="regdate" scope="page" /> 
												<td style="text-align: left;">
													<a style="margin-left: 4px">
													${i.subject}
													<c:if test="${regdate <= nowToDay && regdate >= nowToDay-3}">
														<span class="replyNew">[new]&nbsp;</span>
													</c:if>
													</a>
												</td>
												<td>${i.contents}</td>
												<td>${i.writer}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="text-center">
									<ul class="pagination pagination">
										<c:if test="${pageVO.pageNo != 0}">
											<c:if test="${pageVO.pageNo > pageVO.pageBlock}">
												<li><a
													href="javascript:fn_movePage(${pageVO.firstPageNo})"
													style="text-decoration: none;"><i
														class="fa fa-chevron-left"></i><i
														class="fa fa-chevron-left"></i></a></li>
											</c:if>
											<c:if test="${pageVO.pageNo != 1}">
												<li><a
													href="javascript:fn_movePage(${pageVO.prevPageNo})"
													style="text-decoration: none;"><i
														class="fa fa-chevron-left"></i></a></li>
											</c:if>
											<c:forEach var="i" begin="${pageVO.startPageNo}"
												end="${pageVO.endPageNo}" step="1">
												<li><c:choose>
														<c:when test="${i eq pageVO.pageNo}">
															<a href="javascript:fn_movePage(${i})" id="pageNo"
																style="text-decoration: none; background-color: #428bca;">
																<font style="font-weight: bold; color: #ffffff;">${i}</font>
															</a>
														</c:when>
														<c:otherwise>
															<a href="javascript:fn_movePage(${i})"
																style="text-decoration: none;">${i}</a>
														</c:otherwise>
													</c:choose></li>
											</c:forEach>
											<c:if test="${pageVO.pageNo != pageVO.finalPageNo }">
												<li><a
													href="javascript:fn_movePage(${pageVO.nextPageNo})"
													style="text-decoration: none;"><i
														class="fa fa-chevron-right"></i></a></li>
											</c:if>
											<c:if test="${pageVO.endPageNo < pageVO.finalPageNo }">
												<li><a
													href="javascript:fn_movePage(${pageVO.finalPageNo})"
													style="text-decoration: none;"><i
														class="fa fa-chevron-right"></i><i
														class="fa fa-chevron-right"></i></a></li>
											</c:if>
										</c:if>
									</ul>
								</div>
							</form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->


<%@ include file="/WEB-INF/include/include-footer.jsp" %>