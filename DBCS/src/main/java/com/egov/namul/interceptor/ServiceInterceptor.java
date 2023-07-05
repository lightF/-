package com.egov.namul.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.egov.namul.service.AuthService;

public class ServiceInterceptor extends HandlerInterceptorAdapter {

	@Resource(name = "AuthService")
	private AuthService AuthService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String path = request.getServletPath();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter jsonOut = response.getWriter();

		if(path.contains("/edit")) { //생성&편집 권한
			if(request.getParameter("seq") == null || "".equals(request.getParameter("seq"))) { //생성
				if(Integer.parseInt(session.getAttribute("create_type").toString()) == 2) {
					jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Create Access Denied\"}");
					jsonOut.flush();
					jsonOut.close();				
					return false;
				}
			}else { //편집
				if(Integer.parseInt(session.getAttribute("edit_type").toString()) == 4) {
					jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Edit Access Denied\"}");
					jsonOut.flush();
					jsonOut.close();				
					return false;
				}else {
					if(path.contains("/item")){ //항목 리스트
						int auth = Integer.parseInt(session.getAttribute("per_organize").toString());
						
						if(auth > 1) {
							jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Edit Access Denied\"}");
							jsonOut.flush();
							jsonOut.close();				
							return false;
						}
					}else if (path.contains("/auth")){
						int auth = Integer.parseInt(session.getAttribute("per_organize").toString());
						
						if(auth > 1) {
							jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Edit Access Denied\"}");
							jsonOut.flush();
							jsonOut.close();				
							return false;
						}
					}else {
						int auth_type = Integer.parseInt(session.getAttribute("edit_type").toString());
						
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("path", path);
						param.put("seq", request.getParameter("seq"));
						
						if(auth_type == 2) {
							param.put("auth", 1);
							param.put("auth_seq", session.getAttribute("per_organize"));
						}
						else if(auth_type == 3) {
							param.put("auth", 2);
							param.put("auth_seq", session.getAttribute("per_seq"));
						}
						
						int auth_cnt = AuthService.select_auth_edit(param);
						
						if(auth_cnt == 0) {
							jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Edit Access Denied\"}");
							jsonOut.flush();
							jsonOut.close();				
							return false;
						}
					}				
				}
			}
		}else if(path.contains("/delete")) { //편집 권한
			if(Integer.parseInt(session.getAttribute("edit_type").toString()) == 4) {
				jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Edit Access Denied\"}");
				jsonOut.flush();
				jsonOut.close();				
				return false;
			}else {
				if(path.contains("/item")){ //항목 리스트
					int auth = Integer.parseInt(session.getAttribute("per_organize").toString());
					
					if(auth > 1) {
						jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Edit Access Denied\"}");
						jsonOut.flush();
						jsonOut.close();				
						return false;
					}
				}else if (path.contains("/auth")){
					int auth = Integer.parseInt(session.getAttribute("per_organize").toString());
					
					if(auth > 1) {
						jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Edit Access Denied\"}");
						jsonOut.flush();
						jsonOut.close();				
						return false;
					}
				}else {
					int auth_type = Integer.parseInt(session.getAttribute("edit_type").toString());
					
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("path", path);
					param.put("seq", request.getParameter("seq"));
					
					if(auth_type == 2) {
						param.put("auth", 1);
						param.put("auth_seq", session.getAttribute("per_organize"));
					}
					else if(auth_type == 3) {
						param.put("auth", 2);
						param.put("auth_seq", session.getAttribute("per_seq"));
					}
					
					int auth_cnt = AuthService.select_auth_edit(param);
					
					if(auth_cnt == 0) {
						jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Edit Access Denied\"}");
						jsonOut.flush();
						jsonOut.close();				
						return false;
					}
				}
			}
		}else { //읽기 권한
			if(path.contains("/item")){ //항목 리스트
				if(session.getAttribute("per_seq") == null) {
					jsonOut.write("{\"result\":[]" + ",\"code\":3, \"msg\":\"Permission Denied\"}");
					jsonOut.flush();
					jsonOut.close();				
					return false;
				}else return true;
			}
			
			int auth_type = Integer.parseInt(session.getAttribute("read_type").toString());

			if(auth_type == 2) {
				//직원관리
				if(path.contains("/user")) {
					request.setAttribute("auth", 2);
					request.setAttribute("auth_seq", session.getAttribute("per_seq"));
				}else {
					request.setAttribute("auth", 1);
					request.setAttribute("auth_seq", session.getAttribute("per_organize"));					
				}
			}
			else if(auth_type == 3) {
				request.setAttribute("auth", 2);
				request.setAttribute("auth_seq", session.getAttribute("per_seq"));
			}

			if(request.getParameter("section") != null && !"".equals(request.getParameter("section"))) {
				int auth_seq = Integer.parseInt(session.getAttribute("ag_seq").toString());
				
				if(path.contains("/car")) {
					if(auth_seq > 1) {
						request.setAttribute("auth", 3);
						request.setAttribute("auth_seq", session.getAttribute("per_seq"));
					}	
				}else {
					if(auth_seq > 1) {
						request.setAttribute("auth", 3);
						request.setAttribute("auth_seq", session.getAttribute("per_organize"));
					}
				}							
			}
			
			if(Integer.parseInt(session.getAttribute("read_type").toString()) == 4) {				
				if(request.getParameter("row") != null) {
					jsonOut.write("{\"result\":[]" 
							+ ",\"total\":" + 0
							+ ",\"row\":" + Integer.parseInt(request.getParameter("row").toString())  
							+ ",\"page\":" + Integer.parseInt(request.getParameter("page").toString())
							+ ",\"code\":1, \"msg\":\"Read Access Denied\"}");
				}else {
					jsonOut.write("{\"result\":[]" + ",\"total\":" + 0 + ",\"code\":3, \"msg\":\"Read Access Denied\"}");
				}
				jsonOut.flush();
				jsonOut.close();
				return false;
			}
		}
		
		return true;
	}
}
