<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
	<h1 id="mainTitle" class="main-title"></h1>
		<form class="save-forms">
			<!-- user form 내용 -->
			<div>
				<div class="save-seperate-line"> 
					<span class="star">*</span> 필수입력사항
				</div>
				
				<div>
					<div class="save-form__item">
						<label for="name" class="save-form__item-label">
							 이름 <span class="star">*</span> 
						</label>
						<input type="text" id="name" name="name" class="save-form__item-input" maxlength="50" placeholder="이름을 입력해 주세요." value="${userInfo.userName}"/>
					</div>
					
					<div class="save-form__item">
						<label class="save-form__item-label">
							성별 <span class="star">*</span>
						</label>
						
						<input type="radio" id="boy" name="gender" value='1' /> 
						<label for="boy"> 남자 </label>
						
						<input type="radio" id="girl" name="gender" value='2'/> 
						<label for="girl"> 여자 </label> 
					</div>
	
					<div class="save-form__item">
						<label for="userId" class="save-form__item-label">
							아이디 <span class="star">*</span>
						</label>
						<input type="text" id="userId" name="userId" class="save-form__item-input" maxlength="15" placeholder="5~15자리의 영문 혹은 영문과 숫자 조합" value="${userInfo.userId}" />
						<c:if test="${empty userInfo}">
						    <button type="button" id="userIdCheck" class="popup-side-btn">중복확인</button>
						</c:if>
					</div>
	
	
					<c:if test="${not empty userInfo}">
						<div class="save-form__item">
							<label  for="currentPw" class="save-form__item-label"> 기존 비밀번호  </label>
							<input  type="password" id="currentPw" name="currentPw" class="save-form__item-input" maxlength="15"  placeholder="비밀번호 변경 전, 기존 비밀번호를 확인해주세요." />
							<button type="button"  id="userPwCheck" class="popup-side-btn">비밀번호 확인</button>
						</div>
					</c:if>
	
					<div id="noneOptionPw">
						<div class="save-form__item">
							<label for="password" class="save-form__item-label">
								비밀번호 <span class="star">*</span> 
							</label>
							<input type="password" id="password" name="password" class="save-form__item-input" maxlength="15" placeholder="비밀번호를 입력해 주세요. 비밀번호는 5~15자리 작성"/>
						</div>
		
						<div class="save-form__item">
							<label for="passwordCheck" class="save-form__item-label"> 
								비밀번호 확인 <span class="star">*</span> 
							</label>
							<input type="password" id="passwordCheck" name="passwordCheck" class="save-form__item-input" maxlength="15" placeholder="비밀번호를 한번 더 입력해 주세요."/>
						</div>	
					</div>
					
	
					<div class="save-form__item">
						<label for="phone" class="save-form__item-label">
							연락처 <span class="star">*</span>
						</label>
						<input type="text" id="phone" name="phone" class="save-form__item-input" maxlength="14" placeholder="010-1234-1234, 02-123-1234" value="${userInfo.userTel}"/>
					</div>
	
					<div class="save-form__item">
						<label for="address" class="save-form__item-label">주소</label>
						<input type="text" id="address" name="address" class="save-form__item-input" maxlength="300" placeholder="주소를 입력해주세요." value="${userInfo.userAddress}"/>
					</div>
					
					<div class="save-form__item">
						<label for="userJoin" class="save-form__item-label" >
							등록일자 <span class="star"> * </span> 
						</label>
						<input type="date" id="userJoin" name="userJoin" class="save-form__item-input" value="${userInfo.userJoinDT}"/>
					</div>
					
					<div class="save-form__item">
						<label for="userExpire" class="save-form__item-label" >
							만료일자 <span class="star"> * </span> 
						</label>
						<input type="date" id="userExpire" name="userExpire" class="save-form__item-input" value="${userInfo.userEndDT}"/>
					</div>
	
					<div class="save-form__item">
						<label for="classNum" class="save-form__item-label">개인 수업 횟수</label>
						<input type="number" id="classNum" name="classNum" class="save-form__item-input" min="0" max="50" placeholder="최대 50회까지 등록가능" value="${userInfo.userClassNum}"/>
					</div>
					
					<div class="save-form__item">
						<label for="remark" class="save-form__item-label"> 비고 </label>
						<textarea id="remark" name="remark" class="save-form__item-input" maxlength="1000" placeholder="최대 1,000자까지 작성 가능합니다." ><c:out value="${userInfo.userNote}"/></textarea>
					</div>
					<c:if test="${empty userInfo or userInfo.remainDay >= 0}">
						<div class="save-form__item">
							<label for="lockerId" class="save-form__item-label">사물함 번호</label>
							<input  type="text"   id="lockerId" name="lockerId" class="save-form__item-input" placeholder="사물함 선택 버튼을 눌러서 사물함을 선택하세요" readonly value="${userInfo.lockerId}" />
							<button type="button" id="selectLocker" class="popup-side-btn"> 사물함 선택 </button>
						</div>
						
						<div class="save-form__item">
							<label for="lockerExpire" class="save-form__item-label">만료일자</label>
							<input type="date" id="lockerExpire" name="lockerExpire" class="save-form__item-input" value="${userInfo.lockerExpire}"/>
						</div>
					</c:if>
				</div>
			</div>
			
			<!-- usr1001-form__locker 내부에 의미 없는 bottom-section 존재 -->
			<div>
				<button type="submit" id="save"   class="popup-btn--save"></button>
				<button type="button" id="cancel" class="popup-btn--save">닫기</button>
			</div>
	    </form>
