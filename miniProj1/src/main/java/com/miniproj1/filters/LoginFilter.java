package com.miniproj1.filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.miniproj1.members.MemberDAO;
import com.miniproj1.members.MemberVO;

@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	
	MemberDAO memberDAO = new MemberDAO();

	public LoginFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		String action = req.getParameter("action");
		String uri = req.getRequestURI();
		
		// 자동 로그인을 위한 필터
		if(loginMember == null) {
			Cookie [] cookies = req.getCookies();
			// 쿠키가 있다면
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("uuidCookie")) {
						// 쿠키로 로그인 정보를 가져오기
						MemberVO memberVO = new MemberVO();
						memberVO.setMemberUUID(cookie.getValue());
						try {
							loginMember = memberDAO.getMemberFromUUID(memberVO);
						} catch (SQLException e) {
							res.sendRedirect(req.getContextPath() + "/");
							e.printStackTrace();
							return;
						}
						
						if(loginMember != null) {
							// 세션에 추가
							session.setAttribute("loginMember", loginMember);
							session.setMaxInactiveInterval(30 * 60 * 1000);
							// 다음 일을 하러 가기
							chain.doFilter(request, response);
						}
					}
				}
			}
		}
		
		if(action != null && uri.contains("member")) {
			// 이미 로그인을 한 상태인데 다시 로그인 페이지로 접근한 경우
			if(loginMember != null && action.equals("loginForm")) {
				res.sendRedirect(req.getContextPath() + "/"); // 리다이렉트
				return;
			}
			
			// updateForm
			if(action != null && action.equals("updateForm")) {
				if(loginMember == null) {
					res.sendRedirect(req.getContextPath() + "/"); // 리다이렉트
					return;
				}
			}
		}
		
		if(action != null && uri.contains("board")) {
			// insertForm
			if(loginMember == null && action.equals("insertForm")) {
				res.sendRedirect(req.getContextPath() + "/"); // 리다이렉트
				return;
			}
			// updateForm
			if(loginMember == null && action.equals("updateForm")) {
				res.sendRedirect(req.getContextPath() + "/"); // 리다이렉트
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
