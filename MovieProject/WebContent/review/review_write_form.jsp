<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>���� �����</h1>
	<form action="/MovieProject/reviewwrite.do" method="post">
		<table border="1">
			<tr>
				<td>������</td>
				<td><input type="text" name="title" size="10"></td>
			</tr>
			<tr>
				<td>����</td>
				<td><textarea rows="20" cols="20" name="content"></textarea></td>
			</tr>
			<tr>
				<td>�۾���</td>
				<td><input type="text" name="writer" size="10"></td>
			</tr>
			<tr>
				<td><input type="submit" value="���"> <input
					type="reset" value="�ʱ�ȭ"></td>
			</tr>
		</table>
	</form>

</body>
</html>