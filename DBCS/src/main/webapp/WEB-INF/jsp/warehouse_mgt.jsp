<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--    여기부터 각페이지 본문       -->
<div class="cont warehouse">
	<div class="title">
		<p>창고관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="storage_search" method="post">
				<input type="hidden" name="column" value="seq">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">

				<ul>
					<li class="w30">
						<p>창고코드</p>
						<div>
							<input type="text" name="seq" placeholder="입력하세요.">
						</div>
					</li>
					<li class="calc">
						<p>창고명</p>
						<div>
							<input type="text" name="sr_name" placeholder="입력하세요.">
						</div>
					</li>
					<li class="calc">
						<p>부서명</p>
						<div class="sch_inp">
							<input type="hidden" name="sr_division">
							<input type="text" name="sr_division_text" placeholder="검색하세요." readonly>
							<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
						</div>
					</li>
					<li class="calc">
						<p>유형</p>
						<div>
							<input type="text" name="sr_type" placeholder="입력하세요.">
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button class="btn btn_search">검색<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-dialog="wahs_re" data-type="new">창고등록<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table tb_scroll">
				<table class="storage_list">
					<thead>
						<tr>
							<th data-column="seq" data-order="DESC" class="sort">창고코드</th>
							<th data-column="sr_name" data-order="ASC">창고명</th>
							<th data-column="sr_division" data-order="ASC">부서명</th>
							<th data-column="sr_type" data-order="ASC">창고유형</th>
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


<!-- 창고관리 상세보기 팝업 -->
<div class="dialog wahs_pop" id="wahs_re">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>창고 관리 > <span>창고 등록</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="storage_detail" method="post" action="" >
				<div class="log_box">
					<div class="ul_style">
						<ul class="wrap1">
							<li class="">
								<p>창고코드</p>
								<div>
									<input type="text" name="seq" placeholder="자동생성" readonly >
								</div>
							</li>
							<li class="">
								<p>창고명<span class="ess"></span></p>
								<div>
									<input type="text" name="sr_name" required>
								</div>
							</li>
                            <li class="">
								<p>창고유형<span class="ess"></span></p>
								<div>
									<select name="sr_type" required>
										<option value="">선택</option>
										<option value="1">창고</option>
										<option value="2">캐비넷</option>
										<option value="3">차량</option>
									</select>
								</div>
							</li>	
							<li class="storage_org">
								<p>관리부서</p>
								<div class="sch_inp ">
									<input type="hidden" name="og_seq">
									<input type="text" name="og_seq_text" placeholder="검색하세요." readonly>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li class="add">
								<div class="sch_inp">
									<input type="text" name="" value="SM관리팀">
									<a class="btn del_btn">-</a>
								</div>								
							</li>
						</ul>
					</div>
				</div>
				<div class="btn_area">
					<button type="button" class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //창고관리 상세보기 팝업 -->



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/warehouse_mgt.js"></script>
