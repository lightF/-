<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.jsp" %>
<%@ page session="true" %>
<%
session = request.getSession();
Object ag_seq = session.getAttribute("ag_seq");
%>

<input type="hidden" name="ag_seq" value="<%=ag_seq%>">
<div class="m_cont">
	<div class="m_work_write write">
		<div class="top_area">
			<div class="top_tit">
				<p>콜관리 > <span>근무등록</span></p>
			</div>
			<a class="btn gray back" href="/DBCS/work_management"><i class="ri-arrow-go-back-line"></i></a>
		</div>
		<div class="body">
			<form name="call_work_detail" method="post">
				<input type="hidden" name="seq" value="">
				<div class="wrap">
					<p></p>
					<div class="ul_style">
						<ul>
							<li class="required">
								<p>근무일자<span class="ess"></span></p>
								<div>
									<!--<input type="text" name="wrk_date" placeholder="자동입력" required="required" readonly>-->
									<input type="date" name="wrk_date" class="on_date" placeholder="자동입력" required disabled>
								</div>
							</li>
							<li class="required">
								<p>점검팀<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="pname" value="">
									<input type="hidden" name="pcode" value="">
									<input type="hidden" name="wrk_check" value="">
									<input type="text" name="check_name" placeholder="검색하기" required readonly>
									<a class="btn" data-dialog="team_sch">
										<i class="ri-search-line"></i>
									</a>
								</div>
							</li>
							<li class="required">
								<p>사업단<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="wrk_group" class="group_seq" value="">
									<input type="text" name="group_name" class="group_name" placeholder="점검팀 선택시 자동입력" required="required" readonly>
									<!--<a class="btn" data-dialog="team_sch">
										<i class="ri-search-line"></i>
									</a>
									-->
								</div>
							</li>
							<li class="required">
								<p>설비분류<span class="ess"></span></p>
								<div>
									<select name="sys_seq" required="required">
									</select>
								</div>
							</li>
							<li>
								<p>고장번호</p>
								<div class="sch_inp">
									<input type="hidden" name="bk_seq" value="">
									<input type="text" name="bk_seq_name" placeholder="검색하기" readonly>
									<a class="btn" data-dialog="error_num_sch">
										<i class="ri-search-line"></i>
									</a>
								</div>
							</li>
							<li>
								<p>수리날짜</p>
								<div class="sch_inp">
									<input type="date" name="wrk_repair" >
								</div>
							</li>
							<li>
								<p>수리내역</p>
								<div class="sch_inp">
									<input type="text"name="wrk_history" placeholder="입력하세요"  >
								</div>
							</li>
							<li>
								<p>확인여부</p>
								<div>
									<select name="wrk_confirm">
										<option value="1" selected>확인중</option>
										<option value="2">승인불가</option>
										<option value="3">확인완료</option>
									</select>
								</div>
							</li>
							<li class="required">
								<p>작업시작시간<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="datetime-local" name="wrk_hour" required="required">
								</div>
							</li>
							<li class="required">
								<p>작업종료시간</p>
								<div class="sch_inp">
									<input type="datetime-local" name="wrk_end" >
								</div>
							</li>
							<li class="required">
								<p>작업구분<span class="ess"></p>
								<div>
									<select name="wd_seq" required="required">
										<!-- <option value="">선택</option>
										<option value="">부대업무</option>
										<option value="">고장수리</option>
										<option value="">유지보수</option>
										<option value="">대외업무</option>
										<option value="">기타</option>
										<option value="">특별근무(주)</option>
										<option value="">특별근무(야)</option>
										<option value="">특별근무(주야)</option> -->
									</select>
								</div>
							</li>
							<li class="required">
								<p>작업책임자<span class="ess"></span></p>
								<div class="sch_inp">
									<input type="hidden" name="per_seq" value="">
									<input type="text" name="per_name" placeholder="검색하기" required="required" readonly>
									<a class="btn" data-dialog="person_sch">
										<i class="ri-search-line"></i>
									</a>
								</div>
							</li>
							<li class="textarea">
								<p>작업내역</p>
								<div>
									<textarea name="wrk_work" placeholder="작업 내역을 입력하세요." required >1. 시설명
									2. 위치
									3. 고장내역
									4. 작업내역
									</textarea>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="wrap">
					<p class="left"></p>
					<a class="btn add_btn">+</a>
					<div class="tb">
						<div class="table table_btn">
							<table class="tb_1">
								<colgroup>
									<col width="35%">
									<col width="">
									<col width="30px">
								</colgroup>
								<tbody>
									<tr>
										<th>소속</th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="og_seq[]" value="">
												<input type="text" name="og_name[]" placeholder="검색하기" readonly>
												<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<td rowspan="8"><a class="del_btn">-</a></td>
									</tr>
									<tr>
										<th>근무자</th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="per_seq2[]" value="">
												<input type="text" name="per_name[]" placeholder="검색하기" readonly>
												<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
											</div>
										</td>
									</tr>
									<tr>
										<th>콜근무출발</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_start[]" placeholder="">
											</div>
										</td>
									</tr>
									<tr>
										<th>작업시작</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_work[]" placeholder="">
											</div>
										</td>
									</tr>
									<tr>
										<th>작업종료</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_end[]" placeholder="">
											</div>
										</td>
									</tr>
									<tr>
										<th>콜근무완료</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_finish[]" placeholder="">
											</div>
										</td>
									</tr>
									<tr>
										<th>총근무시간</th>
										<td>
											<input type="text" name="wrk_total[]" placeholder="자동계산">
										</td>
									</tr>
									<tr>
										<th>지급액</th>
										<td>
											<input type="text" name="wrk_amount[]" placeholder="자동계산">
										</td>
									</tr>
										
								<!--
									<input type="hidden" name="wkd_seq[]" value="">
									<tr>
										<th>소속</th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="og_seq[]" value="">
												<input type="text" name="og_name[]" placeholder="검색하기" readonly>
												<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<th>근무자</th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="per_seq2[]" value="">
												<input type="text" name="per_name[]" placeholder="검색하기" readonly>
												<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<th>콜근무출발</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_start[]" placeholder="">
											</div>
										</td>
										<th>작업시작</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_work[]" placeholder="">
											</div>
										</td>
										<th>작업종료</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_end[]" placeholder="">
											</div>
										</td>
										<th>콜근무완료</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_finish[]" placeholder="">
											</div>
										</td>
										<th>총근무시간</th>
										<td>
											<input type="text" name="wrk_total[]" placeholder="자동계산">
										</td>
										<th>지급액</th>
										<td>
											<input type="text" name="wrk_amount[]" placeholder="자동계산">
										</td>
										<th class="tb_btn"><a class="btn del_btn">-</a></th>
									</tr>
									<tr>
										<th>소속</th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="og_seq[]" value="">
												<input type="text" name="og_name[]" placeholder="검색하기" readonly>
												<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<th>근무자</th>
										<td>
											<div class="sch_inp">
												<input type="hidden" name="per_seq2[]" value="">
												<input type="text" name="per_name[]" placeholder="검색하기" readonly>
												<a class="btn" data-dialog="person_sch"><i class="ri-search-line"></i></a>
											</div>
										</td>
										<th>콜근무출발</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_start[]" placeholder="">
											</div>
										</td>
										<th>작업시작</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_work[]" placeholder="">
											</div>
										</td>
										<th>작업종료</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_end[]" placeholder="">
											</div>
										</td>
										<th>콜근무완료</th>
										<td>
											<div class="sch_inp">
												<input type="datetime-local" name="wrk_finish[]" placeholder="">
											</div>
										</td>
										<th>총근무시간</th>
										<td>
											<input type="text" name="wrk_total[]" placeholder="자동계산">
										</td>
										<th>지급액</th>
										<td>
											<input type="text" name="wrk_amount[]" placeholder="자동계산">
										</td>
										<th class="tb_btn"><a class="btn del_btn">-</a></th>
									</tr>
								-->
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="btn_area">
					<button type="button" class="btn save btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn gray close" href="${contextPath}/work_management"><i class="ri-close-line"></i>취소</a>
					<a class="btn del btn_remove"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>



<%@ include file="tail.jsp" %>
<script src="${resourcePath}/js/mobile/work_management_write.js"></script>