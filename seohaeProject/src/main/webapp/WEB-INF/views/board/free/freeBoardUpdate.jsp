<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<c:set var="commandName" value="freeBoardVo"></c:set>
<jsp:include page="/left.do" />
<jsp:include page="/top.do" />
<style>
	@media (max-width: 768px){
		.mainAudio {display:none;}	
	}
</style>
<script>
	$(document).ready(function(){
		$("#btnList").click(function(){ //목록으로
			var obj = document.frm;
			obj.method="POST";
	        obj.action="<c:url value='/main/freeBoardSelectList.do' />";
	        obj.submit();
		});  
		
	      $("#btnUpdate").click(function(){ //수정완료
	          if($("#freeBorTitle").val().replace(/\s/g,"").length == 0){ //공백처리
	             alert("제목을 입력하세요");
	             $("#freeBorTitle").focus();
	             return false;
	          }
	          if($("#freeBorContents").val().replace(/\s/g,"").length == 0){ //공백처리
	             alert("내용을 입력하세요");
	             $("#freeBorContents").focus();
	             return false;
	          }
	          document.getElementById("freeBorContents").value= $("#freeBorContents").val().replace(/\n/g,"<br>"); // 엔터키 처리
	          
	          document.frm.method="POST";
	          document.frm.action="<c:url value='/main/freeBoardUpdate.do' />"; //controller로 이동 -> 수정 기능실행
	          document.frm.submit();
	       }); 
	});
</script>
<div class="col-sm-12">
   <form:form commandName="${commandName }" cssClass="form-horizontal" name="frm">
      <form:input type="hidden" path="pageCnt" value="${pageVO.pageCnt }" />
      <form:input type="hidden" path="pageNo" value="${pageVO.pageNo }" />
      <form:input type="hidden" path="freeBorIdx" value="${freeBoradVo.freeBorIdx }" />
      <form:input type="hidden" path="search" value="${search }" />
      <section class="panel">
         <header class="panel-heading">
            <div class="hearderDivTitle"><span>작성 글 수정</span></div>
         </header>
         
         <div class="panel-body">
            <div class="form-group">
               <label class="col-sm-1 control-label">제목</label>
               <div class="col-sm-11">
                  <form:input type="text" cssClass="form-control parsley-validated" path="freeBorTitle" maxlength="100" placeholder="제목을 입력하세요" />
               </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>  
                     <div class="form-group">
                          <label class="col-sm-1 control-label">내&nbsp;&nbsp;&nbsp;용</label>
                          <div class="col-sm-11">
                            <div style="height:240px">
                                <form:textarea cssClass="form-control freeTextarea" maxlength="2000" path="freeBorContents" placeholder="내용을 입력하세요"></form:textarea>
                            </div>
               			  </div>                  
            		 </div>
         </div>
         <footer class="panel-footer text-right bg-light lter">
            <button type="button" class="btn btn-default btn-s-xs btnList" id="btnList">목록</button>
            <button type="button" class="btn btn-danger btn-s-xs" id="btnCancel" onclick="location.href='javascript:history.back();'">취소</button>
            <button type="button" class="btn btn-success btn-s-xs" id="btnUpdate">저장</button>
         </footer>
      </section>
   </form:form>
</div>
<%@ include file="/WEB-INF/include/include-footer.jsp" %>
</form>