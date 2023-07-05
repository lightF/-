<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- jsp파일 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>SPRING MVC 01</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 <!-- jstl 값 전달받기 -->
<div class="container">
  <h2>SPRING MVC01</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body">Panel Content</div>
    <table class="table table-bordered table-hover">
    	<tr>
    		<td>번호</td>
    		<td>제목</td>
    		<td>작성자</td>
    		<td>작성일</td>
    		<td>조회수</td>
    	</tr>
    	<c:forEach var="vo" items="${list}">
    		<tr>
    			<td>${vo.idx}</td>
    			<td><a href="boardContent.do?idx=${vo.idx}">${vo.title}</a></td>
    			<!-- boardcontroller의 idx와 이름이 같으면 @requestparam을 생략해도 된다. -->
    			<td>${vo.writer}</td>
    			<td>${fn:split(vo.indate," ")[0]}</td>
    			<td>${vo.count}</td>
    		</tr>
    	</c:forEach>
    </table>
    <a href="boardForm.do" class="btn btn-primary btn-sm">글쓰기</a>
    <!-- /boardForm.do 글쓰기버튼을 눌렀을때 url -->
    <!-- 경로 / /boardForm.do -->
    <div class="panel-footer">최현제 </div>
  </div>
</div>

</body>
</html>
