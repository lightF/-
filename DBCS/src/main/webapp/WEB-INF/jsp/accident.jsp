<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--    여기부터 각페이지 본문       -->
<div class="cont accident">
	<div class="title">
		<p>사고일지</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="accident_search" action="" method="post">
				<input type="hidden" name="column" value="seq">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="section" value="1">
				<ul>
					<li class="date w40">
						<p>조회기간</p>
						<div class="start">
							<input type="date" name="start_date" placeholder="시작일">
						</div>
						<div class="end">
							<input type="date" name="end_date" placeholder="종료일">
						</div>
					</li>
					<li class="w30">
						<p>사업단</p>
						<div class="sch_inp">
							<input type="hidden" name="group_name" >
							<input type="text" name="text" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="team_sch" data-type="group"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="w30">
						<p>차량번호</p>
						<div>
							<input type="text" name="ve_number" placeholder="입력하세요.">
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button class="btn btn_search">검색<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-dialog="accident_log_view" data-type="new">사고일지 작성<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="tb_scroll">
				<table class="accident_list">
					<colgroup>
						<col width="6%">
						<col width="10%">
						<col width="12%">
						<col width="8%">
						<col width="8%">
						<col width="8%">
						<col width="8%">
						<col width="">
						<col width="6%">
					</colgroup>
					<thead>
						<tr>
							<th data-column="seq" data-order="ASC">사고번호</th>
							<th data-column="group_name" data-order="ASC">사업단</th>
							<th data-column="team_name" data-order="ASC">점검팀</th>
							<th data-column="ve_name" data-order="ASC">차명</th>
							<th data-column="ve_number" data-order="ASC">차량번호</th>
							<th data-column="ac_blame" data-order="ASC">구분</th>
							<th data-column="ac_date" data-order="ASC">사고일</th>
							<th data-column="ac_place" data-order="ASC">사고장소</th>
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
			<div class="down_area">
				<a class="btn list_xlsx sky">Xlsx 다운로드</a>
			</div>
		</div>
	</div>
</div>
<!--  //  여기까지 각페이지 본문       -->
		



