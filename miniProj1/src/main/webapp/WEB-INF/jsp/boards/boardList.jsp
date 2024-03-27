<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RATTY | 게시글 목록</title>
</head>
<body>
	<jsp:include page="../layout/layoutHeader.jsp"/>
	<main>
		<h1>회원 리스트</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">no</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.bno}</td>
						<td><a href="board?action=view&id=${board.bno}">${board.title}</a></td>
						<td>${board.author}</td>
						<td>${board.createdAt}</td>
						<td>${board.viewCount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>