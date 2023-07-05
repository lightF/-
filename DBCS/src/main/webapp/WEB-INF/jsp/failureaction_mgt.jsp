<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.jsp" %>


<!--    여기부터 각페이지 본문       -->
<div class="cont failureaction">
	<div class="title">
		<p>고장조치관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="action_search" action="" method="post">
				<input type="hidden" name="column" value="og_seq">
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
					<li class="calc">
						<p>사업단</p>
						<div class="sch_inp">
							<input type="hidden" name="og_name">
							<input type="text" name="og_name_text" placeholder="검색" readonly>
							<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="calc">
						<p>구분</p>
						<div>
							<select name="at_division">
								<option value="">선택</option>
								<option value="1">주간</option>
								<option value="2">야간</option>
							</select>
						</div>
					</li>
					<li class="calc">
						<p>처리여부</p>
						<div>
							<select name="prc_seq">
								
							</select>
						</div>
					</li>

				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-type="new" data-dialog="failurea_log_view">조치등록<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table">
				<table class="action_list">
					<thead>
						<tr>
							<th data-column="og_name" data-order="ASC">사업단</th>
							<th data-column="at_daily" data-order="ASC">일자</th>
							<th data-column="at_division" data-order="ASC">구분</th>
							<th data-column="total" data-order="ASC">총 발생</th>
							<th data-column="finish" data-order="ASC">처리완료</th>
							<th data-column="not_take" data-order="ASC">미처리</th>
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
<!--  //  여기까지 각페이지 본문       -->



