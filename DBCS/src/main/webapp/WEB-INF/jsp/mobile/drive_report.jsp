<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.jsp" %>

<div class="m_cont">
	<div class="m_driving_log">
		<div class="top">
			<a class="btn sch_btn">상세검색</a>
			
			<div class="m_search">
				<div class="overlay"></div>
				<div class="sch_box">
					<div class="top_tit">
						<p>운행일지 > <span>상세검색</span></p>
					</div>
					<form name="sch_form" action="" method="">
						<input type="hidden" name="page" value="1">
						<input type="hidden" name="column" value="rc_date">
						<input type="hidden" name="order" value="DESC">
						<ul>
							<li class="date">
								<p>조회기간</p>
								<div class="start">
									<input type="date" name="start_date" placeholder="시작일">
								</div>
								<div class="end">
									<input type="date" name="end_date" placeholder="종료일">
								</div>
							</li>
							<li>
								<p>사업단</p>
								<div class="sch_inp">
									<input type="hidden" name="og_seq">
									<input type="text" name="og_name" placeholder="검색하세요.">
									<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li>
								<p>차량번호</p>
								<div>
									<input type="number" name="ve_number" placeholder="입력하세요.">
								</div>
							</li>
							<li>
								<p>사고여부</p>
								<div>
									<select name="rc_handle">
										<option value="">전체</option>
										<option value="1">무</option>
										<option value="2">유</option>
									</select>
								</div>
							</li>
						</ul>
						<div class="btn_area">
							<button class="btn"><i class="ri-search-line"></i>검색</button>
						</div>
					</form>
				</div>
			</div>
			<p class="title">운행일지</p>
			<a class="btn sky btn_add" data-type="0">일지등록</a>
		</div>
		<div class="m_ul_list">
			<div class="ul_list">
				<ul>
					<!-- <li>
						<p class="count">1</p>
						<a href="${contextPath}/drive_report_view">
							<div class="list_st1">
								<p>운행팀: <span class="name">대구부산터널팀</span></p>
								<span class="line"></span>
								<p>차량번호: <span class="car_nb">91주7539</span></p>
							</div>
							<div class="list_st2">
								<p>운행전: <span class="before">171,417km</span></p>
								<span class="line"></span>
								<p>운행후: <span class="after">171,506km</span></p>
							</div>
							<div class="list_st3">
								<p>운행일자: <span class="date">22-08-29</span></p>
							</div>
						</a>
					</li> -->
				</ul>
			</div>
			<div class="tb_bottom">
				<div class="slt">
					<select name="row">
						<option value="20">20개씩 보기</option>
						<option value="50">50개씩 보기</option>
						<option value="100">100개씩 보기</option>
					</select>
				</div>
				<div class="count">
					<p><span id="total">00</span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">
					<!-- <li><a><img src="${resourcePath}/css/img/prev_prev.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/prev_.png"></a></li>
					<li><a class="active">1</a></li>
					<li><a>2</a></li>
					<li><a>3</a></li>
					<li><a>4</a></li>
					<li><a>5</a></li>
					<li><a><img src="${resourcePath}/css/img/next_.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/next_next.png"></a></li> -->
				</ul>
			</div>
		</div>
	</div>
</div>		

<script src="${resourcePath}/js/mobile/drive_report.js"></script>
<%@ include file="tail.jsp" %>