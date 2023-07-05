package com.egov.namul.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egov.namul.service.CallService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/call")
public class CallController {
	
	@Resource(name = "CallService")
	private CallService CallService;
	
	//근무관리 리스트
	@RequestMapping(value="/work/list")
	public void work_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			if(param.get("date") != null && !"".equals(param.get("date"))) {
				int per = Integer.parseInt(session.getAttribute("per_seq").toString());
				param.put("seq", per);
			}
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
			if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());	
				
			int total = CallService.work_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = CallService.work_list(param);			
				
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
	
	//근무관리 추가/수정
	@RequestMapping(value = "/work/edit")
	public void work_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {
			String[] wkd_seq = request.getParameterValues("wkd_seq[]");
			String[] og_seq = request.getParameterValues("og_seq[]");
			String[] per_seq2 = request.getParameterValues("per_seq2[]");
			String[] wkd_start = request.getParameterValues("wkd_start[]");
			String[] wkd_work = request.getParameterValues("wkd_work[]");
			String[] wkd_end = request.getParameterValues("wkd_end[]");
			String[] wkd_finish = request.getParameterValues("wkd_finish[]");
			String[] wkd_total = request.getParameterValues("wkd_total[]");
			String[] wkd_amount = request.getParameterValues("wkd_amount[]");
			
			multi.clear();
			
			if(wkd_seq != null) {
				for(int i = 0; i < wkd_seq.length; i++) {
					int cnt = 0;

					if(og_seq[i].isEmpty()) cnt++;
					if(per_seq2[i].isEmpty()) cnt++;
					if(wkd_start[i].isEmpty()) cnt++;
					if(wkd_work[i].isEmpty()) cnt++;
					if(wkd_end[i].isEmpty()) cnt++;
					if(wkd_finish[i].isEmpty()) cnt++;
					if(wkd_total[i].isEmpty()) cnt++;
					if(wkd_amount[i].isEmpty()) cnt++;
					
					if(cnt < 8) {
						if("".equals(wkd_seq[i])) multi.add("wkd_seq", 0);
						else multi.add("wkd_seq", wkd_seq[i]);
						
						if("".equals(og_seq[i])) multi.add("og_seq", 0);
						else multi.add("og_seq", og_seq[i]);
						
						if("".equals(per_seq2[i])) multi.add("per_seq2", 0);
						else multi.add("per_seq2", per_seq2[i]);
						
						multi.add("wkd_start", wkd_start[i]);
						multi.add("wkd_work", wkd_work[i]);
						multi.add("wkd_end", wkd_end[i]);
						multi.add("wkd_finish", wkd_finish[i]);
						
						if("".equals(wkd_total[i])) multi.add("wkd_total", 0);
						else multi.add("wkd_total", wkd_total[i]);
						
						if("".equals(wkd_amount[i])) multi.add("wkd_amount", 0);
						else multi.add("wkd_amount", wkd_amount[i]);
					}					
				}
				
				param.put("multi", multi);
				if(multi.get("wkd_seq") != null) param.put("wkd_seq", 2);
			}
			
			CallService.work_edit(param);				
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//근무관리 상세정보
	@RequestMapping(value="/work/detail")
	public void work_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = CallService.work(param); //근무관리
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);

			List<Map<String, Object>> data2 = CallService.work_detail(param); //근무관리 상세정보
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
	
	//근무관리 삭제
	@RequestMapping(value="/work/delete")
	public void work_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			CallService.work_delete(param);			
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//근무관리보고서
	@RequestMapping(value="/work/report")
	public void work_report(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 1);
			List<Map<String, Object>> data1 = CallService.work_report(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);

			param.put("section", 2);
			List<Map<String, Object>> data2 = CallService.work_report(param); 
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
	
	//연장근무관리 리스트
	@RequestMapping(value="/overtime/list")
	public void overtime_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
				
			int total = CallService.overtime_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = CallService.overtime_list(param);			
				
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
	
	//연장근무관리 추가/수정
	@RequestMapping(value = "/overtime/edit")
	public void overtime_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {
			String[] od_seq = request.getParameterValues("od_seq");
			String[] per_seq = request.getParameterValues("per_seq");
			String[] od_start = request.getParameterValues("od_start");
			String[] od_end = request.getParameterValues("od_end");
			String[] od_history = request.getParameterValues("od_history");
			
			if(od_seq != null) {
				multi.clear();
				
				for(int i = 0; i < od_seq.length; i++) {
					int cnt = 0;

					if(per_seq[i].isEmpty()) cnt++;
					if(od_start[i].isEmpty()) cnt++;
					if(od_end[i].isEmpty()) cnt++;
					if(od_history[i].isEmpty()) cnt++;

					if(cnt < 4) {
						if("".equals(od_seq[i]))	multi.add("od_seq", 0);
						else multi.add("od_seq", od_seq[i]);
						
						if("".equals(per_seq[i]))	multi.add("per_seq", 0);
						else multi.add("per_seq", per_seq[i]);
						
						multi.add("od_start", od_start[i]);
						multi.add("od_end", od_end[i]);					
						multi.add("od_history", od_history[i]);
					}					
				}
				
				param.put("multi", multi);
				if(multi.get("od_seq") != null) param.put("is_od_seq", 1);	
			}
			
			CallService.overtime_edit(param);				
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//연장근무관리 상세정보
	@RequestMapping(value="/overtime/detail")
	public void overtime_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = CallService.overtime(param); //근무관리
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);

			List<Map<String, Object>> data2 = CallService.overtime_detail(param); //근무관리 상세정보
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
	
	//연장근무관리 삭제
	@RequestMapping(value="/overtime/delete")
	public void overtime_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			CallService.overtime_delete(param);			
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//연장근무관리 처리결과 변경
	@RequestMapping(value = "/status/edit")
	public void status_edit(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {
			int auth = Integer.parseInt(session.getAttribute("ag_seq").toString());
			
			if(auth == 3) { //일반사용자는 처리 불가
				jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Edit Access Denied\"}");
				return;
			}
			
			CallService.status_update(param);			
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//연장근무관리 보고서
	@RequestMapping(value="/overtime/report")
	public void overtime_report(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 1);
			List<Map<String, Object>> data1 = CallService.overtime_report(param); //결원사항
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);

			param.put("section", 2);
			List<Map<String, Object>> data2 = CallService.overtime_report(param); //연장근무사항 총계
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			param.put("section", 3);
			List<Map<String, Object>> data3 = CallService.overtime_report(param); //연장근무사항 리스트
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
	
	//콜근무통계 (콜근무실적)
	@RequestMapping(value="/revenue/table")
	public void revenue_table(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
					
			int total = CallService.revenue_total(param);
			int startNum = (page - 1) * row;
						
			param.put("start_num", startNum);
			param.put("row_num", row);
					
			List<Map<String, Object>> data = CallService.revenue_list(param);			
					
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
	
	//콜근무통계 (월집계표)
	@RequestMapping(value="/month/table")
	public void month_table(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			List<Map<String, Object>> data1 = CallService.month_list(param); //작업구분 시퀀스 기져오기
			if(data1.size() > 0) {
				data1 = NullToString.nulltostring(data1);
				param.put("div", data1);
			}else param.put("none_div", 1);

			param.put("section", 2);
			List<Map<String, Object>> data2 = CallService.month_list(param); //집계표 계
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			param.put("section", 3);
			List<Map<String, Object>> data3 = CallService.month_list(param); //집계표
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data2) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data3)
			+ ",\"size\":" + data2.size()
			+ ",\"size2\":" + data3.size()
			+ ",\"code\":1}");			
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//연장근무일지
		@RequestMapping(value="/overtime/excel")
		public void overtime_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {		
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("콜근무실적");

		        Row excelRow = null;
	            Cell cell = null;
	            int rowNum = 0;	            
	         
	            //헤더 스타일
	            CellStyle titleStyle = workbook.createCellStyle();		           
	            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
	            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            
	            CellStyle titleStyle2 = workbook.createCellStyle();		           
	            titleStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	            titleStyle2.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
	            
	            //바디 스타일
	            CellStyle bodyStyle = workbook.createCellStyle();
	            bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		        
	            //헤더1
	            String[] header_title_01 = {"소속", "", "성명", "", "결원기간", "", "", "", "결원사유", "", "", "", "결원시간", "", "관련근거", "", "", ""};
	            String[] header_title_02 = {"소속", "", "성명", "", "근무일자", "", "근무시간", "", "", "", "총 시간", "", "근무내역", "", "", "", "", ""};
	            String[] column01 = {"check_name", "per_name", "ot_date", "ot_reason", "ot_hour", "ot_relate"};
	            String[] column02 = {"og_name", "per_name", "od_date", "hour", "od_hour", "od_history"};
	            int[] headerStart01 = {0, 2, 4, 8, 12, 14,};
	            int[] headerLast01 = {1, 3, 7, 11, 13, 17};
	            int[] headerStart02 = {0, 2, 4, 6, 10, 12,};
	            int[] headerLast02 = {1, 3, 5, 9, 11, 17};
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            cell = excelRow.createCell(0);
	            cell.setCellStyle(titleStyle2);
	            cell.setCellValue("1.결원사항");
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title_01.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(titleStyle);
	                cell.setCellValue(header_title_01[i]);
	            }

	            for(int i = 0; i < headerLast01.length; i++)sheet.addMergedRegion(new CellRangeAddress( 1, 1, headerStart01[i], headerLast01[i]));
	           
	            //바디1
	            param.put("section", 1);
				List<Map<String, Object>> data1 = CallService.overtime_report_excel(param); //결원사항
				if(data1.size() > 0) {
					data1 = NullToString.nulltostring(data1);				
					excelRow = sheet.createRow(rowNum++);
					
					for(int i = 0; i < header_title_01.length; i++) {
		            	cell = excelRow.createCell(i);
		                cell.setCellStyle(bodyStyle);
		                
		                for(int j = 0; j < headerStart01.length; j++) {
		                	if(i == headerStart01[j]) cell.setCellValue(data1.get(0).get(column01[j]).toString());
		                }
		            }
					
					 for(int i = 0; i < headerLast01.length; i++)sheet.addMergedRegion(new CellRangeAddress( 2, 2, headerStart01[i], headerLast01[i]));
				}
				
				for(int i = 0; i < 2; i++)  excelRow = sheet.createRow(rowNum++);
				
				cell = excelRow.createCell(0);
	            cell.setCellStyle(titleStyle2);
	            cell.setCellValue("2.연장근무사항");
				
				excelRow = sheet.createRow(rowNum++);
				
				for(int i = 0; i < header_title_02.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(titleStyle);
	                cell.setCellValue(header_title_02[i]);
	            }
				
				for(int i = 0; i < headerLast02.length; i++)sheet.addMergedRegion(new CellRangeAddress( 5, 5, headerStart02[i], headerLast02[i]));

				param.put("section", 2);
				List<Map<String, Object>> data2 = CallService.overtime_report_excel(param); //연장근무사항 총계
				if(data2.size() > 0) {
					data2 = NullToString.nulltostring(data2);
					excelRow = sheet.createRow(rowNum++);
					
					for(int i = 0; i < header_title_02.length; i++) {
		            	cell = excelRow.createCell(i);
		                cell.setCellStyle(bodyStyle);
		                
		                if(i == 0) cell.setCellValue("총계");
		                else if(i == 10) cell.setCellValue(data2.get(0).get("od_total").toString());
		                else cell.setCellValue("");
		            }
				}
				
				param.put("section", 3);
				List<Map<String, Object>> data3 = CallService.overtime_report_excel(param); //연장근무사항 리스트
				if(data3.size() > 0) {
					data3 = NullToString.nulltostring(data3);				
					
					for(Map<String, Object> list : data3) {
						excelRow = sheet.createRow(rowNum++);
						
						for(int i = 0; i < header_title_02.length; i++) {
			            	cell = excelRow.createCell(i);
			                cell.setCellStyle(bodyStyle);
			                
			                for(int j = 0; j < headerStart01.length; j++) {
			                	if(i == headerStart02[j]) cell.setCellValue(list.get(column02[j]).toString());
			                }
			            }
					}
				}
	            
				for(int i = 6; i < data3.size() + 7; i++) {
					for(int j = 0; j < headerLast02.length; j++)sheet.addMergedRegion(new CellRangeAddress( i, i, headerStart02[j], headerLast02[j]));
				}
				
				
		        String fileName = "콜근무실적_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//콜근무통계 엑셀 (콜근무실적)
		@RequestMapping(value="/revenue/excel")
		public void revenue_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {		
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("콜근무실적");

		        Row excelRow = null;
	            Cell cell = null;
	            int rowNum = 0;	            
	         
	            //헤더 스타일
	            CellStyle titleStyle = workbook.createCellStyle();		           
	            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
	            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            
	            //바디 스타일
	            CellStyle bodyStyle = workbook.createCellStyle();
	            bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		        
	            //헤더
	            String[] header_title = {"근무일자", "", "팀명", "", "", "", "작업구분", "", "성명", "", "시작시간", "", "종료시간", "", "근무시간", "", "지급액", "", "설비명", "", "작업내역", "", "", "", "", "", "", ""};
	            String[] column = {"wkd_date", "wrk_group", "wd_name", "per_name", "wkd_start", "wkd_finish", "wkd_hour", "wkd_amount", "sys_name", "wrk_work"};
	            int[] headerStart = {0, 2, 6, 8, 10, 12, 14, 16, 18, 20};
	            int[] headerLast = {1, 5, 7, 9, 11, 13, 15, 17, 19, 27};
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(titleStyle);
	                cell.setCellValue(header_title[i]);
	            }

	            for(int i = 0; i < headerLast.length; i++)sheet.addMergedRegion(new CellRangeAddress( 0, 0, headerStart[i], headerLast[i]));
	           
	            int page = 1;
				int row = 999;
				
				if(param.get("page") != null && !"".equals(param.get("page"))) page = Integer.parseInt(param.get("page").toString());
				if(param.get("row") != null && !"".equals(param.get("row"))) row = Integer.parseInt(param.get("row").toString());	

				int startNum = (page - 1) * row;
						
				param.put("start_num", startNum);
				param.put("row_num", row);
	            
	            //바디
	            List<Map<String, Object>> data = CallService.revenue_excel(param);			
				
				if(data.size() > 0) {
					data = NullToString.nulltostring(data);				
					
					for(int i = 1; i < (data.size() * 10) + 1; i++) {
						excelRow = sheet.createRow(rowNum++);
						
						for(int j = 0; j < header_title.length; j++) {
							 cell = excelRow.createCell(j);
							 cell.setCellStyle(bodyStyle);
							 
							 if(i%10 == 1) {
								 for(int k = 0; k < column.length; k++) {
									 int idx = i/10;
										if(j == headerStart[k]) cell.setCellValue(data.get(idx).get(column[k]).toString());
									 }	
							 }					                 
				         }
					}
					
					//셀 병합하기
					int total = data.size();
					
					for(int i = 0; i < total; i++) {
						for(int j = 0; j < headerStart.length; j++) {
							sheet.addMergedRegion(new CellRangeAddress( (i * 10) + 1, (i + 1) * 10, headerStart[j], headerLast[j]));
						}
					}
				}else {
					 excelRow = sheet.createRow(rowNum++);

					 for(int i = 0; i < header_title.length; i++) {
						 cell = excelRow.createCell(i);
						 cell.setCellStyle(bodyStyle);
						 if(i == 0) cell.setCellValue("존재하는 데이터가 없습니다.");
					 }
					 
					 sheet.addMergedRegion(new CellRangeAddress( 1, 1, 0, header_title.length - 1));
				}
				
		        String fileName = "콜근무실적_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//콜근무통계 엑셀 (월집계표)
		@RequestMapping(value="/month/excel")
		public void month_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {		
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("(월)집계표");

		        Row excelRow = null;
	            Cell cell = null;
	            int rowNum = 0;	            
	         
	            //헤더 스타일
	            CellStyle titleStyle = workbook.createCellStyle();		           
	            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            titleStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
	            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	            
	            //바디 스타일
	            CellStyle bodyStyle = workbook.createCellStyle();
	            bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	            bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		        
	            //헤더
	            String[] header_title_01 = {"구분", "", "집계현황", "", "", "", "", "", "", "", "통계분석", "", "", "", "", "", "비고", ""};
	            String[] header_title_02 = {"", "", "발생횟수", "", "총근무시간", "", "근무인원", "", "집행금액", "", "평균근무시간", "", "건평균 근무인원", "", "건평균 집행금액", "", "", ""};           
	            String[] column01 = {"value1", "value2", "value3", "value4", "value5", "value6", "value7"};
	            String[] column02 = {"wd_name", "value1", "value2", "value3", "value4", "value5", "value6", "value7"};
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title_01.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(titleStyle);
	                cell.setCellValue(header_title_01[i]);
	            }
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title_02.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(titleStyle);
	                cell.setCellValue(header_title_02[i]);
	            }
	            
	            sheet.addMergedRegion(new CellRangeAddress( 0, 1, 0, 1));
	            sheet.addMergedRegion(new CellRangeAddress( 0, 1, 16, 17));
	            sheet.addMergedRegion(new CellRangeAddress( 0, 0, 2, 9));
	            sheet.addMergedRegion(new CellRangeAddress( 0, 0, 10, 15));
	            for(int i = 2; i < 15; i++) {
	            	if(i%2 == 0) sheet.addMergedRegion(new CellRangeAddress( 1, 1, i, i+1));
	            }
	            
	            //바디
	            param.put("section", 1);
				List<Map<String, Object>> data1 = CallService.month_list(param); //작업구분 시퀀스 기져오기
				if(data1.size() > 0) {
					data1 = NullToString.nulltostring(data1);
					param.put("div", data1);
					
					param.put("section", 2);
					List<Map<String, Object>> data2 = CallService.month_list(param); //집계표 계
					if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
					
					param.put("section", 3);
					List<Map<String, Object>> data3 = CallService.month_list(param); //집계표
					if(data3.size() > 0) data3 = NullToString.nulltostring(data3);	
					
					//계 테이블			
					for(Map<String, Object> list : data2) {
						int idx = 0;
						excelRow = sheet.createRow(rowNum++);
						
						for(int i = 0; i < header_title_02.length; i++) {
							cell = excelRow.createCell(i);
							cell.setCellStyle(bodyStyle);
							
							if(i%2 == 0) {
								if(i == 0) cell.setCellValue("계");
								else if(i == 16) cell.setCellValue("");
								else cell.setCellValue(list.get(column01[idx++]).toString());
							}else cell.setCellValue("");
						}
					}
					
					//집계 데이터
					for(Map<String, Object> list : data3) {
						int idx = 0;
						excelRow = sheet.createRow(rowNum++);
						
						for(int i = 0; i < header_title_02.length; i++) {
							cell = excelRow.createCell(i);
							cell.setCellStyle(bodyStyle);
							
							if(i%2 == 0) {
								if(i == 16) cell.setCellValue("");
								else cell.setCellValue(list.get(column02[idx++]).toString());
							}else cell.setCellValue("");
						}
					}
					
					//셀 병합하기
					int total = data3.size() + 1;
					
					for(int i = 2; i < total + 2; i++) {
						for(int j = 0; j < header_title_02.length; j++) {
							if(j%2 == 0) sheet.addMergedRegion(new CellRangeAddress( i, i, j, j+1));
						}
					}
				}else { //데이터가 없는 경우
					param.put("none_div", 1);
					
					 excelRow = sheet.createRow(rowNum++);

					 for(int i = 0; i < header_title_02.length; i++) {
						 cell = excelRow.createCell(i);
						 cell.setCellStyle(bodyStyle);
						 if(i == 0) cell.setCellValue("존재하는 데이터가 없습니다.");
						 else cell.setCellValue("");
					 }
					 
					 sheet.addMergedRegion(new CellRangeAddress( 2, 2, 0, 17));
				}
				
		        String fileName = "콜근무(월집계표)_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//콜근무관리 보고서 엑셀
		@RequestMapping(value="/work/excel")
		public void work_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {		
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("콜근무일지");

		        Row excelRow = null;
	            Cell cell = null;
	            int rowNum = 0;	            
	         
	            //타이틀 스타일
	            
	            
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
	            String[] header_title_01 = {"구분", "", "집계현황", "", "", "", "", "", "", "", "통계분석", "", "", "", "", "", "비고", ""};
	            String[] header_title_02 = {"", "", "발생횟수", "", "총근무시간", "", "근무인원", "", "집행금액", "", "평균근무시간", "", "건평균 근무인원", "", "건평균 집행금액", "", "", ""};           
	            String[] column01 = {"value1", "value2", "value3", "value4", "value5", "value6", "value7"};
	            String[] column02 = {"wd_name", "value1", "value2", "value3", "value4", "value5", "value6", "value7"};
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title_01.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(headerStyle);
	                cell.setCellValue(header_title_01[i]);
	            }
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title_02.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(headerStyle);
	                cell.setCellValue(header_title_02[i]);
	            }                
				
		        String fileName = "콜근무(근무관리보고서)_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//근무관리 상세정보 엑셀
		@RequestMapping(value="/work/detail/excel")
		public void work_detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");

			try {
				Date nowDate = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);

				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("근무관리");
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
				headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headStyle.setFont(font);

				// 테이블 셀 스타일
				CellStyle cellStyle = wb.createCellStyle();

				for (int i = 0; i < 10; i++) {
					sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + (short) 2600);// 컬럼 넓이 조절
				}
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
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
				tableCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				tableCellStyle.setBorderTop((short) 1); // 테두리 위쪽
				tableCellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
				tableCellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
				tableCellStyle.setBorderRight((short) 1); // 테두리 오른쪽
				

				// Header
				row = sheet.createRow(rowNum++);
				cell = row.createCell((short) 0);
				cell.setCellStyle(headStyle);
				cell.setCellValue("근무관리");

				sheet.createRow(rowNum++);
				row = sheet.createRow(rowNum++); // 빈행 추가

				List<Map<String, Object>> list = CallService.select_work_excel(param);
				List<Map<String, Object>> list2 = CallService.select_work_detail_excel(param);
				
				if(list.size() > 0) {
					list = NullToString.nulltostring(list);
				
				String[] title = {"근무일자","사업단","고장번호","수리내역","작업시간","작업구분","작업내역"};
				
				String[] title2 = {"점검팀","설비분류","수리날짜","확인여부","작업종료","작업책임자",""};
				
				String[] data = {
						list.get(0).get("wrk_date").toString(),
						list.get(0).get("group_name").toString(),
						list.get(0).get("bk_seq").toString(),
						list.get(0).get("wrk_history").toString(),
						list.get(0).get("wrk_hour").toString(),
						list.get(0).get("wd_seq").toString(),
						list.get(0).get("wrk_work").toString(),
				};
				
				String[] data2 = {
						list.get(0).get("check_name").toString(),
						list.get(0).get("sys_seq").toString(),
						list.get(0).get("wrk_repair").toString(),
						list.get(0).get("wrk_confirm").toString(),
						list.get(0).get("wrk_end").toString(),
						list.get(0).get("per_seq").toString(),
						""
				};
				
				sheet.addMergedRegion(new CellRangeAddress(9, 13, 1, 3));
				sheet.addMergedRegion(new CellRangeAddress(9, 13, 0, 0));
				
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
			}
				sheet.createRow(rowNum++);
				row = sheet.createRow(rowNum++); // 빈행 추가
				sheet.createRow(rowNum++);
				row = sheet.createRow(rowNum++); // 빈행 추가
				sheet.createRow(rowNum++);
				row = sheet.createRow(rowNum++); // 빈행 추가
				
				
				String[] title3 = {"소속","근무자","콜근무별","작업시작","작업종료","콜근무완료","총근무시간","지급액"};
				String[] column1 = {"og_name", "per_name", "wkd_start", "wkd_work", "wkd_end","wkd_finish","wkd_total","wkd_amount"};
				
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
				
				/*
				String[] title3 = {"소속","근무자","콜근무별","작업시작","작업종료","콜근무완료","총근무시간","지급액"};
				
				for (int i = 0; i < title3.length; i++) { // 타이틀의 길이만큼 0번열에 추가
					cell = row.createCell(i);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(title3[i]);
				}
				
				
				for (int i = 0; i < list2.size(); i++) {
					row = sheet.createRow(rowNum++);
					cell = row.createCell(0);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("og_name").toString());
					
					cell = row.createCell(1);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("per_name").toString());
					
					cell = row.createCell(2);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("wkd_start").toString());
					
					cell = row.createCell(3);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("wkd_work").toString());
					
					cell = row.createCell(4);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("wkd_end").toString());
					
					cell = row.createCell(5);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("wkd_finish").toString());
					
					cell = row.createCell(6);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("wkd_total").toString());
					
					cell = row.createCell(7);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("wkd_amount").toString());
				}
				*/
				
				String fileName = "콜관리(근무관리)_" + strNowDate + ".xlsx";
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
		
		//연장근무관리 상세정보 엑셀
		@RequestMapping(value="/overtime/detail/excel")
		public void overtime_detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");

			try {
				Date nowDate = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);

				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("연장근무관리");
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
					sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + (short) 2800);// 컬럼 넓이 조절
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
				cell.setCellValue("연장근무관리");

				sheet.createRow(rowNum++);
				row = sheet.createRow(rowNum++); // 빈행 추가

				List<Map<String, Object>> list = CallService.overtime_excel(param);
				
				List<Map<String, Object>> list2 = CallService.overtime_detail_excel(param);
				
				if(list.size() > 0) {
					list = NullToString.nulltostring(list);
					list2 = NullToString.nulltostring(list2);
				
				String[] title = {"년월","사업단","관련근거","결원사유","휴가 시작시간","결원일수"};
				String[] title2 = {"지사","점검팀","결원자","","휴가 종료시간","비고"};
				
				String[] data = {
						list.get(0).get("ot_date").toString(),
						list.get(0).get("ot_group").toString(),
						list.get(0).get("ot_relate").toString(),
						list.get(0).get("ot_reason").toString(),
						list.get(0).get("ot_start").toString(),
						list.get(0).get("ot_days").toString()
				};
				String[] data2 = {
						list.get(0).get("brc_seq").toString(),
						list.get(0).get("check_name").toString(),
						list.get(0).get("ot_person").toString(),
						"",
						list.get(0).get("ot_end").toString(),
						list.get(0).get("ot_note").toString()
				};
				
				sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 3));
				
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
			}
				sheet.createRow(rowNum++);
				row = sheet.createRow(rowNum++); // 빈행 추가
				
				
				String[] title3 = {"근무자","근무구분","근무시작시간","근무종료시간","근무내역"};
				String[] column1 = {"per_seq", "od_start", "od_end", "od_history"};
				
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
					sheet.addMergedRegion(new CellRangeAddress(12, 12, 0, 4));
					for(int i = 0; i < column1.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(tableCellStyle);
						cell.setCellValue("존재하는 데이터가 없습니다.");
					}
				}
				
				/*
				String[] title3 = {"근무자","근무구분","근무시작시간","근무종료시간","근무내역"};
				
				for (int i = 0; i < title3.length; i++) { // 타이틀의 길이만큼 0번열에 추가
					cell = row.createCell(i);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(title3[i]);
				}
				
				for (int i = 0; i < list2.size(); i++) {
					row = sheet.createRow(rowNum++);
					cell = row.createCell(0);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("per_seq").toString());
					
					cell = row.createCell(1);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue("연장근무");
					
					cell = row.createCell(2);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("od_start").toString());
					
					cell = row.createCell(3);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("od_end").toString());
					
					cell = row.createCell(4);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list2.get(i).get("od_history").toString());
				}
				*/
				
				String fileName = "콜관리(연장근무관리)_" + strNowDate + ".xlsx";
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
