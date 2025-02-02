<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" href="/css/styles.css">
	</head>

<body>
	<h1 id="mainTitle" class="main-title"></h1>
	
		<form class="save-forms">
			<div>
			
				<div class="save-seperate-line"> 
					<span class="star">*</span> 필수입력사항
				</div>
				
				<div>
					<div class="save-form__item">
						<label for="ptJoin" class="save-form__item-label">
							수업일 <span class="star"> * </span>
						</label>
						
						<input type="date" id="ptJoin" name="ptJoin" class="save-form__item-input" value="${ptInfo.ptDate}"/>
					</div>
						
					<div class="save-form__item">
						<label for="ptStartTime" class="save-form__item-label"> 
							수업 시작 시간 <span class="star"> * </span> 
						</label>
						<input type="time" id="ptStartTime" name="ptStartTime" class="save-form__item-input" value="${ptInfo.ptStartTime}"/>
					</div>
					
	
					<div class="save-form__item">
						<label class="save-form__item-label">
							수업 종류 <span class="star">*</span>
						</label>
						
						<input type="radio" id="aClass" name="classType"  value='01'/> 
						<label for="aClass"> 30분 수업 </label>
						
						<input type="radio" id="bClass" name="classType"  value='02'/> 
						<label for="bClass"> 50분 수업 </label> 
						
					</div>
					
					<div class="save-form__item">
						<label for="userId" class="save-form__item-label">
							회원 id <span class="star">*</span>
						</label>
						<input type="text" id="userId" name="userId" class="save-form__item-input"  readonly value="${ptInfo.userId}"/>
						<div class="osl-evt__none-option" style="display: none;">
							<button type="button" id="selectUserId" class="popup-side-btn" > 회원 선택 </button>
						</div>

					
					</div>

					<div class="save-form__item">
						<label for="trainerId" class="save-form__item-label">
							트레이너 id <span class="star">*</span>
						</label>
						<input type="text" id="trainerId" name="trainerId" class="save-form__item-input" readonly value="${ptInfo.trainerId}"/>
						
						<div class="osl-evt__none-option" style="display: none;">
							<button type="button" id="selectTrainerId" class="popup-side-btn" > 트레이너 선택 </button>
						</div>
						
					</div>
					
					<c:if test="${not empty ptInfo}">
 						<div class="save-form__item">
							<label for="ptStatus" class="save-form__item-label">
								수업 상태 
							</label>
							<select id="ptStatus" class="save-form__item-select" disabled="disabled">
							    <option value="00">수업 예정</option>
							    <option value="01">수업 완료</option>
							    <option value="02">회원 노쇼</option>
							    <option value="03">회원 취소</option>
							    <option value="04">트레이너 노쇼</option>
							    <option value="05">트레이너 취소</option>
							</select>
						</div>
					</c:if>
					
				</div>
				
				<div class="save-seperate-line"> </div>
			</div>
	
	
			<div>
				<button type="submit" id="save"   class="popup-btn--save"></button>
				<button type="button" id="cancel" class="popup-btn--save">닫기</button>
			</div>

	    </form>
</body>

<!-- 모든  검증 기능 동작은 여기서 수행 -->
<script type="text/javaScript" src="/js/jquery-3.7.1.min.js"></script>
<script language="JavaScript" src="/js/com/com.js"></script>
<script language="JavaScript" src="/js/com/validator.js"></script>

