<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.jsp" %>

<div class="m_cont">
	<div class="m_broken_write write">
		<div class="top_area">
			<div class="top_tit">
				<p>고장접수관리 > <span>고장등록</span></p>
				<a class="btn gray back" href="/DBCS/faultreception_mgt"><i class="ri-arrow-go-back-line"></i></a>
			</div>
		</div>
		<div class="body">
			<form name="breakdown_detail" action="" method="">
				<input type="hidden" name="seq" value="">
				<div class="wrap">
					<p>기본정보</p>
					<div class="ul_style">
						<ul>
							<li class="">
								<p>고장번호</p>
								<div>
									<input type="text" name="bk_code" placeholder="" readonly>
								</div>
							</li>
							<li class="">
								<p>일련번호</p>
								<div>
									<input type="text" name="bk_number"  placeholder="">
								</div>
							</li>
							<li class="required">
								<p>기기명<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="dc_seq" required>
									<input type="text" name="dc_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="equip_sch">
										<i class="ri-search-line"></i>
									</a>
								</div>
							</li>
							<li class="">
								<p>위치</p>
								<div>
									<input type="text" name="dc_location" placeholder="자동생성" readonly>
								</div>
							</li>
							<li class="required">
								<p>실고장일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_faulty" required>
								</div>
							</li>
							<li class="required">
								<p>접수일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_receipt" required>
								</div>
							</li>
							<li class="required">
								<p>조치시작일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_start" required>
								</div>
							</li>
							<li class=" required">
								<p>조치완료일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_finish" required>
								</div>
							</li>
							<li class=" required">
								<p>고장시간<span class="ess"></span></p>
								<div>
									<input type="text" name="bk_hour" placeholder="조치완료일시-접수일시 계산." required readonly>
								</div>
							</li>
							<li class="">
								<p>수리비</p>
								<div>
									<input type="text" class="input_number" name="bk_cost" placeholder="입력하세요.">
								</div>
							</li>
							<li class="">
								<p>작업구분</p>
								<div>
									<select name="bk_work">
										<option value="">선택</option>
										<option value="1">점검시</option>
										<option value="2">주간콜</option>
										<option value="3">야간콜</option>
										<option value="4">유선</option>
									</select>
								</div>
							</li>
							<li class="">
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
							<li class="">
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
								<div class="sch_inp">
									<input type="hidden" name="per_seq" required>
									<input type="text" name="per_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="person_sch">
										<i class="ri-search-line"></i>
									</a>
								</div>
							</li>
							<li class="img">
								<p>증빙자료1</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="i_seq1" class="file_input">
									<label for="i_seq1"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>증빙자료2</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="i_seq2" class="file_input">
									<label for="i_seq2"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li>
								<p>특이사항</p>
								<div>
									<input type="text" name="bk_unique" placeholder="입력하세요."> 
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="wrap">
					<p class="left">작업자</p>
					<a class="btn add_btn" data-type="1">+</a>
					<div class="tb">
						<div class="table table_btn">
							<table class="tb_1">
								<colgroup>
									<col width="35%">
									<col width="">
									<col width="30px">
								</colgroup>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="wrap">
					<p class="left">구성부</p>
					<a class="btn add_btn" data-type="2">+</a>
					<div class="tb">
						<div class="table table_btn">
							<table class="tb_2">
								<colgroup>
									<col width="35%">
									<col width="">
									<col width="30px">
								</colgroup>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="btn_area">
					<button class="btn save btn_save" data-save="tmp"><i class="ri-check-line"></i>저장</button>
				</div>

				<div class="wrap wrap_etc">
					<p class="left">저장품내역</p>
					<a class="btn add_btn" data-type="3">+</a>
					<div class="tb">
						<div class="table table_btn">
							<table class="tb_3">
								<colgroup>
									<col width="35%">
									<col width="">
									<col width="30px">
								</colgroup>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="wrap wrap_etc">
					<p class="left">KHC예비품/재료비</p>
					<a class="btn add_btn" data-type="4">+</a>
					<div class="tb">
						<div class="table table_btn">
							<table class="tb_4">
								<colgroup>
									<col width="35%">
									<col width="">
									<col width="30px">
								</colgroup>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="btn_area">
					<button class="btn save btn_save" data-save="save"><i class="ri-check-line"></i>저장</button>
					<a class="btn gray close" href="${contextPath}/faultreception_mgt"><i class="ri-close-line"></i>취소</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
			
	</div>
</div>




<%@ include file="tail.jsp" %>
<script src="${resourcePath}/js/mobile/faultreception_mgt_write.js"></script>
