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
	<h1>sample/all</h1>
	<p><a href="member">member</a></p>
	<p><a href="admin">admin</a></p>
	
	<sec:authentication property="principal" var="p"/>
	<h2>principal : ${p}</h2>
	<h2>principal : ${p == 'anonymousUser'}</h2>
	<%-- <h2>principal.member : ${p.member}</h2> --%>
	<sec:authorize access="isAnonymous()"><!-- security에서의 조건식이라 생각하면된다 -->
		<h3>비 로그인</h3>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()"><!-- security에서의 조건식이라 생각하면된다 -->
		<h3>로그인</h3>
		<sec:authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_MEMBER')">
			<h3>관리자</h3>
		</sec:authorize>
	</sec:authorize>
	
	<sec:authorize access="isFullyAuthenticated()">
		<h3>remember-me가 아님</h3>
	</sec:authorize>
</body>
</html>