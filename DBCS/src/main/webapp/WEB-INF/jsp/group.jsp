<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--    여기부터 각페이지 본문       -->
<div class="cont group">
	<div class="title">
		<p>조직관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="org_search" method="post">
				<input type="hidden" name="page" value="1" >
				<input type="hidden" name="column" value="og_name" >
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="section" value="1">

				<input type="hidden" name="row" value="15" >
				<input type="hidden" name="seq" value="1" >
				<ul>
					<li>
						<p>등급</p>
						<div>
							<select name="og_level">
								<option value="">전체</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
							</select>
						</div>
					</li>
					<li>
						<p>조직명</p>
						<div>
							<input type="text" name="og_name" placeholder="입력하세요.">
						</div>
					</li>
					<li>
						<p>구성원</p>
						<div>
							<input type="text" name="og_person" placeholder="입력하세요.">
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색<i class="ri-search-line"></i></button>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table tb_type2 tb_scroll">
				<table class="org_list">
					<thead>
						<tr>
							<th>등급</th>
							<th data-column="og_name" data-order="ASC" class="sort">조직명</th>
							<th data-column="og_person" data-order="ASC" ></th>
							<th data-column="create_date" data-order="ASC">조직생성일</th>
							<th data-column="og_status" data-order="ASC">상태</th>
						</tr>
					</thead>
					<tbody>
					<!--
						<tr>
							<td>1</td>
							<td class="group_name">
								<div>
									<p>대보정보통신</p>
									<a data-dialog="group_add"><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a data-dialog="group_mb">전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>2</td>
							<td class="group_name">
								<div>
									<p class="depth2">HTC</p>
									<a data-dialog="group_add"><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a data-dialog="group_mb">전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>3</td>
							<td class="group_name">
								<div>
									<p class="depth3">사업1본부</p>
									<a data-dialog="group_add"><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a data-dialog="group_mb">전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>4</td>
							<td class="group_name">
								<div>
									<p class="depth4">SM관리팀</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>비활성</td>
						</tr>
						<tr>
							<td>5</td>
							<td class="group_name">
								<div>
									<p class="depth4">SM사업팀</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>비활성</td>
						</tr>
						<tr>
							<td>6</td>
							<td class="group_name">
								<div>
									<p class="depth4">SM개발팀</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>7</td>
							<td class="group_name">
								<div>
									<p class="depth4">SM관리팀</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>8</td>
							<td class="group_name">
								<div>
									<p class="depth4">공항사업단</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>비활성</td>
						</tr>
						<tr>
							<td>9</td>
							<td class="group_name">
								<div>
									<p class="depth4">도로공사</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>비활성</td>
						</tr>
						<tr>
							<td>9</td>
							<td class="group_name">
								<div>
									<p class="depth3">본사</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>비활성</td>
						</tr>
						<tr>
							<td>10</td>
							<td class="group_name">
								<div>
									<p class="depth4">NVIDIA사업부</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>11</td>
							<td class="group_name">
								<div>
									<p class="depth4">스마트ICT사업</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>2</td>
							<td class="group_name">
								<div>
									<p class="depth2">HTC</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>3</td>
							<td class="group_name">
								<div>
									<p class="depth3">사업1본부</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>4</td>
							<td class="group_name">
								<div>
									<p class="depth4">SM관리팀</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>비활성</td>
						</tr>
						<tr>
							<td>5</td>
							<td class="group_name">
								<div>
									<p class="depth4">SM사업팀</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>비활성</td>
						</tr>
						<tr>
							<td>6</td>
							<td class="group_name">
								<div>
									<p class="depth4">SM개발팀</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>7</td>
							<td class="group_name">
								<div>
									<p class="depth4">SM관리팀</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>활성</td>
						</tr>
						<tr>
							<td>8</td>
							<td class="group_name">
								<div>
									<p class="depth4">공항사업단</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>비활성</td>
						</tr>
						<tr>
							<td>9</td>
							<td class="group_name">
								<div>
									<p class="depth4">도로공사</p>
									<a><i class="ri-add-line"></i>하위조직추가</a>
								</div>
							</td>
							<td class="member">
								<div>
									<p>홍길동, 홍길동, 홍길동</p>
									<a>전체보기</a>
								</div>
							</td>
							<td>20221230</td>
							<td>비활성</td>
						</tr>
						-->
					</tbody>
				</table>
			</div>
			<!--
			<div class="tb_bottom">
				 <div class="slt">
				    <select name="">
						<option value="5">5개씩 보기</option>
						<option value="10">10개씩 보기</option>
						<option value="15">15개씩 보기</option>
						<option value="20">20개씩 보기</option>
						<option value="25">25개씩 보기</option>
						<option value="50">50개씩 보기</option>
						<option value="100">100개씩 보기</option>
					</select>
				</div> 
				<div class="count">
					<p><span class="alert"></span></p>
				</div>
				<ul class="pagination">
					<li><a><img src="${resourcePath}/css/img/prev_prev.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/prev_.png"></a></li>
					<li><a class="active">1</a></li>
					<li><a>2</a></li>
					<li><a>3</a></li>
					<li><a>4</a></li>
					<li><a>5</a></li>
					<li><a><img src="${resourcePath}/css/img/next_.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/next_next.png"></a></li>
				</ul>
			</div>
			-->
			<div class="down_area">
				<a class="btn list_xlsx sky">Xlsx 다운로드</a>
			</div>
		</div>
	</div>
