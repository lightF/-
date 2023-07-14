<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!-- 게시글 상세보기 페이지 -->
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
  <h2>Panel Heading</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body">
    	<table class="table table-bordered">
    		<tr>
    		 <td>번호</td>
    		 <td>${vo.idx}</td>
    		</tr>
    		<tr>
    		 <td>제목</td>
    		 <td>${vo.title}</td>
    		</tr>
			<tr>
    		 <td>내용</td>
    		 <td><textarea rows="10" class="form-control" readonly="readonly">${vo.content}</textarea></td>
    		</tr>
    		<tr>
    		 <td>작성자</td>
    		 <td>${vo.writer}</td>
    		</tr>
    			<td colspan="2" style=" text-align: center;">
    			  <button class="btn btn-sm btn-info">답글</button>
    			  <button class="btn btn-sm btn-success">수정</button>
    			  <button class="btn btn-sm btn-warning">삭제</button>
    			  <button class="btn btn-sm btn-info">목록</button>
    			</td>
    	</table>
    </div>
    <div class="panel-footer">BOARD</div>
  </div>
</div>

</body>
</html>
