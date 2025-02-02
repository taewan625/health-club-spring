<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/views/com/header.jsp"%>

<h1 class="main-title">통계 페이지</h1>

<!-- 목록 content 시작 -->
<div id="core" name="core" class="tables-container">
	<c:set var="newUserCount" value="${fn:length(staticInfo.newUser)}" />
	
	<c:if test="${newUserCount <= 5}">
	  <h2 id="newUserList" name="newUserList" class="sub-title">최근등록 회원 (${newUserCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<c:if test="${newUserCount > 5}">
	  <h2 id="newUserList" name="newUserList" class="sub-title">최근등록 회원 (5건 / ${newUserCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<div id="recentTable" class="table-container">
		<div class="table-header">
		    <div class="table-cell">번호</div>
		    <div class="table-cell">회원 아이디</div>
		    <div class="table-cell">회원명</div>
	    </div>
	    
    	<c:forEach var="newUser" items="${staticInfo.newUser}" varStatus="loop">
    		<div name="newUser" class="osl-evt__table__row table-row">
	    		<div class="table-cell" data-type="user" data-id="${newUser.userId}">${loop.index + 1}</div>
    			<div class="table-cell">${newUser.userId}</div>
    			<div class="table-cell">${newUser.userName}</div>
    		</div>
		</c:forEach>
	</div>
	
	
	<c:set var="finishUserCount" value="${fn:length(staticInfo.finishUser)}" />
	
	<c:if test="${finishUserCount <= 5}">
	  <h2 id="finishUserList" name="finishUserList" class="sub-title">기간 임박 회원 (${finishUserCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<c:if test="${finishUserCount > 5}">
	  <h2 id="finishUserList" name="finishUserList" class="sub-title">기간 임박 회원 (5건 / ${finishUserCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<div id="almostFinishTable" class="table-container">
		<div class="table-header">
		    <div class="table-cell">번호</div>
		    <div class="table-cell">회원 아이디</div>
		    <div class="table-cell">회원명</div>
	    </div>
	    
       	<c:forEach var="finishUser" items="${staticInfo.finishUser}" varStatus="loop">
    		<div name="finishUser" class="osl-evt__table__row table-row">
	    		<div class="table-cell" data-type="user" data-id="${finishUser.userId}">${loop.index + 1}</div>
    			<div class="table-cell">${finishUser.userId}</div>
    			<div class="table-cell">${finishUser.userName}</div>
    		</div>
		</c:forEach>
	</div>
	
	
	<c:set var="user30MostCount" value="${fn:length(staticInfo.user30Most)}" />
	
	<c:if test="${user30MostCount <= 5}">
	  <h2 id="user30MostList" name="user30MostList" class="sub-title">30분 수업 횟수 최대 회원 (${user30MostCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<c:if test="${user30MostCount > 5}">
	  <h2 id="user30MostList" name="user30MostList" class="sub-title">30분 수업 횟수 최대 회원 (5건 / ${user30MostCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<div id="maxUser30Table" class="table-container">
		<div class="table-header">
		    <div class="table-cell">번호</div>
		    <div class="table-cell">회원 아이디</div>
		    <div class="table-cell">회원명</div>
		    <div class="table-cell">수업 횟수</div>
	    </div>
	    
       	<c:forEach var="user30Most" items="${staticInfo.user30Most}" varStatus="loop">
    		<div name="user30Most"  class="osl-evt__table__row table-row">
	    		<div class="table-cell" data-type="user" data-id="${user30Most.userId}">${loop.index + 1}</div>
    			<div class="table-cell">${user30Most.userId}</div>
    			<div class="table-cell">${user30Most.userName}</div>
    			<div class="table-cell">${user30Most.ptCount}</div>
    		</div>
		</c:forEach>
	</div>
	
	
	<c:set var="user50MostCount" value="${fn:length(staticInfo.user50Most)}" />
	
	<c:if test="${user50MostCount <= 5}">
	  <h2 id="user50MostList" name="user50MostList" class="sub-title">50분 수업 횟수 최대 회원 (${user50MostCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<c:if test="${user50MostCount > 5}">
	  <h2 id="user50MostList" name="user50MostList" class="sub-title">50분 수업 횟수 최대 회원 (5건 / ${user50MostCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<div id="maxUser50Table" class="table-container">
		<div class="table-header">
		    <div class="table-cell">번호</div>
		    <div class="table-cell">회원 아이디</div>
		    <div class="table-cell">회원명</div>
		    <div class="table-cell">수업 횟수</div>
	    </div>
	    
       	<c:forEach var="user50Most" items="${staticInfo.user50Most}" varStatus="loop">
    		<div name="user50Most" class="osl-evt__table__row table-row">
	    		<div class="table-cell" data-type="user" data-id="${user50Most.userId}">${loop.index + 1}</div>
    			<div class="table-cell">${user50Most.userId}</div>
    			<div class="table-cell">${user50Most.userName}</div>
    			<div class="table-cell">${user50Most.ptCount}</div>
    		</div>
		</c:forEach>
	</div>
	
	
	<c:set var="trainer30MostCount" value="${fn:length(staticInfo.trainer30Most)}" />
	
	<c:if test="${trainer30MostCount <= 5}">
	  <h2 id="trainer30MostList" name="trainer30MostList" class="sub-title">30분 수업 수행 횟수 최대 트레이너 (${trainer30MostCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<c:if test="${trainer30MostCount > 5}">
	  <h2 id="trainer30MostList" name="trainer30MostList" class="sub-title">30분 수업 수행 횟수 최대 트레이너 (5건 / ${trainer30MostCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<div id="maxTrainer30Table" class="table-container">
		<div class="table-header">
		    <div class="table-cell">번호</div>
		    <div class="table-cell">트레이너 아이디</div>
		    <div class="table-cell">트레이너명</div>
		    <div class="table-cell">수업 횟수</div>
	    </div>
	    
       	<c:forEach var="trainer30Most" items="${staticInfo.trainer30Most}" varStatus="loop">
    		<div name="trainer30Most" class="osl-evt__table__row table-row">
	    		<div class="table-cell" data-type="trainer" data-id="${trainer30Most.trainerId}">${loop.index + 1}</div>
    			<div class="table-cell">${trainer30Most.trainerId}</div>
    			<div class="table-cell">${trainer30Most.trainerName}</div>
    			<div class="table-cell">${trainer30Most.ptCount}</div>
    		</div>
		</c:forEach>
	</div>
	
	
	<c:set var="trainer50MostCount" value="${fn:length(staticInfo.trainer50Most)}" />
	
	<c:if test="${trainer50MostCount <= 5}">
	  <h2 id="trainer50MostList" name="trainer50MostList" class="sub-title">50분 수업 수행 횟수 최대 트레이너 (${trainer50MostCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<c:if test="${trainer50MostCount > 5}">
	  <h2 id="trainer50MostList" name="trainer50MostList" class="sub-title">50분 수업 수행 횟수 최대 트레이너 (5건 / ${trainer50MostCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<div id="maxTrainer50Table" class="table-container">
		<div class="table-header">
		    <div class="table-cell">번호</div>
		    <div class="table-cell">트레이너 아이디</div>
		    <div class="table-cell">트레이너명</div>
		    <div class="table-cell">수업 횟수</div>
	    </div>
       	<c:forEach var="trainer50Most" items="${staticInfo.trainer50Most}" varStatus="loop">
    		<div name="trainer50Most" class="osl-evt__table__row table-row">
	    		<div class="table-cell" data-type="trainer" data-id="${trainer50Most.trainerId}">${loop.index + 1}</div>
    			<div class="table-cell">${trainer50Most.trainerId}</div>
    			<div class="table-cell">${trainer50Most.trainerName}</div>
    			<div class="table-cell">${trainer50Most.ptCount}</div>
    		</div>
		</c:forEach>
	</div>
	
	
	<c:set var="finishPtCount" value="${fn:length(staticInfo.finishPt)}" />
	
	<c:if test="${finishPtCount <= 5}">
	  <h2 id="finishPtList" name="finishPtList" class="sub-title">최근 수행된 개인 수업 일정 (${finishPtCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<c:if test="${finishPtCount > 5}">
	  <h2 id="finishPtList" name="finishPtList" class="sub-title">최근 수행된 개인 수업 일정 (5건 / ${finishPtCount}건) (금일 ~ 30일전)</h2>
	</c:if>
	
	<div id="recentPtTable" class="table-container">
		<div class="table-header">
		    <div class="table-cell">번호</div>
		    <div class="table-cell">수업명</div>
		    <div class="table-cell">회원 아이디</div>
		    <div class="table-cell">트레이너 아이디</div>
		    <div class="table-cell">수업일자</div>
		    <div class="table-cell">수업시작 시간</div>
	    </div>
        <c:forEach var="finishPt" items="${staticInfo.finishPt}" varStatus="loop">
    		<div name="finishPt"  class="osl-evt__table__row table-row">
	    		<div class="table-cell" data-type="pt" data-id="${finishPt.ptId}">${loop.index + 1}</div>
    			<div class="osl-evt__table-cell table-cell">${finishPt.ptCode}</div>
    			<div class="table-cell">${finishPt.userId}</div>
    			<div class="table-cell">${finishPt.trainerId}</div>
    			<div class="table-cell">${finishPt.ptDate}</div>
    			<div class="table-cell">${finishPt.ptStartTime}</div>
    		</div>
		</c:forEach>
	</div>
</div>

<form id="postPopup" name="postPopup">
    <input type="hidden" id="postPopupValue" name="postPopupValue"/>
</form>



<%@ include file="/WEB-INF/jsp/views/com/footer.jsp"%>



<script type="text/javascript">
	// 초기화 이벤트
	$(document).ready(function() {
		gfnTitle('통계 페이지');
		var ptClass = $('.osl-evt__table-cell');

		ptClass.each(function(element) {
		    $(this).text(fnPtClass(this.innerText));
		});
		
		
		// 최근 등록 회원 
	  	$('.osl-evt__table__row[name="newUser"]').slice(5).hide();

	  	$('#newUserList').on('click', function() {
	    	$('.osl-evt__table__row[name="newUser"]').slice(5).toggle();  
	  	});
	  	
	  	
		// 기간 임박 회원
	  	$('.osl-evt__table__row[name="finishUser"]').slice(5).hide();

	  	$('#finishUserList').on('click', function() {
	    	$('.osl-evt__table__row[name="finishUser"]').slice(5).toggle();  
	  	});
	  	
	  	
		// 30분 수업 횟수 최대 회원
	  	$('.osl-evt__table__row[name="user30Most"]').slice(5).hide();

	  	$('#user30MostList').on('click', function() {
	    	$('.osl-evt__table__row[name="user30Most"]').slice(5).toggle();  
	  	});
	  	
	  	
		// 50분 수업 횟수 최대 회원
	  	$('.osl-evt__table__row[name="user50Most"]').slice(5).hide();

	  	$('#user50MostList').on('click', function() {
	    	$('.osl-evt__table__row[name="user50Most"]').slice(5).toggle();  
	  	});
	  	
	  	
		// 30분 수업 수행 횟수 최대 트레이너
	  	$('.osl-evt__table__row[name="trainer30Most"]').slice(5).hide();

	  	$('#trainer30MostList').on('click', function() {
	    	$('.osl-evt__table__row[name="trainer30Most"]').slice(5).toggle();  
	  	});
	  	
	  	
		// 50분 수업 수행 횟수 최대 트레이너
	  	$('.osl-evt__table__row[name="trainer50Most"]').slice(5).hide();

	  	$('#trainer50MostList').on('click', function() {
	    	$('.osl-evt__table__row[name="trainer50Most"]').slice(5).toggle();  
	  	});
	  	
	  	
		// 최근 수행된 개인 수업 일정
	  	$('.osl-evt__table__row[name="finishPt"]').slice(5).hide();

	  	$('#finishPtList').on('click', function() {
	    	$('.osl-evt__table__row[name="finishPt"]').slice(5).toggle();  
	  	});
	  	
	  	
	  	// 상세 팝업창
		$('#core').on("dblclick", ".osl-evt__table__row", function() {
			var dataType = $(this).find(':first-child').data('type');
			var dataId = $(this).find(':first-child').data('id');

			$('#postPopupValue').val(dataId);
			
			gfnPopup(fngetUrl(dataType), 'postPopup', $('#postPopup'));
			
			$('#postPopupValue').val('');
		});
	  	
	});
	
	/**
	 * @function fngetUrl 
	 * @description  이동 경로 반환
	 * @param dataType
	 * @return String
	*/
	function fngetUrl(dataType) {
		if (dataType === 'user') {
			return '/cmm/cmm2000/cmm2000/selectCmm2004View.do';
		} else if (dataType === 'trainer') {
			return '/cmm/cmm2000/cmm2000/selectCmm2005View.do';
		} else if (dataType === 'pt') {
			return '/cmm/cmm2000/cmm2000/selectCmm2006View.do';
		}
	}
	
	
	
	/**
	 * @function fnPtClass 
	 * @description  pt 수업 코드를 수업명으로 변경
	 * @param ptCode
	 * @return String
	*/
	function fnPtClass(ptCode) {
		if (ptCode === '01') {
			return '30분 수업';
		} else {
			return '50분 수업';
		}
	}
	
</script>




</body>
</html>