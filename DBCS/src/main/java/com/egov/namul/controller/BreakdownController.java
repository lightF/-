package com.egov.namul.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.egov.namul.service.BreakdownService;
import com.egov.namul.service.FileService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/breakdown")
public class BreakdownController {

	@Resource(name = "BreakdownService")
	private BreakdownService BreakdownService;
	
	@Resource(name = "FileService")
	private FileService FileService;
	
	//고장접수관리 리스트
	@RequestMapping(value="/list")
	public void list(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {		
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
			
			if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
			if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());
				
			int total = BreakdownService.breakdown_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = BreakdownService.breakdown_list(param);			
				
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
	
	//고장접수관리 기본정보 추가/수정
	@RequestMapping(value = "/edit")
	public void edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] bw_seq = request.getParameterValues("bw_seq[]");
			String[] per_seq2 = request.getParameterValues("per_seq2[]");
			String[] bw_note = request.getParameterValues("bw_note[]");
			String[] bc_seq = request.getParameterValues("bc_seq[]");
			String[] bc_compose = request.getParameterValues("bc_compose[]");
			String[] bc_status = request.getParameterValues("bc_status[]");
			String[] bc_cause = request.getParameterValues("bc_cause[]");
			String[] bc_result = request.getParameterValues("bc_result[]");
			String[] bc_note = request.getParameterValues("bc_note[]");
			String[] bs_seq1 = request.getParameterValues("bs_seq1[]");
			String[] bc_seq1 = request.getParameterValues("bc_seq1[]");
			String[] bs_process1 = request.getParameterValues("bs_process1[]");
			String[] bs_old1 = request.getParameterValues("bs_old1[]");
			String[] bs_new1 = request.getParameterValues("bs_new1[]");
			String[] pt_seq1 = request.getParameterValues("pt_seq1[]");
			String[] bs_count1 = request.getParameterValues("bs_count1[]");
			String[] bs_price1 = request.getParameterValues("bs_price1[]");
			String[] bs_note1 = request.getParameterValues("bs_note1[]");
			String[] bs_seq2 = request.getParameterValues("bs_seq2[]");
			String[] bc_seq2 = request.getParameterValues("bc_seq2[]");
			String[] bs_process2 = request.getParameterValues("bs_process2[]");
			String[] bs_old2 = request.getParameterValues("bs_old2[]");
			String[] bs_new2 = request.getParameterValues("bs_new2[]");
			String[] pt_seq2 = request.getParameterValues("pt_seq2[]");
			String[] bs_count2 = request.getParameterValues("bs_count2[]");
			String[] bs_price2 = request.getParameterValues("bs_price2[]");
			String[] bs_note2 = request.getParameterValues("bs_note2[]");
			
			if(bw_seq != null) { //작업자
				for(int i = 0; i < bw_seq.length; i++) {
					int cnt = 0;

					if(per_seq2[i].isEmpty()) cnt++;
					if(bw_note[i].isEmpty()) cnt++;
					
					if(cnt < 2) {
						if("".equals(bw_seq[i])) multi.add("bw_seq", 0);
						else multi.add("bw_seq", bw_seq[i]);
						
						if("".equals(per_seq2[i])) multi.add("per_seq2", 0);
						else multi.add("per_seq2", per_seq2[i]);
						
						multi.add("bw_note", bw_note[i]);
					}					
				}				
							
				if(multi.get("bw_seq") != null) param.put("bw_seq", 1);
			}
			
			if(bc_seq != null) { //구성부
				for(int i = 0; i < bc_seq.length; i++) {
					int cnt = 0;

					if(bc_compose[i].isEmpty()) cnt++;
					if(bc_status[i].isEmpty()) cnt++;
					if(bc_cause[i].isEmpty()) cnt++;
					if(bc_result[i].isEmpty()) cnt++;
					if(bc_note[i].isEmpty()) cnt++;
					
					if(cnt < 5) {
						if("".equals(bc_seq[i])) multi.add("bc_seq", 0);
						else multi.add("bc_seq", bc_seq[i]);
						
						multi.add("bc_compose", bc_compose[i]);
						multi.add("bc_status", bc_status[i]);
						multi.add("bc_cause", bc_cause[i]);
						multi.add("bc_result", bc_result[i]);
						multi.add("bc_note", bc_note[i]);
					}					
				}
				
				if(multi.get("bc_seq") != null) param.put("bc_seq", 1);
			}
			
			if(bs_seq1 != null) { //저장품내역
				for(int i = 0; i < bs_seq1.length; i++) {
					int cnt = 0;

					if(bc_seq1[i].isEmpty()) cnt++;
					if(bs_process1[i].isEmpty()) cnt++;
					if(bs_old1[i].isEmpty()) cnt++;
					if(bs_new1[i].isEmpty()) cnt++;
					if(pt_seq1[i].isEmpty()) cnt++;					
					if(bs_count1[i].isEmpty()) cnt++;
					if(bs_price1[i].isEmpty()) cnt++;
					if(bs_note1[i].isEmpty()) cnt++;
					
					if(cnt < 8) {
						if("".equals(bs_seq1[i])) multi.add("bs_seq1", 0);
						else multi.add("bs_seq1", bs_seq1[i]);
						
						if("".equals(bc_seq1[i])) multi.add("bc_seq1", 0);
						else multi.add("bc_seq1", bc_seq1[i]);
						
						if("".equals(bs_process1[i])) multi.add("bs_process1", 0);
						else multi.add("bs_process1", bs_process1[i]);
						
						if("".equals(bs_old1[i])) multi.add("bs_old1", 0);
						else multi.add("bs_old1", bs_old1[i]);
						
						if("".equals(bs_new1[i])) multi.add("bs_new1", 0);
						else multi.add("bs_new1", bs_new1[i]);
						
						if("".equals(pt_seq1[i])) multi.add("pt_seq1", 0);
						else multi.add("pt_seq1", pt_seq1[i]);
						
						if("".equals(bs_count1[i])) multi.add("bs_count1", 0);
						else multi.add("bs_count1", bs_count1[i]);
						
						if("".equals(bs_price1[i])) multi.add("bs_price1", 0);
						else multi.add("bs_price1", bs_price1[i]);
						
						multi.add("bs_note1", bs_note1[i]);
					}					
				}
				
				if(multi.get("bs_seq1") != null) param.put("bs_seq1", 1);
			}
			
			if(bs_seq2 != null) { //KHC예비품/재료비
				for(int i = 0; i < bs_seq2.length; i++) {
					int cnt = 0;

					if(bc_seq2[i].isEmpty()) cnt++;
					if(bs_process2[i].isEmpty()) cnt++;
					if(bs_old2[i].isEmpty()) cnt++;
					if(bs_new2[i].isEmpty()) cnt++;
					if(pt_seq2[i].isEmpty()) cnt++;					
					if(bs_count2[i].isEmpty()) cnt++;
					if(bs_price2[i].isEmpty()) cnt++;
					if(bs_note2[i].isEmpty()) cnt++;
					
					if(cnt < 8) {
						if("".equals(bs_seq2[i])) multi.add("bs_seq2", 0);
						else multi.add("bs_seq2", bs_seq2[i]);
						
						if("".equals(bc_seq2[i])) multi.add("bc_seq2", 0);
						else multi.add("bc_seq2", bc_seq2[i]);
						
						if("".equals(bs_process2[i])) multi.add("bs_process2", 0);
						else multi.add("bs_process2", bs_process2[i]);
						
						if("".equals(bs_old2[i])) multi.add("bs_old2", 0);
						else multi.add("bs_old2", bs_old2[i]);
						
						if("".equals(bs_new2[i])) multi.add("bs_new2", 0);
						else multi.add("bs_new2", bs_new2[i]);
						
						if("".equals(pt_seq2[i])) multi.add("pt_seq2", 0);
						else multi.add("pt_seq2", pt_seq2[i]);
						
						if("".equals(bs_count2[i])) multi.add("bs_count2", 0);
						else multi.add("bs_count2", bs_count2[i]);
						
						if("".equals(bs_price2[i])) multi.add("bs_price2", 0);
						else multi.add("bs_price2", bs_price2[i]);
						
						multi.add("bs_note2", bs_note2[i]);
					}					
				}
				
				if(multi.get("bs_seq2") != null) param.put("bs_seq2", 2);
			}
			
