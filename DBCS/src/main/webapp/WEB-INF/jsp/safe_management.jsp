<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>


<!--        여기부터 각페이지 본문             -->
<div class="cont safe_management">
	<div class="title">
		<p>안전용품관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="safety_search" method="post">
				<input type="hidden" name="column" value="seq">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">

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
					<li class="w40">
						<p>안전용품명</p>
						<div>
							<input type="text" name="sd_product" placeholder="입력하세요."/>
						</div>
					</li>
					<li class="w50">
						<p>사업단</p>
						<div>
							<input type="hidden" name="sf_group" class="group_seq">
							<input type="text" name="sf_group_text" class="group_name" placeholder="점검팀 선택시 자동입력" readonly/>
						</div>
					</li>
					<li class="w50">
						<p>점검팀</p>
						<div class="sch_inp">
							<input type="hidden" name="pname">
							<input type="hidden" name="pcode">
							<input type="hidden" name="sf_check">
							<input type="text" name="sf_check_text" placeholder="검색하세요." readonly/>
							<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-type="new" data-dialog="safe_manag_view">안전용품 등록<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="tb_scroll">
				<table class="safety_list">
					<thead>
						<tr>
							<th data-column="group_name" data-order="ASC">사업단</th>
							<th data-column="check_name" data-order="ASC">점검팀</th>
							<th data-column="sys_seq" data-order="ASC">설비명</th>
							<th data-column="sd_product" data-order="ASC">안전용품명</th>
							<th data-column="sf_amount" data-order="ASC">보유수량</th>
							<th data-column="sf_date" data-order="ASC">최종입출일자</th>
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
				<div class="count">
					<p><span class="count_total">00</span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">
					
				</ul>
			</div>
			<div class="down_area">
				<a class="btn list_xlsx sky">Xlsx 다운로드</a>
			</div>
		</div>
	</div>
</div>
<!--    //    여기까지 각페이지 본문             -->

  

<!-- 안전용품 view -->
<div class="dialog pop_up_page" id="safe_manag_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>안전용품 관리 > <span class="detail_tit">안전용품 등록</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="measure_safety_detail" method="post">
				<input type="hidden" name="seq" value="">

				<div class="log_box">
					<div class="ul_style clearfix">
						<ul class="wrap1 clearfix">
							<li class="required">
								<p>사업단<span class="ess"></span></p>
								<div>
									<div>
										<input type="hidden" name="sf_group" class="group_seq">
										<input type="text" name="sf_group_name" class="group_name" placeholder="점검팀 선택시 자동입력" required="required" readonly>
									</div>
								</div>
							</li>
							<li class="required">
								<p>점검팀<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="pname">
									<input type="hidden" name="pcode">
									<input type="hidden" name="sf_check">
									<input type="text" name="sf_check_name" placeholder="검색하기" required="required" readonly>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>안전용품<span class="ess"></span></p>
								<div>
									<select name="sd_product" required="required">
									</select>
								</div>
							</li>
							<li>
								<p>설비명</p>
								<div>
									<select name="sys_seq">
									</select>
								</div>
							</li>
							<li class="required">
								<p>보유수량</p>
								<div>
									<input type="number" name="sf_amount" placeholder="입력하세요."/>
								</div>
							</li>

							<li>
								<p>최종변경일</p>
								<div>
									<input type="date" name="sf_update" placeholder="최종변경일"/>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="tb">
					<div class="table table_btn">
						<table class="safety_detail_list">
							<colgroup>
								<col width="6%">
								<col width="10%">
								<col width="16%">
								<col width="6%">
								<col width="16%">
								<col width="6%">
								<col width="">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>순번</th>
									<th>입출일자</th>
									<th>입고부서</th>
									<th>입고수량</th>
									<th>출고부서</th>
									<th>출고수량</th>
									<th>비고</th>
									<th class="tb_btn"><a class="btn add_btn">+</a></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><div>1</div></td>
									<td><input type="date" name="sd_date[]" placeholder="일자 선택" /></td>
									<td>
										<input type="text" name="sd_input[]" placeholder="입력하세요."/>
									</td>
									<td>
										<input type="number" name="input_ea[]" placeholder="입력하세요."/>
									</td>
									<td>
										<input type="text" name="sd_output[]" placeholder="입력하세요."/>
									</td>
									<td>
										<input type="number" name="output_ea[]" placeholder="입력하세요."/>
									</td>
									<td>
										<input type="text" name="sd_note[]" placeholder="입력하세요."/>
									</td>
									<td class="tb_btn">
										<a class="btn del_btn">-</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="btn_area">
					<button class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //안전용품 view -->



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/safe_management.js"></script>