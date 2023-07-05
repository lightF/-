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

import com.egov.namul.service.FileService;
import com.egov.namul.service.PartService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/part")
public class PartController {

	@Resource(name = "PartService")
	private PartService PartService;
	
	@Resource(name = "FileService")
	private FileService FileService;
	
	//부품관리 리스트
	@RequestMapping(value="/list")
	public void list(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		param.put("multi", multi);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
			if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());	
				
			int total = PartService.part_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = PartService.part_list(param);
				
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
	
	//부품관리 추가/수정
	@RequestMapping(value = "/edit")
	public void edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] sp_seq = request.getParameterValues("sp_seq[]");
			String[] sp_name = request.getParameterValues("sp_name[]");
			String[] sp_value = request.getParameterValues("sp_value[]");
			String[] sp_unit = request.getParameterValues("sp_unit[]");
			
			multi.clear();
			
			if(sp_seq != null) {
				for(int i = 0; i < sp_seq.length; i++) {
					int cnt = 0;

					if(sp_name[i].isEmpty()) cnt++;
					if(sp_value[i].isEmpty()) cnt++;
					if(sp_unit[i].isEmpty()) cnt++;
					
					if(cnt < 3) {
						if("".equals(sp_seq[i])) multi.add("sp_seq", 0);
						else multi.add("sp_seq", sp_seq[i]);
						
						multi.add("sp_name", sp_name[i]);
						
						if("".equals(sp_value[i])) multi.add("sp_value", 0);
						else multi.add("sp_value", sp_value[i]);
						
						if("".equals(sp_unit[i])) multi.add("sp_unit", 0);
						else multi.add("sp_unit", sp_unit[i]);
					}				
				}
				
				param.put("multi", multi);			
				if(multi.get("sp_seq") != null) param.put("sp_seq", 1);	
			}			
			
			PartService.part_edit(param);
			
			if(StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multiFiles = multiRequest.getFiles("upload[]");	 			
				
				String[] fileSeq = request.getParameterValues("f_seq[]");
				String[] fileDel = request.getParameterValues("f_del[]");
				param.put("tb_name", "part");				
				
				if(fileDel != null) FileService.multi_resize_upload(param, multiFiles, "part_file/", fileSeq, fileDel, 250, 200);
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
	
	//부품관리 상세정보
	@RequestMapping(value="/detail")
	public void detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 1); //기본정보
			List<Map<String, Object>> data1 = PartService.part(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			
			param.put("section", 2); //학력사항
			List<Map<String, Object>> data2 = PartService.spec(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			param.put("tb_name", "part"); //부품관리 파일 가져오기
			List<Map<String, Object>> data3 = FileService.file(param);
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
	
	//부품관리 삭제
	@RequestMapping(value="/delete")
	public void delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("tb_name", "part");
			int result = PartService.part_delete(param);
			FileService.delete(param, "part_file/");
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Delete Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//수불관리 리스트
	@RequestMapping(value="/payment/list")
	public void payment_list(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		param.put("multi", multi);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
			if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());
				
			int total = PartService.payment_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = PartService.payment_list(param);
				
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

	//수불관리 추가/수정
	@RequestMapping(value = "/payment/edit")
	public void payment_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] or_seq = request.getParameterValues("or_seq[]");
			String[] pt_seq = request.getParameterValues("pt_seq[]");
			String[] or_from = request.getParameterValues("or_from[]");
			String[] or_buy = request.getParameterValues("or_buy[]");
			String[] or_assign = request.getParameterValues("or_assign[]");
			String[] or_price = request.getParameterValues("or_price[]");
			
			multi.clear();
			
			if(or_seq != null) {
				for(int i = 0; i < or_seq.length; i++) {
					int cnt = 0;

					if(pt_seq[i].isEmpty()) cnt++;
					if(or_from[i].isEmpty()) cnt++;
					if(or_buy[i].isEmpty()) cnt++;
					if(or_assign[i].isEmpty()) cnt++;
					if(or_price[i].isEmpty()) cnt++;
					
					if(cnt < 5) {
						if("".equals(or_seq[i]))	multi.add("or_seq", 0);
						else multi.add("or_seq", or_seq[i]);
						
						if("".equals(pt_seq[i])) multi.add("pt_seq", 0);
						else multi.add("pt_seq", pt_seq[i]);
						
						if("".equals(or_from[i])) multi.add("or_from", 0);
						else multi.add("or_from", or_from[i]);
												
						if("".equals(or_buy[i])) multi.add("or_buy", 0);
						else multi.add("or_buy", or_buy[i]);				
						
						if("".equals(or_assign[i])) multi.add("or_assign", 0);
						else multi.add("or_assign", or_assign[i]);				
						
						if("".equals(or_price[i])) multi.add("or_price", 0);
						else multi.add("or_price", or_price[i]);
					}					
				}
				
				param.put("multi", multi);
				if(multi.get("or_seq") != null) {
					param.put("or_seq", 1);					
					//사업단 또는 배정부서 저장
				}
			}			
					
			PartService.payment_edit(param);			
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//수불관리 상세정보
	@RequestMapping(value="/payment/detail")
	public void payment_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 1); //기본정보
			List<Map<String, Object>> data1 = PartService.payment(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			
			param.put("section", 2); //
			List<Map<String, Object>> data2 = PartService.order_request(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data2)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//수불관리 삭제
	@RequestMapping(value="/payment/delete")
	public void payment_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			PartService.payment_delete(param);
			jsonOut.write("{\"result\":[]" + ",\"code\":1, \"msg\":\"Data Delete Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//수불관리 자동정보
	@RequestMapping(value="/payment/auto")
	public void auto_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("per_seq", Integer.parseInt(session.getAttribute("per_seq").toString()));
			
			List<Map<String, Object>> data = PartService.payment_create(param);
			if(data.size() > 0) data = NullToString.nulltostring(data);
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) 
			+ ",\"size\":" + data.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//발주요청 부품 리스트
	@RequestMapping(value="/order/list")
	public void order_list(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		param.put("multi", multi);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(param.get("search_column") != null && !"".equals(param.get("search_column"))) param.put(param.get("search_column").toString(), param.get("search_word"));
			
			if(param.get("bg_name") != null && !"".equals(param.get("bg_name"))) { //구분 검색하는 경우
				List<Map<String, Object>> budget = PartService.budget(param);
				
				if(budget.size() > 0) param.put("budget", budget);
				else param.put("none_budget", 1);
			}		
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
			if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());		
				
			int total = PartService.order_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = PartService.order_list(param);
				
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
	
	//창고관리 리스트
	@RequestMapping(value="/storage/list")
	public void storage_list(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		param.put("multi", multi);
		
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
			
			if(param.get("sr_division") != null && !"".equals(param.get("sr_division"))) { //관리부서 검색하는 경우
				List<Map<String, Object>> division = PartService.storage_org(param);
				
				if(division.size() > 0) param.put("division", division);
				else param.put("none_division", 1);
			}						
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
			if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());		
				
			int total = PartService.storage_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = PartService.storage_list(param);
				
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
	
	//창고관리 추가/수정
	@RequestMapping(value = "/storage/edit")
	public void storage_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] sg_seq = request.getParameterValues("sg_seq[]");
			String[] og_seq = request.getParameterValues("og_seq[]");
			
			if(sg_seq != null) {
				multi.clear();								
				
				for(int i = 0; i < sg_seq.length; i++) {
					int cnt = 0;					
					if(og_seq[i].isEmpty()) cnt++;
					
					if(cnt < 1) {
						if(sg_seq[i].isEmpty())	multi.add("sg_seq", 0);
						else multi.add("sg_seq", sg_seq[i]);
						
						if(og_seq[i].isEmpty()) multi.add("og_seq", 0);
						else multi.add("og_seq", og_seq[i]);
					}				
				}
				
				param.put("multi", multi);
				if(multi.get("sg_seq") != null) param.put("sg_seq", 1);
			}			
			
			PartService.storage_edit(param);			
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//창고관리 상세정보
	@RequestMapping(value="/storage/detail")
	public void storage_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = PartService.storage(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			
			List<Map<String, Object>> data2 = PartService.storage_group(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data2)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//창고관리 삭제
	@RequestMapping(value="/storage/delete")
	public void storage_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int result = PartService.storage_delete(param);
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Delete Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//창고사용가능 여부 확인
	@RequestMapping(value="/storage/check")
	public void storage_check(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int result = PartService.storage_check(param);
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Delete Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//부품통계(재고내역)
	@RequestMapping(value="/table")
	public void table(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			param.put("section", 1);
			List<Map<String, Object>> data1 = PartService.stock(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			
			param.put("section", 2);
			List<Map<String, Object>> data2 = PartService.stock(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data2) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data1)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//부품통계(재고상세조회)
	@RequestMapping(value="/stock/table")
	public void stock_table(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 3);
			List<Map<String, Object>> data = PartService.stock(param);
				
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
	
	//사업단 정보 가져오기
	@RequestMapping(value="/org")
	public void org(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = PartService.organize_group(param);
			List<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();
			
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);		
			
			if(param.get("og_seq") != null && !"".equals(param.get("og_seq"))) {
				List<Map<String, Object>> org = PartService.organize_depth(param);	
				
				if(org.size() > 0) {
					Set<Integer> set = new HashSet<Integer>();			
					String[] column = {"one", "two", "three", "four", "five", "six"};
					
					for(String col : column) {
						for(Map<String, Object> list : org) {
							if(list.get(col) != null) set.add(Integer.parseInt(list.get(col).toString()));					
						}				
					}		
				
					if(set.size() > 0) param.put("org_seq", set);
				}else 	param.put("org_none", 1);				
				
				param.put("section", 11);
				data2 = PartService.organize_group(param);
				if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			}			
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data2)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//부품통계 엑셀
		@RequestMapping(value="/stock/excel")
		public void stock_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("부품별 재고조회");

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
	            String[] header_title = {"부품코드", "", "부품명", "", "기기명", "", "창고코드", "", "부서명", "", "가능량", "", "재고금액", ""};
	            String[] body_column = {"pt_code", "pt_name", "ds_name", "sr_seq", "og_name", "ol_total", "ol_amount"};
				int[] header_col_start = {0, 2, 4, 6, 8, 10, 12};
				int[] header_col_last = {1, 3, 5, 7, 9, 11, 13};
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(headerStyle);
	                cell.setCellValue(header_title[i]);
	            }
	            
	            for(int i = 0; i < header_col_start.length; i++) sheet.addMergedRegion(new CellRangeAddress( 0, 0, header_col_start[i], header_col_last[i])); 
	        
	            //바디
	            param.put("section", 2);
				List<Map<String, Object>> data1 = PartService.stock(param);
				if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
				
				//총계
				excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(bodyStyle);
	                
	                if(i == 0) cell.setCellValue("총 계");
	                else if(i == 10) cell.setCellValue(data1.get(0).get("ol_total").toString());
	                else if(i == 12) cell.setCellValue(data1.get(0).get("ol_amount").toString());
	            }
	            
	            for(int i = 0; i < header_col_start.length; i++) sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, header_col_start[i], header_col_last[i])); 
				
	            //재고 리스트
				param.put("section", 1);
				List<Map<String, Object>> data2 = PartService.stock(param);
				if(data2.size() > 0) {
					data2 = NullToString.nulltostring(data2);
					
					for(Map<String, Object> list : data2) {
						excelRow = sheet.createRow(rowNum++);
						int idx = 0;
						
			            for(int i = 0; i < header_title.length; i++) {
			            	cell = excelRow.createCell(i);
			                cell.setCellStyle(bodyStyle);		                
			                if(i%2 == 0) cell.setCellValue(list.get(body_column[idx++]).toString());		                
			            }
			            
			            for(int i = 0; i < header_col_start.length; i++) sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, header_col_start[i], header_col_last[i])); 
					}
				}		
	                        
	            String fileName = "부품통계(부품별 재고조회)_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//부품관리 상세정보 엑셀
		@RequestMapping(value="/detail/excel")
		public void detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("부품관리 상세정보");
				XSSFRow row = null;
				XSSFCell cell = null;
				int rowNum = 0;

				// 폰트 스타일
				XSSFFont font = wb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL); // 폰트 스타일
				font.setFontHeightInPoints((short) 12); // 폰트 크기
				font.setBold(true); // Bold 설정
				// font.setColor(new XSSFColor(Color.decode("#457ba2"))); // 폰트 색 지정

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

				// 테이블 스타일 설정
				CellStyle tableCellStyle = wb.createCellStyle();
				tableCellStyle.setBorderTop((short) 1); // 테두리 위쪽
				tableCellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
				tableCellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
				tableCellStyle.setBorderRight((short) 1); // 테두리 오른쪽
				tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				List<Map<String, Object>> list = PartService.part_excel(param);
				List<Map<String, Object>> list2 = PartService.select_spec_excel(param);
				
				if(list.size() > 0) {
					list = NullToString.nulltostring(list);
					
					int colCnt = 6;
					int rowCnt = 9;
				
					String[] title = {"설비분류","기기분류","기기제조사","부품코드","부품명","부품제조사","주요기능", "S/N관리여부","단가계약품 여부"};
					String[] title2 = {"","기기운영여부","연식","예산과목","부품사양","부품단위","하자기간","부품구성","수입검사여부"};
					String[] data = {"sys_seq", "ds_seq", "act_device", "pt_code", "pt_name", "act_unit", "pt_main", "pt_sn", "pt_contract"};
					String[] data2 = {"", "pt_operate", "pt_years", "bg_seq", "pt_spec", "pt_unit", "pt_defect", "pt_part", "pt_test"};
				
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
									if(i == 0) cell.setCellValue(data2[i]);
									else cell.setCellValue(list.get(0).get(data2[i]).toString());
									break;
								default : 
									cell.setCellStyle(tableCellStyle);
									break;
								}
							}
								
							sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 1, 3));
					}
				}
				
				for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가
				
				//Title
				cell = row.createCell((short) 0);
				cell.setCellStyle(headStyle);
				cell.setCellValue("추가스펙");
				
				String[] title3 = {"규격명","규격값","규격단위"};
				String[] column1 = {"sp_name", "sp_value", "sp_unit"};
				
				row = sheet.createRow(rowNum++);
				
				//헤더
				for (int i = 0; i < title3.length; i++) { 
					cell = row.createCell(i);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(title3[i]);
				}
				
				//바디
				if(list2.size() > 0) {
					list2 = NullToString.nulltostring(list2);					
					
					for(Map<String, Object> datas : list2) {
						row = sheet.createRow(rowNum++);
						
						for(int i = 0; i < column1.length; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(datas.get(column1[i]).toString());
						}
					}					
				}else {
					row = sheet.createRow(rowNum++);
					
					for(int i = 0; i < column1.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(tableCellStyle);
						cell.setCellValue("존재하는 데이터가 없습니다.");
					}
					
					sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 0, 2));
				}
					
				 String fileName = "부품관리(부품관리 상세정보)_" + strNowDate + ".xlsx";
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
		
		//수불관리 상세정보
		@RequestMapping(value="/payment/detail/excel")
		public void payment_detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("수불관리 상세정보");
				XSSFRow row = null;
				XSSFCell cell = null;
				int rowNum = 0;

				// 폰트 스타일
				XSSFFont font = wb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL); // 폰트 스타일
				font.setFontHeightInPoints((short) 12); // 폰트 크기
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
				
				List<Map<String, Object>> list = PartService.select_payment_excel(param);
				List<Map<String, Object>> list2 = PartService.select_order_request_excel(param);
				
				if(list.size() > 0) {
					list = NullToString.nulltostring(list);
					
					int colCnt = 8;
					int rowCnt = 5;
					
					String[] title = {"계약명","발주부서","발주일자","납품예정",""};			
					String[] title2 = {"구매품의서 번호","발주업체","납품장소","배정창고","구매담당자"};
					String[] data = {"pm_contract", "og_seq", "pm_date", "pm_delivery", ""};			
					String[] data2 = {"pm_letter", "act_seq", "pm_place", "pm_storage", "per_seq"};
					
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
										case 5 : 
											cell.setCellStyle(cellStyle);
						                	cell.setCellValue(title2[i]);
											break;
										case 6 : 
											cell.setCellStyle(tableCellStyle);
											cell.setCellValue(list.get(0).get(data2[i]).toString());
											break;
										default : 
											cell.setCellStyle(tableCellStyle);
											break;
							}
						}
								
						sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 1, 4));
						sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 6, 7));
					}
				}		

				for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가
				
				String[] title3 = {"부품코드","부품명","구매수량","배정수량","단가"};
				String[] column1 = {"or_seq", "pt_seq", "or_buy", "or_assign", "or_price"};
				
				row = sheet.createRow(rowNum++);
				
				//헤더
				for (int i = 0; i < title3.length; i++) { 
					cell = row.createCell(i);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(title3[i]);
				}
				
				//바디
				if(list2.size() > 0) {
					list2 = NullToString.nulltostring(list2);					
					
					for(Map<String, Object> datas : list2) {
						row = sheet.createRow(rowNum++);
						
						for(int i = 0; i < column1.length; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(datas.get(column1[i]).toString());
						}
					}					
				}else {
					row = sheet.createRow(rowNum++);
					
					for(int i = 0; i < column1.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(tableCellStyle);
						cell.setCellValue("존재하는 데이터가 없습니다.");
					}
				}
				
				
				 String fileName = "부품관리(수불관리 상세정보)_" + strNowDate + ".xlsx";
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
		
		//창고관리 상세정보
		@RequestMapping(value="/storage/detail/excel")
		public void storage_detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("창고관리 상세정보");
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
				
				List<Map<String, Object>> list = PartService.select_storage_excel(param);
				List<Map<String, Object>> list2 = PartService.select_storage_group_excel(param);
				
				if(list.size() > 0) {
					list = NullToString.nulltostring(list);
					
					int colCnt = 3;
					int rowCnt = 3;
				
					String[] title = {"창고코드","창고명","참고유형"};				
					String[] data = {"seq", "sr_name", "sr_type"};
					
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
										default : 
											cell.setCellStyle(tableCellStyle);
											break;
								}
							}
						
						sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 1, 2));
					}
				}
				
				int mergeStart = rowNum;
				
				if(list2.size() > 0) {
					list2 = NullToString.nulltostring(list2);
					
					for(Map<String, Object> datas : list2) {
						row = sheet.createRow(rowNum++);
						
						for(int i = 0; i < 2; i++) {
							cell = row.createCell(i);
							
							switch(i) {
							case 0 : 
								cell.setCellStyle(cellStyle);
			                	cell.setCellValue("관리부서");
								break;
							case 1 :
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get("og_seq").toString());
								break;
							default : 
								cell.setCellStyle(tableCellStyle);
								break;
							}
						}
						
						sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 1, 2));
					}
					
					sheet.addMergedRegion(new CellRangeAddress( mergeStart, rowNum - 1, 0, 0));
				}
				
				 String fileName = "부품관리(창고관리 상세정보)_" + strNowDate + ".xlsx";
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
