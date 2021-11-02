<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //post 방식 한글깨짐 방지하기 위함
    request.setCharacterEncoding("utf-8");
    //사용자의 요청을 가져올 때 request로 처리
    //사용자의 요청은 무조건 String 형태임
    //가로안에 네임이 들어감, 불어올때 네임을 사용
    String username = request.getParameter("username");
    String age = request.getParameter("age");
    String gender = request.getParameter("gender"); 
    //getParameter를 사용하는 경우 여러개를 가져올 수 없고, 처음에 선택한 하나만 가져오게 됨
    //String fruits = request.getParameter("fruits");
    
    //여러개를 가져오고 싶은 경우 배열로 가져와야 함
    String fruits[] = request.getParameterValues("fruits");
  
    String page1 = request.getParameter("page"); 
    String bno = request.getParameter("bno"); 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>이름 : <%=username%> : <%=age%> : <%=gender%> : <%=Arrays.toString(fruits)%></h3>
<h3>page : <%=page1%>, bno : <%=bno %></h3>
</body>
</html>