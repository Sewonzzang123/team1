package com.my.maintest.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.maintest.member.vo.MemberVO;

public class AdminAuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AdminAuthenticationInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 요청URL분석
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String reqURI = uri.substring(contextPath.length());

		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		if (memberVO != null) {
			logger.info(memberVO.getUcode());
			if (memberVO.getUcode().equals("1")) {

				return true;
			}
		}

		response.sendRedirect(request.getContextPath() + "/unauthorizedAccess");
		return false;
	}
}
