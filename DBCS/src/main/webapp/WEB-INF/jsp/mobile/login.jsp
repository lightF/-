<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="head.sub.jsp" %>

	<body>
		<div class="login mobile">
			<div class="box">
				<div class="login_logo">
					<img src="../../css/img/login_logo.png">
				</div>
				<div class="input">
					<form name="" action="" method="">
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
						<button class="btn">LOGIN</button>
						<div class="chk">
							<div>
								<input type="checkbox" name="auto" id="auto">
								<label for="auto"><span></span>자동로그인</label>
							</div>
							<div>
								<input type="checkbox" name="id_save" id="id_save">
								<label for="id_save"><span></span>아이디 저장</label>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>