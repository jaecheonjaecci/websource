<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//form1.jsp에서 사용자가 입력한 값을 서버로 가져오는 역할을 하는 페이지
// : HttpServleetRequest request를 사용함, 변수명은 request로 고정
// request.getParameter("요소이름작성"); 

//가져오는 값에 대한 인코딩 처리 : 안하는 경우 한글이 깨짐
request.setCharacterEncoding("utf-8");

pageContext.forward("forward1.jsp");



/* String userid=request.getParameter("userid");
String password = request.getParameter("password");
String username = request.getParameter("username");
 */


%>
<%-- <p><%=userid %></p>
<p><%=password %></p>
<p><%=username %></p>
<p>client addr : <%=request.getRemoteAddr() %></p>
<p>client host : <%=request.getRemoteHost() %></p>
<p>client port : <%=request.getRemotePort() %></p>
<p>request URI : <%=request.getRequestURI() %></p>
<p>request protocol : <%=request.getProtocol() %></p> --%>