package com.miniproj1.boards;

import java.util.List;

public class BoardService {
	BoardDAO borBoardDAO = new BoardDAO();
	
	public List<BoardVO> list(BoardVO boardVO) {
		List<BoardVO> list = borBoardDAO.list(boardVO.getSearchKey());
		return list;
	}

}
