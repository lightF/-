<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common/head.sub.jsp" %>
<body>
	<header>
		<div class="hd_img">
			<div class="main_hd">
				<div class="logo">
					<a href="${contextPath}/index">
						<img src="${resourcePath}/css/img/logo.png">
					</a>
				</div>
				<div class="mu">
					<ul class="mu_list">
						<li><p>게시판</p></li>
						<li><p>시설관리</p></li>
						<li><p>부품관리</p></li>
						<li><p>고장관리</p></li>
						<li><p>콜관리</p></li>
						<li><p>계측기관리</p></li>
						<li><p>차량관리</p></li>
						<li><p>인사관리</p></li>
						<li><p>시스템관리</p></li>
					</ul>
				</div>
				<div class="info">
					<p><span>${per_name}</span>님 환영합니다.</p>
					<a class="logout" href="${contextPath}/logout"><i class="ri-logout-box-r-line"></i></a>
				</div>
			</div>
			<div class="sub_list mu">
				<div class="sub_mu ">
					<ul>
						<li>
							<a href="${contextPath}/notice"><p>공지사항</p></a>
							<a href="${contextPath}/board"><p>게시판</p></a>
						</li>
						<li>
							<a href="${contextPath}/facility_mgt"><p>기기관리</p></a>
							<a href="${contextPath}/standard_mgt"><p>표준명관리</p></a>
							<a href="${contextPath}/standard2_mgt"><p>표준명2관리</p></a>
							<a href="${contextPath}/account_mgt"><p>거래처관리</p></a>
							<a href="${contextPath}/statistics_mgt"><p>시설통계</p></a>
						</li>
						<li>
							<a href="${contextPath}/spare_mgt"><p>부품관리</p></a>
							<a href="${contextPath}/receipts_mgt"><p>수불관리</p></a>
							<a href="${contextPath}/warehouse_mgt"><p>창고관리</p></a>
							<a href="${contextPath}/part_stats"><p>부품통계</p></a>
						</li>
						<li>
							<a href="${contextPath}/faultreception_mgt"><p>고장접수관리</p></a>
							<a href="${contextPath}/failureaction_mgt"><p>고장조치관리</p></a>
							<a href="${contextPath}/duty_mgt"><p>당직일지</p></a>
							<a href="${contextPath}/ventre_stats"><p>고장통계</p></a>
						</li>
						<li>
							<a href="${contextPath}/work_management"><p>콜관리</p></a>
							<a href="${contextPath}/work_over_management"><p>연장근무관리</p></a>
							<a href="${contextPath}/work_list"><p>콜통계</p></a>
						</li>
						<li>
							<a href="${contextPath}/instrument_management"><p>계측기관리</p></a>
							<a href="${contextPath}/safe_management"><p>안전용품관리</p></a>
							<a href="${contextPath}/instrument_list"><p>계측기통계</p></a>
						</li>
						<li>
							<a href="${contextPath}/drive_report"><p>운행일지</p></a>
							<a href="${contextPath}/car_management"><p>차량관리</p></a>
							<a href="${contextPath}/accident"><p>사고일지</p></a>
							<a href="${contextPath}/car_management_list"><p>차량통계</p></a>
						</li>
						<li>
							<a href="${contextPath}/employee"><p>직원관리</p></a>
							<a href="${contextPath}/group"><p>조직관리</p></a>
							<a href="${contextPath}/personnel_statistics"><p>인사통계</p></a>
						</li>
						<li>
							<a href="${contextPath}/permission"><p>권한관리</p></a>
							<a href="${contextPath}/item_management"><p>입력항목관리</p></a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	
	<div class="contents">
		<aside class="favorites">
			<div>
				<div class="div_tit">
					<p>즐겨찾기</p>
				</div>
				<ul class="sortable">
				</ul>
			</div>
		</aside>