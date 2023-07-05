<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="head.jsp" %>
        <%@ page session="true" %>
            <% session=request.getSession(); Object orgs=session.getAttribute("per_seq"); %>

                <!--    여기부터 각페이지 본문       -->
                <div class="cont notice">

                    <div class="title">
                        <p>공지사항</p>
                        <a class="favo"></a>
                    </div>
                    <div class="wrap">
                        <div class="search">
                            <form name="sch_form">
                                <input type="hidden" value='<%= session.getAttribute("per_seq")%>' id="pseq">
                                <input type="hidden" name="page" value="1">
                                <input type="hidden" name="col" value="update_date">
                                <input type="hidden" name="order" value="DESC">
                                <ul>
                                    <li class="date">
                                        <p>조회기간</p>
                                        <div class="start">
                                            <input type="date" name="start_date" placeholder="시작일">
                                        </div>
                                        <div class="end">
                                            <input type="date" name="end_date" placeholder="종료일">
                                        </div>
                                    </li>
                                    <li>
                                        <p>제목</p>
                                        <div>
                                            <input type="text" name="title" placeholder="입력하세요.">
                                        </div>
                                    </li>
                                    <li>
                                        <p>작성자</p>
                                        <div>
                                            <input type="text" name="editor" placeholder="입력하세요.">
                                        </div>
                                    </li>
                                </ul>
                                <div class="btn_area">
                                    <button type="submit" class="btn search_btn">검색하기<i
                                            class="ri-search-line"></i></button>
                                    <a class="btn sky" data-dialog="notice_write">공지등록<i class="ri-pencil-line"></i></a>
                                </div>
                            </form>

                        </div>
                        <div class="tb">
                            <div class="table">
                                <table>
                                    <thead>
                                        <tr>
                                            <th class="btn_sort" data-col="seq">순번</th>
                                            <th class="btn_sort" data-col="nt_subject">제목</th>
                                            <th>파일</th>
                                            <th class="btn_sort" data-col="per_name">작성자</th>
                                            <th class="btn_sort" data-col="nt_count">조회</th>
                                            <th class="btn_sort" data-col="update_date">등록일</th>
                                        </tr>
                                    </thead>
                                    <tbody class="list_tbody">
                                        <!-- <% for(int i=0; i<20; i++){%>
						<tr>
							<td>1</td>
							<td><a data-dialog="notice_view">공지사항입니다.공지사항입니다.공지사항입니다.공지사항입니다.공지사항입니다.공지사항입니다.공지사항입니다.공지사항입니다.</a></td>
							<td></td>
							<td>홍길동</td>
							<td>3</td>
							<td>2022-8-29</td>
						</tr>
					<%}%>	 -->
                                    </tbody>
                                </table>
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
                                    <p><span id="total">00</span>건이 검색되었습니다.</p>
                                </div>
                                <ul class="paging pagination">
                                    <!-- <li><a><img src="${resourcePath}/css/img/prev_prev.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/prev_.png"></a></li>
					<li><a class="active">1</a></li>
					<li><a>2</a></li>
					<li><a>3</a></li>
					<li><a>4</a></li>
					<li><a>5</a></li>
					<li><a><img src="${resourcePath}/css/img/next_.png"></a></li>
					<li><a><img src="${resourcePath}/css/img/next_next.png"></a></li> -->
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>



                <!-- 공지사항 작성하기 -->
                <div class="dialog dialog_write" id="notice_write">
                    <div class="overlay"></div>
                    <div class="content">
                        <div class="top">
                            <p>공지사항 > <span>글쓰기</span></p>
                            <a class="close"><i class="ri-close-line"></i></a>
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
                                                <textarea name="" id="contents"></textarea>
                                            </div>
                                        </li>
                                        <li class="dropzone needsclick">
                                            <div class="guide_drop"
                                                style="display: none; opacity: 0.7;min-height: 100px;width: calc(100% - 100px);position: absolute;text-align: center;">
                                                <img src="${resourcePath}/img/drag_file.gif" style="height: 100%;">

                                            </div>



                                        </li>
                                    </ul>
                                </div>
                                <div class="btn_area">
                                    <button class="btn nt_save" type="submit"><i class="ri-check-line"></i>저장</button>
                                    <a class="btn close"><i class="ri-close-line"></i>취소</a>
                                    <a class="btn del"><i class="ri-delete-bin-line"></i>삭제</a>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <!-- //공지사항 작성하기 -->

                <script src="${resourcePath}/js/notice.js"></script>
                <script>
                   
                   
                </script>

                <%@ include file='tail.jsp' %>