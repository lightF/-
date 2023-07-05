<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>

<!--        여기부터 각페이지 본문             -->
<div class="cont instrument_list">
	<div class="title">
		<p>계측기 통계</p>
		<a class="favo"></a>
	</div>
	<div class="wrap content">
		<div class="pop_print">
			<div class="tb">
				<h1>계측기 보유현황</h1>
				<div class="tb_scroll">
					<table >
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="down_area">
			<a class="btn pdf_down sky" data-filename="계측기보유현황">Pdf 다운로드</a>
			<a class="btn xlsx sky">Xlsx 다운로드</a>
		</div>
	</div>
	
</div>

 

<script>
    //tabel tbody td a 클릭시 보고서팝업 오픈
    $(".work_management tbody td a").click(function () {
        $("#pop_up_page_view").addClass("dialog_open");
    });

</script>



<%@ include file='tail.jsp' %>
<script type="text/javascript" src="${resourcePath}/js/instrument_list.js"></script>