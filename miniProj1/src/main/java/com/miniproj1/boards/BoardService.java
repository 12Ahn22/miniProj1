package com.miniproj1.boards;

import java.util.List;

public class BoardService {
	BoardDAO borBoardDAO = new BoardDAO();
	
	public List<BoardVO> list() {
		List<BoardVO> list = borBoardDAO.list();
		return list;
	}

}
