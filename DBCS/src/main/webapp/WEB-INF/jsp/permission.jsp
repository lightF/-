<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--    여기부터 각페이지 본문       -->
<div class="cont permission">
	<div class="title">
		<p>권한관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<form name="auth_list" action="" method="post">
			<div class="tb">
				<div class="table">
					<table>	
						<thead>
							<tr>
								<th>
									<div class="mu2line">
										<p class="high">메뉴명</p>
										<span></span>
										<p class="low">권한명</p>
									</div>
								</th>
								<th>최고관리자</th>
								<th>중간관리자</th>
								<th>일반사용자</th>
							</tr>
						</thead>
						<tbody>
<!--							<tr class="board">
								<td class="mu_depth1">
									<span></span>게시판
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><input type="checkbox" name="auth_use" value="1" id="auth_use1"><label for="auth_use1">메뉴 숨기기</label></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_admin_11">
														<label for="board_admin_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_admin_12">
														<label for="board_admin_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_admin_21" >
														<label for="board_admin_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_admin_22">
														<label for="board_admin_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_admin_31">
														<label for="board_admin_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_admin_32">
														<label for="board_admin_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_admin_33">
														<label for="board_admin_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><input type="checkbox" name="auth_use" value="1" id="auth_use2"><label for="auth_use2">메뉴 숨기기</label></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_middle_11">
														<label for="board_middle_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_middle_12">
														<label for="board_middle_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_middle_21" >
														<label for="board_middle_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_middle_22">
														<label for="board_middle_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_middle_31">
														<label for="board_middle_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_middle_32">
														<label for="board_middle_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_middle_33">
														<label for="board_middle_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><input type="checkbox" name="auth_use[]" value="1" id="auth_use3"><label for="auth_use3">메뉴 숨기기</label></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_basic_11">
														<label for="board_basic_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_basic_12">
														<label for="board_basic_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_basic_21" >
														<label for="board_basic_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_basic_22">
														<label for="board_basic_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_basic_31">
														<label for="board_basic_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_basic_32">
														<label for="board_basic_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="board_admin" id="board_basic_33">
														<label for="board_basic_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="board_sub sub">
								<td class="mu_depth2">
									<span></span>공지사항
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_admin_11">
														<label for="notice_admin_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_admin_12">
														<label for="notice_admin_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_admin_21" >
														<label for="notice_admin_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_admin_22">
														<label for="notice_admin_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_admin_31">
														<label for="notice_admin_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_admin_32">
														<label for="notice_admin_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_admin_33">
														<label for="notice_admin_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_middle_11">
														<label for="notice_middle_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_middle_12">
														<label for="notice_middle_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_middle_21" >
														<label for="notice_middle_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_middle_22">
														<label for="notice_middle_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_middle_31">
														<label for="notice_middle_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_middle_32">
														<label for="notice_middle_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_middle_33">
														<label for="notice_middle_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_basic_11">
														<label for="notice_basic_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_basic_12">
														<label for="notice_basic_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_basic_21" >
														<label for="notice_basic_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_basic_22">
														<label for="notice_basic_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_basic_31">
														<label for="notice_basic_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_basic_32">
														<label for="notice_basic_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="notice_admin" id="notice_basic_33">
														<label for="notice_basic_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="board_sub sub">
								<td class="mu_depth2">
									<span></span>사원게시판
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_admin_11">
														<label for="emp_bd_admin_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_admin_12">
														<label for="emp_bd_admin_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_admin_21" >
														<label for="emp_bd_admin_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_admin_22">
														<label for="emp_bd_admin_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bde_admin_31">
														<label for="emp_bd_admin_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_admin_32">
														<label for="emp_bd_admin_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_admin_33">
														<label for="emp_bd_admin_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_middle_11">
														<label for="emp_bd_middle_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_middle_12">
														<label for="emp_bd_middle_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_middle_21" >
														<label for="emp_bd_middle_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_middle_22">
														<label for="emp_bd_middle_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_middle_31">
														<label for="emp_bd_middle_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_middle_32">
														<label for="emp_bd_middle_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_middle_33">
														<label for="emp_bd_middle_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_basic_11">
														<label for="emp_bd_basic_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_basic_12">
														<label for="emp_bd_basic_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_basic_21" >
														<label for="emp_bd_basic_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_basic_22">
														<label for="emp_bd_basic_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_basic_31">
														<label for="emp_bd_basic_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_basic_32">
														<label for="emp_bd_basic_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="emp_bd_admin" id="emp_bd_basic_33">
														<label for="emp_bd_basic_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
							</tr>

							<tr class="facility">
								<td class="mu_depth1">
									<span></span>시설관리
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_admin_11">
														<label for="faci_admin_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_admin_12">
														<label for="faci_admin_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_admin_21" >
														<label for="faci_admin_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_admin_22">
														<label for="faci_admin_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_admin_31">
														<label for="faci_admin_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_admin_32">
														<label for="faci_admin_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_admin_33">
														<label for="faci_admin_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_middle_11">
														<label for="faci_middle_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_middle_12">
														<label for="faci_middle_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_middle_21" >
														<label for="faci_middle_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_middle_22">
														<label for="faci_middle_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_middle_31">
														<label for="faci_middle_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_middle_32">
														<label for="faci_middle_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_middle_33">
														<label for="faci_middle_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_basic_11">
														<label for="faci_basic_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_basic_12">
														<label for="faci_basic_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_basic_21" >
														<label for="faci_basic_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_basic_22">
														<label for="faci_basic_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_basic_31">
														<label for="faci_basic_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_basic_32">
														<label for="faci_basic_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci_admin" id="faci_basic_33">
														<label for="faci_basic_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="facility_sub sub">
								<td class="mu_depth2">
									<span></span>시설관리
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_admin_11">
														<label for="faci2_admin_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_admin_12">
														<label for="faci2_admin_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_admin_21" >
														<label for="faci2_admin_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_admin_22">
														<label for="faci2_admin_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_admin_31">
														<label for="faci2_admin_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_admin_32">
														<label for="faci2_admin_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_admin_33">
														<label for="faci2_admin_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_middle_11">
														<label for="faci2_middle_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_middle_12">
														<label for="faci2_middle_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_middle_21" >
														<label for="faci2_middle_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_middle_22">
														<label for="faci2_middle_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_middle_31">
														<label for="faci2_middle_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_middle_32">
														<label for="faci2_middle_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_middle_33">
														<label for="faci2_middle_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_basic_11">
														<label for="faci2_basic_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_basic_12">
														<label for="faci2_basic_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_basic_21" >
														<label for="faci2_basic_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_basic_22">
														<label for="faci2_basic_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_basic_31">
														<label for="faci2_basic_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_basic_32">
														<label for="faci2_basic_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci2_admin" id="faci2_basic_33">
														<label for="faci2_basic_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="facility_sub sub">
								<td class="mu_depth2">
									<span></span>시설통계
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_admin_11">
														<label for="faci3_admin_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_admin_12">
														<label for="faci3_admin_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_admin_21" >
														<label for="faci3_admin_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_admin_22">
														<label for="faci3_admin_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_admin_31">
														<label for="faci3_admin_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_admin_32">
														<label for="faci3_admin_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_admin_33">
														<label for="faci3_admin_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_middle_11">
														<label for="faci3_middle_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_middle_12">
														<label for="faci3_middle_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_middle_21" >
														<label for="faci3_middle_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_middle_22">
														<label for="faci3_middle_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_middle_31">
														<label for="faci3_middle_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_middle_32">
														<label for="faci3_middle_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_middle_33">
														<label for="faci3_middle_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<div>
										<ul>
											<li>
												<div class="txt"><a>메뉴 숨기기</a></div>
												<div class="chk">
													<div>조회</div>
													<div>생성</div>
													<div>편집</div>
												</div>
											</li>
											<li>
												<div class="txt">전체 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_basic_11">
														<label for="faci3_basic_11"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_basic_12">
														<label for="faci3_basic_12"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">조직 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_basic_21" >
														<label for="faci3_basic_21"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_basic_22">
														<label for="faci3_basic_22"><span></span></label>
													</div>
												</div>
											</li>
											<li>
												<div class="txt">내 게시물</div>
												<div class="chk">
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_basic_31">
														<label for="faci3_basic_31"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_basic_32">
														<label for="faci3_basic_32"><span></span></label>
													</div>
													<div class="checkbox">
														<input type="checkbox" name="faci3_admin" id="faci3_basic_33">
														<label for="faci3_basic_33"><span></span></label>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</td>
							</tr>-->
						</tbody>
					</table>
				</div>
				<div class="btn_area">
					<button class="btn"><i class="ri-check-line"></i>저장하기</button>
				</div>
			</div>
		</form>
	</div>
</div>
<!--  //  여기까지 각페이지 본문       -->

<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/permission.js"></script>