<!-- tra1000 동작은 여기서 수행 -->
<script language="JavaScript">
	$(document).ready(function () {
		$('#cancel').on('click', function(){
			window.close();
		})
		
		/*초기화*/
		// 등록 혹은 수정으로 이동하기 위한 분기 처리
		var submitType;
		// user 선택 시, 검색 조건
		var userCondition;
		// trainer 선택 시, 검색 조건
		var trainerCondition;
		
		// 수정 시, 수업 정보 업데이트를 위한 pt pk로 이용
		var initPtId = '<c:out value = "${ptInfo.ptId}"/>';
		// 수정 시, 수업 횟수 반환하기 위한 user pk로 이용
		var initUserId = '<c:out value = "${ptInfo.userId}"/>';
		// 수정 시, 수업 횟수 반환하기 위한 trainer pk로 이용
		var initTrainerId = '<c:out value = "${ptInfo.trainerId}"/>';
		// 수정 시, 이전 수업 상태 확인
		var initPtStatus = '<c:out value = "${ptInfo.ptStatus}"/>';
		
		// 수업 등록인 경우 초기화
		if ('<c:out value = "${ptInfo}"/>' === '') {
			$('title').text('수업	 등록팝업');
			$('#mainTitle').text('수업 등록');
			$('#save').text('등록');
			
			$('#ptStartTime').val('09:00');
			// css('display', '') == show() <-> hide()
			$('.osl-evt__none-option').show();
			var urlParams = new URLSearchParams(window.location.search);
			
			if (urlParams.get('date') === null) {
				$('#ptJoin').val(gfnFormatDate(+1));
				
			}
			else {
				$('#ptJoin').val(urlParams.get('date'));
			}
			
			submitType = 'insert';
		}
		
		// 수업 상세 경우 초기화
		else {
			$('title').text('수업	 상세팝업');
			$('#mainTitle').text('수업 상세');
			
			
			// 예정된 수업 상세의 수정 버튼 text : 초기화
			if (gfnFormatDate() < '<c:out value = "${ptInfo.ptDate}"/>') {
				$('#save').text('수정');
				submitType = 'changePt';
				
			} 
			
			// 지난 수업 상세의 수정 버튼 text : 상태 변경
			else {
				
				// 한달전 수업은 수업  예정인거 제외하고는 당연히 수정자체가 불가
				if ('<c:out value = "${ptInfo.ptDate}"/>' <= gfnFormatDate(-30) && '<c:out value = "${ptInfo.ptStatus}"/>' !== '00') {
					$('#save').remove();
				}
				
				// 해당 회원의 수업 횟수가 5회 이하이고 '00'이 아니면 변경 불가
				else if ('<c:out value = "${ptInfo.userPtRemainCount}"/>' < 6 && '<c:out value = "${ptInfo.ptStatus}"/>' !== '00') {
					$('#save').remove();
				}
				
				// 지난 수업 상세의 수정이 가능한 경우
				else {
					$('#save').text('수업 상태변경');
					submitType = 'changeStatus';
				}
			}
			
			
			// 수업일, 수업 시작 시간을 readOnly로 변경
			$('#ptJoin').prop('readonly', true);
			$('#ptStartTime').prop('readonly', true);
			
			// 수업 종류 : checked, disabled 설정
			if ('<c:out value = "${ptInfo.ptCode}"/>' === '01') {
				$('#aClass').attr('checked', true);
				$('#bClass').attr('disabled', true);
				
			} else {
				$('#bClass').attr('checked', true);
				$('#aClass').attr('disabled', true);
				
			}
			
			// 수업 상태
			$('#ptStatus').val('<c:out value = "${ptInfo.ptStatus}"/>').prop('selected', true);
		}
		
		// 공통 수업일 최대, 최소값 지정
		$('#ptJoin').attr('min', gfnFormatDate(+1));
		$('#ptJoin').attr('max', gfnFormatDate(+60));
		
		

		// 해당 조건에 부합하는 회원 정보 반환 팝업창
		$('#selectUserId').on('click', function() {
			userCondition = fnPtValidate();
			
			if (userCondition === undefined) {
				return;
				
			}
			gfnPopup("/cmm/cmm2000/cmm2000/selectCmm2001View.do?subType=pt&" + 'ptDate=' + userCondition.ptDate +'&ptTime=' + userCondition.ptTime + '&classType=' + userCondition.classType , '배정 회원 정보');
		})
		
		
		// 해당 조건에 부합하는 트레이너 정보 반환 팝업창
		$('#selectTrainerId').on('click', function() {
			trainerCondition = fnPtValidate();
			
			if (trainerCondition === undefined) {
				return
			}
			gfnPopup("/cmm/cmm2000/cmm2000/selectCmm2002View.do?subType=pt&" + 'ptDate=' + trainerCondition.ptDate +'&ptTime=' + trainerCondition.ptTime + '&classType=' + trainerCondition.classType , '배정 트레이너 정보');
		})
		
		
		
		
		// 해당 조건에 부합하는 트레이너 정보 반환 팝업창
		$('#save').on('click', function () {
			event.preventDefault();
			
			var userId= initUserId;
			var trainerId = initTrainerId;
			
			// 예정된 수업 수정 UI 변경
			if (submitType === 'changePt') {
				// 제출 타입 변경
				submitType = 'update';
				
				$('title').text('수업	수정 팝업');
				$('#mainTitle').text('수업수정');
				
				if (initPtStatus === '00') {
					// 초기화 값 변경 후 종료
					$('.osl-evt__none-option').show();
					
					// 수업일, 수업 시작 시간 풀기
					$('#ptJoin').prop('readonly', false);
					$('#ptStartTime').prop('readonly', false);
					
					// 수업 종류 풀기
					$('input[name="classType"]').attr('disabled', false);
					
				} 
				
				// 수업 상태 풀기
				$('#ptStatus').attr('disabled', false);
				// 수업예정, 취소빼고 : 지난 수업이기 때문에 수업예정으로 갈 수 없다.
				$('option[value="01"]')[0].remove();
				$('option[value="02"]')[0].remove();
				$('option[value="04"]')[0].remove();
				
				userCondition = fnPtValidate();
				trainerCondition = fnPtValidate();
				return;
				
			} 
			
			// 완료된 수업 수정 UI 변경
			else if (submitType === 'changeStatus') {
				submitType = 'updateStatus';
				$('title').text('수업	상태변경 팝업');
				$('#mainTitle').text('수업상태변경');
				
				// 수업 상태 풀기
				$('#ptStatus').attr('disabled', false);
				// 수업예정 삭제 : 지난 수업이기 때문에 수업예정으로 갈 수 없다.
				$('option[value="00"]')[0].remove();
				
				return;
			}
			
			var requestData;
			
			// 등록 로직 처리
			if (submitType === 'insert') {
				if (fnValidateCondition(userCondition, trainerCondition, $('#userId').val(), $('#trainerId').val())) {
					requestData = {
							saveType  : submitType,
							initPtId  : initPtId,
							userId    : $('#userId').val(),
							trainerId : $('#trainerId').val(),
							ptDate    : trainerCondition.ptDate,
							ptTime : trainerCondition.ptTime,
							classType : trainerCondition.classType,
							userPtAdd : '-1',
					}
				}
			} 
			
			// 수정 로직 처리
			else if (submitType === 'update') {
				// 수업 상태 변경 가능 여부 검사: 변경사항 존재 유무
				if (initPtStatus !== '00' && initPtStatus === $('option:checked').val()) {
					alert('수업 상태 변경사항이 없습니다. 변경 후 수정해주세요.');
					return;
					
				}
				
				// 수업 상태 변경 가능 여부 검사: '00' -> '03, 05' 상관 X, 반대의 경우 : 기존의 수업 횟수가 1이상 경우만 가능
				if (initPtStatus !== '00' && $('option:checked').val() === '00' && '<c:out value = "${ptInfo.userPtRemainCount}"/>' < 1) {
					alert('현재 남아있는 수업이 없어 수업 예정으로 상태 변경이 불가합니다.');
					return;
					
				}
				
				
				// 내용 검증
				var checked = fnValidateCondition(userCondition, trainerCondition, $('#userId').val(), $('#trainerId').val())
				
				// 검증 통과시, requestData 생성
				if (checked === false) {
					return;
					
				} else {
					requestData = {
							saveType  : submitType,
							initPtId  : initPtId,
							initUserId  : initUserId,
							initTrainerId  : initTrainerId,
							initPtStatus  : initPtStatus,
							userId    : $('#userId').val(),
							trainerId : $('#trainerId').val(),
							ptDate    : trainerCondition.ptDate,
							ptTime : trainerCondition.ptTime,
							classType : trainerCondition.classType,
							ptStatus  : $('option:checked').val(),
					}
				}
				
				// 수업 상태 변경 로직 : 취소 -> 예정
				if (initPtStatus !== '00' && initPtStatus !== $('option:checked').val()) {
					requestData['userPtAdd'] = '-1';
					fnSavePt(requestData);
					return;
				}
				
				// 수업 내용 수정 시
				if ('<c:out value = "${ptInfo.ptDate}"/>' === userCondition.ptDate
					&& '<c:out value = "${ptInfo.ptStartTime}"/>' === userCondition.ptTime
					&& '<c:out value = "${ptInfo.ptCode}"/>' === userCondition.classType
					&& initUserId === $('#userId').val()
					&& initTrainerId === $('#trainerId').val()
					)
				{
					
					if ($('option:checked').val() !== '00') {
						requestData['userPtAdd'] = '1';
					
					} else {
						alert('수업 수정 변경사항이 없습니다. 변경 후 수정해주세요.');
						return;
					}
				}
				
				// 수업 내용 수정 시
				else {
					requestData['ptStatus'] = '00';
				}

					
			} 
			
			// 상태 변경 로직 처리
			else if (submitType === 'updateStatus') {
				if ($('option:checked').val() === initPtStatus) {
					alert('변경 사항이 없습니다.');
					return;
				}
				
				requestData = { 
						saveType  : submitType,
						initPtId  : initPtId,
						userId    : initUserId,
						trainerId : initTrainerId,
						ptStatus : $('option:checked').val(),
				}
				
				// init 00 : 원래 표대로 수행
				if (initPtStatus === '00') {
					var ptCounts = fnCalculatePtCount($('option:checked').val(), 1);
					requestData['userPtAdd'] = ptCounts[0];
					requestData['trainerPtAdd'] = ptCounts[1];
				}
				
				// init 00 아닐 경우 : 기존 계산 정보 뺀 후 수행할 정보 수행
				else {
					var rollbackPtCounts = fnCalculatePtCount(initPtStatus);
					var ptCounts = fnCalculatePtCount($('option:checked').val());
					
					requestData['userPtAdd'] = ptCounts[0] - rollbackPtCounts[0];
					requestData['trainerPtAdd'] = ptCounts[1] - rollbackPtCounts[1];
					
				}
				
			}
			 
			// 저장 처리
			fnSavePt(requestData);
		})
		
		
	// .ready 끝
	});



	/**
	 * @function    fnPtValidate
	 * @description  회원, 트레이너 선택할 때 미리 존재해야하는 값 존재 유무 검증
	 * @return   object  : 조건 값이 존재하는 객체 반환
	*/
	function fnPtValidate() {
		// 수업일 유효성 검사
		if ($('#ptJoin').val() === '') {
			alert('수업일을 선택해주세요');
			return;
		}
	
		// 수업 종류 선택
		if ($('input[name="classType"]:checked').val() === undefined) {
			alert('수업 종류를 선택해주세요');
			return;
		}
	
		// 수업 시작 시간 선택
		if ($('#ptStartTime').val() === '') {
			alert('수업시간을 선택해주세요');
			return;
		}
		
		return {
			ptDate    : $('#ptJoin').val(),
			ptTime    : $('#ptStartTime').val(),
			classType : $('input[name="classType"]:checked').val(),
		}
	}


	 
	 
	 
	/**
	 * @function    fnValidateCondition
	 * @description  회원, 트레이너 선택 조건이 동일한 조건인지 검증
	 * @param  userCondition : user 선택 조건
	 * @param  trainerCondition : trainer 선택 조건
	 * @param  userId 
	 * @param  trainerId
	 * @return   object  : 조건 값이 존재하는 객체 반환
	*/
	function fnValidateCondition(userCondition, trainerCondition, userId, trainerId) {
		// 회원, 트레이너 선택 조차 안하는 경우
		if (userId === '' || trainerId === '') {
			alert('회원 혹은 트레이너를 선택해주세요.')
			return false;
		}
		
		// 선택 된 회원,트레이너의 조건이 다른 경우
		if ( userCondition.ptDate === $('#ptJoin').val()
			 && userCondition.ptTime === $('#ptStartTime').val()
			 && userCondition.classType === $('input[name="classType"]:checked').val()
			 &&	userCondition.ptDate === trainerCondition.ptDate
			 && userCondition.ptTime === trainerCondition.ptTime
			 && userCondition.classType === trainerCondition.classType
			)
		{
			return true;
		}
		
		else {
			alert('수업 조건이 동일하지 않습니다. 동일한 수업 조건으로 회원, 트레이너를 선택해주세요.\n*회원, 트레이너 선택 후 수업 조건이 변경이 되면 안됩니다.')
			return false;
			
		}
	}
	
	 
	 
	 
 /**
  * @function fnSavePt
  * @description 수업 등록 버튼 클릭 시, 유효성 검사 이후 요청 처리 수행하는 함수
  * @param  requestData 
 */
 function fnSavePt(requestData) {
	 var returnValue = gfnAjaxPost('/pcs/pcs1000/pcs1000/savePcs1000.do', requestData);
    
	// ajax 통신 결과로 수행하는 함수
    if (returnValue.responseStatus === 1) {
		alert(returnValue.successMsg);
		
		// 등록 성공 시, 부모창 reload()
		window.opener.location.reload();
		
	    // 최종 화면 닫기
	    window.close();
    } 
};

/**
 * @function    fnPtStatus
 * @description 수업 상태 코드를 문자열로 변환
 * @param  ptStatus 
 * @return  string
*/
function fnPtStatus(ptStatus) {
	 switch (ptStatus) {
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


/* 조건  예정이 아닌 경우 : 예정으로 롤백 -> 다시 원래 규칙대로
* 예정(00) -> 완료(01)        회(.)  | 트(1)
* 예정(00) -> 회노(02)        회(.)  | 트(.)
* 예정(00) -> 회취(03)        회(1)  | 트(.)
* 예정(00) -> 트노(04)        회(1)  | 트(.)			
* 예정(00) -> 트취(05)        회(1)  | 트(.)
*/

/**
 * @function    fnCalculatePtCount
 * @description 상태별 카운트 해야하는 수 반환
 * @param  ptStatus 
 * @return      array[userPtCnt, trainerPtCnt]
*/
function fnCalculatePtCount(ptStatus) {
	 switch (ptStatus) {
     case '01':
         return [0, 1];
     case '02':
         return [0, 0];
     case '03':
         return [1, 0];
     case '04':
         return [1, 0];
     case '05':
         return [1, 0];
 }
}
</script>

</html>
