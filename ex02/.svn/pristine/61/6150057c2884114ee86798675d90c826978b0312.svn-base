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
	<form method="post">
		<input type="hidden" name="bno" value="${board.bno}">
		<h4><label for="title">title</label></h4>
		<input name="title" id="title" value="${board.title}">
		
		<h4><label for="content">content</label></h4>
		<textarea rows="20" cols="40" name="content" id="content">${board.content}</textarea>
		
		<h4><label for="writer">writer</label></h4>
		<input name="writer" id="writer" value="${board.writer}">
		<input type="hidden" name="pageNum" value="${cri.pageNum}">
		<input type="hidden" name="amount" value="${cri.amount}">
		<input type="hidden" name="type" value="${cri.type}">
		<input type="hidden" name="keyword" value="${cri.keyword}">
		<hr>
		<button type="submit" formaction="modify">수정</button>
		<button type="submit" formaction="remove">삭제</button>
		<a href="list${cri.params}">목록</a>
	</form>
</body>
</html>