package com.miniproj1.members;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miniproj1.hobbies.HobbyDAO;
import com.miniproj1.hobbies.HobbyVO;

public class MemberService {
	MemberDAO memberDAO = new MemberDAO(); // 유저 정보
	HobbyDAO hobbyDAO = new HobbyDAO(); // 취미
	
	public List<MemberVO> list(){
		// DAO를 호출한다.
		List<MemberVO> list = memberDAO.list();
		return list;
	}

	public MemberVO view(MemberVO member) {
		MemberVO memberVO = memberDAO.view(member);
		Map<Integer,String> hobbies = memberDAO.getMemberHobbies(member);
		if(hobbies.size() != 0) memberVO.setHobbies(hobbies);
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

	public Map<String,Object> fetchUpdateFormData(MemberVO member) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		MemberVO memberVO = memberDAO.view(member);
		Map<Integer,String> memberHobbies = memberDAO.getMemberHobbies(member); // 유저가 선택한 취미 목록
		memberVO.setHobbies(memberHobbies);
		
		// Service는 여러 DAO를 가질 수 있음
		List<HobbyVO> hobbyList = hobbyDAO.list();
		
		map.put("memberVO", memberVO);
		map.put("hobbyList", hobbyList);
		
		return map;
	}
}
