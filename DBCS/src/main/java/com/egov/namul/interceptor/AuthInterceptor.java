package com.egov.namul.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.egov.namul.mapper.MainMapper;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Resource(name="MainMapper")
	private MainMapper MainDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();		
		String domain = request.getServerName();
		
		if(session.getAttribute("per_seq") == null) { //로그인이 안되어있는 경우 접근 제한두기
			if(domain.equals("192.168.0.3")) response.sendRedirect("/DBCS/login");
			else if(domain.equals("namulsoft.iptime.org")) response.sendRedirect("/DBCS/login");
			else response.sendRedirect("/login");
			
			return false;
		}
		
		int auth = Integer.parseInt(session.getAttribute("ag_seq").toString()); //사용자 권한
		String path = request.getServletPath(); //호출 서비스		
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("auth", auth);
		param.put("service", path);

		int use = MainDAO.select_menu_use(param); //해당 메뉴 사용여부 가져오기	

		/* 1001=메뉴 사용, 2001=메뉴 미사용, 3001=서비스 호출 */
		if(use == 1001){ 
			Map<String, Object> menu = MainDAO.select_auth(param); //해당 메뉴 권한 정보 가져오기		
			for(String key : menu.keySet()) session.setAttribute(key, menu.get(key));			
		}else if(use == 2001)	{ //메뉴 사용을 하지않는 경우 접근 막기 (인덱스로 강제 이동)
			if(domain.equals("192.168.0.3")) response.sendRedirect("/DBCS/home");
			else if(domain.equals("namulsoft.iptime.org")) response.sendRedirect("/DBCS/home");
			else response.sendRedirect("/home");
			
			return false; 
		}
		
		return super.preHandle(request, response, handler);
	}
}
