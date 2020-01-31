<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h1>홈에 오신것을 환영합니다.</h1>
	<a href="/home/hello?id=ssar">hello이덩-href</a>
	<br />

	<button id="hello-button">hello 페이지 이동-버튼</button>
	<br />




	<!-- <form action="/home/hello" method="post"> 이렇게 다 안적어줘도 됨-->
	<form>
		<input type="hidden" id="id" value="ssal" />
		<!-- 섭밋으로 전송안해서 네임 필요없음 -->
	</form>
	<button id="hello-button-form">hello 이동-폼</button>
	<br />

	<form>
		<input type="hidden" id="username" value="LOVE" /> <input type="hidden"
			id="phone" value="0101111" />

		<!-- 섭밋으로 전송안해서 네임 필요없음 -->
		<button id="hello-put-button">hello 이동-폼</button>

	</form>
	<br />

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

	</script>
	<script>
		//delete 호출
		$('#hello-button').on('click', function() {
			let data = {
				id : 'ssar'
			};
			$.ajax({
				type : 'DELETE',
				url : '/home/hello',
				data : JSON.stringify(data),
				contentType : 'application/json; charset=utf-8',
				dataType : 'json'
			}).done(function(result) {
				if (result.statusCode == 200) {
					alert('글이 삭제되었습니다.');
					location.href = '/home/hello';
				}
			}).fail(function() {
				alert('글 삭제 실패');
			});
		});

		//포스트
		$('#hello-button-form').on('click', function() {
			//let id = $('#id').val();
			let data={
					id: $('#id').val()
				};
			$.ajax({
				type : 'POST',
				url : '/home/hello',
				data : JSON.stringify(data),
				contentType : 'application/json; charset=utf-8', //보내는 데이터
				dataType : 'json' //응답데이터 //데이터받을땐 무조건 문자열로 받아서 이렇게 해줘야 문자열을 제이슨으로 바꿔준다.
			}).done(function(result) { //그래서 여기서 받을때 잭슨이 제이슨을 자바스크립트 오브젝트 타입으로 바꿔줘서 자바스크립트 오브젝트로 도착한다.
				if (result.statusCode == 200) {
					alert('글이 등록되었습니다.');
					location.href = '/home/hello';
				}
			}).fail(function() {
				alert('글 삭제 실패');
			});
		});

		//풋
		$('#hello-put-button').on('click', function() {
			//let id = $('#id').val();
			let data={
					id: $('#id').val(),
					phone: $('#phone').val()
				}
			$.ajax({
				type : 'PUT',
				url : '/home/hello',
				data : JSON.stringify(data),
				contentType : 'application/json; charset=utf-8', //보내는 데이터
				dataType : 'json' //응답데이터 //데이터받을땐 무조건 문자열로 받아서 이렇게 해줘야 문자열을 제이슨으로 바꿔준다.
			}).done(function(result) { //그래서 여기서 받을때 잭슨이 제이슨을 자바스크립트 오브젝트 타입으로 바꿔줘서 자바스크립트 오브젝트로 도착한다.
				if (result.statusCode == 200) {
					alert('글이 인서트되었습니다.');
					location.href = '/home/hello';
				}
			}).fail(function() {
				alert('글 삭제 실패');
			});
		});
	</script>
</body>
</html>