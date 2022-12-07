<%    
/**
 * 받은쪽지 목록
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
<script type="text/javascript">
function messageSendWrite(idx){ 
	document.form1.msgSend.value = idx;
	document.form1.method="POST";   		
	document.form1.action="<c:url value='/messageSendWrite.do' />";   		
	document.form1.submit();
}

function MessageDel() {
	var chkedVal = new Array(); //배열
	var chkedObj = null;
	
	$(":checkbox[name='chk']:checked").each(function(i){
		 chkedObj = new Object();
		 chkedObj.msgNo = $(this).val(); 
		 chkedVal[i] = chkedObj; 
	 });
	if(chkedVal.length == 0){
		alert("선택된 목록이 없습니다. 삭제하시려는 목록을 체크하세요");
		return;
	}else {
		if (confirm("정말 삭제하시겠습니까??") == true){    
			 $.ajax({					
					type:"POST",
					url:"/messageGetListDel.do", 
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
    
    $("#messageSendList").click(function(){
        location.href="/messageSendList.do";
    });
});

function MessageDelete(idx){ 
	if(confirm("받은 쪽지를 삭제하시겠습니까?")){
		document.form1.msgNo.value = idx;
		document.form1.method="POST";   		
		document.form1.action="<c:url value='/messageGetDelete.do' />";   		
		document.form1.submit();
	}
}

function MessageDetail(idx){ 
	document.form1.msgNo.value = idx;
	document.form1.method="POST";   		
	document.form1.action="<c:url value='/messageDetail.do' />";   		
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
                            <h3>받은쪽지함</h3>
                        </div>
                        <p> 받은 쪽지함의 도착한 쪽지를 확인해주세요.</p>
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
				<input type="hidden" name="msgNo" value="1">
				<input type="hidden" name="msgSend" value="1">
	                <table cellspacing="0" class="table table-striped b-t text-sm">
	                    <thead>
	                        <tr>
	                        	<th style="text-align: center;">
									<input type="checkbox" id="checkall" name="checkall" />
								</th>
	                            <th class="product-remove">보낸이</th>
	                            <th class="product-thumbnail">제목</th>
	                            <th class="product-thumbnail">시간</th>
	                            <th class="product-quantity">답장</th>
	                            <th class="product-quantity">삭제</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    	<c:forEach var="row" items="${list}" varStatus="i">
	                         <tr class="cart_item">
	                         	<td style="text-align: center;">
									<input type="checkbox" name="chk" value="${row.msgNo }">
								</td>
	                             <td class="product-thumbnail">
	                                 ${row.msgSend }
	                             </td>
	                             
	                             <td class="product-thumbnail">
	                             	 <a href="#" class="link" onclick="javacscript:MessageDetail('${row.msgNo }');">
	                             	 <c:if test="${row.msgReadyn == 'N'}">
	                                 	<sapn style="color:red;">[NEW]</sapn>
	                                 </c:if>
	                                 ${row.msgName }
	                                 </a>
	                             </td>
	                             <td class="product-thumbnail">
	                                 ${row.msgRegdate }
	                             </td>
	                             
	                             <td class="product-thumbnail">
	                             	 <a href="#" class="link"  onclick="javacscript:messageSendWrite('${row.msgSend }');">
	                                 <button type="button" class="btn-outline-warning hover" id="messageSendWrite">답장보내기 </button>&nbsp;
	                            	 </a>
	                             </td>
	                             <td class="product-thumbnail">
	                             	 <a href="#" class="link" onclick="javacscript:MessageDelete('${row.msgNo }');">
	                                 	<button type="button" class="btn-outline-warning hover" id="deleteBtn">삭제 </button>&nbsp;
	                                 </a>
	                             </td>
	                         </tr>
	                        </c:forEach>
	                    </tbody>
	                </table>
	            </form>
	         <hr />
				<button type="button" class="btn btn-danger btn-s-xs btnList" id="cartDelete" onclick="MessageDel();">선택삭제 </button>&nbsp;
				<button type="button" class="btn btn-primary btn-s-xs btnList" id="messageWrite">쪽지 작성하기 </button>
				<button type="button" class="btn btn-warning btn-s-xs btnList" id="messageSendList">보낸쪽지함 </button>
		</div>
	</div>
</div>
</body>
</html>
