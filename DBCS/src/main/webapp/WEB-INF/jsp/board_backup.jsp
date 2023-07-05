<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>


<!--    여기부터 각페이지 본문       -->
<div class="cont board">
	<div class="title">
		<p>게시판</p>
		<a class="favo"></a>
	</div>
	<div class="wrap">
		<div class="search">
			<form name="" action="" method="">
				<ul>
					<li class="date">
						<p>조회기간</p>
						<div class="start">
							<input type="date" name="start_date" placeholder="시작일" required>
						</div>
						<div class="end">
							<input type="date" name="end_date" placeholder="종료일" required>
						</div>
					</li>
					<li class="w35">
						<p>제목</p>
						<div>
							<input type="text" name=""placeholder="입력하세요." required>
						</div>
					</li>
					<li class="w25">
						<p>작성자</p>
						<div>
							<input type="text" name=""placeholder="입력하세요." required>
						</div>
					</li>
				</ul>
				<div class="btn_area">
					<button class="btn">검색하기<i class="ri-search-line"></i></button>
					<a class="btn sky" data-dialog="board_write">게시글등록<i class="ri-pencil-line"></i></a>
				</div>
			</form>
		</div>
		<div class="tb">
			<div class="table">
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
					<tbody>
					<% for(int i=0; i<20; i++){%>
						<tr>
							<td>1</td>
							<td><a data-dialog="board_view">게시판입니다.게시판입니다.게시판입니다.게시판입니다.게시판입니다.게시판입니다.게시판입니다.게시판입니다.게시판입니다.</a></td>
							<td></td>
							<td>홍길동</td>
							<td>3</td>
							<td>2022-8-29</td>
						</tr>
					<%}%>	
					</tbody>
				</table>
			</div>
			<div class="tb_bottom">
				<div class="slt">
					<select>
						<option>20개씩 보기</option>
						<option>50개씩 보기</option>
						<option>100개씩 보기</option>
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
		</div>
	</div>
</div>



<!-- 게시판 작성하기 -->
<div class="dialog dialog_write" id="board_write">
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
								<input type="text" name="" placeholder="입력하세요." required>
							</div>
						</li>
						<li class="textarea required">
							<p>본문<span class="ess"></span></p>
							<div>
								<textarea name="" required></textarea>
							</div>
						</li>
						<li class="img">
							<p>첨부파일</p>
							<div class="">
								<input type="file" name="file1" id="file1">
								<label for="file1"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img src=""/>
								<a class="file_del"><i class="ri-close-line"></i></a>
							</div>
							<div class="">
								<input type="file" name="file2" id="file2">
								<label for="file2"><i class="ri-upload-fill"></i></label>
								<p>File upload</p>
								<img src=""/>
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
					<button class="btn"><i class="ri-check-line"></i>저장</button>
					<a class="btn close"><i class="ri-close-line"></i>취소</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //게시판 작성하기 -->


<!-- 게시판 view -->
<div class="dialog dialog_write" id="board_view">
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
								<input type="text" name="" placeholder="입력하세요." value="안녕하세요.게시판입니다." required >
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
					<button class="btn"><i class="ri-check-line"></i>저장</button>
					<a class="btn del"><i class="ri-delete-bin-line"></i>삭제</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //게시판 view -->



<%@ include file='tail.jsp' %>