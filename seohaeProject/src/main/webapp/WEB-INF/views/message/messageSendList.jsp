<%    
/**
 * 보낸쪽지 목록
 * @author seohae
 * @since 2017. 11. 05.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 05 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/include-header.jsp"%>
<%@ page session="true"%>
<script type="text/javascript">
function messageSendWrite(){ 
	document.form1.method="POST";   		
	document.form1.action="<c:url value='/messageSendWrite.do' />";   		
	document.form1.submit();
}

function MessageDel() {
	var chkedVal = new Array(); //배열
	var chkedObj = null;
	
	$(":checkbox[name='chk']:checked").each(function(i){
		 chkedObj = new Object();
		 chkedObj.msg_no = $(this).val(); 
		 chkedVal[i] = chkedObj; 
	 });
	if(chkedVal.length == 0){
		alert("선택된 목록이 없습니다. 삭제하시려는 목록을 체크하세요");
		return;
	}else {
		if (confirm("정말 삭제하시겠습니까??") == true){    
			 $.ajax({					
					type:"POST",
					url:"/messageSendListDel.do", 
					dataType:"JSON",
					data : JSON.stringify(chkedVal),
					contentType: "application/json; charset=UTF-8",
					async : false,
					complete: function() {
						alert("삭제 되었습니다.")
						window.location.reload();	
				    }				
				});
		}else{   //취소
	    	return;
		}
	}
}

$(document).ready(function(){
	
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
	
    $("#messageWrite").click(function(){
        location.href="/messageWrite.do";
    });
    
    $("#messageList").click(function(){
        location.href="/messageList.do";
    });
});

function MessageDelete(idx){ 
	if(confirm("보낸 쪽지를 삭제하시겠습니까?")){
		document.form1.msg_no.value = idx;
		document.form1.method="POST";   		
		document.form1.action="<c:url value='/messageSendDelete.do' />";   		
		document.form1.submit();
	}
}

function MessageDetail(idx){ 
	document.form1.msg_no.value = idx;
	document.form1.method="POST";   		
	document.form1.action="<c:url value='/messageSendDetail.do' />";   		
	document.form1.submit();
}
</script>
<!-- *** -->
<section id="history" class="history sections">
    <div class="container">
        <div class="row">
            <div class="main_history">
                <div class="col-sm-12">
                    <div class="single_history_content">
                        <div class="head_title">
                            <h3>보낸쪽지함</h3>
                        </div>
                        <p> 회원님이 BINO의 다른 회원에게 보낸 쪽지함의 쪽지 목록입니다.</p>
                    </div>
                </div>
            </div>
        </div><!--End of row -->
    </div><!--End of container -->
</section><!--End of history -->

<div class="single-product-area">
	<div class="row m-n">
		<div class="col-md-4 col-md-offset-4 m-t-lg">
			<!-- ************************ -->
			<form name="form1" method="post">
				<input type="hidden" name="msg_no" value="1">
	                <table cellspacing="0" class="table table-striped b-t text-sm">
	                    <thead>
	                        <tr>
	                        	<th style="text-align: center;">
									<input type="checkbox" id="checkall" name="checkall" />
								</th>
	                            <th class="product-remove">받는이</th>
	                            <th class="product-thumbnail">제목</th>
	                            <th class="product-quantity">삭제</th>
	                            <th class="product-quantity">읽음여부</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    	<c:forEach var="row" items="${list}" varStatus="i">
	                         <tr class="cart_item">
	                         	<td style="text-align: center;">
									<input type="checkbox" name="chk" value="${row.msg_no }">
								</td>
	                             <td class="product-thumbnail">
	                                 ${row.msg_get }
	                             </td>
	                             <td class="product-thumbnail">
	                             	 <a href="#" class="link" onclick="javacscript:MessageDetail('${row.msg_no }');">
	                                 ${row.msg_name }
	                                 </a>
	                             </td>
	                             <td class="product-thumbnail">
	                                 <a href="#" class="link" onclick="javacscript:MessageDelete('${row.msg_no }');">
	                                 	<button type="button" class="" id="deleteBtn">삭제 </button>&nbsp;
	                                 </a>
	                             </td>
	                             <td class="product-thumbnail">
	                             	 <c:if test="${row.msg_readyn == 'N'}">
	                                 	읽지않음
	                                 </c:if>
	                                 <c:if test="${row.msg_readyn == 'Y'}">
	                                 	읽음
	                                 </c:if>
	                             </td>
	                         </tr>
	                        </c:forEach>
	                    </tbody>
	                </table>
	            </form>
	         <hr />
				<button type="button" class="btn btn-danger btn-s-xs btnList" id="cartDelete" onclick="MessageDel();">선택삭제 </button>&nbsp;
				<button type="button" class="btn btn-primary btn-s-xs btnList" id="messageWrite">쪽지 작성하기 </button>
				<button type="button" class="btn btn-warning btn-s-xs btnList" id="messageList">받은쪽지함 </button>
		</div>
	</div>
</div>
</body>
</html>
