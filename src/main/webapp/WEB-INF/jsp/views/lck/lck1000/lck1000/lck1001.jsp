<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>사물함 배정</title>
		<link rel="stylesheet" href="/css/styles.css">
	</head>
	
	<body>
		<h1 id="mainTitle" class="main-title">사물함 배정</h1>
		
		<form class="save-forms">
			<div>
				
				<div class="save-seperate-line"> 
					<span class="star">*</span> 필수입력사항
				</div>
				
				<div>
					
					<div class="save-form__item">
						<label for="userId" class="save-form__item-label">
							회원 아이디 <span class="star"> * </span>
						</label>
						<input type="text" id="userId" name="userId" class="save-form__item-input" value="${lockerInfo.userId}" placeholder="회원을 선택해주세요" readonly/>
						
						<c:if test="${empty lockerInfo.userId}"> 
							<button type="button" id="selectUserId" class="popup-side-btn" > 회원 선택 </button>
						</c:if>
					</div>
					
							
					<div class="save-form__item">
						<label for="lockerId" class="save-form__item-label">
							사물함 번호 <span class="star"> * </span>
						</label>
						<input type="text" id="lockerId" name="lockerId" class="save-form__item-input" value="${lockerInfo.lockerId}" placeholder="사물함을 선택해주세요" readonly/>
						<c:if test="${empty lockerInfo.lockerId or not empty lockerInfo.userId}"> 
							<button type="button" id="selectLocker" class="popup-side-btn"> 사물함 선택 </button>
						</c:if>
					</div>
						
						
					<div class="save-form__item">
						<label for="lockerExpire" class="save-form__item-label">
							만료일자 <span class="star"> * </span>
						</label>
						<input type="date"  id="lockerExpire" name="lockerExpire" class="save-form__item-input" max = "${lockerInfo.lockerExpire}" value="${lockerInfo.lockerExpire}" />
					</div>
					
				</div>
				
			</div>
	
			<div>
				<button type="submit" id="save"   class="popup-btn--save">배정</button>
				<button type="button" id="cancel" class="popup-btn--save">닫기</button>
			</div>

	    </form>
	    
	</body>


	<script type="text/javaScript" src="/js/jquery-3.7.1.min.js"></script>
	<script type="text/javaScript" src="/js/com/com.js"></script>
	<script type="text/javaScript" src="/js/com/validator.js"></script>

	<script language="JavaScript">
	
	$(document).ready(function () {
		var initData = {
				initLockerId : '<c:out value = "${lockerInfo.lockerId}"/>',
				initUserId   : '<c:out value = "${lockerInfo.userId}"/>',
				initLockerExpire: '<c:out value = "${lockerInfo.lockerExpire}"/>',
		}
		
		// 초기화 : 최대 일자 제한
		$('#lockerExpire').attr('min', gfnFormatDate());
	
		if ('<c:out value = "${lockerInfo.lockerId}"/>' ==='') {
			$('#lockerExpire').val(gfnFormatDate());
		}
		
		// 해당 조건에 부합하는 회원 정보 반환 팝업창
		$('#selectUserId').on('click', function() {
			gfnPopup('/cmm/cmm2000/cmm2000/selectCmm2001View.do?subType=locker', '배정회원 팝업');
		})
		
		// 해당 조건에 부합하는 회원 정보 반환 팝업창
		$('#selectLocker').on('click', function() {
			gfnPopup('/cmm/cmm2000/cmm2000/selectCmm2003View.do', '배정사물함 팝업');
		})
		
		// 사물함 배정 정보 제출 이밴트
		$('#save').on('click', function() {
			saveLocker(initData);
		})
		
		$('#cancel').on('click', function(){
			window.close();
		})
		
	})
	
	function saveLocker(lockerInfo) {
		// 기본 이벤트 방지
		event.preventDefault();
		
		
		if ($('#userId').val() === '') {
			alert('회원 아이디를 선택해주세요');
			return;
		}
		
		if ($('#lockerId').val() === '') {
			alert('사물함 번호를 선택해주세요');
			return;
			
		}
		
		if($('#lockerExpire').val() === '') {
			alert('만료일을 선택해주세요');
			return;
			
		}
		
		
		if (lockerInfo.initLockerId !== '' && lockerInfo.initUserId !== '') {
			lockerInfo['submitType'] = 'edit';

		}
		lockerInfo['userId'] = $('#userId').val();
		lockerInfo['lockerId'] = $('#lockerId').val();
		lockerInfo['lockerExpire'] = $('#lockerExpire').val();
		
		var returnValue = gfnAjaxPost('/lck/lck1000/lck1000/saveLck1000.do', lockerInfo);
		
	    // ajax 통신 결과로 수행하는 함수
	    if (returnValue.responseStatus === 1) {
			alert(returnValue.successMsg);
			
			// 등록 성공 시, 부모창 reload()
			window.opener.location.reload();
			
		    // 최종 화면 닫기
		    window.close();

	    } 
		
	}
	
	</script>

</html>
