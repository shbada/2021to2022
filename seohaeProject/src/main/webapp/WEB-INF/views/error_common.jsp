<%    
/**
 * 에러페이지
 * @author seohae
 * @since 2017. 11. 02.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  (2017. 11. 02 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>${exception.getMessage() }</h4>
	
	<ul>
		<c:forEach items="${exception.getStackTrace() }" var="stack">
			<li>${stack.toString() }</li>
		</c:forEach>
	</ul>
</body>
</html>


