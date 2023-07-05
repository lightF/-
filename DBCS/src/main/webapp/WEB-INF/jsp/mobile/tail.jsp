<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tail.sub.jsp" %>
		
	</body>
</html>


<!--기기팝업 -->
<div class="dialog equip" id="equip_sch">
	<div class="overlay"></div>
	<div class="content">
		<div class="top comm">
			<p>■ <span>기기</span></p>
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="facility_device_list" action="" method="">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="section" value="10">
				<input type="hidden" name="column" value="dc_name">
				<input type="hidden" name="order" value="ASC">	
				<input type="hidden" name="target" value="">
				<div class="search">
					<ul>
						<li>
							<p>기기명</p>
							<div class="sch_inp">
								<input autocomplete="off" type="text" name="search_word" placeholder="입력하세요.">
								<a class="btn"><i class="ri-search-line"></i></a>
							</div>
						</li>
					</ul>
				</div>
				<div class="tb">
					<div class="table tb_scroll">
						<table>
							<thead>
								<tr>
									<th data-column="j_seq" data-order="ASC">기기명</th>
									<th data-column="j_seq" data-order="ASC">설치위치</th>
								</tr>
							</thead>
							<tbody>
							<% for(int i=0; i<10; i++){%>
								<tr>
									<td>기기명</td>
									<td>설치위치</td>
								</tr>
							<%}%>	
							</tbody>
						</table>
					</div>
					<div class="tb_bottom">
						<div class="slt">
							<select name="row">
								<option value="10" selected>10개씩 보기</option>
								<option value="20">20개씩 보기</option>
								<option value="50">50개씩 보기</option>
							</select>
						</div>
						
						<div class="count">
							<p><span>00</span>건이 검색되었습니다.</p>
						</div>
						<ul class="paging pagination">
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //기기팝업 -->


<!-- 직원 검색 -->
<div class="dialog" id="person_sch">
	<!-- <div class="overlay"></div> -->
	<input type="hidden" name="target" value="">
	<div class="content">
		<div class="top comm">
			<p>■ <span>인원현황</span></p>
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="user_list">
				<input type="hidden" name="page" value="1"/>
				<input type="hidden" name="section" value="10"/>	
				<input type="hidden" name="column" value="per_name"/>
				<input type="hidden" name="order" value="ASC"/>	
				<input type="hidden" name="target" value=""/>
				<input type="hidden" name="target_tbl" value=""/>
				<input type="hidden" name="target_num" value=""/>
				<div class="search">
					<ul>
						<li>
							<div class="slt">
								<select name="search_column" required>
									<option value="">선택</option>
									<option value="per_id">사번</option>
									<option value="per_name">이름</option>
									<option value="pos_name">직위</option>
									<option value="jg_name">직책</option>
								</select>
							</div>
							<div class="sch_inp">
								<input autocomplete="off"  type="text" name="search_word" placeholder="입력하세요." required>
								<a class="btn"><i class="ri-search-line"></i></a>
							</div>
						</li>
					</ul>
				</div>
				<div class="tb">
					<div class="table tb_scroll">
						<table>
							<thead>
								<tr>
									<th data-column="per_id" data-order="ASC">사번</th>
									<th data-column="per_name" data-order="ASC">이름</th>
									<th data-column="pos_name" data-order="ASC">직위</th>
									<th data-column="j_seq" data-order="ASC">직책</th>
								</tr>
							</thead>
							<tbody>
							<!-- <% for(int i=0; i<10; i++){%>
								<tr>
									<td>2022010</td>
									<td>홍길동</td>
									<td>소장</td>
									<td>책임자</td>
								</tr>
							<%}%>	 -->
							</tbody>
						</table>
					</div>
					<div class="tb_bottom">
						<div class="slt">
							<select name="row">
								<option value="10" selected>10개씩 보기</option>
								<option value="20">20개씩 보기</option>
								<option value="50">50개씩 보기</option>
							</select>
						</div>
						<div class="count">
							<p><span id="total_person_pop">0</span>건이 검색되었습니다.</p>
						</div>
						<ul class="paging pagination">
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //직원 검색 -->

