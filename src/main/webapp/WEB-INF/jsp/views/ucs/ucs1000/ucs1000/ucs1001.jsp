<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>수업 상세팝업</title>
		<link rel="stylesheet" href="/css/styles.css">
	</head>

	<body>
		<h1 class="main-title">수업 상세</h1>
			<form class="save-forms">
				<div>
					<div class="save-seperate-line"></div>
					
					<div>
						<div class="save-form__item">
							<label for="ptJoin" class="save-form__item-label">수업일 </label>
							<div><c:out value="${userPtInfo.ptDate}"></c:out></div>
						</div>
							
						<div class="save-form__item">
							<label for="ptStartTime" class="save-form__item-label">수업 시작 시간 </label>
							<div><c:out value="${userPtInfo.ptStartTime}"></c:out></div>
						</div>
		
						<div class="save-form__item">
							<label class="save-form__item-label">수업 종류</label>
							
							<c:if test="${userPtInfo.ptCode eq '01'}">
								<label for="aClass"> 30분 수업 </label>
							 </c:if>
							 
							<c:if test="${userPtInfo.ptCode eq '02'}">
								<label for="bClass"> 50분 수업 </label> 
							</c:if>
						</div>
						
						<div class="save-form__item">
							<label for="userId" class="save-form__item-label">회원 id </label>
							<div><c:out value="${userPtInfo.userId}"></c:out></div>
						</div>
	
						<div class="save-form__item">
							<label for="trainerId" class="save-form__item-label">트레이너 id</label>
							<div><c:out value="${userPtInfo.trainerId}"></c:out></div>
						</div>
						
						<div class="save-form__item">
							<label for="ptStatus" class="save-form__item-label">수업 상태</label>
							<div id="ptStatus" name="ptStatus"></div>
						</div>
						
					</div>
					
					<div class="save-seperate-line"></div>
				</div>
		
				<div>
					<button type="button" id="cancel" name="cancel" class="popup-btn--save">닫기</button>
				</div>
	
		    </form>
	</body>
	
	<!-- 모든  검증 기능 동작은 여기서 수행 -->
	<script type="text/javaScript" src="/js/jquery-3.7.1.min.js"></script>
	<script language="JavaScript" src="/js/com/com.js"></script>
	
	<!-- tra1000 동작은 여기서 수행 -->
	<script language="JavaScript">
		$(document).ready(function () {
			// 수업 상태
			$('#ptStatus').text(gfnPtStatus('<c:out value = "${userPtInfo.ptStatus}"/>'))
			
			$('#cancel').on('click', function(){
				window.close();
			})
			
		// .ready 끝
		});
	</script>

</html>
