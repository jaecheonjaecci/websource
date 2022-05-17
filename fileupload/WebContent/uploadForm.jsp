<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 파일 업로드를 하는 경우 request.getParameter로 가져올 수 없음 => 외부 라이브러리를 사용해야함-->
<!-- enctype="multipart/form-data" => 파일 업로드를 할때 해당 부분이 반드시 들어와야한다 -->
<form method="POST" enctype="multipart/form-data" action="uploadResult.jsp">
  File to upload: <input type="file" name="upfile"><br/>
  Notes about the file: <input type="text" name="note"><br/>
  <br/>
  <input type="submit" value="Press"> to upload the file!
</form>
</body>
</html>