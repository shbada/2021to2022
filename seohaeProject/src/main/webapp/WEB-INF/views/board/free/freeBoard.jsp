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
<style>
.btn-outline-primary {
    color: #2980b9;
    background-color: #fff;
    border-color: #2980b9;
}
.btn-outline-primary:hover {
    color: #fff;
    background-color: #3498db;
    border-color: #3498db;
}

.btn-outline-success {
    color: #27ae60;
    background-color: #fff;
    border-color: #27ae60;
}
.btn-outline-success:hover {
    color: #fff;
    background-color: #2ecc71;
    border-color: #2ecc71;
}

.btn-outline-info {
    color: #8e44ad;
    background-color: #fff;
    border-color: #8e44ad;
}
.btn-outline-info:hover {
    color: #fff;
    background-color: #9b59b6;
    border-color: #9b59b6;
}

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
function fn_movePage(pageNo){ //페이징
	$("input[name=pageNo]").val(pageNo);
	var obj = document.viewTable;
	obj.method="POST";
	obj.action="<c:url value='/freeBoardSelectList.do' />";
	obj.submit();         
}

function ListDel() {
	var chkedVal = new Array(); //배열
	var chkedObj = null;
	
	$(":checkbox[name='chkArry']:checked").each(function(i){
		 chkedObj =  new Object();
		 chkedObj.freeBorIdx = $(this).val(); //글번호 얻어옴
		 chkedObj.updateId = "${sessionScope.userId}"; //삭제하는사람: 접속중 아이디
		 //chkedObj.delId = document.viewTable.delId.value;
		 chkedVal[i] = chkedObj; //배열에 선택된 게시글 저장.
	 });
	if(chkedVal.length == 0){
		alert("선택된 목록이 없습니다. 삭제하시려는 목록을 체크하세요");
		return;
	}else {
		if (confirm("정말 삭제하시겠습니까??") == true){    //확인
			 $.ajax({					
					type:"POST",
					url:"/freeBoardListDelete.do", //controller로 이동 -> 선택된 게시글 삭제 기능 실행.
					dataType:"JSON",
					data : JSON.stringify(chkedVal),
					contentType: "application/json; charset=UTF-8",
					async : false,
					complete: function() {
						alert("삭제 되었습니다.")
						window.location.reload();				// ajax통신 끝나면 화면 자동 리플래쉬
				    }				
				});
		}else{   //취소
	    	return;
		}
	}
}

$(document).ready(function(){
	$("#ListCnt").click(function(){ //5,10,15,50 선택하고 옆에 '확인'버튼
		fn_movePage("1");    		
	});
	
	$("#searchView").click(function(){ //검색버튼
		fn_movePage("1");
	});
	
	$("#btnWrite").click(function(){ //글쓰기 버튼
		document.viewTable.method="POST";   		
		document.viewTable.action="<c:url value='/freeBoardWrite.do' />";   		
		document.viewTable.submit();
	});
	
	/** 검색어 지우기  */
	 $("#reset").click(function(){
		$("#search").val(''); 
	 });
});

$(function($){
	var ticker = function() {
		timer = setTimeout(function() {
			$('#ticker li:first').animate( {marginTop: '-38px'}, 600, function() {
				$(this).detach().appendTo('ul#ticker').removeAttr('style');
            });
            ticker();
        }, 3000);         
	};
	
	// 0번 이전 기능
	$(document).on('click','.prev',function() {
		$('#ticker li:last').hide().prependTo($('#ticker')).slideDown(600);
		clearTimeout(timer);
		ticker();
	}); // 0번 기능 끝
		  
	// 1. 클릭하면 다음 요소 보여주기...
	$(document).on('click','.next',function() {
		$('#ticker li:first').animate( {marginTop: '-38px'}, 600, function() {
			$(this).detach().appendTo('ul#ticker').removeAttr('style');
		});
		clearTimeout(timer);
		ticker();
	});
	
	//2. 마우스를 올렸을 때 기능 정지
	var tickerover = function() {
		$('#ticker').mouseover(function(){
			clearTimeout(timer);
			/* document.getElementById("block").style.position="absolute";
			document.getElementById("block").style.width="28.2%";
			document.getElementById("block").style.height="400px";		//css 속성의 - 등 의 특수문자는 사용할수없으므로.. 카멜기법으로사용
			document.getElementById("pageCnt").style.display="none";
			document.getElementById("listCnt").style.display="none"; */

		});
		$('#ticker').mouseout(function(){
			ticker();
			/* document.getElementById("block").style.position="inherit";
			document.getElementById("block").style.width="78%";
			document.getElementById("block").style.height="38px";		//css 속성의 - 등 의 특수문자는 사용할수없으므로.. 카멜기법으로사용
			document.getElementById("pageCnt").style.display="inline";
			document.getElementById("listCnt").style.display="inline"; */
		});  
	};
		tickerover();
	ticker();
});

