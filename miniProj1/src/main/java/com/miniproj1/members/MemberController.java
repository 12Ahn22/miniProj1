package com.miniproj1.members;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class MemberController {
	MemberService memberService = new MemberService();
	
	public List<MemberVO> list(HttpServletRequest request){
		// 서비스를 호출한다.
		List<MemberVO> list = memberService.list();
		
		// JSP에서 사용할 수 있도록 setAttribute 해준다.
		request.setAttribute("list", list);
		return list;
	}

	public void view(HttpServletRequest request, MemberVO member) {
		MemberVO viewMember = memberService.view(member);
		request.setAttribute("member", viewMember);
	}

	public Map<String, Object> delete(MemberVO member) {
		Map<String, Object> map = new HashMap<>();
		
		int updated = memberService.delete(member);
		if(updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "회원 정보 삭제 실패하였습니다");
		}
		return map;
	}

	public Map<String, Object> update(HttpServletRequest request, MemberVO member) {
		Map<String, Object> map = new HashMap<>();
		
		int updated = memberService.update(member);
		if(updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "회원 정보 수정 실패하였습니다");
		}
		return map;
	}
}
