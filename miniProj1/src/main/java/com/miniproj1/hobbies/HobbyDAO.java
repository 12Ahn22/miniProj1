package com.miniproj1.hobbies;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDAO;

public class HobbyDAO extends BaseDAO {
	
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
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
