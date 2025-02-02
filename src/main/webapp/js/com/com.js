/**
 * @function : gfnAjaxPost 
 * @description : 요청 응답을 모두 json으로 수행하는 함수, 비동기는 억제
 * @param  url : 이동할 경로
 * @param  data : 요청보낼 data
 * @return returnValue : 응답 json 데이터 반환
*/
function gfnAjaxPost(url, data) {
	var returnValue;
	
	$.ajax({
		// 전송 타입
		type : "post",
		// 전송할 url 경로
		url : url,
		// 보낼 데이터 (Object, String, Array) 
		data : JSON.stringify(data),

		// 전송 타입
		contentType: "application/json",
		// 보낼 데이터 타입
		dataType : 'json',
		 
		// 동기로 Ajax 요청 수행
		async: false,
		 
		// 응답 반환 성공 시, 수행되는 콜백 함수
		success : function(result) {
			
			if (result.responseStatus === -1) {
				alert(result.failMsg);
			}
			
			returnValue = result;
		},
		 
		// 응답 반환 실패 시, 수행되는 콜백 함수
		error : function(error) {
			alert('작업 중 에러가 발생했습니다. 에러가 반복 될 경우 고객센터에 문의 바랍니다.\npenHealth123@naver.com');
		}
	})

	return returnValue;
}


/**
 * @function    : gfnDefenceXSS 
 * @description : XSS 방지를 위한 문자열 치환
 * @param  data : XSS 위험 문자열 변환
 * @return convertData : 변환된 문자열 반환
*/
function gfnDefenceXSS(data) {
	var convertData = String(data);
	convertData = String(data).replaceAll("<", "&lt;");
	convertData = convertData.replaceAll(">", "&gt;");
	convertData = convertData.replaceAll("\\(", "&#40;")
	convertData = convertData.replaceAll("\\)", "&#41;");
	convertData = convertData.replaceAll("'", "&#x27;");
	
	return convertData;
}


/**
 * @function  gfnPopup
 * @description 공통 팝업
 * @param  url
 * @param  popupName
 * @param  form = ''
*/
function gfnPopup(url, popupName, form = '') {
	var popupOption = 'toolbar=no,scrollbars=no,resizable=yes,status=no,width=1000,menubar=no,height=800,location=no';
	
	// 일번적인 팝업 열기
	if (form === '') {
		window.open(url, popupName, popupOption);
		
	}
	// post 형식으로 팝업 열기
	else {
		window.open('', popupName, popupOption);
		
		form.attr('action', url);
		form.attr('method', 'post');
		form.attr('target', popupName);
		form.submit();
		
		form.val();
	}
}


/**
 * @function  gfnQueryString
 * @description 요청 객체값 문자열로 반환
 * @param  object
 * @return queryString형태 문자열
*/
function gfnQueryString(object) {
    return Object.keys(object).map(key => key + '=' + object[key]).join('&');
}


/**
 * @function    fnPtStatus
 * @description 수업 상태 코드를 문자열로 변환
 * @param 		 ptCode
 * @return      해당 조건에 부합하는 text 반환
*/
function gfnPtStatus(ptCode) {
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
   }
}


/**
 * @function    gfnPtStatusColor
 * @description 수업 상태 코드를 문자열로 변환
 * @param 		 ptCode
 * @return      해당 조건에 부합하는 text 반환
*/
function gfnPtStatusColor(ptCode) {
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
   }
}


/* 개별 jsp에서 조합해서 사용하는 공통 함수 */
/**
 * @function : gfnSearch 
 * @description 검색 조건 별 hidden 된 요소를 동적으로 표출하는 함수
 * @param  type  검색 조건 타입(input인지, select인지)
 * @param optionsHtml  옵션 html 리터럴 
*/
function gfnSearch(type, optionsHtml) {
	// 변화된 요소가 전체보기 인 경우
	if (type === 'none') {
		$('#searchText').empty();
		$('#searchText').prop('type', 'hidden');
		
		$('#searchSelect').attr('hidden', true);
		
	}
	
	// 변화가 된 요소가 input 인 경우
	else if (type === 'text') {
		$('#searchText').prop('type', 'text');
		
		$('#searchSelect').empty();
		$('#searchSelect').attr('hidden', true);
		
	}
	
	// 변화가 된 요소가 select 인 경우
	else if (type === 'select') {
		$('#searchSelect').empty();
		$('#searchSelect').append(optionsHtml);
		
		$('#searchText').prop('type', 'hidden');
		$('#searchSelect').attr('hidden', false);
		
	}
	
	// 공통으로 input data 제거
	$('#searchText').val('');
};


/**
 * @function gfnFormatDate
 * @description 필요한 날짜를 반환하는 함수
 * @param  daysToAdd = 0
 * @return 'yyyy-mm-dd'
*/
function gfnFormatDate(daysToAdd = 0) {
    var targetDate = new Date();
    targetDate.setDate(targetDate.getDate() + daysToAdd);
    
    return  targetDate.getFullYear() + 	'-' 
    		+ ('0' + (targetDate.getMonth() + 1)).slice(-2) + '-' 
    		+ ('0' + targetDate.getDate()).slice(-2);
}


/**
 * @function gfnTitle
 * @description title명 
 * @param  titleName
 * @return
*/
function gfnTitle(titleName) {
	$('title').text(titleName);
}


// date type의 input의 키보드 입력을 방지
$('input[type="date"]').each(function() {
	$(this).keydown(function(event) {
		event.preventDefault();
	});
});
