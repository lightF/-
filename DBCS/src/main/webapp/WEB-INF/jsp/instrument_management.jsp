<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--        여기부터 각페이지 본문             -->
<div class="cont instrument_management">
	<div class="title">
		<p>계측기관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="measure_search" method="post">
				<input type="hidden" name="column" value="seq" >
				<input type="hidden" name="order" value="DESC" >
				<input type="hidden" name="page" value="1" >

				<ul>
					<li class="calc">
						<p>사업단</p>
						<div>
							<input type="hidden" name="og_seq" value="" class="group_seq">
							<input type="text" name="og_seq_text" class="group_name" placeholder="점검팀 검색">
						</div>
					</li>
					<li class="calc">
						<p>계측기명</p>
						<div>
							<!--<input type="text" name="md_name" placeholder="입력하세요."/>-->
							<select name="md_name"></select>
						</div>
					</li>
					
					<li class="mt10 calc">
						<p>점검팀</p>
						<!--
						<div>
							<input type="text" name="ms_check" placeholder="입력하세요."/>
						</div>
						-->
						<div class="sch_inp">
							<input type="hidden" name="pname">
							<input type="hidden" name="pcode">
							<input type="hidden" name="wrk_check">
							<input type="wrk_check_text" name="check_name" placeholder="검색" class="readonly" required="required" readonly="">
							<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="mt10 calc">
						<p>상태</p>
						<div>
							<select name="status">
								<option value="">전체</option>
								<option value="1">이관</option>
								<option value="2">폐기&매각</option>
							</select>
						</div>
					</li>
					<li class="mt10 calc">
						<p>관리번호</p>
						<div class="sch_inp">
							<input type="text" name="ms_manage" placeholder="입력하세요.">
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-type="new" data-dialog="instrument_view">계측기 등록<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="tb_scroll">
				<table class="measure_list">
					<colgroup>
							<col width="6%">
							<col width="10%">
							<col width="6%">
							<col width="12%">
							<col width="12%">
							<col width="8%">
							<col width="12%">
							<col width="10%">
							<col width="14%">
							<col width="4%">
							<col width="6%">
					</colgroup>
					<thead>
						<tr>
							<th data-column="ms_manage" data-order="ASC">관리번호</th>
							<th data-column="md_seq" data-order="ASC">계측기명</th>
							<th data-column="ms_division" data-order="ASC">관리구분</th>
							<th data-column="ms_model" data-order="ASC">모델</th>
							<th data-column="act_company" data-order="ASC">제조사</th>
							<th data-column="group_name" data-order="ASC">사업단</th>
							<th data-column="check_name" data-order="ASC">점검팀</th>
							<th data-column="ms_asset" data-order="ASC">회계자산코드</th>
							<th data-column="ms_project" data-order="ASC">프로젝트코드</th>
							<th data-column="ms_cycle" data-order="ASC">검교정</th>
							<th>이력카드</th>
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
			<div class="down_area">
				<a class="btn list_xlsx sky">Xlsx 다운로드</a>
			</div>
		</div>
	</div>
</div>
<!--    //    여기까지 각페이지 본문             -->

 

