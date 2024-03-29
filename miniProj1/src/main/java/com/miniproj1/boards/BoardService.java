package com.miniproj1.boards;

import java.sql.SQLException;
import java.util.List;

import com.miniproj1.baseDAO.BaseDAO;

public class BoardService {
	BoardDAO boardDAO = new BoardDAO();
	
	public List<BoardVO> list(BoardVO boardVO) throws Exception {
		List<BoardVO> list = null;
		try {
			list = boardDAO.list(boardVO.getSearchKey());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return list;
	}

	public BoardVO view(BoardVO boardVO) {
		BoardVO board = null;
		try {
			// view 카운트를 증가
			boardDAO.increaseViewCount(boardVO.getBno());
			board = boardDAO.view(boardVO.getBno());
			BaseDAO.conn.commit();
		} catch (Exception e) {
			try {
				BaseDAO.conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		// board 반환
		return board; // 만약 얘가 없으면 어떻게 처리하지?
	}
	
	public BoardVO fetchUpdateFormData(BoardVO boardVO) {
		BoardVO board = null;
		try {
			board =  boardDAO.view(boardVO.getBno());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}

	public int delete(BoardVO boardVO) {
		try {
			boardDAO.delete(boardVO.getBno());
			BaseDAO.conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				BaseDAO.conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		}
		return 1;
	}

	public int update(BoardVO boardVO) {
		try {
			boardDAO.update(boardVO);
			BaseDAO.conn.commit();
		} catch (Exception e) {
			try {
				BaseDAO.conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		}
		return 1;
	}

	public int insert(BoardVO boardVO) {
		try {
			boardDAO.insert(boardVO);
			BaseDAO.conn.commit();
		} catch (Exception e) {
			try {
				BaseDAO.conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		}
		return 1;
	}

}
