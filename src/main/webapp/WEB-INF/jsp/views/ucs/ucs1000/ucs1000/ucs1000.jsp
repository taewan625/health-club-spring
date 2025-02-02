<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>수업 관리페이지</title>
	</head>

	<body>
		<%@ include file="/WEB-INF/jsp/views/com/header.jsp"%>
		<div id="calendar" name="calendar" class="calendar"></div>
		
		<form id="postPopup" name="postPopup" name="postPopup">
	    	<input type="hidden" id="postPopupValue" name="postPopupValue"/>
		</form>
	</body>

	<script type="text/javaScript" src="/js/jquery-3.7.1.min.js"></script>
	<script type="text/javaScript" src="/js/index.global.min.js"></script>
	<script type="text/javaScript" src="/js/com/com.js"></script>
	
	<script type="text/JavaScript">
		$(document).ready(function() {
		    // ajax 통신으로 수업 데이터 호출
		    var returnValue = gfnAjaxPost('/ucs/ucs1000/ucs1000/selectUcs1000PtList.do', null);
		 
		    
		    // new 키워드로 calender 생성 : 매개변수로 dom참조값, 객체 (캘린더 커스텀 내부 객체, 표출 이벤트 배열[객체]) 
		    var calendar = new FullCalendar.Calendar($('#calendar')[0], {
		    	// 헤더 설정
		    	headerToolbar: {
		            left: 'prev,next',
		            center: 'title',
		            right: 'today',
		        },
		        
		        // left의 btn text 변경
		        buttonText: {
		            today: '오늘'
		        },
		        
		        // 주 수를 고정하지 않고, 실제 주 수에 따라 동적으로 표시
		        fixedWeekCount: false,
		        // 요일 한국어 표출
		        locale: 'ko',
		        
		        //링크 새창열기
		        eventClick: function(event, jsEvent, view) {
		        	event.jsEvent.preventDefault();

		        	$('#postPopupValue').val(event.event.id);
		        	gfnPopup(event.event.url, 'postPopup', $('#postPopup'));
		        	
		        	$('#postPopupValue').val('');
					return;
		        	
		        },
		        
		        // 달력에 해당하는 일정 표출 - controller에서 [{},{},{} ...] 형식으로 Ajax 데이터 전송 필요 
		        events: returnValue.userPtList.map(function(userPt) {
		            return {
		                // 수업 상태 (수업 예정, 수업 완료, 수업 취소, 노쇼)
		                title: gfnPtStatus(gfnDefenceXSS(userPt.ptStatus)),
		                // 수업 id 
		                url: '/ucs/ucs1000/ucs1000/selectUcs1001View.do',
		                id: gfnDefenceXSS(userPt.ptId),
		                // 수업 일자
		                start: gfnDefenceXSS(userPt.ptDate),
		                color: gfnPtStatusColor(gfnDefenceXSS(userPt.ptStatus)),
		            };
		            
		        }),
		        
		    // calendar 객체 생성
		    });
		    
		    // setOption()을 사용하여 옵션 변경
		    calendar.setOption('firstDay', 1);
		    
	 	    calendar.setOption('titleFormat', function(date) {
		        var year = date.date.year;
		        var month = date.date.month + 1;
		        return year + "년 " + month + "월 수업 ";
		    });
		    
		    // 캘린더 렌더링
		    calendar.render();
		    
		// .ready() 끝
		});
		
		
	</script>
</html>