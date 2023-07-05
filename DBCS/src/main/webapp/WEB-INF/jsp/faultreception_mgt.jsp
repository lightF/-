<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--    여기부터 각페이지 본문       -->
<div class="cont warehouse faul_mgt">
	<div class="title">
		<p>고장접수관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="breakdown_search" method="post" action="">
				<input type="hidden" name="column" value="seq">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">
				<ul>
					<li class="date w30">
						<p>조회기간</p>
						<div class="start">
							<input type="date" name="start_date" placeholder="시작일">
						</div>
						<div class="end">
							<input type="date" name="end_date" placeholder="종료일">
						</div>
					</li>
					<li class="calc">
						<p>설비위치</p>
						<div>
							<input type="text" name="dc_location" placeholder="입력하세요.">
						</div>
					</li>
					<li class="calc">
						<p>고장번호</p>
						<div>
							<input type="text" name="bk_code" placeholder="번호 입력">
						</div>
					</li>
					<li class="calc">
						<p>고장기준</p>
						<div>
							<select name="bk_standard">
								<option value="">전체</option>
								<option value="1">단순정비</option>
								<option value="2">경정비</option>
								<option value="3">중정비</option>
								<option value="4">입고수리</option>
								<option value="5">원상복귀</option>

							</select>
						</div>
					</li>

				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-dialog="faul_mgt_re" data-type="new">고장등록<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table">
				<table class="breakdown_list">
					<colgroup>
						<col width="12%">
						<col width="10%">
						<col width="10%">
						<col width="16%">
						<col width="8%">
						<col width="30%">
						<col width="30%">
					</colgroup>
					<thead>
						<tr>
							<th data-column="bk_code" data-order="ASC">고장번호</th>
							<th data-column="bk_standard" data-order="ASC">고장기준</th>
							<th data-column="bk_receipt" data-order="ASC">접수일시</th>
							<th data-column="bk_start" data-order="ASC">조치시작일시</th>
							<th data-column="bk_hour" data-order="ASC">고장시간</th>
							<th data-column="dc_name" data-order="ASC">기기명</th>
							<th data-column="dc_location" data-order="ASC">설비위치</th>
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



<!-- 고장접수관리 상세보기 팝업 -->
<div class="dialog ception" id="faul_mgt_re">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>고장접수 관리 &gt; <span class="detail_tit">고장 등록</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="breakdown_detail" method="post" enctype="multipart/form-data">
				<input type="hidden" name="seq">

				<div class="log_box">
					<div class="ul_style">
						<ul class="wrap1">
							<li class="required">
								<p>고장번호</p>
								<div>
									<input type="text" name="bk_code" placeholder="" value="고장번호" readonly>
								</div>
							</li>
							<li class="required">
								<p>일련번호</p>
								<div>
									<input type="text" name="bk_number" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>기기명<span class="ess"></span></p>
								<div class="sch">
									<input type="hidden" name="dc_seq" required>
									<input type="text" name="dc_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="equip_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>위치</p>
								<div>
									<input type="text" name="dc_location" placeholder="자동생성" readonly>
								</div>
							</li>
							<li class="required">
								<p>실고장일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_faulty" placeholder="일자 선택" required>
								</div>
							</li>
							<li class="required">
								<p>접수일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_receipt" placeholder="일자 선택" required>
								</div>
							</li>
							<li class="required">
								<p>조치시작일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_start" placeholder="일자 선택" required>
								</div>
							</li>
							<li class="required">
								<p>조치완료일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_finish" placeholder="일자 선택" required>
								</div>
							</li>
							<li class="required">
								<p>고장시간(분)<span class="ess"></span></p>
								<div>
									<input type="text" name="bk_hour" placeholder="조치완료일시-접수일시 계산." required readonly>
								</div>
							</li>
							<li class="required">
								<p>수리비</p>
								<div>
									<input type="text" name="bk_cost" class="input_number" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>작업구분</p>
								<div>
									<select name="bk_work">
										<option value="">선택</option>
                                        <option value="1">점검시</option>
										<option value="2">주간콜</option>
										<option value="3">주간콜</option>
										<option value="4">유선</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>고장기준</p>
								<div>
									<select name="bk_standard">
										<option value="">선택</option>
                                        <option value="1">단순정비</option>
										<option value="2">경정비</option>
										<option value="3">중정비</option>
										<option value="4">입고수리</option>
										<option value="5">원상복귀</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>처리내역</p>
								<div>
									<select name="bk_process">
										<option value="">선택</option>
                                        <option value="1">부품교환</option>
										<option value="2">조정</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>접수자<span class="ess"></span></p>
								<div class="sch">
									<input type="hidden" name="per_seq"required="required">
									<input type="text" name="per_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="img">
								<p>증빙자료1</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="f_seq1" class="file_input">
									<label for="f_seq1"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>증빙자료2</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="f_seq2" class="file_input">
									<label for="f_seq2"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="w100">
								<p>특이사항</p>
								<div>
									<input type="text" name="bk_unique" placeholder="입력하세요." value="">
								</div>
							</li>
						</ul>
					</div>
                    <div class="tb tb1">
						<div class="tit">
							<p>작업자</p>
						</div>
						<div class="table table_btn">
							<table class="tbl_worker">
								<thead>
									<tr>
										<th>작업자<span class="ess"></span></th>
										<th>비고</th>
										<th class="tb_btn"><a class="btn add_btn" data-addlist="1">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb type_W5 tb2">
						<div class="tit">
							<p>구성부</p>
						</div>
						<div class="table table_btn">
							<table class="tbl_composition">
								<thead>
									<tr>
										<th>구성부</th>
										<th>현상</th>
										<th>원인</th>
										<th>조치결과</th>
										<th>비고</th>
										<th class="tb_btn"><a class="btn add_btn" data-addlist="2">+</a></th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>

						</div>
					</div>
					<div class="btn_area">
						<button type="button" class="btn btn_save" data-save="tmp"><i class="ri-check-line"></i>저장</button>
					</div>
					<div class="tb type_W9 tb3">
						<div class="tit">
							<p>저장품내역</p>
						</div>
						<div class="table table_btn">

							<table class="tbl_save">
								<thead>
									<tr>
										<th>구성부</th>
										<th>처리구분</th>
										<th>Unit Count(구)</th>
										<th>Unit Count(신)</th>
										<th>저장품명</th>
										<th>수량</th>
										<th>단가</th>
										<!--<th>입출고구분<span class="ess"></span></th>-->
										<th>비고 </th>
										<th class="tb_btn"><a class="btn add_btn" data-addlist="3">+</a></th>
									</tr>
								</thead>

								<tbody>
								</tbody>

							</table>
						</div>
					</div>
					<div class="tb type_W9 tb4">
						<div class="tit">
							<p>KHC예비품/재료비</p>
						</div>
						<div class="table table_btn">
							<table class="tbl_spare">
								<thead>
									<tr>
										<th>구성부</th>
										<th>처리구분</th>
										<th>Unit Count(구)</th>
										<th>Unit Count(신)</th>
										<th>예비품명</th>
										<th>수량</th>
										<th>단가</th>
										<th>비고 </th>
										<th class="tb_btn"><a class="btn add_btn" data-addlist="4">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>

							</table>
						</div>
					</div>
				</div>
				<div class="btn_area">
					<button type="button" class="btn btn_save" data-save="save"><i class="ri-check-line"></i>저장</button>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //고장접수관리 상세보기 팝업 -->



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/faultreception_mgt.js"></script>