<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>선택 가능 회원 목록</title>
<link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<h1 class="main-title"> 선택 가능 회원 목록</h1>

<!-- 검색 조건 content 시작 -->
 <div class="search-box search-box--small">
 
   	<div class="search-box__menus">
	 	<div id="searchBoxMenusConditions" class="search-box__menu--conditions">
	 	
	 		<span class="search-title">검색</span>
	 		
	 		<select id="searchConditions">
  				  <option value="all"      data-type="none">전체보기</option>
				  <option value="userId"   data-type="text">회원 아이디</option>
				  <option value="userName" data-type="text">회원명</option>
			</select>
			
			<input type="hidden" id="searchText" name="searchText" class="osl-evt__search-condition search-condition" maxlength="30"/>
			
	 	</div> 	
 	</div>
 	
 	<div class="search-box__btns">
 		<div id="select" class="search-box__btn">조회</div>
	</div>
	
 </div>


<!-- 회원 목록 content 시작 -->
<div id="tableContainer" name="tableContainer" class="table-container table-container--small">
	 <div class="table-header">
	      <div class="table-cell">선택</div>
	      <div class="table-cell">회원 아이디</div>
	      <div class="table-cell">회원명</div>
	      <div class="table-cell"></div>
     </div>
</div>
	
<!-- 페이지 핸들러 시작 -->
	<div id="pageHandlerBox" name="pageHandlerBox" class="page-handler-box"></div>


<div class="popup-btns">
	<button type="submit" id="save"   class="popup-btn--save"> 선택 </button>
	<button type="button" id="cancel" class="popup-btn--save"> 닫기 </button>
</div>

</body>

<script type="text/javaScript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javaScript" src="/js/com/com.js"></script>
<script type="text/javascript">


	$(document).ready(function () {
		// 초기 조건 변수 저장
		var requestData = fnInitDataLoad(fnListAjax);
		
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
		
		
		if ('pt' === requestData.subType) {
			$('#tableContainer > :first-child > :last-child').text('남은 수업 횟수');
		}  
		else if ('locker' === requestData.subType) {
			$('#tableContainer > :first-child > :last-child').text('회원 만료일자');
		}
		
		// 검색 조건 별 hidden 된 요소를 동적으로 표출
		$('#searchConditions').on('change', function() {
			// 선택된 옵션의 값
			gfnSearch($(this).find(':selected').data('type'));
			
		});
		
		// 선택
		$('#save').on('click', function() {
			fnSelectUser(requestData.subType);
		})
		
		$('#cancel').on('click', function() {
			window.close();
		})
		
		
		var selectedUsers;
		
		// 라디오 버튼 동적 재할당
		$(document).on('click', '.osl-evt__table__row', function() {
		    var tableCell = $(event.target);
		    var isTableCell = tableCell.hasClass('table-cell');
		    
		    if (isTableCell) {
		    	var radiobox = tableCell.parent().find('input[type="radio"][name="userId"]');
		        radiobox.prop('checked', true);

		    }
		    // pt 경우
		    if (requestData.subType === 'pt') {
		        selectedUsers = radiobox.val().split(',')[0];
		    }
		    
		});
		
	// ready 끝
	})


	/**
	 * @function fnInitDataLoad
	 * @description  초기로딩 및 새로 고침을 할 경우 로딩하는 메서드
	 * @return 조건에 사용된 객체 반환
	*/
	function fnInitDataLoad() {
		var queryString = window.location.search;
		var urlParams = new URLSearchParams(queryString);
		
	    var requestData = {};
		
	    urlParams.forEach(function(value, key) {
	    	requestData[key] = value;
	    });
		
	    requestData['currentPage'] = '1',
		
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
	 * @function fnListAjax
	 * @description 목록에서 수행하는 공통 Ajax 함수
	 * @param  requestData : 요청보낼 데이터
	*/
	function fnListAjax(requestData) {
		 // userList와 페이징정보를 가지고 와서 화면을 동적으로 그리는 ajax
	    var returnValue = gfnAjaxPost('/cmm/cmm2000/cmm2000/selectCmm2000UserList.do', requestData);
	    
	    // ajax 통신 결과로 수행하는 함수
	    if (returnValue.responseStatus === 1) {
			// 화면 다시 그리기
	    	fnReRender(returnValue.jsonResponseData, requestData.currentPage);
		}

        // 브라우저 url만 변경
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
		    
		    if (pagenationInfo === null) {
		    	// 페이징 존재 안함 == 사물함 데이터 존재 안함 == 선택 버튼 제거
		    	$('#save').remove();
		    } 
		    
		    else {
		    	// paging html 그리는 변수
		    	var pageHtml ='';
		    
		    	// 페이지 핸들러 생성 및 추가
		    	if (pagenationInfo.existPrevPage) {
		    		pageHtml += '<a href="' + (pagenationInfo.startPage - 1) + '" class="pageHandler"> < </a> ';
			
		    	}
			
		    	for (var num = pagenationInfo.startPage; num <= pagenationInfo.endPage; num++) {
		    		pageHtml += '<a href="' + num + '" class="pageHandler ' + (num == page ? 'current-page' : '') + '"> ' + gfnDefenceXSS(num) + ' </a> ';
			
		    	}
			
		    	if (pagenationInfo.existNextPage) {
		    		pageHtml += '<a href="' + (pagenationInfo.endPage + 1) + '" class="pageHandler"> > </a> ';
			
		    	}
			
		    	// 페이지 핸들러를 .pageHandlerBox에 추가
		    	$('#pageHandlerBox').html(pageHtml);
		
		    }
		    
			// 사용자 데이터를 추가한다.
		    if (responseData.users === null) {
			   	
		    	var emptyData = $('<div></div>');
		    	emptyData.addClass('empty-data');
		    	emptyData.text('존재하는 정보가 없습니다.');
		   	
		    	$('#pageHandlerBox').append(emptyData);
		    	return;
		    }
	    	
			
			responseData.users.forEach(function(user, index) {
		        // jQuery <div> 요소 생성
		        var tableRow = $('<div></div>');
		
		        tableRow.addClass('osl-evt__table__row table-row');

		        tableRow.html(
		            '<div class="table-cell"><input type="radio" name="userId" value="' + user.usrId + '"/></div>'
		            + '<div class="table-cell">' + gfnDefenceXSS(user.usrId)   + '</div>'
		            + '<div class="table-cell">' + gfnDefenceXSS(user.usrNm) + '</div>'
		        );
		        
		        
		        if (undefined === user.classNum) {
		        	tableRow.append('<div class="table-cell">' + gfnDefenceXSS(user.edDt) + '</div>');
		        }
		        
		        else if (undefined === user.edDt) {
		        	tableRow.append('<div class="table-cell">' + gfnDefenceXSS(user.classNum) +  ' 개' + '</div>');
		        	
		        }
		        
		        $('#tableContainer').append(tableRow);
		        
		    });
	 	}
  
  

	/** 
	 * @function fnSelectUser
	 * @description 회원 목록을 클릭하면 정보가 넘어감
	 * @param responseData 
	 * @param page 
	*/
	function fnSelectUser(subType) {
		var checked = false;

		$('input[type="radio"][name="userId"]').each(function() {
			if (this.checked) {
				if (subType === 'locker') {
					window.opener.document.querySelector('#lockerExpire').max = $(this).closest('.osl-evt__table__row').find('div:last').text();
				}
				
				window.opener.document.querySelector('#userId').value = this.value;
				checked = true;
			}
		})
		
		checked ? window.close() : alert('회원을 선택해주세요');
	}

     
</script>

</html>