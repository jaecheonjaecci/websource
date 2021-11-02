<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("UTF-8");
String userid = request.getParameter("userid");
String password = request.getParameter("password");
String passwordCom = request.getParameter("passwordCom");
String username = request.getParameter("username");
String gender = request.getParameter("gender");
String email = request.getParameter("email");

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>아이디 : <%=userid%></h2>
	<h2>비밀번호 : <%=password %></h2>
	<h2>비밀번호확인 : <%=passwordCom %></h2>
	<h2>이름 : <%=username%></h2>
	<h2>성별 : <%=gender%></h2>
	<h2>이메일 : <%=email %></h2>
</body>
</html>