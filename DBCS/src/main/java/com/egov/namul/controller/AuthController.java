package com.egov.namul.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egov.namul.service.AuthService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

	@Resource(name = "AuthService")
	private AuthService AuthService;
	
	//권한 리스트
	@RequestMapping(value="/list")
	public void list(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		param.put("multi", multi);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {				
			List<Map<String, Object>> data = AuthService.auth_list(param);	
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + data.size()	+ ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//권한 설정
	@RequestMapping(value = "/edit")
	public void edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {
			String[] seq = request.getParameterValues("seq[]");
			String[] ag_seq = request.getParameterValues("ag_seq[]");
			String[] mn_seq = request.getParameterValues("mn_seq[]");
			String[] all_read = request.getParameterValues("all_read[]");
			String[] all_edit = request.getParameterValues("all_edit[]");
			String[] org_read = request.getParameterValues("org_read[]");
			String[] org_edit = request.getParameterValues("org_edit[]");
			String[] my_read = request.getParameterValues("my_read[]");
			String[] my_create = request.getParameterValues("my_create[]");
			String[] my_edit = request.getParameterValues("my_edit[]");
			String[] auth_use = request.getParameterValues("auth_use[]");
			
			if(seq != null) {
				for(int i = 0; i < seq.length; i++) {
					multi.add("seq", seq[i]);
					multi.add("ag_seq", ag_seq[i]);
					multi.add("mn_seq", mn_seq[i]);
					multi.add("all_read", all_read[i]);
					multi.add("all_edit", all_edit[i]);
					multi.add("org_read", org_read[i]);
					multi.add("org_edit", org_edit[i]);
					multi.add("my_read", my_read[i]);
					multi.add("my_create", my_create[i]);
					multi.add("my_edit", my_edit[i]);
					multi.add("auth_use", auth_use[i]);
				}
			}			

			param.put("multi", multi);
			AuthService.auth_edit(param);
			
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
}
