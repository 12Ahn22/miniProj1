package com.miniproj1.members;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	// 공유해 사용하기 위해 static으로 설정
	private static Connection conn = null;
	
	private static PreparedStatement listPs = null;
	
	private static String listSQL = "select id, name, address, phone, gender from tb_members";

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

	public List<MemberVO> list() {
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>();
		
		try {
			listPs = conn.prepareStatement(listSQL);
			rs = listPs.executeQuery();
			
			while(rs.next()) {
				list.add(new MemberVO(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("address"),
							rs.getString("phone"),
							rs.getString("gender")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
