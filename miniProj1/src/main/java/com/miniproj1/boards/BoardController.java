package com.miniproj1.boards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.miniproj1.members.MemberVO;


public class BoardController {
	BoardService boardService = new BoardService();

	public String list(HttpServletRequest request, BoardVO boardVO ) {
		List<BoardVO> list = boardService.list(boardVO);
		
		HttpSession session = request.getSession();
		boolean isLogin = (session.getAttribute("loginMember") != null)? true : false;
		
		request.setAttribute("list", list);
		request.setAttribute("isLogin", isLogin);
		return "boardList";
	}

	public String view(HttpServletRequest request, BoardVO boardVO) {
		// 게시글 정보
		BoardVO board = boardService.view(boardVO);
		request.setAttribute("board", board);
		
		// 수정 가능 여부
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if(loginMember != null) { // 로그인 중인 경우
			if(loginMember.getId().equals(board.getAuthor()) || loginMember.getId().equals("ADMIN")) { // 내가 쓴 글 혹은 어드민
				request.setAttribute("isLogin", true);
			}
		}
		return "boardView";
	}

	public Map<String,Object> delete(HttpServletRequest request, BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); // 로그인 한 유저
		
		// 현재 세션의 유저와 입력된 정보의 id가 같지 않다면 수정 불가
		if(!loginMember.getId().equals("ADMIN")) { // 어드민이 아닐 때만 체크
			if(!loginMember.getId().equals(boardVO.getAuthor())) { // 로그인 유저와 요청 유저가 다르다면
				// 실패
				map.put("status", 404);
				map.put("statusMessage", "잘못된 요청입니다.");
				return map;
			}
		}
		
		int updated = boardService.delete(boardVO);
		
		if(updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "게시글 삭제에 실패하였습니다");
		}
		
		return map;
	}

}
