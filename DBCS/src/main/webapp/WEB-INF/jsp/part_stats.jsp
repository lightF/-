<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--    여기부터 각페이지 본문       -->
<div class="cont part_stats">
	<div class="title">
		<p>부품통계</p>
		<a class="favo"></a>
	</div>

	<div class="tab_area">
		
		<div class="tab">
			<a class="active">부품별재고조회</a>
			<!--<a class="" data-tab="tab2">부서별재고조회</a> 사용x-->
		</div>
		<div class="search">
			<form name="part_search" method="post" action="">
				<ul>
					<li class="">
						<p>사업단</p>
						<div class="sch_inp">
							<input type="hidden" name="og_seq">
							<input type="text" name="og_seq_text" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
						</div>
					</li>
					
					<li class="">
						<p>기준일</p>
						<div>
							<input type="date" name="date">
						</div>
					</li>
					<li class="">
						<p>창고</p>
						<div class="sch_inp">
							<input type="hidden" name="sr_seq">
							<input type="text" name="sr_seq_text" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="store_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<!--
					<li class="">
						<p>부품</p>
						<div class="sch_inp">
							<input type="hidden" name="pt_name">

							<input type="text" name="pt_name_text" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="storage_order"><i class="ri-search-line"></i></a>

						</div>
					</li>
					<!--
					<li class="checkbox">
						<div>
							<input type="checkbox" id="stat_chk" name="stat_chk">
							<label for="stat_chk"><span></span>모든창고 포함</label>
						</div>
					</li>
					-->
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색하기<i class="ri-search-line"></i></button>
				</div>
			</form>
		</div>
		<div class="tab_box">
			<div class="box content">
				<div class="wrap pop_print">
					<div class="tb tb1">
						<div class="table">
							<table class="part_list">
								<colgroup>
									<col width="150px">
									<col width="20%">
									<col width="20%">
									<col width="">
									<col width="">
									<col width="">
									<col width="">
								</colgroup>
								<thead>
									<tr>
										<th>부품코드</th>
										<th>부품명</th>
										<th>기기명</th>
										<th>창고코드</th>
										<th>부서명</th>
										<th>가능량</th>
										<th>재고금액</th>
									</tr>
								</thead>
								<tbody>
							
								</tbody>
							</table>
						</div>
						
					</div>
					<div class="tb tb2">
						<div class="table">
							<table class="stock_table">
								<thead>
									<tr>
										<th>창고코드</th>
										<th>부품코드</th>
										<th>부품단가</th>
										<th>재고수량</th>
										<th>금액</th>
									</tr>
								</thead>
								<tbody>
								</tbody>	
							</table>
						</div>
					</div>
				</div>

				<div class="down_area">
					<a class="btn pdf_down sky" data-filename="부품별재고조회">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>
			
			
		</div>
	</div>
</div>




<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/part_stats.js"></script>
