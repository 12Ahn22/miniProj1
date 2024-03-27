<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RATTY | 게시글 수정</title>
</head>
<body>
	<jsp:include page="../layout/layoutHeader.jsp"/>
	<main>
		<h2>게시물 수정</h2>
		<form>
			<label for="title">제목:</label><br>
			<input type="text" id="title" name="title" value="${board.title}"><br>
			<label for="content">내용:</label><br>
			<textarea id="content" name="content" rows="4" cols="50">${board.content}</textarea><br><br>
			<input type="submit" value="수정">
			<a href="board?action=view&bno=${board.bno}">취소</a>
	</form>
	</main>
</body>
</html>