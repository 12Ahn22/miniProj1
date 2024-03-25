package com.miniproj1.members;

import java.util.List;

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

	public void view(HttpServletRequest request, String id) {
		MemberVO memberVO = memberService.view(id);
		request.setAttribute("member", memberVO);
	}
}
