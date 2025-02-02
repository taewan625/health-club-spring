<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/views/com/header.jsp"%>
	
<h1 class="main-title"> 사물함 이력 현황 페이지</h1>
		
<!-- 검색 조건 content 시작 -->
<div class="search-box">
  	<div class="search-box__menus">
  	
 	<div id="searchBoxMenusConditions" name="searchBoxMenusConditions" class="search-box__menu--conditions">
 	
 		<span class="search-title">검색</span>
 		
		<select id="searchConditions">
			  <option value="all"          data-type="none">전체보기</option>
			  <option value="lockerId"     data-type="text">사물함 번호</option>
			  <option value="userId"       data-type="text">회원 아이디</option>
			  <option value="userName"     data-type="text">회원명</option>
			  <option value="useCode"      data-type="select">이력유형</option>
		</select>
		
		<input type="hidden" id="searchText" name="searchText" class="osl-evt__search-condition search-condition"  maxlength="20" />
		<select id="searchSelect" name="searchSelect" class="osl-evt__search-condition search-condition" hidden> </select>

 	</div> 	
	</div>
	
	<div class="search-box__btns">
		<div id="select" name="select"  class="search-box__btn">조회</div>
	</div>
	
</div>


<!-- 회원 목록 content 시작 -->
<div id="tableContainer" name="tableContainer" class="table-container">
	 <div class="table-header">
	      <div class="table-cell">번호</div>
	      <div class="table-cell">사물함 번호</div>
	      <div class="table-cell">회원 아이디</div>
	      <div class="table-cell">회원명</div>
	      <div class="table-cell">사물함 만기일</div>
	      <div class="table-cell">이력 유형</div>
	      <div class="table-cell">이력 등록일</div>
    </div>
</div>

<!-- 페이지 핸들러 시작 -->
<div id="pageHandlerBox" class="page-handler-box"></div>


<%@ include file="/WEB-INF/jsp/views/com/footer.jsp"%>


