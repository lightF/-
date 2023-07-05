<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<!--    여기부터 각페이지 본문       -->
<div class="cont employee">
	<div class="title">
		<p>직원관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="user_list_search"  method="post">
				<input type="hidden" name="column" value="per_id" />
				<input type="hidden" name="order" value="ASC" />
				<input type="hidden" name="page" value="1" />
				<ul>
					<li>
						<p>사번</p>
						<div>
							<input type="text" name="per_id" placeholder="입력하세요.">
						</div>
					</li>
					<li>
						<p>이름</p>
						<div>
							<input type="text" name="per_name" placeholder="입력하세요.">
						</div>
					</li>
					<li>
						<p>팀</p>
						<div class="sch_inp">
							<input type="hidden" name="team_name">
							<input type="text" name="team_name_text" placeholder="검색">
							<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li>
						<p>사업단</p>
						<div>
							<input type="hidden" name="org_name" class="group_seq">
							<input type="text" name="org_name_text" class="group_name" placeholder="팀 선택">
							<!--a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>-->
						</div>
					</li>
					<li>
						<p>담당업무</p>
						<div>
							<select name="at_seq">
							</select>
						</div>
					</li>
					<li>
						<p>직위</p>
						<div>
							<select name="pos_seq">
							</select>
						</div>
					</li>
					<li>
						<p>직급</p>
						<div>
							<select name="jg_seq">
							</select>
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button class="btn btn_search">검색<i class="ri-search-line"></i></button>
					<!-- <a class="btn sky" data-dialog="emp_add">직원+<i class="ri-user-add-fill"></i></a> -->
					<a class="btn sky" data-dialog="user_detail_view">직원추가<i class="ri-user-add-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table table_btn tb_scroll">
				<table>
					<thead>
						<tr>
							<th data-column="per_id" data-order="DESC" class="sort">
								사번
							</th>
							<th data-column="per_name" data-order="ASC">
								이름
							</th>
							<th data-column="per_organize" data-order="ASC">
								사업단
							</th>
							<th data-column="per_team" data-order="ASC">
								팀
							</th>
							<th data-column="at_seq" data-order="ASC">
								담당업무
							</th>
							<th data-column="pos_seq" data-order="ASC">
								직위
							</th>
							<th data-column="jg_seq" data-order="ASC">
								직급
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="tb_bottom">
				<div class="slt">
					<select name="row" required>
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
		</div>
	</div>
</div>
<!--  //  여기까지 각페이지 본문       -->



