<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
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
    <div class="panel-body">Panel Content
    	<form action="${cpath}/board/reply" method="post"> <!-- 원래글의 idx가 아니라 그룹을 만들수 있다. hidden이 2개 title content -->
    	<input type="hidden" name="idx" value="${vo.idx}"/> <!-- 현재쓰는id가 아니라 부모글의 id(게시글) -->
    	<!-- input으로 type memID로 값을 넘긴다. -->
    	 <input type="hidden" name="memID" value="${mvo.memID}" /> 
    		<div class="form-group">
    			<label>제목</label>
    			<input type="text" name="title" class="form-group" value="${vo.title}"/>
    		</div>
    		<div class="form-group">
    			<label>댓글</label>
    			<textarea rows="10" name="content" class="form-control"></textarea>
    		</div>
    		<div class="form-group">
    			<label>작성자</label> <!-- readonly로 작성자 수정을 못하도록 readonly로 해줘야한다. -->
    			<input type="text" name="writer" readonly="readonly" class="form-group" value="${mvo.memName}"/>
    		</div>
    		<button type="submit" class="btn btn-default btn-sm">댓글</button>
    		<button type="reset" class="btn btn-default btn-sm">취소</button>
    		<button type="button" class="btn btn-default btn-sm" onclick="location.href='${cpath/board/list}'">목록</button>
    	</form>
    </div>
    <div class="panel-footer">BOARD</div>
  </div>
</div>

</body>
</html>
