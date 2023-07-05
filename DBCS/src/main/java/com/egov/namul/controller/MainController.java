package com.egov.namul.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.egov.namul.service.MainService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class MainController {
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name = "SSHProperty")
	Properties SSHProperty;
	
	@Resource(name = "MainService")
	private MainService MainService;
	
	@RequestMapping(value = "home")
	public String Home(HttpSession session, HttpServletRequest request){
		String resPath = "redirect:/login";
		if(null != session.getAttribute("per_seq") && !"".equals(session.getAttribute("per_seq"))) {
			Device device = DeviceUtils.getCurrentDevice(request);
			
			if (device.isNormal())	resPath = "redirect:/index";
			else resPath = "redirect:/notice";
		}
		return resPath;
	}
	
	@RequestMapping(value = "logout") //로그아웃
	public String logout(HttpSession session){ 
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping(value="{page}")
	public String page(@PathVariable("page") String page, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		// 페이지 이동 시 마다 세션 검사
		HttpSession session = request.getSession();
		long refresh_time = session.getLastAccessedTime(); // 세션 마지막 요청 시간
		long now_time = System.currentTimeMillis(); // 현재 시간
		long diff_time = (now_time - refresh_time) / 1000;	
		
		String resPath = "login";
		if(session.getAttribute("per_seq") != null && diff_time < session.getMaxInactiveInterval()) {
			Device device = DeviceUtils.getCurrentDevice(request);

			if (device.isNormal())	resPath = page;
			else resPath = "mobile/"+page;
		}
		
		return resPath;
	}
	
	@RequestMapping(value="{path}/{page}")
	public String doublepage(@PathVariable("path") String path, @PathVariable("page") String page, HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		// 페이지 이동 시 마다 세션 검사
		HttpSession session = request.getSession();
		long refresh_time = session.getLastAccessedTime(); // 세션 마지막 요청 시간
		long now_time = System.currentTimeMillis(); // 현재 시간
		long diff_time = (now_time - refresh_time) / 1000;
		
		String resPath = "login";
		if(null != session.getAttribute("per_seq") && diff_time < session.getMaxInactiveInterval()) {
			Device device = DeviceUtils.getCurrentDevice(request);

			if (device.isNormal())	resPath = path+"/"+page;
			else resPath = "mobile/"+path+"/"+page;			
		}
		
		return resPath;
	}
	
	//메뉴
	@RequestMapping(value="/menu")
	public void menu(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int auth = Integer.parseInt(session.getAttribute("ag_seq").toString());
			param.put("auth", auth);
			
			List<Map<String, Object>> data = MainService.menu(param);
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"size\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"size\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//즐겨찾기 리스트
	@RequestMapping(value="/bookmark/list")
	public void bookmark_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int per = Integer.parseInt(session.getAttribute("per_seq").toString());
			param.put("per_seq", per);					
			
			List<Map<String, Object>> data = MainService.register_chart(param);
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"size\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"size\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//즐겨찾기 추가
	@RequestMapping(value="/bookmark/edit")
	public void bookmark_edit(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {	
			int per = Integer.parseInt(session.getAttribute("per_seq").toString());
			param.put("per_seq", per);	
			
			MainService.bookmark_edit(param);
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//즐겨찾기 삭제
	@RequestMapping(value="/bookmark/delete")
	public void bookmark_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {	
			int per = Integer.parseInt(session.getAttribute("per_seq").toString());
			param.put("per_seq", per);	
			
			MainService.bookmark_delete(param);
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//등록 건수 통계
	@RequestMapping(value="/register/chart")
	public void register(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int auth = Integer.parseInt(session.getAttribute("ag_seq").toString());
			
			if(auth > 1) {
				int org = Integer.parseInt(session.getAttribute("per_organize").toString());
				param.put("og_seq", org);
			}			
			
			List<Map<String, Object>> data = MainService.register_chart(param);
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"size\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"size\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//주간 신고지수
	@RequestMapping(value="/report/chart")
	public void report(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int auth = Integer.parseInt(session.getAttribute("ag_seq").toString());
			
			if(auth > 1) {
				int org = Integer.parseInt(session.getAttribute("per_organize").toString());
				param.put("og_seq", org);
			}
	
			List<Map<String, Object>> chart1 = MainService.breakdown_chart(param);
			List<Map<String, Object>> chart2 = MainService.action_chart(param);
			
			jsonOut.write("{\"result\":["+JsonUtil.getJsonArrayFromList(chart1)  
			+ "," + JsonUtil.getJsonArrayFromList(chart2) + "]"
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//근무일정
	@RequestMapping(value="/days/list")
	public void days(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int per = Integer.parseInt(session.getAttribute("per_seq").toString());
			param.put("per_seq", per);
			
			List<Map<String, Object>> data = MainService.work(param);
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"size\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"size\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//근무자 통계
	@RequestMapping(value="/worker/list")
	public void worker(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int auth = Integer.parseInt(session.getAttribute("ag_seq").toString());
			
			if(auth > 1) {
				int org = Integer.parseInt(session.getAttribute("per_organize").toString());
				param.put("og_seq", org);
			}
			
			List<Map<String, Object>> data = MainService.worker_chart(param);
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"size\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"size\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
}
