<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<script type="text/javascript"> 
	alert('${msg}'); 
	document.location.href = '${url}'; 
</script>
<!-- 여기서부터 jsp 소스넣기  -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>