<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>


<!--    여기부터 각페이지 본문       -->
<div class="cont duty">
	<div class="title">
		<p>당직일지</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="shift_search" method="post" action="" >
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
					<li class="calc">
						<p>사업단</p>
						<div class="sch_inp">
							<input type="hidden" name="og_name">
							<input type="text" name="og_name_text" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="calc">
						<p>구분</p>
						<div>
							<select name="sh_type">
								<option value="">전체</option>
								<option value="1">주간</option>
								<option value="2">야간</option>
							</select>
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-dialog="duty_view" data-type="new">당직일지작성<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table">
				<table class="shift_list">
					<thead>
						<tr>
							<th data-column="og_name" data-order="ASC">사업단</th>
							<th data-column="sh_date" data-order="ASC">일자</th>
							<th data-column="sh_type" data-order="ASC">구분</th>
							<th data-column="sh_count" data-order="ASC">근무인원</th>
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


<!-- 당직일지_view -->
<div class="dialog ception duty_pop" id="duty_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>당직일지 > <span>당직일지 작성</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="breakdown_shift_detail" method="post" enctype="multipart/form-data">
				<input type="hidden" name="seq" value="">
				<input type="hidden" name="" value="">
				<div class="log_box">
					<div class="ul_style">
						<ul class="wrap1">
							<li class="required">
								<p>사업단<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="og_seq" class="group_seq" required>
									<input type="text" name="og_name" class="group_name" value=""placeholder="검색하기" readonly>
									<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
								</div>
							</li>
                            <li class="required">
								<p>당직구분<span class="ess"></span></p>
								<div>
									<select name="sh_type" required>
										<option value="">선택</option>
										<option value="1">주간</option>
										<option value="2">야간</option>
									</select>
								</div>
							</li>
                            <li class="required">
								<p>일자<span class="ess"></span></p>
								<div>
									<input type="date" name="sh_date" placeholder="일자 선택" required>
								</div>
							</li>
                            <li class="required">
								<p>특이사항</p>
								<div>
									<input type="text" name="sh_etc" placeholder="특이사항 입력" >
								</div>
							</li>
                            <li class="required W20">
								<p>날씨</p>
								<div>
									<input type="text" name="sh_weather" placeholder="날씨 입력">
								</div>
							</li>
							<li class="required W20">
								<p>기온</p>
								<div>
									<input type="text" name="sh_temp" placeholder="기온 입력">
								</div>
							</li>
                            <li class="required W20">
								<p>풍향</p>
								<div>
									<input type="text" name="sh_wind" placeholder="풍향 입력">
								</div>
							</li>
							<li class="required W20">
								<p>풍속</p>
								<div>
									<input type="text" name="sh_speed" placeholder="풍속 입력">
								</div>
							</li>
							<li class="required W20 mr0">
								<p>습도</p>
								<div>
									<input type="text" name="sh_humid" placeholder="습도 입력">
								</div>
							</li>
							<li class="img">
								<p>증빙자료1</p>
								<div>
									<input type="file" name="upload[]" id="d_seq1" class="file_input">
									<label for="d_seq1"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>증빙자료2</p>
								<div class="">
									<input type="file" name="upload[]" id="d_seq2" class="file_input">
									<label for="d_seq2"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
						</ul>
					</div>
					<div class="tb hat">
						<div class="tit">
							<p>근무자</p>
						</div>
						<div class="table table_btn row">
							<table class="tbl_work">
								<colgroup>
									<col width="60px">
									<col width="">
									<col width="">
									<col width="">
									<col width="5%">
									<col width="8%">
									<col width="">
									<col width="">
									<col width="20%">
									<col width="">
									<col width="">
								</colgroup>
								<thead>
									<tr>
										<th>순번</th>
										<th class="required">근무형태<span class="ess"></span></th>
										<th class="required">근무장소<span class="ess"></span></th>
										<th class="required">소속<span class="ess"></span></th>
										<th class="required">직급<span class="ess"></span></th>
										<th class="required">성명<span class="ess"></span></th>
										<th class="required">근무시작시간<span class="ess"></span></th>
										<th class="required">근무종료시간<span class="ess"></span></th>
										<th>근무내용</th>
										<th>비고</th>
										<th class="tb_btn"><a class="btn add_btn" data-addlist="1">+</a></th>
									</tr>
								</thead>
								<tbody>
									<!--
									<tr>
										<td><input type="hidden" name="wk_seq[]" value=""></td>
										<td>
											<select name="wt_seq[]" >
											</select>
										</td>
										<td><input type="text" name="wk_place[]" placeholder="입력하세요." required></td>
										<td><input type="text" name="wk_team[]" placeholder="입력하세요." required></td>
										<td><input type="text" name="wk_rank[]" placeholder="입력하세요." required></td>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="per_seq[]" required>
												<input type="text" name="per_name[]" placeholder="검색" readonly>
												<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<td>
											<input type="datetime-local" name="wk_start[]" placeholder="근무시작시간" required>
										</td>
										<td>
											<input type="datetime-local" name="wk_end[]" placeholder="근무종료시간" required>
										</td>
										<td><input type="text" name="wk_content[]" placeholder="입력하세요."></td>
										<td><input type="text" name="wk_note[]" placeholder="입력하세요."></td>
										<td class="tb_btn">
											<a class="btn del_btn">-</a>
										</td>
									</tr>
									-->
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb hat type_W5">
						<div class="tit">
							<p>지시/연락사항</p>
						</div>
						<div class="table table_btn row">
							<table class="tbl_instruct">
								<thead>
									<tr>
										<th class="required">지시 및 연락처</th>
										<th class="required">접수시간</th>
										<th class="required">접수자</th>
										<th class="required">지시내용</th>
										<th>조치결과</th>
										<th class="tb_btn"><a class="btn add_btn" data-addlist="2">+</a></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="ct_seq[]" value="">
											<input type="text" name="ct_phone[]" placeholder="입력하세요." >

										</td>
										<td>
											<input type="datetime-local" id="meeting-time" name="ct_date[]" >
										</td>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="per_seq2[]">
												<input type="text" name="per_seq2_name[]" placeholder="검색" readonly>
												<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<td><input type="text" name="ct_content[]" placeholder="입력하세요."></td>
										<td><input type="text" name="ct_result[]" placeholder="입력하세요."></td>
										<td class="tb_btn">
											<a class="btn del_btn">-</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb hat type_W3">
						<div class="tit">
							<p>보고사항</p>
						</div>
						<div class="table table_btn row">
							<table class="tbl_report">
								<thead>
									<tr>
										<th class="required">보고시간</th>
										<th class="required">내용</th>
										<th class="required">통화자</th>
										<th class="tb_btn"><a class="btn add_btn" data-addlist="3">+</a></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="rp_seq[]" value="">
											<input type="datetime-local" id="meeting-time" name="rp_hour[]" value="">
										</td>
										<td><input type="text" name="rp_content[]" placeholder="입력하세요."></td>
										<td><input type="text" name="rp_caller[]" placeholder="입력하세요." ></td>
										<td class="tb_btn">
											<a class="btn del_btn">-</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="btn_area">
					<button type="button" class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
					<a class="btn close" ><i class="ri-close-line"></i>취소</a>

				</div>
			</form>
		</div>
	</div>
