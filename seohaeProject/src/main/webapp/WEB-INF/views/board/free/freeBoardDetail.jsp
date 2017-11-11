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
$(document).ready(function(){
	/* listReplyJson(); -JSON방식 -> @RestController*/ 
	viewCnt(); //조회수 함수
	likeCnt(); //추천수 함수
	listReply(5); //
	$("#btnList").click(function(){ //목록으로
		var obj = document.frm;
		obj.method="POST";
		obj.action="<c:url value='/freeBoardSelectList.do' />";
		obj.submit();
	});  
	
	$("#btnUpdate").click(function(){ //수정버튼
		var obj = document.frm;
		obj.method = "POST";
		obj.action = "<c:url value='/freeBoardUpdateView.do' />";
		obj.submit();
	});  
	
	$("#btnLike").click(function(){ //추천버튼
		if(confirm("추천 하시겠습니까?")==true){
			//ajax통신 끝나면 리스트 페이지 이동
			var formData = $("form[name=frm]").serialize(); //값을 다가지고와서 변수에 담는다 (리스트형식으로 값이 들어감)
			$.ajax({
				type:"POST",
				url: "<c:url value='/freeBoardLike.do'/>",
				dataType:"text",
				data: formData,
				success: function(result){
					if(result == 'ok'){
						alert("추천 되었습니다!");
						likeCnt();
					}
					if(result == 'fal'){
						alert("이미 추천한 글 입니다!");
					}
				},
				error: function(result){
					alert('에러가 발생하였습니다.');
					return;
				},
			});
		}
	});
	
	$("#btnDelete").click(function(){
		if(confirm("정말 삭제하시겠습니까?")==true){
			//ajax통신 끝나면 리스트 페이지 이동
			var formData = $("form[name=frm]").serialize(); //값을 다가지고와서 변수에 담는다 (리스트형식으로 값이 들어감)
			$.ajax({
				type:"POST",
				url:"/freeBoardDelete.do",
				dataType:"text",
				data: formData,
				success: function(result){
					if(result == 'ok'){
						alert("정상적으로 삭제 되었습니다.");
						location.href="<c:url value='/freeBoardSelectList.do' />";
					}
					else{
						alert("오류가 발생하였습니다. \n 잠시후 다시 시도해주세요.");
					}
				}
			});
		} else{
			return;
		}
	});
	
	$("#btnReply").click(function(){
		if($("#replyText").val().replace(/\s/g,"").length == 0){ //공백처리
            alert("댓글을 입력하세요.");
            $("#replyText").focus();
            return false;
         }
		
		if(confirm("댓글을 등록하시겠습니까?")==true){
			document.getElementById("replyText").value= $("#replyText").val().replace(/\n/g,"<br>");	//엔터키처리
			
			// 태그 .is(":속성") --> 속성값을 가져옴
			if($('#secretReplyMain').is(":checked")) $('input[name=secretReply]').val('Y');
			else $('input[name=secretReply]').val('N');
			
			var formData = $("form[name=frm]").serialize(); //값을 다가지고와서 변수에 담는다 (리스트형식으로 값이 들어감)
			
			$.ajax({
				type:"POST",
				url: "<c:url value='/reply/freeBoardReplyWrite.do'/>",
				dataType:"text",
				data: formData,
				success: function(result){
						alert("댓글이 등록되었습니다!");
						$("#replyText").val(''); //댓글 등록이 되면 댓글 입력칸은 다시 비워야한다.
						$("#secretReplyMain").prop("checked", false); //비밀댓글 체크한거는 댓글등록 후 체크가 안되게 다시 풀려야한다.
						listReply("5"); //함수 호출, '더보기'버튼으로 할 예정: 5로바꿈
						/* listReplyJson(); -> JSON 형식*/
					}
			});
		}
	});

});


function listReplyJson(){
	$.ajax({
		type:"POST",
		dataType:"json",
		data: $("form[name=frm]").serialize(), //값을 다가지고와서 변수에 담는다 (리스트형식으로 값이 들어감)
		url: "<c:url value='/reply/freeBoardJsonListReply.do'/>",
		success: function(result){ //reulst에 controller의 replyList값이 담겨졌다.
			//console.log(result);
			if(result != ""){ //직접 페이지에 뿌려주기
				var replyList = "<table style='width:100%'>";
				for ( var i in result){
					replyList += "<tr>";
					replyList += "<td>" + result[i].replyer;
					replyList += "(" + result[i].regDe + ")<br>";
					replyList += result[i].replyText + "<hr></td>";
					replyList += "<tr>";						
				}
				replyList += "</table>";
			}else{
				var replyList = "<span>등록된 댓글이 없습니다.</span>";
			}
			$("#listReply").html(replyList); //replyList값을 화면에 뿌려줌
		}			
	});
}

/* function listReply(pageNo){ //페이징
	$("form[name=replyFrm] input[name=pageNo]").val(pageNo);
	$("form[name=replyFrm] input[name=pageCnt]").val(5);
	$.ajax({
		type:"POST",
		data: $("form[name=replyFrm]").serialize(),
		url: "<c:url value='/reply/freeBoardListReply.do'/>",
		success: function(result){
			$("#listReply").html(result); //result 값을 뿌려준다.
		}
	});
} */

