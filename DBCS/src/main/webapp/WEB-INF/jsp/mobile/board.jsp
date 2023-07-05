<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.jsp" %>
<%@ page session="true" %>
<%
session = request.getSession();
Object orgs = session.getAttribute("per_seq");

%>


<div class="m_cont">
	<div class="m_notice">
		<div class="top">
			<a class="btn sch_btn">상세검색</a>
			<div class="m_search">
				<div class="overlay"></div>
				<div class="sch_box">
					<div class="top_tit">
						<p>게시판 > <span>상세검색</span></p>
					</div>
					<form name="sch_form">
						<input type="hidden" value='<%= session.getAttribute("per_seq")%>' id="pseq">
						<input type="hidden" name="page" value="1">
						<input type="hidden" name="col" value="update_date">
						<input type="hidden" name="order" value="DESC">
						<ul>
							<li>
								<p>조회기간</p>
								<div class="start">
									<input type="date" name="start_date" placeholder="시작일" >
								</div>
								<div class="end">
									<input type="date" name="end_date" placeholder="종료일" >
								</div>
							</li>
							<li>
								<p>제목</p>
								<div>
									<input type="text" name="title"placeholder="입력하세요." >
								</div>
							</li>
							<li>
								<p>작성자</p>
								<div>
									<input type="text" name="editor"placeholder="입력하세요." >
								</div>
							</li>
						</ul>
						<div class="btn_area">
							<button class="btn" type="submit"><i class="ri-search-line"></i>적용</button>
						</div>
					</form>
				</div>
			</div>
			<p class="title">게시판</p>
			<a class="btn sky">게시글등록</a>
		</div>
		<div class="m_ul_list">
			<div class="ul_list">
				<ul class="list_tbody">
					
					<!-- <li>
						<p class="count">2</p>
						<a href="${contextPath}/notice_view">
							<div class="list_st1">
								<p>작성자 : <span class="name">홍길동</span></p>
								<span class="line"></span>
								<p class="file">파일 : <i class="ri-file-gif-line"></i></p>
							</div>
							<div class="list_st2">
								<p>공지사항입니다.제목입니다 전체공지사항입니다.공지사항입니다. 전체공지사항입니다.</p>
							</div>
							<div class="list_st3">
								<p>등록일 : <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>조회 : <span class="look">52</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">3</p>
						<a href="${contextPath}/notice_view">
							<div class="list_st1">
								<p>작성자 : <span class="name">홍길동</span></p>
								<span class="line"></span>
								<p class="file">파일 : <i class="ri-file-gif-line"></i></p>
							</div>
							<div class="list_st2">
								<p>공지사항입니다.제목입니다 전체공지사항입니다.공지사항입니다. 전체공지사항입니다.</p>
							</div>
							<div class="list_st3">
								<p>등록일 : <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>조회 : <span class="look">52</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">4</p>
						<a href="${contextPath}/notice_view">
							<div class="list_st1">
								<p>작성자 : <span class="name">홍길동</span></p>
								<span class="line"></span>
								<p class="file">파일 : <i class="ri-file-gif-line"></i></p>
							</div>
							<div class="list_st2">
								<p>공지사항입니다.제목입니다 전체공지사항입니다.공지사항입니다. 전체공지사항입니다.</p>
							</div>
							<div class="list_st3">
								<p>등록일 : <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>조회 : <span class="look">52</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">5</p>
						<a href="${contextPath}/notice_view">
							<div class="list_st1">
								<p>작성자 : <span class="name">홍길동</span></p>
								<span class="line"></span>
								<p class="file">파일 : <i class="ri-file-gif-line"></i></p>
							</div>
							<div class="list_st2">
								<p>공지사항입니다.제목입니다 전체공지사항입니다.공지사항입니다. 전체공지사항입니다.</p>
							</div>
							<div class="list_st3">
								<p>등록일 : <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>조회 : <span class="look">52</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">6</p>
						<a href="${contextPath}/notice_view">
							<div class="list_st1">
								<p>작성자 : <span class="name">홍길동</span></p>
								<span class="line"></span>
								<p class="file">파일 : <i class="ri-file-gif-line"></i></p>
							</div>
							<div class="list_st2">
								<p>공지사항입니다.제목입니다 전체공지사항입니다.공지사항입니다. 전체공지사항입니다.</p>
							</div>
							<div class="list_st3">
								<p>등록일 : <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>조회 : <span class="look">52</span></p>
							</div>
						</a>
					</li>
					<li>
						<p class="count">7</p>
						<a href="${contextPath}/notice_view">
							<div class="list_st1">
								<p>작성자 : <span class="name">홍길동</span></p>
								<span class="line"></span>
								<p class="file">파일 : <i class="ri-file-gif-line"></i></p>
							</div>
							<div class="list_st2">
								<p>공지사항입니다.제목입니다 전체공지사항입니다.공지사항입니다. 전체공지사항입니다.</p>
							</div>
							<div class="list_st3">
								<p>등록일 : <span class="date">22-08-29</span></p>
								<span class="line"></span>
								<p>조회 : <span class="look">52</span></p>
							</div>
						</a>
					</li> -->
				</ul>
			</div>
			<!-- <div class="table tb_scroll">
				<table>
					<thead>
						<tr>
							<th>순번</th>
							<th>제목</th>
							<th>파일</th>
							<th>작성자</th>
							<th>조회</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody class="list_tbody">
						<tr>
							<td>1</td>
							<td><a>공지사항입니다. 전체공지사항입니다.</a></td>
							<td></td>
							<td>홍길동</td>
							<td>52</td>
							<td>22-08-29</td>
						</tr>
				
					</tbody>
				</table>
			</div> -->
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
		</div>
	</div>
</div>
		
<script>

//tabel tbody tr 클릭시 공지사항 view-pg 이동
	
    // $(".m_notice tbody tr").click(function() {
    //     window.location.href="${contextPath}/notice_view";
    // });

</script>



<script src="${resourcePath}/js/mobile/board.js"></script>
<%@ include file="tail.jsp" %>