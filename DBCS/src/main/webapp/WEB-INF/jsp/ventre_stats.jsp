<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>


<!--    여기부터 각페이지 본문       -->
<div class="cont ventre">
	<div class="title">
		<p>고장통계</p>
		<a class="favo"></a>
	</div>
	<div class="wrap content">
		<div class="search">
			<form name="breakdown_search" method="post" action="" >
				<ul>
					<li>
						<p>사업단</p>
						<div class="sch_inp">
							<input type="hidden" name="og_seq" value="">
							<input type="text" name="text" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li>
						<p>조회설정</p>
						<div class="ipt">
							<input type="text" name="year" value="2022" placeholder="입력하세요.">
						</div>
						<span>년</span>
						<div>
							<select name="quarter">
								<option value="0">전체</option>
								<option value="1" selected>1/4분기</option>
								<option value="2">2/4분기</option>
								<option value="3">3/4분기</option>
								<option value="4">4/4분기</option>
							</select>
						</div>
					</li>
					<!-- <li>
						<p>구분</p>
						<div>
							<select name="">
								<option value="">전체</option>
								<option value="">KHC</option>
							</select>
						</div>
					</li> -->
				</ul>
				<div class="btn_area">
					<button type="botton" class="btn btn_search">검색하기<i class="ri-search-line"></i></button>
				</div>
			</form>
		</div>
		<div class="pop_print">
			<div class="tb">
				<div class="table">
					<table class="breakdown_list">
						<thead>
							<tr>
								<th>월</th>
								<th>구분</th>
								<th>소계</th>
								<th>TCS</th>
								<th>축중기</th>
								<th>FTMS</th>
								<th>TTMS</th>
								<th>전송</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			
		</div>
		<div class="down_area">
			<a class="btn pdf_down sky" data-filename="고장통계">Pdf 다운로드</a>
			<a class="btn xlsx sky">Xlsx 다운로드</a>
		</div>
	</div>
	
</div>

<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/ventre_stats.js"></script>