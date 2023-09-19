<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/v1/kakao.js"
	crossorigin="anonymous"></script>
<head>
<meta charset="UTF-8">
<title>oauth</title>
</head>
<body>
	<p>로그인</p>
	<form id="f" method="post" action="./klogin.do">
	<input type="hidden" name="part" value="web">
	<input type="hidden" name="kakaoid" value="">
	<input type="hidden" name="kakaomail" value="">
	<input type="hidden" name="kakaonick" value="">
	로그인 :
	<input type="text" name="mname">
	<br> 패스워드 :
	<input type="password" name="mpw">
	<br>
	<input type="submit" value="로그인">
	</form>
	<br>
	<br>
	<span style="width: 50px; display: inline-block;"><img
		src="./img/talk.png" style="cursor: pointer;" id="klogin"></span>
</body>
<script>
	document.querySelector("#klogin").addEventListener("click", function() {
		Kakao.Auth.login({
			success : function(response) {
				Kakao.API.request({
					url : '/v2/user/me',
					success : function(response) {
						let id = response["id"];
						let email = response["kakao_account"]["email"];
						let nickname = response["properties"]["nickname"];
						f.part.value = 'kakao';
						f.kakaoid.value = id;
						f.kakaomail.value = email;
						f.kakaonick.value = nickname;
						console.log(id+" "+email+" "+nickname);
						f.submit();
					},
					fail : function(error) {
						console.log("failed")
					}
				})
			},
			fail : function(error) {
				console.log("key error")
			}
		})
	})
	Kakao.init('28fd7148fc253f83a67ad56c9d28606f');
</script>
</html>