<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

//request 객체 : 사용자의 요청을 가지고 올 수 있다.
//               사용자가 보낸 값도 가져올 수 있음

//sendRedirect() / forward() 사용시 현재 페이지가 보여지지 않기 때문에
//                           필요없는 코드가 됨

/* String page1 = request.getParameter("page");
String password = request.getParameter("password");
out.print("page1 : " + page1+"<br>");
out.print("password : " +password+"<br>"); */


//response.sendRedirect("adminProcess.jsp");
pageContext.forward("adminProcess.jsp");

%>
<!-- <h2><a href="adminProcess.jsp">adminProcess</a></h2> -->