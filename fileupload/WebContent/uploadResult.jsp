<%@page import="java.util.UUID"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		note :
		<%=request.getParameter("note")%></h1>
	<h1>
		upfile :
		<%=request.getParameter("upfile")%></h1>
	<hr>

	<%-- commons fileupload 이용한 처리 --%>
	<%
		//사용자가 요청한 것이 enctype="multipart/form-data"인지 확인하는 작업
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	//true라면
	if (isMultipart) {
		//전송된 파일을 디스크에 저장하기위한 객체
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 가능 사이즈를 지정
		upload.setSizeMax(2000 * 1024);

		//사용자의 request를 분석 => 리스트에 담기 : fileitem이라는 구조를 이용해서
		//                                           리스트에 담음
		List<FileItem> fileItems = upload.parseRequest(request);
		String fieldName = null, fileName = null, value = null;

		/* 		//향상된 for문을 사용해서 출력하는 것도 가능
		for(FileItem item:fileItems){
			if(item.isFormField()){
				fieldName = item.getFieldName();
				value = item.getString("utf-8");
				out.print("<h3>일반데이터</h3>");
				out.print(fieldName+" : "+value+"<br>");
				
			//type이 file로 넘어오는 경우
			}else{
				fieldName = item.getFieldName();
				fileName = item.getName();
				long size = item.getSize();
				out.print("<h3>파일 데이터</h3>");
				out.print(fieldName+" : "+fileName+"<br>");
				out.print("파일크기 : "+size);
			}
		}
		 */

		//iterator는 인터페이스, 자료구조마다 가져오는 구조가 다른데, 데이터를 iterator에
		//담으면 통일된 방식으로 가져올 수 있음

		//리스트에 담긴 데이터를 iterator에 담음
		Iterator<FileItem> iter = fileItems.iterator();
		while (iter.hasNext()) {
			//가지고 올 내용이 있으면이라는 뜻
			FileItem item = iter.next();

			//type이 formfieid(text,password,checkbox....)로 넘어오는 경우
			if (item.isFormField()) {
		//uploadform에서 #name 가져오기
		fieldName = item.getFieldName();

		//요소안에 들어있는 value 값 가져오기
		value = item.getString("utf-8");
		out.print("<h3>일반데이터</h3>");
		out.print(fieldName + " : " + value + "<br>");

		//type이 file로 넘어오는 경우
			} else {
		//uploadform에서 #name 가져오기
		fieldName = item.getFieldName();
		//파일명 가져오기
		fileName = item.getName();
		//업로드 된 파일 사이즈
		long size = item.getSize();

		File file = null;
		
		if (!fileName.isEmpty()) {
			//파일 저장
			String path = "e:\\upload\\";

			//파일 저장 전 중복되지 않는 고유한 키 값 생성 
			//       : 중복된 파일명을 업로드 할 경우 오류 발생
			UUID uuid = UUID.randomUUID();
			file = new File(path + uuid.toString() + "_" + fileName);//업로드 파일명
			item.write(file);

		}

		out.print("<h3>파일 데이터</h3>");
		out.print(fieldName + " : " + fileName + "<br>");
		out.print("파일크기 : " + size);
		
		//올린 파일을 다운로드 할 수 있게 하기 
		//다운로드 시킬 파일명을 download.jsp로 보내주기
		out.print("<p>");
		out.print("<a href='download.jsp?fileName="+file.getName()+"'>"+fileName+"</a>");
		out.print("</p>");
			}
		}
	}
	%>
</body>
</html>