			param.put("multi", multi);
			
			BreakdownService.breakdown_edit(param); //기본정보&작업자&구성부
			BreakdownService.storage_edit(param); //저장품&예비품
			
			if(StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multiFiles = multiRequest.getFiles("upload[]");	 			
				
				String[] fileSeq = request.getParameterValues("f_seq[]");
				String[] fileDel = request.getParameterValues("f_del[]");
				param.put("tb_name", "breakdown");			
				
				if(fileDel != null) FileService.multi_resize_upload(param, multiFiles, "breakdown_file/", fileSeq, fileDel, 250, 200);
			}		
			
			jsonOut.write("{\"result\":[]" + ",\"seq\":" + Integer.parseInt(param.get("seq").toString()) + ",\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//고장접수관리 상세정보
	@RequestMapping(value="/detail")
	public void detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = BreakdownService.breakdown(param); //고장접수관리 상세정보
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			
			param.put("tb_name", "breakdown"); //파일 가져오기
			List<Map<String, Object>> data2 = FileService.file(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);

			List<Map<String, Object>> data3 = BreakdownService.breakdown_worker(param); //작업자 상세정보
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);
			
			List<Map<String, Object>> data4 = BreakdownService.breakdown_compose(param); //구성부 상세정보
			if(data4.size() > 0) data4 = NullToString.nulltostring(data4);
			
			List<Map<String, Object>> data5 = BreakdownService.breakdown_storage(param); //저장품내역 상세정보
			if(data5.size() > 0) data5 = NullToString.nulltostring(data5);
			
			List<Map<String, Object>> data6 = BreakdownService.breakdown_spare(param); //KHC 상세정보
			if(data6.size() > 0) data6 = NullToString.nulltostring(data6);
			
