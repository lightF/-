<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--    여기부터 각페이지 본문       -->
<div class="cont facility">
	<div class="title">
		<p>기기관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="device_search" method="post">
				<input type="hidden" name="column" value="seq">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="section" value="">

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
					<li>
						<p>설치위치</p>
						<div class="">
							<input type="text" name="dc_position" placeholder="입력하세요." >
						</div>
					</li>
					<li>
						<p>표준명</p>
						<div class="sch_inp">
							<input type="hidden" name="ds_name" >
							<input type="text" name="ds_name_text" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="standard1_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li>
						<p>관리팀</p>
						<div class="sch_inp">
							<input type="hidden" name="dc_team" >
							<input type="text" name="dc_team" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li>
						<p>상태</p>
						<div>
							<select name="dc_status">
								<option value="3" selected>상태없음</option>
								<option value="1">이전</option>
								<option value="2">철거</option>
							</select>
						</div>
					</li>
					<!-- <li class="sch_btn">
						
					</li> -->
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search" >검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-dialog="facility_view" data-type="new">기기등록<i class="ri-user-add-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table">
				<table class="facility_list">
					<colgroup>
						<col width="29%">
						<col width="">
						<col width="">
						<col width="">
						<col width="">
						<col width="">
					</colgroup>
					<thead>
						<tr>
							<th data-column="dc_position" data-order="ASC">설치위치</th>
							<th data-column="seq" data-order="DESC" class="sort">기기번호</th>
							<!--<th data-column="dc_location" data-order="ASC">위치명</th>-->
							<th data-column="dc_name" data-order="ASC">기기명</th>
							<th data-column="ds_name" data-order="ASC">표준명</th>
							<th data-column="dc_team" data-order="ASC">관리팀</th>
							<th data-column="dc_current" data-order="ASC">설치일자</th>
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
					<p><span class="count_total"></span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">
				
				</ul>
			</div>
		</div>
	</div>
</div>


