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
    	<form action="${cpath}/board/modify" method="post">
    	<table class="table table-bordered">
    		<tr>
    		 <td>번호</td>
    		 <td><input type="text" clsas="form-control" name="idx" readonly="readonly"  value="${vo.idx}"/></td>
    		</tr>
    		<tr>
    		 <td>제목</td>
    		 <td><input type="text" clsas="form-control" name="title" value="${vo.title}"/></td></td>
    		</tr>
			<tr>
    		 <td>내용</td>
    		 <td><input type="text" clsas="form-control" name="content" value="${vo.content}"/></td></td>
    		</tr>
    		<tr>
    		 <td>작성자</td>
    		 <td><input type="text" clsas="form-control" name="writer" readonly="readonly" value="${vo.writer}"/></td></td>
    		</tr>
    			<td colspan="2" style=" text-align: center;">
    			  <c:if test="${!empty mvo && mvo.memID eq vo.memID}"> <!-- 본인의 글: memID 자신의글 vo.memID: DB에서 가지고온 게시물의 ID -->
	    			  <button type="submit" class="btn btn-sm btn-primary">수정</button>
	    			  <button type="button" class="btn btn-sm btn-success" onclick="location.href='${cpath}/board/remove?idx=${vo.idx}'">삭제</button>
    			  </c:if>
    			    <c:if test="${empty mvo || mvo.memID ne vo.memID}"> <!--로그인을 안헀거나 다르면 NOT EQUALS 자신의글이 아닌경우-->
	    			  <button disabled="disabled" type="submit" class="btn btn-sm btn-primary">수정</button>
	    			  <button disabled="disabled" type="button" class="btn btn-sm btn-success" onclick="location.href='${cpath}/board/remove?idx=${vo.idx}'">삭제</button>
    			  </c:if>
    			  <button type="button" class="btn btn-sm btn-info" onclick="location.href='${cpath}/board/list'">목록</button>
    			</td>
    	</table>
    	</form>
    </div>
    <div class="panel-footer">BOARD</div>
  </div>
</div>

</body>
</html>
