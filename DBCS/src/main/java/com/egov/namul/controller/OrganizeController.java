package com.egov.namul.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egov.namul.service.OrganizeService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/org")
public class OrganizeController {

	@Resource(name = "OrganizeService")
	private OrganizeService OrganizeService;
	
	@RequestMapping(value="/list")
	public void list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			if(param.get("og_name") != null || param.get("og_level") != null || param.get("og_person") != null){ //검색
				if(param.get("og_person") != null && !"".equals(param.get("og_person"))){ //구성원 검색
					List<Map<String, Object>> org = OrganizeService.member_org(param);				
					if(org.size() > 0) param.put("per_org", org);
				}
				
				List<Map<String, Object>> org = OrganizeService.organize_depth(param);	
				
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
			}
			
			List<Map<String, Object>> data = OrganizeService.list(param);
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + data.size()	+ ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + 0 +",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/detail")
	public void detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data = OrganizeService.organize(param);
			
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
	
	@RequestMapping(value="/person/list")
	public void personList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(Integer.parseInt(param.get("section").toString()) == 2) { //하위 조직 포함
				List<Map<String, Object>> org = OrganizeService.organize_level(param);
				
				if(org.size() > 0) {		
					Set<Integer> set = new HashSet<Integer>();			
					String[] column = {"one", "two", "three", "four", "five", "six"};
					
					for(String col : column) {
						for(Map<String, Object> list : org) {
							if(list.get(col) != null) set.add(Integer.parseInt(list.get(col).toString()));					
						}				
					}		
				
					if(set.size() > 0) param.put("depth_seq", set);
				}				
			}
			
			int page = Integer.parseInt(param.get("page").toString());
			int row = Integer.parseInt(param.get("row").toString());	
			
			int total = OrganizeService.member_total(param);
			int startNum = (page - 1) * row;
			
			param.put("start_num", startNum);
			param.put("row_num", row);
			
			List<Map<String, Object>> data = OrganizeService.member_list(param);
			
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
	
	@RequestMapping(value = "/edit")
	public void edit(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int result = OrganizeService.edit(param);
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Edit Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Edit Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/delete")
	public void delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> org = OrganizeService.organize_level(param);
			List<Map<String, Object>> seq = new ArrayList<Map<String, Object>>();
				
			String[] column = {"one", "two", "three", "four", "five", "six"};
				
			if(org.size() > 0) {
				if(org.size() == 1) {
					for(String str : column) {
						Map<String , Object> map = new HashMap<String, Object>();
						if(org.get(0).get(str) != null) map.put("seq", org.get(0).get(str));
						seq.add(map);
					}
				}else {
					for(Map<String, Object> list : org) {
						Map<String , Object> map = new HashMap<String, Object>();
						if(list.get("six") != null) map.put("seq", list.get("six"));
						seq.add(map);
					}
				}
					
				param.put("org_seq", seq);
			}				

			int result = OrganizeService.delete(param);
			jsonOut.write("{\"result\":[]" + ",\"status_code\":" + result + ",\"code\":1, \"msg\":\"Data Delete Success\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//조직관리 리스트 엑셀
	@RequestMapping(value="/list/excel")
	public void list_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			XSSFSheet sheet = wb.createSheet("조직관리");
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
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 4)); // 첫행, 마지막행, 첫열, 마지막열 병합

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
			cell.setCellValue("조직관리");

			sheet.createRow(rowNum++);
			row1 = sheet.createRow(rowNum++); // 빈행 추가
			
			List<Map<String, Object>> list = OrganizeService.select_organize_list_excel(param);
			if(list.size() > 0) {
				list = NullToString.nulltostring(list);		
			
			String[] title = {"등급","조직명","구성원","조직생성일","상태"};
			
			row1 = sheet.createRow(rowNum++);
			for (int i = 0; i < title.length; i++) {
				cell = row1.createCell(i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(title[i]);
			}
			
			for (int i = 0; i < list.size(); i++) {
				row1 = sheet.createRow(rowNum++);
				cell = row1.createCell(0);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("og_level").toString());
				
				cell = row1.createCell(1);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("og_name").toString());
				
				cell = row1.createCell(2);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("og_person").toString());
				
				cell = row1.createCell(3);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("create_date").toString());
				
				cell = row1.createCell(4);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("og_status").toString());
			}
		}

			String fileName = "인사관리(조직관리)_" + strNowDate + ".xlsx";
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
