<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--		여기부터 각페이지 본문			 -->
<div class="cont car_management_list">
	<div class="title">
		<p>차량 통계</p>
		<a class="favo"></a>
	</div>
	<div class="tab_area">
		
		<div class="tab">
			<a class="active" data-tab="car_tab1">연료별</a>
			<a class="" data-tab="car_tab2">설비별</a>
			<a class="" data-tab="car_tab3">사업단별</a>
			<a class="" data-tab="car_tab4">차량별(월)</a>
			<a class="" data-tab="car_tab5">일별운행</a>
		</div>
		<div class="search">
			<form name="car_search" action="" method="">
				<input type="hidden" name="" value="">
				<ul>
					<li class="">
						<p>조회기간</p>
						<div class="start">
							<input type="month" name="date">
						</div>
						<div class="end car_tab5_search">
							<input type="month" name="end_date">
						</div>
					</li>
					<li class="car_tab5_search">
						<p>차량검색</p>
						<div class="sch_inp">
							<input type="hidden" name="ve_seq">
							<input type="text" name="ve_number" placeholder="검색하기" readonly>
							<a class="btn car_sch_btn" data-dialog="car_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
				</ul>
			</form>
		</div>
		<div class="tab_box">
			<div class="box content" data-tab="car_tab1">
				<div class="warp pop_print">
					<div class="tb">
						<div class="table">
							<table>
								<caption>
									■ 차량 운행실적
								</caption>
								<thead>
									<tr>
										<th>구분</th>
										<th>수량</th>
										<th class="color_red">가동일</th>
										<th>총가동거리</th>
										<th>총주유량</th>
										<th>L당거리</th>
										<th>일평균이동</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					
					</div>
					<div class="graphs">
						<div class="graph_box">
							<p>L당 가동거리(km)</p>
							<div class="graph" id="chart1"></div>
						</div>
						<div class="graph_box">
							<p>일평균 이동거리(km)</p>
							<div class="graph" id="chart2"></div>
						</div>
					</div>
				</div>
				<div class="down_area">
					<a class="btn pdf_down sky" data-filename="차량운행실적">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>
			<div class="box content" data-tab="car_tab2" style="display:none">
				<div class="warp pop_print">
					<div class="tb">
						<div class="table">
							<table>
								<caption>
									■ 차량 운행실적
								</caption>
								<thead>
									<tr>
										<th>구분</th>
										<th>수량</th>
										<th>평균가동일</th>
										<th>총가동거리</th>
										<th>총주유량</th>
										<th>L당거리</th>
										<th>일평균이동</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						
					</div>
					<div class="graphs">
						<div class="graph_box">
							<p>L당 가동거리(km)</p>
							<div class="graph" id="chart3"></div>
						</div>
						<div class="graph_box">
							<p>일평균 이동거리(km)</p>
							<div class="graph" id="chart4"></div>
						</div>
					</div>
				</div>
				<div class="down_area">
					<a class="btn pdf_down sky" data-filename="차량운행실적">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>
			<div class="box content" data-tab="car_tab3" style="display:none">
				<div class="warp pop_print">
					<div class="tb">
						<div class="table">
							<table>
								<caption>
									■ 차량 운행실적
								</caption>
								<thead>
									<tr>
										<th>구분</th>
										<th>수량</th>
										<th>평균가동일</th>
										<th>총가동거리</th>
										<th>총주유량</th>
										<th>L당거리</th>
										<th>일평균이동</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="graphs">
						<div class="graph_box">
							<p>L당 가동거리(km)</p>
							<div class="graph" id="chart5"></div>
						</div>
						<div class="graph_box">
							<p>일평균 이동거리(km)</p>
							<div class="graph" id="chart6"></div>
						</div>
					</div>
				</div>
				<div class="down_area">
					<a class="btn pdf_down sky">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>
			<div class="box content" data-tab="car_tab4" style="display:none">
				<div class="warp pop_print">
					<div class="tb">
						<div class="table">
							<table>
								<caption>
									■ 차량별 운행실적
								</caption>
								<thead>
									<tr><th colspan="6">조회월 <span class="tb_month">11</span>월</th></tr>
									<tr>
										<th>차량번호</th>
										<th>운행거리</th>
										<th>주유비</th>
										<th>주차비</th>
										<th>통행료</th>
										<th>정비비</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="down_area">
					<a class="btn pdf_down sky">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>
			<div class="box content" data-tab="car_tab5" style="display:none">
				<div class="warp pop_print">
					<div class="tb">
						<div class="table">
							<table>
								<caption>
									■ 차량별 운행실적
								</caption>
								<thead>
									<tr>
										<th>운행일자</th>
										<th>차량번호</th>
										<th>운행거리</th>
										<th>주유비</th>
										<th>주차비</th>
										<th>통행료</th>
										<th>정비비</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="down_area">
					<a class="btn pdf_down sky">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>

		</div>
	</div>
</div>
<!--	//	여기까지 각페이지 본문			 -->


<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/util/d3.v4.min.js"></script>
<script type="text/javascript" src="${resourcePath}/js/util/chart.js"></script>
<script type="text/javascript" src="${resourcePath}/js/car_management_list.js"></script>