<!-- 사고일지 작성하기 -->
<div class="dialog accident_log" id="accident_log_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>사고일지 > <span class="detail_tit">사고일지 작성</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="accident_detail" method="post" enctype="multipart/form-data">
				<input type="hidden" name="seq">
				<div class="log_box">
					<div class="ul_style">
						<ul class="wrap1">
							<li class="required">
								<p>차량번호<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="ve_seq" >
									<input type="text" name="ve_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="car_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>운전자<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="per_seq">
									<input type="text" name="per_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>사업단<span class="ess"></span></p>
								<div>
									<input type="hidden" name="per_organize" class="group_seq">
									<input type="text" name="per_organize_name" class="group_name" placeholder="점검팀 선택시 자동입력" readonly>
								</div>
							</li>
							<li class="required">
								<p>점검팀<span class="ess"></span></p>
								<div>
									<input type="hidden" name="pname">
									<input type="hidden" name="pcode">
									<input type="hidden" name="per_team">
									<input type="text" name="per_team_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="required">
								<p>사고일자<span class="ess"></span></p>
								<div>
									<input type="date" name="ac_date" placeholder="일자 선택" required="required">
								</div>
							</li>
							<li class="required">
								<p>날씨<span class="ess"></span></p>
								<div>
									<input type="text" name="ac_weather" placeholder="입력하세요." required="required">
								</div>
							</li>
							<li class="required">
								<p>사고장소<span class="ess"></span></p>
								<div>
									<input type="text" name="ac_place" placeholder="입력하세요." required="required">
								</div>
							</li>
							
							<li class="required">
								<p>상대차종<span class="ess"></span></p>
								<div>
									<input type="text" name="ac_model" placeholder="입력하세요." required="required">
								</div>
							</li>
							<li class="required">
								<p>상대차량번호<span class="ess"></span></p>
								<div>
									<input type="text" name="ac_car" placeholder="입력하세요." required="required">
								</div>
							</li>
							<li class="required">
								<p>연락처<span class="ess"></span></p>
								<div>
									<input type="text" name="ac_phone" placeholder="입력하세요." required="required">
								</div>
							</li>
							<li class="w100 textarea">
								<p>사고개요(300자 이내)</p>
								<div>
									<textarea name="ac_summary" placeholder="입력하세요."></textarea>
								</div>
							</li>
							<li class="required">
								<p>책임구분<span class="ess"></span></p>
								<div>
									<select name="ac_blame" required="required">
										<option value="">선택</option>
										<option value="1">자사과실</option>
										<option value="2">상대과실</option>
										<option value="3">쌍방과실</option>
									</select>
								</div>
							</li>
							<li>
								<p>보고일자</p>
								<div>
									<input type="date" name="ac_report" placeholder="일자 선택">
								</div>
							</li>
							<li>
								<p>당사(대인)</p>
								<div>
									<input type="text" name="ac_our1" placeholder="입력하세요.">
								</div>
							</li>
							<li>
								<p>당사(대물)</p>
								<div>
									<input type="text" name="ac_our2" placeholder="입력하세요.">
								</div>
							</li>
							<li >
								<p>상대(대인)</p>
								<div>
									<input type="text" name="ac_match1" placeholder="입력하세요.">
								</div>
							</li>
							<li >
								<p>상대(대물)</p>
								<div>
									<input type="text" name="ac_match2" placeholder="입력하세요.">
								</div>
							</li>
							<li class="w100">
								<p class="pd0505">사고차량 현황 및 수리기간</p>
								<div>
									<input type="text" name="ac_term" placeholder="입력하세요.">
								</div>
							</li>
							<li class="w100">
								<p>기타</p>
								<div>
									<input type="text" name="ac_etc" placeholder="입력하세요.">
								</div>
							</li>
							
							<li class="img mr20">
								<p>이미지1</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="a_file1">
									<label for="a_file1"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
									<!--
									<img src="${resourcePath}/css/img/exex.jpg">
									<a class="file_del"><i class="ri-close-line"></i></a>
									-->
								</div>
							</li>
							<li class="img">
								<p>이미지2</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="a_file2">
									<label for="a_file2"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
									<!--
									<img src="">
									<a class="file_del"><i class="ri-close-line"></i></a>
									-->
								</div>
							</li>
							<li class="img mr20">
								<p>이미지3</p>
								<div class="">
									<input type="file" name="upload[]" id="a_file3">
									<label for="a_file3"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
									<!--
									<img src="">
									<a class="file_del"><i class="ri-close-line"></i></a>
									-->
								</div>
							</li>
							<li class="img">
								<p>이미지4</p>
								<div class="input_file">
									<input type="file" name="upload[]" id="a_file4">
									<label for="a_file4"><i class="ri-upload-fill"></i></label>
									<p>File upload</p>
									<input type="hidden" name="f_seq[]" value="">
									<input type="hidden" name="f_del[]" value="1">
									<!--
									<img src="">
									<a class="file_del"><i class="ri-close-line"></i></a>
									-->
								</div>
							</li>
						</ul>

					</div>
				</div>
				<div class="btn_area">
					<button class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn del btn_remove" data-dialog="delete_pop"><i class="ri-delete-bin-line"></i>삭제</a>
					<a class="btn close close_in"><i class="ri-close-line"></i>취소</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //사고일지 view-->


