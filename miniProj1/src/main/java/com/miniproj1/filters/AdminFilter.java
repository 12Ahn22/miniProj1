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
public class AdminFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public AdminFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// member?action=list 페이지는 관리자만 접근이 가능하다.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		String action = req.getParameter("action");
		String uri = req.getRequestURI();
		
		if(action != null && uri.contains("member")) {
			if(action.equals("list")) {
				// 로그인 여부와 관리자 여부
				if(loginMember == null || !loginMember.getId().equals("ADMIN")) {
					res.sendRedirect(req.getContextPath() + "/"); // 리다이렉트
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
