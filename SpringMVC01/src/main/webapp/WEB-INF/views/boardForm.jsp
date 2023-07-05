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
    <div class="panel-body">Panel Content
     <form action="boardInsert.do" method="post">
    	<table class="table">
    	 <tr>
    	 	<td>제목</td>
    	 	<td><input type="text" name="title" class="form-control"/></td>
    	 </tr>
    	 
    	 <tr>
    	 	<td>내용</td>
    	 	<td><textarea rows="7" class="form-control" name="content"></textarea></td>
    	 </tr>
    	 
    	 <tr>
    	 	<td>작성자</td>
    	 	<td><input type="text" name="writer" class="form-control"/></td>
    	 </tr>
    	 <td colspan="2" align="center">
    	 	<button type="submit" class="btn btn-success btn-sm">등록</button>
    	 	<button type="reset" class="btn btn-waring btn-sm">취소</button>
    	 	
    	 </td>
    	</table>
    </form>
    </div>
     <div class="panel-body">Panel Content</div>
   	 </div>
   </div>
</body>
</html>
