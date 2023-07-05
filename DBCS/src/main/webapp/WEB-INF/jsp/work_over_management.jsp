<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<%@ page session="true" %>
<%
session = request.getSession();
Object ag_seq = session.getAttribute("ag_seq");

%>
<input type="hidden" name="ag_seq" value="<%=ag_seq%>">
<!--        여기부터 각페이지 본문             -->
<div class="cont work_over_management">
	<div class="title">
		<p>연장근무관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="overtime_search" method="post" action="">
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
					<li class="w20">
						<p>사업단</p>
						<div>
							<input type="hidden" name="wrk_group" class="group_seq">
							<input type="text" name="wrk_group_tmp" class="group_name" placeholder="점검팀 검색" required="required" readonly/>
						</div>
					</li>
					<li class="w20">
						<p>점검팀</p>
						<div class="sch_inp">
							<input type="hidden" name="pname">
							<input type="hidden" name="pcode">
							<input type="hidden" name="wrk_check" placeholder="검색"/>
							<input type="text" name="wrk_check_tmp" placeholder="검색" required="required" readonly/>
							<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="w20">
						<p>결원자</p>
						<div class="sch_inp">
							<input type="hidden" name="per_name" placeholder="검색"/>
							<input autocomplete="off" type="text" name="per_name_tmp" placeholder="검색" class="readonly" required="required" readonly>
							<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색<i class="ri-search-line"></i></button>
					<a class="btn sky" data-dialog="work_over_view">연장근무 등록<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="tb_scroll">
				<table class="overtime_list">
					<colgroup>
							<col width="6%">
							<col width="8%">
							<col width="8%">
							<col width="12%">
							<col width="6%">
							<col width="36%">
							<col width="6%">
							<col width="6%">
							<col width="8%">
							<col width="4%">
					</colgroup>
					<thead>
						<tr>
							<th data-column="seq" data-order="DESC" class="sort">근무코드</th>
							<th data-column="ot_date" data-order="ASC">년월</th>
							<th data-column="group_name" data-order="ASC">사업단</th>
							<th data-column="check_name" data-order="ASC">점검팀</th>
							<th data-column="brc_seq" data-order="ASC">지사</th>
							<th data-column="ot_reason" data-order="ASC">결원사유</th>
							<th data-column="per_name" data-order="ASC">결원자</th>
							<th data-column="ot_days" data-order="ASC">결원일수</th>
							<th data-column="ot_confirm" data-order="ASC">처리결과</th>
							<th>보고서</th>
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
<!--    //    여기까지 각페이지 본문             -->


<!-- 연장근무 상세보기 -->
<div class="dialog work_over_pop pop_up_page_type1" id="work_over_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>연장근무 관리 > <span class="detail_text">연장근무 등록</span></p>
	
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="call_overtime_detail" action="" method="post">
			<input type="hidden" name="seq" value="">
				<div class="log_box">
					<div class="ul_style clearfix">
						<ul class="wrap1 clearfix">
							<li>
								<p>년월</p>
								<div class="">
									<input type="month" name="ot_date" placeholder="일자 선택" />
								</div>
							</li>
							<li class="required">
								<p>지사<span class="ess"></span></p>
								<div>
									<select name="brc_seq"></select>
								</div>
							</li>

							<li class="required">
								<p>사업단<span class="ess"></span></p>
								<div>
									<input type="hidden" name="ot_group" class="group_seq" required>
									<input type="text" name="ot_group_name" class="group_name" placeholder="점검팀 선택시 자동입력" required readonly/>
								</div>
							</li>
							<li class="required">
								<p>점검팀<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="pname">
									<input type="hidden" name="pcode">
									<input type="hidden" name="ot_check" required="required">
									<input type="text" name="ot_check_name" placeholder="검색하기" required="required" readonly/>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>관련근거<span class="ess"></span></p>
								<div>
									<input type="text" name="ot_relate" placeholder="입력하세요." value="" required="required"/>
								</div>
							</li>
							<li class="required">
								<p>담당자확인<span class="ess"></span></p>
								<div>
									<select name="ot_confirm">
										<option value="">선택</option>
										<option value="1">확인중</option>
										<option value="2">승인불가</option>
										<option value="3">확인완료</option>
									</select>
								</div>
							</li>

							<li class="li_full required">
								<p>결원사유<span class="ess"></span></p>
								<div>
									<input type="text" name="ot_reason" placeholder="입력하세요." value="" required>
								</div>
							</li>
							<li class="required">
								<p>결원자<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="ot_person" required="required">
									<input type="text" name="ot_person_name" placeholder="검색하기" required="required" readonly/>
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li>
								<p>결원일수</p>
								<div class="sch">
									<input type="number" name="ot_days" value="" readonly placeholder="자동계산">
								</div>
							</li>
							<li class="required">
								<p>휴가 시작시간<span class="ess"></span></p>
								<div>
									<input type="date" name="ot_start" placeholder="일자 선택" required="required"/>
								</div>
							</li>
							<li class="required">
								<p>휴가 종료시간<span class="ess"></span></p>
								<div>
									<input type="date" name="ot_end" placeholder="일자 선택" required="required"/>
								</div>
							</li>
							
							<li>
								<p>비고</p>
								<div>
									<input type="text" name="ot_note" placeholder="입력하세요." value=""/>
								</div>
							</li>
						</ul>
					</div>
					<div class="tb">
						<div class="table table_btn">
							<table class="tbl_call">
								<colgroup>
									<col width="10%">
									<col width="10%">
									<col width="10%">
									<col width="10%">
									<col width="">
									<col>
								</colgroup>

								<thead>
									<tr>
										<th>근무자</th>
										<th>근무구분</th>
										<th>근무시작시간</th>
										<th>근무종료시간</th>
										<th>근무내역</th>
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
					<button type="button" class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn del" data-dialog="delete_pop"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //연장근무 상세보기 -->

<!-- 보고서 확인하기 -->
<div class="dialog pop_up_page_type1 pop_up_report" id="work_over_report">
	<div class="overlay"></div>
	<div class="content">
		<div class="info">
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<div class="pop_print">
				<div class="title">
					<h2>연장근무일지</h2>
				</div>
				<div class="tb tb1">
					<table>
						<caption>
							1.<span>결원사항</span>
						</caption>
						<thead>
							<tr>
								<th>소속</th>
								<th>성명</th>
								<th>결원기간</th>
								<th>결원사유</th>
								<th>결원시간</th>
								<th>관련근거</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td></td>
								<td class="work_time2">
									<span></span><span> ~ </span>
									<span></span>
								</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="tb tb2">
					<table>
						<caption>
							2.<span>연장근무사항</span>
						</caption>
						<thead>
							<tr>
								<th>소속</th>
								<th>성명</th>
								<th>근무일자</th>
								<th>근무시간</th>
								<th>총 시간</th>
								<th>근무내역</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="down_area">
			<a class="btn pdf_down sky" data-filename="연장근무일지">Pdf 다운로드</a>
		</div>
	</div>
</div>
<!-- //보고서 확인하기 -->
<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/work_over_management.js"></script>