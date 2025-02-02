<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>트레이너 상세 페이지</title>
		<link rel="stylesheet" href="/css/styles.css">
	</head>
	
	<body>
		<h1 id="mainTitle" class="main-title">트레이너 상세</h1>
			
			<form class="save-forms">
				<div>
				
					<div class="save-seperate-line"> </div>
					
					<div>
						<div class="save-form__item">
							<label for="name" class="save-form__item-label">이름</label>
							<div><c:out value="${trainerInfo.trainerName}"/></div>
						
						</div>
		
						<div class="save-form__item">
							<label class="save-form__item-label">성별</label>
							
							<c:if test="${trainerInfo.trainerGender eq 1}">
								<label for="boy"> 남자 </label>
							 </c:if>
							 
							<c:if test="${trainerInfo.trainerGender eq 2}">
								<label for="girl"> 여자 </label> 
							</c:if>
						</div>
		
						
						<div class="save-form__item">
							<label for="trainerId" class="save-form__item-label">아이디</label>
							<div><c:out value="${trainerInfo.trainerId}"/></div>
						</div>
						
						
		
						<div class="save-form__item">
							<label for="phone" class="save-form__item-label">연락처 </label>
							<div><c:out value="${trainerInfo.trainerTel}"/></div>
						</div>
		
		
						<div class="save-form__item">
							<label for="address" class="save-form__item-label">주소</label>
							<div><c:out value="${trainerInfo.trainerAddress}"/></div>
						</div>
		
						<div class="save-form__item">
							<label for="trainerJoin" class="save-form__item-label" >입사 일자</label>
							<div><c:out value="${trainerInfo.trainerJoinDT}"/></div>
						</div>
						
						
						<div class="save-form__item">
							<label for="trainerWorkStartTime" class="save-form__item-label"> 근무 시작 시간 </label>
							<div id="trainerWorkStartTime" name="trainerWorkStartTime"><c:out value = "${trainerInfo.trainerWorkStartTime}"/></div>
						</div>
						
						<div class="save-form__item">
							<label for="trainerWorkEndTime" class="save-form__item-label" > 근무 종료 시간 </label>
							<div id="trainerWorkEndTime" name="trainerWorkEndTime"></div>
						</div>
					
						<div class="save-form__item">
							<label for="remark" class="save-form__item-label"> 비고 </label>
							<div id="remark"><c:out value="${trainerInfo.trainerNote}"/></div>
						</div>
								
					</div>
					
					<div class="save-seperate-line"></div>
				</div>
		
				<div>
					<button type="button" id="cancel" class="popup-btn--save">닫기</button>
				</div>
	
		    </form>
	</body>



	<!-- 모든  검증 기능 동작은 여기서 수행 -->
	<script type="text/javaScript" src="/js/jquery-3.7.1.min.js"></script>
	<!-- tra1000 동작은 여기서 수행 -->
	<script language="JavaScript">
		$(document).ready(function() {
			// 시간과 분 분리
			 var startTime = '<c:out value = "${trainerInfo.trainerWorkStartTime}"/>'.split(':');
			 var time = startTime[0];
			 var minute = startTime[1];
		  
			 // 시간 계산
			 time = (parseInt(time, 10) + parseInt('<c:out value = "${trainerInfo.trainerWorkTime}"/>', 10)) % 24;
			 time = (time < 10) ? '0' + time : time;
	
			// 근무 종료 시간
			$('#trainerWorkEndTime').text(time + ':' + minute);
			
			
			$('#cancel').on('click', function() {
				window.close();
			});	
		});
	</script>
</html>
