<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<label for="userid">userid</label> 
		<input type="text" name="userid" id="userid">
	</div>
	<div>
		<label for="password">password</label> 
		<input type="password" name="password" id="password">
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
$(function () {
	$("#userid").change(function () {
		//userid 중복 검사를 시행하기, userid input창에서 변화가 일어나면 실행
		$.ajax({
			//데이터를 보낼 url
			url:'registerProcess.jsp',
			//데이터를 보낼 방식
			type:'post',
			//보낼 데이터
			data:{
				userid:$("#userid").val()
			},
			success:function(data){
				//alert(data);
				//process에서 보낸 true가 공백속 true로 인식이 되기 때문에, 올바르게
				//비교할 수 없음, 그래서 공백제거(trim)를 해야함
				if($.trim(data)=='true'){
					alert("아이디는 사용이 가능합니다.");
				}else{
					alert("아이디는 사용이 불가능합니다.");
				}
			},
			error:function(xhr,textStatus,error){
				
			}
		})
	})
})
</script>
</body>
</html>