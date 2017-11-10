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
	@media (max-width: 768px){
		.mainAudio{display:none;}
	}
</style>
<script>
   $(function(){
      $("#btnList").click(function(){ //목록으로 
         document.frm.method="POST";
         document.frm.action="<c:url value='/freeBoardSelectList.do' />";
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
         document.frm.action="<c:url value='/freeBorWriteSave.do' />";
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
                            <h2>FREE BOARD WRITE</h2>
                        </div>
                        <p> 자유게시판 입니다. BINO에서는 회원분들의 친목, 공부법 공유, 질문과답변 등 회원님들끼리의 관계가 중요하다고 생각되는 만큼, 자유게시판을 통해
                        	회원분들 서로 자유롭게 이야기할 수 있는 게시판을 마련하였습니다. 많은 이용 부탁드립니다.</p>

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
                        <h2>LOGIN</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                            <form class="form-horizontal" name="frm" onsubmit="return false"> <!-- 엔터키 submit 방지 -->
						      <!-- 목록으로 돌아갈때 그전에 검색어, 페이지 수 등이 같아야하므로 -->
						      <input type="hidden" id="search" name="search" value="${freeBoardVo.search}">
						      <input type="hidden" id="pageNo" name="pageNo" value="${freeBoardVo.pageNo}">
						      <input type="hidden" id="pageNo" name="pageCnt" value="${freeBoardVo.pageCnt}">
						      <section class="panel">
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
						         </div>
						      </section>
						   </form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
							 <button type="button" class="btn btn-lg m_t_10t" id="btnList">목록</button>
               				<button type="button" class="btn btn-lg m_t_10" id="btnSave">저장</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>