<!-- 계측기등록view-->
<div class="dialog dialog_small instrument_pop pop_up_page_type1" id="instrument_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>계측기 관리 > <span class="detail_txt">계측기 등록</span></p>
			
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="measure_detail"  method="post" nctype="multipart/form-data">
				<div class="log_box">
					<div class="ul_style clearfix">
						<ul class="wrap1 clearfix">
							<li>
								<p>관리번호</p>
								<div class="sch_inp">
									<input type="text" name="ms_manage" placeholder="입력하세요."/>
								</div>
							</li>
							<li class="required">
								<p>일련번호<span ></span></p>
								<div class="sch_inp">
									<input type="text" name="seq" placeholder="" readonly >
								</div>
							</li>
							<li class="required">
								<p>계측기명<span class="ess"></span></p>
								<div>
									<select name="md_seq" required="required">
									</select>
								</div>
							</li>
							<li>
								<p>규격</p>
								<div>
									<input type="text" name="ms_stand1" placeholder="입력하세요." />
								</div>
							</li>
							<li class="required">
								<p>모델명<span class="ess"></span></p>
								<div>
									<input type="text" name="ms_model" placeholder="입력하세요." required="required"/>
								</div>
							</li>
							<li class="required">
								<p>제조회사<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="act_seq" value="">
									<input type="text" name="act_name" placeholder="검색하기" value="" required>
									<a class="btn" data-dialog="act_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li>
								<p>제조일자</p>
								<div>
									<input type="date" name="ms_create" placeholder="일자 선택" />
								</div>
							</li>
							<li>
								<p>불용일자</p>
								<div>
									<input type="date" name="ms_unuse" placeholder="일자 선택" />
								</div>
							</li>
							<li class="required">
								<p>구매일자<span class="ess"></span></p>
								<div>
									<input type="date" name="ms_buy" placeholder="일자 선택" required="required"/>
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
								<p>사업단</p>
								<div>
									<input type="hidden" name="ms_group" class="group_seq" value="">
									<input type="text" name="ms_group_name"  class="group_name" placeholder="점검팀 선택시 자동 입력" readonly/>
									<!--
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
									-->
								</div>
							</li>
							<li class="required">
								<p>점검팀<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="pname" value="">
									<input type="hidden" name="pcode" value="">
									<input type="hidden" name="ms_check" value="">
									<input type="text" name="ms_check_name" placeholder="검색하기" required="required" readonly/>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li>
								<p>규격</p>
								<div>
									<input type="text" name="ms_stand2" value="" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>부대품</p>
								<div>
									<input type="text" name="ms_access" value="" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>내용연수</p>
								<div>
									<input type="text" name="ms_years" value="" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>허용오차</p>
								<div> 
									<input type="text" name="ms_error" value="" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>취득선</p>
								<div>
									<select name="ms_account">
										<option value="">선택</option>
										<option value="1">본사</option>
										<option value="2">사업단</option>
									</select>
								</div>
							</li>
							<li>
								<p>취득금액</p>
								<div>
									<input type="text" name="ms_acq" value="" class="input_number" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>사용범위</p>
								<div>
									<input type="text" name="ms_range" value="" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>관리구분</p>
								<div>
									<select name="ms_division">
										<option value="">선택</option>
										<option value="1">고정자산</option>
										<option value="2">부외자산</option>
										<option value="3">소모품구분</option>
									</select>
								</div>
							</li>
							<li>
								<p>검교정주기</p>
								<div>
									<select name="ms_cycle">
										<option value="">선택</option>
										<option value="1">없음</option>
										<option value="2">1년</option>
										<option value="3">2년</option>
										<option value="4">3년</option>
										<option value="5">4년</option>
										<option value="6">5년</option>
									</select>
								</div>
							</li>
							<li>
								<p>기타</p>
								<div>
									<input type="text" name="ms_etc" value="" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>회계자산코드</p>
								<div>
									<input type="text" name="ms_asset" value="" placeholder="입력하세요."/>
								</div>
							</li>
							<li>
								<p>프로젝트코드</p>
								<div>
									<input type="text" name="ms_project" value="" placeholder="입력하세요."/>
								</div>
							</li>
							<li class="img">
								<p>이미지1</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="i_seq1" class="file_input">
									<label for="i_seq1"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>이미지2</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="i_seq2" class="file_input">
									<label for="i_seq2"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>이미지3</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="i_seq3" class="file_input">
									<label for="i_seq3"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>이미지4</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="i_seq4" class="file_input">
									<label for="i_seq4"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="tb">
					<div class="table table_btn">
						<table class="tbl_list1">
							<colgroup>
								<col width="">
								<col width="">
								<col width="">
								<col width="">
								<col width="">
								<col width="">
								<col width="">
								<col width="20%">
								<col width="">
								<col width="">
							</colgroup>
							<thead>
								<tr>
									<th>순번</th>
									<th>검교정번호</th>
									<th>교정항목</th>
									<th>검교정일</th>
									<th>검교정기관</th>
									<th>다음검교정일</th>
									<th>판정</th>
									<th>조치방법</th>
									<th>비고</th>
									<th class="tb_btn"><a class="btn add_btn" data-addlist="1">+</a></th>
								</tr>
							</thead>
							<tbody>
								<!--
								<tr>
									<input type="hidden" name="seq[]" value="">
									<td><div>1</div></td>
									<td>
										<input type="number" name="msd_seq[]" placeholder="자동생성" readonly/>
									</td>
									<td>
										<input type="text" name="msd_item[]" placeholder="입력하세요."/>
									</td>
									<td><input type="number" name="msd_date[]" placeholder="일자 선택" /></td>
									<td>
										<input type="text" name="msd_agency[]" placeholder="입력하세요."/>
									</td>
									<td><input type="date" name="msd_next[]" placeholder="일자 선택" /></td>
									<td>
										<div>
											<select name="msd_judge[]">
												<option value="">선택</option>
												<option value="1">합격</option>
												<option value="2">불합격</option>
											</select>
										</div>
									</td>
									<td>
										<div>
											<select name="msd_action[]">
												<option value="">선택</option>
												<option value="1">수리</option>
												<option value="2">재교정</option>
												<option value="3">폐기</option>
											</select>
										</div>
									</td>
									<td><input type="text" name="msd_note[]" placeholder="입력하세요."/></td>
									<td class="tb_btn">
										<a class="btn del_btn">-</a>
									</td>
								</tr>
								-->
							</tbody>
						</table>
					</div>
				</div>
				<div class="tb">
					<div class="table table_btn">
						<table class="tbl_list2">
							<colgroup>
								<col width="6%">
								<col width="6%">
								<col width="10%">
								<col width="20%">
								<col width="20%">
								<col width="">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>순번</th>
									<th>변경구분</th>
									<th>변경일</th>
									<th>인계부서</th>
									<th>인수부서</th>
									<th>근거자료</th>
									<th colspan="3">매각처 및 폐기사유</th>
									<th class="tb_btn"><a class="btn add_btn" data-addlist="2">+</a></th>
								</tr>
							</thead>
							<tbody>
								<!--
								<tr>
									<td><div></div></td>
									<td>
										<input type="text" name="mss_change[]" value="" placeholder="입력하세요."/>
									</td>
									<td>
										<input type="date" name="mss_date[]" value="" placeholder="변경일" />
									</td>
									<td>
										<input type="text" name="mss_over[]" value="" placeholder="입력하세요."/>
									</td>
									<td>
										<input type="text" name="mss_take[]" value="" placeholder="입력하세요."/>
									</td>
									<td>
										<input type="text" name="mss_reason[]" value="" placeholder="입력하세요."/>
									</td>
									<td colspan="3">
										<input type="text" name="mss_sale[]" value="" placeholder="입력하세요."/>
									</td>
									<td class="tb_btn">
										<a class="btn del_btn" >-</a>
									</td>
								</tr>
								-->
							</tbody>
						</table>
					</div>
				</div>
				<div class="btn_area">
					<button class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //계측기등록view -->