<!-- 소속팀 검색 팝업-->
<div class="dialog" id="team_sch">
	<div class="content">
		<div class="top comm">
			<p>■ <span>소속팀</span></p>
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="org_list" method="post">
				<div class="tree_wrap">
					<input type="hidden" name="target" value="">
					<input type="hidden" name="org_seq" value="">
					<input type="hidden" name="target_num" value="">
					<input type="hidden" name="active_level" value="">
					<input type="hidden" name="pop_yn" value="">
					<input type="hidden" name="target_type" value="">
					<div class="ul_style search">
						<ul>
							<li>
								<p>조직명</p>
								<div>
									<input autocomplete="off"  type="text" name="org_list_search"  placeholder="입력하세요.">
									<button type="button" class="btn"><i class="ri-search-line"></i></button>
								</div>
							</li>
						</ul>
					</div>
					<div class="tree">
					</div>
				</div>
				<div class="btn_area">
					<a class="btn"><i class="ri-check-line"></i>확인</a>
				</div>
			</form>
		</div>
	</div>
</div>

<!--// 소속팀 검색 팝업-->


<!-- 저장품 -->
<div class="dialog order" id="storage_order">
	<!-- <div class="overlay"></div> -->
	<div class="content">
		<div class="top comm">
			<p>■ <span>저장품</span></p>
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="breakdown_part_list" method="post">	
				<input type="hidden" name="page" value="1"/>
				<input type="hidden" name="section" value="10"/>	
				<input type="hidden" name="column" value="or_seq"/>
				<input type="hidden" name="order" value="DESC"/>	
				<input type="hidden" name="target" value=""/>	
				<input type="hidden" name="target_num" value=""/>	
				<div class="search">
					<ul>
						<li>
							<div class="slt">
								<select name="search_column">
									<option value="">전체</option>
									<option value="og_name">요청부서</option>
									<option value="pt_code">부품코드</option>
									<option value="sys_name">부품타입</option>
									<option value="pt_name">부품명</option>
									<option value="or_total">수량</option>
								</select>
							</div>
							<div class="sch_inp">
								<input autocomplete="off"  type="text" name="search_word" placeholder="입력하세요.">
								<a class="btn"><i class="ri-search-line"></i></a>
							</div>
						</li>
					</ul>
					<div class="btn_area">
						<a class="btn sky" data-dialog="spare_order">예비품</a>
					</div>
				</div>
				<div class="tb">
					<div class="table tb_scroll">
						<table>
							<thead>
								<tr>
									<th data-column="og_name" data-order="ASC">요청부서</th>
									<th data-column="pt_code" data-order="ASC">부품코드</th>
									<th data-column="sys_name" data-order="ASC">부품타입</th>
									<th data-column="pt_name" data-order="ASC">부품명</th>
									<th data-column="or_total" data-order="ASC">수량</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div class="tb_bottom">
						<div class="slt">
							<select name="row">
								<option value="10" selected>10개씩 보기</option>
								<option value="20">20개씩 보기</option>
								<option value="50">50개씩 보기</option>
							</select>
						</div>
						
						<div class="count">
							<p><span>00</span>건이 검색되었습니다.</p>
						</div>
						<ul class="paging pagination">
						</ul>
					</div>
				</div>
				<div class="btn_area type2">
					<button class="btn"><i class="ri-check-line"></i>확인</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //저장품 -->


