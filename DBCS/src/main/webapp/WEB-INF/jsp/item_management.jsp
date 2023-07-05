<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<link rel="stylesheet" href="${resourcePath}/css/jquery-ui.css" /> 

<!--        여기부터 각페이지 본문             -->
<div class="cont item_management">
	<div class="title">
		<p>항목 관리</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="inner">
			<div class="select_wrap">
				<select name="item_type">
					<option value="" selected>항목선택</option>
					<option value="_item_task_list">담당업무</option>
					<option value="_item_job_list">직책</option>
					<option value="_item_grade_list">직급</option>
					<option value="_item_position_list">직위</option>
					<option value="_item_edu_list">최종학력</option>
					<option value="_item_branch_list">지사</option>
					<option value="_item_process_list">처리구분</option>
					<option value="_item_system_list">설비</option>
					<option value="_item_division_list">직업구분</option>
					<option value="_item_type_list">근무형태</option>
					<option value="_item_pay_list">콜근무수당</option>
					<option value="_item_model_list">모델</option>
					<option value="_item_safe_list">안전용품</option>
					<option value="_item_fuel_list">연료</option>
					<option value="_item_enterprise_list">업체</option>
				</select>
			</div>
			<div class="table_wrap">
				<div class="tb">
					<form name="item" method="post">
					<table>
						<colgroup>
						</colgroup>
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
					</form>
					<div class="tb_btns_right">
						<ol>
						</ol>
					</div>
				</div>
				<div class="tb_btns_bt">
					<a class="add">추가</a>
					<a class="update">저장</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!--    //    여기까지 각페이지 본문             -->
<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/util/jquery-ui.min.js"></script>
<script type="text/javascript" src="${resourcePath}/js/item_management.js"></script>
