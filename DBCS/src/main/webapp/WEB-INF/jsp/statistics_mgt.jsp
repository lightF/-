<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>


<!--    여기부터 각페이지 본문       -->
<div class="cont statistics_mgt">
	<div class="title">
		<p>시설통계</p>
		<a class="favo"></a>
	</div>

	<div class="tab_area">
		<div class="tab">
			<a class="active" data-tab="tab1">사업단설비현황</a>
			<a class="" data-tab="tab2">이전/철거현황</a>
		</div>
		<div class="tab_box">
			<form name="frm_list" method="post">
				<input type="hidden" name="order" value="DESC">
				<input type="hidden" name="page" value="1">
			</form>

			<div class="box content" data-tab="tab1">
				<div class="wrap pop_print">
					<div class="tb">
						<div class="table tb1 tb_scroll">
							<table class="tbl_org">
								<thead>
									<tr>
										<th colspan="3">구분</th>
									</tr>
									<tr>
										<th>설비</th>
										<th>기기명</th>
										<th>합계</th>
									</tr>
								</thead>
								<tbody>
								
								</tbody>
							</table>
						</div>
						<div class="table tb2 tb_scroll">
							<table class="tbl_org2">
								<thead>
									<tr><!--
										<th>경기</th>
										<th>강원</th>
										<th>충청</th>
										<th>호남</th>
										<th>경북</th>
										<th>경남</th>
										<th>천안</th>
										-->
									</tr>

								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>

				</div>
				<!--
				<div class="tb_bottom">
					<div class="slt">
						<select name="row">
							<option value="20">20개씩 보기</option>
							<option value="50">50개씩 보기</option>
							<option value="100">100개씩 보기</option>
						</select>
					</div>
					<div class="count">
						<p><span class="count_total">00</span>건이 검색되었습니다.</p>
					</div>
					<ul class="paging pagination">
						
					</ul>
				</div>
				-->

				<div class="down_area">
					<a class="btn pdf_down sky" data-filename="사업단설비현황">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>

			<div class="box content" data-tab="tab2" style="display:none;">
				<div class="wrap pop_print">
					<div class="tb">
						<div class="table tb_scroll">
							<table class="tbl_history">
								<colgroup>
									<col width="">
									<col width="">
									<col width="28%">
									<col width="">
									<col width="">
									<col width="">
									<col width="">
									<col width="">
									<col width="">
								</colgroup>
								<thead>
									<tr>
										<th>이전일</th>
										<th>이전위치</th>
										<th>설치위치</th>
										<th>구분</th>
										<th>승인</th>
										<th>설치자</th>
										<th>설치부서</th>
										<th>진행상황</th>
										<th>설치건명</th>
									</tr>
								</thead>
								<tbody>
									<!--
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>20220915</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>20220915</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>20220915</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>20220915</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>20220915</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									<tr>
										<td>기존위치</td>
										<td>이전위치</td>
										<td>설치번호</td>
										<td>기기번호</td>
										<td>표준명</td>
										<td>이전/철거일자</td>
										<td>이전/철거사유</td>
										<td>진행상태</td>
										<td>
											<a href="javascript:;">확인</a>
										</td>
									</tr>
									-->
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!--
				<div class="tb_bottom">
					<div class="slt">
						<select name="row" required="">
							<option value="20">20개씩 보기</option>
							<option value="50">50개씩 보기</option>
							<option value="100">100개씩 보기</option>
						</select>
					</div>
					<div class="count">
						<p><span class="count_total">00</span>건이 검색되었습니다.</p>
					</div>
					<ul class="paging pagination">
						
					</ul>
				</div>
				-->

				<div class="down_area">
					<a class="btn pdf_down sky" data-filename="이전/철거현황">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
				</div>
			</div>
		</div>
	</div>
</div>

	



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/statistics_mgt.js"></script>