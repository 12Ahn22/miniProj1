package com.miniproj1.members;

import java.util.List;
import java.util.Map;

public class MemberService {
	MemberDAO memberDAO = new MemberDAO();
	
	public List<MemberVO> list(){
		// DAO를 호출한다.
		List<MemberVO> list = memberDAO.list();
		return list;
	}

	public MemberVO view(MemberVO member) {
		MemberVO memberVO = memberDAO.view(member);
		Map<Integer,String> hobbies = memberDAO.getMemberHobbies(member);
		if(hobbies.size() != 0) memberVO.setMemberHobbies(hobbies);
		return memberVO;
	}

	public int delete(MemberVO member) {
		int updated = memberDAO.delete(member);
		return updated;
	}

	public int update(MemberVO member) {
		int updated = memberDAO.update(member);
		return updated;
	}
}
