<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>


	<h3>������ ��ȭ����</h3>
	<h3>������ ��ȭ����</h3>
	<h3>������ ��ȭ����</h3>
	<h3>������ ��ȭ����</h3>
	<h3>������ ��ȭ����</h3>
	<h3>������ ��ȭ����</h3>
	<h3>������ ��ȭ����</h3>


	<a href="/MovieProject/review/review_write_form.jsp"><input type="button" value="�����ۼ�"> </a>
	<table border="1">
		<tr>
			<td>id</td>
			<td>����</td>
		</tr>
		<tr>
			<td><textarea rows="10" cols="30">����</textarea></td>
			<td><select onchange="if(this.value) location.href=(this.value);">
					<option value=""></option>	
					<option value="/MovieProject/review/review_update_form.jsp">����</option>		
					<option value="����">����</option>		
				</select>
			</td>
		</tr>
		<tr>
			<td>�ۼ���¥</td>
		</tr>

	</table>
</body>
</html>