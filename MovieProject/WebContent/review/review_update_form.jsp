<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>리뷰 수정폼</h3>
	<form action="/MovieProject/reviewupdateok.do" method="post">
	<table border="1">
		<tr>
			<td>id</td>
		</tr>
		<tr>
			<td>평점</td>
		</tr>
		<tr>
			<td><textarea rows="10" cols="30">내용</textarea></td>
			
		</tr>
		<tr>
			<td>작성날짜</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="수정하기">
				<input type="reset" value="초기화">
				<a href="/MyFirstWeb/boardselect.do">
				<input type="button" value="게시글 목록"></a>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>