<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common/head.sub.jsp" %>
<link rel="stylesheet" href="resource/css/login.css">


	<body>
		<div class="login">
			<div class="box">
				<div class="login_logo">
				 	<img src="${resourcePath}/css/img/login_logo.png"> 
				</div>
				<div class="input">
					<form name="login" action="" method="">
						<div class="text">
							<div class="id">
								<p>ID</p>
								<div><input type="text" name="id"></div>
							</div>
							<div class="pw">
								<p>PW</p>
								<div><input type="password" name="pw"></div>
							</div>
						</div>
						<button class="login_btn btn" onclick="return false;">LOGIN</button>
						<div class="chk">
							<!--
							<div>
								<input type="checkbox" name="auto" id="auto">
								<label for="auto"><span></span>자동로그인</label>
							</div>
							-->
							<div>
								<input type="checkbox" name="id_save" id="id_save" value="1">
								<label for="id_save"><span></span>아이디 저장</label>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	

<%@ include file="./common/tail.sub.jsp" %>
<script type="text/javascript" src="${resourcePath}/js/login.js"></script>