<!-- 계측기 이력카드 확인하기 -->
<div class="dialog pop_up_page_type1 pop_up_report" id="instrument_report">
	<div class="overlay"></div>
	<div class="content instrument_card">
		<div class="info">
			<p>계측기관리 > <span>이력카드</span></p>
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<div class="pop_print">
				<h2>계 측 기 이 력 카 드</h2>
				<div class="inner_wrap1 tb">
					<div class="table">
						<table>
							<colgroup>
								<col width="100px">
								<col width="calc(50% - 100px)">
								<col width="100px">
								<col width="calc(50% - 100px)">
							</colgroup>
							<tbody>
								<tr>
									<th>관리번호</th>
									<td class="ms_manage"></td>
									<th>품명</th>
									<td class="md_name"></td>
								</tr>
								<tr>
									<th>규격</th>
									<td class="ms_stand1"></td>
									<th>모델명</th>
									<td class="ms_model"></td>
								</tr>
								<tr>
									<th>제조회사</th>
									<td class="act_company"></td>
									<th>제작일자</th>
									<td class="ms_create"></td>
								</tr>
								<tr>
									<th>일련번호</th>
									<td class="seq"></td>
									<th>부대품</th>
									<td class="ms_access"></td>
								</tr>
								<tr>
									<th>내용연수</th>
									<td class="ms_years"></td>
									<th>운영부서</th>
									<td class="check_name"></td>
								</tr>
								<tr>
									<th>취득선</th>
									<td class="ms_account"></td>
									<th>구매일자</th>
									<td class="ms_buy"></td>
								</tr>
								<tr>
									<th>취득금액</th>
									<td class="ms_acq"></td>
									<th>기타참고사항</th>
									<td class="ms_etc"></td>
								</tr>
								<tr>
									<th>검교정주기</th>
									<td class="ms_cycle"></td>
									<th>허용오차</th>
									<td class="ms_error"></td>
								</tr>
								<tr>
									<th>회계자산코드</th>
									<td class="ms_asset"></td>
									<th>사용범위</th>
									<td class="ms_range"></td>
								</tr>
								<tr>
									<th>프로젝트코드</th>
									<td class="ms_project"></td>
									<th></th>
									<td class=""></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="inner_img"></div>
				</div>
				<div class="tb tb1">
					<table class="tlb_list1">
						<thead>
							<tr>
								<th colspan="5">검교정내역</th>
							</tr>
							<tr>
								<th>검교정일자</th>
								<th>검교정기관</th>
								<th>조치</th>
								<th>판정</th>
								<th>비고</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>0</td>
								<td>0</td>
								<td>0</td>
								<td>0</td>
								<td>0</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="tb tb2">
					<table class="tlb_list2" id="">
						<colgroup>
							<col width="10%">
							<col width="10%">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
						</colgroup>
						<thead>
							<tr>
								<th colspan="6">계측기 이력 사항</th>
							</tr>
							<tr>
								<th>변경구분</th>
								<th>변경일</th>
								<th>인계부서</th>
								<th>인수부서</th>
								<th>근거자료</th>
								<th>매각처 및 폐기사유</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>이관(구)</td>
								<td>2013-01-16</td>
								<td>경남사업단 진주1,2터널팀</td>
								<td>1</td>
								<td>2013년 유지관리 착수</td>
								<td>1</td>
							</tr>
							<tr>
								<td>이관(구)</td>
								<td>2013-01-16</td>
								<td>경남사업단 진주1,2터널팀</td>
								<td>1</td>
								<td>2013년 유지관리 착수</td>
								<td>1</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="down_area">
			<a class="btn pdf_down sky" data-filename="계측기이력카드">Pdf 다운로드</a>
		</div>
	</div>
</div>
<!-- //계측기 이력카드 확인하기 -->



<script>

//table tr클릭시 view팝 a태그 클릭시 report팝
$('.instrument_management tbody td').click(function () {
		if ($(this).index() == $(this).closest('tr').find('td').last().index()) {
			$('#instrument_report').addClass('dialog_open');
		} else {
			$('#instrument_view').addClass('dialog_open');
		}
	});
</script>


<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/instrument_management.js"></script>