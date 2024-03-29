<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RATTY | 게시글 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<jsp:include page="../layout/layoutHeader.jsp"/>
		<main>
			<h1>회원 리스트</h1>
			<c:if test="${isLogin}">
				<a href="board?action=insertForm">새 글 작성하기</a>
			</c:if>
			<form id="searchForm" method="get" action="board?action=list">
				<input type="text" name="searchKey" id="searchKey" placeholder="Search...">
				<input type="hidden" name="action" value="list">
				<input type="submit" value="검색">
			</form>
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
							<td><a href="board?action=view&bno=${board.bno}">${board.title}</a></td>
							<td>${board.author}</td>
							<td>${board.createdAt}</td>
							<td>${board.viewCount}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</main>
	</div>
	<script>
		const searchForm = document.getElementById("searchForm");
		searchForm.addEventListener("submit",(e)=>{
			e.preventDefault();
			searchForm.submit();
		});
	</script>
</body>
</html>