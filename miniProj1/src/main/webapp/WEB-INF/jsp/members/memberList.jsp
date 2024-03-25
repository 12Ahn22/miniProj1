<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RATTY | 회원 목록</title>
</head>
<body>
	<!-- 임시로 로그인 여부 처리 -->
	<jsp:include page="../layout/layoutHeader.jsp">
		<jsp:param name="isLogin" value="false" />
	</jsp:include>
	<main>
		<h1>회원 리스트</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">아이디</th>
					<th scope="col">이름</th>
					<th scope="col">성별</th>
					<th scope="col">휴대번호</th>
					<th scope="col">주소</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${list}">
					<tr>
						<td>${member.id}</td>
						<td>${member.name}</td>
						<td>${member.gender}</td>
						<td>${member.phone}</td>
						<td>${member.address}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>