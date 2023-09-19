<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
long time = System.currentTimeMillis();
SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String check = today.format(time);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AirReserve</title>
</head>
<body>
	<p>항공리스트
	<p>
	<form id="f" method="post" action="./reserveok.do" enctype="application/x-www-form-urlencoded">
		<input type="text" name="aplane_number" placeholder="비행기 코드 넘버" maxlength="14"><br> <select name="acorp">
			<option value="ANA">ANA</option>
			<option value="OK">OK</option>
			<option value="중화항공">중화항공</option>
			<option value="에어아시아">에어아시아</option>
			<option value="대한항공">대한항공</option>
			<option value="아시아나">아시아나</option>
			<option value="제주항공">제주항공</option>
		</select><br> <select name="anation">
			<option value="일본">일본</option>
			<option value="중국">중국</option>
			<option value="베트남">베트남</option>
			<option value="말레이시아">말레이시아</option>
			<option value="크로아티아">크로아티아</option>
			<option value="이집트">이집트</option>
			<option value="터키">터키</option>
		</select> <br> <input type="datetime-local" name="adate"><br>
		<input type="text" name="areserve" placeholder="예약가능인원수"
			onkeyup="air_abb(event)" maxlength="3"><br> <input
			type="text" name="amoney" placeholder="1인 기준 항공료"
			onkeyup="air_abc(event)"><br> 예매 시작 시간 : <input
			type="datetime-local" name="adate2"><br> 예매 종료 시간 : <input
			type="datetime-local" name="adate3"><br> <input
			type="button" value="예약하기" id="btn">
	</form>
</body>
<script>
window.onload = function(){
	var bw = navigator.userAgent.toUpperCase();
	if(bw.indexOf("FIREFOX")>=0){
		alert("해당 브라우저는 접속불가")
	}
}
	/*
	 * onkeypress : keydown 되었을 때 값을 가져옴 (특수키X)
	 onkeydown : keydwon 되었을 때 값을 가져옴(특수키O)
	 onkeyup : keydown 후 keyup 없이 되었을 때 값을 가져옴
	 */
	function air_abc(event) {
		if (event.key >= 0 || event.key <= 9) {
			return;
		} else {
			alert("숫자 값만 입력 가능합니다");
			f.amoney.value="";
			return false;
		}
	}
	 function air_abb(event) {
			if (event.key >= 0 || event.key <= 9) {
				return;
			} else {
				alert("숫자 값만 입력 가능합니다");
				f.areserve.value="";
				return false;
			}
		}
	const times = "<%=check%>";

	var s = f.adate2.value.replaceAll(/-|T|:/g, "")
	var e = f.adate3.value.replaceAll(/-|T|:/g, "")
	/*
		/내용/g : 정규표현식으로 데이터 체크, 치환, 삭제 가능
	 */
	document.querySelector("#btn").addEventListener(
			"click",
			function() {
				if (f.aplane_number.value == "" || f.acorp.value == ""
						|| f.anation.value == "" || f.adate.value == ""
						|| f.adate2.value == "" || f.adate3.value == "") {
					alert("필수항목 입력");
					return false;
				} else {
					if (f.adate.value <= times) {
						alert("출발일자 시간이 정확하지 않습니다");
						return false;
					} else if (f.adate2.value >=f.adate3.value) {
						alert("예매시간을 다시 확인해 주세요")
						f.adate2.value = "";
						f.adate3.value = "";
						console.log(s)
						return false;
					}
					else{
						f.submit();
					}
				}
			})
</script>
</html>