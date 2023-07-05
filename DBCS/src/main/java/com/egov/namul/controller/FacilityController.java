package com.egov.namul.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.egov.namul.service.FacilityService;
import com.egov.namul.service.FileService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/facility")
public class FacilityController {
	
	@Resource(name = "FacilityService")
	private FacilityService FacilityService;
	
	@Resource(name = "FileService")
	private FileService FileService;
	
	//기기관리 리스트
	@RequestMapping(value="/device/list")
	public void device_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			if(param.get("search_column") != null && !"".equals(param.get("search_column"))) param.put(param.get("search_column").toString(), param.get("search_word"));
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page"))) page = Integer.parseInt(param.get("page").toString());
			if(param.get("row") != null && !"".equals(param.get("row"))) row = Integer.parseInt(param.get("row").toString());	
				
			int total = FacilityService.device_total(param);	
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = FacilityService.device_list(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total	+ ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + total	+ ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/device/edit")
	public void device_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] dh_seq = request.getParameterValues("dh_seq[]");
			String[] dh_date = request.getParameterValues("dh_date[]");
			String[] dh_location = request.getParameterValues("dh_location[]");
			String[] dh_install = request.getParameterValues("dh_install[]");
			String[] dh_division = request.getParameterValues("dh_division[]");
			String[] dh_approve = request.getParameterValues("dh_approve[]");
			String[] dh_man = request.getParameterValues("dh_man[]");
			String[] dh_team = request.getParameterValues("dh_team[]");
			String[] dh_state = request.getParameterValues("dh_state[]");
			String[] dh_case = request.getParameterValues("dh_case[]");
			
			multi.clear();
			
			if(dh_seq != null) {
				for(int i = 0; i < dh_seq.length; i++) {
					int cnt = 0;

					if(dh_date[i].isEmpty()) cnt++;
					if(dh_location[i].isEmpty()) cnt++;
					if(dh_install[i].isEmpty()) cnt++;
					if(dh_division[i].isEmpty()) cnt++;
					if(dh_approve[i].isEmpty()) cnt++;
					if(dh_man[i].isEmpty()) cnt++;
					if(dh_team[i].isEmpty()) cnt++;
					if(dh_state[i].isEmpty()) cnt++;
					if(dh_case[i].isEmpty()) cnt++;
					
					if(cnt < 9) {
						if("".equals(dh_seq[i])) multi.add("dh_seq", 0);
						else multi.add("dh_seq", dh_seq[i]);
						
						if("".equals(dh_date[i])) multi.add("dh_date", 0);
						else multi.add("dh_date", dh_date[i]);

						multi.add("dh_location", dh_location[i]);
						multi.add("dh_install", dh_install[i]);
						
						if("".equals(dh_division[i]))	multi.add("dh_division", 1);
						else multi.add("dh_division", dh_division[i]);
						
						if("".equals(dh_approve[i]))	multi.add("dh_approve", 1);
						else multi.add("dh_approve", dh_approve[i]);
						
						multi.add("dh_man", dh_man[i]);
						multi.add("dh_team", dh_team[i]);
						multi.add("dh_state", dh_state[i]);
						multi.add("dh_case", dh_case[i]);
					}					
				}				
						
				if(multi.get("dh_seq") != null) param.put("dh_seq", 1);	
			}			
			
			param.put("multi", multi);
			FacilityService.device_edit(param);		
			
