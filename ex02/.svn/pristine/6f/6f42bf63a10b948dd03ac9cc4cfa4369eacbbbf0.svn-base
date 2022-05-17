<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board Read Page</h1>
	<h2>cri : ${cri}</h2>
	<form method="post">
		<h4><label for="title">title</label></h4>
		<input name="title" id="title" value="${board.title}" readonly>
		
		<h4><label for="content">content</label></h4>
		<textarea rows="20" cols="40" name="content" id="content" readonly>${board.content}</textarea>
		
		<h4><label for="writer">writer</label></h4>
		<input name="writer" id="writer" value="${board.writer}" readonly>
		
		<hr>
		<a href="modify${cri.params}&bno=${board.bno}">수정</a>
		<a href="list${cri.params}">목록</a>
	</form>
</body>
</html>