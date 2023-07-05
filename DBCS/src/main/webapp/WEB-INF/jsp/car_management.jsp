<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--		여기부터 각페이지 본문			 -->
<div class="cont car_management">
	<div class="title">
		<p>차량관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="car_man_search" method="post">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="column" value="update_date">
				<input type="hidden" name="order" value="DESC">
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
							<input type="hidden" name="org_name">
							<input type="text" name="org_name_text" placeholder="검색하세요." readonly/>
							<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="calc">
						<p>차량번호</p>
						<div>
							<input type="text" name="ve_number" placeholder="입력하세요.">
						</div>
					</li>
					<li class="calc">
						<p>검사여부</p>
						<div>
							<select name="ve_yn">
								<option value="">전체</option>
								<option value="1">차량검사</option>
								<option value="2">완료</option>
							</select>
						</div>
					</li>
					<li class="calc">
						<p>사용상태</p>
						<div>
							<select name="ve_status">
								<option value="">전체</option>
								<option value="1" selected>가용</option>
								<option value="2">불용</option>
							</select>
						</div>
					</li>
					
				</ul>
				<div class="btn_area">
					<button class="btn">검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky" data-dialog="car_manag_view">차량 등록<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="tb_scroll">
				<table>
					<thead>
						<tr>
							<th data-column="group_name" data-order="ASC">사업단</th>
							<th data-column="check_name" data-order="ASC">점검팀</th>
							<th data-column="ve_model" data-order="ASC">차종</th>
							<th data-column="ve_name" data-order="ASC">차명</th>
							<th data-column="ve_number" data-order="ASC">차량번호</th>
							<th data-column="update_date" data-order="DESC" class="sort">등록일</th>
							<th data-column="ve_year" data-order="ASC">년식</th>
							<th data-column="ve_type" data-order="ASC">차량구분</th>
							<th data-column="ve_yn" data-order="ASC">차량검사</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="tb_bottom">
				<div class="slt">
					<select name="row">
						<option value="20" selected>20개씩 보기</option>
						<option value="50">50개씩 보기</option>
						<option value="100">100개씩 보기</option>
						<option value="500">500개씩 보기</option>
					</select>
				</div>
				<div class="count">
					<p><span></span>건이 검색되었습니다.</p>
				</div>
				<ul class="pagination">
				</ul>
			</div>
			<div class="down_area">
				<a class="btn list_xlsx sky">Xlsx 다운로드</a>
			</div>
		</div>
	</div>
</div>
<!--	//	여기까지 각페이지 본문			 -->



