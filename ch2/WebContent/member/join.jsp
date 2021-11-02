<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/member/joinProcess.jsp" method="post">
		<div class="form-group">
			<label for="userid">아이디</label> <input type="text"
				class="form-control" id="userid" name="userid">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> <input type="password"
				class="form-control" id="password" name="password">
		</div>
		<div class="form-group">
			<label for="passwordCom">비밀번호 확인</label> <input type="password"
				class="form-control" id="passwordCom" name="passwordCom">
		</div>
		<div class="form-group">
			<label for="username">이름</label> <input type="text"
				class="form-control" id="username" name="username">
		</div>
		<div class="form-group">
		<label for="gender">성별
			<label><input type="checkbox" name="gender" value="남">남</label>
			<label><input type="checkbox" name="gender" value="여">여</label>
			</label>
		</div>
		<div class="form-group">
			<label for="email">이메일</label> <input type="text"
				class="form-control" id="email" name="email">
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>