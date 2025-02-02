<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>로그인</title>
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	    <link type="text/css" rel="stylesheet" href="<c:url value='/css/styles.css'/>"/>
	</head>

	<body>
	    <!-- 타이틀 영역 -->
	    <div class="main-title">
	        <i class="material-icons">fitness_center</i>
	        <span>오픈 헬스장</span>
	    </div>
	
	    <!-- 로그인창 영역 -->
	    <form class="cmm1000-form">
	        <input type="text" 	   id="loginId"  name="id"   placeholder="아이디를 작성해주세요."    maxlength="15" />
	        <input type="password" id="loginPw"  name="pw"   placeholder="비밀번호를 작성해주세요."  maxlength="15"  />
	        <input type="submit"   id="loginBtn" name="btn"  class="cmm1000-login"             value="로그인" />
	    </form>
	    
	</body>


	<script type="text/javaScript" src="/js/jquery-3.7.1.min.js"></script>
	<script type="text/javaScript" src="/js/com/validator.js"></script>
	<script type="text/javaScript" src="/js/com/com.js"></script>
	
	<script type="text/javaScript">
		
		$(document).ready(function(){
			// login 이벤트
			$('#loginBtn').on('click', fnLogin);
		})
		
		/** 
		 * @function fnLogin
		 * @description 로그인 입력 정보 확인 후 응답
		 * @param  
		 * @return 
		*/
		function fnLogin() {
			// submit 기본 동작 방지
			event.preventDefault();
			
			// 입력폼의 id, pw 값
			var loginId = $("#loginId").val();
	       	var loginPw = $("#loginPw").val();

	       	// 검증 성공 시, 공통 ajax 함수 수행
			if (gfnValidateLogin(loginId, loginPw)) {
				var returnValue = gfnAjaxPost('/cmm/cmm1000/cmm1000/selectCmm1000loginInfo.do', {loginId: loginId, loginPw: loginPw});
				
				// 검증 결과가 success인 경우 경로 이동 동작 수행
				if (returnValue.responseStatus === 1) {
					location.replace(returnValue.url);
					
				}
				
				return;
			}
	       	
	       	// 검증 실패 시, 검증 실패 알랏 표출
			alert("올바른 id 혹은 비밀번호를 입력하세요.");	
		}

		
	</script>

</html>