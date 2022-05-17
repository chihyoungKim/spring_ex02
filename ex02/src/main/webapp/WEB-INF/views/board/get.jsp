<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<sec:csrfMetaTags/>
</head>
<body id="page-top">
${_csrf.headerName}<br>
${_csrf.parameterName}<br>
${_csrf.token}
    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
<jsp:include page="../common/nav.jsp" />

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 text-gray-800 mb-4">Board Register Page</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary float-left">Board Read Page</h6>
                        </div>
                        <div class="card-body">
                            <form method="post">
								<div class="form-group">
									<label for="title">title</label>
									<input type="text" class="form-control" placeholder="title" id="title" name="title" value="${board.title}" readonly>
								</div>
								<div class="form-group">
									<label for="content">content</label>
									<textarea class="form-control" placeholder="content" id="content" name="content" readonly>${board.content}</textarea>
								</div>
								<div class="form-group">
									<label for="writer">writer</label>
									<input type="text" class="form-control" placeholder="writer" id="writer" name="writer" value="${board.writer}" readonly>
								</div>
								
								
								
								
								<sec:authorize access="isAuthenticated() && principal.username == #board.writer">
									<a class="btn btn-warning btn-sm" href="modify${cri.params}&bno=${board.bno}">수정</a>
								</sec:authorize>
							</form>
                        </div>
                    </div>
					<div class="card shadow mb-4">
						<div class="card-header py-3">
	                        <h6 class="m-0 font-weight-bold text-primary float-left">files</h6>
	                    </div>
                       	<div class="card-body">
                           <ul class="list-group small container px-1 upload-files">
                           </ul>
                           <div class="container pt-3 px-1">
                               <div class="thumbs row">
                           </div>
                        </div>
                        </div>
                    </div>

                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary float-left">Reply</h6>
                        </div>
                        <sec:authorize access="isAuthenticated()">
	                        <div class="input-group p-3 reply-register-area">
	                        	<textarea class="form-control" style="resize:none"></textarea>
	                        	<div class="input-group-append">
	                        		<button type="button" class="btn btn-primary">작성</button>
	                        	</div>
	                        </div>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
	                        <div class="text-center py-4">
	                        	<a href="/member/login">댓글 등록은 로그인 이후에</a>
	                        </div>
                        </sec:authorize>
                        
                        <div class="card-body list-group p-3 chat">
	                        <!-- <div class="list-group-item p-3 bg-dark text-white" data-rno="1234">
	                        	<Strong>작성자</Strong>
	                        	<small class="float-right mt-1">게시일</small>
								<div class="dropdown float-right mr-2">
									<a href="#" data-toggle="dropdown" class="small text-white">
										<i class="fas fa-ellipsis-v"></i>
									</a>
									<div class="dropdown-menu small">
										<a class="dropdown-item" href="#">수정</a>
										<a class="dropdown-item" href="#">삭제</a>
									</div>
								</div>
							</div>
							<div class="reply-content">
								<p class="p-3 list-group-item mb-0 d-none">글 내용</p>
		                        <div class="input-group">
		                        	<textarea class="form-control" style="resize:none">글 내용</textarea>
		                        	<div class="input-group-append">
		                        		<button type="button" class="btn btn-warning rounded-0">작성</button>
		                        	</div>
		                        </div>
	                        </div> -->
                        </div>
                        <div class="p-3">
							<button class="btn btn-info btn-block btn-more">더보기</button>
						</div>
                        
                        
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
			<jsp:include page="../common/footer.jsp" />

            </div>
            <!-- End of Main Content -->
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.username" var="replyer"/>
			</sec:authorize>
            <script src="/resources/js/reply.js"></script>
            <script>
            	var bno = '${board.bno}'
            	var replyer = '${replyer}'
            	var headerName = $("meta[name='_csrf_header']").attr("content");
            	var token = $("meta[name='_csrf']").attr("content");
            	
            	/* $(document).ajaxSend(function(e, xhr) {
            		xhr.setRequestHeader(headerName, token);
            	}); */
            	
            	
            	console.log(replyService);
            	
            	$(function() {

					function getReplyStr(reply) {
						var str = "";
						str += '<div class="list-group-item p-3 bg-dark text-white" data-rno="' + reply.rno + '">';
								str += '	<Strong>' + reply.replyer + '</Strong>';
								str += '	<small class="float-right mt-1">' + replyService.displayTime(reply.replyDate) + '</small>';
									
								if(replyer && reply.replyer == replyer) {
									str += '	<div class="dropdown float-right mr-2">';
									str += '		<a href="#" data-toggle="dropdown" class="small text-white">';
									str += '			<i class="fas fa-ellipsis-v"></i>';
									str += '		</a>';
									str += '		<div class="dropdown-menu small">';
									str += '			<a class="dropdown-item btn-reply-modify" href="#">수정</a>';
									str += '			<a class="dropdown-item btn-reply-remove" href="#">삭제</a>';
									str += '		</div>';
									str += '	</div>';
								}
								
								str += '</div>';
								str += '<div class="reply-content">';
								str += '	<p class="p-3 list-group-item mb-0" style="white-space:pre-line">' + reply.reply + '</p>';
								str += '	<div class="input-group d-none">';
								str += '		<textarea class="form-control" style="resize:none">글 내용</textarea>';
								str += '		<div class="input-group-append">';
								str += '			<button type="button" class="btn btn-warning rounded-0">작성</button>';
								str += '		</div>';
								str += '	</div>';
								str += '</div>';
								return str;
					}

					//전역으로 
					var lastRno;
					var amount; // undefined
					
					// 댓글 목록 조회
            		function showList(lastRno, amount) {
						console.log(lastRno, amount)
						var param = {bno: bno, lastRno: lastRno, amount: amount}
            			replyService.getList(param, function(result) {
                    		console.log(result);
							var str = '';
							for(var i in result) {
								str += getReplyStr(result[i]);
							}
							$(".chat").html(str);
                    	}, function(xhr) {
                    		console.log(xhr);
                    	})
            		}
					showList(lastRno, amount);

					/* event */
					// 댓글 등록
					$(".reply-register-area button").click(function() {
						var replyContent = $(".reply-register-area textarea").val()
						if(replyContent.trim().length == 0) {
							alert("댓글 내용을 작성하시오")
							$(".reply-register-area textarea").focus();
							return;
						}
						var reply = {bno:bno, reply: replyContent, replyer:replyer};
						replyService.add(reply, function(result){
							alert("글 등록 성공");
							console.log(result);
							$(".reply-register-area textarea").val("");
							replyService.get(result, function(result) {
								console.log(result); // reply object
								$(".chat").prepend(getReplyStr(result)); // 댓글 목록 자식 태그중 가장 첫번째 순서에 추가
							});
						});
					})

					//댓글 삭제
					$(".chat").on("click", ".btn-reply-remove", function() {
						event.preventDefault();
						// alert($(this).parent().parent().parent().data("rno"))
						var $dom = $(this.closest(".bg-dark"));
						// console.log($dom.next().html())
						var rno = $dom.data("rno"); // closest는 조상태그중에 가장가까운거 반대는find
						
						replyService.remove({rno : rno, replyer : $dom.find("strong").text()}, function(result) {
							if(result && result === 'success') {
	            				alert("글 삭제 성공");
	            				console.log(result);
								$dom.next().remove();
								$dom.remove();
							}
            			}, function(xhr) {
            				var str = "";
            				if(xhr.status==500) {
            					str = "비로그인";
            				}
            				if(xhr.status==403) {
            					str = "다른 유저";
            				}
	            			alert(str + " 삭제 실패");
            			});
					})

					//댓글 수정
					$(".chat").on("click", ".btn-reply-modify", function() {
						event.preventDefault();
						var $dom = $(this.closest(".bg-dark"));
						var rno = $dom.data("rno");
						console.log(rno);



						$(".reply-content > p").show();
						$(".reply-content > div").addClass("d-none");

						var $p = $dom.next().find("p");
						$dom.next().find("p").hide();
						$dom.next().find("div").find("textarea").val($p.text()).end().removeClass("d-none")

					})

					// 댓글 수정 반영
					$(".chat").on("click", ".reply-content button", function() {
						var $replyContent = $(this).closest(".reply-content");
						var rno = $replyContent.prev().data("rno")
						var $dom = $(this.closest(".bg-dark"));
						var content = $replyContent.find("textarea").val();
						console.log(rno)
						console.log(content)

						var reply = {rno:rno, reply:content, replyer:$dom.find("strong").text()};


						replyService.update(reply, function(result){
							
							//성공
							if(result && result === 'success') {
								$replyContent.find("p").text(content).show();
								$replyContent.find("div").addClass("d-none");
							}
						}, function() {
							alert("실패");
						});
					})

					// 더보기 버튼
					$(".btn-more").click(function() {
						var lastRno = $(".chat > .bg-dark").last().data("rno");
						console.log(lastRno);
						var param = {bno:bno, lastRno:lastRno};


						var $btnMore = $(this);
						replyService.getList(param, function(result) {
                    		console.log(result);
							var str = '';
							for(var i in result) {
								str += getReplyStr(result[i]);
							}
							$(".chat").append(str);
                    	}, null, function() {
							$btnMore.prop("disabled", true)
						}, function(result) {
							if(result.length == 0) $btnMore.remove();
							setTimeout(function () {
								$btnMore.prop("disabled", false)
							}, 1000)
						})
					})

					$(document).scroll(function() {
						var dh = $(document).height();
						var wh = $(window).height();
						var wst = $(window).scrollTop();
						if(dh == wh + wst) {
							var lastRno = $(".chat > .bg-dark").last().data("rno");
							var param = {bno:bno, lastRno:lastRno};
							replyService.getList(param, function(result) {
								var str = '';
								for(var i in result) {
									str += getReplyStr(result[i]);
								}
								$(".chat").append(str);
                    		})
						}
						// console.log(dh, wh, wst);
					})
					
					// 첨부된 파일의 확인
					var showFiles = function() {
						$.getJSON("/board/attachs", {bno:bno})
						.done(function(result) {
							console.log(result);

							var str = "";
							var thumbStr = "";
							for(var i in result) {
								console.log(result[i])
								console.log($.param(result[i]))
								str += '<li class="list-group-item" data-uuid="' + result[i].uuid + '" data-path="' + result[i].path + '" data-image="' + result[i].image + '" data-origin="' + result[i].origin + '"><a href="/download?' + $.param(result[i]) + '">' + result[i].origin + '</a></li>'
								if(result[i].image) {
									var o = {...result[i]}; // clone문법
									o.uuid = 's_' + o.uuid;

									thumbStr += '<div class="col-6 col-sm-4 col-lg-3 col-xl-2" data-uuid="' + result[i].uuid + '" data-path="' + result[i].path + '" data-image="' + result[i].image + '" data-origin="' + result[i].origin + '">';
									thumbStr += '	<figure class="d-inline-block" style="position:relative;">';
									thumbStr += '		<figcaption><span>&times;</span></button></figcaption>';
									thumbStr += '		<a href="/display?' + $.param(result[i]) + '" data-lightbox="img" data-title="' + o.origin + '"><img alt="" src="/display?' + $.param(o) + '" class="mx-1 my-2 img-thumbnail"></a>';
									thumbStr += '	</figure>';
									thumbStr += '</div>';
								}
							}
							$(".upload-files").append(str);
							$(".thumbs").append(thumbStr);
						})
					}
					
					showFiles();
					
					
            	})
				



            	// 글 등록
            	// reply, callback, error
            	/*
            	replyService.add({bno:bno, reply:"get.jsp 작성테스트", replyer:"작성자"}, function(result){
            		alert("글 등록 성공");
            		console.log(result);
            	});
            	*/
            	
            	// 삭제
            	/*
            	replyService.remove(16, function(result) {
            		alert("글 삭제 성공");
            		console.log(result);
            	});
            	*/
            	
            	// 조회
            	/*
            	replyService.get(16, function(result) {
            		alert("글 조회 성공");
            		console.log(result);
            	});
            	*/
            	
            	// 수정
            	/*
            	replyService.update({rno:16, reply:"get.jsp 수정테스트"}, function(result){
            		alert("글 수정 성공");
            		console.log(result);
            	});
            	*/
            	
            </script>
            
        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
	
</body>

</html>