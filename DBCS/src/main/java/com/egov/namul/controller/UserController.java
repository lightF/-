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
import com.egov.namul.service.UserService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Resource(name = "UserService")
	private UserService UserService;
	
	@Resource(name = "FileService")
	private FileService FileService;
	
	@RequestMapping(value = "/login")
	public void login(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			List<Map<String, Object>> data = UserService.login(param);

			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				
				for(String key : data.get(0).keySet()) session.setAttribute(key, data.get(0).get(key));
				session.setMaxInactiveInterval(3600*24);	
				session.setAttribute("page", "/index");
				jsonOut.write("{\"result\":" + JsonUtil.getJsonStringFromList(data) + ",\"code\":1" + ",\"url\":\"/home\"" + ",\"msg\":\"Login Success\"}");	
			}else jsonOut.write("{\"result\":[]" + ",\"code\":0" + ",\"url\":\"/home\"" + ",\"msg\":\"No Data\"}");				
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
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
			
			if(param.get("search_column") != null && !"".equals(param.get("search_column"))) param.put(param.get("search_column").toString(), param.get("search_word"));
			
			if(param.get("pos_name") != null && !"".equals(param.get("pos_name"))) { //직위 시퀀스 가져오기
				List<Map<String, Object>> seqList = UserService.position(param);
					
				if(seqList.size() > 0) param.put("pos_list", seqList);
				else param.put("pos_none", 1);
			}
			
			if(param.get("j_name") != null && !"".equals(param.get("j_name"))) { //직책 시퀀스 가져오기
				List<Map<String, Object>> seqList = UserService.job(param);
					
				if(seqList.size() > 0) param.put("j_list", seqList);
				else param.put("j_none", 1);
			}
			
			int page = 1;
			int row = 999;
			
			if(param.get("page") != null && !"".equals(param.get("page"))) page = Integer.parseInt(param.get("page").toString());
			if(param.get("row") != null && !"".equals(param.get("row"))) row = Integer.parseInt(param.get("row").toString());		
				
			int total = UserService.total(param);
			int startNum = (page - 1) * row;
					
			param.put("start_num", startNum);
			param.put("row_num", row);
				
			List<Map<String, Object>> data = UserService.list(param);
				
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
	public void edit(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		try {	
			String[] sc_seq = request.getParameterValues("sc_seq[]");
			String[] sc_enter = request.getParameterValues("sc_enter[]");
			String[] sc_finish = request.getParameterValues("sc_finish[]");
			String[] sc_name = request.getParameterValues("sc_name[]");
			String[] sc_major = request.getParameterValues("sc_major[]");
			String[] sc_relation = request.getParameterValues("sc_relation[]");
			String[] fe_seq = request.getParameterValues("fe_seq[]");
			String[] ed_seq = request.getParameterValues("ed_seq[]");
			String[] ed_finish = request.getParameterValues("ed_finish[]");
			String[] ed_expire = request.getParameterValues("ed_expire[]");
			String[] ed_organ = request.getParameterValues("ed_organ[]");
			String[] ed_process = request.getParameterValues("ed_process[]");
			String[] ed_edu = request.getParameterValues("ed_edu[]");
			String[] ed_score = request.getParameterValues("ed_score[]");
			String[] l_seq = request.getParameterValues("l_seq[]");
			String[] l_finish = request.getParameterValues("l_finish[]");
			String[] l_expire = request.getParameterValues("l_expire[]");
			String[] l_organ = request.getParameterValues("l_organ[]");
			String[] l_name = request.getParameterValues("l_name[]");
			String[] l_grade = request.getParameterValues("l_grade[]");
			String[] l_num = request.getParameterValues("l_num[]");
			String[] oc_seq = request.getParameterValues("oc_seq[]");
			String[] oc_join = request.getParameterValues("oc_join[]");
			String[] oc_resign = request.getParameterValues("oc_resign[]");
			String[] oc_name = request.getParameterValues("oc_name[]");
			String[] oc_note = request.getParameterValues("oc_note[]");
			String[] proj_seq = request.getParameterValues("proj_seq[]");
			String[] proj_start = request.getParameterValues("proj_start[]");
			String[] proj_end = request.getParameterValues("proj_end[]");
			String[] proj_place = request.getParameterValues("proj_place[]");
			String[] proj_name = request.getParameterValues("proj_name[]");
			String[] proj_part = request.getParameterValues("proj_part[]");
			String[] proj_position = request.getParameterValues("proj_position[]");
			String[] po_seq = request.getParameterValues("po_seq[]");
			String[] po_start = request.getParameterValues("po_start[]");
			String[] po_end = request.getParameterValues("po_end[]");
			String[] po_place = request.getParameterValues("po_place[]");
			String[] po_manage = request.getParameterValues("po_manage[]");
			String[] po_part = request.getParameterValues("po_part[]");
			String[] po_system = request.getParameterValues("po_system[]");
			String[] soc_seq = request.getParameterValues("soc_seq[]");
			String[] soc_start = request.getParameterValues("soc_start[]");
			String[] soc_end = request.getParameterValues("soc_end[]");
			String[] soc_place = request.getParameterValues("soc_place[]");
			String[] soc_manage = request.getParameterValues("soc_manage[]");
			String[] soc_part = request.getParameterValues("soc_part[]");
			String[] soc_system = request.getParameterValues("soc_system[]");
			
			String [] lv_seq = request.getParameterValues("lv_seq[]");
			String [] per_seq = request.getParameterValues("per_seq[]");
			String [] lv_cp = request.getParameterValues("lv_cp[]");
			String [] lv_no = request.getParameterValues("lv_no[]");
			String [] lv_date = request.getParameterValues("lv_date[]");
			String [] lv_level = request.getParameterValues("lv_level[]");
			String [] lv_standard = request.getParameterValues("lv_standard[]");
			String [] update_date = request.getParameterValues("update_date[]");
			
			multi.clear();
			
			if(sc_seq != null) { //학력사항
				for(int i = 0; i < sc_seq.length; i++) {
					int cnt = 0;

					if("".equals(sc_enter[i])) cnt++;
					if("".equals(sc_finish[i])) cnt++;
					if("".equals(sc_name[i])) cnt++;
					if("".equals(sc_major[i])) cnt++;
					if("".equals(sc_relation[i])) cnt++;
					if("".equals(fe_seq[i])) cnt++;
					
					if(cnt < 6) {
						if("".equals(sc_seq[i])) multi.add("sc_seq", 0);
						else multi.add("sc_seq", sc_seq[i]);
						
						multi.add("sc_enter", sc_enter[i]);
						multi.add("sc_finish", sc_finish[i]);						
						multi.add("sc_name", sc_name[i]);					
						multi.add("sc_major", sc_major[i]);
						
						if("".equals(sc_relation[i])) multi.add("sc_relation", 0);
						else multi.add("sc_relation", sc_relation[i]);
						
						if("".equals(fe_seq[i])) multi.add("fe_seq", 0);
						else multi.add("fe_seq", fe_seq[i]);
					}					
				}
				
				if(multi.get("sc_seq") != null) param.put("sc_seq", 1);	
			}
			
			if(ed_seq != null) { //교육이력 시퀀스
				for(int i = 0; i < ed_seq.length; i++) {
					int cnt = 0;

					if("".equals(ed_finish[i])) cnt++;
					if("".equals(ed_expire[i])) cnt++;
					if("".equals(ed_organ[i])) cnt++;
					if("".equals(ed_process[i])) cnt++;
					if("".equals(ed_edu[i])) cnt++;
					if("".equals(ed_score[i])) cnt++;
					
					if(cnt < 6) {
						if("".equals(ed_seq[i])) multi.add("ed_seq", 0);
						else multi.add("ed_seq", ed_seq[i]);
						
						multi.add("ed_finish", ed_finish[i]);
						multi.add("ed_expire", ed_expire[i]);						
						multi.add("ed_organ", ed_organ[i]);
						multi.add("ed_process", ed_process[i]);
						multi.add("ed_edu", ed_edu[i]);
						
						if("".equals(ed_score[i])) multi.add("ed_score", 0);
						else multi.add("ed_score", ed_score[i]);
					}				
				}
				
				if(multi.get("ed_seq") != null) param.put("ed_seq", 1);	
			}
			
			if(l_seq != null) { //자격면허 시퀀스
				for(int i = 0; i < l_seq.length; i++) {
					int cnt = 0;

					if("".equals(l_finish[i])) cnt++;
					if("".equals(l_expire[i])) cnt++;
					if("".equals(l_organ[i])) cnt++;
					if("".equals(l_name[i])) cnt++;
					if("".equals(l_grade[i])) cnt++;
					if("".equals(l_num[i])) cnt++;
					
					if(cnt < 6) {
						if("".equals(l_seq[i])) multi.add("l_seq", 0);
						else multi.add("l_seq", l_seq[i]);
						
						multi.add("l_finish", l_finish[i]);
						multi.add("l_expire", l_expire[i]);						
						multi.add("l_organ", l_organ[i]);
						multi.add("l_name", l_name[i]);
						multi.add("l_grade", l_grade[i]);
						multi.add("l_num", l_num[i]);
					}					
				}
				
				if(multi.get("l_seq") != null) param.put("l_seq", 1);	
			}
			
			if(oc_seq != null) { //타사이력 시퀀스
				for(int i = 0; i < oc_seq.length; i++) {
					int cnt = 0;

					if("".equals(oc_join[i])) cnt++;
					if("".equals(oc_resign[i])) cnt++;
					if("".equals(oc_name[i])) cnt++;
					if("".equals(oc_note[i])) cnt++;

					if(cnt < 4) {
						if("".equals(oc_seq[i])) multi.add("oc_seq", 0);
						else multi.add("oc_seq", oc_seq[i]);
						
						multi.add("oc_join", oc_join[i]);
						multi.add("oc_resign", oc_resign[i]);						
						multi.add("oc_name", oc_name[i]);
						multi.add("oc_note", oc_note[i]);
					}					
				}
				
				if(multi.get("oc_seq") != null) param.put("oc_seq", 1);	
			}
			
			if(proj_seq != null) { //프로젝트
				for(int i = 0; i < proj_seq.length; i++) {
					int cnt = 0;

					if("".equals(proj_start[i])) cnt++;
					if("".equals(proj_end[i])) cnt++;
					if("".equals(proj_place[i])) cnt++;
					if("".equals(proj_name[i])) cnt++;
					if("".equals(proj_part[i])) cnt++;
					if("".equals(proj_position[i])) cnt++;
					
					if(cnt < 6) {
						if("".equals(proj_seq[i])) multi.add("proj_seq", 0);
						else multi.add("proj_seq", proj_seq[i]);
						
						multi.add("proj_start", proj_start[i]);
						multi.add("proj_end", proj_end[i]);						
						multi.add("proj_place", proj_place[i]);
						multi.add("proj_name", proj_name[i]);
						multi.add("proj_part", proj_part[i]);
						multi.add("proj_position", proj_position[i]);
					}					
				}
				
				if(multi.get("proj_seq") != null) param.put("proj_seq", 1);	
			}
			
			if(po_seq != null) { //발주처
				for(int i = 0; i < po_seq.length; i++) {
					int cnt = 0;

					if("".equals(po_start[i])) cnt++;
					if("".equals(po_end[i])) cnt++;
					if("".equals(po_place[i])) cnt++;
					if("".equals(po_manage[i])) cnt++;
					if("".equals(po_part[i])) cnt++;
					if("".equals(po_system[i])) cnt++;
					
					if(cnt < 6) {
						if("".equals(po_seq[i])) multi.add("po_seq", 0);
						else multi.add("po_seq", po_seq[i]);
						
						multi.add("po_start", po_start[i]);
						multi.add("po_end", po_end[i]);						
						multi.add("po_place", po_place[i]);
						multi.add("po_manage", po_manage[i]);
						multi.add("po_part", po_part[i]);
						multi.add("po_system", po_system[i]);
					}					
				}
				
				if(multi.get("po_seq") != null) param.put("po_seq", 1);	
			}
			
			if(soc_seq != null) { //협회경력
				for(int i = 0; i < soc_seq.length; i++) {
					int cnt = 0;

					if("".equals(soc_start[i])) cnt++;
					if("".equals(soc_end[i])) cnt++;
					if("".equals(soc_place[i])) cnt++;
					if("".equals(soc_manage[i])) cnt++;
					if("".equals(soc_part[i])) cnt++;
					if("".equals(soc_system[i])) cnt++;
					
					if(cnt < 6) {
						if("".equals(soc_seq[i])) multi.add("soc_seq", 0);
						else multi.add("soc_seq", soc_seq[i]);
						
						multi.add("soc_start", soc_start[i]);
						multi.add("soc_end", soc_end[i]);						
						multi.add("soc_place", soc_place[i]);
						multi.add("soc_manage", soc_manage[i]);
						multi.add("soc_part", soc_part[i]);
						multi.add("soc_system", soc_system[i]);
					}				
				}
				
				if(multi.get("soc_seq") != null) param.put("soc_seq", 1);	
			}
			
			if(lv_seq != null) { //경력정보
				for(int i = 0; i < lv_seq.length; i++) {
					int cnt = 0;
					
					if("".equals(per_seq[i])) cnt++;
					if("".equals(lv_cp[i])) cnt++;
					if("".equals(lv_no[i])) cnt++;
					if("".equals(lv_date[i])) cnt++;
					if("".equals(lv_level[i])) cnt++;
					if("".equals(lv_standard[i])) cnt++;
					if("".equals(update_date[i])) cnt++;
					
					if(cnt < 7) {
						if("".equals(lv_seq[i])) multi.add("lv_seq", 0);
						else multi.add("lv_seq", lv_seq[i]);
						
						multi.add("per_seq", per_seq[i]);
						multi.add("lv_cp", lv_cp[i]);						
						multi.add("lv_no", lv_no[i]);
						multi.add("lv_date", lv_date[i]);
						multi.add("lv_level", lv_level[i]);
						multi.add("lv_standard", lv_standard[i]);
						multi.add("update_date", update_date[i]);
					}				
				}
				
				if(multi.get("lv_seq") != null) param.put("lv_seq", 1);	
			}
			
			param.put("multi", multi);		
			UserService.edit(param, null);
			
			if(StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) { //파일업로드
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				List<MultipartFile> multiFiles = multiRequest.getFiles("upload[]");	 			
				
				String[] fileSeq = request.getParameterValues("f_seq");
				String[] fileDel = request.getParameterValues("f_del");
				param.put("tb_name", "personnel");
				
				if(fileDel != null) FileService.multi_resize_upload(param, multiFiles, "personnel_file/", fileSeq, fileDel, 136, 172);
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
	
	@RequestMapping(value="/detail")
	public void detail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			param.put("section", 1); //기본정보
			List<Map<String, Object>> data1 = UserService.profile(param);
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);
			
			param.put("section", 2); //학력사항
			List<Map<String, Object>> data2 = UserService.profile(param);
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			param.put("section", 3); //교육이력
			List<Map<String, Object>> data3 = UserService.profile(param);
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);
			
			param.put("section", 4); //자격면허
			List<Map<String, Object>> data4 = UserService.profile(param);
			if(data4.size() > 0) data4 = NullToString.nulltostring(data4);
			
			param.put("section", 5); //타사이력
			List<Map<String, Object>> data5 = UserService.profile(param);
			if(data5.size() > 0) data5 = NullToString.nulltostring(data5);
			
			param.put("section", 6); //자사이력
			List<Map<String, Object>> data6 = UserService.profile(param);
			if(data6.size() > 0) data6 = NullToString.nulltostring(data6);
			
			param.put("section", 7); //프로젝트
			List<Map<String, Object>> data7 = UserService.profile(param);
			if(data7.size() > 0) data7 = NullToString.nulltostring(data7);
			
			param.put("section", 8); //발주처
			List<Map<String, Object>> data8 = UserService.profile(param);
			if(data8.size() > 0) data8 = NullToString.nulltostring(data8);
			
			param.put("section", 9); //협회경력
			List<Map<String, Object>> data9 = UserService.profile(param);
			if(data9.size() > 0) data9 = NullToString.nulltostring(data9);
			
			param.put("section", 10); //기타정보
			List<Map<String, Object>> data10 = UserService.profile(param);
			if(data10.size() > 0) data10 = NullToString.nulltostring(data10);
			
			param.put("section", 11); //경력수첩
			List<Map<String, Object>> data11 = UserService.select_level(param);
			if(data11.size() > 0) data11 = NullToString.nulltostring(data11);
			
			jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data1) 
			+ ",\"result2\":" + JsonUtil.getJsonArrayFromList(data2)
			+ ",\"result3\":" + JsonUtil.getJsonArrayFromList(data3)
			+ ",\"result4\":" + JsonUtil.getJsonArrayFromList(data4)
			+ ",\"result5\":" + JsonUtil.getJsonArrayFromList(data5)
			+ ",\"result6\":" + JsonUtil.getJsonArrayFromList(data6)
			+ ",\"result7\":" + JsonUtil.getJsonArrayFromList(data7)
			+ ",\"result8\":" + JsonUtil.getJsonArrayFromList(data8)
			+ ",\"result9\":" + JsonUtil.getJsonArrayFromList(data9)
			+ ",\"result10\":" + JsonUtil.getJsonArrayFromList(data10)
			+ ",\"result11\":" + JsonUtil.getJsonArrayFromList(data11)
			+ ",\"size\":" + data1.size()
			+ ",\"size2\":" + data2.size()
			+ ",\"size3\":" + data3.size()
			+ ",\"size4\":" + data4.size()
			+ ",\"size5\":" + data5.size()
			+ ",\"size6\":" + data6.size()
			+ ",\"size7\":" + data7.size()
			+ ",\"size8\":" + data8.size()
			+ ",\"size9\":" + data9.size()
			+ ",\"size10\":" + data10.size()
			+ ",\"size11\":" + data11.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
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
			param.put("tb_name", "personnel");
			UserService.delete(param);	
			FileService.delete(param, "personnel_file/");
			jsonOut.write("{\"result\":[],\"code\":1, \"msg\":\"Data Delete Success\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Delete Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
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
			
			param.put("section", 1); //정규직
			List<Map<String, Object>> data1 = UserService.table(param);					
			param.put("section", 5); //정규직 총계
			List<Map<String, Object>> data2 = UserService.table(param);
			
			if(data1.size() > 0) data1 = NullToString.nulltostring(data1);	
			if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
			
			for(Map<String, Object> list : data2) data1.add(list);
			
			param.put("section", 2); //정규직 계
			List<Map<String, Object>> data3 = UserService.table(param);			
			param.put("section", 6); //정규직 계의 총계
			List<Map<String, Object>> data4 = UserService.table(param);
			
			if(data3.size() > 0) data3 = NullToString.nulltostring(data3);
			if(data4.size() > 0) data4 = NullToString.nulltostring(data4);
			
			for(Map<String, Object> list : data4) data3.add(list);
			
			param.put("section", 3); //비정규직
			List<Map<String, Object>> data5 = UserService.table(param);			
			param.put("section", 7); //비정규직 총계
			List<Map<String, Object>> data6 = UserService.table(param);
			
			if(data5.size() > 0) data5 = NullToString.nulltostring(data5);
			if(data6.size() > 0) data6 = NullToString.nulltostring(data6);
			
			for(Map<String, Object> list : data6) data5.add(list);
			
			param.put("section", 4); //비정규직 계
			List<Map<String, Object>> data7 = UserService.table(param);			
			param.put("section", 8); //비정규직 계의 총계
			List<Map<String, Object>> data8 = UserService.table(param);
			
			if(data7.size() > 0) data7 = NullToString.nulltostring(data7);
			if(data8.size() > 0) data8 = NullToString.nulltostring(data8);
			
			for(Map<String, Object> list : data8) data7.add(list);
			
			List<Map<String, Object>> data9 = UserService.org_group(param);
			List<Map<String, Object>> data10 = UserService.job_grade_list(param);
			
			if(data9.size() > 0) data9 = NullToString.nulltostring(data9);
			if(data10.size() > 0) data10 = NullToString.nulltostring(data10);
			
			List<Object> data = new ArrayList<Object>();	
			
			if(data1.size() > 0) {
				for(int i = 0; i < data1.size()/data10.size(); i++) {
					List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();

					for(int j = (i*data10.size()); j < (i*data10.size()) + data10.size(); j++) tmpList.add(data1.get(j));
					tmpList.add(data3.get(i));
					for(int j = (i*3); j < (i*3) + 3; j++) tmpList.add(data5.get(j));	
					tmpList.add(data7.get(i));
					
					data.add(tmpList);
				}
			}
			
			jsonOut.write("{\"result\":" + JsonUtil.ListToJson(data) 
			+ ",\"size\":" + data1.size()
			+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	@RequestMapping(value="/type/chart")
	public void typeChart(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			int total = UserService.person_total(param); //전체 사원수
			
			param.put("section", 1);
			param.put("total", total);
			List<Map<String, Object>> data = UserService.rate(param); //통계
			
			jsonOut.write("{\"result\":["+JsonUtil.getJsonArrayFromList(data) + "]"
					+ ",\"total\":" + total
					+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}		
	}
	
	@RequestMapping(value="/group/chart")
	public void groupChart(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if(request.getAttribute("auth") != null) {
				param.put("auth", request.getAttribute("auth"));
				param.put("auth_seq", request.getAttribute("auth_seq"));
			}
			
			int total = UserService.person_total(param); //전체 사원수
			
			param.put("section", 2);
			param.put("total", total);
			List<Map<String, Object>> data = UserService.rate(param); //통계
			
			jsonOut.write("{\"result\":["+JsonUtil.getJsonArrayFromList(data) + "]"
					+ ",\"total\":" + total
					+ ",\"code\":1}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}		
	}
	
	@RequestMapping(value="/check")
	public void check(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			int result = UserService.user_id(param);
			if(result > 0) jsonOut.write("{\"result\":[]" + ",\"count\":" + result + ",\"code\":1}");
			else jsonOut.write("{\"result\":[]" + ",\"count\":" + result + ",\"code\":0}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//인사통계 엑셀
		@RequestMapping(value="/excel")
		public void excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");
			
			try {
				Date nowDate = new Date();	        
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);
				
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("직급별 인원 현황");

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
	            
	            param.put("section", 1); //정규직
				List<Map<String, Object>> data1 = UserService.table(param);					
				param.put("section", 5); //정규직 총계
				List<Map<String, Object>> data2 = UserService.table(param);
				
				if(data1.size() > 0) data1 = NullToString.nulltostring(data1);	
				if(data2.size() > 0) data2 = NullToString.nulltostring(data2);
				
				for(Map<String, Object> list : data2) data1.add(list);
				
				param.put("section", 2); //정규직 계
				List<Map<String, Object>> data3 = UserService.table(param);			
				param.put("section", 6); //정규직 계의 총계
				List<Map<String, Object>> data4 = UserService.table(param);
				
				if(data3.size() > 0) data3 = NullToString.nulltostring(data3);
				if(data4.size() > 0) data4 = NullToString.nulltostring(data4);
				
				for(Map<String, Object> list : data4) data3.add(list);
				
				param.put("section", 3); //비정규직
				List<Map<String, Object>> data5 = UserService.table(param);			
				param.put("section", 7); //비정규직 총계
				List<Map<String, Object>> data6 = UserService.table(param);
				
				if(data5.size() > 0) data5 = NullToString.nulltostring(data5);
				if(data6.size() > 0) data6 = NullToString.nulltostring(data6);
				
				for(Map<String, Object> list : data6) data5.add(list);
				
				param.put("section", 4); //비정규직 계
				List<Map<String, Object>> data7 = UserService.table(param);			
				param.put("section", 8); //비정규직 계의 총계
				List<Map<String, Object>> data8 = UserService.table(param);
				
				if(data7.size() > 0) data7 = NullToString.nulltostring(data7);
				if(data8.size() > 0) data8 = NullToString.nulltostring(data8);
				
				for(Map<String, Object> list : data8) data7.add(list);
				
				List<Map<String, Object>> data9 = UserService.org_group(param);
				List<Map<String, Object>> data10 = UserService.job_grade_list(param);
				
				if(data9.size() > 0) data9 = NullToString.nulltostring(data9);
				if(data10.size() > 0) data10 = NullToString.nulltostring(data10);
				
				List<Object> data = new ArrayList<Object>();	
				
				if(data1.size() > 0) {
					for(int i = 0; i < data1.size()/data10.size(); i++) {
						List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();

						for(int j = (i*data10.size()); j < (i*data10.size()) + data10.size(); j++) tmpList.add(data1.get(j));
						tmpList.add(data3.get(i));
						for(int j = (i*3); j < (i*3) + 3; j++) tmpList.add(data5.get(j));	
						tmpList.add(data7.get(i));
						
						data.add(tmpList);
					}
				}
				
				String[] header_title_01 = {"계약", "인턴", "촉탁", "계"};
				
				//헤더		
				excelRow = sheet.createRow(rowNum++);

				cell = excelRow.createCell(0);
	            cell.setCellStyle(headerStyle);
	            cell.setCellValue("구분");
	            
	            if(data10.size() > 0) {
	            	int idx = 0;
	            	
	            	for(int i = 1; i < data10.size() + 2; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);
	                    cell.setCellValue("정규직");
	            	}
	            	
	            	for(int i = data10.size() + 2; i < data10.size() + 6; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);
	                    cell.setCellValue(header_title_01[idx++]);
	                    
	                    sheet.addMergedRegion(new CellRangeAddress( 0, 2, i, i));
	            	}
	            	
	            	//셀 병합
	            	sheet.addMergedRegion(new CellRangeAddress( 0, 2, 0, 0));            	
	            	sheet.addMergedRegion(new CellRangeAddress( 0, 0, 1, data10.size() + 1)); 
	            }else {
	            	for(int i = 1; i < header_title_01.length + 1; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);
	                    cell.setCellValue(header_title_01[i-1]);
	            	}
	            }
	            
	            //두번쨰 헤더
	            excelRow = sheet.createRow(rowNum++);
	            
	            cell = excelRow.createCell(0);
	            cell.setCellStyle(headerStyle);
	            
	            if(data10.size() > 0) {
	            	int idx = 0;
	            	
	            	for(int i = 1; i < data10.size() + 2; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);                    
	                    if(i < data10.size() + 1) cell.setCellValue(data10.get(idx++).get("job").toString());
	                    else cell.setCellValue("계");
	            	}
	            	
	            	for(int i = data10.size() + 2; i < data10.size() + 6; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);
	            	}
	            }else {
	            	for(int i = 1; i < header_title_01.length + 1; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);
	            	}
	            }
	            
	            //세번째 헤더
	            excelRow = sheet.createRow(rowNum++);
	            
	            cell = excelRow.createCell(0);
	            cell.setCellStyle(headerStyle);
	            
	            if(data10.size() > 0) {
	            	int idx = 0;
	            	
	            	for(int i = 1; i < data10.size() + 2; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);                    
	                    if(i < data10.size() + 1) cell.setCellValue(data10.get(idx++).get("grade").toString());
	                    else cell.setCellValue("계");
	            	}
	            	
	            	for(int i = data10.size() + 2; i < data10.size() + 6; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);
	            	}
	            	
	            	//셀 병합
	            	sheet.addMergedRegion(new CellRangeAddress( 1, 2, data10.size() + 1, data10.size() + 1));
	            }else {
	            	for(int i = 1; i < header_title_01.length + 1; i++) {
	            		cell = excelRow.createCell(i);
	                    cell.setCellStyle(headerStyle);
	            	}
	            }
	            
	            //바디
	            if(data.size() > 0) {
	            	for(int i = 0; i < data.size(); i++) {
						@SuppressWarnings("unchecked")
						List<Map<String, Object>> dataList = (List<Map<String, Object>>) data.get(i);
						excelRow = sheet.createRow(rowNum++);
						
						int idx2 = 0;
						
						for(int j = 0; j < dataList.size() + 1; j++) {
							cell = excelRow.createCell(j);
			                cell.setCellStyle(bodyStyle);
			                if(j == 0) cell.setCellValue(dataList.get(0).get("area").toString());
			                else cell.setCellValue(dataList.get(idx2++).get("value").toString());		                
						}			
					}
	            }
	            
	            String fileName = "인사통계(직급별 인원 현황)_" + strNowDate + ".xlsx";
	            String outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
	            
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "attachment;fileName=\"" + outputFileName + "\"");

	            workbook.write(response.getOutputStream());
	            workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value="/list/excel")
		public void list_excel(@RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");

			try {
				
				int page = 1;
				int row = 999;
				
				if(param.get("page") != null && !"".equals(param.get("page"))) page = Integer.parseInt(param.get("page").toString());
				if(param.get("row") != null && !"".equals(param.get("row"))) row = Integer.parseInt(param.get("row").toString());	

				
				int startNum = (page - 1) * row;

				param.put("start_num", startNum);
				param.put("row_num", row);
				
				Date nowDate = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);

				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("직원관리");
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
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

				// 셀병합
				sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 6)); // 첫행, 마지막행, 첫열, 마지막열 병합

				// 테이블 스타일 설정
				CellStyle tableCellStyle = wb.createCellStyle();
				tableCellStyle.setBorderTop((short) 1); // 테두리 위쪽
				tableCellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
				tableCellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
				tableCellStyle.setBorderRight((short) 1); // 테두리 오른쪽
				tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				tableCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				// Header
				row1 = sheet.createRow(rowNum++);
				cell = row1.createCell((short) 0);
				cell.setCellStyle(headStyle);
				cell.setCellValue("직원관리");

				sheet.createRow(rowNum++);
				row1 = sheet.createRow(rowNum++); // 빈행 추가
				
				List<Map<String, Object>> list = UserService.select_member_list_excel(param);

				if(list.size() > 0) {
					list = NullToString.nulltostring(list);
				
				String[] title = {"사번","이름","사업단","팀","담당업무","직위","직급"};
				
				
				for(int i=0; i<title.length; i++) {
					cell = row1.createCell(i);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(title[i]);
				}
				
				if(list.isEmpty()) {
					sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 8));
					row1 = sheet.createRow(rowNum++);
					cell = row1.createCell(0);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue("표시할 데이터가 없습니다.");
				}else {
				for(int i=0; i<list.size(); i++) {
					row1 = sheet.createRow(rowNum++);
					cell = row1.createCell(0);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list.get(i).get("per_id").toString());

					cell = row1.createCell(1);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list.get(i).get("per_name").toString());

					cell = row1.createCell(2);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list.get(i).get("per_organize").toString());

					cell = row1.createCell(3);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list.get(i).get("per_team").toString());

					cell = row1.createCell(4);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list.get(i).get("at_seq").toString());

					cell = row1.createCell(5);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list.get(i).get("pos_seq").toString());

					cell = row1.createCell(6);
					cell.setCellStyle(tableCellStyle);
					cell.setCellValue(list.get(i).get("jg_seq").toString());
				}
			}
		}

				String fileName = "인사관리(직원관리)_" + strNowDate + ".xlsx";
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
		
		//직원관리 엑셀
		@RequestMapping(value="/detail/excel")
		public void detail_excel(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/xlsx");

			try {
				Date nowDate = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String strNowDate = simpleDateFormat.format(nowDate);

				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("직원관리 상세보기");
				XSSFRow row = null;
				XSSFCell cell = null;
				int rowNum = 0;
				
				sheet.autoSizeColumn(0);

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
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

				// 테이블 스타일 설정
				CellStyle tableCellStyle = wb.createCellStyle();
				tableCellStyle.setBorderTop((short) 1); // 테두리 위쪽
				tableCellStyle.setBorderBottom((short) 1); // 테두리 아래쪽
				tableCellStyle.setBorderLeft((short) 1); // 테두리 왼쪽
				tableCellStyle.setBorderRight((short) 1); // 테두리 오른쪽
				tableCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				tableCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

				// Header
				row = sheet.createRow(rowNum++);
				cell = row.createCell((short) 0);
				cell.setCellStyle(headStyle);
				cell.setCellValue("직원관리 상세보기");	
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
				
				param.put("section", 1);
				List<Map<String, Object>> list = UserService.select_profile_excel(param);
				if(list.size() > 0) list = NullToString.nulltostring(list);
				
				int rowCnt = 10;
				int colCnt = 10;
				
				String[] title = {"성명","생년월일","사업단","소속팀","근무지","사번","이메일","우편번호","전화번호","입사일"};
				String[] title2 = {"영문","한문","관할지역","담당업무","직급","직위","직책","주소","휴대폰번호","승진일"};
				String[] title3 = {"","","","","","","직종","상세주소","사무실번호","퇴사일"};
				String[] data = {"per_name", "per_birth", "per_organize", "per_team", "per_place", "per_id", "per_email", "per_zip", "per_home","per_join"};			
				String[] data2 = {"per_ename", "per_cname", "per_area", "at_seq", "jg_seq", "pos_seq", "j_seq", "per_addr", "per_mobile", "per_promote"};			
				String[] data3 = {"", "", "", "", "", "", "per_type", "per_detail", "per_office", "per_resign"};
				
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
								case 3 : 
									cell.setCellStyle(cellStyle);
					               	cell.setCellValue(title2[i]);
									break;
								case 4 : 
									cell.setCellStyle(tableCellStyle);
									cell.setCellValue(list.get(0).get(data2[i]).toString());
									break;
								case 7 : 
									cell.setCellStyle(cellStyle);
					               	cell.setCellValue(title3[i]);
									break;
								case 8 : 
									cell.setCellStyle(tableCellStyle);
									if(i < 6) cell.setCellValue(data3[i]);
									else cell.setCellValue(list.get(0).get(data3[i]).toString());
									break;
								default : 
									cell.setCellStyle(tableCellStyle);
									break;
							}
						}
							
						sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 1, 2));
						sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 4, 6));
						sheet.addMergedRegion(new CellRangeAddress( rowNum - 1, rowNum - 1, 8, 9));
					}
					
					for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가				
					
					String[] title4 = {"입학일","졸업(예정)일","학교명","전공","업무관련성","최종학력"};
					String[] column4 = {"sc_enter", "sc_finish", "sc_name", "sc_major", "sc_relation", "fe_seq"};
					
					row = sheet.createRow(rowNum++);
					
					for(int i=0; i<title4.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(title4[i]);
					}
					
					param.put("section", 2);
					List<Map<String, Object>> list2 = UserService.select_profile_excel(param);
					if(list2.size() > 0) {
						list2 = NullToString.nulltostring(list2);					
						
						for(Map<String, Object> datas : list2) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title4.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get(column4[i]).toString());
							}
						}
					}
					
					for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가				
					
					String[] title5 = {"수료일","만료일","교육기관","과정명","교육명","점수"};
					String[] column5 = {"ed_finish", "ed_expire", "ed_organ", "ed_process", "ed_edu", "ed_score"};
					
					row = sheet.createRow(rowNum++);
					
					for(int i=0; i<title5.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(title5[i]);
					}
					
					param.put("section", 3);
					List<Map<String, Object>> list3 = UserService.select_profile_excel(param);
					if(list3.size() > 0) {
						list3 = NullToString.nulltostring(list3);					
						
						for(Map<String, Object> datas : list3) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title5.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get(column5[i]).toString());
							}
						}
					}
					
					for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가				
					
					String[] title6 = {"수료일","만료일","발행기관","자격증명","등급","일련번호"};
					String[] column7 = {"l_finish", "l_expire", "l_organ", "l_name", "l_grade", "l_num"};
					
					row = sheet.createRow(rowNum++);
					
					for(int i=0; i<title6.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(title6[i]);
					}
					
					param.put("section", 4);
					List<Map<String, Object>> list4 = UserService.select_profile_excel(param);
					if(list4.size() > 0) {
						list4 = NullToString.nulltostring(list4);					
						
						for(Map<String, Object> datas : list4) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title6.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get(column7[i]).toString());
							}
						}
					}
					
					for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가				
					
					String[] title7 = {"입사일","퇴사일","회사명","비고"};
					String[] column8 = {"oc_join", "oc_resign", "oc_name", "oc_note"};
					
					row = sheet.createRow(rowNum++);
					
					for(int i=0; i<title7.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(title7[i]);
					}
					
					param.put("section", 5);
					List<Map<String, Object>> list5 = UserService.select_profile_excel(param);
					if(list5.size() > 0) {
						list5 = NullToString.nulltostring(list5);					
						
						for(Map<String, Object> datas : list5) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title7.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get(column8[i]).toString());
							}
						}
					}
					
					for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가				
					
					String[] title8 = {"발령일","종료일","근무지","소속팀","담당업무","직위"};
					String[] column9 = {"c_appoint", "c_finish", "c_place", "c_team", "c_part", "c_position"};
					
					row = sheet.createRow(rowNum++);
					
					for(int i=0; i<title8.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(title8[i]);
					}
					
					param.put("section", 6);
					List<Map<String, Object>> list6 = UserService.select_profile_excel(param);
					if(list6.size() > 0) {
						list6 = NullToString.nulltostring(list6);					
						
						for(Map<String, Object> datas : list6) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title8.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get(column9[i]).toString());
							}
						}
					}
					
					for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가				
					
					String[] title9 = {"시작일","종료일","근무지","프로잭트명","담당업무","직위"};
					String[] column10 = {"proj_start", "proj_end", "proj_place", "proj_name", "proj_part", "proj_position"};
					
					row = sheet.createRow(rowNum++);
					
					for(int i=0; i<title9.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(title9[i]);
					}
					
					param.put("section", 7);
					List<Map<String, Object>> list7 = UserService.select_profile_excel(param);
					if(list7.size() > 0) {
						list7 = NullToString.nulltostring(list7);					
						
						for(Map<String, Object> datas : list7) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title9.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get(column10[i]).toString());
							}
						}
					}
					
					for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가				
					
					String[] title10 = {"시작일","종료일","근무지","참여사업","담당업무","담당설비"};
					String[] column11 = {"po_start", "po_end", "po_place", "po_manage", "po_part", "po_system"};
					
					row = sheet.createRow(rowNum++);
					
					for(int i=0; i<title10.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(title10[i]);
					}
					
					param.put("section", 8);
					List<Map<String, Object>> list8 = UserService.select_profile_excel(param);
					if(list8.size() > 0) {
						list8 = NullToString.nulltostring(list8);					
						
						for(Map<String, Object> datas : list8) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title10.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get(column11[i]).toString());
							}
						}
					}
					
					for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가				
					
					String[] title11 = {"시작일","종료일","근무지","참여사업","담당업무","담당설비"};
					String[] column12 = {"soc_start", "soc_end", "soc_place", "soc_manage", "soc_part", "soc_system"};
					
					row = sheet.createRow(rowNum++);
					
					for(int i=0; i<title11.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(title11[i]);
					}
					
					param.put("section", 9);
					List<Map<String, Object>> list9 = UserService.select_profile_excel(param);
					if(list9.size() > 0) {
						list9 = NullToString.nulltostring(list9);					
						
						for(Map<String, Object> datas : list9) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title11.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get(column12[i]).toString());
							}
						}
					}
					
					for(int i = 0; i < 2; i++) row = sheet.createRow(rowNum++); // 빈행 추가				
					
					String[] title12 = {"차종","차량번호","명의","재직상태","권한등급","비밀번호"};
					String[] column13 = {"car_model", "car_number", "car_name", "per_status", "ag_seq", "per_pwd"};
					
					row = sheet.createRow(rowNum++);
					
					for(int i=0; i<title12.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(title12[i]);
					}
					
					param.put("section", 10);
					List<Map<String, Object>> list10 = UserService.select_profile_excel(param);
					if(list10.size() > 0) {
						list10 = NullToString.nulltostring(list10);					
						
						for(Map<String, Object> datas : list10) {
							row = sheet.createRow(rowNum++);
							
							for(int i = 0; i < title12.length; i++) {
								cell = row.createCell(i);
								cell.setCellStyle(tableCellStyle);
								cell.setCellValue(datas.get(column13[i]).toString());
							}
						}
					}
					
				String fileName = "인사관리(직원관리 상세보기)_" + strNowDate + ".xlsx";
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
