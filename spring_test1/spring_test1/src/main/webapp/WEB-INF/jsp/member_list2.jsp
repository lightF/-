<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 //배열값이 null일경우 jsp에서 해당 HTML 코드를 비활성시키는 방법
 String aa = null;
 try{
	List<ArrayList<String>> member_data = (List<ArrayList<String>>)request.getAttribute("member_data");
 	int many = member_data.size();
 	aa= "ok";
 }catch(Exception e){
	 out.print("오류발생");
	  aa = (String)request.getAttribute("member_data");
 }
 	
 %>
 <%if(aa==null){ %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 리스트 페이지</title>
</head>
<body>
<p>회원가입 고객 리스트 가입자수 : <%=many%>명</p>
<table border="1" width="1000">
<thead>
	<tr>
	<th width="5%">번호</th>
	<th width="20%">아이디</th>
	<th width="30%">이메일</th>
	<th width="20%" >연락처</th>	
	<th width="5%">나이</th>
	<th width="20%">가입일자</th>
	</tr>
</thead>

</table>
<br><br>
<form id="f" method="get" action="./spring6ok.do" onsubmit="return idsearch()">
 검색: <input type="text" name="search">
<input type="submit" value="검색">
</form>
</body>
<%} %>
<script>
var word = "홍길 동님 환   영  합 니다. ";
/*
 trim() => 앞,뒤에 공백만 삭제
 replace() => 첫번째 관련 단어만 변경
 replaceAll() => 모든 관련 단어 변경
 */
var word2 = word.replaceAll(" ","");
console.log(word2);

function idsearch() {
	//입력에 따른 공백을 제거 후 조건문으로 재확인
	f.search.value = f.search.value.replaceAll(" ","");
	if(f.search.value==""){
		alert("검색할 단어를 입력하세요!!");
		return false;
	}
	else{
		
		return;
	}
	
}
</script>
</html>