package com.miniproj1.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.miniproj1.members.MemberVO;

@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

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
		
		if(action != null && uri.contains("member")) {
			// 이미 로그인을 한 상태인데 다시 로그인 페이지로 접근한 경우
			if(loginMember != null && action.equals("loginForm")) {
				res.sendRedirect(req.getContextPath() + "/"); // 리다이렉트
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