function listDetail(idx){ //글 상세보기
	document.viewTable.freeBorIdx.value = idx;
	document.viewTable.method="POST";   		
	document.viewTable.action="<c:url value='/freeBoardDetail.do' />";   		
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
                            <h2>FREE BOARD</h2>
                        </div>
                        <p> 자유게시판 입니다. BINO에서는 회원분들의 친목, 공부법 공유, 질문과답변 등 회원님들끼리의 관계가 중요하다고 생각되는 만큼, 자유게시판을 통해
                        	회원분들 서로 자유롭게 이야기할 수 있는 게시판을 마련하였습니다. 많은 이용 부탁드립니다.</p>

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
                        <h2>FREE BOARD WRITE</h2>
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
								<input type="hidden" name="pageNo" value="${pageVO.pageNo }" />
								<input type="hidden" name="userId" value="${freeBoardVo.userId }" />
							    <input type="hidden" name="freeBorIdx" value="1">
									<!-- 검색어 순위 기능 -->
									<section class="panel searchWordPanel">
									<div class="divLolUpNumber">
										<div class="block" id="block">
										    <ul id="ticker">
										    	<c:forEach var="searchWord" items="${searchWord }" varStatus="idx">
											        <li>
											        	<a href="#">
											        		<span class="spanLolUpNumber">${idx.count }</span>
											        		<span class="spanLolUpText">${searchWord.searchWord }</span>
											        	</a>
											        </li>			    	
										    	</c:forEach>
										    </ul>
										</div>
										<div class="navi">
											<button type="button" class="btn btn-md prev">이전</button>
											<button type="button" class="btn btn-md next">다음</button>
										</div>
									</div>
								</section>
								<br />
								<!-- 검색어 순위 기능 종료 -->
								<div class="search">
									<form name="frm_search">
										<input type="text" size="50" class="form-control" name="search" id="search" placeholder="검색어를 입력하세요" value="${search }" />
										<button type="button" class="btn btn-md" id="searchView">검색</button>
										<button type="button" class="btn btn-md" name="" id="reset">초기화</button>
									</form>
							   </div>
								<hr>
								<!-- 페이징 개수 선택 기능 -->
								<div class="row text-sm wrapper">
									<div class="col-sm-4 m-b-xs"> <!-- 5,10,15,50 선택하고 확인 클릭시 게시글 수가 바뀌는데, 그때 바뀌면서 선택한 숫자도 그대로있게 하기 위함 -->
										<select class="form-control" name="pageCnt" id="pageCnt">
											<option value="5"  <c:if test="${pageVO.pageCnt == '5'}">selected="selected"</c:if>>5개</option>
											<option value="10"  <c:if test="${pageVO.pageCnt == '10'}">selected="selected"</c:if>>10개</option>
											<option value="15"  <c:if test="${pageVO.pageCnt == '15'}">selected="selected"</c:if>>15개</option>
											<option value="50"  <c:if test="${pageVO.pageCnt == '50'}">selected="selected"</c:if>>50개</option>
										</select>								
										<button class="btn btn-md" type="button" id="ListCnt">확인</button>			
									</div>
								</div>
								<!-- 검색 기능 종료 -->
								<hr>
								<!-- 글 목록 구하기 -->		
								<div class="table-responsive">
									<table class="table table-striped b-t text-sm" summary="자유게시판" >
										<caption></caption>
										<colgroup>
											<col width="5%">
											<col width="5%">
											<col width="20%">
											<col width="">
											<col width="10%">
											<col width="10%">
											<col width="10%">						
											<col width="10%">						
										</colgroup>
										<thead>
											<tr>
												<th style="text-align:center;">
													<input type="checkbox" id="checkall" name="checkall" />
												</th>
												<th>번호</th>
												<th class="th-sortable" style="text-align:center" data-toggle="class">제목
													<span class="th-sort">
														<i class="fa fa-sort-down text"></i>
														<i class="fa fa-sort-up text-active"></i>
														<i class="fa fa-sort"></i>
													</span>
												</th>
												<th>내 용</th>
												<th>작성자</th>
												<th>작성일</th>
												<th>조회</th>
												<th>추천수</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="boardList" items="${boardList }">
											<tr>
												<td style="text-align:center;">
													<input type="checkbox" name="chkArry" value="${boardList.freeBorIdx }">
												</td>
												<td style="text-align:center;">${boardList.freeBorIdx }</td>
												<td class="tlWrap">
													<fmt:parseDate value="${boardList.regDe }" var="regDe" pattern="yyyy-MM-dd" scope="page" />
			                  						<fmt:parseNumber value="${regDe.time/(1000*60*60*24)}" integerOnly="true" var="regDe" scope="page" />
													<a href="#" class="link" onclick="javacscript:listDetail('${boardList.freeBorIdx }');">
														<c:choose>
							                              <c:when test="${fn:length(boardList.freeBorTitle) > 21}">
							                                 <c:if test="${regDe <= nowToDay && regDe >= nowToDay-3}">
							                                    <span class="writeNew">[new]&nbsp;</span>
							                                 </c:if>
							                                 <c:out value="${fn:substring(boardList.freeBorTitle,0,50)}" />...
							                              </c:when>
							                              <c:otherwise>
							                                 <c:if test="${regDe <= nowToDay && regDe >= nowToDay-3}">      <!-- 3일까지만 new표시 -->
							                                    <span class="writeNew">[new]&nbsp;</span>
							                                 </c:if>
							                                 <c:out value="${boardList.freeBorTitle}" />
							                              </c:otherwise>         
							                           </c:choose>
							                           <c:if test="${boardList.recent > 0}">
											               <span style="color: red;">(${boardList.recent})</span>
											           </c:if>
													</a>
												</td>
												<td class="tlWrap">
								                   <c:choose>
								                     <c:when test="${fn:length(boardList.freeBorContents) > 21}">
								                          <c:out value="${fn:substring(fn:substring(boardList.freeBorContents, 0, fn:indexOf(boardList.freeBorContents,'<br>')),0,50)}"/>....
								                     </c:when>
								                     <c:otherwise>
								                          <%-- <c:out value="${fn:replace(i.freeBorContents, '<br>','')}" /> --%>
								                          <c:out value="${fn:substring(boardList.freeBorContents, 0, fn:indexOf(boardList.freeBorContents,'<br>'))}" />
								                     </c:otherwise>
								                   </c:choose>
								                </td>
			
												<td>${boardList.userNm }</td>
												<td>${boardList.regDe }</td>
												<td>${boardList.freeBorViewCnt }</td>
												<td>${boardList.freeBorLike }</td>							
											</tr>
										</c:forEach>
											<c:if test="${empty boardList }">
												<c:choose>
												<c:when test="${sessionScope.userLevel == 'ADMIN'}">
													<tr>
														<td colspan="7" style="text-align:center"><span class="noData">검색 조건에 맞는 데이터가 없습니다.</span></td>
													</tr>
												</c:when>
													<c:otherwise>
														<tr>
															<td colspan="8" style="text-align:center"><span class="noData">검색 조건에 맞는 데이터가 없습니다.</span></td>
														</tr>
													</c:otherwise>
												</c:choose>
											</c:if>	
										</tbody>
									</table>
									</div>
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
								<div class="loginButton" style="text-align: center">
		                          <button type="button" class="btn btn-lg m_t_10" onclick="ListDel();">삭제 </button>
								  <button type="button" class="btn btn-lg m_t_10" id="btnWrite">글쓰기</button>
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