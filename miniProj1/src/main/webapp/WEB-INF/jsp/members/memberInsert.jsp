<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html lang="ko">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>RATTY | 회원 가입</title>
		</head>

		<body>
			<!-- 임시로 로그인 여부 처리 -->
			<jsp:include page="../layout/layoutHeader.jsp">
				<jsp:param name="isLogin" value="false" />
			</jsp:include>
			<main>
				<h1>회원 가입</h1>
				<form id="rForm">
					<input type="hidden" name="action" value="insert" />
					<div>
						<label for="id">아이디:</label>
						<input type="text" id="id" name="id" required>
					</div>
					<div>
						<label for="name">이름:</label>
						<input type="text" id="name" name="name" required>
					</div>
					<div>
						<label for="password">비밀번호:</label>
						<input type="password" id="password" name="password" required>
					</div>
					<div>
						<label for="password2">비밀번호확인:</label>
						<input type="password" id="password2" name="password2" required>
					</div>
					<div>
						<label for="phone">전화번호:</label>
						<input type="tel" id="phone" name="phone" required >
					</div>
					<div>
						<label for="address">주소:</label>
						<input type="text" id="address" name="address" required >
					</div>
					<div>
						<label>성별:</label>
						<input type="radio" id="female" name="gender" value="female">
						<label for="female">여성</label>
						<input type="radio" id="male" name="gender" value="male">
						<label for="male">남성</label>
					</div>
					<div>
						<label>취미:</label>
						<c:forEach var="hobby" items="${hobbyList}">
							<input type="checkbox" id="${hobby.id}" name="hobbies" value="${hobby.hobby}">
							<label for="${hobby.id}">${hobby.hobby}</label>
						</c:forEach>
					</div>
					<button type="submit">생성</button>
					<a href="member?action=list">취소</a>
				</form>
			</main>
			<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
			<script>

				// 업데이트 요청을 보내는 이벤트 리스너
				const rForm = document.getElementById("rForm");
				rForm.addEventListener("submit", (e) => {
					e.preventDefault();
					fetch("member", {
						method: "POST",
						body: formToSerialize("rForm"),
						headers: { "Content-type": "application/json; charset=utf-8" }
					}).then((res) => res.json())
						.then((data) => {
							if (data.status === 204) {
								alert("회원 가입에 성공했습니다.");
								// 페이지 리다이렉트
								location = "member?action=list";
							} else {
								alert(data.statusMessage);
							}
						});
				});
			</script>
		</body>

		</html>