<%    
/**
 * 1:1 문의 작성(회원)
 * @author seohae
 * @since 2017. 11. 11.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 11 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/mainTop.do" />
<script>
$(function(){
	
	/** 게시글 목록으로 돌아가기위해 [목록] 버튼 클릭시 */
    $("#btnList").click(function(){ //게시글 목록 페이지로 이동
       document.frm.method="POST";
       document.frm.action="<c:url value='/oneList.do' />";
       document.frm.submit();
    });
	
    /** 글작성을 완료하고[저장] 버튼 클릭시 */
    $("#btnSave").click(function(){ //글쓰기 완료
       if($("#reqTitle").val().replace(/\s/g,"").length == 0){ //공백처리: 제목 입력안했을때
          alert("제목을 입력하세요");
          $("#reqTitle").focus(); //제목 입력칸으로 마우스커서가 자동으로 감
          return false;
       }
       if($("#reqDesc").val().replace(/\s/g,"").length == 0){ //공백처리: 내용 입력안했을때
          alert("내용을 입력하세요");
          $("#reqDesc").focus();
          return false;
       }
       document.getElementById("reqDesc").value= $("#reqDesc").val().replace(/\n/g,"<br>"); // 엔터키 처리
       
       document.frm.method="POST";
       document.frm.action="<c:url value='/oneWriteSave.do' />"; //컨트롤러로 이동
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
                            <h2>1:1 REQUEST</h2>
                        </div>
                        <p> 1:1 문의 게시판입니다. BINO를 이용하시는 회원님들의 모든 의견과 충고를 존중하겠습니다. 
                        	BINO에게 질문 또는 의견이 있으신 분들은 1:1 문의글을 작성해주세요. </p>

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
                        <h2>1:1 REQUEST</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                              <form class="form-horizontal" name="frm" onsubmit="return false">
								<!-- 엔터키 submit 방지 -->
								<!-- 목록으로 돌아갈때 그전에 검색어, 페이지 수 등이 같아야하므로 -->
								<section class="panel">
									<header class="panel-heading">
										<div class="hearderDivTitle">
											<span>1:1 문의하기</span>
										</div>
									</header>
									<!-- 입력 틀 시작 -->
									<div class="panel-body">
										<div class="form-group">
											<label class="col-sm-1 control-label">제&nbsp;&nbsp;&nbsp;목</label>
											<div class="col-sm-11">
												<input type="text" class="form-control parsley-validated" name="reqTitle" id="reqTitle" maxlength="100" placeholder="제목을 입력하세요">
											</div>
										</div>
										<div class="line line-dashed line-lg pull-in"></div>
			
										<div class="form-group">
											<label class="col-sm-1 control-label">내&nbsp;&nbsp;&nbsp;용</label>
											<div class="col-sm-11">
												<textarea class="form-control freeTextarea" maxlength="2000" name="reqDesc" id="reqDesc" placeholder="내용을 입력하세요"></textarea>
											</div>
										</div>
									</div>
									<!-- 입력 틀 END -->
								</section>
							</form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                           <button type="button" class="btn btn-lg m_t_10" id="btnList">목록</button>
							<button type="button" class="btn btn-lg m_t_10" id="btnSave">저장</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>