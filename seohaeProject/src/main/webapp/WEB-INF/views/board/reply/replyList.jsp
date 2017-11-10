<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
});

function fn_movePage(pageCnt){ //더보기 클릭 시!
	listReply(pageCnt);
}

/** 삭제버튼 클릭시*/
function replyDelete(idx){ //idx: 댓글번호
	if(confirm("댓글을 삭제하시겠습니까?") == true){
		$.ajax({
			type : "patch",
			url : "/reply/replyOneDelete.do?freeBorReplyIdx="+idx,
			success : function(){
				fn_movePage($("#pageCnt").val()); //함수호출: 삭제 후 더보기 버튼 다시 안나오고 그대로 그 댓글개수가 있게함
				alert("정상적으로 삭제되었습니다.");
			}
		});
	}
}

/**수정완료 */
function replyOneUpdate(freeBorReplyIdx){
	var secretReply = 'N'; 
	//is: 속성값 가져옴 ( 체크가 되어있으면, )
	if ($('#secretReply' + freeBorReplyIdx).is(":checked")) secretReply = 'Y'; //.is는 체크박스의 속성을 true/false 인치 체크하는 기능
	if (confirm("댓글을 수정하시겠습니까?") == true){
		document.getElementById("replyText").value = $('#replyText').val().replace(/\n/g, "<br>"); //\n을 <br>로 바꿔서 db에 넣음
		$.ajax({
			type : 'put',
			//&는 js의 규칙. 변수를 이어서 스트링을 쓸때 앞에 써줘야함 (&파라미터명=파라미터값)
			url : "/reply/replyOneUpdate.do?freeBorReplyIdx=" + freeBorReplyIdx + "&replyText=" + $("#replyText").val() + "&secretReply=" + secretReply,
			success : function(){
				fn_movePage($("#pageCnt").val());
				alert("정상적으로 수정 되었습니다.");
			}
		});
	}else{
		return;
	}
}

/** 수정버튼 클릭 후 취소 버튼 클릭시 */
function replyOneUpdateCancle(){
	fn_movePage($("#pageCnt").val()); //취소할때 현재 게시글로 그대로 돌아감
}

/** 수정버튼 클릭시*/
function replyUpdate(freeBorReplyIdx, paramText, secretReply){
	var replyText = paramText.replace(/<br>/g, '\n');
	//동적으로 태그만들기
	var tag = "<textarea class='form-control replyTextarea' maxlength='20000' name='replyText' id='replyText'>" + replyText + "</textarea>";
	tag += "<button type='button' class='btn-outline-warning hover' onclick='javascript:replyOneUpdate(" + freeBorReplyIdx + ");'>수정확인</button>";
	tag += "<button type='button' class='btn-outline-danger hover' onclick='javascript:replyOneUpdateCancle();'>취소</button>";
	tag += "<div class='secretReply'><input type='checkbox' id='secretReply" + freeBorReplyIdx +"' />비밀댓글</div>";
	$("." + freeBorReplyIdx).html(tag); //.은 만약에 20번 댓글이면 20번 댓글을 찾음
	if(secretReply == 'Y'){
		$("." + freeBorReplyIdx).find("input:checkbox[id='secretReply"+ freeBorReplyIdx + "']").prop("checked", true); //prop: 체크박스를 체크해줌. ->Y이면 체크가 되어있어야된다. 비밀댓글이므로
	}
}

</script>
<table study = 'width:100%'>
	<c:forEach var="row" items="${replyList }">
		<tr>
			<td class="replyId ${row.freeBorReplyIdx }"> <!-- 클래스 이름 변별력을 위해 -->
				${row.replyer }
				(${row.regDe })
				<br>
				<c:choose>
					<c:when test="${row.secretReply == 'Y' }"> <!-- 비밀댓글이면, -->
						<span style="color:red;">${row.replyText }</span>
						<input type="hidden" id="secretReply" value="${row.secretReply }">
					</c:when>
					<c:otherwise>
						<span>${row.replyText }</span>
					</c:otherwise>
				</c:choose>
				<c:if test="${row.userId == sessionScope.userId }"> <!-- 댓글 작성자와 접속중인 아이디가 같으면 -->
					<button type="button" class="btn-outline-warning hover" onclick="javascript:replyUpdate('${row.freeBorReplyIdx }', '${row.replyText }', '${row.secretReply }' );">수정</button>
					<button type="button" class="btn-outline-danger hover" onclick="javascript:replyDelete('${row.freeBorReplyIdx }');">삭제</button>
				</c:if>
				<hr>
			</td>
		</tr>
	</c:forEach>
</table>
<c:choose>
	<c:when test="${fn:length(replyList) != 0 }">
		<div class="text-center">
			<c:if test="${pageVO.totalCount > fn:length(replyList)}"> <!-- 전체 개수가 더 크면 (5개 이상이면) -->
				<button type="button" class="btn btn-info btn-s-xs btnList" onclick="javascript:fn_movePage('${fn:length(replyList)+5 }')">더보기</button> <!-- 5개씩 더 보이게하겠다 -> +5한 이유 -->
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<span>등록된 댓글이 없습니다.</span>
	</c:otherwise>
</c:choose>
<input type="hidden" id="pageCnt" value="${pageVO.pageCnt }" />
