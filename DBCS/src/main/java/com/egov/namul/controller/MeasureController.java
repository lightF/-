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

import com.egov.namul.service.FileService;
import com.egov.namul.service.MeasureService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/measure")
public class MeasureController {
	
	@Resource(name = "MeasureService")
	private MeasureService MeasureService;
	
	@Resource(name = "FileService")
	private FileService FileService;
	
	//계측기 리스트
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
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
			if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());
				
			int total = MeasureService.measure_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = MeasureService.measure_list(param);			
				
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
	
	//계측기 추가/수정
	@RequestMapping(value = "/edit")
	public void edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] msd_seq = request.getParameterValues("msd_seq[]");
			String[] msd_item = request.getParameterValues("msd_item[]");
			String[] msd_date = request.getParameterValues("msd_date[]");
			String[] msd_agency = request.getParameterValues("msd_agency[]");
			String[] msd_next = request.getParameterValues("msd_next[]");
			String[] msd_judge = request.getParameterValues("msd_judge[]");
			String[] msd_action = request.getParameterValues("msd_action[]");
			String[] msd_note = request.getParameterValues("msd_note[]");
			String[] mss_seq = request.getParameterValues("mss_seq[]");
			String[] mss_change = request.getParameterValues("mss_change[]");
			String[] mss_date = request.getParameterValues("mss_date[]");
			String[] mss_over = request.getParameterValues("mss_over[]");
			String[] mss_take = request.getParameterValues("mss_take[]");
			String[] mss_reason = request.getParameterValues("mss_reason[]");
			String[] mss_sale = request.getParameterValues("mss_sale[]");
			
			multi.clear();
			
			if(msd_seq != null) { //검교정
				for(int i = 0; i < msd_seq.length; i++) {
					int cnt = 0;

					if(msd_item[i].isEmpty()) cnt++;
					if(msd_date[i].isEmpty()) cnt++;
					if(msd_agency[i].isEmpty()) cnt++;
					if(msd_next[i].isEmpty()) cnt++;
					if(msd_judge[i].isEmpty()) cnt++;
					if(msd_action[i].isEmpty()) cnt++;
					if(msd_note[i].isEmpty()) cnt++;
					
					if(cnt < 7) {
						if("".equals(msd_seq[i])) multi.add("msd_seq", 0);
						else multi.add("msd_seq", msd_seq[i]);
						
						multi.add("msd_item", msd_item[i]);
						
						if("".equals(msd_date[i])) multi.add("msd_date", 0);
						else multi.add("msd_date", msd_date[i]);
						
						multi.add("msd_agency", msd_agency[i]);
						
						if("".equals(msd_next[i])) multi.add("msd_next", 0);
						else multi.add("msd_next", msd_next[i]);
						
						if("".equals(msd_judge[i])) multi.add("msd_judge", 0);
						else multi.add("msd_judge", msd_judge[i]);
						
						if("".equals(msd_action[i])) multi.add("msd_action", 0);
						else multi.add("msd_action", msd_action[i]);
						
						multi.add("msd_note", msd_note[i]);
					}					
				}
				
				if(multi.get("msd_seq") != null) param.put("msd_seq", 1);	
			}
			
			if(mss_seq != null) { //매각처 및 폐기사유
				for(int i = 0; i < mss_seq.length; i++) {
					int cnt = 0;

					if(mss_change[i].isEmpty()) cnt++;
					if(mss_date[i].isEmpty()) cnt++;
					if(mss_over[i].isEmpty()) cnt++;
					if(mss_take[i].isEmpty()) cnt++;
					if(mss_reason[i].isEmpty()) cnt++;
					if(mss_sale[i].isEmpty()) cnt++;
					
					if(cnt < 6) {
						if("".equals(mss_seq[i])) multi.add("mss_seq", 0);
						else multi.add("mss_seq", mss_seq[i]);
						
						multi.add("mss_change", mss_change[i]);
						
						if("".equals(mss_date[i])) multi.add("mss_date", 0);
						else multi.add("mss_date", mss_date[i]);
						
						multi.add("mss_over", mss_over[i]);
						multi.add("mss_take", mss_take[i]);
						multi.add("mss_reason", mss_reason[i]);
						multi.add("mss_sale", mss_sale[i]);
					}
				}
				
				if(multi.get("mss_seq") != null) param.put("mss_seq", 1);	
			}
			
			param.put("multi", multi);
			MeasureService.measure_edit(param);
			
			if(StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multiFiles = multiRequest.getFiles("upload[]");	 			
				
				String[] fileSeq = request.getParameterValues("f_seq[]");
				String[] fileDel = request.getParameterValues("f_del[]");
				
				param.put("per_seq", session.getAttribute("per_seq"));
				param.put("tb_name", "measure");
				
				
				if(fileDel != null) FileService.multi_resize_upload(param, multiFiles, "measure_file/", fileSeq, fileDel, 250, 200);
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
	
	//계측기 상세정보
	@RequestMapping(value="/detail")
	public void detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = MeasureService.measure(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			
			param.put("tb_name", "measure"); //파일 가져오기
			List<Map<String, Object>> data2 = FileService.file(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			List<Map<String, Object>> data3 = MeasureService.measure_detail(param);
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);
			
			List<Map<String, Object>> data4 = MeasureService.measure_sale(param);
			if(data4.size() > 0) data4 = NullToString.nulltostring(data4);
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data2)
			+ ",\"result3\":" + JsonUtil.getJsonArrayFromList(data3)
			+ ",\"result4\":" + JsonUtil.getJsonArrayFromList(data4)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"size3\":" + data3.size()
			+ ",\"size4\":" + data4.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//계측기 삭제
	@RequestMapping(value="/delete")
	public void delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("tb_name", "measure");
			MeasureService.measure_delete(param);
			FileService.delete(param, "measure_file/");
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//계측기 보고서
	@RequestMapping(value="/report")
	public void report(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 1);
			List<Map<String, Object>> data1 = MeasureService.measure_report(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			
			param.put("tb_name", "measure"); //파일 가져오기
			List<Map<String, Object>> data2 = FileService.file(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			param.put("section", 2);
			List<Map<String, Object>> data3 = MeasureService.measure_report(param);
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);
			
			param.put("section", 3);
			List<Map<String, Object>> data4 = MeasureService.measure_report(param);
			if(data4.size() > 0) data4 = NullToString.nulltostring(data4);
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data2)
			+ ",\"result3\":" + JsonUtil.getJsonArrayFromList(data3)
			+ ",\"result4\":" + JsonUtil.getJsonArrayFromList(data4)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"size3\":" + data3.size()
			+ ",\"size4\":" + data4.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//안전용품관리 리스트
	@RequestMapping(value="/safety/list")
	public void safety_list(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			if(param.get("sd_product") != null && !"".equals(param.get("sd_product"))) { //안전용품명 검색하는 경우
				List<Map<String, Object>> safe = MeasureService.safe(param);
				
				if(safe.size() > 0) param.put("safe", safe);
				else param.put("none_safe", 1);
			}
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
			if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());
				
			int total = MeasureService.safety_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = MeasureService.safety_list(param);			
				
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
	
	//안전용품관리 추가/수정
	@RequestMapping(value = "/safety/edit")
	public void safety_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] sd_seq = request.getParameterValues("sd_seq[]");
			String[] sd_date = request.getParameterValues("sd_date[]");
			String[] sd_seq1 = request.getParameterValues("sd_seq1[]");
			String[] sd_input = request.getParameterValues("sd_input[]");
			String[] input_ea = request.getParameterValues("input_ea[]");
			String[] sd_seq2 = request.getParameterValues("sd_seq2[]");
			String[] sd_output = request.getParameterValues("sd_output[]");
			String[] output_ea = request.getParameterValues("output_ea[]");
			String[] sd_note = request.getParameterValues("sd_note[]");
			
			multi.clear();
			
			if(sd_seq != null) {
				for(int i = 0; i < sd_seq.length; i++) {
					int cnt = 0;

					if(sd_date[i].isEmpty()) cnt++;
					if(sd_seq1[i].isEmpty()) cnt++;
					if(sd_input[i].isEmpty()) cnt++;
					if(input_ea[i].isEmpty()) cnt++;
					if(sd_seq2[i].isEmpty()) cnt++;
					if(sd_output[i].isEmpty()) cnt++;
					if(output_ea[i].isEmpty()) cnt++;
					if(sd_note[i].isEmpty()) cnt++;
					
					if(cnt < 6) {
						if("".equals(sd_seq[i]))	multi.add("sd_seq", 0);
						else multi.add("sd_seq", sd_seq[i]);
						
						if("".equals(sd_date[i]))	multi.add("sd_date", 0);
						else multi.add("sd_date", sd_date[i]);
						
						multi.add("sd_input", sd_input[i]);
						
						if("".equals(input_ea[i]))	multi.add("input_ea", 0);
						else multi.add("input_ea", input_ea[i]);
						
						multi.add("sd_output", sd_output[i]);
						
						if("".equals(output_ea[i])) multi.add("output_ea", 0);
						else multi.add("output_ea", output_ea[i]);
						
						multi.add("sd_note", sd_note[i]);
						
						if("".equals(sd_seq1[i])) multi.add("sd_seq1", 0);
						else multi.add("sd_seq1", sd_seq1[i]);
						
						multi.add("sd_seq1", sd_seq1[i]);
						
						if("".equals(sd_seq2[i])) multi.add("sd_seq2", 0);
						else multi.add("sd_seq2", sd_seq2[i]);
						
						multi.add("sd_seq2", sd_seq2[i]);
					}
				}
				
				param.put("multi", multi);
				if(multi.get("sd_seq") != null) param.put("sd_seq", 1);
			}
			
			MeasureService.safety_edit(param);			
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Edit Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}	
	
	//안전용품관리 상세정보
	@RequestMapping(value="/safety/detail")
	public void safety_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = MeasureService.safety(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);

			List<Map<String, Object>> data2 = MeasureService.safety_detail(param);
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
	
	//안전용품관리 삭제
	@RequestMapping(value="/safety/delete")
	public void safety_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			MeasureService.safety_delete(param);
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//계측기 통계 (계측기 보유현황)
	@RequestMapping(value="/table")
	public void table(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 1);
			List<Map<String, Object>> data1 = MeasureService.measure_table(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);

			param.put("section", 2);
			List<Map<String, Object>> data2 = MeasureService.measure_table(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			param.put("section", 4);
			List<Map<String, Object>> data3 = MeasureService.measure_table(param);
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);
			
			List<Object> data = new ArrayList<Object>();	
			
			if(data2.size() > 0) {
				List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
				for(Map<String, Object> list : data2) tmpList.add(list);						
				data.add(tmpList);
			}
			
			if(data1.size() > 0) {				
				for(int i = 0; i < data1.size()/data3.size(); i++) {
					List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();

					for(int j = (i*data3.size()); j < (i*data3.size()) + data3.size(); j++) tmpList.add(data1.get(j));						
					data.add(tmpList);
				}
			}			
			
			jsonOut.write("{\"result\":" + JsonUtil.ListToJson(data) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data3)
			+ ",\"size\":" + data.size()
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
	
	//계측기 통계 엑셀
		@RequestMapping(value="/stock/excel")
		public void stock_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("계측기 보유현황");

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
	            
	            param.put("section", 1);
				List<Map<String, Object>> data1 = MeasureService.measure_table(param);
				if(data1.size() > 0) data1 = NullToString.nulltostring(data1);

				param.put("section", 2);
				List<Map<String, Object>> data2 = MeasureService.measure_table(param);
				if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
				
				 //사업단 출력
	            param.put("section", 4);
				List<Map<String, Object>> data3 = MeasureService.measure_table(param);
				
				List<Object> data = new ArrayList<Object>();	
				
				if(data2.size() > 0) {
					List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
					for(Map<String, Object> list : data2) tmpList.add(list);						
					data.add(tmpList);
				}
				
				if(data1.size() > 0) {				
					for(int i = 0; i < data1.size()/data3.size(); i++) {
						List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();

						for(int j = (i*data3.size()); j < (i*data3.size()) + data3.size(); j++) tmpList.add(data1.get(j));						
						data.add(tmpList);
					}
				}
	            
	            //헤더
	            String[] header_title = {"품명", "", ""};
	            String[] body_column = {"pt_code", "pt_name", "ds_name", "sr_seq", "og_name", "ol_total", "ol_amount"};
				int header_col_start = 0;
				int header_col_last = 2;
	            
	            excelRow = sheet.createRow(rowNum++);
	            
	            for(int i = 0; i < header_title.length; i++) {
	            	cell = excelRow.createCell(i);
	                cell.setCellStyle(headerStyle);
	                cell.setCellValue(header_title[i]);
	            }
	            
	            sheet.addMergedRegion(new CellRangeAddress( 0, 0, header_col_start, header_col_last)); 
	                   
				if(data3.size() > 0) {
					data3 = NullToString.nulltostring(data3);
					int idx = 0;
					
					for(int i = 3; i < data3.size() + 3; i++) {
						cell = excelRow.createCell(i);
		                cell.setCellStyle(headerStyle);
		                cell.setCellValue(data3.get(idx++).get("og_name").toString());
					}				
					
					for(int i = 0; i < data.size(); i++) {
						@SuppressWarnings("unchecked")
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data.get(i);
						excelRow = sheet.createRow(rowNum++);
						
						int idx2 = 0;
						
						for(int j = 0; j < dataList.size() + 3; j++) {
							cell = excelRow.createCell(j);
			                cell.setCellStyle(bodyStyle);
			                if(j == 0) cell.setCellValue(dataList.get(0).get("md_name").toString());
			                else if(j > 2) cell.setCellValue(dataList.get(idx2++).get("value").toString());
			                
						}
						
						sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 0, 2));				
					}				
				}            
	                        
	            String fileName = "계측기통계(계측기 보유현황)_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//안전용품 리스트 엑셀
				@RequestMapping(value="/safety/list/excel")
				public void safety_list_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
				// Workbook wb = new HSSFWorkbook();
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/xlsx");
							try {
								int page = 1;
								int row = 999;
								
								if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
								if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());
									
								int startNum = (page - 1) * row;
										
								param.put("start_num", startNum);
								param.put("row_num", row);
								
								Date nowDate = new Date();	        
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
								String strNowDate = simpleDateFormat.format(nowDate);
								
								XSSFWorkbook wb = new XSSFWorkbook();
								XSSFSheet sheet = wb.createSheet("안전용품관리 리스트");
								XSSFRow row1 = null;
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

								for (int i = 0; i < 9; i++) {
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
								sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 5)); // 첫행, 마지막행, 첫열, 마지막열 병합

								// 테이블 스타일 설정
								CellStyle tableCellStyle = wb.createCellStyle();
								tableCellStyle.setBorderTop((short) 1); // 테두리 위쪽
								tableCellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
								tableCellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
								tableCellStyle.setBorderRight((short) 1); // 테두리 오른쪽
								tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

								// Header
								row1 = sheet.createRow(rowNum++);
								cell = row1.createCell((short) 0);
								cell.setCellStyle(headStyle);
								cell.setCellValue("안전용품관리");

								sheet.createRow(rowNum++);
								row1 = sheet.createRow(rowNum++); // 빈행 추가

								row1 = sheet.createRow(rowNum++);
								cell = row1.createCell((short) 0);
								cell.setCellStyle(cellStyle);
								cell.setCellValue("사업단");

								cell = row1.createCell((short) 1);
								cell.setCellStyle(cellStyle);
								cell.setCellValue("점검팀");

								cell = row1.createCell((short) 2);
								cell.setCellStyle(cellStyle);
								cell.setCellValue("설비명");

								cell = row1.createCell((short) 3);
								cell.setCellStyle(cellStyle);
								cell.setCellValue("안전용품명");

								cell = row1.createCell((short) 4);
								cell.setCellStyle(cellStyle);
								cell.setCellValue("보유수량");
								
								cell = row1.createCell((short) 5);
								cell.setCellStyle(cellStyle);
								cell.setCellValue("최종입출날짜");

								// Body
								List<Map<String, Object>> list = MeasureService.safety_list_excel(param);
								if(list.size() > 0) {
									list = NullToString.nulltostring(list);		
									
								if(list.isEmpty()) {
									sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 5));
									row1 = sheet.createRow(rowNum++);
									cell = row1.createCell(0);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue("표시할 데이터가 없습니다.");
								}else {
								for (int i = 0; i < list.size(); i++) {
									
									row1 = sheet.createRow(rowNum++);
									cell = row1.createCell(0);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("group_name").toString());

									cell = row1.createCell(1);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("check_name").toString());

									cell = row1.createCell(2);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("system_name").toString());

									cell = row1.createCell(3);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("name").toString());

									cell = row1.createCell(4);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("sf_amount").toString());
									
									cell = row1.createCell(5);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("sd_date").toString());
								}
							}
						}
						        String fileName = "계측기관리(안전용품관리 리스트)_" + strNowDate + ".xlsx";
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
				
				// 안전용품 팝업 엑셀
						@RequestMapping(value = "/safety/detail/excel")
						public void select_safety_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response)
								throws Exception {
							response.setCharacterEncoding("UTF-8");
							response.setContentType("application/xlsx");
							try {
								Date nowDate = new Date();	        
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
								String strNowDate = simpleDateFormat.format(nowDate);
								
								XSSFWorkbook wb = new XSSFWorkbook();
								XSSFSheet sheet = wb.createSheet("안전용품관리");
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

								for (int i = 0; i < 9; i++) {
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
								cell.setCellValue("안전용품관리 상세보기");

								sheet.createRow(rowNum++);
								row = sheet.createRow(rowNum++); // 빈행 추가

								List<Map<String, Object>> list = MeasureService.safety_excel(param);
								List<Map<String, Object>> list2 = MeasureService.safety_detail_excel(param);
								
								if(list.size() > 0) {
									list = NullToString.nulltostring(list);

								String[] title1 = { "사업단", "안전용품", "보유수량" };

								String[] data1 = { 
										list.get(0).get("group_name").toString(),
										list.get(0).get("sd_name").toString(),
										list.get(0).get("sf_amount").toString()
								};

								String[] title2 = { "점검팀", "설비명", "최종변경일"};

								String[] data2 = { 
										list.get(0).get("check_name").toString(),
										list.get(0).get("sys_name").toString(),
										list.get(0).get("sf_update").toString()
								};

								for (int i = 0; i < title1.length; i++) { // 타이틀의 길이만큼 0번열에 추가
									
									row = sheet.createRow(rowNum++);
									cell = row.createCell(0);
									cell.setCellStyle(cellStyle);
									cell.setCellValue(title1[i]);

									cell = row.createCell(1);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(data1[i]);

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
								
								String[] title3 = {"순번","입출일자","입고부서","입고수량","출고부서","출고수량","비고"};
								String[] column1 = {"sd_seq", "sd_date", "sd_input", "input_ea","sd_output","output_ea","sd_note"};
								
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
									sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 6));
									for(int i = 0; i < column1.length; i++) {
										cell = row.createCell(i);
										cell.setCellStyle(tableCellStyle);
										cell.setCellValue("존재하는 데이터가 없습니다.");
									}
								}
								
								
								 String fileName = "계측기관리(안전용품관리)_" + strNowDate + ".xlsx";
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
						
						//계측관리 리스트 엑셀
						@RequestMapping(value="/list/excel")
						public void measure_list_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
							response.setCharacterEncoding("UTF-8");
							response.setContentType("application/xlsx");
							
							try {
								
								int page = 1;
								int row = 999;
								
								if(param.get("page") != null && !"".equals(param.get("page")))	page = Integer.parseInt(param.get("page").toString());			
								if(param.get("row") != null && !"".equals(param.get("row")))	row = Integer.parseInt(param.get("row").toString());
									
								int startNum = (page - 1) * row;
										
								param.put("start_num", startNum);
								param.put("row_num", row);
								
								Date nowDate = new Date();	        
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
								String strNowDate = simpleDateFormat.format(nowDate);
								
								XSSFWorkbook wb = new XSSFWorkbook();
								XSSFSheet sheet = wb.createSheet("계측기관리 리스트");
								XSSFRow row1 = null;
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
								sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 9)); // 첫행, 마지막행, 첫열, 마지막열 병합

								// 테이블 스타일 설정
								CellStyle tableCellStyle = wb.createCellStyle();
								tableCellStyle.setBorderTop((short) 1); // 테두리 위쪽
								tableCellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
								tableCellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
								tableCellStyle.setBorderRight((short) 1); // 테두리 오른쪽
								tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

								// Header
								row1 = sheet.createRow(rowNum++);
								cell = row1.createCell((short) 0);
								cell.setCellStyle(headStyle);
								cell.setCellValue("계측기관리 리스트");

								sheet.createRow(rowNum++);
								row1 = sheet.createRow(rowNum++); // 빈행 추가
								
								String[] title = {
										"관리번호","계측기명","관리구분","모델","제조사","사업단","점검팀",
										"회계자산코드", "프로젝트코드", "검교정"
								};
								
								for(int i=0; i<title.length; i++) {
									cell = row1.createCell(i);
									cell.setCellStyle(cellStyle);
									cell.setCellValue(title[i]);
								}
								
								List<Map<String, Object>> list = MeasureService.measure_list_excel(param);
								if(list.size() > 0) {
									list = NullToString.nulltostring(list);		
								
								if(list.isEmpty()) {
									sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 5));
									row1 = sheet.createRow(rowNum++);
									cell = row1.createCell(0);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue("표시할 데이터가 없습니다.");
								}else {
								for(int i=0; i<list.size(); i++) {
									row1 = sheet.createRow(rowNum++);
									cell = row1.createCell(0);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("ms_manage").toString());

									cell = row1.createCell(1);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("md_seq").toString());

									cell = row1.createCell(2);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("ms_division").toString());

									cell = row1.createCell(3);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("ms_model").toString());

									cell = row1.createCell(4);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("act_company").toString());

									cell = row1.createCell(5);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("group_name").toString());

									cell = row1.createCell(6);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("check_name").toString());
									
									cell = row1.createCell(7);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("ms_asset").toString());
									
									cell = row1.createCell(8);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("ms_project").toString());
									
									cell = row1.createCell(9);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(i).get("ms_cycle").toString());
								}
							}
						}
								
								 String fileName = "기기관리(계측기관리 리스트)_" + strNowDate + ".xlsx";
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
						
						//계측관리 기본정보 엑셀
						@RequestMapping(value="/detail/excel")
						public void measure_dtail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
							response.setCharacterEncoding("UTF-8");
							response.setContentType("application/xlsx");
							response.setCharacterEncoding("UTF-8");
							response.setContentType("application/xlsx");
							try {
								Date nowDate = new Date();	        
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
								String strNowDate = simpleDateFormat.format(nowDate);
								
								XSSFWorkbook wb = new XSSFWorkbook();
								XSSFSheet sheet = wb.createSheet("기기관리(계측기관리 상세보기)");
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

								for (int i = 0; i < 9; i++) {
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
								cell.setCellValue("계측기관리 상세보기");

								sheet.createRow(rowNum++);
								row = sheet.createRow(rowNum++); // 빈행 추가
								
								List<Map<String, Object>> list = MeasureService.measure_excel(param);
								List<Map<String, Object>> list2 = MeasureService.measure_detail_excel(param);
								List<Map<String, Object>> list3 = MeasureService.measure_sale_excel(param);
								
								if(list.size() > 0) {
									list = NullToString.nulltostring(list);
									list2 = NullToString.nulltostring(list2);
									list3 = NullToString.nulltostring(list3);
								
								
								String[] title1 = {
										"관리번호","계측기명","모델명","제조일자","구매일자","사업단","규격",
										"내용연수","취득선","사용범위","검교정주기","회계자산코드"
										};
								
								String[] data1 = {
										list.get(0).get("ms_manage").toString(),
										list.get(0).get("md_seq").toString(),
										list.get(0).get("ms_model").toString(),
										list.get(0).get("ms_create").toString(),
										list.get(0).get("ms_buy").toString(),
										list.get(0).get("group_name").toString(),
										list.get(0).get("ms_stand1").toString(),
										list.get(0).get("ms_years").toString(),
										list.get(0).get("ms_account").toString(),
										list.get(0).get("ms_range").toString(),
										list.get(0).get("ms_cycle").toString(),
										list.get(0).get("ms_asset").toString()
								};
								
								String[] title2 = {
										"일련번호","규격","제조회사","불용일자","설비분류","점검팀","부대품",
										"허용오차","취득금액","관리구분","기타","프로젝트코드"
								};
								
								String[] data2 = {
										list.get(0).get("seq").toString(),
										list.get(0).get("ms_stand2").toString(),
										list.get(0).get("act_seq").toString(),
										list.get(0).get("ms_unuse").toString(),
										list.get(0).get("system_name").toString(),
										list.get(0).get("check_name").toString(),
										list.get(0).get("ms_access").toString(),
										list.get(0).get("ms_error").toString(),
										list.get(0).get("ms_acq").toString(),
										list.get(0).get("ms_division").toString(),
										list.get(0).get("ms_etc").toString(),
										list.get(0).get("ms_project").toString()
								};
								
								for (int i = 0; i < title1.length; i++) { // 타이틀의 길이만큼 0번열에 추가
									row = sheet.createRow(rowNum++);
									cell = row.createCell(0);
									cell.setCellStyle(cellStyle);
									cell.setCellValue(title1[i]);
									
									cell = row.createCell(1);
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(data1[i]);
									
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
								
								
								String[] title3 = {"순번","검교정번호","교정항목","검교정일","검교정기간","다음검교정일","판정",
										"조치방법", "비고"};
								String[] column1 = {"msd_seq", "ms_seq", "msd_item", "msd_date","msd_agency","msd_next"
										,"msd_judge","msd_action","msd_note"};
								
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
									sheet.addMergedRegion(new CellRangeAddress(18, 18, 0, 8));
									for(int i = 0; i < column1.length; i++) {
										cell = row.createCell(i);
										cell.setCellStyle(tableCellStyle);
										cell.setCellValue("존재하는 데이터가 없습니다.");
									}
								}
							
								sheet.createRow(rowNum++);
								row = sheet.createRow(rowNum++); // 빈행 추가
								
								String[] title4 = {"순번","변경구분","변경일","인계부서","인수부서","근거자료","매각처 및 폐기사유"};
								String[] column2 = {"mss_seq", "mss_change", "mss_date", "mss_over","mss_take","mss_reason"
										,"mss_sale"};
								
								row = sheet.createRow(rowNum++);
								
								//헤더
								for (int i = 0; i < title4.length; i++) { 
									cell = row.createCell(i);
									cell.setCellStyle(cellStyle);
									cell.setCellValue(title4[i]);
								}
								
								//바디
								if(list3.size() > 0) {
									list3 = NullToString.nulltostring(list3);					
									
									for(Map<String, Object> datas : list2) {
										row = sheet.createRow(rowNum++);
										
										for(int i = 0; i < column2.length; i++) {
											cell = row.createCell(i);
											cell.setCellStyle(tableCellStyle);
											cell.setCellValue(datas.get(column2[i]).toString());
										}
									}					
								}else {
									row = sheet.createRow(rowNum++);
									sheet.addMergedRegion(new CellRangeAddress(22, 22, 0, 6));
									for(int i = 0; i < column2.length; i++) {
										cell = row.createCell(i);
										cell.setCellStyle(tableCellStyle);
										cell.setCellValue("존재하는 데이터가 없습니다.");
									}
								}
								
								 String fileName = "기기관리(계측기관리 상세보기)_" + strNowDate + ".xlsx";
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
