<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>


<!--    여기부터 각페이지 본문       -->
<div class="cont group standard">
	<div class="title">
		<p>표준명관리2</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="" action="" method="">
				<ul>
					<li>
						<p>등급</p>
						<div>
							<select name="level">
								<option value="9">전체</option>
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
						<p>표준명2</p>
						<div class="">
							<input type="text" name="ds_name" placeholder="입력하세요.">
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button type="button" class="btn btn_search">검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky btn_add" data-state="new">트리생성<i class="ri-pencil-fill"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table tb_type2">
				<table class="tbl_standard2 none">
					<thead>
						<tr>
							<th><div>등급</div></th>
							<th><div>표준명2</div></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<!--
			<div class="tb_bottom">
				<div class="slt">
					<select name="">
						<option value="">20개씩 보기</option>
						<option value="">50개씩 보기</option>
						<option value="">100개씩 보기</option>
					</select>
				</div>
				<div class="count">
					<p><span>00</span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging">
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
		</div>
	</div>
</div>
<!--  //  여기까지 각페이지 본문       -->


<!-- 하위조직추가팝업 -->
<div class="dialog group_pop" id="group_add">
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
							<p>표준명2</p>
							<div>
								<input type="text" name="" placeholder="입력하세요." required>
							</div>
						</li>
					</ul>
				</div>
				<div class="btn_area">
					<button class="btn"><i class="ri-check-line"></i>저장</button>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
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
			<form name="" action="" method="">
				<div class="ul_style">
					<ul>
						<li>
							<p>표준명2</p>
							<div>
								<input type="text" name="" placeholder="입력하세요." value="대보정보통신" required>
							</div>
						</li>
					</ul>
				</div>
				<div class="btn_area">
					<button class="btn"><i class="ri-check-line"></i>저장</button>
					<a class="btn del"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>


<!-- 트리생성팝업 -->
<div class="dialog group_pop" id="tree_write2">
	<div class="content">
		<div class="top">
			<p>트리생성</p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="" action="" method="">
				<input type="hidden" name="state" value="">
				<input type="hidden" name="seq" value="">
				<input type="hidden" name="ds_pcode" value="">
				<div class="ul_style">
					<ul>
						<li>
							<p>표준명</p>
							<div>
								<input type="text" name="ds_name" placeholder="입력하세요." required>
							</div>
						</li>
						<!--
						<li>
							<div class="chkbox">
								<input type="checkbox" name="tree_chk" id="tree_khc" required>
								<label for="tree_khc"><span></span>KHC</label>
							</div>
							<div class="chkbox">
								<input type="checkbox" name="tree_chk" id="tree_cne" required>
								<label for="tree_cne"><span></span>CNE</label>
							</div>
						</li>
						-->
					</ul>
				</div>
				<div class="btn_area">
					<button type="button" class="btn btn_save"><i class="ri-check-line"></i>저장</button>
					<a class="btn close btn_close"><i class="ri-close-line"></i><span>취소</span></a>
				</div>
			</form>
		</div>
	</div>
</div>


<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/standard2_mgt.js"></script>