package com.yk726.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yk726.myweb.command.UserVO;

public class UserAuthHandler implements HandlerInterceptor{

	//모든페이지에 적용될 인터페이스
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println("유저 인터셉터 실행됨");
		
		HttpSession session = request.getSession();
		
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		
		if(userVO == null) { //로그인 안됐을때
			
			response.sendRedirect(request.getContextPath() + "/user/login"); //로그인페이지로
			
			return false; //컨트롤러 실행x
		} else { //로그인이 됐을때
			return true;
		}
		
	}
	
	
}
