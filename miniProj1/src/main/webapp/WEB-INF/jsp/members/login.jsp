<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html lang="ko">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>RATTY | LOGIN</title>
		</head>

		<body>
			<!-- 임시로 로그인 여부 처리 -->
			<jsp:include page="../layout/layoutHeader.jsp">
				<jsp:param name="isLogin" value="false" />
			</jsp:include>
			<main>
				<h1>LOGIN</h1>
				<form id="loginForm">
					<input type="hidden" name="action" value="login">
					<div>
						<label for="id">아이디:</label>
						<input type="text" id="id" name="id" required>
					</div>
					<div>
						<label for="password">비밀번호:</label>
						<input type="password" id="password" name="password" required>
					</div>
					<input type="submit" value="로그인">
				</form>
			</main>
			<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
			<script>
				const loginForm = document.getElementById("loginForm");
				loginForm.addEventListener("submit", (e) => {
					e.preventDefault();

					fetch("member", {
						method: "POST",
						body: formToSerialize("loginForm"),
						headers: { "Content-type": "application/json; charset=utf-8" }
					}).then((res) => res.json())
						.then((data) => {
							if (data.status === 204) {
								alert("로그인에 성공했습니다.");
								// 페이지 리다이렉트
								location = "/";
							} else {
								alert(data.statusMessage);
							}
						});
				});
			</script>
		</body>

		</html>