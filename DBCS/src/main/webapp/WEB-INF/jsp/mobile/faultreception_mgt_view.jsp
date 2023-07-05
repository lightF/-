<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.jsp" %>

<div class="m_cont">
	<div class="m_broken_write write m_broken_view">
		<div class="top_area">
			<div class="top_tit">
				<p>고장접수관리 > <span>상세보기</span></p>
			</div>
			<a class="btn gray back" href="${contextPath}/faultreception_mgt"><i class="ri-arrow-go-back-line"></i></a>
		</div>
		<div class="body">
			<form name="breakdown_edit" action="" method="">
				<input type="hidden" name="seq" value="">
				<div class="wrap">
					<p>기본정보</p>
					<div class="ul_style">
						<ul>
							<li class="">
								<p>고장번호</p>
								<div>
									<input type="text" name="bk_code" placeholder="자동입력" readonly>
								</div>
							</li>
							<li class="">
								<p>일련번호</p>
								<div>
									<input type="text" name="bk_number"  placeholder="입력하세요." required>
								</div>
							</li>
							<li class="required">
								<p>기기명<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="dc_seq" value="">
									<input type="text" name="text" placeholder="검색하기" required="required" readonly>
									<a class="btn" data-dialog="equip_sch">
										<i class="ri-search-line"></i>
									</a>
								</div>
							</li>
							<li class="">
								<p>위치</p>
								<div>
									<input type="text" name="dc_location" placeholder="위치 자동생성" readonly>
								</div>
							</li>
							<li class="required">
								<p>실고장일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_faulty" required="required">
								</div>
							</li>
							<li class="required">
								<p>접수일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_receipt" required="required">
								</div>
							</li>
							<li class="required">
								<p>조치시작일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_start" required="required">
								</div>
							</li>
							<li class=" required">
								<p>조치완료일시<span class="ess"></span></p>
								<div>
									<input type="datetime-local" name="bk_finish" required="required">
								</div>
							</li>
							<li class=" required">
								<p>고장시간<span class="ess"></span></p>
								<div>
									<input type="text" name="bk_hour" placeholder="조치완료일시 - 접수일시 =" readonly>
								</div>
							</li>
							<li class="">
								<p>수리비</p>
								<div>
									<input type="text" name="bk_cost" placeholder="입력하세요.">
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
									<input type="hidden" name="per_seq" value="">
									<input type="text" name="text" placeholder="검색하기" required="required" readonly>
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
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>증빙자료2</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="i_seq2" class="file_input">
									<label for="i_seq2"><i class="ri-upload-fill"></i></label>
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
					<p>작업자</p>
					<div class="tb">
						<div class="table table_btn">
							<table class="tb_1">
								<tbody>
									<input type="hidden" name="bw_seq[]" value="">
									<tr>
										<th class=" required">작업자<span class="ess"></span></th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="per_seq[]" value="">
												<input type="text" name="text" placeholder="검색하기" required="required" readonly>
												<a class="btn" data-dialog="person_sch">
													<i class="ri-search-line"></i>
												</a>
											</div>
										</td>
										<th>비고</th>
										<td><input type="text" name="bw_note[]" placeholder="입력하세요."></td>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
									<tr>
										<th class="required">작업자<span class="ess"></span></th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="per_seq[]" value="">
												<input type="text" name="text" placeholder="검색하기" required="required" readonly>
												<a class="btn" data-dialog="person_sch">
													<i class="ri-search-line"></i>
												</a>
											</div>
										</td>
										<th>비고</th>
										<td><input type="text" name="bw_note[]" placeholder="입력하세요." required></td>
										<th class="tb_btn"><a class="btn del_btn">-</a></th>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="wrap">
					<p>구성부</p>
					<div class="tb">
						<div class="table table_btn">
							<table class="tb_2">
								<tbody>
									<input type="hidden" name="bc_seq[]" value="">
									<tr>
										<th class="required">구성부<span class="ess"></span></th>
										<td><input type="text" name="bc_compose[]" placeholder="입력하세요." required="required"></td>
										<th class="required">현상<span class="ess"></span></th>
										<td><input type="text" name="bc_status[]" placeholder="입력하세요." required="required"></td>
										<th class="required">원인<span class="ess"></span></th>
										<td><input type="text" name="bc_cause[]" placeholder="입력하세요." required="required"></td>
										<th class="required">조치결과<span class="ess"></span></th>
										<td><input type="text" name="bc_result[]" placeholder="입력하세요." required="required"></td>
										<th>비고</th>
										<td><input type="text" name="bc_note[]" placeholder="입력하세요."></td>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
									<tr>
										<th class="required">구성부<span class="ess"></span></th>
										<td><input type="text" name="bc_compose[]" placeholder="입력하세요." required="required"></td>
										<th class="required">현상<span class="ess"></span></th>
										<td><input type="text" name="bc_status[]" placeholder="입력하세요." required="required"></td>
										<th class="required">원인<span class="ess"></span></th>
										<td><input type="text" name="bc_cause[]" placeholder="입력하세요." required="required"></td>
										<th class="required">조치결과<span class="ess"></span></th>
										<td><input type="text" name="bc_result[]" placeholder="입력하세요." required="required"></td>
										<th>비고</th>
										<td><input type="text" name="bc_note[]" placeholder="입력하세요."></td>
										<th class="tb_btn"><a class="btn del_btn">-</a></th>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="wrap">
					<p>저장품내역</p>
					<div class="tb">
						<div class="table table_btn">
							<table class="tb_3">
								<tbody>
									<input type="hidden" name="bs_seq1[]" value="">
									<tr>
										<th class="required">구성부<span class="ess"></span></th>
										<td>
											<select name="bc_seq1[]" required="required">
												<option value="">선택</option>
												<option value="1">1</option>
												<option value="2">2</option>
											</select>
										</td>
										<th class="required">처리구분<span class="ess"></span></th>
										<td>
											<select name="bs_process1[]" required="required">
												<option value="">선택</option>
												<option value="1">유상</option>
												<option value="2">무상</option>
											</select>
										</td>
										<th>Unit Count(구)</th>
										<td><input type="text" name="bs_old1[]" placeholder="입력하세요."></td>
										<th>Unit Count(신)</th>
										<td><input type="text" name="bs_new1[]" placeholder="입력하세요."></td>
										<th class="required">부품 및 창고위치<span class="ess"></span></th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="pt_seq1[]" value="">
												<input type="text" name="text" placeholder="검색하기" required="required" readonly>
												<a class="btn" data-dialog="storage_order"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<th class="required">수량<span class="ess"></span></th>
										<td><input type="text" name="bs_count1[]" placeholder="입력하세요." required="required"></td>
										<th class="required">단가<span class="ess"></span></th>
										<td><input type="text" name="bs_price1[]" placeholder="입력하세요." required="required"></td>
										<th>비고</th>
										<td><input type="text" name="bs_note1[]" placeholder="입력하세요."></td>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
									<tr>
										<th class="required">구성부<span class="ess"></span></th>
										<td>
											<select name="bc_seq1[]" required="required">
												<option value="">선택</option>
												<option value="1">1</option>
												<option value="2">2</option>
											</select>
										</td>
										<th class="required">처리구분<span class="ess"></span></th>
										<td>
											<select name="bs_process1[]" required="required">
												<option value="">선택</option>
												<option value="1">유상</option>
												<option value="2">무상</option>
											</select>
										</td>
										<th>Unit Count(구)</th>
										<td><input type="text" name="bs_old1[]" placeholder="입력하세요."></td>
										<th>Unit Count(신)</th>
										<td><input type="text" name="bs_new1[]" placeholder="입력하세요."></td>
										<th class="required">부품 및 창고위치<span class="ess"></span></th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="pt_seq1[]" value="">
												<input type="text" name="text" placeholder="검색하기" required="required" readonly>
												<a class="btn" data-dialog="storage_order"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<th class="required">수량<span class="ess"></span></th>
										<td><input type="text" name="bs_count1[]" placeholder="입력하세요." required="required"></td>
										<th class="required">단가<span class="ess"></span></th>
										<td><input type="text" name="bs_price1[]" placeholder="입력하세요." required="required"></td>
										<th>비고</th>
										<td><input type="text" name="bs_note1[]" placeholder="입력하세요."></td>
										<th class="tb_btn"><a class="btn del_btn">-</a></th>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="wrap">
					<p>KHC예비품/재료비</p>
					<div class="tb">
						<div class="table table_btn">
							<table class="tb_4">
								<tbody>
									<input type="hidden" name="bs_seq2[]" value="">
									<tr>
										<th class="required">구성부<span class="ess"></span></th>
										<td>
											<select name="bc_seq2[]" required="required">
												<option value="">선택</option>
												<option value="1">1</option>
												<option value="2">2</option>
											</select>
										</td>
										<th class="required">처리구분<span class="ess"></span></th>
										<td>
											<select name="bs_process2[]" required="required">
												<option value="">선택</option>
												<option value="1">유상</option>
												<option value="2">무상</option>
											</select>
										</td>
										<th>Unit Count(구)</th>
										<td><input type="text" name="bs_old2[]" placeholder="입력하세요." ></td>
										<th>Unit Count(신)</th>
										<td><input type="text" name="bs_new2[]" placeholder="입력하세요."></td>
										<th class="required">부품 및 창고위치<span class="ess"></span></th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="pt_seq2[]" value="">
												<input type="text" name="text" placeholder="검색하기" required="required" readonly>
												<a class="btn" data-dialog="storage_order"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<th class="required">수량<span class="ess"></span></th>
										<td><input type="text" name="bs_count2[]" placeholder="입력하세요." required="required"></td>
										<th class="required">단가<span class="ess"></span></th>
										<td><input type="text" name="bs_price2[]" placeholder="입력하세요." required="required"></td>
										<th>비고</th>
										<td><input type="text" name="bs_note2[]" placeholder="입력하세요."></td>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
									<tr>
										<th class="required">구성부<span class="ess"></span></th>
										<td>
											<select name="bc_seq2[]" required="required">
												<option value="">선택</option>
												<option value="1">1</option>
												<option value="2">2</option>
											</select>
										</td>
										<th class="required">처리구분<span class="ess"></span></th>
										<td>
											<select name="bs_process2[]" required="required">
												<option value="">선택</option>
												<option value="1">유상</option>
												<option value="2">무상</option>
											</select>
										</td>
										<th>Unit Count(구)</th>
										<td><input type="text" name="bs_old2[]" placeholder="입력하세요." ></td>
										<th>Unit Count(신)</th>
										<td><input type="text" name="bs_new2[]" placeholder="입력하세요."></td>
										<th class="required">부품 및 창고위치<span class="ess"></span></th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="pt_seq2[]" value="">
												<input type="text" name="text" placeholder="검색하기" required="required" readonly>
												<a class="btn" data-dialog="storage_order"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<th class="required">수량<span class="ess"></span></th>
										<td><input type="text" name="bs_count2[]" placeholder="입력하세요." required="required"></td>
										<th class="required">단가<span class="ess"></span></th>
										<td><input type="text" name="bs_price2[]" placeholder="입력하세요." required="required"></td>
										<th>비고</th>
										<td><input type="text" name="bs_note2[]" placeholder="입력하세요."></td>
										<th class="tb_btn"><a class="btn del_btn">-</a></th>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="btn_area">
					<button class="btn save" data-dialog="save_pop"><i class="ri-check-line"></i>저장</button>
					<a class="btn del" data-dialog="delete_pop"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
			
	</div>
</div>




<%@ include file="tail.jsp" %>
