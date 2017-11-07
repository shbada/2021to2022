<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/left.do" />
<jsp:include page="/top.do" />
<jsp:useBean id="toDay" class="java.util.Date" scope="request"/>               <!-- 현재날짜 구하기 -->
<fmt:parseNumber value="${toDay.time/(1000*60*60*24)}" integerOnly="true" var="nowToDay" scope="request" />
<script>
function fn_movePage(pageNo){ //페이징
	$("input[name=pageNo]").val(pageNo);
	var obj = document.viewTable;
	obj.method="POST";
	obj.action="<c:url value='/main/freeBoardSelectList.do' />";
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
					url:"/main/freeBoardListDelete.do", //controller로 이동 -> 선택된 게시글 삭제 기능 실행.
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
	
	$("#search").click(function(){ //검색버튼
		fn_movePage("1");
	});
	$("#btnWrite").click(function(){ //글쓰기 버튼
		document.viewTable.method="POST";   		
		document.viewTable.action="<c:url value='/main/freeBoardWrite.do' />";   		
		document.viewTable.submit();
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
	document.viewTable.action="<c:url value='/main/freeBoardDetail.do' />";   		
	document.viewTable.submit();
}

function selectTotalListXlsExcel(){
	$("form[name=viewTable] input[name=pageNo]").val(1);
	var obj = document.viewTable;
	obj.action = "<c:url value='/selectTotalListXlsExcel.do' />"; //form 태그안의 action을 생략하고 여기에 쓰는것.
	obj.method="post";
	obj.submit();
}

function excelUploadInsert(){
	alert(1);
}

function selectTotalListXlsxExcel(){
	alert(1);
}
</script>

<form name="viewTable" onsubmit="return flase;">
	<input type="hidden" name="pageNo" value="${pageVO.pageNo }" />
	<input type="hidden" name="userId" value="${freeBoardVo.userId }" />
    <input type="hidden" name="freeBorIdx" value="1">
		<div class="mainTitle"><h1><em><strong>자 유 게 시 판</strong></em></h1></div>
		
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
						<button type="button" class="btn btn-sm btn-white prev">이전</button>
						<button type="button" class="btn btn-sm btn-white next">다음</button>
					</div>
				</div>
			</section>
			<!-- 검색어 순위 기능 종료 -->
			
			<!-- 페이징 개수 선택 기능 -->
			<section class="panel">
					<div class="row text-sm wrapper">
						<div class="col-sm-4 m-b-xs"> <!-- 5,10,15,50 선택하고 확인 클릭시 게시글 수가 바뀌는데, 그때 바뀌면서 선택한 숫자도 그대로있게 하기 위함 -->
							<select class="input-sm form-control input-s-sm inline" name="pageCnt" id="pageCnt">
								<option value="5"  <c:if test="${pageVO.pageCnt == '5'}">selected="selected"</c:if>>5개</option>
								<option value="10"  <c:if test="${pageVO.pageCnt == '10'}">selected="selected"</c:if>>10개</option>
								<option value="15"  <c:if test="${pageVO.pageCnt == '15'}">selected="selected"</c:if>>15개</option>
								<option value="50"  <c:if test="${pageVO.pageCnt == '50'}">selected="selected"</c:if>>50개</option>
							</select>								
							<button class="btn btn-sm btn-white" type="button" id="ListCnt">확인</button>			
						</div>
			<!-- 페이징 개수 선택 기능 종료 -->	
			<!-- 엑셀 업로드 버튼 -->
			<div class="col-sm-5 m-b-xs">
				<button class="btn btn-sm btn-white excelBtn" type="button" onclick="javascript:excelUploadInsert();">엑셀업로드</button>
				<button class="btn btn-sm btn-white excelBtn" type="button" onclick="javascript:selectTotalListXlsExcel();">엑셀 xls 다운로드</button>
				<button class="btn btn-sm btn-white excelBtn" type="button" onclick="javascript:selectTotalListXlsxExcel();">엑셀 xlsx 다운로드</button>
			</div>
			
			<!-- 검색 기능 -->
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" class="input-sm form-control" name="search" placeholder="Search" value="${search }" />
								<span class="input-group-btn">
									<button class="btn btn-sm btn-white" type="button" id="search">검색</button>
								</span>
							</div>
						</div>
					</div>
			<!-- 검색 기능 종료 -->
			
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
									<c:when test="${sessionScope.userId == 'admin'}">
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
				</form>
			</section>
			<!-- 글 목록 구하기 종료-->	
	<button type="button" class="btn btn-sm btn-white" onclick="ListDel();">삭제 </button>
	<button type="button" class="btn btn-sm btn-success" id="btnWrite">글쓰기</button>
<jsp:include page="/bottom.do" />