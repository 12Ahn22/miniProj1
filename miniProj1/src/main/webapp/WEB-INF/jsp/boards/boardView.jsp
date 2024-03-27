<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RATTY | 게시글 상세</title>
</head>
<body>
	<jsp:include page="../layout/layoutHeader.jsp"/>
	<main>
		<h1>${board.title}</h1>
		<div><span>작성자: ${board.author}</span><span>조회수: ${board.viewCount}</span><span>작성일: ${board.createdAt}</span></div>
		<div>
			${board.content}
		</div>
	</main>
</body>
</html>