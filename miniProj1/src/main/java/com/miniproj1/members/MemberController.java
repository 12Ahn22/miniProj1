package com.miniproj1.members;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import com.miniproj1.hobbies.HobbyVO;

public class MemberController {
	MemberService memberService = new MemberService();

	public String list(HttpServletRequest request) throws Exception {
		// 서비스를 호출한다.
		List<MemberVO> list = memberService.list();

		// JSP에서 사용할 수 있도록 setAttribute 해준다.
		request.setAttribute("list", list);
		return "memberList";
	}

	public String view(HttpServletRequest request, MemberVO member) {
		MemberVO viewMember = memberService.view(member);
		request.setAttribute("member", viewMember);
		return "memberView";
	}

	public Map<String, Object> delete(HttpServletRequest request, MemberVO member) {
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

		// 로그인 하지않았다면
		if (loginMember == null) {
			map.put("status", 404);
			map.put("statusMessage", "로그인을 해야합니다.");
			return map;
		}

		// 현재 세션의 유저와 입력된 정보의 id가 같지 않다면 수정 불가
		if (!loginMember.getId().equals("ADMIN")) { // 어드민이 아닐 때만 체크
			if (!loginMember.getId().equals(member.getId())) { // 로그인 유저와 요청 유저가 다르다면
				// 실패
				map.put("status", 404);
				map.put("statusMessage", "잘못된 요청입니다.");
				return map;
			}
		}

		int updated = memberService.delete(member);
		if (updated == 1) { // 성공
			map.put("status", 204);
			// 관리자가 아니라면 세션 정보를 삭제
			if(!loginMember.getId().equals("ADMIN")) {
				session.removeAttribute("loginMember");
				session.invalidate();
			}
		} else {
			map.put("status", 404);
			map.put("statusMessage", "회원 정보 삭제 실패하였습니다");
		}

		return map;
	}

	public Map<String, Object> update(HttpServletRequest request, MemberVO member) {
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		// 로그인 하지않았다면
		if (loginMember == null) {
			map.put("status", 404);
			map.put("statusMessage", "로그인을 해야합니다.");
			return map;
		}

		// 현재 세션의 유저와 입력된 정보의 id가 같지 않다면 수정 불가
		if (!loginMember.getId().equals("ADMIN")) { // 어드민이 아닐 때만 체크
			if (!loginMember.getId().equals(member.getId())) { // 로그인 유저와 요청 유저가 다르다면
				// 실패
				map.put("status", 404);
				map.put("statusMessage", "잘못된 요청입니다.");
				return map;
			}
		}

		int updated = memberService.update(member);
		if (updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "회원 정보 수정 실패하였습니다");
		}
		return map;
	}

	public String fetchUpdateFormData(HttpServletRequest request, MemberVO member) {
		Map<String, Object> map = memberService.fetchUpdateFormData(member);
		request.setAttribute("member", map.get("memberVO"));
		request.setAttribute("hobbyList", map.get("hobbyList"));
		return "memberUpdate";
	}

	public String fetchInsertFormData(HttpServletRequest request) {
		List<HobbyVO> hobbyList = memberService.fetchInsertFormData();
		request.setAttribute("hobbyList", hobbyList);
		return "memberInsert";
	}

	public Map<String, Object> insert(HttpServletRequest request, MemberVO member) {
		Map<String, Object> map = new HashMap<>();
		int updated = memberService.insert(member);
		if (updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "회원 가입에 실패하였습니다");
		}
		return map;
	}

	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, MemberVO member) {
		Map<String, Object> map = new HashMap<>();

		// 성공
		if (memberService.authenticateMember(member)) {
			HttpSession session = request.getSession();
			
			MemberVO viewMember = memberService.view(member);
			if(viewMember == null) {
				map.put("status", 500);
				map.put("statusMessage", "로그인에 실패했습니다.");
				return map;
			}
			
			// 자동 로그인 체크를 한 경우, 쿠키를 저장해 전달.
			if(member.getAutoLogin() != null && member.getAutoLogin().equals("Y")) { // 자동 로그인 체크를 한 요청인 경우
				// UUID 생성
				String uuid = UUID.randomUUID().toString();
				member.setMemberUUID(uuid);
				
				// 해당 UUID를 유저 데이터에 저장
				if(memberService.updateUUID(member) == 0) {
					map.put("status", 404);
					map.put("statusMessage", "자동 로그인 설정에 실패했습니다.");
					return map;
				}
				
				// 해당 UUID 값을 쿠키로 반환
				Cookie uuidCookie = new Cookie("uuidCookie", uuid);
				uuidCookie.setMaxAge(24 * 60 * 60); //24시간
				uuidCookie.setPath("/");

				response.addCookie(uuidCookie);
			}
			
			session.setAttribute("loginMember", viewMember);
			session.setMaxInactiveInterval(30 * 60 * 1000); // 30분
			map.put("status", 204);
		} else {
			// 실패
			map.put("status", 404);
			map.put("statusMessage", "아이디 혹은 비밀번호가 잘못되었습니다.");
		}
		return map;
	}

	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 세션에서 로그인 정보 얻기
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		loginMember.setMemberUUID("");
		memberService.updateUUID(loginMember);
		// 세션 삭제
		session.removeAttribute("loginMember");
		session.invalidate();

		// main 화면으로 리다이렉트 하도록 응답
		return "redirect:/";
	}

	public String profile(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO viewMember = (MemberVO) session.getAttribute("loginMember");
		request.setAttribute("member", viewMember);
		return "memberProfile";
	}
}
