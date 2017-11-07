<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/left.do" />
<jsp:include page="/top.do" />
<style>
	@media (max-width: 768px){
		.mainAudio{display:none;}
	}
</style>
<script>
   $(function(){
      $("#btnList").click(function(){ //목록으로 
         document.frm.method="POST";
         document.frm.action="<c:url value='/main/freeBoardSelectList.do' />";
         document.frm.submit();
      });
      $("#btnSave").click(function(){ //글쓰기 완료
         if($("#freeBorTitle").val().replace(/\s/g,"").length == 0){ //공백처리
            alert("제목을 입력하세요");
            $("#freeBorTitle").focus(); //제목 입력칸으로 마우스커서가 자동으로 감
            return false;
         }
         if($("#freeBorContents").val().replace(/\s/g,"").length == 0){ //공백처리
            alert("내용을 입력하세요");
            $("#freeBorContents").focus();
            return false;
         }
         document.getElementById("freeBorContents").value= $("#freeBorContents").val().replace(/\n/g,"<br>"); // 엔터키 처리
         
         document.frm.method="POST";
         document.frm.action="<c:url value='/main/freeBorWriteSave.do' />";
         document.frm.submit();
      });
   });
</script>
<div class="col-sm-12">
   <form class="form-horizontal" name="frm" onsubmit="return false"> <!-- 엔터키 submit 방지 -->
      <!-- 목록으로 돌아갈때 그전에 검색어, 페이지 수 등이 같아야하므로 -->
      <input type="hidden" id="search" name="search" value="${freeBoardVo.search}">
      <input type="hidden" id="pageNo" name="pageNo" value="${freeBoardVo.pageNo}">
      <input type="hidden" id="pageNo" name="pageCnt" value="${freeBoardVo.pageCnt}">
      <section class="panel">
         <header class="panel-heading">
            <div class="hearderDivTitle"><span>새 글 작성</span></div>
         </header>
         
         <div class="panel-body">
            <div class="form-group">
               <label class="col-sm-1 control-label">제&nbsp;&nbsp;&nbsp;목</label>
               <div class="col-sm-11">
                  <input type="text" class="form-control parsley-validated" name="freeBorTitle" id="freeBorTitle" maxlength="100" placeholder="제목을 입력하세요">
               </div>
            </div>
            <div class="line line-dashed line-lg pull-in"></div>
            
            <div class="form-group">
               <label class="col-sm-1 control-label">내&nbsp;&nbsp;&nbsp;용</label>
               <div class="col-sm-11">
                  <textarea class="form-control freeTextarea" maxlength="2000" name="freeBorContents" id="freeBorContents" placeholder="내용을 입력하세요" ></textarea>
               </div>
            </div>
            <footer class="panel-footer text-right bg-light lter">
               <button type="button" class="btn btn-default btn-s-xs btnList" id="btnList">목록</button>
               <button type="button" class="btn btn-success btn-s-xs" id="btnSave">저장</button>
            </footer>
         </div>
      </section>
   </form>
</div>
<%@ include file="/WEB-INF/include/include-footer.jsp" %>