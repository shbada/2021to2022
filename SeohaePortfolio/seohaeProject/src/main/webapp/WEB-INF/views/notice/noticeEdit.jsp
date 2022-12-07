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
<style type="text/css">
/* UI Object */
legend{display:none}
.tbl_type,.tbl_type th,.tbl_type td{border:0}
.tbl_type{width:100%;border-top:1px solid #999;border-bottom:1px solid #999;color:#666;font-size:12px;table-layout:fixed}
.tbl_type caption{display:none} 
.tbl_type th{padding:5px 0 4px;border-bottom:solid 1px #d2d2d2;background-color:#f1f1f4;color:#333;font-weight:bold;line-height:18px;vertical-align:top}
.tbl_type td{padding:8px 0 5px 10px;border-bottom:solid 1px #d2d2d2;text-align:left}
.tbl_type td.cont{padding:20px;line-height:1.4em}
.tbl_type2{width:100%;margin-top:15px;border-top:1px solid #999;border-bottom:1px solid #999;color:#666;font-size:12px;table-layout:fixed}
.tbl_type2,.tbl_type2 th,.tbl_type2 td{border:0}
.tbl_type2 caption{display:none}
.tbl_type2 td{padding:5px 0 3px 10px;line-height:1.4em;text-align:left}
.tbl_type2 .input_txt td{padding:5px 0 5px 10px;border-bottom:solid 1px #d2d2d2}
.tbl_type2 .input_txt td input,.tbl_type2 .input_txt td textarea{vertical-align:middle}
.tbl_type2 .input_txt input.name{width:100px;padding:2px 0 1px;border:solid 1px #d2d2d2}
.tbl_type2 .input_txt textarea.comment{width:80%;height:14px;padding:2px 0 1px;border:solid 1px #d2d2d2}
.tbl_type2 .input_txt input.submit{width:60px;border:solid 1px #666;background-color:#f2f2f2;font-family:'돋움';font-size:12px;line-height:normal}
/* //UI Object */
</style>
<script>
var gfv_count = 1;
$(function(){
	/** 공지글 작성 저장 */
    $("#save").click(function(){
       $("#saveFrm").submit();
    });
    /** 공지글 목록으로 이동 */
   $("#list").click(function(){ 
	   location.href="noticeList.do";
   });
    
   /** 파일추가 */
   $("#addFile").on("click", function(e){ 
       e.preventDefault();
       fn_addFile();
   });
    
   /** 파일삭제 */
   $("a[name='delete']").on("click", function(e){ 
       e.preventDefault();
       fn_deleteFile($(this));
   });
});

/** 파일추가 버튼 클릭시 생김 */
function fn_addFile(){
   var str = "<p><input type='file' name='file_"+(gfv_count++)+"'><span class='button'><a href='#this' class='button' name='delete'>삭제</a></span></p>";
   $("#fileDiv").append(str);
   $("a[name='delete']").on("click", function(e){ //삭제 버튼
       e.preventDefault();
       fn_deleteFile($(this));
   });
}

/** 파일삭제 버튼 클릭시 생김 */
function fn_deleteFile(obj){
   obj.parent().parent().remove();
}

</script>
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
                        <h2>NOTICE WRITE</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                               <form action="insertNotice.do" method="post" enctype="multipart/form-data" id="saveFrm">
					               <input  type="hidden" name="state" value="modify" />
									<table class="table table-striped b-t text-sm">
					                  <colgroup>
					                     <col width="100" />
					                     <col width="" />
					                     <col width="100" />
					                     <col width="100" />
					                     <col width="30" />
					                     <col width="30" />
					                     <col width="30" />
					                  </colgroup>
					                  <tbody>
					
					                  <tr>
					                     <th scope="row">TITLE</th>
					                     <td><input type="text" name="title" class="form-control" size="50"/></td>
					                  </tr>
					                  <tr>
					                     <th scope="row">WRITER</th>
					                     <td><input type="text" name="writer" class="form-control" size="20" value="${sessionScope.userId}" readonly/></td>
					                  </tr>
					                  <tr>
					                  <th scope="row">CONTENTS</th>
					                    <td>
					                     <textarea rows="20" cols="110" name="contents" class="form-control" ></textarea>
					                    </td>
					                  </tr>
					                  <tr>
					                     <th scope="row">이미지첨부</th>
					                     <td>
					 	                 	<div id="fileDiv">
					 	                 		<p>
					                     			<input type="file" name="file_0" class="form-control">
					                     			<span class="button"><a href="#this" class="button" id="delete" name="delete">삭제</a></span>
					                     		</p>
					    	            	 </div>
					                     </td>
					                  </tr>
					                  <tr>
					                  <th></th>
					                  	<td>
					                     <span class="button"><a href="#" id="addFile">파일추가</a></span>                     
					                  	 </td>
					               	  </tr>
					                  </tbody>
					               </table>
					                <div class="loginButton" style="text-align: center">
			                           <button type="button" class="btn btn-lg m_t_10" name="save" id="save" data-toggle="tooltip" data-placement="bottom">저장</button>
				                       <button type="button" class="btn btn-lg m_t_10" id="list" data-toggle="tooltip" data-placement="bottom">취소</button>
			                        </div>
					            </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>