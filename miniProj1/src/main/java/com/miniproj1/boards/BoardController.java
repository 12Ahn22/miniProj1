package com.miniproj1.boards;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class BoardController {
	BoardService boardService = new BoardService();

	public String list(HttpServletRequest request, BoardVO boardVO ) {
		List<BoardVO> list = boardService.list(boardVO);
		
		HttpSession session = request.getSession();
		boolean isLogin = (session.getAttribute("loginMember") != null)? true : false;
		
		request.setAttribute("list", list);
		request.setAttribute("isLogin", isLogin);
		return "boardList";
	}

}
