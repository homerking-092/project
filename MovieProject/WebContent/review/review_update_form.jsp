<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>���� ������</h3>
	<form action="/MovieProject/reviewupdateok.do" method="post">
	<table border="1">
		<tr>
			<td>id</td>
		</tr>
		<tr>
			<td>����</td>
		</tr>
		<tr>
			<td><textarea rows="10" cols="30">����</textarea></td>
			
		</tr>
		<tr>
			<td>�ۼ���¥</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="�����ϱ�">
				<input type="reset" value="�ʱ�ȭ">
				<a href="/MyFirstWeb/boardselect.do">
				<input type="button" value="�Խñ� ���"></a>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>