			List<Map<String, Object>> data7 = BreakdownService.breakdown_compose(param);
			if(data7.size() > 0) data7 = NullToString.nulltostring(data7);
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data2)
			+ ",\"result3\":" + JsonUtil.getJsonArrayFromList(data3)
			+ ",\"result4\":" + JsonUtil.getJsonArrayFromList(data4)
			+ ",\"result5\":" + JsonUtil.getJsonArrayFromList(data5)
			+ ",\"result6\":" + JsonUtil.getJsonArrayFromList(data6)
			+ ",\"result7\":" + JsonUtil.getJsonArrayFromList(data7)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"size3\":" + data3.size()
			+ ",\"size4\":" + data4.size()
			+ ",\"size5\":" + data5.size()		
			+ ",\"size6\":" + data6.size()	
			+ ",\"size7\":" + data7.size()	
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//고장접수관리 삭제
	@RequestMapping(value="/delete")
	public void delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {
			int result = BreakdownService.breakdown_delete(param);
			
			if(result == 1001) {
				param.put("tb_name", "breakdown");				
				FileService.delete(param, "breakdown_file/");
			}			
			
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//구성부 리스트(셀렉트)
	@RequestMapping(value="/compose/list")
	public void compose_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {			
			List<Map<String, Object>> data = BreakdownService.breakdown_compose(param);
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"size\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"size\":" + data.size() + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//저장품 또는 예비품 팝업 리스트
	@RequestMapping(value="/part/list")
	public void part_list(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(param.get("search_column") != null && !"".equals(param.get("search_column"))) param.put(param.get("search_column").toString(), param.get("search_word"));
			
			int section = Integer.parseInt(param.get("section").toString());
			
			if(section == 11) { //구분
				List<Map<String, Object>> budget = BreakdownService.budget(param);
				
				if(budget.size() > 0) param.put("budget", budget);
				else param.put("none_budget", 1);
			}
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
			if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());
				
			int total = BreakdownService.part_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = BreakdownService.part_list(param);			
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total	+ ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + total + ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//고장조치관리 리스트
	@RequestMapping(value="/action/list")
	public void action_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
				
			int total = BreakdownService.action_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = BreakdownService.action_list(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				
				param.put("action_num", 1);
				List<Map<String, Object>> data2 = BreakdownService.action_count(param);	
				
				param.put("action_num", 2);
				List<Map<String, Object>> data3 = BreakdownService.action_count(param);

				for(int i = 0; i < data.size(); i++) {	
					int og_seq = Integer.parseInt(data.get(i).get("og_seq").toString());
					int at_daily = Integer.parseInt(data.get(i).get("at_daily").toString());
					int at_division = Integer.parseInt(data.get(i).get("at_division").toString());
					
					data.get(i).put("finish", 0);
					data.get(i).put("not_take", 0);
					
					for(Map<String, Object> list : data2) {
						int og_seq2 = Integer.parseInt(list.get("og_seq").toString());
						int at_daily2 = Integer.parseInt(list.get("at_daily").toString());
						int at_division2 = Integer.parseInt(list.get("at_division").toString());						
						
						if(og_seq == og_seq2 && at_daily == at_daily2 && at_division == at_division2) {
							data.get(i).put("finish", list.get("finish"));
							break;
						}
					}
					
					for(Map<String, Object> list : data3) {
						int og_seq2 = Integer.parseInt(list.get("og_seq").toString());
						int at_daily2 = Integer.parseInt(list.get("at_daily").toString());
						int at_division2 = Integer.parseInt(list.get("at_division").toString());						
						
						if(og_seq == og_seq2 && at_daily == at_daily2 && at_division == at_division2) {
							data.get(i).put("not_take", list.get("not_take"));
							break;
						}
					}
				}
				
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total	+ ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + total + ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//고장조치관리 추가/수정
	@RequestMapping(value="/action/edit")
	public void action_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		param.put("multi", multi);
		
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			BreakdownService.action_edit(param);
			
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//고장조치관리 삭제
	@RequestMapping(value="/action/delete")
	public void action_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {
			BreakdownService.action_delete(param);
			
			jsonOut.write("{\"result\":[]" + ",\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//고장조치관리 기본정보
	@RequestMapping(value="/action/detail")
	public void action_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = BreakdownService.action(param); //고장조치관리 상세정보
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);

			List<Map<String, Object>> data2 = BreakdownService.action_date(param); //고장조치관리 상세 리스트
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
	
	//고장조치보고서
	@RequestMapping(value="/action/report")
	public void action_report(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data = BreakdownService.action_report(param);
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"count\":" + data.size()	+ ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"count\":" + 0 + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}	
	
	//당직일지 리스트
	@RequestMapping(value="/shift/list")
	public void shift_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
				
			int total = BreakdownService.shift_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = BreakdownService.shift_list(param);			
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total	+ ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + total + ",\"row\":" + row  + ",\"page\":" + page + ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//당직일지 추가/수정
	@RequestMapping(value="/shift/edit")
	public void shift_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] wk_seq = request.getParameterValues("wk_seq[]");
			String[] wt_seq = request.getParameterValues("wt_seq[]");
			String[] wk_place = request.getParameterValues("wk_place[]");
			String[] wk_team = request.getParameterValues("wk_team[]");
			String[] wk_rank = request.getParameterValues("wk_rank[]");
			String[] per_seq = request.getParameterValues("per_seq[]");
			String[] wk_start = request.getParameterValues("wk_start[]");
			String[] wk_end = request.getParameterValues("wk_end[]");
			String[] wk_content = request.getParameterValues("wk_content[]");
			String[] wk_note = request.getParameterValues("wk_note[]");
			String[] ct_seq = request.getParameterValues("ct_seq[]");
			String[] ct_phone = request.getParameterValues("ct_phone[]");
			String[] ct_date = request.getParameterValues("ct_date[]");
			String[] per_seq2 = request.getParameterValues("per_seq2[]");
			String[] ct_content = request.getParameterValues("ct_content[]");
			String[] ct_result = request.getParameterValues("ct_result[]");
			String[] rp_seq = request.getParameterValues("rp_seq[]");
			String[] rp_hour = request.getParameterValues("rp_hour[]");
			String[] rp_content = request.getParameterValues("rp_content[]");
			String[] rp_caller = request.getParameterValues("rp_caller[]");
			
			multi.clear();
			
			if(wk_seq != null) { //근무자
				for(int i = 0; i < wk_seq.length; i++) {
					int cnt = 0;

					if(wt_seq[i].isEmpty()) cnt++;
					if(wk_place[i].isEmpty()) cnt++;
					if(wk_team[i].isEmpty()) cnt++;
					if(wk_rank[i].isEmpty()) cnt++;
					if(per_seq[i].isEmpty()) cnt++;					
					if(wk_start[i].isEmpty()) cnt++;
					if(wk_end[i].isEmpty()) cnt++;
					if(wk_content[i].isEmpty()) cnt++;
					if(wk_note[i].isEmpty()) cnt++;
					
					if(cnt < 9) {
						if("".equals(wk_seq[i])) multi.add("wk_seq", 0);
						else multi.add("wk_seq", wk_seq[i]);
						
						if("".equals(wt_seq[i])) multi.add("wt_seq", 0);
						else multi.add("wt_seq", wt_seq[i]);
						
						multi.add("wk_place", wk_place[i]);
						multi.add("wk_team", wk_team[i]);
						multi.add("wk_rank", wk_rank[i]);
						
						if("".equals(per_seq[i])) multi.add("per_seq", 0);
						else multi.add("per_seq", per_seq[i]);
						
						if("".equals(wk_start[i])) multi.add("wk_start", 0);
						else multi.add("wk_start", wk_start[i]);
						
						if("".equals(wk_end[i])) multi.add("wk_end", 0);
						else multi.add("wk_end", wk_end[i]);
						
						multi.add("wk_content", wk_content[i]);
						multi.add("wk_note", wk_note[i]);
					}					
				}
				
				if(multi.get("wk_seq") != null) {
					System.out.println("저장이 되고있는가?");
					param.put("wk_seq", 1);
				}
			}
			
			if(ct_seq != null) { //지시/연락사항
				for(int i = 0; i < ct_seq.length; i++) {
					int cnt = 0;

					if(ct_phone[i].isEmpty()) cnt++;
					if(ct_date[i].isEmpty()) cnt++;
					if(per_seq2[i].isEmpty()) cnt++;
					if(ct_content[i].isEmpty()) cnt++;
					if(ct_result[i].isEmpty()) cnt++;					
										
					if(cnt < 5) {
						if("".equals(ct_seq[i])) multi.add("ct_seq", 0);
						else multi.add("ct_seq", ct_seq[i]);
						
						multi.add("ct_phone", ct_phone[i]);
						
						if("".equals(ct_date[i])) multi.add("ct_date", 0);
						else multi.add("ct_date", ct_date[i]);
						
						if("".equals(per_seq2[i])) multi.add("per_seq2", 0);
						else multi.add("per_seq2", per_seq2[i]);
						
						multi.add("ct_content", ct_content[i]);
						multi.add("ct_result", ct_result[i]);
					}			
				}
				
				if(multi.get("ct_seq") != null) param.put("ct_seq", 1);
			}
			
			if(rp_seq != null) { //보고사항
				for(int i = 0; i < rp_seq.length; i++) {
					int cnt = 0;

					if(rp_hour[i].isEmpty()) cnt++;
					if(rp_content[i].isEmpty()) cnt++;
					if(rp_caller[i].isEmpty()) cnt++;
					
					if(cnt < 3) {
						if("".equals(rp_seq[i])) multi.add("rp_seq", 0);
						else multi.add("rp_seq", rp_seq[i]);
						
						if("".equals(rp_hour[i])) multi.add("rp_hour", 0);
						else multi.add("rp_hour", rp_hour[i]);
						
						multi.add("rp_content", rp_content[i]);
						multi.add("rp_caller", rp_caller[i]);
					}					
				}
				
				if(multi.get("rp_seq") != null) param.put("rp_seq", 1);
			}
			
			param.put("multi", multi);
			
			BreakdownService.shift_edit(param);
			
			if(StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multiFiles = multiRequest.getFiles("upload[]");	 			
				
				String[] fileSeq = request.getParameterValues("f_seq[]");
				String[] fileDel = request.getParameterValues("f_del[]");

				param.put("tb_name", "shift");			
				
				if(fileDel != null) FileService.multi_resize_upload(param, multiFiles, "shift_file/", fileSeq, fileDel, 250, 200);
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
	
	//당직일지 기본정보
	@RequestMapping(value="/shift/detail")
	public void shift_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = BreakdownService.shift(param); //당직일지 기본정보
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			
			param.put("tb_name", "shift"); //사고일지 사진 가져오기
			List<Map<String, Object>> data2 = FileService.file(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);

			List<Map<String, Object>> data3 = BreakdownService.worker(param); //근무자
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);			
			
			List<Map<String, Object>> data4 = BreakdownService.contact(param); //지시/연락사항
			if(data4.size() > 0) data4 = NullToString.nulltostring(data4);

			List<Map<String, Object>> data5 = BreakdownService.report(param); //보고사항
			if(data5.size() > 0) data5 = NullToString.nulltostring(data5);		
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data2)
			+ ",\"result3\":" + JsonUtil.getJsonArrayFromList(data3)
			+ ",\"result4\":" + JsonUtil.getJsonArrayFromList(data4)
			+ ",\"result5\":" + JsonUtil.getJsonArrayFromList(data5)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"size3\":" + data3.size()
			+ ",\"size4\":" + data4.size()
			+ ",\"size5\":" + data5.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//당직일지 삭제
	@RequestMapping(value="/shift/delete")
	public void shift_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {
			param.put("tb_name", "shift");
			BreakdownService.shift_delete(param);
			FileService.delete(param, "shift_file/");
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//당직일지보고서
	@RequestMapping(value="/shift/report")
	public void shift_report(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String group = "";
			String date = "";
			
			param.put("section", 1);
			List<Map<String, Object>> data1 = BreakdownService.shift_report(param); //조직명과 날짜 가져오기
			if(data1.size() > 0) {
				data1 = NullToString.nulltostring(data1);
				group = data1.get(0).get("og_name").toString() + "\"";
				date = data1.get(0).get("sh_date").toString() + "\"";
				for(String key : data1.get(0).keySet()) param.put(key, data1.get(0).get(key));
			}			

			param.put("section", 2);
			List<Map<String, Object>> data2 = BreakdownService.shift_report(param); //근무자
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);			
			
			param.put("section", 3);
			List<Map<String, Object>> data3 = BreakdownService.shift_report(param); //지시 및 연락사항
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);

			param.put("section", 4);
			List<Map<String, Object>> data4 = BreakdownService.shift_report(param); //고장 및 조치 현황 합계
			if(data4.size() > 0) data4 = NullToString.nulltostring(data4);		
			
			param.put("section", 5);
			List<Map<String, Object>> data5 = BreakdownService.shift_report(param); //고장 및 조치 현황 리스트
			if(data5.size() > 0) data5 = NullToString.nulltostring(data5);			
			
			param.put("section", 6);
			List<Map<String, Object>> data6 = BreakdownService.shift_report(param); //보고사항
			if(data6.size() > 0) data6 = NullToString.nulltostring(data6);

			param.put("section", 7);
			List<Map<String, Object>> data7 = BreakdownService.shift_report(param); //지사 리스트
			if(data7.size() > 0) data7 = NullToString.nulltostring(data7);	
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data2) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data3)
			+ ",\"result3\":" + JsonUtil.getJsonArrayFromList(data4)
			+ ",\"result4\":" + JsonUtil.getJsonArrayFromList(data5)
			+ ",\"result5\":" + JsonUtil.getJsonArrayFromList(data6)
			+ ",\"result6\":" + JsonUtil.getJsonArrayFromList(data7)
			+ ",\"size\":" + data2.size()
			+ ",\"size2\":" + data3.size()
			+ ",\"size3\":" + data4.size()
			+ ",\"size4\":" + data5.size()
			+ ",\"size5\":" + data6.size()
			+ ",\"size6\":" + data7.size()
			+ ",\"group\":\"" + group
			+ ",\"date\":\"" + date
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//고장통계 (설비별 가동률)
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
			
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			
			int quarter = Integer.parseInt(param.get("quarter").toString());
			
			param.put("section", 1);
			List<Map<String, Object>> data1 = BreakdownService.system_table(param); //설비 시퀀스 가져오기
			if(data1.size() > 0) { 
				data1 = NullToString.nulltostring(data1);
				param.put("system", data1);
			}else param.put("none_system", 1);	
			
			param.put("type", 1);
			param.put("section", 2);
			List<Map<String, Object>> data2 = BreakdownService.system_table(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);			
			for(Map<String, Object> list : data2) data.add(list); //합치기
			
			param.put("section", 3);
			List<Map<String, Object>> data3 = BreakdownService.system_table(param);
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);			
			for(Map<String, Object> list : data3) data.add(list); //합치기
			
			if(quarter == 0) { //전체
				for(int i = 0; i < 4; i++) {
					param.put("quarter", i+1);
					quarter =  i+1;			
					
					if(quarter == 1) {
						param.put("month", 1);
						param.put("date", param.get("year").toString() + "01" + "01");
					}
					else if(quarter == 2) {
						param.put("month", 4);
						param.put("date", param.get("year").toString() + "04" + "01");
					}
					else if(quarter == 3) {
						param.put("month", 7);
						param.put("date", param.get("year").toString() + "07" + "01");
					}
					else {
						param.put("month", 10);
						param.put("date", param.get("year").toString() + "10" + "01");
					}
					
					param.put("section", 4);
					List<Map<String, Object>> data4 = BreakdownService.system_table(param);
					if(data4.size() > 0) data4 = NullToString.nulltostring(data4);			
					for(Map<String, Object> list : data4) data.add(list); //합치기
					
					param.put("section", 5);
					List<Map<String, Object>> data5 = BreakdownService.system_table(param);
					if(data5.size() > 0) data5 = NullToString.nulltostring(data5);			
					for(Map<String, Object> list : data5) data.add(list); //합치기
					
					if(quarter == 1) {
						param.put("month", 2);
						param.put("date", param.get("year").toString() + "02" + "01");
					}
					else if(quarter == 2) {
						param.put("month", 5);
						param.put("date", param.get("year").toString() + "05" + "01");
					}
					else if(quarter == 3) {
						param.put("month", 8);
						param.put("date", param.get("year").toString() + "08" + "01");
					}
					else {
						param.put("month", 11);
						param.put("date", param.get("year").toString() + "11" + "01");
					}
					
					param.put("section", 4);
					List<Map<String, Object>> data6 = BreakdownService.system_table(param);
					if(data6.size() > 0) data6 = NullToString.nulltostring(data6);			
					for(Map<String, Object> list : data6) data.add(list); //합치기
					
					param.put("section", 5);
					List<Map<String, Object>> data7 = BreakdownService.system_table(param);
					if(data7.size() > 0) data7 = NullToString.nulltostring(data7);			
					for(Map<String, Object> list : data7) data.add(list); //합치기
					
					if(quarter == 1) {
						param.put("month", 3);
						param.put("date", param.get("year").toString() + "03" + "01");
					}
					else if(quarter == 2) {
						param.put("month", 6);
						param.put("date", param.get("year").toString() + "06" + "01");
					}
					else if(quarter == 3) {
						param.put("month", 9);
						param.put("date", param.get("year").toString() + "09" + "01");
					}
					else {
						param.put("month", 12);
						param.put("date", param.get("year").toString() + "12" + "01");
					}
					
					param.put("section", 4);
					List<Map<String, Object>> data8 = BreakdownService.system_table(param);
					if(data8.size() > 0) data8 = NullToString.nulltostring(data8);			
					for(Map<String, Object> list : data8) data.add(list); //합치기
					
					param.put("section", 5);
					List<Map<String, Object>> data9 = BreakdownService.system_table(param);
					if(data9.size() > 0) data9 = NullToString.nulltostring(data9);			
					for(Map<String, Object> list : data9) data.add(list); //합치기
					
					param.put("type", 1);
					param.put("section", 2);
					List<Map<String, Object>> data10 = BreakdownService.system_table(param);
					if(data10.size() > 0) data10 = NullToString.nulltostring(data10);			
					for(Map<String, Object> list : data10) data.add(list); //합치기
					
					param.put("section", 3);
					List<Map<String, Object>> data11 = BreakdownService.system_table(param);
					if(data11.size() > 0) data11 = NullToString.nulltostring(data11);			
					for(Map<String, Object> list : data11) data.add(list); //합치기
				}				
			}else { //분기별				
				param.put("type", 2);
				
				if(quarter == 1) {
					param.put("month", 1);
					param.put("date", param.get("year").toString() + "01" + "01");
				}
				else if(quarter == 2) {
					param.put("month", 4);
					param.put("date", param.get("year").toString() + "04" + "01");
				}
				else if(quarter == 3) {
					param.put("month", 7);
					param.put("date", param.get("year").toString() + "07" + "01");
				}
				else {
					param.put("month", 10);
					param.put("date", param.get("year").toString() + "10" + "01");
				}
				
				param.put("section", 4);
				List<Map<String, Object>> data4 = BreakdownService.system_table(param);
				if(data4.size() > 0) data4 = NullToString.nulltostring(data4);			
				for(Map<String, Object> list : data4) data.add(list); //합치기
				
				param.put("section", 5);
				List<Map<String, Object>> data5 = BreakdownService.system_table(param);
				if(data5.size() > 0) data5 = NullToString.nulltostring(data5);			
				for(Map<String, Object> list : data5) data.add(list); //합치기
				
				if(quarter == 1) {
					param.put("month", 2);
					param.put("date", param.get("year").toString() + "02" + "01");
				}
				else if(quarter == 2) {
					param.put("month", 5);
					param.put("date", param.get("year").toString() + "05" + "01");
				}
				else if(quarter == 3) {
					param.put("month", 8);
					param.put("date", param.get("year").toString() + "08" + "01");
				}
				else {
					param.put("month", 11);
					param.put("date", param.get("year").toString() + "11" + "01");
				}
				
				param.put("section", 4);
				List<Map<String, Object>> data6 = BreakdownService.system_table(param);
				if(data6.size() > 0) data6 = NullToString.nulltostring(data6);			
				for(Map<String, Object> list : data6) data.add(list); //합치기
				
				param.put("section", 5);
				List<Map<String, Object>> data7 = BreakdownService.system_table(param);
				if(data7.size() > 0) data7 = NullToString.nulltostring(data7);			
				for(Map<String, Object> list : data7) data.add(list); //합치기
				
				if(quarter == 1) {
					param.put("month", 3);
					param.put("date", param.get("year").toString() + "03" + "01");
				}
				else if(quarter == 2) {
					param.put("month", 6);
					param.put("date", param.get("year").toString() + "06" + "01");
				}
				else if(quarter == 3) {
					param.put("month", 9);
					param.put("date", param.get("year").toString() + "09" + "01");
				}
				else {
					param.put("month", 12);
					param.put("date", param.get("year").toString() + "12" + "01");
				}
				
				param.put("section", 4);
				List<Map<String, Object>> data8 = BreakdownService.system_table(param);
				if(data8.size() > 0) data8 = NullToString.nulltostring(data8);			
				for(Map<String, Object> list : data8) data.add(list); //합치기
				
				param.put("section", 5);
				List<Map<String, Object>> data9 = BreakdownService.system_table(param);
				if(data9.size() > 0) data9 = NullToString.nulltostring(data9);			
				for(Map<String, Object> list : data9) data.add(list); //합치기
				
				for(Map<String, Object> list : data2) data.add(list); //합치기
				for(Map<String, Object> list : data3) data.add(list); //합치기	
			}

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
	
	//고장통계 (설비별 가동률) 엑셀
		@RequestMapping(value="/excel")
		public void excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("설비별 가동률");

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
	            String[] header_title = {"월", "", "구분", "", "소계", "", "TCS", "", "축중기", "", "FTMS", "", "TTMS", "", "전송", ""};
	            String[] body_column = {"value1", "value2", "value3", "value4", "value5", "value6"};
				int[] header_col_start = {0, 2, 4, 6, 8, 10, 12, 14};
				int[] header_col_last = {1, 3, 5, 7, 9, 11, 13, 15};
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(headerStyle);
	                cell.setCellValue(header_title[i]);
	            }
	            
	            for(int i = 0; i < header_col_start.length; i++) sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, header_col_start[i], header_col_last[i])); 

	            if(request.getAttribute("auth") != null) {
					param.put("auth", request.getAttribute("auth"));
					param.put("auth_seq", request.getAttribute("auth_seq"));
				}
				
				List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
				
				int quarter = Integer.parseInt(param.get("quarter").toString());
				
				param.put("section", 1);
				List<Map<String, Object>> data1 = BreakdownService.system_table(param); //설비 시퀀스 가져오기
				if(data1.size() > 0) { 
					data1 = NullToString.nulltostring(data1);
					param.put("system", data1);
				}else param.put("none_system", 1);	
				
				param.put("type", 1);
				param.put("section", 2);
				List<Map<String, Object>> data2 = BreakdownService.system_table(param);
				if(data2.size() > 0) data2 = NullToString.nulltostring(data2);			
				for(Map<String, Object> list : data2) data.add(list); //합치기
				
				param.put("section", 3);
				List<Map<String, Object>> data3 = BreakdownService.system_table(param);
				if(data3.size() > 0) data3 = NullToString.nulltostring(data3);			
				for(Map<String, Object> list : data3) data.add(list); //합치기
				
				if(quarter == 0) { //전체
					for(int i = 0; i < 4; i++) {
						param.put("quarter", i+1);
						quarter =  i+1;			
						
						if(quarter == 1) {
							param.put("month", 1);
							param.put("date", param.get("year").toString() + "01" + "01");
						}
						else if(quarter == 2) {
							param.put("month", 4);
							param.put("date", param.get("year").toString() + "04" + "01");
						}
						else if(quarter == 3) {
							param.put("month", 7);
							param.put("date", param.get("year").toString() + "07" + "01");
						}
						else {
							param.put("month", 10);
							param.put("date", param.get("year").toString() + "10" + "01");
						}
						
						param.put("section", 4);
						List<Map<String, Object>> data4 = BreakdownService.system_table(param);
						if(data4.size() > 0) data4 = NullToString.nulltostring(data4);			
						for(Map<String, Object> list : data4) data.add(list); //합치기
						
						param.put("section", 5);
						List<Map<String, Object>> data5 = BreakdownService.system_table(param);
						if(data5.size() > 0) data5 = NullToString.nulltostring(data5);			
						for(Map<String, Object> list : data5) data.add(list); //합치기
						
						if(quarter == 1) {
							param.put("month", 2);
							param.put("date", param.get("year").toString() + "02" + "01");
						}
						else if(quarter == 2) {
							param.put("month", 5);
							param.put("date", param.get("year").toString() + "05" + "01");
						}
						else if(quarter == 3) {
							param.put("month", 8);
							param.put("date", param.get("year").toString() + "08" + "01");
						}
						else {
							param.put("month", 11);
							param.put("date", param.get("year").toString() + "11" + "01");
						}
						
						param.put("section", 4);
						List<Map<String, Object>> data6 = BreakdownService.system_table(param);
						if(data6.size() > 0) data6 = NullToString.nulltostring(data6);			
						for(Map<String, Object> list : data6) data.add(list); //합치기
						
						param.put("section", 5);
						List<Map<String, Object>> data7 = BreakdownService.system_table(param);
						if(data7.size() > 0) data7 = NullToString.nulltostring(data7);			
						for(Map<String, Object> list : data7) data.add(list); //합치기
						
						if(quarter == 1) {
							param.put("month", 3);
							param.put("date", param.get("year").toString() + "03" + "01");
						}
						else if(quarter == 2) {
							param.put("month", 6);
							param.put("date", param.get("year").toString() + "06" + "01");
						}
						else if(quarter == 3) {
							param.put("month", 9);
							param.put("date", param.get("year").toString() + "09" + "01");
						}
						else {
							param.put("month", 12);
							param.put("date", param.get("year").toString() + "12" + "01");
						}
						
						param.put("section", 4);
						List<Map<String, Object>> data8 = BreakdownService.system_table(param);
						if(data8.size() > 0) data8 = NullToString.nulltostring(data8);			
						for(Map<String, Object> list : data8) data.add(list); //합치기
						
						param.put("section", 5);
						List<Map<String, Object>> data9 = BreakdownService.system_table(param);
						if(data9.size() > 0) data9 = NullToString.nulltostring(data9);			
						for(Map<String, Object> list : data9) data.add(list); //합치기
						
						param.put("type", 1);
						param.put("section", 2);
						List<Map<String, Object>> data10 = BreakdownService.system_table(param);
						if(data10.size() > 0) data10 = NullToString.nulltostring(data10);			
						for(Map<String, Object> list : data10) data.add(list); //합치기
						
						param.put("section", 3);
						List<Map<String, Object>> data11 = BreakdownService.system_table(param);
						if(data11.size() > 0) data11 = NullToString.nulltostring(data11);			
						for(Map<String, Object> list : data11) data.add(list); //합치기
					}
					
					quarter = 0;
				}else { //분기별				
					param.put("type", 2);
					
					if(quarter == 1) {
						param.put("month", 1);
						param.put("date", param.get("year").toString() + "01" + "01");
					}
					else if(quarter == 2) {
						param.put("month", 4);
						param.put("date", param.get("year").toString() + "04" + "01");
					}
					else if(quarter == 3) {
						param.put("month", 7);
						param.put("date", param.get("year").toString() + "07" + "01");
					}
					else {
						param.put("month", 10);
						param.put("date", param.get("year").toString() + "10" + "01");
					}
					
					param.put("section", 4);
					List<Map<String, Object>> data4 = BreakdownService.system_table(param);
					if(data4.size() > 0) data4 = NullToString.nulltostring(data4);			
					for(Map<String, Object> list : data4) data.add(list); //합치기
					
					param.put("section", 5);
					List<Map<String, Object>> data5 = BreakdownService.system_table(param);
					if(data5.size() > 0) data5 = NullToString.nulltostring(data5);			
					for(Map<String, Object> list : data5) data.add(list); //합치기
					
					if(quarter == 1) {
						param.put("month", 2);
						param.put("date", param.get("year").toString() + "02" + "01");
					}
					else if(quarter == 2) {
						param.put("month", 5);
						param.put("date", param.get("year").toString() + "05" + "01");
					}
					else if(quarter == 3) {
						param.put("month", 8);
						param.put("date", param.get("year").toString() + "08" + "01");
					}
					else {
						param.put("month", 11);
						param.put("date", param.get("year").toString() + "11" + "01");
					}
					
					param.put("section", 4);
					List<Map<String, Object>> data6 = BreakdownService.system_table(param);
					if(data6.size() > 0) data6 = NullToString.nulltostring(data6);			
					for(Map<String, Object> list : data6) data.add(list); //합치기
					
					param.put("section", 5);
					List<Map<String, Object>> data7 = BreakdownService.system_table(param);
					if(data7.size() > 0) data7 = NullToString.nulltostring(data7);			
					for(Map<String, Object> list : data7) data.add(list); //합치기
					
					if(quarter == 1) {
						param.put("month", 3);
						param.put("date", param.get("year").toString() + "03" + "01");
					}
					else if(quarter == 2) {
						param.put("month", 6);
						param.put("date", param.get("year").toString() + "06" + "01");
					}
					else if(quarter == 3) {
						param.put("month", 9);
						param.put("date", param.get("year").toString() + "09" + "01");
					}
					else {
						param.put("month", 12);
						param.put("date", param.get("year").toString() + "12" + "01");
					}
					
					param.put("section", 4);
					List<Map<String, Object>> data8 = BreakdownService.system_table(param);
					if(data8.size() > 0) data8 = NullToString.nulltostring(data8);			
					for(Map<String, Object> list : data8) data.add(list); //합치기
					
					param.put("section", 5);
					List<Map<String, Object>> data9 = BreakdownService.system_table(param);
					if(data9.size() > 0) data9 = NullToString.nulltostring(data9);			
					for(Map<String, Object> list : data9) data.add(list); //합치기
					
					for(Map<String, Object> list : data2) data.add(list); //합치기
					for(Map<String, Object> list : data3) data.add(list); //합치기	
				}
				
				//바디
				if(data.size() > 0) {
					String[] month_title = new String[data.size()];
					String[] row_title = {"고장시간(분)", "고장건수", "고장율", "가동율"};
					
					for(int i = 0; i < month_title.length; i++) {
						switch(quarter) {
						case 1 : 
							if(i == 0) month_title[i] = "종합";
							else if(i == 4) month_title[i] = "1월";
							else if(i == 8) month_title[i] = "2월";
							else if(i == 12) month_title[i] = "3월";
							else if(i == 16) month_title[i] = "1/4분기";
							else month_title[i] = "";
							break;
						case 2 : 
							if(i == 0) month_title[i] = "종합";
							else if(i == 4) month_title[i] = "4월";
							else if(i == 8) month_title[i] = "5월";
							else if(i == 12) month_title[i] = "6월";
							else if(i == 16) month_title[i] = "2/4분기";
							else month_title[i] = "";
							break;
						case 3 : 
							if(i == 0) month_title[i] = "종합";
							else if(i == 4) month_title[i] = "7월";
							else if(i == 8) month_title[i] = "8월";
							else if(i == 12) month_title[i] = "9월";
							else if(i == 16) month_title[i] = "3/4분기";
							else month_title[i] = "";
							break;
						case 4 : 
							if(i == 0) month_title[i] = "종합";
							else if(i == 4) month_title[i] = "10월";
							else if(i == 8) month_title[i] = "11월";
							else if(i == 12) month_title[i] = "12월";
							else if(i == 16) month_title[i] = "4/4분기";
							else month_title[i] = "";
							break;
						default : 
							if(i == 0) month_title[i] = "종합";
							else if(i == 4) month_title[i] = "1월";
							else if(i == 8) month_title[i] = "2월";
							else if(i == 12) month_title[i] = "3월";
							else if(i == 16) month_title[i] = "1/4분기";
							else if(i == 20) month_title[i] = "4월";
							else if(i == 24) month_title[i] = "5월";
							else if(i == 28) month_title[i] = "6월";
							else if(i == 32) month_title[i] = "2/4분기";
							else if(i == 36) month_title[i] = "7월";
							else if(i == 40) month_title[i] = "8월";
							else if(i == 44) month_title[i] = "9월";
							else if(i == 48) month_title[i] = "3/4분기";
							else if(i == 52) month_title[i] = "10월";
							else if(i == 56) month_title[i] = "11월";
							else if(i == 60) month_title[i] = "12월";
							else if(i == 64) month_title[i] = "4/4분기";
							else month_title[i] = "";
							break;
						}
					}					
					
					int idx2 = 0;
					int idx3 = 0;
					
					for(Map<String, Object> list : data) {
						excelRow = sheet.createRow(rowNum++);
						int idx = 0;						
						if(idx2 == 4) idx2 = 0; 
						
			            for(int i = 0; i < header_title.length; i++) {
			            	cell = excelRow.createCell(i);
			                cell.setCellStyle(bodyStyle);
			                if(i == 0) cell.setCellValue(month_title[idx3++]);
			                else if(i == 2) cell.setCellValue(row_title[idx2++]);
			                else if(i%2 == 0) cell.setCellValue(list.get(body_column[idx++]).toString());		                	                
			            }
			            
			            for(int i = 0; i < header_col_start.length; i++) sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, header_col_start[i], header_col_last[i])); 
					}				
				}
	                        
	            String fileName = "고장통계(설비별 가동률)_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//고장접수관리-------------->수정이 필요함!!!!!
				@RequestMapping(value = "/detail/excel")
				public void detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request,
						HttpServletResponse response) throws Exception {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/xlsx");

					try {
						Date nowDate = new Date();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
						String strNowDate = simpleDateFormat.format(nowDate);

						XSSFWorkbook wb = new XSSFWorkbook();
						XSSFSheet sheet = wb.createSheet("고장접수관리 상세정보");
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

						List<Map<String, Object>> list = BreakdownService.select_breakdown_excel(param);
						List<Map<String, Object>> list2 = BreakdownService.select_breakdown_worker_excel(param);
						List<Map<String, Object>> list3 = BreakdownService.select_breakdown_compose_excel(param);
						List<Map<String, Object>> list4 = BreakdownService.select_breakdown_storage_excel(param);
						List<Map<String, Object>> list5 = BreakdownService.select_breakdown_spare_excel(param);

						String[] title = { "고장번호", "기기명", "실고장일시", "조치시작일시", "고장시간", "작업구분", "처리내역", "특이사항" };
						String[] title2 = { "일련번호", "위치", "접수일시", "조치완료일시", "수리비", "고장기준", "접수자", "" };
						String[] data = { "bk_code", "dc_seq", "bk_faulty", "bk_start", "bk_hour", "bk_work", "bk_process", "bk_unique" };
						String[] data2 = { "bk_number", "dc_location", "bk_receipt", "bk_finish", "bk_cost", "bk_standard", "per_name", "" };
						
						if(list.size() > 0) {
							list = NullToString.nulltostring(list);
							
							int colCnt = 8;
							int rowCnt = 8;
							
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
										if(i == 7) cell.setCellValue(data2[i]);
										else cell.setCellValue(list.get(0).get(data2[i]).toString());
										break;
									default : 
										cell.setCellStyle(tableCellStyle);
										break;
									}
								}
								
								sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 1, 3));
								sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 5, 7));
							}
						}
						
						for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가
						
						String[] title3 = { "작업자", "비고", "", "", "", "", "", "" };
						String[] column3 = {"per_seq", "bw_note"};
						
						for(int i=0; i<title3.length; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(title3[i]);
						}
						
						if(list2.size() > 0) {
							list2 = NullToString.nulltostring(list2);
							
							for(Map<String, Object> datas : list2) {
								row = sheet.createRow(rowNum++);
								
								for(int i = 0; i < title3.length; i++) {
									cell = row.createCell(i);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(datas.get(column3[i]).toString());
								}
							}
						}else {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title3.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue("표시할 데이터가 없습니다.");
							}
							
							sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 0, 7));
						}
						
						for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가
						
						String[] title4 = { "구성부", "", "현상", "원인", "조치결과", "비고", "", "" };
						String[] column4 = {"bc_compose", "bc_status", "bc_cause", "bc_result", "bc_note"};
						
						for(int i=0; i<title4.length; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(title4[i]);
						}
						
						if(list3.size() > 0) {
							list3 = NullToString.nulltostring(list3);
							
							for(Map<String, Object> datas : list3) {
								row = sheet.createRow(rowNum++);
								
								for(int i = 0; i < list3.size(); i++) {
									
									cell.setCellStyle(tableCellStyle);								
									switch(i) {
									case 0 : 
										cell.setCellValue(datas.get(column4[i]).toString());
										break;
									case 2 : 
										cell.setCellValue(datas.get(column4[i]).toString());
										break;
									case 3 : 
										cell.setCellValue(datas.get(column4[i]).toString());
										break;
									case 4 : 
										cell.setCellValue(datas.get(column4[i]).toString());
										break;
									case 5 : 
										cell.setCellValue(datas.get(column4[i]).toString());
										break;
									default : 
										break;
									}
									
									sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 0, 1));
									sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 5, 7));
								}
							}						
						}else {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title4.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue("표시할 데이터가 없습니다.");
							}

							sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 0, 7));
						}
						
						/*
						//구성부
						String[] title3 = { "구성부", "원인", "비고" };
						String[] title4 = { "현상", "조치결과", "" };
						
						for (int i = 0; i < list3.size(); i++) {
						String[] data3 = { list3.get(i).get("bc_compose").toString(), list3.get(i).get("bc_cause").toString(),
								list3.get(i).get("bc_note").toString() };
						String[] data4 = { list3.get(i).get("bc_status").toString(), list3.get(i).get("bc_result").toString(), "" };
						
						for(int j=0; j<title3.length; j++) {
							row = sheet.createRow(rowNum++);
							cell = row.createCell(0);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(title3[j]);

							cell = row.createCell(1);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(data3[j]);

							cell = row.createCell(2);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(title4[j]);

							cell = row.createCell(3);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(data4[j]);
							}
						}
						
						sheet.createRow(rowNum++);
						row = sheet.createRow(rowNum++); // 빈행 추가
						
						//저장내역
						String[] title5 = { "구성부", "Unit Count(구)", "저장품명", "단가", "비고" };
						String[] title6 = { "처리구분", "Unit Count(신)", "수량", "입출고구분", "" };
						
						for (int i = 0; i < list4.size(); i++) {
							String[] data5 = { list4.get(i).get("bs_seq1").toString(), list4.get(i).get("bs_old1").toString(),
									list4.get(i).get("pt_seq1").toString(), list4.get(i).get("bs_price1").toString(),
									list4.get(i).get("bs_note1").toString() };
							String[] data6 = { list4.get(i).get("bs_process1").toString(), list4.get(i).get("bs_new1").toString(),
									list4.get(i).get("bs_count1").toString(), "", // 기획서에 입력동작 파악불가
									"" };
						
						for(int j=0; j<title5.length; j++) {
							row = sheet.createRow(rowNum++);
							cell = row.createCell(0);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(title5[j]);

							cell = row.createCell(1);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(data5[j]);

							cell = row.createCell(2);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(title6[j]);

							cell = row.createCell(3);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(data6[j]);
							}
						}
						
						//KHC예비품
						for (int i = 0; i < list4.size(); i++) {
							String[] data7 = { list5.get(0).get("bs_seq2").toString(), list5.get(0).get("bs_old2").toString(),
									list5.get(0).get("pt_seq2").toString(), list5.get(0).get("bs_price2").toString(),
									list5.get(0).get("bs_note2").toString(), };

							String[] data8 = { list5.get(0).get("bs_process2").toString(), list5.get(0).get("bs_new2").toString(),
									list5.get(0).get("bs_count2").toString(), "", "" };
						
						for(int j=0; j<title5.length; j++) {
							row = sheet.createRow(rowNum++);
							cell = row.createCell(0);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(title5[j]);

							cell = row.createCell(1);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(data7[j]);

							cell = row.createCell(2);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(title6[j]);

							cell = row.createCell(3);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(data8[j]);
							}
						}*/
						
						String fileName = "고장관리(고장접수관리)_" + strNowDate + ".xlsx";
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
				
				// 고장조치관리 기본정보
					@RequestMapping(value = "/action/detail/excel")
					public void action_detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						response.setContentType("application/xlsx");

						try {
							Date nowDate = new Date();
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
							String strNowDate = simpleDateFormat.format(nowDate);

							XSSFWorkbook wb = new XSSFWorkbook();
							XSSFSheet sheet = wb.createSheet("고장조치관리");
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

							for (int i = 0; i < 10; i++) {
								sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + (short) 2600);// 컬럼 넓이 조절
							}

							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 정렬
							cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
							cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 얘를 꼭 해야함
							cellStyle.setBorderTop((short) 1); // 테두리 위쪽
							cellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
							cellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
							cellStyle.setBorderRight((short) 1); // 테두리 오른쪽

							// 셀병합
							sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 3)); // 첫행, 마지막행, 첫열, 마지막열 병합

							// 테이블 스타일 설정
							CellStyle tableCellStyle = wb.createCellStyle();
							tableCellStyle.setBorderTop((short) 1); // 테두리 위쪽
							tableCellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
							tableCellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
							tableCellStyle.setBorderRight((short) 1); // 테두리 오른쪽
							tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

							// Header
							row = sheet.createRow(rowNum++);
							cell = row.createCell((short) 0);
							cell.setCellStyle(headStyle);
							cell.setCellValue("고장조치관리");

							sheet.createRow(rowNum++);
							row = sheet.createRow(rowNum++); // 빈행 추가

							List<Map<String, Object>> list = BreakdownService.select_action_excel(param);
							List<Map<String, Object>> list2 = BreakdownService.select_action_date_excel(param);
							
							if(list.size() > 0) {
								list = NullToString.nulltostring(list);
								list2 = NullToString.nulltostring(list2);
							
							
							String[] title = {"사업단","근무일자","지사","설비구분","고장발생시각","고장내역","처리구분","조치시작시간","조치내역","미조치사유"};
							
							String[] title2 = {"","구분","장소","기기명","고장접수시각","조치구분","처리자","조치완료시각","사용부품","수리자"};
							
							String[] data = {
									list.get(0).get("og_seq").toString(),
									list.get(0).get("at_date").toString(),
									list.get(0).get("brc_seq").toString(),
									list.get(0).get("sys_seq").toString(),
									list.get(0).get("at_occur").toString(),
									list.get(0).get("at_history").toString(),
									list.get(0).get("prc_seq").toString(),
									list.get(0).get("at_start").toString(),
									list.get(0).get("at_content").toString(),
									list.get(0).get("at_reason").toString()
							};
							
							String[] data2 = {
									"",
									list.get(0).get("at_division").toString(),
									list.get(0).get("at_place").toString(),
									list.get(0).get("at_device").toString(),
									list.get(0).get("at_receipt").toString(),
									list.get(0).get("at_action").toString(),
									list.get(0).get("at_manager").toString(),
									list.get(0).get("at_finish").toString(),
									list.get(0).get("at_part").toString(),
									list.get(0).get("at_repair").toString(),
							};
							
							for (int i = 0; i < title.length; i++) { // 타이틀의 길이만큼 0번열에 추가
								row = sheet.createRow(rowNum++);
								cell = row.createCell(0);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(title[i]);

								cell = row.createCell(1);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(data[i]);

								cell = row.createCell(2);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(title2[i]);

								cell = row.createCell(3);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(data2[i]);
							}

							sheet.createRow(rowNum++);
							row = sheet.createRow(rowNum++); // 빈행 추가
							
							String[] title3 = {"조치구분","지사","설비","장소","기기명","접수시간","고장내역","조치내역","작성자"};
							
							String[] data3 = {
									list2.get(0).get("at_action").toString(),
									list2.get(0).get("og_name").toString(),
									list2.get(0).get("sys_seq").toString(),
									list2.get(0).get("at_place").toString(),
									list2.get(0).get("at_device").toString(),
									list2.get(0).get("at_receipt").toString(),
									list2.get(0).get("at_history").toString(),
									list2.get(0).get("at_content").toString(),
									list2.get(0).get("per_name").toString(),
							};
							
							for (int i = 0; i < title3.length; i++) { // 타이틀의 길이만큼 0번열에 추가
								cell = row.createCell(i);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(title3[i]);
							}
							
							row = sheet.createRow(rowNum++);
							for (int i = 0; i < title3.length; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(data3[i]);
							}
							}
							
							String fileName = "고장관리(고장조치관리)_" + strNowDate + ".xlsx";
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
					
					//당직일지 엑셀 
					@RequestMapping(value = "/shift/detail/excel")
					public void shift_detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						response.setContentType("application/xlsx");

						try {
							Date nowDate = new Date();
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
							String strNowDate = simpleDateFormat.format(nowDate);

							XSSFWorkbook wb = new XSSFWorkbook();
							XSSFSheet sheet = wb.createSheet("당직일지");
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

							for (int i = 0; i < 10; i++) {
								sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + (short) 2600);// 컬럼 넓이 조절
							}

							cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 정렬
							cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
							cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 얘를 꼭 해야함
							cellStyle.setBorderTop((short) 1); // 테두리 위쪽
							cellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
							cellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
							cellStyle.setBorderRight((short) 1); // 테두리 오른쪽

							// 셀병합
							sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 3)); // 첫행, 마지막행, 첫열, 마지막열 병합

							// 테이블 스타일 설정
							CellStyle tableCellStyle = wb.createCellStyle();
							tableCellStyle.setBorderTop((short) 1); // 테두리 위쪽
							tableCellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
							tableCellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
							tableCellStyle.setBorderRight((short) 1); // 테두리 오른쪽
							tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

							// Header
							row = sheet.createRow(rowNum++);
							cell = row.createCell((short) 0);
							cell.setCellStyle(headStyle);
							cell.setCellValue("당직일지");

							sheet.createRow(rowNum++);
							row = sheet.createRow(rowNum++); // 빈행 추가

							List<Map<String, Object>> list = BreakdownService.select_shift_excel(param);
							List<Map<String, Object>> list2 = BreakdownService.select_worker_excel(param);
							List<Map<String, Object>> list3 = BreakdownService.select_contact_excel(param);
							List<Map<String, Object>> list4 = BreakdownService.select_report_excel(param);
							
							if(list.size() > 0) {
								list = NullToString.nulltostring(list);
							
							
							String[] title = {"사업단","일자",""};
							String[] title2 = {"당직구분","특이사항",""};
							String[] title3 = {"날씨","기온","풍향","풍속","습도"};
							
							String[] data = {
									list.get(0).get("og_seq").toString(),
									list.get(0).get("sh_date").toString(),
									""
							};
							String[] data2 = {
									list.get(0).get("sh_type").toString(),
									list.get(0).get("sh_etc").toString(),
									""
							};
							
							String[] data3 = {
									list.get(0).get("sh_weather").toString(),
									list.get(0).get("sh_temp").toString(),
									list.get(0).get("sh_wind").toString(),
									list.get(0).get("sh_speed").toString(),
									list.get(0).get("sh_humid").toString(),
							};
							
							for (int i = 0; i < title.length; i++) { // 타이틀의 길이만큼 0번열에 추가
								row = sheet.createRow(rowNum++);
								cell = row.createCell(0);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(title[i]);

								cell = row.createCell(1);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(data[i]);

								cell = row.createCell(2);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(title2[i]);

								cell = row.createCell(3);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(data2[i]);
							}
							
							
							for (int i = 0; i < title3.length; i++) { // 타이틀의 길이만큼 0번열에 추가
								cell = row.createCell(i);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(title3[i]);
							}
							
							row = sheet.createRow(rowNum++);
							for (int i = 0; i < title3.length; i++) {
							cell = row.createCell(i);
							cell.setCellStyle(tableCellStyle);
							cell.setCellValue(data3[i]);
							}
						}
							sheet.createRow(rowNum++);
							row = sheet.createRow(rowNum++); // 빈행 추가
							
							
							String[] title4 = {"순번","근무형태","근무장소","소속","직급","성명","근무시작시간",
									"근무종료시간","근무내용","비고"};
							String[] column1 = {"wk_seq", "sh_seq", "wk_place", "wk_team", "wk_rank","per_seq","wk_start",
									"wk_end","wk_content","wk_note"};
							
							row = sheet.createRow(rowNum++);
							
							//헤더
							for (int i = 0; i < title4.length; i++) { 
								cell = row.createCell(i);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(title4[i]);
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
								sheet.addMergedRegion(new CellRangeAddress(10, 10, 0, 10));
								for(int i = 0; i < column1.length; i++) {
									cell = row.createCell(i);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue("존재하는 데이터가 없습니다.");
								}
							}
							
							
							sheet.createRow(rowNum++);
							row = sheet.createRow(rowNum++); // 빈행 추가
							
							String[] title5 = {"지시 및 연락처","접수시간","접수자","지시내용","조치결과"};
							String[] column2 = {"ct_phone", "ct_date", "per_seq", "ct_content", "ct_result"};
							
							row = sheet.createRow(rowNum++);
							
							//헤더
							for (int i = 0; i < title5.length; i++) { 
								cell = row.createCell(i);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(title5[i]);
							}
							
							//바디
							if(list3.size() > 0) {
								list3 = NullToString.nulltostring(list3);					
								
								for(Map<String, Object> datas : list3) {
									row = sheet.createRow(rowNum++);
									
									for(int i = 0; i < column2.length; i++) {
										cell = row.createCell(i);
										cell.setCellStyle(tableCellStyle);
										cell.setCellValue(datas.get(column2[i]).toString());
									}
								}					
							}else {
								row = sheet.createRow(rowNum++);
								sheet.addMergedRegion(new CellRangeAddress(14, 14, 0, 4));
								for(int i = 0; i < column2.length; i++) {
									cell = row.createCell(i);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue("존재하는 데이터가 없습니다.");
								}
							}
							
							
							sheet.createRow(rowNum++);
							row = sheet.createRow(rowNum++); // 빈행 추가
							
							String[] title6 = {"보고시간","내용","통화자"};
							String[] column3 = {"rp_hour", "rp_content", "rp_caller"};
							
							row = sheet.createRow(rowNum++);
							
							//헤더
							for (int i = 0; i < title6.length; i++) { 
								cell = row.createCell(i);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(title6[i]);
							}
							
							//바디
							if(list4.size() > 0) {
								list4 = NullToString.nulltostring(list4);					
								
								for(Map<String, Object> datas : list4) {
									row = sheet.createRow(rowNum++);
									
									for(int i = 0; i < column3.length; i++) {
										cell = row.createCell(i);
										cell.setCellStyle(tableCellStyle);
										cell.setCellValue(datas.get(column3[i]).toString());
									}
								}					
							}else {
								row = sheet.createRow(rowNum++);
								sheet.addMergedRegion(new CellRangeAddress(18, 18, 0, 4));
								for(int i = 0; i < column3.length; i++) {
									cell = row.createCell(i);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue("존재하는 데이터가 없습니다.");
								}
							}
							
							
							String fileName = "고장관리(당직일지)_" + strNowDate + ".xlsx";
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