<!-- 기기 view -->
<div class="dialog facility_pop" id="facility_view">
	<div class="overlay"></div>
	<div class="content pdf_print">
		<div class="top">
			<p>기기 관리 > <span>기기 등록</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="device_detail" method="post" enctype="multipart/form-data" id="device_detail">
				<input type="hidden" name="seq" value="">
				<div class="log_box pop_print">
					<div class="ul_style">
						<ul class="wrap1">
							<li class="required">
								<p>기기명<span class="ess"></span></p>
								<div>
									<input type="text" name="dc_name" placeholder="입력하세요." value="" required >
								</div>
							</li>
							<li class="required">
								<p>표준명<span class="ess"></span></p>
								<div class="sch">
									<input type="hidden" name="ds_seq1" >
									<input type="text" name="ds_seq1_text" placeholder="검색하세요." value="" readonly>
									<a class="btn" data-dialog="standard1_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>표준명2<span class="ess"></span></p>
								<div class="sch">
									<input type="hidden" name="ds_seq2" placeholder="입력하세요." required >
									<input type="text" name="ds_seq2_text" placeholder="입력하세요."value="" readonly >
									<a class="btn" data-dialog="standard2_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>세부기기명</p>
								<div>
									<input type="text" name="dc_device" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>설치위치<span class="ess"></span></p>
								<div>
									<input type="text" name="dc_position" placeholder="입력하세요." value="" required >
								</div>
							</li>
							<li class="required">
								<p>설치사<span class="ess"></span></p>
								<div class="sch">
									<input type="hidden" name="dc_install" required >
									<input type="text" name="dc_install_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="cmt_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<!--
							<li class="required">
								<p>위치명<span class="ess"></span></p>
								<div>
									<input type="text" name="dc_location" placeholder="입력하세요." value="" required >
								</div>
							</li>
							-->
							<li class="required">
								<p>제조업체<span class="ess"></span></p>
								<div class="sch">
									<input type="hidden" name="dc_produce"  required >
									<input type="text" name="dc_produce_name" placeholder="검색하기"readonly>
									<a class="btn" data-dialog="cmt_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>최초설치일<span class="ess"></span></p>
								<div>
									<input type="date" name="dc_first" placeholder="일자 선택" required >
								</div>
							</li>
                            <li class="required">
								<p>공급업체<span class="ess"></span></p>
								<div class="sch">
									<input type="hidden" name="dc_supply" required >
									<input type="text" name="dc_supply_name" placeholder="검색하기" readonly >
									<a class="btn" data-dialog="cmt_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>일련번호</p>
								<div>
									<input type="text" name="dc_number" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>제작일<span class="ess"></span></p>
								<div>
									<input type="date" name="dc_create" placeholder="일자 선택" required >
								</div>
							</li>
							<li class="required">
								<p>관리번호</p>
								<div>
									<input type="text" name="dc_control" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>취득일<span class="ess"></span></p>
								<div>
									<input type="date" name="dc_take" placeholder="일자 선택" required >
								</div>
							</li>
							<li class="required">
								<p>내용연수</p>
								<div>
									<input type="text" name="dc_years" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>하자기간</p>
								<div>
									<input type="text" name="dc_defect" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>하자만료일<span class="ess"></span></p>
								<div>
									<input type="date" name="dc_expiry" placeholder="일자 선택" required>
								</div>
							</li>
							<li class="required">
								<p>모델명</p>
								<div>
									<input type="text" name="dc_model" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>제원</p>
								<div>
									<input type="text" name="dc_data" placeholder="입력하세요." value="">
								</div>
							</li>
							<li>
								<p>사용전원</p>
								<div>
									<input type="text" name="dc_volt" placeholder="입력하세요.">
								</div>
							</li>
							<li>
								<p>구입가격</p>
								<div>
									<input type="text" name="dc_price" placeholder="입력하세요.">
								</div>
							</li>
							<li class="required">
								<p>서비스팀 <span class="ess"></span></p>
								<div class="sch">
									<input type="hidden" name="dc_team" required >
									<input type="text" name="dc_team_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required"></li>
							
							
							<li class="img">
								<p>사진 </p>
								<div><!--이미지 있을경우 upload-->
									<input type="file" name="upload[]" id="fa_file">
									<label for="fa_file"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<!--
									<img src="" >
									<a class="file_del"><i class="ri-close-line"></i></a>
									-->
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="required">
								<p>현위치설치일<span class="ess"></span></p>
								<div>
									<input type="date" name="dc_current" placeholder="일자 선택" required >
								</div>
							</li>
							<li class="mr0">
								<p>규격</p>
								<div>
									<input type="text" name="dc_norm" placeholder="입력하세요.">
								</div>
							</li>
							<li class="required">
								<p>철거여부</p>
								<div>
									<select name="dc_remove" >
										<option value="0">선택</option>
										<option value="1">사용중</option>
										<option value="2">철거</option>
									</select>
								</div>
							</li>
							<li class="mr0">
								<p>비고</p>
								<div>
									<input type="text" name="dc_note" placeholder="입력하세요.">
								</div>
							</li>
						</ul>
					</div>
                    <div class="tb">
						<div class="tit">
							<p>이전 및 철거기록</p>
						</div>
						<div class="table table_btn">
							<table class="pop_facility">
								<colgroup>
									<col width="">
									<col width="">
									<col width="25%">
									<col width="80px">
									<col width="80px">
									<col width="">
									<col width="">
									<col width="">
									<col width="">
									<col width="">
								</colgroup>
								<thead>
									<tr> 
										<th>이전일</th>
										<th>이전위치</th>
										<th>설치위치</th>
										<th>구분</th>
										<th>승인</th>
										<th>설치자</th>
										<th>설치부서</th>
										<th>진행상황</th>
										<th>설치건명</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="dh_seq[]" value="">
											<input type="hidden" name="dc_seq[]" value="">
											<input type="text" name="dh_date[]" placeholder="이전일" value="">
										</td>
										<td><input type="text" name="dh_location[]" placeholder="이전위치" value=""></td>
										<td><input type="text" name="dh_install[]" placeholder="설치위치" value=""></td>
										<td>
											<select name="dh_division[]">
												<option value="">선택</option>
												<option value="1">신규</option>
												<option value="2">철거</option>
												<option value="3">이동</option>
											</select>
										</td>
										<td>
											<select name="dh_approve[]">
												<option value="">선택</option>
												<option value="1" selected>미승인</option>
												<option value="2">승인</option>
											</select>
										</td>
										<td><input type="text" name="dh_man[]" placeholder="설치자" value=""></td>
										<td><input type="text" name="dh_team[]" placeholder="설치부서" value=""></td>
										<td><input type="text" name="dh_state[]" placeholder="진행상황" value=""></td>
										<td><input type="text" name="dh_case[]" placeholder="설치건명" value=""></td>
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
					<button class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<!--<a class="btn btn_down pdf_down sky" data-filename="기기관리">Pdf 다운로드</a>-->
					<a class="btn btn_down xlsx sky">Xlsx 다운로드</a>
					<a class="btn close"><i class="ri-close-line"></i><span>취소</span></a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i><span>삭제</span></a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //기기 view -->


<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/facility_mgt.js"></script>