<!-- 차량 등록 -->
<div class="dialog dialog_small pop_up_page_type1" id="car_manag_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>차량관리 > <span>차량 등록</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="car_vehicle_detail" id="car_vehicle_detail" method="post" enctype="multipart/form-data">
				<input type="hidden" name="seq" value="">
				<input type="hidden" name="ve_creater" value="">
				<div class="log_box">
					<div class="ul_style clearfix">
						<ul class="wrap1 clearfix">
							<li class="required">
								<p>사업단<span class="ess"></span></p>
								<div>
									<input type="hidden" name="og_seq" class="group_seq" value="" required>			
									<input type="text" name="og_name" class="group_name" value="" placeholder="점검팀 선택시 자동입력" readonly>
								</div>
							</li>
							<li class="required">
								<p>차량번호(구 등록번호)<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="text" name="ve_number" placeholder="입력하세요." required>
								</div>
							</li>
							<li>
								<p>차명<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="text" name="ve_name" placeholder="입력하세요." required>
								</div>
							</li>
							<li class="required">
								<p>차종<span class="ess"></span></p>
								<div>
									<select name="ve_model" required="required">
										<option value="">선택</option>
										<option value="1">승용</option>
										<option value="2">승합</option>
										<option value="3">화물</option>
										<option value="4">기타</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>년식<span class="ess"></span></p>
								<div>
									<input type="number" name="ve_year" placeholder="입력하세요." required="required" min="0" max="9999" required="required"/>
								</div>
							</li>
							<li class="required">
								<p>차대번호<span class="ess"></span></p>
								<div>
									<input type="text" name="ve_identify" placeholder="입력하세요." required="required"/>
								</div>
							</li>
							<li>
								<p>점검팀</p>
								<div class="sch_inp">
									<input type="hidden" name="pname" value=""/>
									<input type="hidden" name="pcode" value=""/>
									<input type="hidden" name="ve_check" value=""/>
									<input type="text" name="ve_check_name" value="" placeholder="검색하기" readonly/>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li>
								<p>점검팀인수일</p>
								<div>
									<input type="date" name="check_date" placeholder="일자 선택"/>
								</div>
							</li>
							<li>
								<p>수임자</p>
								<div class="sch_inp">
									<input type="hidden" name="ve_delegate"/>
									<input type="text" name="ve_delegate_name" placeholder="검색하기" readonly />
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li>
								<p>수임자인수일</p>
								<div>
									<input type="date" name="delegate_date" placeholder="일자 선택"/>
								</div>
							</li>
							<li class="required">
								<p>검사유효기간</p>
								<div>
									<input type="date" name="ve_expiry" placeholder="일자 선택">
								</div>
							</li>
							<li>
								<p>주행거리</p>
								<div>
									<input type="text" name="ve_drive" placeholder="자동 입력" readonly/>
								</div>
							</li>
							<li class="required">
								<p>설비</p>
								<div>
									<select name="sys_seq">
										<option value="">선택</option>
										<!--
										<option value="1">대외지원_수정</option>
										<option value="2">업무지원_수정</option>
										<option value="3">고장수리_수정</option>
										<option value="4">TCS/축중기_수정</option>
										<option value="5">FTMS</option>
										<option value="6">전송_수정</option>
										<option value="7">선로_수정</option>
										<option value="8">하이패스_수정</option>
										<option value="9">TCS/하이패스</option>
										<option value="10">자가망</option>
										<option value="1">제한/면탈</option>
										<option value="12"1>감독지원</option>
										<option value="13">단장</option>
										<option value="14">부대/대외</option>
										<option value="15">네트워크</option>
										<option value="16">장대터널</option>
										<option value="17">대외ITS</option>
										<option value="18">본사</option>
										<option value="19">교통/주차</option>
										<option value="20">원톨링</option>
										<option value="21">스마트톨링</option>-->
									</select>
								</div>
							</li>
							<li class="required">
								<p>프로젝트명<span class="ess"></span></p>
								<div>
									<input type="text" name="ve_project" placeholder="입력하세요." required="required" />
								</div>
							</li>
							<li class="required">
								<p>연료<span class="ess"></span></p>
								<div>
									<select name="ve_fuel" required="required">
										<option value="">선택</option>
										<!--<option value="">휘발류</option>
										<option value="">등유</option>
										<option value="">LPG</option>-->
									</select>
								</div>
							</li>
							<li class="required">
								<p>차량구입일자<span class="ess"></span></p>
								<div>
									<input type="date" name="ve_buy" placeholder="일자 선택" required="required" />
								</div>
							</li>
							<li>
								<p>차량구분</p>
								<div>
									<select name="ve_type">
										<option value="">선택</option>
										<option value="1">법인</option>
										<option value="2">임차</option>
										<option value="3">동원</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>용도<span class="ess"></span></p>
								<div>
									<input type="text" name="ve_use" placeholder="입력하세요." required="required" />
								</div>
							</li>
							<li>
								<p>무료통행구간</p>
								<div>
									<input type="text" name="ve_free" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>통행표지번호</p>
								<div>
									<input type="text" name="ve_pass" placeholder="입력하세요." />
								</div>
							</li>
							<li>
								<p>긴급차량지정번호</p>
								<div>
									<input type="text" name="ve_urgent" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>지정일자</p>
								<div>
									<input type="date" name="ve_appoint" placeholder="일자 선택" />
								</div>
							</li>
							<li>
								<p>경광등</p>
								<div>
									<select name="ve_lightbar">
										<option value="">선택</option>
										<option value="1">유</option>
										<option value="2">무</option>
									</select>
								</div>
							</li>
							<li>
								<p>경찰청</p>
								<div>
									<input type="text" name="ve_police" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>블랙박스설치</p>
								<div>
									<select name="ve_recorder">
										<option value="">선택</option>
										<option value="1">유</option>
										<option value="2">무</option>
									</select>
								</div>
							</li>
							<li>
								<p>블랙박스일련번호</p>
								<div>
									<input type="text" name="recorder_num" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>GPS설치</p>
								<div>
									<select name="ve_gps">
										<option value="">선택</option>
										<option value="1">유</option>
										<option value="2">무</option>
									</select>
								</div>
							</li>
							<li>
								<p>GPS일련번호</p>
								<div>
									<input type="text" name="gps_num" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>리프트설치</p>
								<div>
									<select name="ve_lift">
										<option value="">선택</option>
										<option value="1">유</option>
										<option value="2">무</option>
									</select>
								</div>
							</li>
							<li>
								<p>차량사용상태</p>
								<div>
									<select name="ve_status">
										<option value="">선택</option>
										<option value="1">가용</option>
										<option value="2">불용</option>
									</select>
								</div>
							</li>
							
							<li class="required">
								<p>작성자1<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="ve_writer1" placeholder="검색하기" required="required" />
									<input type="text" name="ve_writer1_name" placeholder="검색하기" required="required" readonly/>
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							
							<li>
								<p>작성자2</p>
								<div class="sch_inp">
									<input type="hidden" name="ve_writer2" placeholder="검색하기"/>
									<input type="text" name="ve_writer2_name" placeholder="검색하기"readonly/>
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							
							<li>
								<p>작성자3</p>
								<div class="sch_inp">
									<input type="hidden" name="ve_writer3" placeholder="검색하기" />
									<input type="text" name="ve_writer3_name" placeholder="검색하기" readonly />
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							
							<li>
								<p>작성자4</p>
								<div class="sch_inp">
									<input type="hidden" name="ve_writer4" placeholder="검색하기" />
									<input type="text" name="ve_writer4_name" placeholder="검색하기" readonly/>
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="w100">
								<p>비고</p>
								<div>
									<input type="text" name="ve_note" placeholder="입력하세요."/>
								</div>
							</li>
							<li class="img fl_right">
								<p>차량앞면</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="f_seq1" class="file_input">
									<label for="f_seq1"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>차량뒷면</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="f_seq2" class="file_input">
									<label for="f_seq2"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>차량옆면</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="f_seq3" class="file_input">
									<label for="f_seq3"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="tb">
					<div class="table">
						<table>
							<colgroup>
								<col width="6%">
								<col width="6%">
								<col width="16%">
								<col width="40%">
								<col width="">
							</colgroup>
							<thead>
								<tr>
									<th>순번</th>
									<th>구분</th>
									<th>변경일</th>
									<th>변경사항</th>
									<th>근거</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<input type="hidden" name="vi_seq[]">
									<td><div>자동작성</div></td>
									<td><div>자동작성</div></td>
									<td><div>자동작성</div></td>
									<td><div>자동작성</div></td>
									<td>
										<input type="text" name="vl_basis[]" value="" placeholder="입력하세요."/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="btn_area">
					<button type="button" class="btn btn_save" ><i class="ri-check-line"></i>저장</button>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a> 
				</div>
			</form>
		</div>
	</div>
</div>
<!-- // 차량 등록  -->
<%@ include file='tail.jsp' %>
<script src="${resourcePath}/js/car_management.js"></script>