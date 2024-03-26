package com.miniproj1.hobbies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HobbyDAO {
	private static Connection conn = null;

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

	private static PreparedStatement listPs = null;

	private static String listSQL = "select id, hobby from tb_hobbies";

	public List<HobbyVO> list() {
		List<HobbyVO> list = new ArrayList<>();
		try {
			listPs = conn.prepareStatement(listSQL);
			ResultSet rs = listPs.executeQuery();
			
			while(rs.next()) {
				list.add(new HobbyVO(rs.getInt("id"),rs.getString("hobby")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