<!-- 조치 상세 -->
<div class="dialog failurea_pop" id="failurea_log_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>고장조치 관리 > <span>고장조치 등록</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="action_detail" method="post">
				<input type="hidden" name="seq">
				<div class="log_box" id="tableData">
					<div class="ul_style">
						<ul class="wrap1">
							<li class="required">
								<p>사업단<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="og_seq" class="group_seq">
									<input type="text" name="og_name" class="group_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p></p>
								<div>
								</div>
							</li>
							<li class="required">
								<p>근무일자</p>
								<div>
									<input type="date" name="at_date" placeholder="일자 선택">
								</div>
							</li>
							<li class="required">
								<p>구분</p>
								<div>
									<select name="at_division">
										<option value="">선택</option>
										<option value="1">주간</option>
										<option value="2">야간</option>

									</select>
								</div>
							</li>
							<li class="required">
								<p>지사<span class="ess"></span></p>
								<div>
									<select name="brc_seq">
										
									</select>
								</div>
							</li>
							<li class="required">
								<p>장소</p>
								<div>
									<input type="text" name="at_place" placeholder="장소 입력">
								</div>
							</li>
							<li class="required">
								<p>설비구분<span class="ess"></span></p>
								<div>
									<select name="sys_seq" required="required">
										
									</select>
								</div>
							</li>
							<li class="required">
								<p>기기명<span class="ess"></span></p>
								<div>
									<input type="text" name="at_device" placeholder="기기명 입력" required="required">
								</div>
							</li>
							<li class="required">
								<p>고장발생시각<span class="ess"></span></p>
								<div>
									<input type="datetime-local" id="meeting-time" name="at_occur" required="required">
								</div>
							</li>
							<li class="required">
								<p>고장접수시각<span class="ess"></span></p>
								<div>
									<input type="datetime-local" id="meeting-time" name="at_receipt"  required="required">
								</div>
							</li>
							<li class="required">
								<p>고장내역</p>
								<div>
									<input type="text" name="at_history" placeholder="고장내역 입력">
								</div>
							</li>
							<li class="required">
								<p>조치구분</p>
								<div>
									<select name="at_action">
										<option value="">선택</option>
										<option value="1">조치</option>
										<option value="2">미조치</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>처리구분</p>
								<div>
									<select name="prc_seq">
									
									</select>
								</div>
							</li>
							<li class="required">
								<p>처리자</p>
								<div>
									<select name="at_manager">
										<option value="">선택</option>
										<option value="1">콜근무자</option>
										<option value="2">당직자</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>조치시작시각</p>
								<div>
									<input type="datetime-local" id="meeting-time" name="at_start" value="">
								</div>
							</li>
							<li class="required">
								<p>조치완료시각</p>
								<div>
									<input type="datetime-local" id="meeting-time" name="at_finish" value="0">
								</div>
							</li>
							<li class="required">
								<p>조치내역</p>
								<div>
									<input type="text" name="at_content" placeholder="조치내역 입력">
								</div>
							</li>
							<li class="required">
								<p>사용부품</p>
								<div>
									<input type="text" name="at_part" placeholder="사용부품 입력">
								</div>
							</li>
							<li class="required">
								<p>미조치사유</p>
								<div>
									<input type="text" name="at_reason" placeholder="미조치사유 입력">
								</div>
							</li>
							<li class="required">
								<p>수리자</p>
								<div class="sch_inp">
									<input type="hidden" name="at_repair">
									<input type="text" name="at_repair_text" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
						</ul>
						<div class="tb">
							<div class="table row">
								<form name="tbl_form" method="post">
									<input type="hidden" name="column" value="at_action">
									<input type="hidden" name="order" value="DESC">
								
									<table class="tbl_detail">
										<thead>
											<tr>
												<th data-column="at_action" data-order="ASC">조치구분</th>
												<th data-column="og_name" data-order="ASC">지사</th>
												<th data-column="sys_seq" data-order="ASC">설비</th>
												<th data-column="at_place" data-order="ASC">장소</th>
												<th data-column="at_device" data-order="ASC">기기명</th>
												<th data-column="at_receipt" data-order="ASC">접수시간</th>
												<th data-column="at_history" data-order="ASC">고장내역</th>
												<th data-column="at_content" data-order="ASC">조치내역</th>
												<th data-column="per_name" data-order="ASC">작성자</th>
											</tr>
										</thead>
										<tbody>
											<!--
											<tr>
												<input type="hidden" name="seq[]" value="">
												<td>
													<select name="at_action[]">
														<option value="">선택</option>
														<option value="1">조치</option>
														<option value="2">미조치</option>
													</select>
												</td>
												<td><input type="text" name="og_name[]" placeholder="입력하세요.">
												</td>
												<td>
													<select name="sys_seq[]">
													
													</select>
												</td>
												<td><input type="text" name="at_place[]" placeholder="입력하세요.">
												</td>
												<td><input type="text" name="at_device[]" placeholder="입력하세요.">
												</td>
												<td><input type="datetime-local" name="at_receipt[]" placeholder="입력하세요.">
												</td>
												<td><input type="text" name="at_history[]" placeholder="입력하세요.">
												</td>
												<td><input type="text" name="at_content[]" placeholder="입력하세요.">
												</td>
												<td><input type="text" name="per_name[]" placeholder="입력하세요.">
												</td>
											</tr>
											-->
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="btn_area">
					<button class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //조치 상세 -->


<!-- 조치 보고서 확인 -->
<div class="dialog pop_up_report " id="failurea_report">
	<div class="overlay"></div>
	<div class="content">
		<div class="info">
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="action_report"  method="post">
				<input type="hidden" name="og_seq">
				<input type="hidden" name="" value="">
				<input type="hidden" name="" value="">
				<div class="pop_print">
					<div class="title">
						<h2>설비별고장수리현황</h2>
					</div>
					<div class="table report_type">
						<table class="">
							<thead>
								<tr>
									<th rowspan="2">사업단</th>
									<th rowspan="2">설비명</th>
									<th rowspan="2">장소</th>
									<th rowspan="2">기기명</th>
									<th rowspan="2">고장일시</th>
									<th rowspan="2">접수일시</th>
									<th rowspan="2">고장내역</th>
									<th rowspan="2">수리완료시간</th>
									<th rowspan="2">총 고장시간</th>
									<th colspan="2">수리 및 미조치 내역</th>
									<th rowspan="2">수리자</th>
								</tr>
								<tr>
									<th>수리완료내역</th>
									<th>미조치사유</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				
			</form>
		</div>
		<div class="down_area">
			<a class="btn pdf_down sky" data-filename="설비별고장수리현황">Pdf 다운로드</a>
		</div>
	</div>
</div>
<!-- 조치 보고서 확인 -->



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/failureaction_mgt.js"></script>