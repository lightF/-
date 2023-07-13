<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		var result='${result}'; //result 변수에 ${result} 를받는다. jstl로 값 받기 등록된 게시글 번호가 result로 날라오낟.
  		checkModal(result); //함수 만들기
  		
  		$("#regBtn").click(function(){
  			location.href="${cpath}/board/register";
  		});
  	});
  	function checkModal(result){	//result가 값이 없으면 그냥 끝낸다.
  		if (result=='') {
			return;
		}
  		if (parseInt(result)>0) {	//result가 0보다 크면 값을 받겠다.
  			//새로운 다이얼로그 창 띄우기
  			$(".modal-body").html("게시글 " +parseInt(result)+"번이 등록되었습니다.");
		}
  		$("#myModal").modal("show");
  	}
  </script>
</head>
<body>
 
<div class="container">
  <h2>Spring MVC</h2>
  <div class="panel panel-default">
    <div class="panel-heading">
        <c:if test="${empty mvo}">
	     <form class="form-inline" action="${cpath}/login/loginProcess" method="post">
			  <div class="form-group">
			    <label for="memID">ID:</label>
			    <input type="text" class="form-control" name="memID">
			  </div>
		  <div class="form-group">
		    <label for="memPwd">Password:</label>
		    <input type="password" class="form-control" name="memPwd">
		  </div>
			  <button type="submit" class="btn btn-default">로그인</button>
		 </form>
		</c:if>
		<c:if test="${!empty mvo}">
			<form class="form-inline" action="${cpath}/login/loginProcess" method="post">
				  <div class="form-group">
				    <label for="memID">${mvo.memName}님</label>
				  </div>
				  <button type="submit" class="btn btn-default">로그아웃</button>
			 </form>
		</c:if>
   		 </div>
		    <div class="panel-body">Panel Content
		    	<table class="table table-bordered table-hover">
		    		<thead>
	    		 <tr>	
		    		  <th>번호</th>
		    		  <th>제목</th>
		    		  <th>작성자</th>
		    		  <th>작성일</th>
		    		  <th>조회수</th>
	    		  </tr>
    		</thead>
    		<c:forEach var="vo" items="${list}">
    			 <tr>	
	    		  <td>${vo.idx}</td>
	    		  <td><a href="${cpath}/board/get?idx=${vo.idx}">${vo.title}</a></td>
	    		  <td>${vo.writer}</td>
	    		  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${vo.indate}"/></td>
	    		  <td>${vo.count}</td>
    		  </tr>
    		  </c:forEach>
    		  
    		  <c:if test="${!empty mvo}"> <!-- 로그인을 하지않으면 글쓰기 안됨 -->
    		  <tr>
    		  	<td colspan="5">
    		  	  <button id="regBtn" class="btn btn-sm btn-primary pull-right">글쓰기</button>
    		  	</td>
    		  </tr>
    		  
    		  
    		  </c:if>
    	</table>
    	<!-- MODAL 추가 -->
    	 <div class="modal fade" id="myModal" role="dialog">
   		 	<div class="modal-dialog">
    
     	 <!-- Modal content-->
      	<div class="modal-content">
       	 <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    	</div>
 	 </div>
  <!-- MODAL END -->
		</div>
    <div class="panel-footer">BOARD</div>
	</div>
 </div>
</body>
</html>
