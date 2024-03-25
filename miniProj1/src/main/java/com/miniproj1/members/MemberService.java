package com.miniproj1.members;

import java.util.List;

public class MemberService {
	MemberDAO memberDAO = new MemberDAO();
	
	public List<MemberVO> list(){
		// DAO를 호출한다.
		List<MemberVO> list = memberDAO.list();
		return list;
	}

	public MemberVO view(String id) {
		MemberVO memberVO = memberDAO.view(id);
		List<String> hobbies = memberDAO.getMemberHobbies(id);
		memberVO.setHobbies(hobbies);
		return memberVO;
	}
}