function listReply(pageCnt){//댓글 더보기
	$("form[name=replyFrm] input[name=pageNo]").val('1');
	$("form[name=replyFrm] input[name=pageCnt]").val(pageCnt);
	$.ajax({
		type:"POST",
		data: $("form[name=replyFrm]").serialize(),
		url: "<c:url value='/reply/freeBoardListReply.do'/>",
		success: function(result){
			$("#listReply").html(result); //result 값을 뿌려준다.
		}
	});
}

function likeCnt(){
	$.ajax({
		type : "GET",
		url : "<c:url value='/freeBoardLikeCnt.do?freeBorIdx=${freeBoardVo.freeBorIdx}' />",
		success : function(result){
			$("#freBoardLike").html(result); //result값을 추가시킴 (jsp 페이지에 출력)
		}
	});
}

function viewCnt(){
	$.ajax({
		type : "GET",
		url : "<c:url value='/freeBoardViewCnt.do?freeBorIdx=${freeBoardVo.freeBorIdx}' />",
		success : function(result){
			$("#freeBoardView").html(result); //result값을 추가시킴 (jsp 페이지에 출력)
		}
	});
}

function fn_movePage(pageNo){ //댓글 페이징
	listReply(pageNo);  //호출하여 페이징   
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
                        <h2>FREE BOARD DETAIL</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                             <form class="form-horizontal" name="frm">
							<input type="hidden" name="pageCnt" value="${pageVO.pageCnt }" />
					        <input type="hidden" name="search" value="${search}">
				      		<input type="hidden" name="pageNo" value="${pageVO.pageNo}"> <!-- 2번을 누르면 -->
				      		<input type="hidden" name="freeBorIdx" value="${freeBoardVo.freeBorIdx}">
				      		<input type="hidden" name="userId" value="${freeBoardVo.userId }" />
				      		<input type="hidden" name="secretReply" /> <!-- value 필요없음, form submit할때 이 값도 가져가야하므로 hidden써줌 -->
							<section class="panel">
							<div class="panel-body">
								<div class="form-group">
									<label class="col-sm-1 control-label">제목</label>
									<div class="col-sm-6">
										<div class="form-control">
											${freeBoardVo.freeBorTitle }
										</div>
									</div>
									<label class="col-sm-1 control-label">조회수</label>
									<div class="col-sm-1">
										<div class="form-control" id="freeBoardView" ></div>
									</div>
									<label class="col-sm-1 control-label">추천수</label>
									<div class="col-sm-1">
										<div class="form-control" id="freBoardLike"></div>
									</div>
									 <c:if test="${sessionScope.userId  != freeBoardVo.userId }"> <!-- 자기가 쓴글이 아니여야 버튼이 보인다 -->
										<div class="col-sm-1 btnlike">
											<button type="button" class="btn-outline-warning hover" id="btnLike">추천</button>
										</div>
									 </c:if>
								</div>	
								<div class="line line-dashed line-lg pull-in"></div>
								<div class="form-group">
									<label class="col-sm-1 control-label">내용</label>
									<div class="col-sm-11">
										<div style="height:240px">
											<div class="contents" id="freeTextarea">${freeBoardVo.freeBorContents } </div>
										</div>
									</div>
								</div><br />
								<hr>
								<div class="line line-dashed line-lg pull-in"></div> <!-- 흰색줄 -->
									<div class="form-group">
										<label class="col-sm-1 control-label"></label>
										<div class="col-sm-11">
											<div>
												<div class="table-responsive" id="listReply"></div>
											</div>
										</div>
									</div><br />
									<hr>
									<div class="line line-dashed line-lg pull-in"></div> <!-- 흰색줄 -->
										<div class="form-group">
											<label class="col-sm-1 control-label">댓글</label>
											<div class="col-sm-11">
												<div style="height:100px">
													<textarea class="form-control freeReplTextarea" maxlength="2000" name="replyText" id="replyText" placeholder="댓글을 입력하세요"></textarea>
												</div>
												<br>
												<input type="checkbox" id="secretReplyMain" />비밀댓글
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-1 control-label"></label>
											<div class="col-sm-11">
												<button type="button" style="float:right;" class="btn btn-success btn-s-xs" id="btnReply">댓글등록</button>
											</div>
										</div>
										
							</div>
						</section>
					</form>
					<form name="replyFrm">
						<input type="hidden" name="freeBorIdx" value="${freeBoardVo.freeBorIdx }" />
					    <input type="hidden" name="pageCnt" value="">
				      	<input type="hidden" name="pageNo" value="${pageVO.pageNo}">
					</form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                          <button type="button" class="btn btn-lg m_t_10" id="btnList">목록</button>
							<c:if test="${freeBoardVo.userId == sessionScope.userId}"> <!-- 자기가 쓴 글이여야 버튼이 보인다 -->
								<button type="button" class="btn btn-lg m_t_10" id="btnDelete">삭제</button>
								<button type="button" class="btn btn-lg m_t_10" id="btnUpdate">수정</button>
							</c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>