<script type="text/JavaScript">
	//검색 조건 별 hidden 된 요소를 동적으로 표출 이벤트
	$(document).ready(function () {
		gfnTitle('사물함이력현황 페이지');
		
		// 초기 조건 변수 저장
		var requestData = fnInitDataLoad();
		
		// 페이징 검색 사물함 표출 이벤트
		$('#pageHandlerBox').on('click', '.pageHandler', function(event) {
			event.preventDefault();
			requestData = fnPageDataLoad(requestData, this);
		})
		
		// 조건 검색 조회버튼 클릭으로 수행하는 이벤트
		$('#select').on('click', function(){
			requestData = fnSearchDataLoad(requestData);
		})
		
		// 조건 검색 엔터로 수행하는 이벤트
		$('#searchText').on('keypress', function(event) {
		    if(event.keyCode == 13){
				event.preventDefault();
				requestData = fnSearchDataLoad(requestData);
		    }
		});
		
		// 검색 조건 별 hidden 된 요소를 동적으로 표출
		$('#searchConditions').on('change', function() {
			// 선택된 옵션의 값
			gfnSearch($(this).find(':selected').data('type'), fnOptionsHtml($(this).val()));
		
		});
		
		
	// ready 끝
	})

	/**
	 * function : fnOptionsHtml
	 * function 설명 : 옵션 html 문자열 생성
	 * @param value : 특정 select 조건
	 * @return : 옵션 html 문자열 반환
	*/	
	function fnOptionsHtml(value) {
		 if (value === 'useCode') {
			 return '<option value="01">배정</option>'
					+ '<option value="02">변경</option>'
				  	+ '<option value="03">배정해제</option>'
				  	+ '<option value="04">탈취해제</option>'
				  	+ '<option value="05">탈취변경</option>';
		 }
	 }
	
	
	
	/**
	 * @function fnListAjax
	 * @description 목록에서 수행하는 공통 Ajax 함수
	 * @param  requestData : 요청보낼 데이터
	*/
	function fnListAjax(requestData) {
		// trainerList와 페이징정보를 가지고 와서 화면을 동적으로 그리는 ajax
		var returnValue = gfnAjaxPost('/lck/lck1000/lck1100/selectLck1100LockerList.do', requestData);
	    
	    // ajax 통신 결과로 수행하는 함수
	    if (returnValue.responseStatus === 1) {
			// 화면 다시 그리기
	    	fnReRender(returnValue.jsonResponseData, requestData.currentPage);
		}
	}

	
	/**
	 * @function fnInitDataLoad
	 * @description  초기로딩 및 새로 고침을 할 경우 로딩하는 메서드
	 * @return 조건에 사용된 객체 반환
	*/
	function fnInitDataLoad() {
		var requestData = {
				searchKey   : undefined,
				searchValue : undefined,
				currentPage : '1',
		}
		
		fnListAjax(requestData);
	    return requestData;
	}
	
	
	/**
	 * @function fnSearchDataLoad
	 * @description 조회 데이터 목록
	 * @param requestData 
	 * @return requestData
	*/
	function fnSearchDataLoad(requestData) {
		// 검색 조건 종류 : 1.전체조회 2.입력 3.검색
		var type = $('#searchConditions').find(':selected').data('type');
		// 1. 전체 조건일 경우
		if (type === 'none') {
			return fnInitDataLoad();
			
		}
		// 2. 입력하는 검색 조건
		else if (type === 'text') {
			// 입력값이 존재하지 않을 경우 조회 거부
			if ($('#searchText').val().trim() === '') {
				alert('입력값이 존재하지 않습니다.');
				return requestData;
			}
			
			requestData['searchValue'] = $('#searchText').val().trim();
	    }
	    // 3. 선택하는 검색 조건
	    else if (type === 'select') {
	    	requestData['searchValue'] = $('#searchSelect').val();
	    }
		
		requestData['searchKey'] = $('#searchConditions').val();
		requestData['currentPage'] = '1';
		fnListAjax(requestData);
		return requestData;
	 }
	
	
	/** 
	 * @function fnPageDataLoad
	 * @description 페이징 처리 데이터 목록
	 * @param requestData
	 * @return requestData
	*/
	function fnPageDataLoad(requestData, target) {
		// 클릭된 요소가 페이지 핸들러인지 확인
		if ($(target).hasClass('pageHandler')) {
			// 새로운 페이지 번호 설정
			requestData['currentPage'] = $(target).attr('href');
		    fnListAjax(requestData);
		}
		
		return requestData; 
	}
	
	
	
	/** 
	 * @function fnReRender
	 * @description 동적 데이터 그리기
	 * @param responseData 
	 * @param page 
	*/
	function fnReRender(responseData, page) {
		/* 초기 제거 세팅 */
		// 기존 로우를 제거한다. (헤더는 유지)
		$('.osl-evt__table__row').remove();
	
	    // 기존 페이지 핸들러 내부 비우기
	    $('#pageHandlerBox').empty();
	 
	    /* 새로 그리기*/
	    var pagenationInfo = responseData.pagination;
	    
	    if (pagenationInfo !== null) {
	    	// paging html 그리는 변수
		    var pageHtml ='';
		    
			// 페이지 핸들러 생성 및 추가
			if (pagenationInfo.existPrevPage) {
			    pageHtml += '<a href="' + (pagenationInfo.startPage - 1) + '" class="pageHandler"> < </a> ';
			}
			
			for (var num = pagenationInfo.startPage; num <= pagenationInfo.endPage; num++) {
			    pageHtml += '<a href="' + num + '" class="pageHandler ' + (num == page ? 'current-page' : '') + '"> ' + num + ' </a> ';
			}
			
			if (pagenationInfo.existNextPage) {
			    pageHtml += '<a href="' + (pagenationInfo.endPage + 1) + '" class="pageHandler"> > </a> ';
			}
			
			// 페이지 핸들러를 .pageHandlerBox에 추가
			$('#pageHandlerBox').html(pageHtml);
	    }
	    
	    
	    // 사용자 데이터를 추가한다.
	    var lockersList = responseData.lockerHistorys;
	    
	    if (lockersList === null) {
	    	var emptyData = $('<div></div>');
	        emptyData.addClass('empty-data');
	
	        emptyData.text('존재하는 정보가 없습니다.');
	    	
	        $('#pageHandlerBox').append(emptyData);
	    	return;
	    }
	    
	    lockersList.forEach(function(locker, index) {
	        // jQuery <div> 요소 생성
	        var tableRow = $('<div></div>');
	        // jQuery 클래스 추가
	        tableRow.addClass('osl-evt__table__row table-row');
	
	        // jQuery inner Html
	        tableRow.html(
	            '<div class="table-cell">' + (index + 1) + '</div>'
	            + '<div class="table-cell">' + locker.lockerId + '</div>'
	            + '<div class="table-cell">' + locker.userId + '</div>'
	            + '<div class="table-cell">' + locker.userName + '</div>'
	            + '<div class="table-cell">' + locker.lockerExpireDate + '</div>'
	            + '<div class="table-cell">' + getLockerHistoryCode(locker.lockerCode) + '</div>'
	            + '<div class="table-cell">' + locker.registerDate + '</div>'
	        );
	
	        $('#tableContainer').append(tableRow);
	    });

	}
	
	
	
	function getLockerHistoryCode(lockerCode) {
	    switch (lockerCode) {
	        case '01': 
	            return '사물함 배정';
	        case '02': 
	            return '사물함 변경';
	        case '03': 
	            return '사물함 배정해제';
	        case '04': 
	            return '사물함 탈취로 인한 해제';
	        case '05': 
	            return '사물함 탈취로 인한 변경';
	    }
	}
	

</script>



</body>
</html>