<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html lang="ko">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>RATTY | 회원 수정</title>
		</head>

		<body>
			<jsp:include page="../layout/layoutHeader.jsp" />
			<main>
				<h1>회원 수정</h1>
				<form id="uForm">
					<h2>${member.id}</h2>
					<input type="hidden" name="id" value="${member.id}" />
					<input type="hidden" name="action" value="update" />
					<div>
						<label for="name">이름:</label>
						<input type="text" id="name" name="name" required value="${member.name}">
					</div>
					<div>
						<label for="password">비밀번호:</label>
						<input type="password" id="password" name="password" required>
						<label for="password2">비밀번호확인:</label>
						<!-- 서버로 보내지 않을 내용은 name을 써주지않는다. -->
						<input type="password" id="password2" required>
					</div>
					<div>
						<label for="phone">전화번호:</label>
						<input type="tel" id="phone" name="phone" required value="${member.phone}">
					</div>
					<div>
						<label for="address">주소:</label>
						<input type="text" id="address" name="address" required value="${member.address}">
					</div>
					<div>
						<label>성별:</label>
						<input type="radio" id="female" name="gender" value="female" ${member.gender.equals('female') ? 'checked'
							:''} disabled>
						<label for="female">여성</label>
						<input type="radio" id="male" name="gender" value="male" ${member.gender.equals('female') ? '' :'checked'}
							disabled>
						<label for="male">남성</label>
					</div>
					<div>
						<label>취미:</label>
						<c:forEach var="hobby" items="${hobbyList}">
							<input type="checkbox" id="${hobby.id}" name="hobbies" value="${hobby.hobby}" ${member.hobbies[hobby.id]
								!=null ? 'checked' : '' }>
							<label for="${hobby.id}">${hobby.hobby}</label>
						</c:forEach>
					</div>
					<button type="submit">수정</button>
					<a href="member?action=view&id=${member.id}">취소</a>
				</form>
			</main>
			<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
			<script>
				// 업데이트 요청을 보내는 이벤트 리스너
				const uForm = document.getElementById("uForm");
				uForm.addEventListener("submit", (e) => {
					e.preventDefault();
					
					// 유효성 검사
					if(!validateSamePassword(password, password2 ,()=>{password.focus()})) return;

					// 전화번호는 XXX-XXXX-XXXX 형식으로만 입력받음
					if(!validatePhoneNumber(phone, ()=>{phone.focus()})) return;

					fetch("member", {
						method: "POST",
						body: formToSerialize("uForm"),
						headers: { "Content-type": "application/json; charset=utf-8" }
					}).then((res) => res.json())
						.then((data) => {
							if (data.status === 204) {
								alert("회원 정보 수정에 성공했습니다.");
								// 페이지 리다이렉트
								location = "member?action=view&id=${member.id}";
							} else {
								alert(data.statusMessage);
							}
						});
				});
			</script>
		</body>

		</html>