<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--    여기부터 각페이지 본문       -->
<div class="cont account">
	<div class="title">
		<p>거래처관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="frm_search" method="post" action="" >
				<input type="hidden" name="column" value="seq">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="section" value="">
				<ul><!-- 사용안함
					<li class="date">
						<p>조회기간</p>
						<div class="start">
							<input type="date" name="start_date" placeholder="시작일">
						</div>
						<div class="end">
							<input type="date" name="end_date" placeholder="종료일">
						</div>
					</li>
					-->
					<li class="calc">
						<p>거래처명</p>
						<div class="">
							<input type="text" name="act_company" placeholder="입력하세요." >
						</div>
					</li>
					<li class="calc">
						<p>대표자</p>
						<div>
							<input type="text" name="act_ceo" placeholder="입력하세요.">
						</div>
					</li>
					<li class="calc">
						<p>담당자</p>
						<div class="">
							<input type="text" name="act_manager" placeholder="입력하세요.">
						</div>
					</li>

				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-dialog="account_add_view" data-type="new">거래처등록<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table tb_scroll">
				<table class="tbl_account">
					<thead>
						<tr>
							<th data-column="seq" data-order="ASC" class="sort">거래처코드</th>
							<th data-column="act_company" data-order="ASC">거래처명</th>
							<th data-column="act_ceo"  data-order="ASC">대표자</th>
							<th data-column="act_manager" data-order="ASC">담당자</th>
							<th data-column="act_hp" data-order="ASC">담당번호</th>
							<th data-column="act_fax" data-order="ASC">팩스번호</th>
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
					<p><span class="count_tot"></span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">
				</ul>
			</div>
		</div>
	</div>
</div>
<!--  //  여기까지 각페이지 본문       -->




<!-- 거래처 상세보기 -->
<div class="dialog account_pop" id="account_add_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>거래처관리 > <span>상세 거래처관리</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="frm_detail" method="post" action="" >
				<input type="hidden" name="type" value="">
				<input type="hidden" name="seq" value="">
				<div class="log_box">
					<div class="ul_style">
						<ul class="wrap1">
							<li class="required">
								<p>거래처명<span class="ess"></span></p>
								<div>
									<input type="text" name="act_company" placeholder="입력하세요." value="거래처명" required>
								</div>
							</li>
							<li class="required">
								<p>사업자번호</p>
								<div>
									<input type="text" name="act_number" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>대표자명</p>
								<div>
									<input type="text" name="act_ceo" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>업태</p>
								<div>
									<input type="text" name="act_status" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>전화번호<span class="ess"></span></p>
								<div>
									<input type="text" name="act_phone" placeholder="입력하세요." value="010-1234-5678" required>
								</div>
							</li>
							<li class="required">
								<p>종목</p>
								<div>
									<input type="text" name="act_event" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>팩스번호</p>
								<div>
									<input type="text" name="act_fax" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="required">
								<p>이메일</p>
								<div>
									<input type="text" name="act_email" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="addr">
								<p>주소</p>
								<div class="zip_code">
									<input type="text" name="act_zip">
								</div>
								<div class="addr1 sch">
									<input type="text" name="act_addr" placeholder="입력하세요." >
									<a class="btn btn_addr"><i class="ri-search-line"></i></a>
								</div>
								<p class="px100">상세주소</p>
								<div class="addr2">
									<input type="text" name="act_detail"placeholder="입력하세요." >
								</div>
							</li>
							<!--
							<li class="required">
								<p>상세주소</p>
								<div>
									<input type="text" name="" placeholder="입력하세요." value="">
								</div>
							</li>-->
							<li class="required mr_10">
								<p>홈페이지</p>
								<div>
									<input type="text" name="act_url" placeholder="입력하세요." value="">
								</div>
							</li>
							<li class="mr_0">
								<p>담당자</p>
								<div>
									<input type="text" name="act_manager" placeholder="입력하세요.">
								</div>
							</li>
							<li class="mr_10">
								<p>담당자연락처</p>
								<div>
									<input type="text" name="act_hp" placeholder="입력하세요.">
								</div>
							</li>
							<li class="mr_0">
								<p>태그</p>
								<div>
									<input type="text" name="act_tag" placeholder="입력하세요.">
								</div>
							</li>
							<li>
								<p>사용여부</p>
								<div>
									<select name="act_use">
										<option value="0">선택</option>
										<option value="1" selected>예</option>
										<option value="2">아니요</option>
									</select>
								</div>
							</li>
						</ul>

					</div>
				</div>
				<div class="btn_area">
					<button type="button" class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn close"><i class="ri-close-line"></i>취소</span></a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- // 거래처 상세보기  -->

 

<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/account_mgt.js"></script>
