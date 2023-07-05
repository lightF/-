<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<%@ page session="true" %>
<%
session = request.getSession();
Object orgs = session.getAttribute("per_seq");
Object ag_seq = session.getAttribute("ag_seq");

%>
<input type="hidden" name="ag_seq" value="<%=ag_seq%>">
<!--        여기부터 각페이지 본문             -->
<div class="cont work_management">
	<div class="title">
		<p>콜관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="call_work_list" method="post">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="column" value="seq">
				<input type="hidden" name="order" value="DESC">
				<ul>
					<li class="date">
						<p>조회기간</p>
						<div class="start">
							<input autocomplete="off" type="date" name="start_date" placeholder="시작일">
						</div>
						<div class="end">
							<input autocomplete="off" type="date" name="end_date" placeholder="종료일">
						</div>
					</li>
					<li class="w21">
						<p>사업단</p>
						<div>
							<input type="hidden" name="wrk_group" class="group_seq">
							<input type="text" name="group_name" class="group_name" placeholder="점검팀 검색" class="readonly" value="" readonly>
						</div>
					</li>
					<li class="w21">
						<p>점검팀</p>
						<div class="sch_inp">
							<input type="hidden" name="pname" >
							<input type="hidden" name="pcode" >
							<input type="hidden" name="wrk_check" >
							<input type="text" name="check_name" placeholder="검색" class="readonly" required="required" readonly>
							<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="w20">
						<p>확인여부</p>
						<div>
							<select name="wrk_confirm">
								<option value="" selected>전체</option>
								<option value="1">확인중</option>
								<option value="2">승인불가</option>
								<option value="3">확인완료</option>
							</select>
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button class="btn">검색<i class="ri-search-line"></i></button>
					<a class="btn sky" data-dialog="work_manag_view">근무 등록<i class="ri-pencil-fill"></i></a>
				</div>

			</form>
		</div>
		<div class="tb">
			<div class="tb_scroll">
				<table>
					<colgroup>
						<col width="12%">
						<col width="12%">
						<col width="12%">
						<col width="20%">
						<col width="12%">
						<col width="12%">
						<col width="12%">
						<col width="10%">
						<col width="6%">
					</colgroup>
					<thead>
						<tr>
							<th data-column="seq" data-order="DESC" class="sort">콜코드</th>
							<th data-column="wrk_date" data-order="ASC">일자</th>
							<th data-column="wrk_group" data-order="ASC">사업단</th>
							<th data-column="wrk_check" data-order="ASC">점검팀</th>
							<th data-column="wd_seq" data-order="ASC">작업구분</th>
							<th data-column=wrk_hour"" data-order="ASC">시작시간</th>
							<th data-column="wrk_end" data-order="ASC">종료시간</th>
							<th data-column="wrk_confirm" data-order="ASC">확인여부</th>
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
					<p><span id="total"></span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">
				</ul>
			</div>
		</div>
	</div>
</div>
<!--    //    여기까지 각페이지 본문             -->


