package com.miniproj1.members;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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

		String action = request.getParameter("action");

		switch (action) {
		case "list" -> {
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
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/members/memberView.jsp");
			rd.forward(request, response);
		}
		case "loginForm" ->{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/members/login.jsp");
			rd.forward(request, response);
		}
		default -> {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/notFound.jsp");
			rd.forward(request, response);
		}
		};
	}
}
