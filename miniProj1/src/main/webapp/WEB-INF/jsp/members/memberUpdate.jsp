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
			<!-- 임시로 로그인 여부 처리 -->
			<jsp:include page="../layout/layoutHeader.jsp">
				<jsp:param name="isLogin" value="false" />
			</jsp:include>

			<main>
				<h1>회원 수정</h1>
				<form>
					<div>
						<input type="hidden" name="id" value="${member.id}"/>
						<label for="name">이름:</label>
						<input type="text" id="name" name="name" required value="${member.name}">
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
							<c:if test="${member.gender.equals('female')}">
								<input type="radio" id="female" name="gender" value="female" checked>
								<label for="female">여성</label>
								<input type="radio" id="male" name="gender" value="male">
								<label for="male">남성</label>
							</c:if>
							<c:if test="${member.gender.equals('male')}">
								<input type="radio" id="female" name="gender" value="female">
								<label for="female">여성</label>
								<input type="radio" id="male" name="gender" value="male" checked>
								<label for="male">남성</label>
							</c:if>
					</div>
					<div>
						<label>취미:</label>
						<c:forEach var="hobby" items="${member.hobbies}">
							<c:if test="${member.memberHobbies.get(hobby.key) != null}">
								<input type="checkbox" id="hobby${hobby.key}" name="hobbies" value="${hobby.key}" checked>
								<label for="hobby${hobby.key}">${hobby.value}</label>
							</c:if>
							<c:if test="${member.memberHobbies.get(hobby.key) == null}">
								<input type="checkbox" id="hobby${hobby.key}" name="hobbies" value="${hobby.key}">
								<label for="hobby${hobby.key}">${hobby.value}</label>
							</c:if>
						</c:forEach>
					</div>
					<button type="submit">가입</button>
				</form>
			</main>
		</body>

		</html>