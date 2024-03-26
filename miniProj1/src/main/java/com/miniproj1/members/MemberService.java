package com.miniproj1.members;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miniproj1.hobbies.HobbyDAO;
import com.miniproj1.hobbies.HobbyVO;
import com.miniproj1.memberhobby.MemberHobbyDAO;

public class MemberService {
	MemberDAO memberDAO = new MemberDAO(); // 유저 정보
	HobbyDAO hobbyDAO = new HobbyDAO(); // 취미
	MemberHobbyDAO memberHobbyDAO = new MemberHobbyDAO(); // 멤버-취미 관계
	
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

	// 나중에 트랜잭션 처리를 해야하는 걸까..?
	public int update(MemberVO member) {
		// 멤버-취미 테이블에서 해당 멤버를 전부 삭제
		memberHobbyDAO.deleteAll(member.getId());
		
		// 받은 취미를 전부 insert
		Map<Integer, String> hobbies = member.getHobbies();
		for(var hobby: hobbies.entrySet()) {
			// 실패 처리를 어떻게 해야할까?
			memberHobbyDAO.insert(member.getId(), hobby.getKey());
		}
		// 멤버 수정 사항 업데이트
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

	public List<HobbyVO> fetchInsertFormData() {
		return hobbyDAO.list();
	}

	public int insert(MemberVO member) {
		// 멤버를 먼저 생성
		int updated = memberDAO.insert(member);
		
		// 취미-멤버 테이블에 데이터 생성
		Map<Integer, String> hobbies = member.getHobbies();
		for(var hobby: hobbies.entrySet()) {
			memberHobbyDAO.insert(member.getId(), hobby.getKey());
		}
		return updated;
	}

	public boolean authenticateMember(MemberVO member) {
		return memberDAO.authenticateMember(member);		
	}
}
