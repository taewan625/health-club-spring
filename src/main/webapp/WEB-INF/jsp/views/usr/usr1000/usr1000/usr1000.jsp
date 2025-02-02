<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/views/com/header.jsp"%>

<h1 class="main-title"> 회원관리 페이지</h1>

<!-- 검색 조건 content 시작 -->
 <div class="search-box">
   	<div class="search-box__menus">
   	
	 	<div id="searchBoxMenusConditions" name="searchBoxMenusConditions" class="search-box__menu--conditions">
	 		<span class="search-title">검색</span>
	 		<select id="searchConditions"  name="searchConditions">
				  <option value="all"        data-type="none">전체보기</option>
				  <option value="userId"     data-type="text">아이디</option>
				  <option value="userName"   data-type="text">회원명</option>
				  <option value="userPhone"  data-type="text">연락처</option>
				  <option value="classNum"   data-type="text">개인수업 횟수</option>
				  <option value="deleteCode" data-type="select">삭제 여부</option>
				  <option value="userStatus" data-type="select">회원 상태</option>
				  <option value="issueCode"  data-type="select">사물함 특이사항</option>
			</select>
			<input type="hidden" id="searchText" name="searchText" class="osl-evt__search-condition search-condition"  maxlength="20"/>
			<select id="searchSelect" name="searchSelect" class="osl-evt__search-condition search-condition" hidden></select>
	 	</div>
   	</div>
 
 	<div class="search-box__btns">
 		<div id="select" name="select" class="search-box__btn">조회</div>
 		<div id="insert" name="insert" class="search-box__btn">등록</div>
 		<div id="update" name="update" class="search-box__btn">수정</div>
 		<div id="delete" name="delete" class="search-box__btn">삭제</div>
 	</div>
 
 </div>

<!-- 회원 목록 content 시작 -->
<div id="tableContainer" class="table-container">
	<div class="table-header">
    	<div class="table-cell">
    		<input type="checkbox" id="allCheckBox" name="allCheckBox"/>
      	</div>
      	<div class="table-cell">번호</div>
      	<div class="table-cell">회원 아이디</div>
      	<div class="table-cell">회원명</div>
      	<div class="table-cell">연락처</div>
      	<div class="table-cell">회원상태</div>
      	<div class="table-cell">개인수업 횟수</div>
      	<div class="table-cell">사물함 특이사항</div>
      	<div class="table-cell">헬스 시작일</div>
    </div>
</div>
	
<!-- 페이지 핸들러 시작 -->
<div id="pageHandlerBox" class="page-handler-box"></div>

<form id="postPopup" name="postPopup">
    <input type="hidden" id="postPopupValue" name="postPopupValue"/>
</form>



<%@ include file="/WEB-INF/jsp/views/com/footer.jsp"%>
	


