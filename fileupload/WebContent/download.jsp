<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//파일명 가져오기
	String fileName = request.getParameter("fileName");
	

	//전송된 파일이 실제로 저장된 폴더로 가서 해당 파일 읽어오기
	String uploadPath = "e:\\upload\\"+fileName;
	FileInputStream fis = new FileInputStream(uploadPath);
	
	
	//.getOutputStream() 이미 호출되었습니다. => 이미 불러진게 있어서 또 불려졌다고 콘솔에 뜨는 것
	//											안 뜨게 하려면 이미 서버쪽에 불려진 것을 종료시키면 됨 
	//											서버쪽에 뜨는 경고문이라 무시해도 되긴 함
	out.clear();
	out=pageContext.pushBody();
	
	
	//uuid 값을 떼내고 다운로드 하기 : 언더바 이전을 지우고, 나머지만 남기기
	int pos = fileName.indexOf("_");
	fileName = fileName.substring(pos+1);
	
	
	//브라우저를 통해서 다운로드 하기
	//MIME타입(text,img...) 작성 : application/octet-stream는 모든 미디어 타입을 처리가능
	response.setContentType("application/octet-stream");
	
	//url에 붙은 한글은 자동으로 인코딩 되기 때문에 그 파일 이름으로는 파일이 원래 있던 폴더에서 
	//파일을 찾을 수 없음 =>  다시 원래 파일명으로 디코딩을 해줘야 함
	fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
	
	//파일을 브라우저를 통해 같이 보내기(이미 정의되어 있어서 이대로 사용해야함)
	response.setHeader("Content-Disposition", "attachment;filename="+fileName);

	
	//읽어온 파일을 클라이언트 브라우저로 전송
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	int numRead = 0;
	byte[] b = new byte[4896];
	while((numRead = fis.read(b,0,b.length))!=-1){
		bos.write(b, 0, numRead);
	}
	bos.flush();
	bos.close();
	fis.close();
%>