<!-- 사고일지 보고서 확인하기 -->
<div class="dialog pop_up_report" id="accident_log_report">
	<div class="overlay"></div>
	<div class="content">
		<div class="info">
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<div class="pop_print">
				<p class="title">차량 사고 보고서</p>
				<div class="tb report_type">
					<div class="table">
						<table class="tableData">
							<thead>
							</thead>
							<tbody>
								<tr class="n3">
									<th colspan="2">소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;속</th>
									<td colspan="2" class="team_name"></td>
									<th colspan="2">직&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;위</th>
									<td colspan="2" class="pos_seq"></td>
									<th colspan="2">운&nbsp;전&nbsp;자</th>
									<td colspan="2" class="per_name"></td>
								</tr>
								<tr></tr>
								<tr class="n3 row">
									<th rowspan="2" colspan="2">사고일시</th>
									<td rowspan="2" colspan="2" class="ac_date"></td>
									<th rowspan="2" colspan="2">사고장소</th>
									<td rowspan="2" colspan="2" class="ac_place"></td>
									<th colspan="2">기상상태</th>
									<td colspan="2" class="ac_weather"></td>
								</tr>
								<tr class="n3 row">
									<th rowspan="3">상대차량</th>
									<th>차 명</th>
									<td colspan="2" class="ac_model"></td>
								</tr>
								<tr class="row">
									<th rowspan="2" colspan="2">차&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;명</th>
									<td rowspan="2" colspan="2" class="ve_name"></td>
									<th rowspan="2" colspan="2">차량번호</th>
									<td rowspan="2" colspan="2" class="ve_number"></td>
									<th>차 번</th>
									<td colspan="2" class="ac_car"></td>
								</tr>
								<tr class="row">
									<th>연락처</th>
									<td colspan="2" class="ac_phone"></td>
								</tr>
								<tr class="he100">
									<th rowspan="4" colspan="2">사고발생개요</th>
									<td rowspan="4" colspan="10" class="textarea ac_summary"></td>
								</tr>
								<tr></tr>
								<tr></tr>
								<tr></tr>
								<tr>
									<th rowspan="2" colspan="2">구&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;분</th>
									<th rowspan="2" colspan="5" class="tit3">대인</th>
									<th rowspan="2" colspan="5" class="tit3">대물</th>
								</tr>
								<tr></tr>
								<tr>
									<th rowspan="4" colspan="2">피해정도</th>
									<td rowspan="2" colspan="5"><p>당&nbsp;&nbsp;&nbsp;사 :&nbsp;<span class="ac_our1"></span></p></td>
									<td rowspan="2" colspan="5"><p>당&nbsp;&nbsp;&nbsp;사 :&nbsp;<span class="ac_our2"></span></p></td>
								</tr>
								<tr></tr>
								<tr>
									<td rowspan="2" colspan="5"><p>상대방 :&nbsp;<span class="ac_match1"></span></p></td>
									<td rowspan="2" colspan="5"><p>상대방 :&nbsp;<span class="ac_match2"></span></p></td>
								</tr>
								<tr></tr>
								<tr>
									<th rowspan="2" colspan="2">책임소재</th>
									<td rowspan="2" colspan="10" class="textarea ac_blame"></td>
								</tr>
								<tr></tr>
								<tr>
									<th rowspan="2" colspan="2">사고차량</br> 현황 및</br>수리기간</th>
									<td rowspan="2" colspan="10" class="textarea ac_term"></td>
								</tr>
								<tr></tr>
								<tr>
									<th rowspan="2" colspan="2">기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;타</th>
									<td rowspan="2" colspan="10" class="textarea ac_etc"></td>
								</tr>
								<tr></tr>
								<tr>
									<td colspan="12" class="bt bd_bottom">
										<p>위와 같이 차량 사고가 발생하였음을 보고합니다.</p>
										
									</td>
								</tr>
								<tr>
									<td colspan="12" class="bt bd_top">
										<p><span class="year"></span>년 <span class="moth"></span>월 <span class="day"></span>일</p>
									</td>
								</tr>
							</tbody>
							
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="down_area">
			<a class="btn pdf_down sky" data-filename="사고보고서">Pdf 다운로드</a>
		</div>
	</div>
</div>
<!-- //사고일지 보고서 확인하기 -->



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/accident.js"></script>