			if(StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multiFiles = multiRequest.getFiles("upload[]");	 			
				
				String[] fileSeq = request.getParameterValues("f_seq[]");
				String[] fileDel = request.getParameterValues("f_del[]");
				param.put("tb_name", "device");
				if(fileDel != null) FileService.multi_resize_upload(param, multiFiles, "device_file/", fileSeq, fileDel, 250, 200);
			}			
			
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/device/detail")
	public void device_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {		
			List<Map<String, Object>> data1 = FacilityService.device(param);
			List<Map<String, Object>> data2 = FacilityService.device_history(param);			
			param.put("tb_name", "device"); //파일 가져오기
			List<Map<String, Object>> data3 = FileService.file(param);				
			
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);		
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data2)
			+ ",\"result3\":" + JsonUtil.getJsonArrayFromList(data3)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"size3\":" + data3.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/device/delete")
	public void device_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("tb_name", "device");
			int result = FacilityService.device_delete(param);
			FileService.delete(param, "device_file/");
			
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Delete Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/standard1/list")
	public void standard1_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			param.put("tb_name", "device_standard1");
			
			List<Map<String, Object>> seq = FacilityService.standard_parent(param);
			
			if(seq.size() > 0) {
				Set<Integer> set = new HashSet<Integer>();			
				String[] column = {"one", "two", "three", "four", "five", "six"};
				
				for(String col : column) {
					for(Map<String, Object> list : seq) {
						if(list.get(col) != null) set.add(Integer.parseInt(list.get(col).toString()));					
					}				
				}		
			
				if(set.size() > 0) param.put("depth_seq", set);
			}else 	param.put("depth_none", 1);
			
			int total = FacilityService.standard_total(param);				
			List<Map<String, Object>> data = FacilityService.standard_list(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total	 + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + total + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/standard1/detail")
	public void standard1_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("tb_name", "device_standard1");
			List<Map<String, Object>> data = FacilityService.standard(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/standard1/edit")
	public void standard1_edit(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("tb_name", "device_standard1");
			param.put("max", 4);
			
			int result = FacilityService.standard_edit(param);
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Edit Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/standard1/delete")
	public void standard1_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 1);
			param.put("tb_name", "device_standard1");
			
			int result = FacilityService.standard_delete(param);
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Delete Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/standard2/list")
	public void standard2_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			param.put("tb_name", "device_standard2");
			
			List<Map<String, Object>> seq = FacilityService.standard_parent(param);
			
			if(seq.size() > 0) {
				Set<Integer> set = new HashSet<Integer>();			
				String[] column = {"one", "two", "three", "four", "five", "six"};
				
				for(String col : column) {
					for(Map<String, Object> list : seq) {
						if(list.get(col) != null) set.add(Integer.parseInt(list.get(col).toString()));					
					}				
				}		
			
				if(set.size() > 0) param.put("depth_seq", set);
			}else 	param.put("depth_none", 1);
				
			int total = FacilityService.standard_total(param);				
			List<Map<String, Object>> data = FacilityService.standard_list(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total	+ ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + total + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/standard2/detail")
	public void standard2_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("tb_name", "device_standard2");
			List<Map<String, Object>> data = FacilityService.standard(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/standard2/edit")
	public void standard2_edit(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("tb_name", "device_standard2");
			param.put("max", 6);
			
			int result = FacilityService.standard_edit(param);
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Edit Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/standard2/delete")
	public void standard2_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 2);
			param.put("tb_name", "device_standard2");
			
			int result = FacilityService.standard_delete(param);
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Delete Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/account/list")
	public void account_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			if(param.get("search_column") != null && !"".equals(param.get("search_column"))) param.put(param.get("search_column").toString(), param.get("search_word"));
			
			int page = Integer.parseInt(param.get("page").toString());
			int row = Integer.parseInt(param.get("row").toString());	
				
			int total = FacilityService.account_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = FacilityService.account_list(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total	+ ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + total	+ ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/account/detail")
	public void account_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data = FacilityService.account(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/account/edit")
	public void account_edit(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {			
			FacilityService.account_edit(param);
			jsonOut.write("{\"result\":[]" + ",\"code\":1, \"msg\":\"Data Edit Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/account/delete")
	public void account_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {			
			int result = FacilityService.account_delete(param);
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Delete Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/org/table")
	public void org_table(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {			
			//param.put("section", 2);
			//List<Map<String, Object>> depth = FacilityService.organize_seq(param); //사업단 하위레벨 표준명 시퀀스 가져오기
			
			param.put("section", 3);
	        List<Map<String, Object>> orgData = FacilityService.organize_seq(param);
			if(orgData.size() > 0) orgData = NullToString.nulltostring(orgData);
			int size = orgData.size(); //사업단 개수 가져오기
			
			/*
			if(depth.size() > 0) { 
				Set<Integer> set = new HashSet<Integer>();			
				String[] column = {"one", "two", "three", "four", "five", "six"};
				
				for(String col : column) {
					for(Map<String, Object> list : depth) {
						if(list.get(col) != null) set.add(Integer.parseInt(list.get(col).toString()));					
					}				
				}		
			
				if(set.size() > 0) param.put("depth_seq", set);
			}
			*/
			
			int page = Integer.parseInt(param.get("page").toString());
			int row = Integer.parseInt(param.get("row").toString());
			
			int total = FacilityService.standard1_total(param);
			
			if(total > 0) {			
				int startNum = (page - 1) * row;
				
				param.put("start_num", startNum);
				param.put("row_num", row);
				
				param.put("section", 1);
				List<Map<String, Object>> data1 = FacilityService.organize_table(param);
				List<Object> data3 = new ArrayList<Object>();	
				
				if(data1.size() > 0) {
					data1 = NullToString.nulltostring(data1);				
					
					if(orgData.size() > 0) {												
						startNum = (page - 1) * (row * size);
						
						param.put("start_num", startNum);
						param.put("row_num", (row * size));
						
						param.put("section", 2);
						List<Map<String, Object>> data2 = FacilityService.organize_table(param);						
											
						
						for(int i = 0; i < data2.size()/size; i++) {
							List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();

							for(int j = (i*size); j < (i*size) + size; j++) {
								data2.get(j).putAll(data1.get(i));
								tmpList.add(data2.get(j));						
							}
							data3.add(tmpList);
						}
						
						data2 = NullToString.nulltostring(data2);
						
						jsonOut.write("{\"result\":" + JsonUtil.ListToJson(data3)
						+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(orgData)
						+ ",\"total\":" + data1.size()
						+ ",\"org_size\":" + size
						+ ",\"row\":" + row  
						+ ",\"page\":" + page
						+ ",\"code\":1}");
					}else {
						jsonOut.write("{\"result\":" + JsonUtil.ListToJson(data3)
						+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(orgData)
						+ ",\"total\":" + data1.size()
						+ ",\"org_size\":" + size
						+ ",\"row\":" + row 
						+ ",\"page\":" + page
						+ ",\"code\":1}");
					}				
				}
			}else jsonOut.write("{\"result\":[]" + ",\"result2\":[]" + ",\"total\":" + 0 + ",\"total2\":" + 0 + ",\"org_size\":" + size + ",\"row\":" + row + ",\"page\":" + page + ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/history/table")
	public void history_table(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {			
			int page = Integer.parseInt(param.get("page").toString());
			int row = Integer.parseInt(param.get("row").toString());	
				
			int total = FacilityService.history_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = FacilityService.history_list(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total	+ ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + total	+ ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//사업단설비현황 엑셀
		@RequestMapping(value="/org/excel")
		public void org_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("사업단설비현황");

		        Row excelRow = null;
	            Cell cell = null;
	            int rowNum = 0;	            
	         
	            //헤더 스타일
	            CellStyle headerStyle = workbook.createCellStyle();		           
	            headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
	            headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            
	            //바디 스타일
	            CellStyle bodyStyle = workbook.createCellStyle();
	            bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		        
	            //구분 헤더
	            String[] header_title_01 = {"구분", "", "", "", "", "", "", "", "", "", "", "합계", ""};
	            String[] header_title_02 = {"설비", "", "", "기기명", "", "", "", "", "", "", "", "", ""};
	            
	            int[] header_row_start_01 = {0, 0, 1, 1};
	            int[] header_row_last_01 = {0, 1, 1, 1};
	            int[] header_col_start_01 = {0, 11, 0, 3};
	            int[] header_col_last_01 = {10, 12, 2, 10};
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title_01.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(headerStyle);
	                cell.setCellValue(header_title_01[i]);
	            }
	            
	            //사업단 헤더
	            param.put("section", 3);
	            List<Map<String, Object>> orgData = FacilityService.organize_seq(param);
	            
	            if(orgData.size() > 0) {
	            	orgData = NullToString.nulltostring(orgData);
	            	
	            	int orgSize = (orgData.size() * 2) + 13;
	            	int idx = 0;
	            	
	            	for(int i = 13; i < orgSize; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);
	                    if(i%2 == 1) cell.setCellValue(orgData.get(idx++).get("og_name").toString());
	            	}
	            } 
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title_02.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(headerStyle);
	                cell.setCellValue(header_title_02[i]);
	            }
	            
	            if(orgData.size() > 0) {            	
	            	int orgSize = (orgData.size() * 2) + 13;
	            	
	            	for(int i = 13; i < orgSize; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);
	            	}
	            } 
	            
	            for(int i = 0; i < header_row_start_01.length; i++) sheet.addMergedRegion(new CellRangeAddress( header_row_start_01[i], header_row_last_01[i], header_col_start_01[i], header_col_last_01[i]));            
	            if(orgData.size() > 0) {  
	            	int orgSize = (orgData.size() * 2) + 13;
	            	
	            	for(int i = 13; i < orgSize; i++) {
	            		if(i%2 == 1) sheet.addMergedRegion(new CellRangeAddress( 0, 1, i, i+1));  
	            	}
	            }
	                  
	            
	            
	            
	            //구분 바디
	            param.put("section", 2);
				List<Map<String, Object>> depth = FacilityService.organize_seq(param); //사업단 하위레벨 표준명 시퀀스 가져오기
				int size = FacilityService.organize_cnt(param); //사업단 개수 가져오기
				
				if(depth.size() > 0) { 
					Set<Integer> set = new HashSet<Integer>();			
					String[] column = {"one", "two", "three", "four", "five", "six"};
					
					for(String col : column) {
						for(Map<String, Object> list : depth) {
							if(list.get(col) != null) set.add(Integer.parseInt(list.get(col).toString()));					
						}				
					}		
				
					if(set.size() > 0) param.put("depth_seq", set);
				}
				
				int page =1;
				int row = 999;
				if(param.get("page") != null && !"".equals(param.get("page"))) page = Integer.parseInt(param.get("page").toString());
				if(param.get("row") != null && !"".equals(param.get("row"))) row = Integer.parseInt(param.get("row").toString());
				
				int startNum = (page - 1) * row;
				
				param.put("start_num", startNum);
				param.put("row_num", row);
				
				int total = FacilityService.standard1_total(param);
				
				if(total > 0) {
					int tmpRow = rowNum;
					
					param.put("section", 1);
					List<Map<String, Object>> data1 = FacilityService.organize_table(param);
					
					if(data1.size() > 0) {
						data1 = NullToString.nulltostring(data1);
						
						String[] body_column_01 = {"type", "device", "total"};
						int[] body_col_start_01 = {0, 3, 11};
						int[] body_col_last_01 = {2, 10, 12};
						
						for(Map<String, Object> list : data1) {
							excelRow = sheet.createRow(rowNum++);
				            
				            for(int i = 0; i < header_title_01.length; i++) {
				            	cell = excelRow.createCell(i);
				                cell.setCellStyle(bodyStyle);
				                
				                switch(i){
				                case 0 : cell.setCellValue(list.get(body_column_01[0]).toString()); break;
				                case 3 : cell.setCellValue(list.get(body_column_01[1]).toString()); break;
				                case 11 : cell.setCellValue(list.get(body_column_01[2]).toString()); break;
				                default : break;
				                }
				            }

				            //셀병합
				            for(int i = 0; i < body_col_start_01.length; i++) sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, body_col_start_01[i], body_col_last_01[i])); 
						}
						
						//사업단 바디
						if(depth.size() > 0) {						
							param.put("section", 2);
							List<Map<String, Object>> data2 = FacilityService.organize_table(param);						
							
							data2 = NullToString.nulltostring(data2);
							int idx = 0;

							for(int i = 0; i < data2.size(); i++) {
								int orgSize = (orgData.size() * 2) + 13;
								
								if(i % orgData.size() == 0) {
									excelRow = sheet.getRow(tmpRow++);
									
									//내용 넣기
									for(int j = 13; j < orgSize; j++) {
										cell = excelRow.createCell(j);
										cell.setCellStyle(bodyStyle);
										if(j%2 == 1) cell.setCellValue(data2.get(idx++).get("value").toString());
									}
									
									//병합
									for(int j = 13; j < orgSize; j++) if(j%2 == 1) sheet.addMergedRegion(new CellRangeAddress( tmpRow - 1, tmpRow - 1, j, j+1));  
								}																	
							}
						}
					}
				}
	            
		        String fileName = "시설통계(사업단설비현황)_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//이전/철거 현황 엑셀
		@RequestMapping(value="/history/excel")
		public void history_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("이전및철거현황");

		        Row excelRow = null;
	            Cell cell = null;
	            int rowNum = 0;	            
	         
	            //헤더 스타일
	            CellStyle headerStyle = workbook.createCellStyle();		           
	            headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
	            headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            
	            //바디 스타일
	            CellStyle bodyStyle = workbook.createCellStyle();
	            bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            
	            //헤더
	            String[] header_title = {"이전일", "", "이전위치", "", "설치위치", "", "구분", "승인", "설치자", "", "설치부서", "", "진행상황", "", "설치건명", ""};
	            String[] body_column = {"dh_date", "dh_location", "dh_install", "dh_division", "dh_approve", "dh_man", "dh_team", "dh_state", "dh_case", ""};
	            int[] body_idx = {0, 2, 4, 6, 7, 8, 10, 12, 14, 0};
				int[] header_col_start = {0, 2, 4, 8, 10, 12, 14};
				int[] header_col_last = {1, 3, 5, 9, 11, 13, 15};
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(headerStyle);
	                cell.setCellValue(header_title[i]);
	            }
	            
	            for(int i = 0; i < header_col_start.length; i++) sheet.addMergedRegion(new CellRangeAddress( 0, 0, header_col_start[i], header_col_last[i])); 
	        
	            int page =1;
				int row = 999;
				if(param.get("page") != null && !"".equals(param.get("page"))) page = Integer.parseInt(param.get("page").toString());
				if(param.get("row") != null && !"".equals(param.get("row"))) row = Integer.parseInt(param.get("row").toString());
				
				int startNum = (page - 1) * row;
				
				param.put("start_num", startNum);
				param.put("row_num", row);
	            
	            //바디
				List<Map<String, Object>> data = FacilityService.history_excel(param);
					
				if(data.size() > 0) {
					data = NullToString.nulltostring(data);
					
					for(Map<String, Object> list : data) {
						int idx = 0;
						excelRow = sheet.createRow(rowNum++);
			            
			            for(int i = 0; i < header_title.length; i++) {
			            	cell = excelRow.createCell(i);
			                cell.setCellStyle(bodyStyle);
			                
			                if(i == body_idx[idx]) {
			                	cell.setCellValue(list.get(body_column[idx]).toString());
			                	idx++;
			                }		               
			            }
			            
			            for(int i = 0; i < header_col_start.length; i++) sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, header_col_start[i], header_col_last[i])); 
					}
				}else {
					
				}
	            
	            String fileName = "시설통계(이전 및 철거현황)_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//기기관리 리스트 엑셀
		@RequestMapping(value="/device/detail/excel")
		public void measure_list_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
					
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
						
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("기기관리 상세정보");
				XSSFRow row = null;
				XSSFCell cell = null;
				int rowNum = 0;

				// 폰트 스타일
				XSSFFont font = wb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL); // 폰트 스타일
				font.setFontHeightInPoints((short) 12); // 폰트 크기
				font.setBold(true); // Bold 설정

				// Header스타일
				CellStyle headStyle = wb.createCellStyle();
				headStyle.setFont(font);
				headStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

				// 테이블 셀 스타일
				CellStyle cellStyle = wb.createCellStyle();

				for (int i = 0; i < 10; i++) sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + (short) 2600);// 컬럼 넓이 조절
				
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 정렬
				cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 얘를 꼭 해야함
				cellStyle.setBorderTop((short) 1); // 테두리 위쪽
				cellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
				cellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
				cellStyle.setBorderRight((short) 1); // 테두리 오른쪽
						
				CellStyle tableCellStyle = wb.createCellStyle();
				tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				tableCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				tableCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				tableCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    tableCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    tableCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

				List<Map<String, Object>> list = FacilityService.device_excel(param);
				list = NullToString.nulltostring(list);
						
				int colCnt = 7;
				int rowCnt = 13;				
						
				String[] title = {"기기명","세부기기명","설치사","제조업체","공급업체","제작일","취득일", "하자기간","모델명","사용전원","서비스팀","규격", "/"};					
				String[] title2 = {"표준명","표준명2","설치위치","위치명","최초설치일","일련번호","관리번호"	, "내용연수","하자만료일","재원","구입가격","현위치설치일","비고","철거여부"};
				String[] data = {"dc_name", "dc_device", "dc_install", "dc_produce", "dc_supply", "dc_create", "dc_take", "dc_defect", "dc_model", "dc_volt", "dc_team", "dc_norm", "/"};					
				String[] data2 = {"ds_seq1", "ds_seq2", "dc_position", "dc_location", "dc_first", "dc_number", "dc_control", "dc_years", "dc_expiry", "dc_data", "dc_price", "dc_current", "dc_note", "dc_remove"};
						
				for(int i = 0; i < rowCnt; i++) {
					row = sheet.createRow(rowNum++);
							
					for(int j = 0; j < colCnt; j++) {
						cell = row.createCell(j);
								
								switch(j) {
									case 0 : 
										cell.setCellStyle(cellStyle);
					                	cell.setCellValue(title[i]);
										break;
									case 1 :
										cell.setCellStyle(tableCellStyle);
										if(i == rowCnt-1) cell.setCellValue(data[i]);
										else cell.setCellValue(list.get(0).get(data[i]).toString());
										break;
									case 3 : 
										cell.setCellStyle(cellStyle);
					                	cell.setCellValue(title2[i]);
										break;
									case 4 : 
										cell.setCellStyle(tableCellStyle);
										cell.setCellValue(list.get(0).get(data2[i]).toString());
										break;
									default : 
										cell.setCellStyle(tableCellStyle);
										break;
								}
							}
							
							sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 1, 2));
							sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 4, 6));
						}
						
						for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가
						
						//Title
						cell = row.createCell((short) 0);
						cell.setCellStyle(headStyle);
						cell.setCellValue("이전 및 철거기록");
						
						String[] title3 = {"이전일","이전위치","설치위치","구분","승인","설치자","설치부서","진행현황","설치건명"};
						String[] column3 = {"dh_date", "dh_location", "dh_install", "dh_division", "dh_approve", "dh_man", "dh_team", "dh_state", "dh_case"};
						
						row = sheet.createRow(rowNum++);
						
						for(int i=0; i<title3.length; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(title3[i]);
						}
						
						List<Map<String, Object>> list2 = FacilityService.device_history_excel(param);
						
						if(list2.isEmpty()) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title3.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue("표시할 데이터가 없습니다.");
							}					
							
							sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 0, 8));
						}else {
							if(list2.size() > 0) list2 = NullToString.nulltostring(list2);
							
							for(Map<String, Object> datas : list2) {
								row = sheet.createRow(rowNum++);
								
								for(int i = 0; i < title3.length; i++) {
									cell = row.createCell(i);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(datas.get(column3[i]).toString());
								}
							}
						}
						
						 String fileName = "기기관리(기기관리 상세정보)_" + strNowDate + ".xlsx";
				            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
				            
				            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

				            // 엑셀 출력
				            wb.write(response.getOutputStream());
				            wb.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				//거래처 상세 정보 엑셀
				@RequestMapping(value="/account/detail/excel")
				public void account_detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/xlsx");
					
					try {
						Date nowDate = new Date();	        
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						String strNowDate = simpleDateFormat.format(nowDate);
						
						XSSFWorkbook wb = new XSSFWorkbook();
						XSSFSheet sheet = wb.createSheet("거래처관리 상세정보");
						XSSFRow row = null;
						XSSFCell cell = null;
						int rowNum = 0;

						// 폰트 스타일
						XSSFFont font = wb.createFont();
						font.setFontName(HSSFFont.FONT_ARIAL); // 폰트 스타일
						font.setFontHeightInPoints((short) 15); // 폰트 크기
						font.setBold(true); // Bold 설정
						// font.setColor(new XSSFColor(Color.decode("#457ba2"))); // 폰트 색 지정

						// Header스타일
						CellStyle headStyle = wb.createCellStyle();
						headStyle.setFont(font);
						headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

						// 테이블 셀 스타일
						CellStyle cellStyle = wb.createCellStyle();

						for (int i = 0; i < 10; i++) sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + (short) 2600);// 컬럼 넓이 조절					

						cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 정렬
						cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 얘를 꼭 해야함
						cellStyle.setBorderTop((short) 1); // 테두리 위쪽
						cellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
						cellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
						cellStyle.setBorderRight((short) 1); // 테두리 오른쪽

						// 테이블 스타일 설정
						CellStyle tableCellStyle = wb.createCellStyle();
						tableCellStyle.setBorderTop((short) 1); // 테두리 위쪽
						tableCellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
						tableCellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
						tableCellStyle.setBorderRight((short) 1); // 테두리 오른쪽
						tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

						List<Map<String, Object>> list = FacilityService.account_excel(param);
						list = NullToString.nulltostring(list);
						
						int colCnt = 7;
						int rowCnt = 8;
						
						String[] title = {"거래처명","대표자명","전화번호","팩스번호","주소","홈페이지","담당자","태그"};					
						String[] title2 = {"사업자번호","업태","종목","이메일","상세주소","","담장자연락처","사용여부"};					
						String[] data = {"act_company", "act_ceo", "act_phone", "act_fax", "act_addr", "act_url", "act_manager", "act_tag"};					
						String[] data2 = {"act_number", "act_status", "act_event", "act_email", "act_detail", "", "act_hp", "act_use"};
						
						for(int i = 0; i < rowCnt; i++) {
							row = sheet.createRow(rowNum++);
									
							for(int j = 0; j < colCnt; j++) {
								cell = row.createCell(j);
										
										switch(j) {
											case 0 : 
												cell.setCellStyle(cellStyle);
							                	cell.setCellValue(title[i]);
												break;
											case 1 :
												cell.setCellStyle(tableCellStyle);
												cell.setCellValue(list.get(0).get(data[i]).toString());
												break;
											case 4 : 
												cell.setCellStyle(cellStyle);
							                	cell.setCellValue(title2[i]);
												break;
											case 5 : 
												cell.setCellStyle(tableCellStyle);
												if(i == 5) cell.setCellValue(data2[i]);
												else cell.setCellValue(list.get(0).get(data2[i]).toString());
												break;
											default : 
												cell.setCellStyle(tableCellStyle);
												break;
										}
									}
									
									sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 1, 3));
									sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 5, 6));
								}
						
						 String fileName = "기기관리(거래처관리 상세정보)_" + strNowDate + ".xlsx";
				            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
				            
				            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

				            // 엑셀 출력
				            wb.write(response.getOutputStream());
				            wb.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				
				}
}