</div>
<!-- //당직일지_view -->


<!-- 당직일지 보고서 확인하기 -->
<div class="dialog duty_report pop_up_report" id="duty_report">
	<div class="overlay"></div>
	<div class="content">
		<div class="info">
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="breakdown_shift_report"  method="post">
				<input type="hidden" name="seq" value="">
				<input type="hidden" name="" value="">
				<div class="pop_print print_area" id="tableData">
					<div class="table wrap-top">
						<table>
							<tbody>
								<tr class="title">
									<td rowspan="2">
										<h2>특 별 근 무 일 지</h2>
										<p class="date-txt">2022.08.07 (일요일) [주간]</p>
									</td>
									<td  rowspan="2">확<br>인</td>
									<td width="164px">감독</td>
								</tr>
								<tr>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="table tb1">
						<table class="list">
							<colgroup>
								<col width="">
								<col width="120px">
								<col width="120px">
								<col width="120px">
								<col width="">
							</colgroup>
							<thead>
								<tr>
									<th rowspan="2">근무장소</th>
									<th colspan="3">근무자</th>
									<th rowspan="2">근무시간</th>
									<th rowspan="2">근무내용</th>
									<th rowspan="2">비고<br>(근무자확인)</th>
								</tr>
								<tr>
									<th>소속</th>
									<th>직급</th>
									<th>성명</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div class="table tb2">
						<p>■ 지시 및 연락사항</p>
						<table class="contact">
							<thead>
								<tr>
									<th>지시 및 연락자</th>
									<th>접수기간</th>
									<th>접수자</th>
									<th>지시내용</th>
									<th>조치결과</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="table tb3">
						<p>■ 고장 및 조치 현황</p>
						<table class="total">
							<thead>
								<tr>
									<th rowspan="2">구&nbsp;&nbsp;분</th>
									<th colspan="2">계</th>
									<th colspan="2">영업시스템</th>
									<th colspan="2">ITS</th>
									<th colspan="2">제한차량/면탈</th>
									<th colspan="2">기타</th>
									<th rowspan="2">미조치<br> 건수</th>
								</tr>
								<tr>
									<th>발생</th>
									<th>처리</th>
									<th>발생</th>
									<th>처리</th>
									<th>발생</th>
									<th>처리</th>
									<th>발생</th>
									<th>처리</th>
									<th>발생</th>
									<th>처리</th>
								</tr>
							</thead>
							<tbody>
								<tr class="total_one">
									<td>계</td>
									<td>a</td>
									<td>vb</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="table tb4">
						<p>■ 보고사항</p>
						<table class="report">
							<colgroup>
								<col width="15%">
								<col width="">
								<col width="20%">
							</colgroup>
							<thead>
								<tr>
									<th>보고시간</th>
									<th>내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</th>
									<th>통화자(소속, 직급, 성명)</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="table vertical">
						<table>
							<tbody>
								<tr>
									<th width="140px;">기타사항</th>
									<td colspan="7"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="box">
						<p>당직자 : <span>(인)</span></p>
						<p>부단장 : <span>(인)</span></p>
					</div>
					<div class="txt-foot">
						<strong>대보정보통신(주) 경기사업단장</strong>
						<span>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  (인)</span>
					</div>
				</div>
				
			</form>
		</div>
		<div class="down_area">
			<a class="btn pdf_down sky" data-filename="특별근무일지">Pdf 다운로드</a>
		</div>
	</div>
</div>
<!-- //당직일지 보고서 확인하기 -->

<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/duty_mgt.js"></script>