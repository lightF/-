<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.jsp" %>

<div class="m_cont">
	<div class="m_broken">
		<div class="top">
			<a class="btn sch_btn">상세검색</a>
			<div class="m_search">
				<div class="overlay"></div>
				<div class="sch_box">
					<div class="top_tit">
						<p>고장접수관리 > <span>상세검색</span></p>
					</div>
					<form name="action_search" method="post" enctype="multipart/form-data">
						<input type="hidden" name="column" value="seq">
						<input type="hidden" name="order" value="DESC">
						<input type="hidden" name="page" value="1">
						<ul>
							<li>
								<p>조회기간</p>
								<div class="start">
									<input type="date" name="start_date" placeholder="시작일">
								</div>
								<div class="end">
									<input type="date" name="end_date" placeholder="종료일">
								</div>
							</li>
							<li>
								<p>설비위치</p>
								<div>
									<input type="text" name="dc_location" placeholder="입력하세요.">
								</div>
							</li>
							<li>
								<p>고장번호</p>
								<div>
									<input type="text" name="bk_code" placeholder="입력하세요.">
								</div>
							</li>
							<li>
								<p>고장기준</p>
								<div>
									<select name="bk_standard">
										<option value="">전체</option>
										<option value="1">단순정비</option>
										<option value="2">경정비</option>
										<option value="3">중정비</option>
										<option value="4">입고수리</option>
										<option value="5">원상복귀</option>
									</select>
								</div>
							</li>
						</ul>
						<div class="btn_area">
							<button type="button" class="btn btn_search"><i class="ri-search-line"></i>검색</button>
						</div>
					</form>
				</div>
			</div>
			<p class="title">고장접수관리</p>
			<a class="btn sky btn_add" data-type="new" >고장등록</a>
		</div>
		<div class="m_ul_list">
			<div class="ul_list">
				<ul>
					<!--
					<li>
						<p class="count">1</p>
						<a href="${contextPath}/faultreception_mgt_view">
							<div class="list_st1">
								<p>기기명: <span class="device">운전자표시기</span></p>
								<span class="line"></span>
								<p>고장기준: <span class="fa_type">단순정비</span></p>
							</div>
							<div class="list_st2">
								<p>설비위치: <span class="location">하이패스 경기 동서울지사 청계영업소 60차로</span></p>
							</div>
							<div class="list_st3">
								<p>접수일시: <span class="date1">22-08-29</span></p>
								<span class="line"></span>
								<p>조치일시: <span class="date2">22-08-29</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">2</p>
						<a href="${contextPath}/faultreception_mgt_view">
							<div class="list_st1">
								<p>기기명: <span class="device">운전자표시기</span></p>
								<span class="line"></span>
								<p>고장기준: <span class="fa_type">단순정비</span></p>
							</div>
							<div class="list_st2">
								<p>설비위치: <span class="location">하이패스 경기 동서울지사 청계영업소 60차로</span></p>
							</div>
							<div class="list_st3">
								<p>접수일시: <span class="date1">22-08-29</span></p>
								<span class="line"></span>
								<p>조치일시: <span class="date2">22-08-29</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">3</p>
						<a href="${contextPath}/faultreception_mgt_view">
							<div class="list_st1">
								<p>기기명: <span class="device">운전자표시기</span></p>
								<span class="line"></span>
								<p>고장기준: <span class="fa_type">단순정비</span></p>
							</div>
							<div class="list_st2">
								<p>설비위치: <span class="location">하이패스 경기 동서울지사 청계영업소 60차로</span></p>
							</div>
							<div class="list_st3">
								<p>접수일시: <span class="date1">22-08-29</span></p>
								<span class="line"></span>
								<p>조치일시: <span class="date2">22-08-29</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">4</p>
						<a href="${contextPath}/faultreception_mgt_view">
							<div class="list_st1">
								<p>기기명: <span class="device">운전자표시기</span></p>
								<span class="line"></span>
								<p>고장기준: <span class="fa_type">단순정비</span></p>
							</div>
							<div class="list_st2">
								<p>설비위치: <span class="location">하이패스 경기 동서울지사 청계영업소 60차로</span></p>
							</div>
							<div class="list_st3">
								<p>접수일시: <span class="date1">22-08-29</span></p>
								<span class="line"></span>
								<p>조치일시: <span class="date2">22-08-29</span></p>
							</div>
						</a>
					</li>
					-->
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
					<p><span class="count_total">0</span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">
					<!--
					<li><a><img src="${resourcePath}/css/img/prev_prev.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/prev_.png"></a></li>
					<li><a class="active">1</a></li>
					<li><a>2</a></li>
					<li><a>3</a></li>
					<li><a>4</a></li>
					<li><a>5</a></li>
					<li><a><img src="${resourcePath}/css/img/next_.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/next_next.png"></a></li>
					-->
				</ul>
			</div>
		</div>
	</div>
</div>		



<%@ include file="tail.jsp" %>
<script src="${resourcePath}/js/mobile/faultreception_mgt.js"></script>