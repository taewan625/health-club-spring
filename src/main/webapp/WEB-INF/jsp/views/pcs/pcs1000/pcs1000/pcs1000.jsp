<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/views/com/header.jsp"%>
	
<body>
	
<!-- 수업 목록 content 시작 -->
<div id="calendar" class="calendar"></div>

<form id="postPopup" name="postPopup">
    <input type="hidden" id="postPopupValue" name="postPopupValue"/>
</form>



<%@ include file="/WEB-INF/jsp/views/com/footer.jsp"%>



<script type="text/javaScript" src="/js/index.global.min.js"></script>
<script type="text/JavaScript">
	$(document).ready(function() {
		gfnTitle("수업일정관리");
	    var numClicks = 0;
	    
	    function fnDetail (event, element, view) {
	    	numClicks = 0;
	    }
	    
	    function fnStatus (event, element, view) {
	    	clearTimeout(timeOut) ;
	    	numClicks = 0 ;
	    }
	    
	    // ajax 통신으로 수업 데이터 호출
	    var returnValue = gfnAjaxPost('/pcs/pcs1000/pcs1000/selectPcs1000PtList.do', null);
	 
	    // ajax 통신 결과로 수행하는 함수
	    if (returnValue.responseStatus === 1) {
	        ptList = returnValue.ptList.map(function(pt) {
	            return {
	                // 수업 상태 (수업 예정, 수업 완료, 수업 취소, 노쇼)
	                title: fnPtStatus(gfnDefenceXSS(pt.ptStatus)),
	                // 수업 id 
	                url: '/pcs/pcs1000/pcs1000/selectPcs1001View.do',
	                id: gfnDefenceXSS(pt.ptId),
	                // 수업 일자
	                start: gfnDefenceXSS(pt.ptDate),
	                color: fnPtStatusColor(gfnDefenceXSS(pt.ptStatus)),
	            };
	        });
	        
		}
	    
	
	    // new 키워드로 calender 생성 : 매개변수로 dom참조값, 객체 (캘린더 커스텀 내부 객체, 표출 이벤트 배열[객체]) 
	    var calendar = new FullCalendar.Calendar($('#calendar')[0], {
	    	// 헤더 설정
	    	headerToolbar: {
	            left: 'prev,next today',
	            center: 'title',
	            right: 'insertButton',
	        },
	        
	        // left의 btn text 변경
	        buttonText: {
	            today: '오늘'
	        },
	        
	        // 등록 버튼
	        customButtons: {
	        	insertButton: {
	        		text: '수업등록',
	        		click: function() {
	        			event.preventDefault();
	        			gfnPopup('/pcs/pcs1000/pcs1000/selectPcs1001View.do', 'postPopup', $('#postPopup'));
	        		}
	        	},
	        },
	        
	        // 주 수를 고정하지 않고, 실제 주 수에 따라 동적으로 표시
	        fixedWeekCount: false,
	        // 요일 한국어 표출
	        locale: 'ko',
	        
	        //링크 새창열기
	        eventClick: function(event, jsEvent, view) {
	        	event.jsEvent.preventDefault(); 

	        	numClicks++;
	        	
	        	switch(numClicks) {
				case 2:
					fnStatus(event, jsEvent, view) ;
					break ;
				case 1:
					timeOut = setTimeout(function() {
						fnDetail(event, jsEvent, view);
						
						$('#postPopupValue').val(event.event.id);
						gfnPopup(event.event.url, 'postPopup', $('#postPopup'));

						$('#postPopupValue').val('');
						return;
						
					}, 400);
					break ;
				}
	        	
	        },
	        
	        // 달력에 해당하는 일정 표출 - controller에서 [{},{},{} ...] 형식으로 Ajax 데이터 전송 필요 
	        events: ptList,
	        
	        
	        dateClick: function(info) {
	        	var todayDate = new Date().getDate();
	        	
	        	var parts = info.dateStr.split('-');
	        	var day = parts[parts.length - 1];
	        	
	        	if (todayDate < Number(day)) {
		        	var childPopup = gfnPopup('/pcs/pcs1000/pcs1000/selectPcs1001View.do?date=' + info.dateStr, 'postPopup', $('#postPopup'));
	        	}
	        },
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
	
	
	 /**
	  * @function    fnPtStatus
	  * @description 수업 상태 코드를 문자열로 변환
	  * @param  ptCode 
	  * @return      해당 조건에 부합하는 text 반환
	 */
	function fnPtStatus(ptCode) {
		 switch (ptCode) {
	        case '00':
	            return '수업 예정';
	        case '01':
	            return '수업 완료';
	        case '02':
	            return '회원 노쇼';
	        case '03':
	            return '회원 취소';
	        case '04':
	            return '트레이너 노쇼';
	        case '05':
	            return '트레이너 취소';
	        default:
	            return '알 수 없음';
	    }
	}
	 
	 /**
	  * @function    fnPtStatus
	  * @description 수업 상태 코드를 문자열로 변환
	  * @param  ptCode 
	  * @return      해당 조건에 부합하는 text 반환
	 */
	function fnPtStatusColor(ptCode) {
		 switch (ptCode) {
	        case '00':
	            return 'green';
	        case '01':
	            return 'blue';
	        case '02':
	            return 'black';
	        case '03':
	            return 'red';
	        case '04':
	            return 'black';
	        case '05':
	            return 'red';
	        default:
	            return 'gray';
	    }
	}
	
</script>



</body>
</html>