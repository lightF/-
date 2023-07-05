<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.jsp" %>

<div class="m_cont">
	<div class="m_notice_write write">
		<div class="top_area">
			<div class="top_tit">
				<p>공지사항 > <span>공지상세</span></p>
			</div>
		</div>
		<div class="body">
			<form name="add_form">
				<div class="ul_style">
					<ul>
						<li class="required">
							<p>제목<span class="ess"></span></p>
							<div>
								<input type="text" name="nt_subject" placeholder="입력하세요." required>
							</div>
						</li>
						<li class="textarea required">
							<p>본문<span class="ess"></span></p>
							<div>
								<textarea name=""  id="contents"></textarea>
							</div>
						</li>
						<li class="img">
							<p>첨부파일</p>
							<div class="">
								<input type="file" name="file1" id="file1" class="file_input">
								<label for="file1"><i class="ri-upload-fill"></i></label>
								<img src=""/>
							</div>
							<div class="">
								<input type="file" name="file2" id="file2" class="file_input">
								<label for="file2"><i class="ri-upload-fill"></i></label>
								<img src=""/>
							</div>
							<div class="">
								<input type="file" name="file3" id="file3" class="file_input">
								<label for="file3"><i class="ri-upload-fill"></i></label>
								<img src=""/>
							</div>
							<div class="">
								<input type="file" name="file4" id="file4" class="file_input">
								<label for="file4"><i class="ri-upload-fill"></i></label>
								<img src=""/>
							</div>
						</li>
					</ul>
				</div>
				<div class="btn_area">
					
					<a class="btn close gray" href="${contextPath}/notice"><i class="ri-close-line"></i>취소</a>

				</div>
			</form>
		</div>
	</div>
</div>




<script src="${resourcePath}/js/mobile/notice_view.js"></script>
<%@ include file="tail.jsp" %>