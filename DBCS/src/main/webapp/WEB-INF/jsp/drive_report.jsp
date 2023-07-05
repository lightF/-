<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<!--        여기부터 각페이지 본문             -->
<div class="cont drive_report">
	<div class="title">
		<p>운행일지</p>
		<a class="favo"></a>				
	</div>
	<div class="wrap">	
		<div class="search">
			<form name="sch_form">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="column" value="rc_date">
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
					<li class="w40">
						<p>사업단</p>
						<div class="sch_inp">
							<input type="hidden" name="group_name">
							<input type="text" name="group_name_text" placeholder="검색하세요." readonly/>
							<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="w50">
						<p>차량번호</p>
						<div>
							<input type="text" name="ve_number" placeholder="입력하세요."/>
						</div>
					</li>
					<li class="w50">
						<p>사고여부</p>
						<div>
							<select name="rc_handle">
								<option value="">전체</option>
								<option value="1">무</option>
								<option value="2">유</option>
							</select>
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button type="submit" class="btn search_btn">검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky" data-dialog="drive_repo">운행일지 작성<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb"> 
			<div class="table tb_scroll">
				<table>
					<colgroup>
						<col width="10%">
						<col width="8%">
						<col width="8%">
						<col width="12%">
						<col width="16%">
						<col width="8%">
						<col width="12%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th data-order="ASC" data-column="rc_date" class="sort">운행일자</th>
							<th data-order="ASC" data-column="rc_before">운행전(km)</th>
							<th data-order="ASC" data-column="rc_after">운행후(km)</th>
							<th data-order="ASC" data-column="group_name">사업단</th>
							<th data-order="ASC" data-column="team_name">운행팀</th>
							<th data-order="ASC" data-column="ve_number">차량번호</th>
							<th data-order="ASC" data-column="rc_amount/rc_refuel">주유금액(원)/량(l)</th>
							<th data-order="ASC" data-column="rc_trouble">점검금액</th>
							<th data-order="ASC" data-column="rc_trouble">주차비</th>
							<th data-order="ASC" data-column="rc_trouble">통행료</th>
							<!-- <th>운행보고서</th> -->
						</tr>
					</thead>
					<tbody class="list_tbody">
						<!-- <tr>
							<td>2022. 09. 05</td>
							<td>운행전1</td>
							<td>운행후</td>
							<td>사업단</td>
							<td>차량번호</td>
							<td>주유</td>
							<td>점검</td>
							<td>확인</td>
							<td>
								<a data-dialog="pop_up_page_view" ><i class="ri-draft-fill"></i></a>
							</td>
						</tr>	 -->
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
					<p><span id="total"></span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">
				</ul>
			</div>
			<div class="down_area">
				<a class="btn sky" data-dialog="drive_repo2">운행일지 보고서<i class="ri-draft-fill"></i></a>
				<a class="btn list_xlsx sky">Xlsx 다운로드</a>
			</div>
		</div>
	</div>
</div>

<!--    //    여기까지 각페이지 본문             -->

         

