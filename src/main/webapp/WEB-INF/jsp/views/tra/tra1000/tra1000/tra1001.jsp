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
						<label for="name" class="save-form__item-label">
							 이름 <span class="star">*</span> 
						</label>
						
						<input type="text" id="name" name="name" class="save-form__item-input"  maxlength="50" placeholder="이름을 입력해 주세요." value="${trainerInfo.trainerName}"/>
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
						<label for="trainerId" class="save-form__item-label">
							아이디 <span class="star">*</span>
						</label>
						
						<input type="text" id="trainerId" name="trainerId" class="save-form__item-input" maxlength="15" placeholder="5~15자리의 영문 혹은 영문과 숫자 조합" value="${trainerInfo.trainerId}"/>
						
						<c:if test="${empty trainerInfo}">
						    <button type="button" id="trainerIdCheck" class="popup-side-btn">중복확인</button>
						</c:if>
						
					</div>
					
					
					<c:if test="${not empty trainerInfo}">
						<div class="save-form__item">
							<label  for="currentPw" class="save-form__item-label"> 기존 비밀번호  </label>
							<input  type="password" id="currentPw" name="currentPw" class="save-form__item-input" maxlength="15"  placeholder="비밀번호 변경 전, 기존 비밀번호를 확인해주세요." />
							
							<button type="button"  id="trainerPwCheck" class="popup-side-btn">비밀번호 확인</button>
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
						
						<input type="text" id="phone" name="phone" class="save-form__item-input" maxlength="14" placeholder="010-1234-1234" value="${trainerInfo.trainerTel}" />
					
					</div>
	
					<div class="save-form__item">
						<label for="address" class="save-form__item-label">주소</label>
						<input type="text" id="address" name="address" class="save-form__item-input" maxlength="300" placeholder="주소를 입력해주세요." value="${trainerInfo.trainerAddress}" />
					
					</div>

					<div class="save-form__item">
						<label for="trainerJoin" class="save-form__item-label" >
							입사 일자 <span class="star"> * </span>
						</label>
						
						<input type="date" id="trainerJoin" name="trainerJoin" class="save-form__item-input" value="${trainerInfo.trainerJoinDT}"/>
						
					</div>
					
					<div class="save-form__item">
						<label for="trainerWorkStartTime" class="save-form__item-label"> 
							근무 시작 시간 <span class="star"> * </span> 
						</label>
						
						<input type="time" id="trainerWorkStartTime" name="trainerWorkStartTime" class="save-form__item-input" value="${trainerInfo.trainerWorkStartTime}"/>
						
						<c:if test="${not empty trainerInfo}">
						    <button type="button" id="changeworkStartTimeCheck" class="popup-side-btn"> 근무시간 변경 확인 </button>
						</c:if>
						
					</div>
					
					<div class="save-form__item">
						<label for="trainerWorkTime" class="save-form__item-label" >
							근무 시간 <span class="star"> * </span> 
						</label>
						
						<select id="trainerWorkTime" class="save-form__item-select"></select>
						
					</div>
					
					<div class="save-form__item">
						<label for="trainerWorkEndTime" class="save-form__item-label" > 근무 종료 시간 </label>
						<input  type="time" id="trainerWorkEndTime" name="trainerWorkEndTime" class="save-form__item-input" readonly/>
					</div>
				
					<div class="save-form__item">
						<label for="remark" class="save-form__item-label"> 비고 </label>
						<textarea id="remark" name="remark" class="save-form__item-input" maxlength="1000" placeholder="최대 1,000자까지 작성 가능합니다." ><c:out value="${trainerInfo.trainerNote}"/></textarea>
					
					</div>
							
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
	<script type="text/javaScript" src="/js/com/com.js"></script>
	<script type="text/javaScript" src="/js/com/validator.js"></script>
	
	<!-- tra1000 동작은 여기서 수행 -->
	<script language="JavaScript">
		$(document).ready(function() {
			/*고정되야하는 값 저장 : 제출 타입, 초기값 */
		    var initData = {
				checkedWorkTime: true,
		        submitType: '',
		        trainerId: ''
		    };
			
			$('#cancel').on('click', function() {
				window.close();
			});	
	
			// 초기화 함수 수행
			fnInit(initData);
			
			// 이벤트 등록
			
			// 근무시작 시간이 변경될 경우 근무종료시간을 변경하는 이벤트
			$('#trainerWorkStartTime').on('change', function() {
				fnSetWorkEndTime();
				initData['checkedWorkTime'] = false;
			});
	
			// 근무시작 시간이 변경될 경우 근무종료시간을 변경하는 이벤트
			$('#trainerWorkTime').on('change', function() {
				fnSetWorkEndTime();
				initData['checkedWorkTime'] = false;
			});
			
			$('#changeworkStartTimeCheck').on('click', function(){
				initData['checkedWorkTime'] = fnCheckedWorkTime();
			})
			
				
			// 중복 아이디 검사
			var checkedId ='';
			 
			$('#trainerIdCheck').on('click', function() {
				checkedId = gfnIdCheck('/cmm/cmm1000/cmm1000/selectCmm1000checkMemberId.do', $('#trainerId').val());
			});
		
			
			// 기존 비밀번호 검사 통과 시, 새로변경할 비밀번호 input 표출 및 비밀번호 확인 버튼을 취소로 변경
			var checkedPw = false;
			
			// 수정 화면에서 비밀번호를 변경하려는 버튼을 클릭할 경우 발생하는 이벤트
			$('#trainerPwCheck').on('click', function() {
				checkedPw = fnEditPwForm(checkedPw, initData);
			})
	
	
			
			// 트레이너 등록 & 수정 검증 및 Ajax 요청
			$('#save').on('click', function (event) {
				event.preventDefault();
				// 나중에 json으로 보낼 객체
				var trainerFormDatas = new Map();
				trainerFormDatas.set('saveType', initData.submitType);
	
	
				// 이름
				if (gfnValidateName($('#name').val())) {
					trainerFormDatas.set('trainerName', $('#name').val());
					  
				} else {
					alert('올바르지 않는 이름입니다. 다시 작성해주세요');
					return;
					  
				}
				  
				  
				// 성별
				if ($('input[name="gender"]:checked').val() === undefined) {
					alert('성별을 선택해주세요');
					return;
			  
				} else {
					trainerFormDatas.set('gender', $('input[name="gender"]:checked').val());
		
				}
				  
					
				// 등록 id
				if (initData.submitType === 'insert') {
					// 아이디 중복검사여부 확인 - 아이디 중복검사 시, 아이디 검증도 동시 수행
					if (checkedId === '' || checkedId !== $('#trainerId').val()) {
						alert('id 중복 확인을 해주세요');
						return;
		
					} else {
						trainerFormDatas.set('trainerId', $('#trainerId').val());
							
					}
				}
				
				// 수정 id
				else {
					trainerFormDatas.set('trainerId', initData.trainerId);
					
				}
				  
				  
				// 가입 시, 패스워드 작성 및 패스워드 확인  || 수정 시, 패스워드 작성 및 패스워드 확인 
				if (initData.submitType === 'insert' || (initData.submitType === 'update' && checkedPw) ) {
					// 검증
					var isCollectPassword = gfnPwDoubleCheck($('#password').val(), $('#passwordCheck').val());
					
					// 검증 통과 후 값 저장
					if (isCollectPassword) {
						trainerFormDatas.set('trainerPw', $('#password').val());
					
					} else {
						return;
						
					}
				}
				
					
				// 전화번호
				if (gfnValidatePhone($('#phone').val())) {
					trainerFormDatas.set('trainerPhone', $('#phone').val());
		
				} else {
					alert('올바르지 않는 전화번호입니다. 다시 작성해주세요');
					return;
					
				}
				  
				  
				// 주소
				trainerFormDatas.set("trainerAddress", $('#address').val().trim());
				
				  
				// 입사 일자
				if ($('#trainerJoin').val() === '') {
					alert('입사일을 선택해주세요');
					return;
		
				} else {
					trainerFormDatas.set('trainerJoin', $('#trainerJoin').val());
					
				}
	
	 		  
				// 근무시간 수정 - true가 되는 요소는 근무 시간 및 근무 시작 시간 건들이면 무조건 false로 변환
				if (initData.submitType === 'update') {
					if (initData.checkedWorkTime) {
						// 근무 시작 시간
						trainerFormDatas.set("trainerWorkStartTime", $('#trainerWorkStartTime').val());
						// 근무 시간
						trainerFormDatas.set("trainerWorkTime", $('#trainerWorkTime').val());
						
					} else {
						alert('근무시간 변경 확인 후 트레이너 수정을 수행해주세요');
						return;
						
					}
				} else {
					// 근무 시작 시간
					trainerFormDatas.set("trainerWorkStartTime", $('#trainerWorkStartTime').val());
					// 근무 시간
					trainerFormDatas.set("trainerWorkTime", $('#trainerWorkTime').val());
					
				}
			 
				// 비고
				trainerFormDatas.set("remark", $('#remark').val().trim());
				  
				// json으로 데이터 보내기 -> 실패 메시지 혹은 성공 메시지 및 창 닫기
	
				// 데이터를 JSON 형태로 변환
				var trainerInfo = Object.fromEntries(trainerFormDatas);
				
				var returnValue = gfnAjaxPost('/tra/tra1000/tra1000/saveTra1000.do', trainerInfo);
			    
			    // ajax 통신 결과로 수행하는 함수
			    if (returnValue.responseStatus === 1) {
			    	alert(returnValue.successMsg);
					
			    	// 등록 성공 시, 부모창 reload()
					window.opener.location.reload();
					
				    // 최종 화면 닫기
				    window.close();
			    } 
				
			});
			// .ready() 끝
		}); 
		
		
		/**
		 * @function        : fnInit()
		 * function 설명          : DOM이 그려진 후 수정 혹은 등록 화면에 맞춰서 text 초기화
		 * @param  initData : 초기화 객체 참조 넣음 
		 * @return 
		*/
		function fnInit(initData) {
			/* 수정 화면의 경우 초기 값 셋팅 */
			if ('<c:out value = "${trainerInfo}"/>' !== '' ) {
				// init 값
				initData.submitType = 'update';
				initData.trainerId = '<c:out value = "${trainerInfo.trainerId}"/>';
				
				// tab 명칭
				$('title').text('트레이너 수정페이지');
				// 제목
				$('#mainTitle').text('트레이너 수정');
				// 제출 text
				$('#save').text('수정');
				
				
				// 성별 radio input
				if ('<c:out value = "${trainerInfo.trainerGender}"/>' === '1') {
					$('#boy').attr('checked', true);
					
				} else {
					$('#girl').attr('checked', true);
					
				}
				
				// 아이디
				$('#trainerId').attr('readOnly', true); 
				
				// 비밀번호 인풋 요소 숨기기
				$('#noneOptionPw').css('display', 'none');
				
				// 입사일 지정
				$('#trainerJoin').attr('min', gfnFormatDate());
				
				// 등록일이 금일 이전이면 readonly
				if ('<c:out value = "${trainerInfo.trainerJoinDT}"/>' < gfnFormatDate() ) {
					$('#trainerJoin').prop('readonly', true);
				}
				
			}
			
			// 등록 화면의 경우 초기 값 셋팅
			else {
				// init 값
				initData.submitType = 'insert';
				
				$('title').text('트레이너 가입페이지');
				$('#mainTitle').text('트레이너 가입');
				$('#save').text('가입');
				
				// 입사일 최소값 지정 
				$('#trainerJoin').attr('min', gfnFormatDate());
				$('#trainerJoin').val(gfnFormatDate());
				// 근무 시작 시간(default값 : 09시)
				$('#trainerWorkStartTime').val('09:00');
				
			}
			
			// 공통 초기 셋팅
			// 근무 시간
			fnSetWorkTime(1 , 8);
			// 근무 종료 시간
			fnSetWorkEndTime();
		}
		
		
		
		/**
		 * function : fnSetWorkTime 
		 * function 설명 : 근무시간 select box의 option 그려주는 함수
		 * @param  min, max : 최소 시간, 최대 시간
		 * @return
		*/
		function fnSetWorkTime(min, max) {
			var hourSelect = $('#trainerWorkTime');
		  
			for (var i = min; i <= max; i++) {
			  
				var option = $('<option></option>');
		    
				option.val(i);
				option.text(i < 10 ? '0' + i + ' 시간' : i + ' 시간');
		    	
				// 만약 trainer.workTime과 i가 동일한 경우에만 selected 속성 추가
				if (i + '' === '<c:out value = "${trainerInfo.trainerWorkTime}"/>') {
					option.prop('selected', true);
				}
	
				hourSelect.append(option);
			}
		}	
	
		
		/**
		 * function : fnSetWorkEndTime 
		 * function 설명 : 근무 종료 시간을 동적으로 변경
		 * @return
		*/
		function fnSetWorkEndTime() {
			 // 시간과 분 분리
			 var startTime = $('#trainerWorkStartTime').val().split(':');
			 var time = startTime[0];
			 var minute = startTime[1];
		  
			 // 시간 계산
			 time = (parseInt(time, 10) + parseInt($('#trainerWorkTime').val(), 10)) % 24;
			 time = (time < 10) ? '0' + time : time;
	
			 // 종료시간 설정
			 $('#trainerWorkEndTime').val(time + ':' + minute);
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
				
				$('#trainerPwCheck').text('비밀번호 확인');
				
				$('#noneOptionPw').css('display', 'none');
				$('#password').val('');
				$('#passwordCheck').val('');
				
				alert('비밀번호 변경을 취소합니다.');
				return false;
				
			}
		
			// 기존 비밀번호 검사가 false인 경우 : 비밀번호 검사를 하지 않아 변경할 비밀번호 인풋창들이 표출 되지 않은 경우
			isCollectPw = gfnPwCheck('/cmm/cmm1000/cmm1000/selectCmm1000CheckMemberPw.do', {'memberId' : initData.trainerId , 'memberPw' : $('#currentPw').val()} );
			
			if (isCollectPw) {
				$('#noneOptionPw').css('display', '');
				$('#trainerPwCheck').text('비밀번호 변경 취소');
				return true;
				
			}
		}
		
		/**
		 * function : fnCheckedWorkTime 
		 * function 설명 : 근무 변경 가능 시간 체크
		 * @return boolean
		*/
		function fnCheckedWorkTime() {
			 var returnValue = gfnAjaxPost('/tra/tra1000/tra1000/selectTra1000WorkTime.do', {'workStartTime' : $('#trainerWorkStartTime').val(),
				 																		   	 'workTime' : $('#trainerWorkTime').val(), 
				 																		   	 'trainerId' : '<c:out value = "${trainerInfo.trainerId}"/>'});
			    
			 // ajax 통신 결과로 수행하는 함수
			 if (returnValue.responseStatus === 1) {
				 alert(returnValue.successMsg);
				 return true;
			    
			 }  
			 
			 return false;
		 }
	
	</script>
</html>