</div>
<!--  //  여기까지 각페이지 본문       -->



<!-- 하위조직추가팝업 -->
<div class="dialog group_pop gadd" id="group_add">
	<div class="content">
		<div class="top">
			<p>하위조직추가</p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="" action="" method="">
				<div class="ul_style">
					<ul>
						<li>
							<p>조직명</p>
							<div>
								<input type="text" name="" placeholder="입력하세요.">
							</div>
						</li>
						<li>
							<p>조직생성일</p>
							<div>
								<input type="date" name="">
							</div>
						</li>
						<li class="state">
							<p>상태</p>
							<div>
								<select name="">
									<option value="0">선택하세요</option>
									<option value="1">활성</option>
									<option value="2">비활성</option>
								</select>
							</div>
						</li>
						<li class="chk">
							<p>사업단</p>
							<div>
								<input type="checkbox" name="" id="group_chk">
								<label for="group_chk"><i class="ri-check-line"></i></label>
							</div>
						</li>
					</ul>
				</div>
				<div class="btn_area">
					<button class="btn point" data-dialog="group_chk_pop"><i class="ri-check-line"></i>저장</button>
				</div>
			</form>
		</div>
	</div>
</div>


<!-- 하위조직수정팝업 -->
<div class="dialog group_pop" id="group_re">
	<div class="content">
		<div class="top">
			<p>하위조직수정</p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="org_detail" method="post">
				<input type="hidden" name="seq" value="">
				<input type="hidden" name="og_code" value="">
				<input type="hidden" name="og_pcode" value="">

				<div class="ul_style">
					<ul>
						<li>
							<p>조직명</p>
							<div>
								<input type="text" name="og_name" placeholder="입력하세요." value="">
							</div>
						</li>
						<li>
							<p>조직생성일</p>
							<div>
								<input type="date" name="og_date">
							</div>
						</li>
						<li class="state">
							<p>상태</p>
							<div>
								<select name="og_status">
									<option value="0">선택</option>
									<option value="1">활성</option>
									<option value="2">비활성</option>
								</select>
							</div>
						</li>
						<li class="chk">
							<p>사업단</p>
							<div>
								<input type="checkbox" id="group_chk_re" name="og_group">
								<label for="group_chk_re"><i class="ri-check-line"></i></label>
							</div>
						</li>
					</ul>
				</div>
				<div class="btn_area">
					<button type="button" class="btn point btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>


<!-- 구성원 전체보기팝업 -->
<div class="dialog group_mb_pop" id="group_mb">
	<div class="content">
		<div class="top">
			<p>경기사업단 인원현황</p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="org_person_list" method="">
				<input type="hidden" name="seq" value="">
				<input type="hidden" name="column" value="per_seq">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">

				<div class="search">
					<div class="radio">
						<input type="radio" name="group" id="group1" value="1" checked>
						<label for="group1"><span></span>현재조직</label>
					</div>
					<div class="radio">
						<input type="radio" name="group" id="group2" value="2">
						<label for="group2"><span></span>하위조직포함</label>
					</div>
					<a class="photo_view" data-active="photo">
						<i class="ri-account-box-fill"></i>사진으로 보기
					</a>
					<a class="table_view none" data-active="table">
						<i class="ri-file-list-fill"></i>테이블로 보기
					</a>
				</div>
			</form>
			<div class="tb group_mb_tb ">
				<div class="table tb_scroll">
					<table>
						<thead>
							<tr>
								<th>사번</th>
								<th>이름</th>
								<th>직위</th>
								<th>직책</th>
							</tr>
						</thead>
						<tbody>
							<!--
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							<tr>
								<td>2022101</td>
								<td>홍길동</td>
								<td>소장</td>
								<td>책임자</td>
							</tr>
							-->
						</tbody>
					</table>
				</div>
				
			</div>
			<div class="photo">
				<ul>
					<li>
						<div class="img">
							<img src="${resourcePath}/css/img/hajun.jpg">
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
					<li>
						<div class="img">
							
						</div>
						<div class="info">
							<p>ITS총괄</p>
							<p>부장</p>
							<p>이길동</p>
						</div>
					</li>
				</ul>
			</div>

			<div class="tb_bottom">
				<div class="slt">
					<select name="row">
						<option value="5">5개씩 보기</option>
						<option value="10">10개씩 보기</option>
						<option value="15">15개씩 보기</option>
						<option value="20">20개씩 보기</option>
						<option value="25">25개씩 보기</option>
						<option value="50">50개씩 보기</option>
						<option value="100">100개씩 보기</option>
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

<!-- 조직생성확인 팝업 -->
<div class="dialog basic chk_pop" id="group_chk_pop">
	<div class="content">
		<div class="top"></div>
		<div class="body">
			<p>조직명이 생성 되었습니다.</p>
			<div class="btn_area">
				<a class="btn point close"><i class="ri-check-line"></i>확인</a>
			</div>
		</div>
	</div>
</div>

<!-- 조직생성확인 팝업 -->
<div class="dialog basic chk_pop" id="group_info_pop">
	<div class="content">
		<div class="top"></div>
		<div class="body">
			<p>조직명은 6레벨까지 생성 가능합니다</p>
			<div class="btn_area">
				<a class="btn point close"><i class="ri-check-line"></i>확인</a>
			</div>
		</div>
	</div>
</div>




<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/group.js"></script>