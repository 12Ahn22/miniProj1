package com.miniproj1.boards;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO = new BoardDAO();
	
	public List<BoardVO> list(BoardVO boardVO) {
		return boardDAO.list(boardVO.getSearchKey());
	}

	public BoardVO view(BoardVO boardVO) {
		// view 카운트를 증가
		boardDAO.increaseViewCount(boardVO.getBno());
		// board 반환
		return boardDAO.view(boardVO.getBno());
	}

}
