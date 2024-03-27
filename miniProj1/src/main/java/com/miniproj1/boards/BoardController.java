package com.miniproj1.boards;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardController {
	BoardService boardService = new BoardService();

	public String list(HttpServletRequest request, HttpServletResponse response) {
		List<BoardVO> list = boardService.list();
		request.setAttribute("list", list);
		return "boardList";
	}

}
