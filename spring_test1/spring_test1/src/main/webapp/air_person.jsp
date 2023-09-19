<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비행기 예매 페이지</title>
<script type="text/javascript">
//select 태그에 값을 가져오는 핸들링 코드
var ori_money;
function data(z){
    var a = z.split("|");
    document.getElementsByName("mcode")[0].value=a[0];	//비행기코드
    document.getElementsByName("aircorp")[0].value=a[1];	//항공사
    document.getElementById("totalmoney").value=a[2];	//총 금액
    ori_money = a[2];	//기본
    document.getElementById("mperson").value=1; //해당 항공사 변경시 인원 초기화
}
function person(p){
    console.log(p);
    var sum = Number(p) * Number(ori_money);
    document.getElementById("totalmoney").value=sum;
}
function btn() {
  var form = document.getElementById('f');
  f.action = './personok.do';
  f.submit();
}
</script>
</head>
<body>
<%
	ResultSet rs = null;
	String db_driver = "com.mysql.jdbc.Driver";
	String db_url = "jdbc:mysql://umj7-003.cafe24.com/hyunje901030";
	String db_user = "hyunje901030";
	String db_pass = "sorj5604!@";
	try {
	    Class.forName(db_driver);
	    Connection con = DriverManager.getConnection(db_url, db_user, db_pass);
	    String sql = "select * from air_reserve order by mname DESC";
	    PreparedStatement ps = con.prepareStatement(sql);
	    rs = ps.executeQuery();
	    out.print(rs.next());
	} catch (Exception e) {
	    e.printStackTrace();
	}
%>

<form id="f" method="post">
<input type="text" name="mid" placeholder="아이디 입력"><br>
<input type="text" name="mname" placeholder="고객명"><br>
<input type="text" name="mpost" placeholder="여권번호"><br> 
<input type="text" name="mtel" placeholder='고객연락처('-' 미입력)' maxlength='14'><br> 
<select name='aircorp' onchange='data(this.value)'>
<option value="">항공사 선택하세요</option>
<% while(rs.next()){
%>
<option value="<%=rs.getString("mname")%>|
<%=rs.getString("mport")%>">
<%=rs.getString("mname")%></option>
<%
}
%>
</select><br>
<select>
</select>
<input type="text" id="mperson" name="mperson" placeholder='인원수를 입력하세요' value='1' onkeyup='person(this.value)' /><br/> 
<p>총 항공료</p> 
</form>
<input type="text" id="mairfare" readonly="readonly" value='0'><br/> 
<input type="text" id="totalmoney" readonly="readonly" value='0'><br/> 
<!-- 완료 버튼 클릭 시 btn 함수 호출 -->
<input type='button' onclick='btn()' value='완료'>
</body>