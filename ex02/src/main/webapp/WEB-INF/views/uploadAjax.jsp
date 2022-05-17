<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	$(function() {
		var $clone = $(".uploadDiv").clone();
		
		/* 
		정규표현식 리터럴
		*/
		var regexp = /(.*?)\.(exe|sh|js|jsp)$/;
		var maxSize = 1024 * 1024 * 5;
		
		function validateFiles(fileName, fileSize) {
			if(fileSize > maxSize) {
				alert("파일 사이즈 초과");
				return false;
			}
			if(regexp.test(fileName)) {
				alert("해당 파일의 종류는 업로드할 수 없습니다.");
				return false;
			}
			return true;
		}
		
		$(".uploadDiv").on("change", ":file", function() {
			var formData = new FormData();
			
			for(var i in this.files) {
				if(!validateFiles(this.files[i].name, this.files[i].size)) {
					return false;
				}
				formData.append("files", this.files[i]);
			}
			
			$.post({
				processData : false,
				contentType : false,
				data : formData,
				url : "/upload",
				dataType : "json"
			}).done(function(result) {
				console.log(result);
				$(".uploadDiv").html($clone.html());
			})
		})
	})
</script>
</head>
<body>
	<div class="uploadDiv">
		<input type="file" multiple>
	</div>
	<!-- input태그의 조작을 하기 위해 누군가의 자손으로 만드는것이 편하다. -->
</body>
</html>