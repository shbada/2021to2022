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
<style>
.btn-outline-warning {
    color: #f39c12;
    background-color: #fff;
    border-color: #f39c12;
}
.btn-outline-warning:hover {
    color: #fff;
    background-color: #f1c40f;
    border-color: #f1c40f;
}
.btn-outline-danger {
    color: #c0392b;
    background-color: #fff;
    border-color: #c0392b;
}
.btn-outline-danger:hover {
    color: #fff;
    background-color: #e74c3c;
    border-color: #e74c3c;
}
</style>
<script>
$(document).ready(function() {
	 //최상단 체크박스 클릭
   $("#checkall").click(function(){
       //클릭되었으면
       if($("#checkall").prop("checked")){
           //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
           $("input[name=chk]").prop("checked",true);
           //클릭이 안되있으면
       }else{
           //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
           $("input[name=chk]").prop("checked",false);
       }
   });		    
});

function AdminDel() {
	   var chkedVal = new Array(); //배열
	   var chkedObj = null;
	   
	   $(":checkbox[name='chk']:checked").each(function(i){
	       chkedObj = new Object();
	       chkedObj.userId = $(this).val(); 
	       chkedVal[i] = chkedObj; 
	    });
	   if(chkedVal.length == 0){
	      alert("선택된 목록이 없습니다. 영구제명하시려는 회원을 선택하세요");
	      return;
	   }else {
	      if (confirm("선택된 회원들을 영구제명하시겠습니까??") == true){    
	          $.ajax({               
	               type:"POST",
	               url:"/allListDelete.do", 
	               dataType:"JSON",
	               data : JSON.stringify(chkedVal),
	               contentType: "application/json; charset=UTF-8",
	               async : false,
	               complete: function() {
	                  alert("처리가 완료되었습니다.")
	                  window.location.reload();   
	                }            
	            });
	      }else{   //취소
	          return;
	      }
	   }
	}


function memberDelete(idx){ 
	if(confirm("해당 회원을 영구제명 하시겠습니까?") == true){
		document.form1.userId.value = idx;
		document.form1.method="POST";   		
		document.form1.action="<c:url value='/memberDelete.do' />";   		
		document.form1.submit();
	}
}

function memberDown(idx){ 
	if(confirm("해당 회원을 '일반회원'으로 강등하겠습니까?") == true){
		document.form1.userId.value = idx;
		document.form1.method="POST";   		
		document.form1.action="<c:url value='/memberDown.do' />";   		
		document.form1.submit();
	}
}

function memberUp(idx){ 
	if(confirm("해당 회원을 '관리자'로 등업하시겠습니까?") == true){
		document.form1.userId.value = idx;
		document.form1.method="POST";   		
		document.form1.action="<c:url value='/memberUp.do' />";   		
		document.form1.submit();
	}
}

function listDetail(idx){ 
  document.form1.userId.value = idx;
  document.form1.method="POST";         
  document.form1.action="<c:url value='/memberDetail.do' />";         
  document.form1.submit();
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
                            <h2>ADMIN</h2>
                        </div>
                        <p> 관리자 전용 페이지입니다.</p>

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
                        <h2>ALL MEMBER LIST</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                               <!-- *********************************************************************************** -->
                               <form name="form1" method="post" action="#">
				           		<input type="hidden" name="userId" value="1">
									<table cellspacing="0" class="table table-striped b-t text-sm">
									<tr>
										<!-- 체크박스 추가하여 체크에 따라 회원 강제 삭제 -->
										<td><input type="checkbox" id="checkall" name="checkall" /></td>
										<th>회원번호</th>
										<th>회원아이디</th>
										<th>이름</th>
										<th>회원등급</th> 
										<th>가입날짜</th>
										<th>탈퇴여부</th>
										<th>적립금</th> <!-- 최신 적립금 액수를 가져와 기록 -->			
										<th>제명</th> <!-- 사용자, 관리자 조절하기(DB연동) -->			
										<th>등업</th> <!-- 사용자, 관리자 조절하기(DB연동) -->	
									</tr>
									<c:forEach var="member" items="${list }">
										<tr>
											<td><input type="checkbox" name="chk" value="${member.userId }"/></td>
											<td>${member.registNo }</td>
											<td>
												 <a href="#" class="link" onclick="javacscript:listDetail('${member.userId }');">
													${member.userId }
												 </a>
											</td>				
											<td>${member.userNm }</td>
											<td>
												<c:if test="${member.userLevel == 'ADMIN' }">
													<sapn class="link" style="color:blue;">관리자</sapn>
												</c:if>
												<c:if test="${member.userLevel == 'USER' }">
													일반회원
												</c:if>
											</td>
											<td>${member.regDe }</td>
											<td>
												<c:if test="${member.userDelYn == 'Y' }">
													<sapn class="link" style="color:red;">탈퇴</sapn>
												</c:if>
												<c:if test="${member.userDelYn == 'N' }">
													가입
												</c:if>
											</td>
											<td>${member.bino }</td>				
											<td>		
												<a href="#" class="link" onclick="javacscript:memberDelete('${member.userId }');">			
													<button type="button" class="btn-outline-warning hover">영구제명</button>
												</a>																																										
											</td>	
											<td>					
												<a href="#" class="link" onclick="javacscript:memberDown('${member.userId }');">
													<c:if test="${member.userLevel == 'ADMIN' }">
														<button type="button" class="btn-outline-danger hover">강등</button>	
													</c:if>
												</a>
												<a href="#" class="link" onclick="javacscript:memberUp('${member.userId }');">
													<c:if test="${member.userLevel == 'USER' }">
														<button type="button" class="btn-outline-danger hover">등업</button>	
													</c:if>	
												</a>																																								
											</td>			
										</tr>
									</c:forEach> <!-- 컨트롤러의 addAttribute의 list -->		
								</table>
							</form>
                            </div>
                        </div>
                        <div class="loginButton" style="text-align: center">
                          <button type="button" class="btn btn-danger btn-s-xs btnList" id="requestDelete2" onclick="AdminDel();">선택 회원 제명 </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->


<%@ include file="/WEB-INF/include/include-footer.jsp" %>