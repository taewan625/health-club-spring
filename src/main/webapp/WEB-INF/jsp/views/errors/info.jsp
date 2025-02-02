<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>info page</title>
		<link rel="stylesheet" href="/css/styles.css">
	</head>

	<body>
	    <h1 class="info-contents">
	    	<c:choose>
	        	<c:when test="${empty errMsg}">
	        		현재 점검 중에 있습니다. 양해 부탁드립니다.
	        	</c:when>
	        	<c:otherwise>
	        		${errMsg}
	        	</c:otherwise>
	    	</c:choose>
	    </h1>
	    
		<h3 class="info-contents"> 문의가 필요시, 메일을 보내주시길 바랍니다.</h3>
		
		<h3 class="info-contents">openHealth123@naver.com</h3>
		
	    <c:if test="${empty errMsg}">
	        <a class="info__home--link" href="/">홈으로 이동합니다.</a>
	    </c:if>
	</body>
	
</html>