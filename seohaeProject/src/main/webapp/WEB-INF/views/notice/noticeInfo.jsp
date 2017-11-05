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
<script>
$(function(){
	viewCnt(); //조회수
	likeCnt(); //추천수

/*    $("a[name='file']").click(function(){
	   location.href="downloadFile.do?no="+no;
   }); */
   
   /** 목록으로 이동 */
   $("#list").click(function(){
	   location.href="noticeList.do";
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
   
   /** 공지글 추천 */
   $("#btnLike").click(function(){
	   if(confirm("추천 하시겠습니까??") == true){
		   var formData = $("form[name='noFrm']").serialize();
		   $.ajax({
			  type : "POST",
			  url : "<c:url value='/noticeLike.do' />",
			  dataType : "text",
			  data : formData,
			  success : function(result){
				  if(result =='ok'){
					  alert("해당 글의 추천이 완료되었습니다.");
					  likeCnt();
				  }
				  if(result =='false'){
					  alert("회원님은 이미 해당 글을 추천하셨습니다.");
				  }
			  },
			  error : function(result){
				  alert("에러가 발생하였습니다.");
				  retrun;
			  }
		   });
	   }
   });
	
});

/** 조회수 */
function viewCnt(){
	$.ajax({
		type : "get",
		url : "<c:url value='/noticeViewCnt.do?no=${detail.no}' />",
		success : function(result){
			$("#noticeView").html(result);
		}
	});
}

/** 추천수 */
function likeCnt(){
	$.ajax({
		type : "get",
		url : "<c:url value='/noticeLikeCnt.do?no=${detail.no}' />",
		success : function(result){
			$("#noticeLike").html(result);
		}
	});
}

/** 파일 다운로드 */
function fileDownload(idx){ 
	document.noFrm.no.value = idx;
	document.noFrm.method="POST";   		
	document.noFrm.action="<c:url value='/downloadFile.do' />";   		
	document.noFrm.submit();
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
                        <h2>NOTICE DETAIL</h2>
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
                               		 <c:if test="${detail.writer != sessionScope.userId }">
										<button type="button" class="btn btn-lg m_t_10" id="btnLike" style="text-align: right;">추천하기</button>
									</c:if>
									<hr>
					         		<input type="hidden" name="no" value ="${detail.no}" />
					         		<input type="hidden" name="secretReply" />
										<table class="table table-striped b-t text-sm">
										<thead>
											<tr>
												<th scope="row">글번호</th>
												<td>
													${detail.no}
													<input type="hidden" id="no" name="no" value="${detail.no }">
												</td>
												<th scope="row">조회수</th>
												<td id="noticeView"></td>
												<th scope="row">추천수</th>
												<td id="noticeLike"></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<th scope="row">WRITER</th>
												<td>${detail.writer}</td>
												<th scope="row">DATE</th>
												<td  colspan="4">${detail.regdate}</td>
											</tr>
											<tr>
												<th scope="row">TITLE</th>
												<td colspan="5">
													${detail.title}
												</td>
											</tr>
											<tr>
												<th>contents</th>
												<td colspan="5">
													<textarea name="contents" rows="20" cols="110" readonly>${detail.contents}</textarea>
												</td>
											</tr>
											<tr>
								                <th scope="row">첨부파일</th>
								                <td colspan="5">
								                    <c:forEach var="row" items="${list }">
								                    <p>
								                        <input type="hidden" id="no" name="no" value="${row.no }">
								                        <a href="#" class="link" name="file" onclick="javacscript:fileDownload('${row.no }');">
								                        ${row.oName }
								                        </a>
								                        (${row.fileSize } kb)
								                    </p>
								                    </c:forEach>
								                </td>
								            </tr>
										</tbody>
									</table>
					               </form>
									</div>
				      			</div>
					   		</div>
					   		<form name="replyFrm">
								<input type="hidden" name="no" value="${detail.no}" />
								<input type="hidden" name="pageNo" value="" />
								<input type="hidden" name="pageCnt" value="" />
							</form>
	                        	<div class="loginButton" style="text-align: center">
		                          	 <button type="button" class="btn btn-lg m_t_10" name="list" id="list" data-toggle="tooltip" data-placement="bottom" >목록</button>
		                          	  <c:if test="${sessionScope.userId == 'admin' }">
			                          	 <button type="button" class="btn btn-lg m_t_10" name="update" id="update" data-toggle="tooltip" data-placement="bottom" >수정</button>
				                     	 <button type="button" class="btn btn-lg m_t_10" id="delete" data-toggle="tooltip" data-placement="bottom">삭제</button>
			                     	 </c:if>
	                        	</div>
                    	</div>
                    </div>
				</div>
			</div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>