package com.miniproj1.boards;

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
import com.miniproj1.members.MemberVO;
import com.miniproj1.utils.CommonUtil;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

	private void doService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardController boardController = new BoardController();
		
		// 한글 설정
		request.setCharacterEncoding("utf-8");
		// 요청 데이터와 VO객체 매핑을 위한 objectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		// 요청이 파라미터와 JSON 둘 로 나뉘기 때문에 분기처리 해줘야함
		String contentType = request.getContentType();
		// 둘 다, 넘어온 데이터를 VO와 매핑 시켜야함
		BoardVO boardVO = null;

		if (contentType == null || contentType.startsWith("application/x-www-form-urlencoded")) { 
			// 파라미터를 MemberVO와 매핑
			// request.getParameterMap()는 Map<String,String[]>를 반환하기 때문에
			// 이것을 Map<String,Object>으로 변환해줘야한다.
			boardVO = objectMapper.convertValue(CommonUtil.convertMap(request.getParameterMap()), BoardVO.class);
		} else if (contentType.startsWith("application/json")) { // JSON으로 온 요청인 경우
			// JSON String을 MemberVO와 매핑
			boardVO = objectMapper.readValue(request.getInputStream(), BoardVO.class);
		}

		Object result = switch (boardVO.getAction()) {
			case "list" -> boardController.list(request, boardVO);
			case "view" -> boardController.view(request, boardVO);
			case "delete" -> boardController.delete(request, boardVO);
			case "updateForm" -> boardController.updateForm(request, boardVO);
			default -> "notFound";
		};
		
		// 응답 부분 정리
		// 1. map인 경우 2.JSP페이지인 경우
		if (result.getClass() == String.class) {
			String url = (String) result;
			// 리다이렉트
			if (url.startsWith("redirect:")) {
				response.sendRedirect(url.substring("redirect:".length()));
			} else {
				RequestDispatcher rd = null;
				// JSP 페이지를 응답으로 전달
				if (result.equals("notFound"))
					rd = request.getRequestDispatcher("/WEB-INF/jsp/" + result + ".jsp");
				else
					rd = request.getRequestDispatcher("/WEB-INF/jsp/boards/" + result + ".jsp");
				rd.forward(request, response);
			}
		} else if (result.getClass() == Map.class || result.getClass() == HashMap.class) {
			// JSON을 응답으로 전달
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(objectMapper.writeValueAsString(result));
		}
	}
}
