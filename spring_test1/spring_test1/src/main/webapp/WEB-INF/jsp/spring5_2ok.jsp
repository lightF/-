<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring 3일차 - Controller -> View로 배열값 출력(표현식)</title>
</head>
<body>
<app:set var="delete" value="${person_delete}"></app:set>
<p>고객명 리스트</p>
<!-- 
:set(표현식 변수 생성) var 변수명을 생성해서 함수를 이용하여 값을 받는 형태
:forEach 배열을 반복시킬 때 사용
:for 일반 반복문
:if 조건문을 생성 조건형태는 test이름으로 생성 
 -->
<app:set var="person_ea" value="${fn:length(person_list)}"></app:set>
<p>가입자 수 : ${ea}</p>
<p>탈퇴인원 수 : ${delete}</p>
<ul>
<!-- forEach items
varStatus : 순차번호, 배열의 첫번째,마지막 값, 갯수부분-->
<app:forEach var="username" items="${person_list}" varStatus="st">
<app:if test="${username!='강감찬'}">

<li>번호: ${person_ea} 이름: ${username}</li>
</app:if>
<app:set var="person_ea" value="${person_ea-1}"></app:set>

</app:forEach>
</ul>
<!-- forEach (for문)-->
<app:forEach var="w" begin="1" end="5" step="1">
${w}
</app:forEach>

</body>
</html>