<!-- 운행일지 등록 (add / edit) -->
<div class="dialog pop_up_page drive_repo_pop" id="drive_repo">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>운행일지 > <span>운행일지 작성</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="add_form" method="post" action="">
				<input type="hidden" name="seq" value="">
				<div class="log_box">
					<div class="ul_style clearfix">
						<ul class="wrap1 clearfix">
							<li class="required">
								<p>차량번호<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="ve_seq" />
									<input type="text" name="ve_number" placeholder="검색하기" class="iess" required="required" readonly/>
									<a class="btn car_sch_btn" data-dialog="car_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>입력자<span class="ess"></span></p>
								<div class="">
									<input type="hidden" name="per_seq" value='<%= session.getAttribute("per_seq")%>' class="ess" />
									<input type="text" name="per_name" required="required"
									value='<%= session.getAttribute("per_name")%>' class="iess" readonly/>
									<!-- <a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a> -->
								</div>
							</li>
							<li class="required">
								<p>운행일자<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="date" name="rc_date" class="iess" required="required" />
								</div>
							</li>
							<li>
								<p>주행거리(km)</p>
								<div>
									<input type="text" name="rc_drive" placeholder="자동입력" value="" readonly />
								</div>
							</li>
							<li class="required">
								<p>운행전(km)<span class="ess"></span></p>
								<div>
									<input type="text" name="rc_before" class="iess" placeholder="자동입력" value="" readonly required="required"/>	
								</div>
							</li>
							<li>
								<p>운행후(km)</p>
								<div>
									<input type="text" name="rc_after" placeholder="자동입력" value="" readonly />
								</div>
							</li>

							<li>
								<p>가동시간(분)</p>
								<div>
									<input type="text" name="rc_operate" placeholder="자동입력" value="" readonly />
								</div>
							</li>
							<li>
								<p>경유지</p>
								<div>
									<input type="text" name="rc_area" placeholder="자동입력" id="area" value="" readonly />	
								</div>
							</li>
							<li>
								<p>통행료(원)</p>
								<div>
									<input type="text" name="rc_pass" placeholder="자동입력" value="" readonly />
								</div>
							</li>
							<li>
								<p>주차비(원)</p>
								<div>
									<input type="text" name="rc_park" placeholder="자동입력" value="" readonly />
								</div>
							</li>
							<li>
								<p>주유리터</p>
								<div>
									<input type="text" name="rc_refuel" placeholder="자동입력" value="" id="oil" readonly />
								</div>
							</li>
							<li>
								<p>주유금액(원)</p>
								<div>
									<input type="text" name="rc_amount" class="input_number" placeholder="입력하세요." value="" id="oil_all" />
								</div>
							</li>
							<li>
								<p>주유단가(원)</p>
								<div>
									<input type="text" name="rc_price" class="input_number" placeholder="입력하세요." value="" id="oil_l"/>
								</div>
							</li>
							<li class="required">
								<p>사고처리</p>
								<div>
									<select name="rc_handle" id="">
										<option value="">선택</option>
										<option value="1">없음</option>
										<option value="2">현금</option>
										<option value="3">보험</option>
									</select>
								</div>
							</li>
							<li>
								<p>고장수리비(원)</p>
								<div>
									<input type="text" name="rc_trouble"class="input_number" placeholder="입력하세요." value=""/>
								</div>
							</li>
							<li>
								<p>고장부품</p>
								<div>
									<input type="text" name="rc_part" placeholder="입력하세요." value=""/>
								</div>
							</li>
							<li class="img">
								<p>첨부파일1</p>
								<div class="">
									<input type="file" name="upload[]" id="f_seq1" class="file_input">
									<label for="f_seq1"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
								</div>
							</li>
							<li class="img">
								<p>첨부파일2</p>
								<div class="">
									<input type="file" name="upload[]" id="f_seq2" class="file_input">
									<label for="f_seq2"><i class="ri-upload-fill"></i></label>
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
						<table class="under_tb">
							<colgroup>
								<col width="6%">
								<col width="12%">
								<col width="12%">
								<col width="8%">
								<col width="8%">
								<col width="8%">
								<col width="8%">
								<col width="10%">
								<col width="">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>순번</th>
									<th>출발지</th>
									<th>도착지</th>
									<th>거리</th>
									<th>시간(분)</th>
									<th>통행료</th>
									<th>주차비</th>
									<th>운전자</th>
									<th>비고</th>
									<th class="tb_btn"><a class="btn add_btn">+</a></th>
								</tr>
							</thead>
							<tbody>
								<tr tr_seq="1">
									<td>
										<div>
											<!--순번-->
											<input type="text" name="soonbun"
											value="1" readonly />
											<input type="hidden" name="rh_seq[]">
										</div>
					 				</td>
									<td>
										<div>
											<!--출발지-->
											<input type="text" name="rh_depart[]"
												id="str1" placeholder="입력하세요." />
										</div>
									</td>
									<td>
										<div>
											<!--도착지-->
											<input type="text" name="rh_arrival[]"
												id="fin1" placeholder="입력하세요." />
										</div>
									</td>
									<td>
										<div>
											<!--거리-->
											<input type="text" name="rh_distance[]"
												id="" placeholder="입력하세요." />
										</div>
									</td>
									<td>
										<!--시간(분)-->
										<input type="text" name="rh_minute[]"
											id="" placeholder="입력하세요." />
									</td>
									<td>
										<div>
											<!--통행료-->
											<input type="text" name="rh_pass[]"
												id="" placeholder="입력하세요." />
										</div>
									</td>
									<td>
										<div>
											<!--주차비-->
											<input type="text" name="rh_park[]"
												id="" placeholder="입력하세요." />
										</div>
									</td>
									<td>
										<div>
											<!--운전자-->
											<input type="text" name="rh_driver[]"
												id="" placeholder="입력하세요." />
										</div>
									</td>
									<td>
										<div>
											<!--비고-->
											<input type="text" name="rh_note[]"
												id="" placeholder="입력하세요." />
										</div>
									</td>
									<td class="tb_btn">
										<a class="btn del_btn">-</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="btn_area">
					<button type="submit" class="btn edit_btn"><i class="ri-check-line"></i>저장</button>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
					
					<!-- view 일때 
					<button type="submit" class="btn edit_btn"><i class="ri-check-line"></i>저장</button>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn del" data-dialog="delete_pop"><i class="ri-delete-bin-line"></i>삭제</a>
					-->
					
				</div>
			</form>
		</div>
	</div>
