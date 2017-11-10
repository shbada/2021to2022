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
<script>
$(function(){
	viewCnt(); //조회수
	likeCnt(); //추천수

   /** 목록으로 이동 */
   $("#list").click(function(){
	   alert(1);
	   location.href="questionList.do";
   });
   
   /** 공지글 수정 페이지로 이동 */
   $("#update").click(function(){
		document.noFrm.method="POST";
		document.noFrm.action="<c:url value='/openNoticeUpdate.do'/>";
		document.noFrm.submit(); 
   });
   
   /** 해당 글 삭제 */
   $("#delete").click(function(){
	   if(confirm("정말로 삭제하시겠습니까?") == true){
		   var formData = $("form[name=noFrm]").serialize();
		   $.ajax({
				  type : "POST",
				  url : "<c:url value='/noticeDelete.do' />",
				  dataType : "text",
				  data : formData,
				  success : function(result){
					  if(result=="ok"){
						  alert("삭제가 되었습니다.");
						  location.href="<c:url value='/notice.do' />";
					  }else{
						  alert("오류가 발생했습니다. \n 잠시 후 다시 시도해 주세요.");
					  }
				  }
			  });
	   }
	   
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
				         		<input type="hidden" name="no" value ="${detail.qIdx}" />
				         		<input type="hidden" name="secretReply" />
									<table class="table table-striped b-t text-sm">
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
				               </form>
								</div>
			      			</div>
				   		</div>
                       	<div class="loginButton" style="text-align: center">
                          	 <button type="button" class="btn btn-lg m_t_10" name="list" id="list" data-toggle="tooltip" data-placement="bottom" >목록</button>
                          	  <c:if test="${detail.qUserId == sessionScope.userId }">
	                          	 <button type="button" class="btn btn-lg m_t_10" name="update" id="update" data-toggle="tooltip" data-placement="bottom" >수정</button>
		                     	 <button type="button" class="btn btn-lg m_t_10" id="delete" data-toggle="tooltip" data-placement="bottom">삭제</button>
	                     	 </c:if>
                       	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>