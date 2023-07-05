<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/mobile.head.sub.jsp" %>


<body>
	<header class="m_hd">
		<div class="logo">
			<a href="${contextPath}/notice">
				<img src="${resourcePath}/css/img/m_logo.png">
			</a>
		</div>
		<a class="btn sky mu_btn"><img src="${resourcePath}/css/img/m_menu_ico.png"></a>
		<div class="m_menu">
			<div class="overlay"></div>
			<div class="mu_list">
				<div class="mu_hd">
					<p><span>${per_name}</span>님 환영합니다.</p>
					<a class="close"><i class="ri-close-line"></i></a>
				</div>
				<ul>
					<li>
						<a href="${contextPath}/notice">공지사항</a>
					</li>
					<li>
						<a href="${contextPath}/board">게시판</a>
					</li>
					<li>
						<a href="${contextPath}/faultreception_mgt">고장접수관리</a>
					</li>
					<li>
						<a href="${contextPath}/work_management">콜관리</a>
					</li>
					<li>
						<a href="${contextPath}/drive_report">운행일지</a>
					</li>
				</ul>
				<a class="logout" href="/DBCS/logout">log out <i class="ri-logout-box-r-line"></i></a>
			</div>
		</div>
	</header>
	