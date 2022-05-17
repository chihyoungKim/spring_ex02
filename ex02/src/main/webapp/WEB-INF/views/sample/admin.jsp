<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sample/admin</h1>
	<form action="/logout" method="post">
		<sec:csrfInput/>
		<button>logout</button>
		<hr>
		<sec:authentication property="principal" var="p"/>
		<!-- var키워드가 들어감으로써 setter로써 작동했다. -->
		<hr>
		${p}
		<hr>
		${p.username}
		<hr>
		${p.password}
		<hr>
		${p.enabled}
		<hr>
		${p.authorities}
		<hr>
		${p.member}
		<hr>
		${p.member.username}
		<hr>
		<sec:authentication property="principal.member.username"/>
	</form>
</body>
</html>