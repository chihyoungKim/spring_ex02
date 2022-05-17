<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/resources/assets/vendor/jquery/jquery.min.js"></script>
<script>
$(function() {
	$("#btnAjaxText").on("click", function() {
		console.log("call text")
		$.ajax("/sample/getText").done(function(result) {
			console.log(result)
		});
	});
	
	$("#btnAjaxSample").on("click", function() {
		console.log("call sample")
		/* 
		$.ajax("/sample/getSample").done(function(result) {
			console.log(result)
		});
		*/
		$.ajax({
			url : "/sample/getSample",
			dataType : "json",
			success : function(result) {
				console.log(result)
			}
		})
		
	});
	$("#btnAjaxList").on("click", function() {
		console.log("call list")
		$.ajax({
			url : "/sample/getList",
			dataType : "json",
			success : function(result) {
				console.log(result)
			}
		})
		
	});
	
})
</script>
</head>
<body>
	<button id="btnAjaxText">ajax call</button>
	<button id="btnAjaxSample">ajax sample call</button>
	<button id="btnAjaxList">ajax list call</button>
</body>
</html>