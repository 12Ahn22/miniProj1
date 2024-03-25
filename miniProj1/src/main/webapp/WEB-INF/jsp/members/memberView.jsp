<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RATTY | 회원 상세정보</title>
</head>
<body>
	<!-- 임시로 로그인 여부 처리 -->
	<jsp:include page="../layout/layoutHeader.jsp">
		<jsp:param name="isLogin" value="false" />
	</jsp:include>
	
	<main>
		<h1>${member.id}</h1>
		<div><span>이름:</span><span>${member.name}</span></div>
		<div><span>성별:</span><span>${member.gender}</span></div>
		<div><span>전화번호:</span><span>${member.phone}</span></div>
		<div><span>주소:</span><span>${member.address}</span></div>
		<div><span>취미:</span>
			<c:forEach var="hobby" items="${member.hobbies}">
				<span>${hobby}</span>
			</c:forEach>
		</div>
	</main>
</body>
</html>