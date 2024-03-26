package com.miniproj1.members;

import java.io.IOException;
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
		// 매핑
		ObjectMapper objectMapper = new ObjectMapper();

		String action = request.getParameter("action");

		switch (action) {
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
			String id = request.getParameter("id");
			memberController.view(request, id);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/members/memberView.jsp");
			rd.forward(request, response);
		}
		case "loginForm" ->{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/members/login.jsp");
			rd.forward(request, response);
		}
		
		case "delete" -> {
			// map을 결과로 받아서 JSON으로 맵핑해 응답으로 돌려줘야한다.
			String id = request.getParameter("id");
			Map<String, Object> map = memberController.delete(id);
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
