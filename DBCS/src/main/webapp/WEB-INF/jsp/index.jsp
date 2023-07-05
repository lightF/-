<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<!--        여기부터 각페이지 본문             -->
<div class="cont index">
	
	<dl class="summary">
		<dt>당일 등록 건수 통계</dt>
		<dd>
			<ul>
				<li>
					<p class="count_num">0</p>
					<p>고장접수</p>
				</li>
				<li>
					<p class="count_num1">0</p>
					<p>고장조치</p>
				</li>
				<li>
					<p class="count_num2">0</p>
					<p>콜근무</p>
				</li>
				<li>
					<p class="count_num3">0</p>
					<p>운행일지</p>
				</li>
			</ul>
		</dd>
		<dt>당월 등록 건수 통계</dt>
		<dd>
			<ul>
				<li>
					<p class="count_num4">0</p>
					<p>고장접수</p>
				</li>
				<li>
					<p class="count_num5">0</p>
					<p>고장조치</p>
				</li>
				<li>
					<p class="count_num6">0</p>
					<p>콜근무</p>
				</li>
				<li>
					<p class="count_num7">0</p>
					<p>운행일지</p>
				</li>
			</ul>
		</dd>
	</dl>
	<div class="summary_inner_wrap">
		<dl class="summary summary2">
			<dt>주간 신고지수</dt>
			<canvas id="myChart" width="215px">주간 신고지수</canvas>
			<!-- <dd>그래프</dd> -->
		</dl>
		<dl class="summary summary3">
			<dt>근무일정</dt>
			<dd>
				<div id="calendar"></div>
			</dd>
		</dl>
	</div>
	<dl class="summary summary4">
		<dt>근무자 통계</dt>
		<dd>
			<div class="tb_inner_type1">
				<table>
					<caption>
						근무자 통계
					</caption>
					<thead>
						<tr>
							<th>&nbsp;</th>
							<th>콜근무</th>
							<th>대체근무</th>
							<th>운행</th>
							<th>계</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>당일</td>
							<td class="today_call"></td>
							<td class="today_work"></td>
							<td class="today_drive"></td>
							<td class="today_total"></td>
						</tr>
						<tr>
							<td>당월</td>
							<td class="month_call"></td>
							<td class="month_work"></td>
							<td class="month_drive"></td>
							<td class="month_total"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</dd>
	</dl>
</div>

<%@ include file='tail.jsp' %>
<script src="${resourcePath}/js/chart3_9_1/chart3_9_1.js"></script>
<script src="${resourcePath}/js/chart3_9_1/chartjs-plugin-datalabels.min.js"></script>
<script src="${resourcePath}/js/util/popper.js"></script>
<script src="${resourcePath}/js/util/tippy.js"></script>
<script src="${resourcePath}/js/index.js"></script>