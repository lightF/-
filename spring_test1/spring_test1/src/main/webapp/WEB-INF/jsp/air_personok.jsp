<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

	ResultSet rs = null;
	String db_driver = "com.mysql.jdbc.Driver";
	String db_url = "jdbc:mysql://umj7-003.cafe24.com/hyunje901030";
	//String db_url = "jdbc:mysql://localhost:3306/wjswjdgh123";
	String db_user = "hyunje901030";
	String db_pass = "sorj5604!@";
try {
	Class.forName(db_driver);
	Connection con = DriverManager.getConnection(db_url, db_user, db_pass);
	String sql = "select * from air_reserve order by acode desc";
	PreparedStatement ps = con.prepareStatement(sql);
	rs = ps.executeQuery();
} catch (Exception e) {
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="f">
		<input type="hidden" name="acode" value=""> 
		<input type="hidden" name="aplane_number" value="">
		<p>예매
		<p>
			<input type="text" name="mid" placeholder="ID"><br> 
			<input type="text" name="mname" placeholder="NAME"><br>
			<input type="text" name="mpport" placeholder="PASSPORTCODE"><br>
			<input type="text" name="mtel" placeholder="PHONENUMBER" maxlength="11"><br> 
			<select name="aircorp" onchange="data(this.value)">
			<option value="">항공사 선택</option>
				<%
				while (rs.next()) {
				%>
				<option
				value="<%=rs.getString("acorp")%>/<%=rs.getString("amoney")%>/<%=rs.getString("aplane_number")%>"><%=rs.getString("acorp")%></option>
				<%
				}
				%>
			</select> 
			<input type="text" name="mperson" placeholder="인원수입력" onkeyup="person(this.value)"><br>
		<p>총 항공료
		<p> <br> 
		<input type="text" name="totalmoney" readonly value="0"><br>
		<input type="button" value="완료" id="btn">
	</form>
</body>
<script>
	var money;
	function data(z) {
		var a = z.split("/")
		console.log(a)
		f.acode.value = a[2]; //뱅기코드
		f.aplane_number.value = a[0]; //항공사명
		f.totalmoney.value = a[1]; //금액
		money = a[1] //1인 기준 금액
		f.mperson.value=1; //항공사 변경시 인원 초기화
	}
	function person(p) {
		var sum = Number(p) * Number(money);
		f.totalmoney.value = sum;
	}
	document.querySelector("#btn").addEventListener("click", function() {

	})
</script>
</html>