<!-- //근무 view -->
<div class="dialog work_manag_pop pop_up_page_type1" id="work_manag_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>콜 관리 > <span>근무 등록</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>

		<div class="body">
			<form name="call_work_detail" method="post">
				<input type="hidden" name="target" value="">
				<input type="hidden" name="seq" value="">
				<div class="log_box">
					<div class="ul_style clearfix">
						<ul class="wrap1 clearfix">
							<li class="required">
								<p>근무일자<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="date" name="wrk_date" value="" class="on_date" required disabled>
								</div>
							</li>
							<li class="required">
								<p>점검팀<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="pname">
									<input type="hidden" name="pcode">
									<input type="hidden" name="wrk_check" >
									<input autocomplete="off" type="text" name="check_name" placeholder="검색하기" required readonly>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>사업단<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="wrk_group" class="group_seq">
									<input type="text" name="group_name" class="group_name" placeholder="점검팀 선택시 자동입력" required readonly>
								</div>
							</li>
							<li class="required">
								<p>설비분류<span class="ess"></span></p>
								<div>
									<select name="sys_seq" required="required">
									</select>
								</div>
							</li>
							<li>
								<p>고장번호</p>
								<div class="sch_inp">
									<input type="hidden" name="bk_seq" >
									<input type="text" name="bk_seq_name" placeholder="검색하기" class="readonly" readonly/>
									<a class="btn" data-dialog="error_num_sch">
										<i class="ri-search-line"></i>
									</a>
								</div>
							</li>
							<li>
								<p>수리날짜</p>
								<div>
									<input autocomplete="off" type="date" name="wrk_repair" placeholder="일자 선택"/>
								</div>
							</li>
							<li>
								<p>수리내역</p>
								<div>
									<input autocomplete="off" type="text" name="wrk_history" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>확인여부</p>
								<div>
									<select name="wrk_confirm">
										<option value="">선택</option>
										<option value="1">확인중</option>
										<option value="2">승인불가</option>
										<option value="3">확인완료</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>작업시간<span class="ess"></span></p>
								<div>
									<input autocomplete="off" type="datetime-local" name="wrk_hour" placeholder="일자 선택" required="required"/>
								</div>
							</li>
							<li class="required">
								<p>작업종료</p>
								<div>
									<input autocomplete="off" type="datetime-local" name="wrk_end" placeholder="일자 선택" />
								</div>
							</li>
							<li class="required">
								<p>작업구분<span class="ess"></span></p>
								<div>
									<select name="wd_seq" required="required">
									</select>
								</div>
							</li>
							<li class="required">
								<p>작업책임자<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="per_team">
									<input type="hidden" name="per_seq">
									<input type="text" name="per_name" placeholder="검색하기" class="readonly" required="required" readonly/>
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>

							<li class="li_big">
								<p>작업내역</p>
								<div>
									<textarea name="wrk_work">1.시설명 : &#10;2.위치 : &#10;3.고장내역 : &#10;4.작업내역 : </textarea>
								</div>
							</li>
						</ul>
					</div>
					<div class="tb">
						<div class="table table_btn">
							<table class="tbl_work">
								<colgroup>
									<col width="">
									<col width="8%">
									<col width="">
									<col width="">
									<col width="">
									<col width="">
									<col width="8%">
									<col width="8%">
									<col width="">
								</colgroup>
								<thead>
									<tr>
										<th>소속</th>
										<th>근무자</th>
										<th>콜근무출발</th>
										<th>시작시간</th>
										<th>종료시간</th>
										<th>콜근무완료</th>
										<th>총근무시간</th>
										<th>지급액</th>
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
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a> 
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //근무 view -->

<!-- 보고서 확인하기 -->
<div class="dialog pop_up_page_type1 pop_up_report" id="work_manag_report">
	<div class="overlay"></div>
	<div class="content">
		<div class="info">
			<p>콜관리 > <span>콜관리 보고서</span></p>
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<div class="pop_print">
				<div class="top">
					
					<div class="title">
						<h2>콜 근 무 일 지</h2>
					</div>
				</div>
				<div class="box_info">
					<div class="info_confirm">
						<table>
							<tr>
								<td rowspan="2">결재</td>
								<td>담 당</td>
								<td>과/차장</td>
								<td>부단장</td>
								<td>단장</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="info_date">
					일 자 : <span>2022년 08월 30일 (화요일)</span>
				</div>
				<div class="tb">
					<div class="table">
						<table>
							<colgroup>
								<col width="">
								<col width="">
								<col width="">
								<col width="">
								<col width="">
								<col width="">
								<col width="35%">
								<col width="">
							</colgroup>
							<thead>
								<tr>
									<th>소속</th>
									<th>직급</th>
									<th>성명</th>
									<th>근무시간</th>
									<th>작업/이동</th>
									<th>지급액</th>
									<th>작업내용</th>
									<th>비고</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						
					</div>
					<div class="worker">
						<p>작업자 : <span>박용훈</span></p>
					</div>
				</div>
			</div>
		</div>
		<div class="down_area">
			<a class="btn pdf_down sky" data-filename="콜관리보고서">Pdf 다운로드</a>
		</div>
	</div>
</div>
<!-- //보고서 확인하기 -->

<%@ include file='tail.jsp' %>
<script src="${resourcePath}/js/work_management.js"></script>
