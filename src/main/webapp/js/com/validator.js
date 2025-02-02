// 알파벳 소문자와 숫자로만 이루어진 패턴
var ALPAHA_NUM_PATTERN = /^[a-z0-9]+$/;

// 숫자로만 이루어진 패턴
var NUM_PATTERN = /^\d+$/;


/**
 * @function gfnValidLength
 * @description 유효범위
 * @param  min   : 최소 정수 범위
 * @param  max   : 최대 정수 범위
 * @param  value : 사용자 입력 문자열 길이
 * @return 해당 조건에 부합 할 경우 true 반환
*/
function gfnValidLength(min, max, value) {
	return (min <= value && value <= max);
	
}


/**
 * @function gfnValidateAllPhone
 * @description 모든 전화번호 검증식
 * @param  phoneNumber
 * @return 해당 조건에 부합 할 경우 true 반환
*/
function gfnValidateAllPhone(phoneNumber) {
	var regexs = [/^010-\d{4}-\d{4}$/, /^0\d{3}-\d{4}-\d{4}$/, /^(0\d{1,2})-\d{3}-\d{4}$/];

	return regexs.some(regex => regex.test(phoneNumber));
}


/**
 * @function gfnValidatePhone
 * @description 휴대전화번호 검증식
 * @param  phoneNumber
 * @return 해당 조건에 부합 할 경우 true 반환
*/
function gfnValidatePhone(phoneNumber) {
	var phoneRegix = /^010-\d{4}-\d{4}$/;

	return phoneRegix.test(phoneNumber);
}


/**
 * @function gfnValidateName
 * @description 올바른 이름인지 작성
 * @param   name
 * @return 해당 조건에 부합 할 경우 true 반환
*/
function gfnValidateName(name) {
	// 한글만을 검증하는 정규 표현식
	var koreanRegex = /^[가-힣]+$/;
	//영어만을 검증하는 정규 표현식
	var englishRegex = /^[a-zA-Z]+$/;
	
	return gfnValidLength(3, 50, name.length) && (koreanRegex.test(name) || englishRegex.test(name));
}


/**
 * @function gfnValidateLogin
 * @description login 시 올바른 조건 정보 입력했는지 확인하는 함수
 * @param  id 
 * @param  pw 
 * @return 해당 조건에 부합 할 경우 true 반환
*/
function gfnValidateLogin(id, pw) {
	return gfnValidateId(id) && gfnValidatePw(pw);
	
}


/**
 * @function gfnValidateId
 * @description 5자 이상 10자 이하, 알파벳 소문자, 숫자만 허용, 단 숫자만 넣는 것은 허용 안함
 * @param  id 
 * @return 해당 id 조건에 부합 할 경우 true 반환
*/
function gfnValidateId(id) {
	return gfnValidLength(5, 15, id.length) && ALPAHA_NUM_PATTERN.test(id) && !NUM_PATTERN.test(id);

}

/**
 * @function gfnValidatePw
 * @description 5자 이상 10자 이하, 알파벳 소문자, 숫자만 허용
 * @param  pw
 * @return 해당 PW 조건에 부합 할 경우 true 반환
*/
function gfnValidatePw(pw) {
	return gfnValidLength(5, 15, pw.length) && ALPAHA_NUM_PATTERN.test(pw);
	
}





/**
 * @function : gfnIdCheck 
 * @description      : 등록, 수정 form에서 Id 중복 체크
 * @param  url  이동할 경로
 * @param data  요청보낼 data
 * @return id
*/
function gfnIdCheck(url, id) {
	// trainerId 유효성 검증
	if (gfnValidateId(id)) {
		var returnValue = gfnAjaxPost(url, id);

		// 검증 결과가 success인 경우 경로 이동 동작 수행
		if (returnValue.responseStatus === 1) {
			alert(returnValue.successMsg);
			return id;
			
		}
		
	} else {
		alert('올바르지 않는 id입니다. 다시 작성해주세요');
		
	}
}



/**
 * @function : gfnIdCheck 
 * @description      : 등록, 수정 form에서 Id 중복 체크
 * @param  url  이동할 경로
 * @param idPw  id, pw 존재하는 객체
 * @return true
*/
function gfnPwCheck(url, idPw) {
	// trainerId 유효성 검증
	if (gfnValidateId(idPw.memberPw)) {
		var returnValue = gfnAjaxPost(url, idPw);

		// 검증 결과가 success인 경우 경로 이동 동작 수행
		if (returnValue.responseStatus === 1) {
			alert(returnValue.successMsg);
			return true;
			
		}
		
	} else {
		alert('올바르지 않는 pw입니다. 다시 작성해주세요');
		
	}
}





/**
 * @function : gfnPwDoubleCheck 
 * @description  비밀번호, 비밀번호 확인 값이 동일한지 확인
 * @param  pw 
 * @param doublePw 
 * @return boolean
*/
function gfnPwDoubleCheck(pw, doublePw) {
	// 비밀번호 검증
	if (!gfnValidatePw(pw)) {
		alert('올바르지 않는 password입니다. 다시 작성해주세요.\n5자 이상 10자 이하, 알파벳 소문자, 숫자만 허용');
		return false;
		  
	}
	
	// 비밀번호와 중복 비밀번호 확인
	if (pw === doublePw) {
		return true;
		  
	} else {
		alert('비밀번호 값과 비밀번호 확인값이 다릅니다. 다시 작성해주세요');
		return false;
	} 
}
