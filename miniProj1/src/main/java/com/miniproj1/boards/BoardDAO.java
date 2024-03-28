package com.miniproj1.boards;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDAO;

public class BoardDAO extends BaseDAO {
	
	private static PreparedStatement listPs = null;
	private static PreparedStatement viewPs = null;
	private static PreparedStatement increaseViewCountPs = null;
	private static PreparedStatement deletePs = null;
	private static PreparedStatement updatePs = null;
	private static PreparedStatement insertPs = null;
	
	private static String listSQL = "select bno, title, author, content, created_at,view_count from tb_boards where title like ?";
	private static String viewSQL = "select bno, title, author, content, created_at,view_count from tb_boards where bno = ?";
	private static String increaseViewCountSQL = "update tb_boards set view_count = view_count + 1 where bno = ?";
	private static String deleteSQL = "delete from tb_boards where bno = ?";
	private static String updateSQL = "update tb_boards set title = ?, content = ? where bno = ?";
	private static String insertSQL = "insert into tb_boards (title, content, author) values(?,?,?)";
	
	
	public List<BoardVO> list(String searchKey) {
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<>();
		
		try {
			listPs = conn.prepareStatement(listSQL);
			if(searchKey == null) {
				listPs.setString(1, "%");
			} else listPs.setString(1, "%" + searchKey + "%");
			rs = listPs.executeQuery();
			
			while(rs.next()) {
				list.add(new BoardVO(
							rs.getInt("bno"),
							rs.getString("title"),
							rs.getString("author"),
							rs.getString("content"),
							rs.getString("created_at"),
							rs.getInt("view_count")
						));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public BoardVO view(Integer bno) {
		ResultSet rs = null;
		BoardVO board = null;
		
		try {
			viewPs = conn.prepareStatement(viewSQL);
			viewPs.setInt(1, bno);
			rs = viewPs.executeQuery();
			if(rs.next()) {
				board = new BoardVO(
							rs.getInt("bno"),
							rs.getString("title"),
							rs.getString("author"),
							rs.getString("content"),
							rs.getString("created_at"),
							rs.getInt("view_count")
						);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	public int increaseViewCount(Integer bno) {
		int updated = 0;
		try {
			increaseViewCountPs = conn.prepareStatement(increaseViewCountSQL);
			increaseViewCountPs.setInt(1, bno);
			updated = increaseViewCountPs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}

	public int delete(Integer bno) {
		int updated = 0;
		try {
			deletePs = conn.prepareStatement(deleteSQL);
			deletePs.setInt(1, bno);
			updated = deletePs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}

	public int update(BoardVO boardVO) {
		int updated = 0;
		try {
			updatePs = conn.prepareStatement(updateSQL);
			updatePs.setString(1, boardVO.getTitle());
			updatePs.setString(2, boardVO.getContent());
			updatePs.setInt(3, boardVO.getBno());
			updated = updatePs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}

	public int insert(BoardVO boardVO) {
		int updated = 0;
		try {
			insertPs = conn.prepareStatement(insertSQL);
			insertPs.setString(1, boardVO.getTitle());
			insertPs.setString(2, boardVO.getContent());
			insertPs.setString(3, boardVO.getAuthor());
			updated = insertPs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}

}
