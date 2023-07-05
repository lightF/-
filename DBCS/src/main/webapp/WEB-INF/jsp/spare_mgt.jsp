<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>


<!--    여기부터 각페이지 본문       -->
<div class="cont employee spare">
	<div class="title">
		<p>부품관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="part_search" method="post" >
				<input type="hidden" name="column" value="seq">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">

				<ul>
					<li>
						<p>부품명</p>
						<div>
							<input type="text" name="pt_name" placeholder="입력하세요.">
						</div>
					</li>
					<li>
						<p>예산과목</p>
						<div>
							<!--<input type="text" name="bg_name" placeholder="입력하세요.">-->
							<select name="bg_seq">
							</select>
							
						</div>
					</li>
					<li>
						<p>설비분류</p>
						<div>
							<select name="sys_name" >
							</select>
						</div>
					</li>
					<li>
						<p>기기명</p>
						<div class="sch_inp">
							<input type="hidden" name="ds_name">
							<input type="text" name="ds_name_text" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="standard1_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li>
						<p>부품사양</p>
						<div>
							<input type="text" name="pt_spec" placeholder="입력하세요.">
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-dialog="emp_re" data-type="new">부품등록<i class="ri-user-add-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table tb_scroll">
				<table class="part_list">
					<thead>
						<tr>
							<th data-column="pt_code" data-order="ASC">부품코드</th>
							<th data-column="bg_seq" data-order="ASC">예산과목</th>
							<th data-column="sys_seq" data-order="ASC">설비분류</th>
							<th data-column="ds_name" data-order="ASC">기기명</th>
							<th data-column="act_company" data-order="ASC">기기제조사</th>
							<th data-column="pt_name" data-order="ASC">부품명</th>
							<th data-column="pt_spec" data-order="ASC">부품사양</th>
							<th data-column="act_company" data-order="ASC">부품제조사</th>
							<th data-column="pt_operate" data-order="ASC">사용구분</th>
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




<!-- 부품관리 상세보기 팝업 -->
<div class="dialog spare_pop" id="emp_re">
	<div class="overlay"></div>
	<div class="content pdf_print">
		<div class="top">
			<p>부품관리 > <span>상세 부품관리</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="part_detail" method="post" enctype="multipart/form-data">
				<input type="hidden" name="seq" value="">

				<div class="log_box pop_print">
					<div class="ul_style">
						<ul class="wrap1">
							<li class="required">
								<p>설비분류<span class="ess"></span></p>
								<div>
									<select name="sys_seq" required>
									</select>
								</div>
							</li>
							<li class="required">
								<p></p>
								<div></div>
							</li>
							<li class="required">
								<p>기기분류<span class="ess"></span></p>
								
								<div class="sch_inp">
									<input type="hidden" name="ds_seq">
									<input type="text" name="ds_seq_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="standard1_sch"><i class="ri-search-line"></i></a>
								</div>
								
							</li>
							<li class="required">
								<p>기기운영여부</p>
								<div>
									<select name="pt_operate">
                                        <option value="">선택</option>
										<option value="1">예</option>
										<option value="2">아니요</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>기기제조사<span class="ess"></span></p>
								<div class="">
									<input type="text" name="act_device" placeholder="입력하세요.">
									<!--
									<input type="text" name="act_device_text" placeholder="검색하기" required readonly>
									<a class="btn" data-dialog="act_sch"><i class="ri-search-line"></i></a>
									-->

								</div>
							</li>
							<li class="required">
								<p>연식<span class="ess"></span></p>
								<div>
									<input type="text" name="pt_years" class="input_number" placeholder="입력하세요." value="" required>
								</div>
							</li>
                            <li class="required">
								<p>부품코드<span class="ess"></span></p>
								<div>
									<input type="text" name="pt_code" placeholder="입력하세요." value="" required>
								</div>
							</li>
							<li class="required">
								<p>예산과목<span class="ess"></span></p>
								<div>
									<select name="bg_seq" required>
										<option value="">선택</option>
										<option value="">저장품</option>
										<option value="">재료비</option>
										<option value="">KHC예비기</option>
                                        <option value="">KHC예비품</option>
                                        <option value="">지급품</option>
										<option value="">대보예비품</option>
                                        <option value="">기타</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>부품명<span class="ess"></span></p>
								<div>
									<input type="text" name="pt_name" placeholder="입력하세요." value="부품명" required>
								</div>
							</li>
							<li class="required">
								<p>부품사양<span class="ess"></span></p>
								<div>
									<input type="text" name="pt_spec" placeholder="입력하세요." value="부품사양" required>
								</div>
							</li>
							<li class="required">
								<p>부품제조사<span class="ess"></span></p>
								<div class="">
									<input type="text" name="act_unit" placeholder="입력하세요.">
									<!--
									<input type="text" name="act_unit_text" placeholder="검색하기" required readonly>
									<a class="btn" data-dialog="act_sch"><i class="ri-search-line"></i></a>
									-->

								</div>
							</li>
							
							<li>
								<p>부품단위</p>
								<div>
									<input type="text" name="pt_unit" placeholder="입력하세요.">
								</div>
							</li>
							<li>
								<p>주요기능</p>
								<div>
									<input type="text" name="pt_main" placeholder="입력하세요.">
								</div>
							</li>
							<li>
								<p>하자기간</p>
								<div>
									<input type="text" name="pt_defect" class="input_number" placeholder="입력하세요.">
								</div>
							</li>
							<li >
								<p>S/N관리여부</p>
								<div>
									<select name="pt_sn">
                                        <option value="">선택</option>
										<option value="1">예</option>
										<option value="2">아니요</option>
									</select>
								</div>
							</li>
							<li >
								<p>부품구분</p>
								<div>
									<select name="pt_part">
                                        <option value="">선택</option>
										<option value="1">국산</option>
										<option value="2">외산</option>
                                        <option value="3">당사개발품</option>
									</select>
								</div>
							</li>
							<li class="">
								<p>단가계약품 여부</p>
								<div>
									<select name="pt_contract">
                                        <option value="">선택</option>
										<option value="1">예</option>
										<option value="2">아니요</option>
									</select>
								</div>
							</li>
							<li class="">
								<p>수입검사여부</p>
								<div>
									<select name="pt_test">
                                        <option value="">선택</option>
										<option value="1">예</option>
										<option value="2">아니요</option>
									</select>
								</div>
							</li>
							<li class="img">
								<p>이미지1</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="s_seq1" class="file_input">
									<label for="s_seq1"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>이미지2</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="s_seq2" class="file_input">
									<label for="s_seq2"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>시방서1</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="s_seq3" class="file_input">
									<label for="s_seq3"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
								
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>시방서2</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="f_seq4" class="file_input">
									<label for="f_seq4"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
						</ul>
					</div>
                    <div class="tb col">
						<div class="tit">
							<p>추가스펙</p>
						</div>
						<div class="table table_btn">
							<table class="detail_list">
								<thead>
									<tr>
										<th>규격명</th>
										<th>규격값</th>
										<th>규격단위</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="sp_seq[]">
											<input type="text" name="sp_name[]" placeholder="입력하세요.">
										</td>
										<td><input type="text" name="sp_value[]" class="input_number" placeholder="입력하세요."></td>
										<td><input type="text" name="sp_unit[]" class="input_number" placeholder="입력하세요."></td>
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
					<!--<a class="btn btn_down pdf_down sky" data-filename="부품관리">Pdf 다운로드</a>-->
					<a class="btn btn_down xlsx sky">Xlsx 다운로드</a>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
					
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //부품관리 상세보기 팝업 -->



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/spare_mgt.js"></script>