<!-- 직원정보 상세보기 팝업 -->
<div class="dialog emp_pop" id="user_detail_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>직원 관리 > <span>직원 등록</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="user_detail" action="" method="post">
			<input type="hidden" name="seq" value="">
				<div class="emp_info pop_print">
					<div class="ul_style">
						<div class="tit">
							<p>인 적 사 항</p>
						</div>
						<ul>
							<li class="name required">
								<p>성명<span class="ess"></span></p>
								<div>
									<input type="text" name="per_name" placeholder="성명(국문)" required>
								</div>
								<div>
									<input type="text" name="per_ename" placeholder="성명(영문)">
								</div>
								<div>
									<input type="text" name="per_cname" placeholder="성명(한문)">
								</div>
							</li>
							<li class="required birthday">
								<p>생년월일<span class="ess"></span></p>
								<div>
									<input type="date" name="per_birth" required>
								</div>
							</li>
							<li class="photo_img img">
								<input type="file" name="upload[]" id="f_seq1" class="file_input">
								<label for="f_seq1"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<input type="hidden" name="f_seq" value="">
								<input type="hidden" name="f_del" value="1">
							</li>
							<li class="required line2 organize">
								<p>사업단<span class="ess"></span></p>
								<div>
									<input type="hidden" name="per_organize" class="group_seq" required>
									<input type="text" name="per_organize_tmp" class="group_name" placeholder="소속팀 검색시 자동입력" readonly>
								</div>
							</li>
							<li class="line2 area">
								<p>관할지역</p>
								<div>
									<input type="text" name="per_area" placeholder="입력하세요.">
								</div>
							</li>
							<li class="required line2 team">
								<p>소속팀<span class="ess"></span></p>
								<div class="sch">
									<input type="hidden" name="pname">
									<input type="hidden" name="pcode">
									<input type="hidden" name="per_team" required>
									<input type="text" name="per_team_tmp" placeholder="검색하기">
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="line2 business">
								<p>담당업무</p>
								<div>
									<select name="at_seq">
									</select>
								</div>
							</li>
							<li class="line2 place">
								<p>근무지</p>
								<div>
									<input type="text" name="per_place" placeholder="입력하세요.">
								</div>
							</li>
							<li class="line2 rank">
								<p>직급</p>
								<div>
									<select name="jg_seq">
									</select>
								</div>
							</li>
							<li class="required line2 ctu id">
								<p>사번(수정 불가)<span class="ess"></span></p>
								<div>
									<input type="text" name="per_id" placeholder="입력하세요." oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
								</div>
								<input type="hidden" name="per_id_chk" value="0">
								<a class="btn test">중복검사<span class="ess_ye"></span></a>
								<!-- 중복검사 전 -->
								<p class="ipt"><i class="ri-error-warning-line"></i> 중복검사 필요</p>
							</li>
							<li class="mr0 line2 posi">
								<p>직위</p>
								<div>
									<select name="pos_seq">
									</select>
								</div>
							</li>
							<li class="line2_5 email">
								<p>이메일</p>
								<div>
									<input type="text" name="per_email" placeholder="입력하세요.">
								</div>
							</li>
							<li class="line2_5 job">
								<p>직책</p>
								<div>
									<select name="j_seq">
									</select>
								</div>
							</li>
							<li class="mr0 line2_5 work">
								<p>직종</p>
								<div>
									<select name="per_type">
										<option value="">선택하세요.</option>
										<option value="1">정규직</option>
										<option value="2">계약직</option>
										<option value="3">인턴직</option>
										<option value="4">촉탁직</option>
									</select>
								</div>
							</li>
							<li class="addr">
								<p>주소</p>
								<div class="zip_code">
									<input type="number" name="per_zip" value="" readonly>
								</div>
								<div class="addr1 sch">
									<input type="text" name="per_addr" placeholder="입력하세요." readonly>
									<a class="btn search_home"><i class="ri-search-line"></i></a>
								</div>
								<p>상세주소</p>
								<div class="addr2">
									<input type="text" name="per_detail" placeholder="입력하세요.">
								</div>
							</li>
							<li class="line2_5 phone_nb">
								<p>연락처(본인휴대폰)<span class="ess"></span></p>
								<div>
									<input type="text" name="per_mobile" placeholder="입력하세요." oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
								</div>
							</li>
							<li class="line2_5 zip_nb">
								<p>연락처(집)</p>
								<div>
									<input type="text" name="per_home" placeholder="입력하세요." oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
								</div>
							</li>
							<li class="line2_5 mr0 ops_nb">
								<p>연락처(사무실)</p>
								<div>
									<input type="text" name="per_office" placeholder="입력하세요." oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
								</div>
							</li>
							<li class="line2_5 join">
								<p>입사일<span class="ess"></span></p>
								<div>
									<input type="date" name="per_join" placeholder="입력하세요." required>
								</div>
							</li>
							<li class="line2_5 promote">
								<p>승진일</p>
								<div>
									<input type="date" name="per_promote" placeholder="입력하세요.">
								</div>
							</li>
							<li class="line2_5 mr0 resign">
								<p>퇴사일</p>
								<div>
									<input type="date" name="per_resign" placeholder="입력하세요.">
								</div>
							</li>
						</ul>
					</div>
					<div class="tb">
						<div class="tit">
							<p>학 력 사 항</p>
						</div>
						<div class="table table_btn">
							<table>
								<thead>
									<tr>
										<th>입학일</th>
										<th>졸업(예정)일</th>
										<th>학교명</th>
										<th>전공</th>
										<th>업무관련성</th>
										<th>최종학력</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb">
						<div class="tit">
							<p>교 육 이 력</p>
						</div>
						<div class="table table_btn">
							<table>
								<thead>
									<tr>
										<th>수료일</th>
										<th>만료일</th>
										<th>교육기관</th>
										<th>과정명</th>
										<th>교육명</th>
										<th>점수</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb">
						<div class="tit">
							<p>자 격 면 허</p>
						</div>
						<div class="table table_btn">
							<table>
								
								<thead>
									<tr>
										<th>수료일</th>
										<th>만료일</th>
										<th>발행기관</th>
										<th>자격증명</th>
										<th>등급</th>
										<th>일련번호</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb">
						<div class="tit">
							<p>타 사 이 력</p>
						</div>
						<div class="table table_btn">
							<table>
								<thead>
									<tr>
										<th>입사일</th>
										<th>퇴사일</th>
										<th class="x2">회사명</th>
										<th class="x2">비고</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb">
						<div class="tit">
							<p>자 사 이 력</p>
						</div>
						<div class="table table_btn">
							<table>
								<thead>
									<tr>
										<th>발령일</th>
										<th>종료일</th>
										<th>근무지</th>
										<th>소속팀</th>
										<th>담당업무</th>
										<th>직위</th>
										<!--<th class="tb_btn"><a class="btn add_btn">+</a></th>-->
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>자동입력</td>
										<td>자동입력</td>
										<td>자동입력</td>
										<td>자동입력</td>
										<td>자동입력</td>
										<td>자동입력</td>
										
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb">
						<div class="tit">
							<p>프 로 젝 트</p>
						</div>
						<div class="table table_btn">
							<table>
								<thead>
									<tr>
										<th>시작일</th>
										<th>종료일</th>
										<th>근무지</th>
										<th>프로젝트명</th>
										<th>담당업무</th>
										<th>직위</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb">
						<div class="tit">
							<p>경 력 수 첩</p>
						</div>
						<div class="table table_btn">
							<table>
								<thead>
									<tr>
										<th>회원번호</th>
										<th>발급기관</th>
										<th>발급번호</th>
										<th>경력등급</th>
										<th>발급일자</th>
										<th>발급기준</th>
										<th>최종갱신일</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb">
						<div class="tit">
							<p>발 주 처 경 력</p>
						</div>
						<div class="table table_btn">
							<table>
								<thead>
									<tr>
										<th>시작일</th>
										<th>종료일</th>
										<th>근무지</th>
										<th>참여사업</th>
										<th>담당업무</th>
										<th>담당설비</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb">
						<div class="tit">
							<p>협 회 경 력</p>
						</div>
						<div class="table table_btn">
							<table>
								<thead>
									<tr>
										<th>시작일</th>
										<th>종료일</th>
										<th>근무지</th>
										<th>참여사업</th>
										<th>담당업무</th>
										<th>담당설비</th>
										<th class="tb_btn"><a class="btn add_btn">+</a></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tb">
						<div class="tit">
							<p>기 타 정 보</p>
						</div>
						<div class="table table_btn">
							<table>
								<thead>
									<tr>
										<th>차종</th>
										<th>차량번호</th>
										<th>명의</th>
										<th class="required">재직상태<span class="ess"></span></th>
										<th class="required">권한등급<span class="ess"></span></th>
										<th class="required">로그인 비밀번호<span class="ess ess_pwd"></span></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="text" name="car_model" placeholder="입력하세요."></td>
										<td><input type="text" name="car_number" placeholder="입력하세요."></td>
										<td><input type="text" name="car_name" placeholder="입력하세요."></td>
										<td>
											<select name="per_status" required>
												<option value="">선택하세요.</option>
												<option value="1">재직</option>
												<option value="2">퇴직</option>
											</select>
										</td>
										<td>
											<select name="ag_seq" required>
												<option value="">선택하세요.</option>
												<option value="1">최고관리자</option>
												<option value="2">중간관리자</option>
												<option value="3">일반사용자</option>
											</select>
										</td>
										<td><input type="password" name="per_pwd" placeholder="*****" ></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="btn_area">
					<button type="button" class="btn point"><i class="ri-check-line"></i>저장</button>
					<!-- view팝업에서 다운로드버튼 생성 -->
					<a class="btn pdf_down sky" data-filename="직원관리">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn close" style="display: block;"><i class="ri-close-line"></i>취소</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //직원정보 상세보기 팝업 -->

<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/employee.js"></script>