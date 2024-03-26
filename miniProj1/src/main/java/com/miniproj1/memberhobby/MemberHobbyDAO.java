package com.miniproj1.memberhobby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberHobbyDAO {
	private static Connection conn = null;
	
	private static PreparedStatement deleteAllPs = null;
	private static PreparedStatement insertPs = null;
	
	private static String deleteAllSQL = "delete from tb_member_hobbies where member_id = ?";
	private static String insertSQL= "insert into tb_member_hobbies (member_id, hobby_id) values (?,?)";
	
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

	public int deleteAll(String id) {
		int updated = 0;
		try {
			deleteAllPs = conn.prepareStatement(deleteAllSQL);
			deleteAllPs.setString(1, id);
			updated = deleteAllPs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}

	public int insert(String memberId, Integer hobbyId) {
		int updated = 0;
		try {
			insertPs = conn.prepareStatement(insertSQL);
			insertPs.setString(1, memberId);
			insertPs.setInt(2, hobbyId);
			updated = insertPs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
}
