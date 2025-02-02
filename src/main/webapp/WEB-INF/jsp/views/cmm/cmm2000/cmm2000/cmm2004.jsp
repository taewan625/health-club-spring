<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 상세 페이지</title>
<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
	<h1 id="mainTitle" class="main-title">회원 상세</h1>
	
		<form class="save-forms">
			<!-- user form 내용 -->
			<div>
			
				<div class="save-seperate-line"> </div>
				
				<div>
					<div class="save-form__item">
						<label for="name" class="save-form__item-label">이름</label>
						<div><c:out value="${userInfo.userName}"/></div>
					</div>
					
					<div class="save-form__item">
						<label class="save-form__item-label">성별</label>
						
						<c:if test="${userInfo.userGender eq 1}">
							<label for="boy"> 남자 </label>
						 </c:if>
						 
						<c:if test="${userInfo.userGender eq 2}">
							<label for="girl"> 여자 </label> 
						</c:if>
					</div>
					
					<div class="save-form__item">
						<label for="userId" class="save-form__item-label">아이디</label>
						<div><c:out value="${userInfo.userId}"/></div>
					</div>
	
					<div class="save-form__item">
						<label for="phone" class="save-form__item-label">연락처 </label>
						<div><c:out value="${userInfo.userTel}"/></div>
					</div>
	
					<div class="save-form__item">
						<label for="address" class="save-form__item-label">주소</label>
						<div><c:out value="${userInfo.userAddress}"/></div>
					</div>
					
					<div class="save-form__item">
						<label for="userJoin" class="save-form__item-label" >등록일자</label>
						<div><c:out value="${userInfo.userJoinDate}"/></div>
					</div>
					
					<div class="save-form__item">
						<label for="userExpire" class="save-form__item-label" >만료일자</label>
						<div><c:out value="${userInfo.userEndDate}"/></div>
					</div>
	
					
					<div class="save-form__item">
						<label for="classNum" class="save-form__item-label">개인 수업 횟수</label>
						<div><c:out value="${userInfo.userClassNum}"/></div>
					</div>
					
					<div class="save-form__item">
						<label for="remark" class="save-form__item-label"> 비고 </label>
						<div id="remark"><c:out value="${userInfo.userNote}"/></div>
					</div>
				</div>
				
				<div class="save-form__item">
					<label for="lockerId" class="save-form__item-label">사물함 번호</label>
					<div id="lockerId" name="lockerId"></div>
				</div>

				<div class="save-form__item">
					<label for="lockerExpire" class="save-form__item-label">만료일자</label>
					<div><c:out value="${userInfo.lockerExpire}"/></div>
				</div>
				
			</div>
	
			<!-- usr1001-form__locker 내부에 의미 없는 bottom-section 존재 -->
			<div>
				<button type="button" id="cancel" class="popup-btn--save">닫기</button>
			</div>

	    </form>
</body>



<!-- 모든  검증 기능 동작은 여기서 수행 -->
<script type="text/javaScript" src="/js/jquery-3.7.1.min.js"></script>

<!-- usr1000 동작은 여기서 수행 -->
<script type="text/javaScript">
	$(document).ready(function() {
		// 사물함 번호
		if ('${userInfo.userLockerIssue}' === '-') {
			$('#lockerId').text('${userInfo.lockerId}');

		} else {
			$('#lockerId').text('${userInfo.userLockerIssue}'.replace(/(\d+),(\d+)/, '$1번에서 $2번으로 변경'));
		}
		
		$('#cancel').on('click', function() {
			window.close();
		});	
	})
</script>

</html>