</div>
<!-- // 운행일지 등록 (add / edit) -->

<!--보고서 팝업-->
<div class="dialog pop_up_page drive_repo_pop" id="drive_repo2">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>운행일지 > <span>운행일지 작성</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="car_report_form" method="post" action="">
				<input type="hidden" name="seq" value="">
				<div class="log_box">
					<div class="search">
						<ul>
							<li>
								<div class="title">차량번호</div>
								<div class="slt">
									<div class="sch_inp">
										<input type="hidden" name="ve_seq" value="" />
										<input type="text" name="ve_number" placeholder="검색하기" class="ess" required="required" readonly/>
										<a class="btn car_sch_btn" data-dialog="car_sch"><i class="ri-search-line"></i></a>
									</div>
								</div>
								<div class="title">운행일</div>
								<div>
									<input type="date" name="date" value='' class="ess" />
								</div>
								<div>
									 <a class="btn search_btn">조회<i class="ri-search-line"></i></a>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="tb pop_print">
					<div class="table table_btn">
						<h2><span>0000년 00월</span> 차량운행실적</h2>
						<ul class="car_report_sign">
							<li>
								※주유영수증, 수리내역서 첨부
							</li>
							<li>
								<table>
									<tbody>
										<tr>
											<td rowspan="2">결<br>재</td>
											<td>담당</td>
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
									</tbody>
								</table>
							</li>
						</ul>
						<table class="head_title">
							<tbody>
								<tr>
									<th>차량명</th>
									<td></td>
									<th>소  속</th>
									<td></td>
									<th>수임자</th>
									<td></td>
								</tr>
								<tr>
									<th>등록번호</th>
									<td></td>
									<th>팀  명</th>
									<td></td>
									<th>차량구분</th>
									<td></td>
								</tr>
							</tbody>
						</table>
						<table class="car_report_body">
							<colgroup>
								<col width="6%">
								<col width="10%">
								<col width="10%">
								<col width="10%">
								<col width="8%">
								<col width="8%">
								<col width="8%">
								<col width="8%">
								<col width="8%">
								<col width="34%">
							</colgroup>
							<thead>
								<tr>
									<th>운행일</th>
									<th>운전전(Km)</th>
									<th>운전후(Km)</th>
									<th>주행거리(Km)</th>
									<th>주유량(ℓ)</th>
									<th>주유금액(원)</th>
									<th>수리금액(원)</th>
									<th>통행료(원)</th>
									<th>주차료(원)</th>
									<th>경유지</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="btn_area">
					<a class="btn pdf_down sky" data-filename="운행일지">Pdf 다운로드</a>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
					
					<!-- view 일때 
					<button type="submit" class="btn edit_btn"><i class="ri-check-line"></i>저장</button>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn del" data-dialog="delete_pop"><i class="ri-delete-bin-line"></i>삭제</a>
					-->
					
				</div>
			</form>
		</div>
	</div>
</div>

<script>

//table tr클릭시 view팝 a태그 클릭시 report팝
	$('.drive_report tbody td').click(function () {
		if ($(this).index() == $(this).closest('tr').find('td').last().index()) {
			$('#drive_repo_report').addClass('dialog_open');
		} else {
			$('#drive_repo_view').addClass('dialog_open');
		}
	});
</script>

<%@ include file='tail.jsp' %>

<script src="${resourcePath}/js/drive_report.js"></script>