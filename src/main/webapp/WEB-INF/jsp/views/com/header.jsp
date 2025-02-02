<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" href="/css/styles.css">
	</head>
	
	<body>
		
		<c:if test="${'admin' eq  sessionScope.memberInfo['memberLevel']}">
			<div class="com-header__nav">
				<div class="com-header__nav-titles">
					<a class="com-header__nav-title" href="/cmm/cmm1000/cmm1000/selectCmm1000View.do">
						<i class="material-icons">fitness_center</i>
					</a>
			
					<div class="com-header__nav-title">회원 관리 
						<div class="com-header__nav-content">
							<a href="/usr/usr1000/usr1000/selectUsr1000View.do">회원 관리</a>
						</div>
					</div>
			
					<div class="com-header__nav-title">트레이너 관리
						<div class="com-header__nav-content">
							<a href="/tra/tra1000/tra1000/selectTra1000View.do">트레이너 관리</a>
						</div>
					</div>
					
					<div class="com-header__nav-title">수업일정 관리
						<div class="com-header__nav-content">
							<a href="/pcs/pcs1000/pcs1000/selectPcs1000View.do">수업일정 관리</a>
						</div>
					</div>
					
					<div class="com-header__nav-title">사물함 관리
						<div class="com-header__nav-content">
							<a href="/lck/lck1000/lck1000/selectLck1000View.do">사물함 관리</a>
							<a href="/lck/lck1000/lck1100/selectLck1100View.do">사물함 이력 현황</a> 
						</div>
					</div>
					
					<div class="com-header__nav-title">통계 관리
						<div class="com-header__nav-content">
							<a href="/sta/sta1000/sta1000/selectSta1000View.do">통계 현황</a>
						</div>
					</div>
					
				</div>
			
				<div class="com-header__nav-log-infos">
					<div class="com-header__nav-log-info">관리자</div>
					<a href="/cmm/cmm1000/cmm1000/selectCmm1000Logout.do" id="logOut" name="logOut" class="com-header__nav-log-info com-header__nav-log-info__btn">로그아웃</a>
				</div>
			
			</div>
		</c:if>
		
		<c:if test="${'trainer' eq sessionScope.memberInfo['memberLevel']}">
			<div class="com-header__nav">
				<div class="com-header__nav-titles">
					<a class="com-header__nav-title" href="/cmm/cmm1000/cmm1000/selectCmm1000View.do">
						<i class="material-icons">fitness_center</i>
					</a>
			
					<div class="com-header__nav-title">회원 관리 
						<div class="com-header__nav-content">
							<a href="/usr/usr1000/usr1000/selectUsr1000View.do">회원 관리</a>
						</div>
					</div>
					
					<div class="com-header__nav-title">수업일정 관리
						<div class="com-header__nav-content">
							<a href="/pcs/pcs1000/pcs1000/selectPcs1000View.do">수업일정 현황</a>
						</div>
					</div>
					
					<div class="com-header__nav-title">사물함 관리
						<div class="com-header__nav-content">
							<a href="/lck/lck1000/lck1000/selectLck1000View.do">사물함 관리</a>
							<a href="/lck/lck1000/lck1100/selectLck1100View.do">사물함 이력 현황</a> 
						</div>
					</div>
					
				</div>
			
				<div class="com-header__nav-log-infos">
					<div class="com-header__nav-log-info">${sessionScope.memberInfo['name']}</div>
					<a href="/cmm/cmm1000/cmm1000/selectCmm1000Logout.do" id="logOut" name="logOut" class="com-header__nav-log-info com-header__nav-log-info__btn">로그아웃</a>
				</div>
				
			</div>
		</c:if>
		
		<c:if test="${'user' eq  sessionScope.memberInfo['memberLevel']}">
			<div class="com-header__nav">
				<div class="com-header__nav-titles">
					<a class="com-header__nav-title" href="/cmm/cmm1000/cmm1000/selectCmm1000View.do">
						<i class="material-icons">fitness_center</i>
					</a>
			
					<div class="com-header__nav-title">개인일정 조회 
						<div class="com-header__nav-content">
							<a href="/ucs/ucs1000/ucs1000/selectUcs1000View.do">개인일정 현황</a>
						</div>
					</div>
		
				</div>
			
				<div class="com-header__nav-log-infos">
					<div class="com-header__nav-log-info">${sessionScope.memberInfo['name']}</div>
					<a href="/cmm/cmm1000/cmm1000/selectCmm1000Logout.do" id="logOut" name="logOut" class="com-header__nav-log-info com-header__nav-log-info__btn">로그아웃</a>
				</div>
				
			</div>
		</c:if>

<body>
