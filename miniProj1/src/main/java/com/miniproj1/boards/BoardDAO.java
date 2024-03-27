package com.miniproj1.boards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miniproj1.members.MemberVO;

public class BoardDAO {
	private static Connection conn = null;

	private static PreparedStatement listPs = null;
	private static PreparedStatement viewPs = null;
	private static PreparedStatement deletePs = null;
	private static PreparedStatement updatePs = null;
	private static PreparedStatement insertPs = null;
	
	private static String listSQL = "select bno, title, author, content, created_at,view_count from tb_boards where title like ?";
	private static String viewSQL = "select bno, title, author, content, created_at,view_count from tb_boards where bno = ?";
	private static String deleteSQL = "delete from tb_members where id = ?";
	private static String updateSQL = "update tb_members set name = ?, password = ?, address = ?, phone = ? where id = ?";
	private static String insertSQL = "insert into tb_members (id, name, password, address, phone, gender) values(?,?,?,?,?,?)";
	
	static {
		try {
			// 1. 클래스를 로드
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 데이터베이스와 연결
			conn = DriverManager.getConnection( //
					"jdbc:mariadb://localhost:3306/miniproj_db", // 
					"bituser", "1004");
			
			System.out.println("MariaDB 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

}