</body>



<!-- 모든  검증 기능 동작은 여기서 수행 -->
<script type="text/javaScript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javaScript" src="/js/com/com.js"></script>
<script type="text/javaScript" src="/js/com/validator.js"></script>

<!-- usr1000 동작은 여기서 수행 -->
<script type="text/javaScript">
	$(document).ready(function() {
		/* 등록&수정 기본 셋팅 값*/
		/*고정되야하는 값 저장 : 제출 타입, 초기값 */
	    var initData = {
				submitType: '',
				// 기존 id, 기존 사물함 번호, 기존 사물함 만료일자
				initUserId       : '<c:out value = "${userInfo.userId}"/>',
				initLockerId     : '<c:out value = "${userInfo.lockerId}"/>',
				initLockerExpire : '<c:out value = "${userInfo.lockerExpire}"/>',
	    };
		
		// 초기화 함수 수행
		fnInit(initData);
		
		// 등록일 만료일 관련 이벤트
		
		// 회원 등록, 만료 일자 변경시, min max 동적 변경
		$('#userJoin').on('change', function() {
			$('#userExpire').attr('min', $('#userJoin').val());
			$('#lockerExpire').attr('min', $('#userJoin').val());
		})
	
		$('#userExpire').on('change', function() {
			$('#userJoin').attr('max', $('#userExpire').val());
			$('#lockerExpire').attr('max', $('#userExpire').val());
		})
		
		$('#classNum').on('input', function() {
			 var value = $(this).val();
			    if (value.length > 2) {
			        $(this).val(value.slice(0, 2));
			    }
		})
		
		
		// 중복 검사에 통과된 id 값
		var checkedId ='';
		 
		$('#userIdCheck').on('click', function() {
			checkedId = gfnIdCheck('/cmm/cmm1000/cmm1000/selectCmm1000checkMemberId.do', $('#userId').val());
		});
		
		
		// 기존 비밀번호 검사 통과 시, 새로변경할 비밀번호 input 표출 및 비밀번호 확인 버튼을 취소로 변경
		var checkedPw = false;
		
		// 수정 화면에서 비밀번호를 변경하려는 버튼을 클릭할 경우 발생하는 이벤트
		$('#userPwCheck').on('click', function() {
			checkedPw = fnEditPwForm(checkedPw, initData);
		})
	
		// 선택할 사물함 표출 팝업  selectLocker
		$('#selectLocker').on('click', function() {
			gfnPopup('/cmm/cmm2000/cmm2000/selectCmm2003View.do', 'lockerSelect');
			
		})
		 
		
		// 회원 등록 & 수정 검증 및 Ajax 요청 함수
		$('#save').on('click', function (event) {
			event.preventDefault();
			
			// 나중에 json으로 보낼 객체
			var userFormDatas = new Map();
			
			// 타입
			userFormDatas.set('saveType', initData.submitType);
	
			// 이름
			if (gfnValidateName($('#name').val())) {
				userFormDatas.set('userName', $('#name').val());
			  
			} else {
				alert('올바르지 않는 이름입니다. 다시 작성해주세요');
				return;
			  
			}
		  
			// 성별
			if ($('input[name="gender"]:checked').val() === undefined) {
				alert('성별을 선택해주세요');
				return;
		  
			} else {
				userFormDatas.set('gender', $('input[name="gender"]:checked').val());
	
			}
		  
			// 등록 id
			if (initData.submitType === 'insert') {
				// 아이디 중복검사여부 확인 - 아이디 중복검사 시, 아이디 검증도 동시 수행
				if (checkedId === '' || checkedId !== $('#userId').val()) {
					alert('id 중복 확인을 해주세요');
					return;
	
				} else {
					userFormDatas.set('userId', $('#userId').val());
				}
			}
			
			// 수정 id
			else {
				userFormDatas.set('userId', initData.initUserId);
				
			}
			
			
			// 가입 시, 패스워드 작성 및 패스워드 확인  || 수정 시, 패스워드 작성 및 패스워드 확인 
			if (initData.submitType === 'insert' || (initData.submitType === 'edit' && checkedPw) ) {
				// 검증
				var isCollectPassword = gfnPwDoubleCheck($('#password').val(), $('#passwordCheck').val());
				
				// 검증 통과 후 값 저장
				if (isCollectPassword) {
					userFormDatas.set('userPw', $('#password').val());
				
				} else {
					return;
					
				}
			}
		  
			
			// 전화번호
			if (gfnValidateAllPhone($('#phone').val())) {
				userFormDatas.set('userPhone', $('#phone').val());
	
			} else {
				alert('올바르지 않는 전화번호입니다. 다시 작성해주세요');
				return;
			
			}
		  
	
			// 주소
			userFormDatas.set("userAddress", $('#address').val().trim());
		  
			
			// 회원 등록일자
			if ($('#userJoin').val() === '') {
				alert('등록일을 선택해주세요');
				return;
	
			} else {
				userFormDatas.set('userJoin', $('#userJoin').val());
			
			}
			
			// 회원 만료일자
			if ($('#userExpire').val() === '') {
				alert('만료일을 선택해주세요');
				return;
	
			} else {
				userFormDatas.set('userExpire', $('#userExpire').val());
			
			}
		  
		  
			// 수업횟수classNum
			if (gfnValidLength(0, 50, $('#classNum').val())) {
				userFormDatas.set("userClassNum", $('#classNum').val());
				  
			  } else {
				  alert('수업은 0 ~ 50회 까지만 가능합니다.');
				  return;
				  
			  }
		  
			// 비고
			userFormDatas.set("remark", $('#remark').val().trim());
		  
		  
		
			// 사물함 체크박스가 체크 될 경우만 검증 및 데이터 등록
			var lockerFormDatas = new Map();
			
			// 선택한 사물함 번호
			var lockerId = $('#lockerId').val();
			// 선택한 만료 일자
			var lockerExpire = $('#lockerExpire').val();
			
			// 기존 사물함 정보가 없는 경우
 			if (initData.initLockerId === '' && initData.initLockerExpire === '') {
 				if (lockerId !== '' || lockerExpire !== '') {
 	 				// 사물함 번호 검증
 	 				if (lockerId === '') {
 	 					alert('사물함 번호를 선택해주세요');
 	 				 	return;
 	 				 	
 	 				}

 	 				// 사물함 만료일자 검증
 	 				if (lockerExpire === '' ) {
 	 					alert('사물함 만료일자를 등록해주세요')
 	 					return;
 	 					
 	 				} 
 	 				else if ( new Date(lockerExpire) < new Date($(userJoin).val()) || new Date($(userExpire).val()) <= new Date(lockerExpire)) {
 	 					alert('유효한 사물함 만료일을 선택해주세요.');
 	 				  	return;
 	 				} 

 					// 사물함 정보에 회원 id 넣기
 	 				lockerFormDatas.set('userId', $('#userId').val());
 					
 					// 선택한 사물함 번호 및 만료일 등록
 					lockerFormDatas.set("lockerId", lockerId);
 					lockerFormDatas.set("lockerExpire", lockerExpire);
 				}			
 			} 
			// 기존 사물함 정보가 존재하는 경우
 			else {
				// 기존 사물함 정보와 다른 경우
 				if (initData.initLockerId !== lockerId  || initData.initLockerExpire !== lockerExpire) {
 					if ( new Date(lockerExpire) < new Date($(userJoin).val()) || new Date($(userExpire).val()) <= new Date(lockerExpire)) {
 	 					alert('유효한 사물함 만료일을 선택해주세요.');
 	 				  	return;
 	 				}
 					
	 				lockerFormDatas.set("beforeLockerId", initData.initLockerId);
	 				lockerFormDatas.set("beforeLockerExpire", initData.initLockerExpire);
	 				
 					lockerFormDatas.set("lockerId", lockerId);
 					lockerFormDatas.set("lockerExpire", lockerExpire);
	 				
 					// 사물함 정보에 회원 id 넣기
	 				lockerFormDatas.set('userId', initData.initUserId);
	 				
	 				// 사물함 정보 수정시 회원의 사물함 이슈를 default로 초기화
	 				userFormDatas.set('lockerIssue', '-');
 				}
 			}
			
		
			// json으로 데이터 보내기 -> 실패 메시지 혹은 성공 메시지 및 창 닫기
			
			// 데이터를 JSON 형태로 변환
			var userInfo = Object.fromEntries(userFormDatas);
			var lockerInfo = Object.fromEntries(lockerFormDatas);
			
			var returnValue = gfnAjaxPost('/usr/usr1000/usr1000/saveUsr1000.do', { 'userInfo': userInfo, 'lockerInfo': lockerInfo });
		    
		    // ajax 통신 결과로 수행하는 함수
		    if (returnValue.responseStatus === 1) {
				alert(returnValue.successMsg);
				
				// 등록 성공 시, 부모창 reload()
				window.opener.location.reload();
				
			    // 최종 화면 닫기
			    window.close();
		    } 
		
		});

		
		$('#cancel').on('click', function(){
			window.close();
		})
	// ready 끝
	})


	/**
	 * @function        : fnInit()
	 * function 설명          : DOM이 그려진 후 수정 혹은 등록 화면에 맞춰서 text 초기화
	 * @param  initData : 초기화 객체 참조 넣음 
	 * @return 
	*/
	function fnInit(initData) {
		//수정 화면의 경우 초기 값 셋팅
		if ('<c:out value = "${userInfo}"/>' !== '') {
			// init 값
			initData.submitType = 'update';
			
			
			// tab 명칭
			$('title').text('회원 수정팝업');
			// 제목
			$('#mainTitle').text('회원 수정');
			// 제출 text
			$('#save').text('수정');
			
			// 성별 radio input
			if ('<c:out value = "${userInfo.userGender}"/>' === '1') {
				$('#boy').attr('checked', true);
				
			} else {
				$('#girl').attr('checked', true);
				
			}
			
			// 아이디
			$('#userId').attr('readOnly', true); 
			
			// 비밀번호
			$('#noneOptionPw').css('display', 'none');
			
			// 만료일자
			$('#userExpire').attr('min', gfnFormatDate());
			
			// 수업 횟수
			$('#classNum').attr('placeholder', '기존 수업 횟수 : ' + '<c:out value = "${userInfo.userClassNum}"/>');
			
			// 등록일이 금일 이전이면 readonly
			if ('<c:out value = "${userInfo.userJoinDT}"/>' < gfnFormatDate() ) {
				$('#userJoin').prop('readonly', true);
			}
			
		} 

		// 등록 화면의 경우 초기 값 셋팅
		else {
			// init  값
			initData.submitType = 'insert';
			
			$('title').text('회원 가입팝업');
			$('#mainTitle').text('회원 가입');
			$('#save').text('가입');
			
			// 만료일자
			$('#userExpire').attr('min', gfnFormatDate());
			
			$('#userJoin').val(gfnFormatDate());
			$('#userJoin').attr('min', gfnFormatDate());
			$('#userExpire').val(gfnFormatDate());
		}
	
		// 등록일자
		$('#lockerExpire').attr('min', gfnFormatDate());
	}
	
	/**
	 * function : fnEditPwForm 
	 * function 설명 : 비밀번호 검사를하여 변경할 비밀번호 인풋창들이 표출 된 경우
	 * @param  checkedPw : 비밀번호 확인 성공 유무에 따른 true, false
	 * @param  initData  : 수정 초기 데이터의 id 이용
	 * @return boolean
	*/
	function fnEditPwForm(checkedPw, initData) {
		// 기존 비밀번호 검사가 true인 경우 : 비밀번호 검사를 완료하여 변경할 비밀번호 인풋창들이 표출 된 경우
		if (checkedPw) {
			
			$('#userPwCheck').text('비밀번호 확인');
			
			$('#noneOptionPw').css('display', 'none');
			$('#password').val('');
			$('#passwordCheck').val('');
			
			alert('비밀번호 변경을 취소합니다.');
			return false;
			
		}
	
		// 기존 비밀번호 검사가 false인 경우 : 비밀번호 검사를 하지 않아 변경할 비밀번호 인풋창들이 표출 되지 않은 경우
		isCollectPw = gfnPwCheck('/cmm/cmm1000/cmm1000/selectCmm1000CheckMemberPw.do', {'memberId' : initData.initUserId , 'memberPw' : $('#currentPw').val()} );
		
		if (isCollectPw) {
			$('#noneOptionPw').css('display', '');
			$('#userPwCheck').text('비밀번호 변경 취소');
			return true;
		}
	}
</script>
</html>
