<%@page import="user.domain.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="user.persistence.MemberDAO"%>
<%@page import="user.persistence.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//전체 조회 후 페이지 이동
	Connection con = JdbcUtil.getConnection();
	MemberDAO dao = new MemberDAO(con);

	List<MemberDTO> list = dao.getRows();
	JdbcUtil.close(con);

	request.setAttribute("list", list);
	//request에 담았으니까 무조건 forward로 이동해야 함
	pageContext.forward("all.jsp");
	
%>