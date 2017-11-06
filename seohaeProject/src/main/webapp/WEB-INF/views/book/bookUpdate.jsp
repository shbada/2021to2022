<%    
/**
 * 판매교재 수정페이지
 * @author seohae
 * @since 2017. 11. 06.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 06 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/mainTop.do" />
<script>
var gfv_count = 1;
$(function(){
   $("#save").click(function(){
	  var obj = document.saveFrm;
	  obj.method = "POST";
	  obj.action = "<c:url value='bookUpdateSave.do' />";
	  obj.submit();
   });
   
   $("#list").click(function(){ //목록으로 버튼
	   location.href="bookList.do";
   });
   
   $("#deleteBtn").click(function(){
       if(confirm("해당 교재를 삭제하시겠습니까?")){
       	document.form1.method="POST";
           document.form1.action="<c:url value='/bookDelete.do' />";
           document.form1.submit();
       }
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
                            <h2>BOOK BUY</h2>
                        </div>
                        <p> 프로그래밍 관련 교재 판매 게시판입니다. BINO는 프로그래밍 관련 질문과 답변 및 자료 공유 등에 대해 정해진 포인트만큼
                        	적립을 도와드리고 있습니다. 교재 구매시, 현재까지 적립된 포인트를 사용할 수 있습니다. 현재 회원님의 포인트 내역을 알고싶으시면,
                        	<a href="#" style="color: red">포인트 내역</a> 게시판으로 이동해주세요.</p>

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
                        <h2>BOOK UPDATE</h2>
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
                               		<input type="hidden" name="pdNo" value="${BookVo.pdNo}">
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
					                     <th scope="row">개발언어</th>
					                     <td colspan="2"><input type="text" name="pdCg" class="form-control" size="50" value="${BookVo.pdCg}"/></td>
					                  </tr>
					                  <tr>
					                     <th scope="row">교재명</th>
					                     <td colspan="2"><input type="text" name="pdName" class="form-control" size="50" value="${BookVo.pdName}"/></td>
					                  </tr>
					                  <tr>
					                     <th scope="row">교재 가격</th>
					                     <td colspan="2"><input type="text" name="pdPrice" class="form-control" size="20" value="${BookVo.pdPrice}"/></td>
					                  </tr>
					                  <tr>
					                  <th scope="row">교재 설명</th>
					                    <td colspan="2">
					                     <textarea rows="10" cols="110" name="pdInfo" class="form-control" >${BookVo.pdInfo}</textarea>
					                    </td>
					                  </tr>
					                  <tr>
					                     <th scope="row">이미지</th>
					                     <td colspan="2">
					 	                 	<div id="fileDiv">
					 	                 		<p>
					                     			<input type="file" name="product_photo" class="form-control" >
					                     		</p>
					    	            	 </div>
					                     </td>
					                  </tr>
					                  <tr>
					                  <th></th>
					                  	<td colspan="2">
					                     <span class="button"><a href="#" id="save" class="btn btn-lg m_t_10">저장</a></span>   
					                     <button type="button" class="btn btn-lg m_t_10" id="deleteBtn">삭제</button>                  
					                     <span class="button"><a href="#" id="list" class="btn btn-lg m_t_10">목록</a></span>                     
					                  	 </td>
					               	  </tr>
					                  </tbody>
					               </table>
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