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
<c:set var="commandName" value="freeBoardVo"></c:set>
<jsp:useBean id="toDay" class="java.util.Date" scope="request"/>               <!-- 현재날짜 구하기 -->
<fmt:parseNumber value="${toDay.time/(1000*60*60*24)}" integerOnly="true" var="nowToDay" scope="request" />
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
	        obj.action="<c:url value='/freeBoardSelectList.do' />";
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
	          document.frm.action="<c:url value='/freeBoardUpdate.do' />"; //controller로 이동 -> 수정 기능실행
	          document.frm.submit();
	       }); 
	});
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
                        <h2>FREE BOARD UPDATE</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                             <form:form commandName="${commandName }" cssClass="form-horizontal" name="frm">
							      <form:input type="hidden" path="pageCnt" value="${pageVO.pageCnt }" />
							      <form:input type="hidden" path="pageNo" value="${pageVO.pageNo }" />
							      <form:input type="hidden" path="freeBorIdx" value="${freeBoradVo.freeBorIdx }" />
							      <form:input type="hidden" path="search" value="${search }" />
							      <section class="panel">
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
							      </section>
							   </form:form>
								<form name="replyFrm">
									<input type="hidden" name="freeBorIdx" value="${freeBoardVo.freeBorIdx }" />
								    <input type="hidden" name="pageCnt" value="">
							      	<input type="hidden" name="pageNo" value="${pageVO.pageNo}">
								</form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
							 <button type="button" class="btn btn-lg m_t_10" id="btnList">목록</button>
				            <button type="button" class="btn btn-lg m_t_10" id="btnCancel" onclick="location.href='javascript:history.back();'">취소</button>
				            <button type="button" class="btn btn-lg m_t_10" id="btnUpdate">저장</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>