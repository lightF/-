<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<%@ page session="true" %>
<%
session = request.getSession();
Object orgs = session.getAttribute("per_seq");

%>

<!--    여기부터 각페이지 본문       -->
<div class="cont notice">
	
	<div class="title">
		<p>게시판</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="sch_form">
				<input type="hidden" value='<%= session.getAttribute("per_seq")%>' id="pseq">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="column" value="update_date">
				<input type="hidden" name="order" value="DESC">
				<ul>
					<li class="date">
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
					<button type="submit" class="btn search_btn">검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky" data-dialog="notice_write">작성하기<i class="ri-pencil-line"></i></a>
				</div>
			</form>
			
		</div>
		<div class="tb">
			<div class="table tb_scroll">
				<table>
					<thead>
						<tr>
							<th data-column="seq" data-order="DESC" class="sort">순번</th>
							<th data-column="bd_subject" data-order="ASC">제목</th>
							<th data-column="bd_file" data-order="ASC">파일</th>
							<th data-column="per_name" data-order="ASC">작성자</th>
							<th data-column="bd_count" data-order="ASC">조회</th>
							<th data-column="update_date" data-order="ASC">등록일</th>
						</tr>
					</thead>
					<tbody class="list_tbody">
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
					<p><span id="total"></span>건이 검색되었습니다.</p>
				</div>
				<ul class="paging pagination">				
				</ul>
			</div>
		</div>
	</div>
</div>



<!-- 게시판 작성하기 -->
<div class="dialog dialog_write" id="notice_write">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>게시판 > <span>글쓰기</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="add_form" >
				<div class="ul_style">
					<ul>
						<li class="required">
							<p>제목<span class="ess"></span></p>
							<div>
								<input type="text" name="nt_subject" placeholder="입력하세요." required="required">
							</div>
						</li>
						<li class="textarea required">
							<p>본문<span class="ess"></span></p>
							<div>
								<textarea name="contents" id="contents"></textarea>
							</div>
						</li>
						<li class="img">
							<p>첨부파일</p>
							<div class="">
								<input type="file" name="file1" id="file1" class="file_input">
								<label for="file1"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img class="h200px" src=""/>

								<a class="file_del"><i class="ri-close-line"></i></a>
							</div>
							<div class="">
								<input type="file" name="file2" id="file2" class="file_input">
								<label for="file2"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img src=""/>

								<a class="file_del"><i class="ri-close-line"></i></a>
								
							</div>
							<div class="">
								<input type="file" name="file3" id="file3" class="file_input">
								<label for="file3"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img src=""/>

								<a class="file_del"><i class="ri-close-line"></i></a>
								
							</div>
							<div class="">
								<input type="file" name="file4" id="file4" class="file_input">
								<label for="file4"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img src=""/>

								<a class="file_del"><i class="ri-close-line"></i></a>
								
							</div>
						</li>
					</ul>
				</div>
				<div class="btn_area">
					<a class="btn nt_save" ><i class="ri-check-line"></i>저장</a>
					<!-- <a class="btn pdf_down sky">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a> -->
					<a class="btn del"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>	
			</form>
			
		</div>
	</div>
</div>
<!-- //게시판 작성하기 -->


<!-- 게시판 view -->
<div class="dialog dialog_write" id="notice_view">
	<div class="overlay"></div>
	<div class="content">
		<div class="top">
			<p>게시판 > <span>글쓰기</span></p>
			<a class="close"><i class="ri-close-line"></i></a>
		</div>
		<div class="body">
			<form name="" action="" method="">
				<div class="ul_style">
					<ul>
						<li class="required">
							<p>제목<span class="ess"></span></p>
							<div>
								<input type="text" name="" placeholder="입력하세요." value="안녕하세요. 게시판입니다." required >
							</div>
						</li>
						<li class="textarea required">
							<p>본문<span class="ess"></span></p>
							<div>
								<textarea name="" required>여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 여기에 본문내용이 입력됩니다 </textarea>
							</div>
						</li>
						<li class="img">
							<p>첨부파일</p>
							<div class="upload">
								<input type="file" name="file1" id="file1">
								<label for="file1"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img src="${resourcePath}/css/img/exex.jpg"/>
								<a class="file_del"><i class="ri-close-line"></i></a>
							</div>
							<div class="upload">
								<input type="file" name="file2" id="file2">
								<label for="file2"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img src="${resourcePath}/css/img/exex.jpg"/>
								<a class="file_del"><i class="ri-close-line"></i></a>
							</div>
							<div class="">
								<input type="file" name="file3" id="file3">
								<label for="file3"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img src=""/>
								<a class="file_del"><i class="ri-close-line"></i></a>
							</div>
							<div class="">
								<input type="file" name="file4" id="file4">
								<label for="file4"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img src=""/>
								<a class="file_del"><i class="ri-close-line"></i></a>
							</div>
						</li>
					</ul>
				</div>
				<div class="btn_area">
					<button class="btn" data-dialog="save_pop"><i class="ri-check-line"></i>저장</button>
					<a class="btn pdf_down sky">Pdf 다운로드</a>
					<a class="btn xlsx sky">Xlsx 다운로드</a>
					<a class="btn del" data-dialog="delete_pop"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //게시판 view -->




<script src="${resourcePath}/js/board.js"></script>

<%@ include file='tail.jsp' %>
