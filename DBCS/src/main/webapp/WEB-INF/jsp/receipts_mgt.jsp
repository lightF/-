<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>


<!--    여기부터 각페이지 본문       -->
<div class="cont receipts">
	<div class="title">
		<p>수불관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="payment_search" method="post">
				<input type="hidden" name="column" value="seq">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">

				<ul>
					<li class="date">
						<p>발주일자</p>
						<div class="start">
							<input type="date" name="start_date" placeholder="시작일">
						</div>
						<div class="end">
							<input type="date" name="end_date" placeholder="종료일">
						</div>
					</li>
					<li class="calc">
						<p>발주번호</p>
						<div>
							<input type="text" name="pm_seq" placeholder="입력하세요.">
						</div>
					</li>
					<li class="calc">
						<p>배정창고</p>
						<div class="sch_inp">
							<input type="hidden" name="pm_storage">
							<input type="text" name="pm_storage_name" placeholder="검색" readonly>
							<a class="btn" data-dialog="store_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="calc">
						<p>담당자</p>
						<div>
							<input type="text" name="per_name" placeholder="입력하세요.">
						</div>
					</li>
					<li class="calc">
						<p>계약명</p>
						<div>
							<input type="text" name="pm_contract" placeholder="입력하세요.">
						</div>
					</li>
					<li class="calc">
						<p>부품명</p>
						<div>
							<input type="text" name="pt_name" placeholder="입력하세요.">
						</div>
					</li>

				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-dialog="receipts_log_view" data-type="new">수불요청<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table tb_scroll">
				<table class="payment_list">
					<colgroup>
						<col width="8%">
						<col width="8%">
						<col width="10%">
						<col width="12%">
						<col width="14%">
						<col width="20%">
						<col width="20%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th data-column="seq" data-order="ASC">구매발주번호</th>
							<th data-column="pm_date" data-order="ASC">발주일자</th>
							<th data-column="og_seq" data-order="ASC">사업단</th>
							<th data-column="sr_name" data-order="ASC">배정창고</th>
							<th data-column="act_company" data-order="ASC">발주업체명</th>
							<th data-column="act_seq" data-order="ASC">부품명</th>
							<th data-column="pm_contract" data-order="ASC">계약명</th>
							<th data-column="per_name" data-order="ASC">담당자</th>
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
						<option value="500">500개씩 보기</option>
					</select>
				</div>
				<div class="count">
					<p><span class="count_total"></span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">
				</ul>
			</div>
		</div>
	</div>
</div>
<!--  //  여기까지 각페이지 본문       -->
		


<!-- 수불관리 확인하기 -->
<div class="dialog receipts_pop" id="receipts_log_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>수불관리 > <span>상세 수불관리</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="payment_detail" method="post" >
				<input type="hidden" name="seq" value="">
				<div class="log_box pop_print">
					<div class="ul_style">
						<ul class="wrap1">
							<li class="w100">
								<p>계약명</p>
								<div>
									<input type="text" name="pm_contract" placeholder="입력하세요.">
								</div>
							</li>
							<li class="required">
								<p>구매품의서 번호<span class="ess"></span></p>
								<div>
									<input type="text" name="pm_letter" placeholder="입력하세요." required>
								</div>
							</li>
							<li class="required">
								<p>발주부서<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="og_seq" required>
									<input type="text" name="og_seq_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>발주업체<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="act_seq" required>
									<input type="text" name="act_seq_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="act_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>발주일자<span class="ess"></span></p>
								<div>
									<input type="date" name="pm_date" placeholder="일자 선택">
								</div>
							</li>
							<li class="required">
								<p>납품장소<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="pm_place" required>
									<input type="text" name="pm_place_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="store_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>납품예정일 <span class="ess"></span></p>
								<div>
									<input type="date" name="pm_delivery" placeholder="일자 선택" required>
								</div>
							</li>
							<li class="required">
								<p>배정창고 <span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="pm_storage" required>
									<input type="text" name="pm_storage_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="store_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>구매담당자 <span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="per_seq">
									<input type="text" name="per_seq_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>발주선택 <span class="ess"></span></p>
								<div class="rdo">
									<input type="radio" name="pm_division" value="1" id="optional_rdo" required>
									<label for="optional_rdo">임의발주</label>
									<span class="line"></span>
									<input type="radio" name="pm_division" value="2" id="order_rdo">
									<label for="order_rdo">요청발주</label>
									<small>선택된 항목으로만 발주 가능합니다.</small>
								</div>
							</li>
						</ul>
					</div>
					<div class="tb col">
						<div class="tit">
							<p>발주요청</p>
						</div>
						<div class="table table_btn">
							<table class="detail_list">
								<thead>
									<tr>
										<th>부품코드</th>
										<th>부품명</th>
										<th>구매수량</th>
										<th>배정수량</th>
										<th>단가</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="btn_area">
					<button class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<!--<a class="btn btn_down pdf_down sky" data-filename="수불관리">Pdf 다운로드</a>
					<a class="btn btn_down xlsx sky">Xlsx 다운로드</a>
					-->
					<a class="btn close"><i class="ri-close-line"></i>발주취소</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //수불관리 확인하기 -->



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/receipts_mgt.js"></script>
