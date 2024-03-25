<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>RATTY | 당신의 작은 반려동물</title>
</head>
<body>
	<!-- 임시로 로그인 여부 처리 -->
	<jsp:include page="WEB-INF/jsp/layout/layoutHeader.jsp">
		<jsp:param name="isLogin" value="false" />
	</jsp:include>
</body>
</html>