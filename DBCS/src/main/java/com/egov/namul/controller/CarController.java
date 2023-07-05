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

import com.egov.namul.service.CarService;
import com.egov.namul.service.FileService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/car")
public class CarController {

	@Resource(name = "CarService")
	private CarService carService;
	
	@Resource(name = "FileService")
	private FileService FileService;

	//운행일지 리스트
	@RequestMapping(value = "/list")
	public void drive_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

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
			
			if(param.get("page") != null && !"".equals(param.get("page"))) page = Integer.parseInt(param.get("page").toString());
			if(param.get("row") != null && !"".equals(param.get("row"))) row = Integer.parseInt(param.get("row").toString());	

			int total = carService.drive_total(param);
			int startNum = (page - 1) * row;

			param.put("start_num", startNum);
			param.put("row_num", row);

			List<Map<String, Object>> data = carService.drive_list(param);

			if (data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total + ",\"row\":"
						+ row + ",\"page\":" + page + ",\"code\":1}");
			} else
				jsonOut.write("{\"result\":[]" + ",\"total\":" + total + ",\"row\":" + row + ",\"page\":" + page
						+ ",\"code\":1, \"msg\":\"No Data\"}");
		} catch (Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		} finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//운행일지 상세정보
	@RequestMapping(value="/detail")
	public void drive_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {		
			List<Map<String, Object>> data1 = carService.drive(param);
			List<Map<String, Object>> data2 = carService.drive_history(param);			
			param.put("tb_name", "race"); //사고일지 사진 가져오기
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
	
	//운행일지 추가/수정
	@RequestMapping(value="/edit")
	public void drive_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] rh_seq = request.getParameterValues("rh_seq[]");
			String[] rh_depart = request.getParameterValues("rh_depart[]");
			String[] rh_arrival = request.getParameterValues("rh_arrival[]");
			String[] rh_distance = request.getParameterValues("rh_distance[]");
			String[] rh_minute = request.getParameterValues("rh_minute[]");
			String[] rh_pass = request.getParameterValues("rh_pass[]");
			String[] rh_park = request.getParameterValues("rh_park[]");
			String[] rh_driver = request.getParameterValues("rh_driver[]");
			String[] rh_note = request.getParameterValues("rh_note[]");
			
			multi.clear();
			
			if(rh_seq != null) {
				for(int i = 0; i < rh_seq.length; i++) {
					int cnt = 0;

					if(rh_depart[i].isEmpty()) cnt++;
					if(rh_arrival[i].isEmpty()) cnt++;
					if(rh_distance[i].isEmpty()) cnt++;
					if(rh_minute[i].isEmpty()) cnt++;
					if(rh_pass[i].isEmpty()) cnt++;
					if(rh_park[i].isEmpty()) cnt++;
					if(rh_driver[i].isEmpty()) cnt++;
					if(rh_note[i].isEmpty()) cnt++;
					
					if(cnt < 8) {
						if("".equals(rh_seq[i])) multi.add("rh_seq", 0);
						else multi.add("rh_seq", rh_seq[i]);
						
						multi.add("rh_depart", rh_depart[i]);
						multi.add("rh_arrival", rh_arrival[i]);
						
						if("".equals(rh_distance[i])) multi.add("rh_distance", 0);
						else multi.add("rh_distance", rh_distance[i]);
						
						if("".equals(rh_minute[i])) multi.add("rh_minute", 0);
						else multi.add("rh_minute", rh_minute[i]);
						
						if("".equals(rh_pass[i])) multi.add("rh_pass", 0);
						else multi.add("rh_pass", rh_pass[i]);
						
						if("".equals(rh_park[i])) multi.add("rh_park", 0);
						else multi.add("rh_park", rh_park[i]);
						
						multi.add("rh_driver", rh_driver[i]);
						multi.add("rh_note", rh_note[i]);
					}					
				}
				
				param.put("multi", multi);			
				if(multi.get("rh_seq") != null) param.put("rh_seq", 1);	
			}
			
			carService.drive_edit(param);

			if(StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multiFiles = multiRequest.getFiles("upload[]");	 			
				
				String[] fileSeq = request.getParameterValues("f_seq[]");
				String[] fileDel = request.getParameterValues("f_del[]");
				param.put("tb_name", "race");		

				if(fileDel != null) FileService.multi_resize_upload(param, multiFiles, "race_file/", fileSeq, fileDel, 250, 200);
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
	
	//운행일지 삭제
	@RequestMapping(value="/delete")
	public void drive_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("tb_name", "race");		
			carService.drive_delete(param);
			FileService.delete(param, "race_file/");
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//운행일지 보고서
	@RequestMapping(value="/report")
	public void drive_report(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			List<Map<String, Object>> data1 = carService.drive_report(param); // 차량 기본정보
			
			List<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();
			
			if(data1.size() > 0) {
				data1 = NullToString.nulltostring(data1);
				
				param.put("section", 2);
				List<Map<String, Object>> data3 = carService.drive_report(param); // 운행일지 합산		
				
				param.put("section", 3);
				List<Map<String, Object>> data4 = carService.drive_report(param); // 운행일지
				
				if(data4.size() > 0) {
					data3 = NullToString.nulltostring(data3);	
					data4 = NullToString.nulltostring(data4);
					
					for(Map<String, Object> map : data3) data2.add(map);
					for(Map<String, Object> map : data4) data2.add(map);
				}				
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
	
	//사고일지 리스트
	@RequestMapping(value = "/accident/list")
	public void accident_list(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			if(param.get("page") != null && !"".equals(param.get("page"))) page = Integer.parseInt(param.get("page").toString());
			if(param.get("row") != null && !"".equals(param.get("row"))) row = Integer.parseInt(param.get("row").toString());	

			int total = carService.accident_total(param);
			int startNum = (page - 1) * row;

			param.put("start_num", startNum);
			param.put("row_num", row);

			List<Map<String, Object>> data = carService.accident_list(param);

			if (data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total + ",\"row\":"
					+ row + ",\"page\":" + page + ",\"code\":1}");
			} else
				jsonOut.write("{\"result\":[]" + ",\"total\":" + total + ",\"row\":" + row + ",\"page\":" + page
					+ ",\"code\":1, \"msg\":\"No Data\"}");
			} catch (Exception e) {
				e.printStackTrace();
				jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
			} finally {
				jsonOut.flush();
				jsonOut.close();
		}
	}
		
	//사고일지 추가/수정
	@RequestMapping(value="/accident/edit")
	public void accident_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		param.put("multi", multi);
			
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {			
			carService.accident_edit(param);
			
			if(StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multiFiles = multiRequest.getFiles("upload[]");	 			
				
				String[] fileSeq = request.getParameterValues("f_seq[]");
				String[] fileDel = request.getParameterValues("f_del[]");
				param.put("tb_name", "accident");				
				
				if(fileDel != null) FileService.multi_resize_upload(param, multiFiles, "accident_file/", fileSeq, fileDel, 250, 200);
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
		
	//사고일지 기본정보
	@RequestMapping(value="/accident/detail")
	public void accident_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {		
			List<Map<String, Object>> data1 = carService.accident(param); //사고일지 기본정보				
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
				
			param.put("tb_name", "accident"); //사고일지 사진 가져오기
			List<Map<String, Object>> data2 = FileService.file(param);
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
		
	//사고일지 삭제
	@RequestMapping(value="/accident/delete")
	public void accident_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {
			param.put("tb_name", "accident");
			carService.accident_delete(param);
			FileService.delete(param, "accident_file/");
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}

	//사고일지 보고서
	@RequestMapping(value="/accident/report")
	public void accident_report(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {		
			List<Map<String, Object>> data = carService.accident_report(param); //사고일지 기본정보				
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
	
	
	
	//차량관리 리스트
	@RequestMapping(value="/vehicle/list")
	public void vehicle_list(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page"))) page = Integer.parseInt(param.get("page").toString());
			if(param.get("row") != null && !"".equals(param.get("row"))) row = Integer.parseInt(param.get("row").toString());		
				
			int total = carService.vehicle_total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = carService.vehicle_list(param);
				
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
	
	//차량관리 추가/수정
	@RequestMapping(value = "/vehicle/edit")
	public void vehicle_edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		param.put("writer", session.getAttribute("per_seq"));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			String[] vl_seq = request.getParameterValues("dh_seq[]");
			String[] vl_basis = request.getParameterValues("dh_seq[]");
			
			multi.clear();
			
			if(vl_seq != null) {
				for(int i = 0; i < vl_seq.length; i++) {
					int cnt = 0;

					if(vl_basis[i].isEmpty()) cnt++;

					if(cnt < 1) {
						if("".equals(vl_seq[i])) multi.add("vl_seq", 0);
						else multi.add("vl_seq", vl_seq[i]);
						
						multi.add("vl_basis", vl_basis[i]);						
					}
				}
				
				param.put("multi", multi);			
				if(multi.get("vl_seq") != null) param.put("vl_seq", 1);	
			}
			
			carService.vehicle_edit(param);
			
			if(StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multiFiles = multiRequest.getFiles("upload[]");	 			
				
				String[] fileSeq = request.getParameterValues("f_seq[]");
				String[] fileDel = request.getParameterValues("f_del[]");
				param.put("tb_name", "vehicle");				
				
				if(fileDel != null) FileService.multi_resize_upload(param, multiFiles, "vehicle_file/", fileSeq, fileDel, 250, 200);
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
	
	//차량관리 상세정보
	@RequestMapping(value="/vehicle/detail")
	public void vehicle_detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data1 = carService.vehicle(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);

			List<Map<String, Object>> data2 = carService.vehicle_log(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			param.put("tb_name", "vehicle"); //부품관리 파일 가져오기
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
	
	//차량관리 삭제
	@RequestMapping(value="/vehicle/delete")
	public void vehicle_delete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("tb_name", "vehicle");
			carService.vehicle_delete(param);	
			FileService.delete(param, "vehicle_file/");
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//차량통계(테이블)
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
			
			int section = Integer.parseInt(param.get("section").toString());
			
			if(section == 2) { //설비별
				List<Map<String, Object>> system = carService.system_list(param);
				
				if(system.size() > 0) param.put("system", system);
				else param.put("none_system", 1);
			}
			
			List<Map<String, Object>> data1 = carService.revenue_list(param);
				
			if(data1.size() > 0) {
				data1 = NullToString.nulltostring(data1);
				
				if(section < 5) {
					List<Map<String, Object>> data2 = carService.revenue_total(param);
					if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
					
					for(Map<String, Object> list : data2) data1.add(list);
				}				
				
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) + ",\"size\":" + data1.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"size\":" + 0	+ ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//L당 가동거리
	@RequestMapping(value="/liter/chart")
	public void liter_chart(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int section = Integer.parseInt(param.get("section").toString());
			
			if(section == 2) { //설비별
				List<Map<String, Object>> system = carService.system_list(param);
				
				if(system.size() > 0) param.put("system", system);
				else param.put("none_system", 1);
			}
			
			List<Map<String, Object>> data = carService.liter_chart(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);				
				jsonOut.write("{\"result\":[" + JsonUtil.getJsonArrayFromList(data) + "],\"size\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"size\":" + 0	+ ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//일평균 이동거리
	@RequestMapping(value="/move/chart")
	public void move_chart(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int section = Integer.parseInt(param.get("section").toString());
			
			if(section == 2) { //설비별
				List<Map<String, Object>> system = carService.system_list(param);
				
				if(system.size() > 0) param.put("system", system);
				else param.put("none_system", 1);
			}
			
			List<Map<String, Object>> data = carService.day_chart(param);
				
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);				
				jsonOut.write("{\"result\":[" + JsonUtil.getJsonArrayFromList(data) + "],\"size\":" + data.size() + ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"size\":" + 0	+ ",\"code\":1, \"msg\":\"No Data\"}");	
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	// 운행일지 리스트 엑셀파일
	@RequestMapping(value = "/list/excel")
	public void drive_excel(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
			XSSFSheet sheet = wb.createSheet("운행일지 리스트");
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

			for (int i = 0; i < 11; i++) {
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
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 8)); // 첫행, 마지막행, 첫열, 마지막열 병합

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
			cell.setCellValue("운행일지");

			sheet.createRow(rowNum++);
			row1 = sheet.createRow(rowNum++); // 빈행 추가

			row1 = sheet.createRow(rowNum++);
			cell = row1.createCell((short) 0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("운행일자");

			cell = row1.createCell((short) 1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("운행전");

			cell = row1.createCell((short) 2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("운행후");

			cell = row1.createCell((short) 3);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("사업단");

			cell = row1.createCell((short) 4);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("운행팀");

			cell = row1.createCell((short) 5);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("차량번호");

			cell = row1.createCell((short) 6);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("주유금액");

			cell = row1.createCell((short) 7);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("주유량");

			cell = row1.createCell((short) 8);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("점검금액");
			
			cell = row1.createCell((short) 9);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("주차비");

			cell = row1.createCell((short) 10);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("통행료");

			// Body
			List<Map<String, Object>> list = carService.drive_list_excel(param);
			
			if(list.size() > 0) {
				list = NullToString.nulltostring(list);		

			for (int i = 0; i < list.size(); i++) {
				row1 = sheet.createRow(rowNum++);
				cell = row1.createCell(0);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("rc_date").toString());

				cell = row1.createCell(1);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("rc_before").toString());

				cell = row1.createCell(2);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("rc_after").toString());

				cell = row1.createCell(3);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("group_name").toString());

				cell = row1.createCell(4);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("team_name").toString());

				cell = row1.createCell(5);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("ve_number").toString());

				cell = row1.createCell(6);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("rc_amount").toString());

				cell = row1.createCell(7);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("rc_refuel").toString());

				cell = row1.createCell(8);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("rc_trouble").toString());
				
				cell = row1.createCell(9);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("rc_pass").toString());

				cell = row1.createCell(10);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("rc_park").toString());
			}
			}

			 String fileName = "차량관리(운행일지 리스트)_" + strNowDate + ".xlsx";
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

	// 운행일지 팝업 엑셀
	@RequestMapping(value = "/detail/excel")
	public void drive_detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xlsx");
		try {
			
			Date nowDate = new Date();	        
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String strNowDate = simpleDateFormat.format(nowDate);
			
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("운행일지 상세보기");
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
			cell.setCellValue("운행일지 상세보기");

			sheet.createRow(rowNum++);
			row = sheet.createRow(rowNum++); // 빈행 추가

			List<Map<String, Object>> list = carService.drive_detail_excel(param);
			List<Map<String, Object>> list2 = carService.drive_history_excel(param);

			if(list.size() > 0) {
				list = NullToString.nulltostring(list);
				list2 = NullToString.nulltostring(list2);
			
			String[] title1 = { "차량번호", "운행일자", "운행전(Km)", "가동시간", "통행료", "주유리터", "주유단가", "고장수리비"/* "첨부파일1" */ };

			String[] data1 = { list.get(0).get("ve_number").toString(),
					list.get(0).get("rc_date").toString(),
					list.get(0).get("rc_before").toString(),
					list.get(0).get("rc_operate").toString(),
					list.get(0).get("rc_pass").toString(),
					list.get(0).get("rc_refuel").toString(),
					list.get(0).get("rc_price").toString(),
					list.get(0).get("rc_trouble").toString()
			};

			String[] title2 = { "입력자", "주행거리", "운행후(Km)", "경유지", "주차비", "주유금액", "사고처리", "고장부품"/* ,"첨부파일2" */ };

			String[] data2 = { list.get(0).get("per_name").toString(),
					list.get(0).get("rc_drive").toString(),
					list.get(0).get("rc_after").toString(),
					list.get(0).get("rc_area").toString(),
					list.get(0).get("rc_park").toString(),
					list.get(0).get("rc_amount").toString(),
					list.get(0).get("rc_handle").toString(),
					list.get(0).get("rc_part").toString()
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

			String[] title3 = {"순번","출발지","도착지","거리","시간(분)","통행료","주차비","운전자","비고"};
			String[] column1 = {"rh_seq", "rh_depart", "rh_arrival", "rh_distance","rh_minute","rh_pass","rh_park","per_name","rh_note"};
			
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
				sheet.addMergedRegion(new CellRangeAddress(14, 14, 0, 8));
				for(int i = 0; i < column1.length; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue("존재하는 데이터가 없습니다.");
				}
			}
			
			/*
			row = sheet.createRow(rowNum++);
			cell = row.createCell((short) 0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("순번");

			cell = row.createCell((short) 1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("출발지");

			cell = row.createCell((short) 2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("도착지");

			cell = row.createCell((short) 3);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("거리");

			cell = row.createCell((short) 4);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("시간(분)");

			cell = row.createCell((short) 5);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("통행료");

			cell = row.createCell((short) 6);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("주차비");

			cell = row.createCell((short) 7);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("운전자");

			cell = row.createCell((short) 8);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("비고");
			
			
			if(list2.isEmpty()) {
				sheet.addMergedRegion(new CellRangeAddress(14, 14, 0, 8));
				row = sheet.createRow(rowNum++);
				cell = row.createCell(0);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue("표시할 데이터가 없습니다.");
			}else {
			for (int i = 0; i < list2.size(); i++) {
				row = sheet.createRow(rowNum++);
				cell = row.createCell(0);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list2.get(i).get("rh_seq").toString());

				cell = row.createCell(1);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list2.get(i).get("rh_depart").toString());

				cell = row.createCell(2);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list2.get(i).get("rh_arrival").toString());

				cell = row.createCell(3);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list2.get(i).get("rh_distance").toString());

				cell = row.createCell(4);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list2.get(i).get("rh_minute").toString());

				cell = row.createCell(5);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list2.get(i).get("rh_pass").toString());

				cell = row.createCell(6);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list2.get(i).get("rh_park").toString());

				cell = row.createCell(7);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list2.get(i).get("per_name").toString());

				cell = row.createCell(8);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list2.get(i).get("rh_note").toString());
			}
		}*/
	

			 String fileName = "차량관리(운행일지)_" + strNowDate + ".xlsx";
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
	
	//차량관리 리스트 엑셀
	@RequestMapping(value = "/vehicle/list/excel")
	public void vehicle_excel(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
			XSSFSheet sheet = wb.createSheet("차량관리 리스트");
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
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 8)); // 첫행, 마지막행, 첫열, 마지막열 병합

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
			cell.setCellValue("차량관리 리스트");

			sheet.createRow(rowNum++);
			row1 = sheet.createRow(rowNum++); // 빈행 추가
			
			List<Map<String, Object>> list = carService.vehicle_list_excel(param);
			if(list.size() > 0) {
				list = NullToString.nulltostring(list);		
			
			String[] title = {
					"사업단","점검팀","차종","차명","차량번호","등록일","년식","차량구분","차량검사"
			};
			
			for(int i=0; i<title.length; i++) {
				cell = row1.createCell(i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(title[i]);
			}
			
			
			for(int i=0; i<list.size(); i++) {
				row1 = sheet.createRow(rowNum++);
				cell = row1.createCell(0);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("group_name").toString());

				cell = row1.createCell(1);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("check_name").toString());

				cell = row1.createCell(2);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("ve_model").toString());

				cell = row1.createCell(3);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("ve_name").toString());

				cell = row1.createCell(4);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("ve_number").toString());

				cell = row1.createCell(5);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("update_date").toString());

				cell = row1.createCell(6);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("ve_year").toString());
				
				cell = row1.createCell(7);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("ve_type").toString());
				
				cell = row1.createCell(8);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(list.get(i).get("ve_yn").toString());
			}
		}
			
			 String fileName = "차량관리(차량관리 리스트)_" + strNowDate + ".xlsx";
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
	
	//차량관리 기본정보 엑셀
	@RequestMapping(value = "/vehicle/detail/excel")
	public void vehicle_detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xlsx");
		try {
			
			Date nowDate = new Date();	        
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String strNowDate = simpleDateFormat.format(nowDate);
			
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("차량관리");
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
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 8)); // 첫행, 마지막행, 첫열, 마지막열 병합

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
			cell.setCellValue("차량관리");

			sheet.createRow(rowNum++);
			row = sheet.createRow(rowNum++); // 빈행 추가
			
			List<Map<String, Object>> list = carService.vehicle_excel(param);
			List<Map<String, Object>> list2 = carService.select_vehicle_log_excel(param);
			
			if(list.size() > 0) {
				list = NullToString.nulltostring(list);
			
			String[] title = {"사업단","차명","년식","점검팀","수임자","검사유효기간","설비","연료",
					"차량구분","무료통행기간","긴급차량지정번호","경광등","블랙박스설치","GPS설치","리프트설치","비고",
					"","",""};
			
			String[] title2 = {"차량번호","차종","차대번호","점검팀인수일","수임자인수일","주행거리",
					"프로젝트명","차량구입일자", "용도","통행표지번호","지정일자","경찰청","블랙박스일련번호",
					"GPS일련번호","차량사용상태","작성자1",
					"작성자2","작성자3","작성자4"};
			
			String[] data = {
					list.get(0).get("og_seq").toString(),
					list.get(0).get("ve_name").toString(),
					list.get(0).get("ve_year").toString(),
					list.get(0).get("ve_check").toString(),
					list.get(0).get("ve_delegate").toString(),
					list.get(0).get("ve_expiry").toString(),
					list.get(0).get("sys_seq").toString(),
					list.get(0).get("ve_fuel").toString(),
					list.get(0).get("ve_type").toString(),
					list.get(0).get("ve_free").toString(),
					list.get(0).get("ve_urgent").toString(),
					list.get(0).get("ve_lightbar").toString(),
					list.get(0).get("ve_recorder").toString(),
					list.get(0).get("ve_gps").toString(),
					list.get(0).get("ve_lift").toString(),
					list.get(0).get("ve_note").toString(),
					"","","",
			};
			
			String[] data2= {
					list.get(0).get("ve_number").toString(),
					list.get(0).get("ve_model").toString(),
					list.get(0).get("ve_identify").toString(),
					list.get(0).get("check_date").toString(),
					list.get(0).get("delegate_date").toString(),
					list.get(0).get("ve_drive").toString(),
					list.get(0).get("ve_project").toString(),
					list.get(0).get("ve_buy").toString(),
					list.get(0).get("ve_use").toString(),
					list.get(0).get("ve_pass").toString(),
					list.get(0).get("ve_appoint").toString(),
					list.get(0).get("ve_police").toString(),
					list.get(0).get("recorder_num").toString(),
					list.get(0).get("gps_num").toString(),
					list.get(0).get("ve_status").toString(),
					list.get(0).get("ve_writer1").toString(),
					list.get(0).get("ve_writer1").toString(),
					list.get(0).get("ve_writer1").toString(),
					list.get(0).get("ve_writer1").toString(),
			};
			
			for (int i = 0; i <title.length; i++) { // 타이틀의 길이만큼 0번열에 추가
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
			
			String[] title3 = {"순번","구분","변경일","변경사항","근거"};
			String[] column1 = {"vl_seq", "vl_divide", "vl_update", "vl_change", "vl_basis"};
			
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
				//sheet.addMergedRegion(new CellRangeAddress(10, 10, 0, 10));
				for(int i = 0; i < column1.length; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue("존재하는 데이터가 없습니다.");
				}
			}
			
			 String fileName = "차량관리(차량관리)_" + strNowDate + ".xlsx";
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
	
	
	// 사고일지 리스트 엑셀파일 
	@RequestMapping(value = "/accident/list/excel")
	public void accident_excel(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
			XSSFSheet sheet = wb.createSheet("사고일지 리스트");
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
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 7)); // 첫행, 마지막행, 첫열, 마지막열 병합

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
			cell.setCellValue("사고일지 리스트");

			sheet.createRow(rowNum++);
			row1 = sheet.createRow(rowNum++); // 빈행 추가

			List<Map<String, Object>> list = carService.accident_list_excel(param);
			String[] title = {"사고번호","사업단","점검팀","차명","차량번호","구분","사고일","사고장소"};
			String[] column1 = {"seq", "group_name", "team_name","ve_name","ve_number",
					"ac_blame","ac_date","ac_place"};
			
			row1 = sheet.createRow(rowNum++);
			
			//헤더
			for (int i = 0; i < title.length; i++) { 
				cell = row1.createCell(i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(title[i]);
			}
			
			//바디
			if(list.size() > 0) {
				list = NullToString.nulltostring(list);					
				
				for(Map<String, Object> datas : list) {
					row1 = sheet.createRow(rowNum++);
					
					for(int i = 0; i < column1.length; i++) {
						cell = row1.createCell(i);
						cell.setCellStyle(tableCellStyle);
						cell.setCellValue(datas.get(column1[i]).toString());
					}
				}					
			}else {
				row1 = sheet.createRow(rowNum++);
				//sheet.addMergedRegion(new CellRangeAddress(18, 18, 0, 4));
				for(int i = 0; i < column1.length; i++) {
					cell = row1.createCell(i);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue("존재하는 데이터가 없습니다.");
				}
			}
			
			 String fileName = "차량관리(사고일지 리스트)_" + strNowDate + ".xlsx";
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
	
	@RequestMapping(value="/accident/detail/excel")
	public void accident_detail_excel(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xlsx");
		try {
			
			Date nowDate = new Date();	        
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String strNowDate = simpleDateFormat.format(nowDate);
			
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("사고일지");
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
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 3));
			sheet.addMergedRegion(new CellRangeAddress(8, 8, 0, 3));
			sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 3));
			sheet.addMergedRegion(new CellRangeAddress(13, 13, 0, 3));
			sheet.addMergedRegion(new CellRangeAddress(14, 14, 0, 3));// 첫행, 마지막행, 첫열, 마지막열 병합
			sheet.addMergedRegion(new CellRangeAddress(15, 15, 0, 3));
			sheet.addMergedRegion(new CellRangeAddress(16, 16, 0, 3));
			
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
			cell.setCellValue("사고일지");

			sheet.createRow(rowNum++);
			row = sheet.createRow(rowNum++); // 빈행 추가
			
			List<Map<String, Object>> list = carService.accident_detail_excel(param);
			
			if(list.size() > 0) {
				list = NullToString.nulltostring(list);
			
			String[] title = {"차량번호","사업단","사고일자","사고장소","상대차량번호"};
			String[] title2 = {"운전자","점검팀","날씨","상대차종","연락처"};
			
			String[] data = {
					list.get(0).get("ve_seq").toString(),
					list.get(0).get("per_organize").toString(),
					list.get(0).get("ac_date").toString(),
					list.get(0).get("ac_place").toString(),
					list.get(0).get("ac_car").toString()
			};
			
			String[] data2 = {
					list.get(0).get("per_seq").toString(),
					list.get(0).get("per_team").toString(),
					list.get(0).get("ac_weather").toString(),
					list.get(0).get("ac_model").toString(),
					list.get(0).get("ac_phone").toString()
			};
			
			for (int i = 0; i <title.length; i++) { // 타이틀의 길이만큼 0번열에 추가
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
		
				row = sheet.createRow(rowNum++);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("사고개요");
			
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellStyle(tableCellStyle);
			cell.setCellValue(list.get(0).get("ac_summary").toString());
			
			String[] title4 = {"책임구분","당사(대인)","상대(대인)"};
			String[] title5 = {"보고일","당사(대물)","상대(대물)"};
			String[] data3 = {
					list.get(0).get("ac_blame").toString(),
					list.get(0).get("ac_our1").toString(),
					list.get(0).get("ac_our2").toString(),
			};
			String[] data4 = {
					list.get(0).get("ac_report").toString(),
					list.get(0).get("ac_match1").toString(),
					list.get(0).get("ac_match2").toString(),
			};
			
			for (int i = 0; i <title4.length; i++) { // 타이틀의 길이만큼 0번열에 추가
				row = sheet.createRow(rowNum++);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(title4[i]);
				
				cell = row.createCell(1);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(data3[i]);
			
				cell = row.createCell(2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(title5[i]);
			
				cell = row.createCell(3);
				cell.setCellStyle(tableCellStyle);
				cell.setCellValue(data4[i]);
			}
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("사고차량 현황 및 수리기간");
		
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellStyle(tableCellStyle);
			cell.setCellValue(list.get(0).get("ac_term").toString());

			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("기타");
			
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellStyle(tableCellStyle);
			cell.setCellValue(list.get(0).get("ac_etc").toString());
		
			sheet.createRow(rowNum++);
			row = sheet.createRow(rowNum++); // 빈행 추가
			}
			 String fileName = "차량관리(사고일지)_" + strNowDate + ".xlsx";
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
	
	//차량통계 엑셀
	@RequestMapping(value="/excel")
	public void excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xlsx");
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			Date nowDate = new Date();	        
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String strNowDate = simpleDateFormat.format(nowDate);
			
			XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet1 = workbook.createSheet("연료별");
	        XSSFSheet sheet2 = workbook.createSheet("설비별");
	        XSSFSheet sheet3 = workbook.createSheet("사업단별");
	        XSSFSheet sheet4 = workbook.createSheet("차량별");

	        Row excelRow = null;
            Cell cell = null;
            int rowNum1 = 0;	
            int rowNum2 = 0;	
            int rowNum3 = 0;	
            int rowNum4 = 0;	
         
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
            
            String[] header1 = {"구분", "", "수량", "",	"가동일", "",	"총가동거리", "", "총주유량", "", "L당거리", "", "일평균이동", ""};
            String[] header2 = {"구분", "", "수량", "",	"평균가동일", "",	"총가동거리", "", "총주유량", "", "L당거리", "", "일평균이동", ""};
            String[] header3 = {"차량번호", "", "운행거리", "",	"주유비", "",	"주차비", "", "통행료", "", "정비비", ""};
            String[] column = {"name", "value1", "value2", "value3", "value4", "value5", "value6"};
            String[] column2 = {"ve_number", "rc_drive", "rc_amount", "rc_park", "rc_pass", "rc_trouble"};
            
            //연료별 헤더
            excelRow = sheet1.createRow(rowNum1++);
            
            for(int i = 0; i < header1.length; i++) {
            	cell = excelRow.createCell(i);
                cell.setCellStyle(headerStyle);
                if(i%2 == 0) {
                	cell.setCellValue(header1[i]);
                	sheet1.addMergedRegion(new CellRangeAddress( 0, 0, i, i+1)); 
                }
            }
            
            //설비별 헤더
            excelRow = sheet2.createRow(rowNum2++);
            
            for(int i = 0; i < header2.length; i++) {
            	cell = excelRow.createCell(i);
                cell.setCellStyle(headerStyle);
                if(i%2 == 0) {
                	cell.setCellValue(header2[i]);
                	sheet2.addMergedRegion(new CellRangeAddress( 0, 0, i, i+1)); 
                }
            }
            
            //사업단별 헤더
            excelRow = sheet3.createRow(rowNum3++);
            
            for(int i = 0; i < header2.length; i++) {
            	cell = excelRow.createCell(i);
                cell.setCellStyle(headerStyle);
                if(i%2 == 0) {
                	cell.setCellValue(header2[i]);
                	sheet3.addMergedRegion(new CellRangeAddress( 0, 0, i, i+1)); 
                }
            }
            
            //차량별 헤더
            excelRow = sheet4.createRow(rowNum4++);
            
            for(int i = 0; i < header3.length; i++) {
            	cell = excelRow.createCell(i);
                cell.setCellStyle(headerStyle);
                if(i%2 == 0) {
                	cell.setCellValue(header3[i]);
                	sheet4.addMergedRegion(new CellRangeAddress( 0, 0, i, i+1)); 
                }
            }
            
            param.put("section", 1);
            List<Map<String, Object>> data1 = carService.revenue_list(param); // 연료별
            
            if(data1.size() > 0) {
				data1 = NullToString.nulltostring(data1);
				
				List<Map<String, Object>> data2 = carService.revenue_total(param);
				if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
				
				for(Map<String, Object> list : data2) data1.add(list);
			}			            
            
            param.put("section", 3);
            List<Map<String, Object>> data3 = carService.revenue_list(param); // 사업단별
            
            if(data3.size() > 0) {
            	data3 = NullToString.nulltostring(data3);
				
				List<Map<String, Object>> data4 = carService.revenue_total(param);
				if(data4.size() > 0) data4 = NullToString.nulltostring(data4);
				
				for(Map<String, Object> list : data4) data3.add(list);
			}
            
            param.put("section", 4);
            List<Map<String, Object>> data4 = carService.revenue_list(param); // 차량별
            
            if(data4.size() > 0) {
				data4 = NullToString.nulltostring(data4);
				
				List<Map<String, Object>> data2 = carService.revenue_total(param);
				if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
				
				for(Map<String, Object> list : data2) data4.add(list);
			}
            
            param.put("section", 2);
            List<Map<String, Object>> system = carService.system_list(param);
				
			if(system.size() > 0) param.put("system", system);
			else param.put("none_system", 1);						
			
			List<Map<String, Object>> data2 = carService.revenue_list(param); // 설비별
            
			if(data2.size() > 0) {
				data2 = NullToString.nulltostring(data2);
				
				List<Map<String, Object>> data5 = carService.revenue_total(param);
				if(data5.size() > 0) data5 = NullToString.nulltostring(data5);
				
				for(Map<String, Object> list : data5) data2.add(list);
			}
            
            //연료별 바디
            if(data1.size() > 0) {
            	for(Map<String, Object> list : data1) {
            		excelRow = sheet1.createRow(rowNum1++);
            		int idx = 0;
            		
            		for(int i = 0; i < header1.length; i++) {				            		
	            		cell = excelRow.createCell(i);
		                cell.setCellStyle(bodyStyle);
		                if(i%2 == 0) {
		                	cell.setCellValue(list.get(column[idx++]).toString());
		                	sheet1.addMergedRegion(new CellRangeAddress( rowNum1 - 1, rowNum1 - 1, i, i+1)); 
		                }
	            	}
            	}	
            }
            
            //설비별 바디
            if(data2.size() > 0) {
            	for(Map<String, Object> list : data2) {
            		excelRow = sheet2.createRow(rowNum2++);
            		int idx = 0;
            		
            		for(int i = 0; i < header1.length; i++) {				            		
	            		cell = excelRow.createCell(i);
		                cell.setCellStyle(bodyStyle);
		                if(i%2 == 0) {
		                	cell.setCellValue(list.get(column[idx++]).toString());
		                	sheet2.addMergedRegion(new CellRangeAddress( rowNum2 - 1, rowNum2 - 1, i, i+1)); 
		                }
	            	}
            	}		            	
            }
            
            //사업단별 바디
            if(data3.size() > 0) {
            	for(Map<String, Object> list : data3) {
            		excelRow = sheet3.createRow(rowNum3++);
            		int idx = 0;
            		
            		for(int i = 0; i < header1.length; i++) {				            		
	            		cell = excelRow.createCell(i);
		                cell.setCellStyle(bodyStyle);
		                if(i%2 == 0) {
		                	cell.setCellValue(list.get(column[idx++]).toString());
		                	sheet3.addMergedRegion(new CellRangeAddress( rowNum3 - 1, rowNum3 - 1, i, i+1)); 
		                }
	            	}
            	}	
            }
            
          //차량별 바디
            if(data4.size() > 0) {
            	for(Map<String, Object> list : data4) {
            		excelRow = sheet4.createRow(rowNum4++);
            		int idx = 0;
            		
            		for(int i = 0; i < header3.length; i++) {				            		
	            		cell = excelRow.createCell(i);
		                cell.setCellStyle(bodyStyle);
		                if(i%2 == 0) {
		                	cell.setCellValue(list.get(column2[idx++]).toString());
		                	sheet4.addMergedRegion(new CellRangeAddress( rowNum4 - 1, rowNum4 - 1, i, i+1)); 
		                }
	            	}
            	}	
            }
            
            String fileName = "차량통계(차량 운행실적)_" + strNowDate + ".xlsx";
            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
            
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

            workbook.write(response.getOutputStream());
            workbook.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
				
}
