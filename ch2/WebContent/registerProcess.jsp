<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//데이터가 오는지 먼저 확인
	System.out.print(request.getParameter("userid"));

	//db작업 결과 화면에 기록
	//아이디 사용가능 true, 사용불가 false
	// out.print("true"); 는 register -> success가 받음
	out.print("true");

%>