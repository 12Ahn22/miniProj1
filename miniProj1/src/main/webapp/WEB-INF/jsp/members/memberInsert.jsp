<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html lang="ko">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>RATTY | 회원 가입</title>
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		</head>
		<body>
			<div class="container">
				<jsp:include page="../layout/layoutHeader.jsp" />
				<main>
					<h1>회원 가입</h1>
					<form id="rForm">
						<input type="hidden" name="action" value="insert" />
						<div>
							<label for="id">아이디:</label>
							<input type="text" id="id" name="id" required>
							<input type="button" id="duplicateId" value="중복확인">
							<span id="duplicateMsg"></span>
						</div>
						<div>
							<label for="name">이름:</label>
							<input type="text" id="name" name="name" required>
						</div>
						<div>
							<label for="password">비밀번호:</label>
							<input type="password" id="password" name="password" required autocomplete="off">
							<label for="password2">비밀번호확인:</label>
							<!-- 서버로 보내지 않을 내용은 name을 써주지않는다. -->
							<input type="password" id="password2" required autocomplete="off">
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
							<input type="radio" id="female" name="gender" value="female" checked>
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
						<button class="btn btn-primary" type="submit">생성</button>
						<a class="btn btn-secondary" href="member?action=list">취소</a>
					</form>
				</main>
			</div>
			<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
			<script>
				let validUserId = false;

				// 아이디 수정 시, 중복 체크를 다시 하도록 이벤트 설정
				const id = document.getElementById("id");
				id.addEventListener("keyup",()=>{
					validUserId = false;
				})

				// 업데이트 요청을 보내는 이벤트 리스너
				const rForm = document.getElementById("rForm");
				rForm.addEventListener("submit", (e) => {
				e.preventDefault();

					if(!validUserId){
						alert("계정 중복 검사를 진행해주세요.");
						return;
					}

					// 유효성 검사
					if(!validateSamePassword(password, password2 ,()=>{password.focus()})) return;

					// 전화번호는 XXX-XXXX-XXXX 형식으로만 입력받음
					if(!validatePhoneNumber(phone, ()=>{phone.focus()})) return;

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

				// 중복 확인을 위한 코드
				const duplicateId = document.getElementById("duplicateId");
				duplicateId.addEventListener("click",()=>{
					if(!id.value){
						alert("아이디를 입력해주세요.");
						id.focus();
						return;
					}

					const duplicateMsg = document.getElementById("duplicateMsg");
					const param = {
						id: id.value,
						action:"checkDuplicateId"
					};
					fetch("member", {
						method: "POST",
						body: JSON.stringify(param),
						headers: { "Content-type": "application/json; charset=utf-8" }
					}).then((res) => res.json())
						.then((data) => {
							if (data.status === 204) {
								alert("사용 가능한 계정입니다.");
								duplicateMsg.textContent = "사용 가능한 계정입니다.";
								duplicateMsg.className = "text-success";
								validUserId = true;
							} else {
								alert(data.statusMessage);
								duplicateMsg.textContent = "계정이 중복되었습니다.";
								duplicateMsg.className = "text-danger";
								validUserId = false;
							}
						});
				});
			</script>
		</body>

		</html>