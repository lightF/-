<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>

<input type="file" id="upload" name="upload">
<label for="upload">파일 선택</label>
<button type="button" onclick="test();">업로드</button>

</body>
</html>

<script type="text/javascript">


function test(){
	var formData = new FormData();
	formData.append('upload', $('#upload')[0].files[0]);
	
	  $.ajax({
			url : 'http://localhost:8080/user/edit',
	  		type : 'post',
			dataType : 'json',
			enctype: 'multipart/form-data',
			data : formData,
			async: false,
			contentType: false,
			processData: false,
	  		success : function(data){
				// 서비스 성공 시 처리 할 내용
				console.log(data);
			},
	  		error : function(data){
				// 서비스 실패 시 처리 할 내용
	  			alert('error test');
			}
		});
}

</script>