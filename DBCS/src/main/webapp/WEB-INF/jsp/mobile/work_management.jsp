<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.jsp" %>

<div class="m_cont">
	<div class="m_work">
		<div class="top">
			<a class="btn sch_btn">상세검색</a>
			<div class="m_search">
				<div class="overlay"></div>
				<div class="sch_box">
					<div class="top_tit">
						<p>콜관리 > <span>상세검색</span></p>
					</div>
					<form name="work_search" method="post">
						<input type="hidden" name="column" value="seq">
						<input type="hidden" name="order" value="DESC">
						<input type="hidden" name="page" value="1">

						<ul>
							<li>
								<p>조회기간</p>
								<div class="start">
									<input type="date" name="start_date" placeholder="시작일">
								</div>
								<div class="end">
									<input type="date" name="end_date" placeholder="종료일">
								</div>
							</li>
							<li>
								<p>사업단</p>
								<div>
									<input type="hidden" name="wrk_seq" class="group_seq">
									<input type="text" name="wrk_group" class="group_name"placeholder="점검팀 검색시 자동입력">
								</div>
							</li>
							<li>
								<p>점검팀</p>
								<div class="sch_inp">
									<input type="hidden" name="pname" >
									<input type="hidden" name="pcode" >
									<input type="text" name="wrk_check" placeholder="검색" readonly required>
									<a class="btn" data-dialog="team_sch"><i class="ri-search-line"></i></a>
								</div>
							</li>
							<li>
								<p>확인여부</p>
								<div>
									<select name="wrk_confirm">
										<option value="">전체</option>
										<option value="1">확인중</option>
										<option value="2">승인불가</option>
										<option value="3">확인완료</option>
									</select>
								</div>
							</li>
						</ul>
						<div class="btn_area">
							<button type="button" class="btn btn_search"><i class="ri-search-line"></i>적용</button>
						</div>
					</form>
				</div>
			</div>
			<p class="title">콜관리</p>
			<a class="btn sky btn_add" data-type="new">근무등록</a>
		</div>
		<div class="m_ul_list">
			<div class="ul_list">
				<ul>
					<li>
						<p class="count">1</p>
						<a href="${contextPath}/work_management_view">
							<div class="list_st1">
								<p>사업단: <span class="group">경기사업단</span></p>
								<span class="line"></span>
								<p>점검팀: <span class="team">자가통신망2팀</span></p>
							</div>
							<div class="list_st2">
								<p>작업구분: <span class="work">부대업무</span></p>
								<span class="line"></span>
								<p>확인여부: <span class="check">확인중</span></p>
							</div>
							<div class="list_st3">
								<p>일자: <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>시간: <span class="time">15:00 ~ 18:00</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">1</p>
						<a href="${contextPath}/work_management_view">
							<div class="list_st1">
								<p>사업단: <span class="group">경기사업단</span></p>
								<span class="line"></span>
								<p>점검팀: <span class="team">자가통신망2팀</span></p>
							</div>
							<div class="list_st2">
								<p>작업구분: <span class="work">부대업무</span></p>
								<span class="line"></span>
								<p>확인여부: <span class="check">확인중</span></p>
							</div>
							<div class="list_st3">
								<p>일자: <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>시간: <span class="time">15:00 ~ 18:00</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">1</p>
						<a href="${contextPath}/work_management_view">
							<div class="list_st1">
								<p>사업단: <span class="group">경기사업단</span></p>
								<span class="line"></span>
								<p>점검팀: <span class="team">자가통신망2팀</span></p>
							</div>
							<div class="list_st2">
								<p>작업구분: <span class="work">부대업무</span></p>
								<span class="line"></span>
								<p>확인여부: <span class="check">확인중</span></p>
							</div>
							<div class="list_st3">
								<p>일자: <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>시간: <span class="time">15:00 ~ 18:00</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">1</p>
						<a href="${contextPath}/work_management_view">
							<div class="list_st1">
								<p>사업단: <span class="group">경기사업단</span></p>
								<span class="line"></span>
								<p>점검팀: <span class="team">자가통신망2팀</span></p>
							</div>
							<div class="list_st2">
								<p>작업구분: <span class="work">부대업무</span></p>
								<span class="line"></span>
								<p>확인여부: <span class="check">확인중</span></p>
							</div>
							<div class="list_st3">
								<p>일자: <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>시간: <span class="time">15:00 ~ 18:00</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">1</p>
						<a href="${contextPath}/work_managementk_view">
							<div class="list_st1">
								<p>사업단: <span class="group">경기사업단</span></p>
								<span class="line"></span>
								<p>점검팀: <span class="team">자가통신망2팀</span></p>
							</div>
							<div class="list_st2">
								<p>작업구분: <span class="work">부대업무</span></p>
								<span class="line"></span>
								<p>확인여부: <span class="check">확인중</span></p>
							</div>
							<div class="list_st3">
								<p>일자: <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>시간: <span class="time">15:00 ~ 18:00</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">1</p>
						<a href="${contextPath}/work_management_view">
							<div class="list_st1">
								<p>사업단: <span class="group">경기사업단</span></p>
								<span class="line"></span>
								<p>점검팀: <span class="team">자가통신망2팀</span></p>
							</div>
							<div class="list_st2">
								<p>작업구분: <span class="work">부대업무</span></p>
								<span class="line"></span>
								<p>확인여부: <span class="check">확인중</span></p>
							</div>
							<div class="list_st3">
								<p>일자: <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>시간: <span class="time">15:00 ~ 18:00</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">1</p>
						<a href="${contextPath}/work_management_view">
							<div class="list_st1">
								<p>사업단: <span class="group">경기사업단</span></p>
								<span class="line"></span>
								<p>점검팀: <span class="team">자가통신망2팀</span></p>
							</div>
							<div class="list_st2">
								<p>작업구분: <span class="work">부대업무</span></p>
								<span class="line"></span>
								<p>확인여부: <span class="check">확인중</span></p>
							</div>
							<div class="list_st3">
								<p>일자: <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>시간: <span class="time">15:00 ~ 18:00</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">1</p>
						<a href="${contextPath}/work_management_view">
							<div class="list_st1">
								<p>사업단: <span class="group">경기사업단</span></p>
								<span class="line"></span>
								<p>점검팀: <span class="team">자가통신망2팀</span></p>
							</div>
							<div class="list_st2">
								<p>작업구분: <span class="work">부대업무</span></p>
								<span class="line"></span>
								<p>확인여부: <span class="check">확인중</span></p>
							</div>
							<div class="list_st3">
								<p>일자: <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>시간: <span class="time">15:00 ~ 18:00</span></p>
							</div>
						</a>
					</li>
				</ul>
			</div>
			
			<div class="tb_bottom">
				<div class="slt">
					<select name="row">
						<option value="20">20개씩 보기</option>
						<option value="50">50개씩 보기</option>
						<option value="100">100개씩 보기</option>
					</select>
				</div>
				<div class="count">
					<p><span class="count_total">0</span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">
					<!--
					<li><a><img src="${resourcePath}/css/img/prev_prev.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/prev_.png"></a></li>
					<li><a class="active">1</a></li>
					<li><a>2</a></li>
					<li><a>3</a></li>
					<li><a>4</a></li>
					<li><a>5</a></li>
					<li><a><img src="${resourcePath}/css/img/next_.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/next_next.png"></a></li>
					-->
				</ul>
			</div>
		</div>
	</div>
</div>


<%@ include file="tail.jsp" %>
<script src="${resourcePath}/js/mobile/work_management.js"></script>