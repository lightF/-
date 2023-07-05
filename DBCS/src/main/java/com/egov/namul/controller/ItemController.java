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

import com.egov.namul.service.ItemService;
import com.egov.namul.util.JsonUtil;
import com.egov.namul.util.NullToString;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
	
	@Resource(name = "ItemService")
	private ItemService ItemService;
	
	@RequestMapping(value = "/{page}/list")
	public void list(@PathVariable("page") String page, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
		
		try {
			if("task".equals(page)) param.put("tb_name", "assign_task");
			else if("job".equals(page)) param.put("tb_name", "job");
			else if("grade".equals(page)) param.put("tb_name", "job_grade");
			else if("position".equals(page)) param.put("tb_name", "position");
			else if("edu".equals(page)) param.put("tb_name", "final_education");			
			else if("process".equals(page)) param.put("tb_name", "process");			
			else if("division".equals(page)) param.put("tb_name", "work_division");			
			else if("type".equals(page)) param.put("tb_name", "work_type");
			else if("pay".equals(page)) param.put("tb_name", "pay");			
			else if("model".equals(page)) param.put("tb_name", "model");
			else if("safe".equals(page)) param.put("tb_name", "safe");			
			else if("fuel".equals(page)) param.put("tb_name", "fuel");
			else if("system".equals(page)) param.put("tb_name", "system");
			else if("budget".equals(page)) param.put("tb_name", "budget");
			else if("branch".equals(page)) param.put("tb_name", "branch");
			else { //업체는 페이징 필요
				if(param.get("search_column") != null && !"".equals(param.get("search_column"))) param.put(param.get("search_column").toString(), param.get("search_word"));				
				param.put("tb_name", "enterprise");
				
				int pageData = 1;
				int rowData = 999;
				
				if(param.get("page") != null && !param.get("page").toString().isEmpty()) pageData = Integer.parseInt(param.get("page").toString());
				if(param.get("row") != null && !param.get("row").toString().isEmpty()) rowData = Integer.parseInt(param.get("row").toString());

				int total = ItemService.item_total(param);	
				int startNum = (pageData - 1) * rowData;
				
				param.put("start_num", startNum);
				param.put("row_num", rowData);
				
				List<Map<String, Object>> data = ItemService.item(param);
				
				if(data.size() > 0) {
					data = NullToString.nulltostring(data);
					jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" + total	+ ",\"row\":" + rowData  + ",\"page\":" + pageData + ",\"code\":1}");
				}else jsonOut.write("{\"result\":[]" + ",\"total\":" + total	+ ",\"row\":" + rowData  + ",\"page\":" + pageData + ",\"code\":1, \"msg\":\"No Data\"}");
				
				return;
			}
			
			List<Map<String, Object>> data = ItemService.item(param);
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				jsonOut.write("{\"result\":" + JsonUtil.getJsonArrayFromList(data) + ",\"total\":" +  data.size()	+ ",\"code\":1}");
			}else jsonOut.write("{\"result\":[]" + ",\"total\":" + data.size() + ",\"code\":1, \"msg\":\"No Data\"}");
		}catch(Exception e) {
			e.printStackTrace();
			jsonOut.write("{\"result\":[],\"code\":0, \"msg\":\"Data Load Fail\"}");
		}finally {
			jsonOut.flush();
			jsonOut.close();
		}
	}
	
	//항목관리 추가/수정/삭제
	@RequestMapping(value = "/{page}/edit")
	public void task_edit(@PathVariable("page") String page, @RequestParam Map<String, Object> param, @RequestParam MultiValueMap<String, Object> multi, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter jsonOut = null;

		jsonOut = response.getWriter();
			
		try {
			if("task".equals(page)) param.put("tb_name", "assign_task");
			else if("job".equals(page)) param.put("tb_name", "job");
			else if("grade".equals(page)) param.put("tb_name", "job_grade");
			else if("position".equals(page)) param.put("tb_name", "position");
			else if("edu".equals(page)) param.put("tb_name", "final_education");			
			else if("process".equals(page)) param.put("tb_name", "process");			
			else if("division".equals(page)) param.put("tb_name", "work_division");			
			else if("type".equals(page)) param.put("tb_name", "work_type");
			else if("pay".equals(page)) param.put("tb_name", "pay");			
			else if("model".equals(page)) param.put("tb_name", "model");
			else if("safe".equals(page)) param.put("tb_name", "safe");			
			else if("fuel".equals(page)) param.put("tb_name", "fuel");
			else if("system".equals(page)) param.put("tb_name", "system");
			else if("budget".equals(page)) param.put("tb_name", "budget");
			else if("branch".equals(page)) param.put("tb_name", "branch");
			else param.put("tb_name", "enterprise");
			
			String[] seq = request.getParameterValues("seq[]");
			String[] division = request.getParameterValues("division[]");
			String[] name = request.getParameterValues("name[]");

			if(name != null) {
				for(int i = 0; i < name.length; i++) {
					if(!"".equals(name[i])) {
						multi.add("name", name[i]);
						if(seq != null) multi.add("seq", seq[i]);
						if(division != null) multi.add("division", division[i]);
					}					
				}
				
				if(multi.get("name").size() > 0) {
					param.put("size", multi.get("name").size());
					if(division != null) param.put("division", 1);
				}
			}	
			
			param.put("multi", multi);
			ItemService.item_edit(param);
			
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
