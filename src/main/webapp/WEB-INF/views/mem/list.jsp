<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>join</h2>

	<table border="1">
		<tr>
			<th>username</th>
			<th>password</th>
			<th>email</th>
		</tr>
		<tr>
			<td><input type="text" id="username" value="" /></td>
			<td><input id="password" type="password" value="" /></td>
			<td><input id="email" type="email" value="" /></td>
		</tr>
	</table>
	<button id="mem_join">join</button>
	<br />
	<br />

	<h2>List</h2>

	<c:forEach var="mem" items="${mems}">
		<table border="1">
			<tr>
				<th>username</th>
				<th>password</th>
				<th>email</th>
				<th>createDate</th>
			</tr>
			<td>${mem.username}</td>
			<td>${mem.password}</td>
			<td>${mem.email}</td>
			<td>${mem.createTime}</td>

			<tr>

			</tr>
		</table>

		<button onclick="mem_update(${mem.id})">edit</button>
		<br />
		<br />
	</c:forEach>
	<!-- for문이라서 id값 찾을 수 없어서 onclick 으로 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		function mem_update(mem_id){ //자바스크립트 낙타표기법안씀
			location.href='/mem/'+mem_id;

			}

		$('#mem_join').on('click', function() {
			let data = {
				username : $('#username').val(),
				password : $('#password').val(),
				email : $('#email').val(),
			};

			$.ajax({
				type : 'POST',
				url : '/mem/api/join',
				data : JSON.stringify(data),
				contentType : 'application/Json; charset=utf-8',
				dataType : 'text'
			}).done(function(result) {
				if (result === 'ok') {
					alert('가입완료');
					location.href = '/mem';
				} else {
					alert('회원정보 수정 실패');
				}
			}).fail(function(result) {
				alert('서버오류');
			});
		});
	</script>
	
</body>
</html>