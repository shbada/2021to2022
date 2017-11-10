<%    
/**
 * 질문과답변 글작성
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
	//저장
    $("#save").click(function(){
	  var obj = document.saveFrm;
	  obj.method = "POST";
	  obj.action = "<c:url value='insertAnswer.do' />";
	  obj.submit();
    });
    
    /** 목록으로 이동 */
    $("#list").click(function(){
 	   location.href="questionList.do";
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
                        <h2>ANSWER WRITE</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                               <form enctype="multipart/form-data" name="saveFrm">
                               		<input type="hidden" name="qIdx" value ="${questionVo.qIdx}" />
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
					                  <th scope="row">질문글 번호</th>
					                    <td colspan="2">
					                     ${questionVo.qIdx }
					                    </td>
					                  </tr>
					                  <tr>
					                  <th scope="row">질문내용</th>
					                    <td colspan="2">
					                     ${questionVo.qDesc}
					                    </td>
					                  </tr>
					                  <tr>
					                  <th scope="row">답변내용</th>
					                    <td colspan="2">
					                     <textarea rows="10" cols="110" name="aDesc" ></textarea>
					                    </td>
					                  </tr>
					                  </tbody>
					               </table>
					              </form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                           <button type="button" class="btn btn-lg m_t_10" name="save" id="save" >저장</button>
                           <button type="button" class="btn btn-lg m_t_10" name="list" id="list" data-toggle="tooltip" data-placement="bottom" >목록</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>