<script type="text/javaScript" src="/js/checkbox.js"></script>
<script type="text/JavaScript">
	// 초기화 이벤트
	$(document).ready(function() {
		gfnTitle('회원관리 페이지');
		
		// 검색 조건별 인풋, 셀랙트 박스 표출 숨김 이벤트
		$('#searchConditions').on('change', function() {
			gfnSearch($(this).find(':selected').data('type'), fnOptionsHtml($(this).val()));
		});
		
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
		
		// 회원 등록 팝업창
		$('#insert').click(function() {
			gfnPopup('/usr/usr1000/usr1000/selectUsr1001View.do', 'postPopup', $('#postPopup'));
		});
		
		//회원 수정 팝업창
		$('#update').click(function() {
			if (datas.length === 1) {
				$('#postPopupValue').val(datas[0]);
				gfnPopup('/usr/usr1000/usr1000/selectUsr1001View.do', 'postPopup', $('#postPopup'));

				$('#postPopupValue').val('');
				return;
			}
			
			alert('하나의 회원를 선택하세요');
		});
		
		// 회원 상세 팝업창
		$('#tableContainer').on("dblclick", ".osl-evt__table__row", function() {
		    $('#postPopupValue').val($(this).find('#selectCheckbox').val());
		    gfnPopup('/cmm/cmm2000/cmm2000/selectCmm2004View.do','postPopup', $('#postPopup'));
		    
		    $('#postPopupValue').val('');
		});
		
		// 회원 삭제 알랏
		$('#delete').on("click", function() {
			if (datas.length < 1) {
				alert('하나 이상의 회원을 선택하세요');
				return;
			}
			
			if (confirm('정말로 회원을 삭제하시겠습니까? \n*회원 삭제 시, 해당 회원의 사물함이 같이 삭제됩니다.')) {
				var returnValue = gfnAjaxPost('/usr/usr1000/usr1000/deleteUsr1000UserList.do', datas);
			    // ajax 통신 결과로 수행하는 함수
			    if (returnValue.responseStatus === 1) {
			    	alert(returnValue.successMsg);
					
			    	// 등록 성공 시, 부모창 reload()
			    	window.location.reload();
			    } 
				
			} else {
				alert('회원 삭제를 취소하였습니다.');
			}
		})
		
	// ready 끝
	})
	
	
	/**
	 * function : fnOptionsHtml
	 * function 설명 : 옵션 html 문자열 생성
	 * @param value : 특정 select 조건
	 * @return : 옵션 html 문자열 반환
	*/	
	function fnOptionsHtml(value) {
		 if (value === 'deleteCode') {
			 return '<option value="Y">삭제</option> <option value="N">미삭제</option>';
		 }
		
		 if (value === 'userStatus') {
			 return '<option value="01">정상</option> <option value="02">임박</option> <option value="03">만료</option>';
		 }
		
		 if (value === 'issueCode') {
			 return '<option value="Y">이슈 존재</option> <option value="N">이슈 없음</option>';
		 }
	 }
	
	/**
	 * @function fnListAjax
	 * @description 목록에서 수행하는 공통 Ajax 함수
	 * @param  requestData : 요청보낼 데이터
	*/
	function fnListAjax(requestData) {
		 // userList와 페이징정보를 가지고 와서 화면을 동적으로 그리는 ajax
	    var returnValue = gfnAjaxPost('/usr/usr1000/usr1000/selectUsr1000UserList.do', requestData);
	    
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
				searchKey   : 'deleteCode',
				searchValue : 'N',
				currentPage : '1',
		}
		
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
				return requestData;;
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
	    var usersList = responseData.users;
	    
	    if (usersList === null) {
	    	var emptyData = $('<div></div>');
	        emptyData.addClass('empty-data');

	        emptyData.text('존재하는 정보가 없습니다.');
	    	
	        $('#pageHandlerBox').append(emptyData);
	    	return;
	    }
	    
	    usersList.forEach(function(user, index) {
	        // jQuery <div> 요소 생성
	        var tableRow = $('<div></div>');
	        // jQuery 클래스 추가
	        tableRow.addClass('osl-evt__table__row table-row');

	        // jQuery inner Html
	        tableRow.html(
	            '<div class="table-cell"><input type="checkbox" id="selectCheckbox" value="' + user.id + '"/></div>' +
	            '<div class="table-cell">' + (index + (pagenationInfo.recordSize * (page - 1)) + 1) + '</div>' +
	            '<div class="table-cell">' + user.id + '</div>' +
	            '<div class="table-cell">' + user.name + '</div>' +
	            '<div class="table-cell">' + user.phoneNumber + '</div>' +
	            '<div class="table-cell">' + fnGetStatus(user.remainDay) + '</div>' +
	            '<div class="table-cell">' + user.classNumber + '</div>' +
	            '<div class="table-cell">' + fnGetIssue(user.issue) + '</div>' +
	            '<div class="table-cell">' + user.joinDate + '</div>'
	        );

	        // jQuery appendChild
	        $('#tableContainer').append(tableRow);
	    });
		
	    /* 동적으로 다시 체크박스 연결 */
		gfnRebindCheckbox();
	}
	
	/**
	 * @function fnGetStatus 
	 * @description  남은 일수에 따른 회원 상태 변경
	 * @param remainDay
	 * @return String
	*/
	function fnGetStatus(remainDay) {
	    if (5 < remainDay) {
	        return "정상";
	        
	    } else if (0 <=remainDay && remainDay <= 5) {
	        return "임박";
	        
	    } else {
	        return "만료";
	    }
	}
	
	
	/**
	 * @function fnGetIssue 
	 * @description  이슈 문자열 변경
	 * @param issue
	 * @return String
	*/
	function fnGetIssue(issue) {
		 if (issue === '-') {
			return issue; 
		 }
		 
		 return issue.replace(/(\d+),(\d+)/, '$1번에서 $2번으로 변경');
	 }
	
</script>



</body>
</html>
