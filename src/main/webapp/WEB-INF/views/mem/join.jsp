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

	<h2>Edit</h2>

	<table border="1">
		<tr>
			<th>id</th>
			<th>username</th>
			<th>password</th>
			<th>email</th>
			<th>createDate</th>
		</tr>
		<tr>
			<td><input type="text" value="" /></td>
			<td><input id="password" type="password" value=""/></td>
			<td><input id="email" type="email" value="" /></td>
		</tr>
	</table>
	<button id="mem_join">join</button>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$('#mem_update_proc').on('click', function() {
			let data = {
				password : $('#password').val(),
				email : $('#eamil').val(),
				id : $('#id').val()
			};

			$.ajax({
				type : 'PUT',
				url : '/mem/api/update',
				data : JSON.stringify(data),
				contentType : 'application/Json; charset=utf-8',
				dataType : 'text'
			}).done(function(result) {
				if (result === 'ok') {
					alert('회원정보가 수정되었습니다.');
					location.href = '/mem';
				} else {
					alert('회원정보 수정 실패');
				}
			}).fail(function(result) {
				alert('서버오류');
			});
		});
	</script>
	
	<script>
		$('#mem_delete').on('click', function() {
			let data = {
				id : $('#id').val()
			};

			$.ajax({
				type : 'DELETE',
				url : '/mem/api/delete',
				data : JSON.stringify(data),
				contentType : 'application/Json; charset=utf-8',
				dataType : 'text'
			}).done(function(result) {
				if (result === 'ok') {
					alert('회원정보가 삭제되었습니다.');
					location.href = '/mem';
				} else {
					alert('회원정보 삭제 실패');
				}
			}).fail(function(result) {
				alert('서버오류');
			});
		});
	</script>
</body>
</html>