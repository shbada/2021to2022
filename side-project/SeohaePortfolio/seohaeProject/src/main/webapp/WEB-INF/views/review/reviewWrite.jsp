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
    $("#save").click(function(){
	  var obj = document.saveFrm;
	  obj.method = "POST";
	  obj.action = "<c:url value='insertReivew.do' />";
	  obj.submit();
    });
    
});

function list(idx){ 
	document.hiddenFrm.pdNo.value = idx;
	document.hiddenFrm.method="POST";   		
	document.hiddenFrm.action="<c:url value='/reviewList.do' />";   		
	document.hiddenFrm.submit();
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
                            <h2>REVIEW</h2>
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
                               <form enctype="multipart/form-data" name="saveFrm">
                               		<input type="hidden" name="pdName" value="${book.pdName}">
									<input type="hidden" name="pdUrl" value="${book.pdUrl}">
									<input type="hidden" name="pdInfo" value="${book.pdInfo}">
									<input type="hidden" name="pdNo" value="${book.pdNo}">
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
					                     <th scope="row">교재이름</th>
					                     <td colspan="2"><input type="text" name="pdName" class="form-control" size="50" value="${book.pdName}" readonly/></td>
					                  </tr>
					                  <tr>
					                  <th scope="row">교재</th>
					                     <td colspan="2"><img src="<c:url value="/img/${book.pdUrl}" />" width="150px" height="200px"></td>
					                  </tr>
					                  <tr>
					                  <th scope="row">평가</th>
					                     <td colspan="2">
					                     	<select class="country_to_state country_select" id="reviewLevel" name="reviewLevel">
				                               <option value="5">5점-매우만족</option>
				                               <option value="4">4점-만족</option>
				                               <option value="3">3점-보통</option>
				                               <option value="2">2점-조금부족</option>
				                               <option value="1">1점-부족</option>
				                            </select>
					                     </td>
					                  </tr>
					                  <tr>
					                     <th scope="row">제목</th>
					                     <td colspan="2"><input type="text" name="subject" class="form-control" size="50"/></td>
					                  </tr>
					                  <tr>
					                  <th scope="row">후기내용</th>
					                    <td colspan="2">
					                     <textarea rows="10" cols="110" name="contents" ></textarea>
					                    </td>
					                  </tr>
					                  <tr>
					                     <th scope="row">이미지</th>
					                     <td colspan="2">
					 	                 	<div id="fileDiv">
					 	                 		<p>
					                     			<input type="file" name="img" class="form-control">
					                     		</p>
					    	            	 </div>
					                     </td>
					                  </tr>
					                  </tbody>
					               </table>
					              </form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                           <button type="button" class="btn btn-lg m_t_10" name="save" id="save" >저장</button>
                           <button type="button" class="btn btn-lg m_t_10" onclick="javascript:list('${book.pdNo}');">목록</button>
                        </div>
                        <form name="hiddenFrm">
							<input type="hidden" name="pdNo" value="1">
						</form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>