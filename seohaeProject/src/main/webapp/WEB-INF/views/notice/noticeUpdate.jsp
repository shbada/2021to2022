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
var gfv_count = '${fn:length(list)+1}';
$(function(){

	viewCnt(); //조회수
	likeCnt(); //추천수
	 
	/** 파일 추가 버튼 */
   $("#addFile").on("click", function(e){ 
       e.preventDefault();
       fn_addFile();
   });
    
   /** 파일 삭제 버튼 */
   $("a[name^='delete']").on("click", function(e){ 
       e.preventDefault();
       fn_deleteFile($(this));
   });
   
   /** 공지글 수정 완료 */
   $("#update").click(function(){
	   document.frm.method="POST";
	   document.frm.action="<c:url value='/noticeUpdate.do' />";
	   document.frm.submit();
   });
   
   /** 공지사항 목록으로 이동 */
   $("#list").click(function(){
	   location.href="noticeList.do";
   });
   
   /** 해당 글 삭제 */
   $("#delete").click(function(){
	   location.href="/noticeDelete.do?no=${detail.no}";
   });
   
});

/** 파일 추가 버튼 클릭시 */
function fn_addFile(){
	   var str = "<p><input type='file' id = 'file_"+(gfv_count)+"' name='file_"+(gfv_count)+"'><span class='button'><a href='#this' class='button' id = 'delete_"+(gfv_count)+"'  name='delete_"+(gfv_count)+"'>삭제</a></span></p>";
	   $("#fileDiv").append(str);
	   $("#delete_"+(gfv_count++)).on("click", function(e){ //삭제 버튼
           e.preventDefault();
           fn_deleteFile($(this));
       });
}

/** 파일 삭제 버튼 클릭시 */
function fn_deleteFile(obj){
   obj.parent().parent().remove();
}

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
                        <h2>NOTICE EDIT</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                               <form name="frm" id="frm" enctype="multipart/form-data">
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
													<input type="text" id="title" name="title" class="form-control" size="50" value="${detail.title}"/>
												</td>
											</tr>
											<tr>
												<th>contents</th>
												<td colspan="5">
													<textarea name="contents" rows="20" cols="110" >${detail.contents}</textarea>
												</td>
											</tr>
											<tr>
								                <th scope="row">첨부파일</th>
								                <td colspan="5">
								               		<div id="fileDiv">
									                    <c:forEach var="row" items="${list }" varStatus="var">
						                        		<p>
								                            <input type="hidden" id="IDX" name="IDX_${var.index}" value="${row.no }" />
								                            <a href="#this" id="name_${var.index }" name="name_${var.index }">${row.oName }</a>
								                            <input type="file" id="file_${var.index }" name="file_${var.index }" /> 
								                            (${row.fileSize }kb)
								                            <span class="button"><a href="#this" id="delete_${var.index }" name="delete_${var.index }">삭제</a></span>
						                       			</p>
					                       				</c:forEach>
				                       				</div>
								                </td>
								            </tr>
				
											<tr>
											<th></th>
												<td colspan="5">
													<span class="button"><a href="#" id="addFile">파일추가</a></span>  
												</td>
											</tr>
										</tbody>
									</table>
								</form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                           	<button type="button" class="btn btn-lg m_t_10" name="list" id="list" data-toggle="tooltip" data-placement="bottom" >목록</button>
	                        <button type="button" class="btn btn-lg m_t_10" name="update" id="update" data-toggle="tooltip" data-placement="bottom" >저장</button>
		                    <button type="button" class="btn btn-lg m_t_10" id="delete" data-toggle="tooltip" data-placement="bottom">삭제</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>