package com.miniproj1.memberhobby;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.miniproj1.baseDAO.BaseDAO;

public class MemberHobbyDAO extends BaseDAO{
	private static PreparedStatement deleteAllPs = null;
	private static PreparedStatement insertPs = null;
	
	private static String deleteAllSQL = "delete from tb_member_hobbies where member_id = ?";
	private static String insertSQL= "insert into tb_member_hobbies (member_id, hobby_id) values (?,?)";

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