<!-- 예비품 -->
<div class="dialog order" id="spare_order">
	<!-- <div class="overlay"></div> -->
	<div class="content">
		<div class="top comm">
			<p>■ <span>예비품</span></p>
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="breakdown_part_list2" method="post">	
				<input type="hidden" name="page" value="1"/>
				<input type="hidden" name="section" value="11"/>	
				<input type="hidden" name="column" value="seq"/>
				<input type="hidden" name="order" value="DESC"/>	
				<input type="hidden" name="target" value=""/>	
				<div class="search">
					<ul>
						<li>
							<div class="slt">
								<select name="search_column">
									<option value="">전체</option>
									<option value="pt_code">부품코드</option>
									<option value="ds_name">기기명</option>
									<option value="pt_name">부품명</option>
									<option value="pt_spec">부품사양</option>
								</select>
							</div>
							<div class="sch_inp">
								<input autocomplete="off"  type="text" name="search_word" placeholder="입력하세요.">
								<a class="btn"><i class="ri-search-line"></i></a>
							</div>
						</li>
					</ul>
					<div class="btn_area">
						<a class="btn sky" data-dialog="storage_order">저장품</a>
					</div>
				</div>
				<div class="tb">
					<div class="table tb_scroll">
						<table>
							<thead>
								<tr>
									<th data-column="bg_seq" data-order="ASC">구분</th>
									<th data-column="pt_code" data-order="ASC">부품코드</th>
									<th data-column="ds_name" data-order="ASC">기기명</th>
									<th data-column="pt_name" data-order="ASC">부품명</th>
									<th data-column="pt_spec" data-order="ASC">부품사양</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div class="tb_bottom">
						<div class="slt">
							<select name="row">
								<option value="10" selected>10개씩 보기</option>
								<option value="20">20개씩 보기</option>
								<option value="50">50개씩 보기</option>
							</select>
						</div>
						
						<div class="count">
							<p><span>00</span>건이 검색되었습니다.</p>
						</div>
						<ul class="paging pagination">
						</ul>
					</div>
				</div>
				<div class="btn_area type2">
					<button class="btn"><i class="ri-check-line"></i>확인</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //예비품 -->


<!-- 고장번호검색 -->
<div class="dialog" id="error_num_sch">
	<!-- <div class="overlay"></div> -->
	<div class="content">
		<div class="top comm">
			<p>■ <span>고장접수</span></p>
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="breakdown_list">
				<input  type="hidden" name="page" value="1"/>
				<input  type="hidden" name="column" value="bk_receipt"/>
				<input  type="hidden" name="order" value="ASC"/>
				<input  type="hidden" name="section" value="10"/>
				<input  type="hidden" name="target" value=""/>
				<div class="search">
					<ul>
						<li>
							<div class="slt">
								<select name="search_case">
									<option value="pop_s1" selected>고장기준</option>
									<option value="pop_s2">기기명</option>
									<option value="pop_s3">설비위치</option>
								</select>
							</div>
							<div>
								<div class="pop_s1">
									<select name="bk_standard" class="pop_s1">
										<option value="">전체</option>
										<option value="1">단순정비</option>
										<option value="2">경정비</option>
										<option value="3">중정비</option>
										<option value="4">입고수리</option>
										<option value="5 ">원상복귀</option>
									</select>
								</div>
								<!--
								<div class="pop_s2">
									<input type="date" name="start_date" placeholder="날짜를 선택하세요">&nbsp;&nbsp;- &nbsp;&nbsp;
									<input type="date" name="end_date" placeholder="날짜를 선택하세요">
								</div>-->
								<div class="pop_s2">
									<input autocomplete="off"  type="text" name="dc_name" placeholder="기기명" >
								</div>
								<div class="pop_s3">
									<input autocomplete="off"  type="text" name="dc_location" placeholder="설비위치">
								</div>
								<button class="btn pop_search_submit"><i class="ri-search-line"></i></button>
							</div>
						</li>
					</ul>
				</div>
				<div class="tb">
					<div class="table tb_scroll">
						<table>
							<thead>
								<tr>
									<th data-column="bk_standard" data-order="ASC">고장기준</th>
									<th data-column="bk_receipt" data-order="ASC">접수일시</th>
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
								<option value="10" selected>10개씩 보기</option>
								<option value="20">20개씩 보기</option>
								<option value="50">50개씩 보기</option>
							</select>
						</div>

						<div class="count">
							<p><span>00</span>건이 검색되었습니다.</p>
						</div>
						<ul class="paging pagination">
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //고장번호검색 -->


