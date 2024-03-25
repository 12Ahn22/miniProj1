package com.miniproj1.boards;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// 한글 설정
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");

		switch (action) {
		case "list" -> {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/boards/boardList.jsp");
			rd.forward(request, response);
		}
		case "insertForm" -> {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/boards/boardInsert.jsp");
			rd.forward(request, response);
		}
		case "updateForm" -> {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/boards/boardUpdate.jsp");
			rd.forward(request, response);
		}
		case "view" -> {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/boards/boardView.jsp");
			rd.forward(request, response);
		}
		};
	}
}
