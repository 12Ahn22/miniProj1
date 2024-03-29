package com.miniproj1.members;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.miniproj1.baseDAO.BaseDAO;

public class MemberDAO extends BaseDAO{
	private static PreparedStatement listPs = null;
	private static PreparedStatement viewPs = null;
	private static PreparedStatement memberHobbiesPs = null;
	private static PreparedStatement deletePs = null;
	private static PreparedStatement updatePs = null;
	private static PreparedStatement insertPs = null;
	private static PreparedStatement authenticatePs = null;
	private static PreparedStatement updateUUIDPs = null;
	private static PreparedStatement getMemberFromUUIDPs = null;
	
	private static String listSQL = "select id, name, address, phone, gender from tb_members";
	private static String viewSQL = "select id, name, address, phone, gender from tb_members where id = ?";
	private static String memberHobbiesSQL = "select id, hobby from tb_member_hobbies tmh join tb_hobbies th on tmh.hobby_id = th.id where member_id = ?";
	private static String deleteSQL = "delete from tb_members where id = ?";
	private static String updateSQL = "update tb_members set name = ?, password = ?, address = ?, phone = ? where id = ?";
	private static String insertSQL = "insert into tb_members (id, name, password, address, phone, gender) values(?,?,?,?,?,?)";
	private static String authenticateSQL = "select id from tb_members where id=? and password = ?";
	private static String updateUUIDSQL = "update tb_members set memberUUID = ? where id = ?";
	private static String getMemberFromUUIDSQL = "select id, name, address, phone, gender from tb_members where memberUUID = ?";

	public List<MemberVO> list() throws SQLException {
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>();
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
		rs.close();
		listPs.close();
		return list;
	}

	public MemberVO view(MemberVO member) throws SQLException {
		ResultSet rs = null;
		MemberVO viewMember = null;
		viewPs = conn.prepareStatement(viewSQL);
		viewPs.setString(1, member.getId());
		rs = viewPs.executeQuery();
		
		if(rs.next()) {
			viewMember = new MemberVO(
					rs.getString("id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("phone"),
					rs.getString("gender")
				);
		}
		rs.close();
		viewPs.close();
		return viewMember;
	}
	
	public Map<Integer,String> getMemberHobbies(MemberVO member) throws SQLException {
		ResultSet rs = null;
		Map<Integer,String> map = new HashMap<>();
		
		memberHobbiesPs = conn.prepareStatement(memberHobbiesSQL);
		memberHobbiesPs.setString(1, member.getId());
		rs = memberHobbiesPs.executeQuery();
		while(rs.next()) {
			map.put(rs.getInt("id"), rs.getString("hobby"));
		}
		rs.close();
		memberHobbiesPs.close();
		return map;
	}

	public int delete(MemberVO member) throws SQLException {
		int updated = 0;
		deletePs = conn.prepareStatement(deleteSQL);
		deletePs.setString(1, member.getId());
		updated = deletePs.executeUpdate();
		deletePs.close();
		return updated;
	}

	public int update(MemberVO member) throws SQLException {
		int updated = 0;
		updatePs = conn.prepareStatement(updateSQL);
		updatePs.setString(1, member.getName());
		updatePs.setString(2, member.getPassword());
		updatePs.setString(3, member.getAddress());
		updatePs.setString(4, member.getPhone());
		updatePs.setString(5, member.getId());
		updated = updatePs.executeUpdate();
		updatePs.close();
		return updated;
	}

	public int insert(MemberVO member) throws SQLException {
		int updated = 0;
		insertPs = conn.prepareStatement(insertSQL);
		insertPs.setString(1, member.getId());
		insertPs.setString(2, member.getName());
		insertPs.setString(3, member.getPassword());
		insertPs.setString(4, member.getAddress());
		insertPs.setString(5, member.getPhone());
		insertPs.setString(6, member.getDBGender());
		updated = insertPs.executeUpdate();
		insertPs.close();
		return updated;
	}

	public boolean authenticateMember(MemberVO member) throws SQLException {
		ResultSet rs = null;
		authenticatePs = conn.prepareStatement(authenticateSQL);
		authenticatePs.setString(1, member.getId());
		authenticatePs.setString(2, member.getPassword());
		rs = authenticatePs.executeQuery();
		if(rs.next()) {
			return true;
		}
		authenticatePs.close();
		return false;
	}

	public int updateUUID(MemberVO member) throws SQLException {
		int updated = 0;
		
		updateUUIDPs = conn.prepareStatement(updateUUIDSQL);
		updateUUIDPs.setString(1, member.getMemberUUID());
		updateUUIDPs.setString(2, member.getId());
		updated =  updateUUIDPs.executeUpdate();
		updateUUIDPs.close();
		return updated;
	}

	public MemberVO getMemberFromUUID(MemberVO member) throws SQLException {
		ResultSet rs = null;
		MemberVO viewMember = null;
		getMemberFromUUIDPs = conn.prepareStatement(getMemberFromUUIDSQL);
		getMemberFromUUIDPs.setString(1, member.getMemberUUID());
		rs = getMemberFromUUIDPs.executeQuery();
		
		if(rs.next()) {
			viewMember = new MemberVO(
					rs.getString("id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("phone"),
					rs.getString("gender")
				);
		}
		rs.close();
		getMemberFromUUIDPs.close();
		return viewMember;
	}
}
