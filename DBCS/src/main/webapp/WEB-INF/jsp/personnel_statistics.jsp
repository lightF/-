<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--        여기부터 각페이지 본문             -->
<div class="cont personnel_statistics">
	<div class="title">
		<p>인사 통계</p>
		<a class="favo"></a>
	</div>
	<div class="wrap content">
		<div class="search">
			<form name="" action="" method="">
				<ul>
					<li class="date">
						<p>조회기간</p>
						<div class="start">
							<input type="date" name="start_date" placeholder="시작일" required="">
						</div>
						<div class="end">
							<input type="date" name="end_date" placeholder="종료일" required="">
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button class="btn">검색하기<i class="ri-search-line"></i></button>
				</div>
			</form>
		</div>
		<div class="pop_print">
			<div class="tb">
				<h1>직급별 인원 현황</h1>
				<div class="tb_scroll">
					<table>
						<caption>
							<div>
								<dl>
									<dt>가.&nbsp;</dt>
									<dd>총괄</dd>
								</dl>
								<div class="caption_date">
									<div>2022-08-01</div>
									<div>현재</div>
								</div>
							</div>
						</caption>
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
					
				</div>
			</div>
			<div class="graphs_wrap">
				<div class="graph_box graph_1">
					<p>고용형태별 분포도</p>
					<div>
						<div class="graph" id="chart1"></div>
					</div>
				</div>
				<div class="graph_box graph_2">
					<p>사업단별 인원현황</p>
					<div>
						<div class="graph" id="chart2"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="down_area">
			<a class="btn pdf_down sky" data-filename="인사통계">Pdf 다운로드</a>
			<a class="btn xlsx sky">Xlsx 다운로드</a>
		</div>
	</div>
	
</div>
<!--    //    여기까지 각페이지 본문             -->

    

<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/util/jquery.animateNumber.min.js"></script>
<script type="text/javascript" src="${resourcePath}/js/util/d3.v4.min.js"></script>
<script type="text/javascript" src="${resourcePath}/js/util/chart.js"></script>
<script type="text/javascript" src="${resourcePath}/js/personel_statistics.js"></script>