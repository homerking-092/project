<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>


	<h3>위에는 영화정보</h3>
	<h3>위에는 영화정보</h3>
	<h3>위에는 영화정보</h3>
	<h3>위에는 영화정보</h3>
	<h3>위에는 영화정보</h3>
	<h3>위에는 영화정보</h3>
	<h3>위에는 영화정보</h3>


	<a href="/MovieProject/review/review_write_form.jsp"><input type="button" value="리뷰작성"> </a>
	<table border="1">
		<tr>
			<td>id</td>
			<td>평점</td>
		</tr>
		<tr>
			<td><textarea rows="10" cols="30">내용</textarea></td>
			<td><select onchange="if(this.value) location.href=(this.value);">
					<option value=""></option>	
					<option value="/MovieProject/review/review_update_form.jsp">수정</option>		
					<option value="삭제">삭제</option>		
				</select>
			</td>
		</tr>
		<tr>
			<td>작성날짜</td>
		</tr>

	</table>
</body>
</html>