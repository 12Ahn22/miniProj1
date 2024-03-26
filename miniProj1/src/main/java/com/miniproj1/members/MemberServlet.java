package com.miniproj1.members;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberController memberController = new MemberController();
       
    public MemberServlet() {
        super();
    }
    
    private Map<String, Object> convertMap(Map<String, String[]> map) {
		Map<String, Object> result = new HashMap<>();
		for (var entry : map.entrySet()) {
			if (entry.getValue().length == 1) {
				//문자열 1건
				// 해당 키에 해당하는 값이 단일 객체라는 의미
				result.put(entry.getKey(), entry.getValue()[0]);
			} else {
				//문자열 배열을 추가한다  
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}
	
	private void doService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 설정
		request.setCharacterEncoding("utf-8");
		// 요청 데이터와 VO객체 매핑을 위한 objectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		
		// 요청이 파라미터와 JSON 둘 로 나뉘기 때문에 분기처리 해줘야함
		String contentType = request.getContentType();
		
		// 둘 다, 넘어온 데이터를 VO와 매핑 시켜야함
		MemberVO memberVO = null;
		
		if(contentType == null || contentType.startsWith("application/x-www-form-urlencoded")) { // contentType이 null인 경우 (list, view)와 같이 페이지 이동 요청인 경우
			// 파라미터를 MemberVO와 매핑
			// request.getParameterMap()는 Map<String,String[]>를 반환하기 때문에
			// 이것을 Map<String,Object>으로 변환해줘야한다.
			memberVO = objectMapper.convertValue(convertMap(request.getParameterMap()), MemberVO.class);
		}else if(contentType.startsWith("application/json")) { // JSON으로 온 요청인 경우
			// JSON String을 MemberVO와 매핑
			memberVO = objectMapper.readValue(request.getInputStream(), MemberVO.class);
		}
		
		// 매핑한 VO를 사용해 요청을 처리
		switch (memberVO.getAction()) {
		case "list" -> {
			// 컨트롤러를 호출한다.
			memberController.list(request);
			// 포워딩
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/members/memberList.jsp");
			rd.forward(request, response);
		}
		case "insertForm" -> {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/members/memberInsert.jsp");
			rd.forward(request, response);
		}
		case "updateForm" -> {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/members/memberUpdate.jsp");
			rd.forward(request, response);
		}
		case "view" -> {
			memberController.view(request, memberVO);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/members/memberView.jsp");
			rd.forward(request, response);
		}
		case "loginForm" ->{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/members/login.jsp");
			rd.forward(request, response);
		}
		
		case "delete" -> {
			// map을 결과로 받아서 JSON으로 맵핑해 응답으로 돌려줘야한다.
			Map<String, Object> map = memberController.delete(memberVO);
			// JSON으로 반환
			response.setContentType("application/json;charset=UTF-8");
			// map을 String으로 변환
			response.getWriter().append(objectMapper.writeValueAsString(map));
		}
		default -> {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/notFound.jsp");
			rd.forward(request, response);
		}
		};
	}
}