<!-- 차량검색 -->
<div class="dialog" id="car_sch">
	<!-- <div class="overlay"></div> -->
	<div class="content">
		<div class="top comm">
			<p>■ <span>차량현황</span></p>
			<a class="close_in"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="car_vehicle_list" method="POST">
				<input type="hidden" name="page" value="1"/>
				<input type="hidden" name="section" value="10"/>	
				<input type="hidden" name="column" value="ve_number"/>
				<input type="hidden" name="order" value="ASC"/>	
				<input type="hidden" name="target" value=""/>	
				<div class="search">
					<ul>
						<li>
							<div class="slt">
								<select name="search_column">
									<option value="ve_number">차량번호</option>
									<option value="ve_name">차명</option>
									<option value="ve_model">차종</option>
									<option value="ve_fuel">연료</option>
								</select>
							</div>
							<div>
								<input autocomplete="off" type="text" name="search_word" placeholder="입력하세요." />
								<a class="btn"><i class="ri-search-line"></i></a>
							</div>
						</li>
					</ul>
				</div>
				<div class="tb">
					<div class="table tb_scroll">
						<table>
							<thead>
								<tr>
									<th data-column="ve_number" data-order="ASC">차량번호</th>
									<th data-column="ve_name" data-order="ASC">차명</th>
									<th data-column="ve_model" data-order="ASC">차종</th>
									<th data-column="ve_fuel" data-order="ASC" >연료</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div class="tb_bottom">
						<div class="slt">
							<select name="row">
								<option value="10" selected>10개씩 보기</option>
								<option value="20">20개씩 보기</option>
								<option value="30">50개씩 보기</option>
							</select>
						</div>

						<div class="count">
							<p><span id="total_car">0</span>건이 검색되었습니다.</p>
						</div>
						<ul class="paging pagination">
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //차량검색 -->


<!-- 기본 저장 팝업 -->
<div class="dialog basic chk_pop" id="save_pop">
	<div class="overlay"></div>
	<div class="content">
		<div class="top"></div>
		<div class="body">
			<p>저장 하시겠습니까?</p>
			<div class="btn_area">
				<a class="btn point"><i class="ri-check-line"></i>예</a>
				<a class="btn close"><i class="ri-close-line"></i>아니오</a>
			</div>
		</div>
	</div>
</div>

<!-- 저장되었습니다 팝업 -->
<div class="dialog basic chk_pop" id="save_chk_pop">
	<div class="overlay"></div>
	<div class="content">
		<div class="top"></div>
		<div class="body">
			<p>저장 되었습니다.</p>
			<div class="btn_area">
				<a class="btn point close"><i class="ri-check-line"></i>확인</a>
			</div>
		</div>
	</div>
</div>

<!-- 추가 확인 팝업 -->
<div class="dialog basic chk_pop" id="add_chk_pop">
	<div class="overlay"></div>
	<div class="content">
		<div class="top"></div>
		<div class="body">
			<p>해당 내용을 추가 하시겠습니까?</p>
			<div class="btn_area">
				<a class="btn point"><i class="ri-check-line"></i>확인</a>
				<a class="btn close"><i class="ri-close-line"></i>취소</a>
			</div>
		</div>
	</div>
</div>

<!-- 삭제 확인 팝업 -->
<div class="dialog basic chk_pop" id="delete_pop">
	<div class="overlay"></div>
	<div class="content">
		<div class="top"></div>
		<div class="body">
			<p>삭제 하시겠습니까?</p>
			<div class="btn_area">
				<a class="btn point" ><i class="ri-check-line"></i>예</a>
				<a class="btn close del_close"><i class="ri-close-line"></i>아니오</a>
			</div>
		</div>
	</div>
</div>


<!-- 삭제되었습니다 팝업 -->
<div class="dialog basic chk_pop" id="del_chk_pop">
	<div class="overlay"></div>
	<div class="content">
		<div class="top"></div>
		<div class="body">
			<p>삭제되었습니다.</p>
			<div class="btn_area">
				<a class="btn point close"><i class="ri-check-line"></i>확인</a>
			</div>
		</div>
	</div>
</div>

