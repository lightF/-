<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	int total = (int)request.getAttribute("total"); // 전체가입자수
 
	List<ArrayList<String>> member_data = (List<ArrayList<String>>)request.getAttribute("member_data");
 	int many = member_data.size();
 	String part = (String)request.getAttribute("part");
 	String s = null;
 	//null값을 조건에서 1순위로 체크!
 	if(part!=null&&part.equals("tel")){
 		s = "selected";
 	}
 
 %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 리스트 페이지</title>
</head>
<body>
<p>회원가입 고객 리스트 가입자수 : <%=total %>명</p>
<table border="1" width="1000">
<thead>
	<tr>
	<th width="5%">번호</th>
	<th width="20%">아이디</th>
	<th width="30%">이메일</th>
	<th width="20%">연락처</th>	
	<th width="5%">나이</th>
	<th width="20%">가입일자</th>
	</tr>
</thead>
<tbody>
<!-- database에 내용이 출력되는 파트 -->
<%
	if(member_data.get(0).get(1) == null){%>
		<tr align="center">
		<td colspan="6">검색한 내용을 찾을 수 없습니다.</td>
		</tr>
<%}else{
 	int w =0;
	do{
%>
	<tr align="center">
	<td><%=many-w%></td>
	<td><%=member_data.get(w).get(1)%></td>
	<td><%=member_data.get(w).get(2)%></td>
	<td><%=member_data.get(w).get(3)%></td>
	<td><%=member_data.get(w).get(4)%></td>
	<td><%=member_data.get(w).get(5)%></td>
	</tr>
	<%
	w++;
	}while(w<many);
}
	%>
</tbody>
</table>
<br><br>
<form id="f" method="get" action="./spring6ok.do" onsubmit="return idsearch()">
 검색: <input type="text" name="search">
 <!-- 검색시 분류 설정을 하여 검색 되도록 함 -->
 <select name="part" id="con">
 <option selected value="id">아이디</option>
 <option value="tel">연락처</option>
 </select>
 <!-- 검색시 분류 설정을 하여 검색 되도록 함 -->
<input type="submit" value="검색">
<input type="button" value="전체목록" onclick="alldata()">
</form>
</body>

<script>
var word = "홍길 동님 환   영  합 니다. ";
/*
 trim() => 앞,뒤에 공백만 삭제
 replace() => 첫번째 관련 단어만 변경
 replaceAll() => 모든 관련 단어 변경
 */
 //검색한 단어를 입력값에 유지시키는 스크립 코드
var word2 = word.replaceAll(" ","");
var wd = window.location.search;
console.log(wd);
if(wd=="")
	f.search.value = "";
else{
	var sel = document.getElementById("con");
	var sh = wd.split("&part=");
	
	for(i=0;i<sel.length;i++){
		if(sel.options[i].value==sh[1]){
			sel.options[i].selected = true;
			break;
		}		
	}
	var sh2 = sh[0].split("?search=");
	f.search.value=sh2[1];
		
	
}

function alldata(){
	location.href="./spring6ok.do";
}
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