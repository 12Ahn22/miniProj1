package com.miniproj1.boards;

import java.util.List;

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

}
