<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>


<!--        여기부터 각페이지 본문             -->
<div class="cont work_list">
	<div class="title">
		<p>콜 통계</p>
		<a class="favo"></a>
	</div>

	<div class="tab_area">
		<div class="tab">
			<a class="active" data-tab="work_list_tab1">콜근무실적</a>
			<a class="" data-tab="work_list_tab2">월집계표</a>
		</div>
		<div class="search">
			<form name="revenue_search" method="post">
				<input type="hidden" name="page" value="1">
				<ul>
					<li class="calc">
						<p>사업단</p>
						<div class="sch_inp">
							<input type="hidden" name="og_seq" value="">
							<input type="text" name="og_seq_text" placeholder="검색하기" readonly/>
							<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="date">
						<p>조회기간</p>
						<div class="">
							<input type="month" name="date" placeholder="시작일">
						</div>
						<!--
						<div class="end">
							<input type="date" name="end_date" placeholder="종료일">
						</div>-->
					</li>
					
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">
						검색<i class="ri-search-line"></i>
					</button>
				</div>
			</form>
		</div>
		<div class="tab_box">
			<div class="box content" data-tab="work_list_tab1">
				<div class="wrap">
					<div class="pop_print">
						<h1><span class="date"></span> 콜근무실적</h1>
						<dl class="tb_dl">
							<dt>기관명&nbsp;:&nbsp;</dt>
							<dd>경기사업단</dd>
						</dl>
						<div class="tb">
							<div class="tb_scroll">
								<table class="revenue_list">
									<thead>
										<tr>
											<th>근무일자</th>
											<th>팀명</th>
											<th>작업구분</th>
											<th>성명</th>
											<th>시작시간</th>
											<th>종료시간</th>
											<th>근무시간</th>
											<th>지급액</th>
											<th>설비명</th>
											<th>작업내역</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							</div>

							<div class="tb_bottom">
								<div class="slt">
									<select name="row">
										<option value="20">20개씩 보기</option>
										<option value="50">50개씩 보기</option>
										<option value="100">100개씩 보기</option>
									</select>
								</div>
								<ul class="paging pagination"></ul>
								<div class="count">
									<p><span class="count_total">00</span>건이 검색되었습니다.</p>
								</div>
								<ul class="paging pagination">
									
								</ul>
							</div>

						</div>
					</div>
				</div>
				<div class="down_area">
					<a class="btn pdf_down sky" data-filename="콜근무실적">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>
			<div class="box content" data-tab="work_list_tab2" style="display:none;">
				<div class="wrap">
					<div class="pop_print">
						<h1><span class="date"></span> 콜근무 (월)집계표</h1>
						<dl class="tb_dl">
							<dt>기관명&nbsp;:&nbsp;</dt>
							<dd>경기사업단</dd>
						</dl>
						<div class="tb tb1" style="width:120px">
							<div class="table">
								<table class="">
									<thead>
										<tr>
											<th rowspan="2">구분</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>계</td>
										</tr>
										<tr>
											<td>유지보수</td>
										</tr>
										<tr>
											<td>고장수리</td>
										</tr>
										<tr>
											<td>부대업무</td>
										</tr>
										<tr>
											<td>대외업무</td>
										</tr>
										<tr>
											<td>기타</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="tb tb2">
							<div class="table">
								<table class="month_list">
									<thead>
										<tr>
											<th colspan="4">집계현황</th>
											<th colspan="3">통계분석</th>
											<th rowspan="2">비고</th>
										</tr>
										<tr>
											<th>발생횟수</th>
											<th>총근무시간</th>
											<th>근무인원</th>
											<th>집행금액</th>
											<th>평균근무시간</th>
											<th>건평균 근무인원</th>
											<th>건평균 집행금액</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>
				<div class="down_area">
					<a class="btn pdf_down sky" data-filename="월집계표">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!--    //    여기까지 각페이지 본문             -->



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/work_list.js"></script>