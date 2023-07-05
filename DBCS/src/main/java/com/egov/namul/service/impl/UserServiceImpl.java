package com.egov.namul.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.egov.namul.mapper.OrganizeMapper;
import com.egov.namul.mapper.UserMapper;
import com.egov.namul.service.UserService;
import com.egov.namul.util.ImageResize;
import com.egov.namul.util.NullToString;
import com.egov.namul.util.SHAUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("UserService")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService {

	@Resource(name="UserMapper")
	private UserMapper userDAO;
	
	@Resource(name="OrganizeMapper")
	private OrganizeMapper organizeDAO;
	
	@Resource(name = "fileUploadProperty")
	private Properties fileUploadProperty;
	
	@Override
	public List<Map<String, Object>> login(Map<String, Object> param) throws Exception { //로그인
		// TODO Auto-generated method stub
		if(param.get("pw") != null) param.put("password", SHAUtil.SHA512Encrypt(param.get("pw").toString()));		
		return userDAO.select_member(param);
	}

	@Override
	public List<Map<String, Object>> organize(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_org_seq(param);
	}

	@Override
	public List<Map<String, Object>> assign_task(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_task_seq(param);
	}

	@Override
	public List<Map<String, Object>> position(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_position_seq(param);
	}

	@Override
	public List<Map<String, Object>> job_grade(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_grade_seq(param);
	}

	@Override
	public List<Map<String, Object>> job(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_job_seq(param);
	}

	@Override
	public int total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_member_count(param);
	}

	@Override
	public List<Map<String, Object>> list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_member_list(param);
	}

	@Override
	public void edit(Map<String, Object> param, MultipartFile multiFile) throws Exception {
		try {
		// TODO Auto-generated method stub
		boolean isUpdate = false;		
		if(param.get("seq") != null && !"".equals(param.get("seq"))) isUpdate = true;		
		if(param.get("per_pwd") != null && !"".equals(param.get("per_pwd"))) param.put("per_pwd", SHAUtil.SHA512Encrypt(param.get("per_pwd").toString()));
		
		if(isUpdate) { //소속팀, 담당업무, 직위 업데이트 발생시 자사이력에 남기기
			param.put("section", 1);
			int status = userDAO.select_career_status(param); //변동 상태 확인
		
			if(status == 1001) {
				param.put("section", 2);
				int seq = userDAO.select_career_status(param); //마지막 자사이력 시퀀스 가져오기
				
				if(seq > 0) { //마지막 이력 종료일 저장
					param.put("c_seq", seq);					
					param.put("section", 2);										
				}else {	 //입사일 저장
					param.put("section", 1);
				}
				
				List<Map<String, Object>> data1 = userDAO.select_career_history(param);				
				for(String key : data1.get(0).keySet()) param.put(key, data1.get(0).get(key));
				userDAO.insert_career(param);
				
				//변동사항 업데이트
				param.put("section", 3);
				
				List<Map<String, Object>> data2 = userDAO.select_career_history(param);				
				for(String key : data2.get(0).keySet()) param.put(key, data2.get(0).get(key));
				userDAO.insert_career(param);
			}			
		}
		
		userDAO.insert_member(param); //직원 기본 정보 추가		
		
		userDAO.delete_member_school(param); //학력사항 삭제
		if(param.get("sc_seq") != null) userDAO.insert_member_school(param); //학력사항 추가/수정
			
		userDAO.delete_member_edu(param); //교육이력 삭제
		if(param.get("ed_seq") != null) userDAO.insert_member_edu(param); //교육이력 추가/수정
		
		userDAO.delete_member_license(param); //자격면허 삭제
		if(param.get("l_seq") != null) userDAO.insert_member_license(param); //자격면허 추가/수정
		
		userDAO.delete_member_career(param); //타사이력 삭제
		if(param.get("oc_seq") != null) userDAO.insert_member_career(param); //타사이력 추가
		
		userDAO.delete_member_project(param); //프로젝트 삭제
		if(param.get("proj_seq") != null) userDAO.insert_member_project(param); //프로젝트 추가
		
		userDAO.delete_member_order(param); //발주처 삭제
		if(param.get("po_seq") != null) userDAO.insert_member_order(param); //발주처 추가
		
		userDAO.delete_member_society(param); //협회경력 삭제
		if(param.get("soc_seq") != null) userDAO.insert_member_society(param); //협회경력 추가
		
		userDAO.delete_member_level(param); //협회경력 삭제
		if(param.get("lv_seq") != null) userDAO.insert_member_level(param); //협회경력 추가
		
		//fileManager(param, multiFile); //프로필 사진
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Map<String, Object>> profile(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_profile(param);
	}

	@Override
	public void delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		fileDeleteManager(param); //업로드 되어있는 파일 삭제
		
		param.put("section", 1); //직원 추가정보 삭제
		userDAO.delete_member(param);
		
		param.put("section", 2); //직원 기본정보 삭제
		userDAO.delete_member(param);
	}

	@Override
	public List<Map<String, Object>> table(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_person_cnt(param);
	}

	@Override
	public int person_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_person_total(param);
	}

	@Override
	public List<Map<String, Object>> rate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_person_rate(param);
	}

	@Override
	public int user_id(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_user_id(param);
	}

	//파일 업로드
	private void fileManager(Map<String, Object> param, MultipartFile multiFile) {
		try {
			String path = fileUploadProperty.getProperty("file.uploadfile.path"); //파일 경로
			
			String[] folderName = {"", "profile/", "resize/"};
			String[] paramName = {"f_unique", "f_resize"};
			String pathTmp = path;
			
			int idx = 0;
			
			List<Map<String, Object>> data = userDAO.select_file(param); //파일 유무 확인			
			if(data.size() > 0) data = NullToString.nulltostring(data);
			
			for(String str : folderName) { //폴더 존재 여부 체크
				pathTmp += str;					
				File file = new File(pathTmp);	
				
				//권한 열기
				file.setReadable(true, false);
				file.setWritable(true, false);
				file.setExecutable(true, false);
				
				if(!file.exists()) file.mkdir();				
				
				if(!str.equals("")) { //파일 삭제				
					if(data.size() > 0) { //파일이 있는 경우
						Path filePath = Paths.get(pathTmp + data.get(idx).get(paramName[idx++]));
						Files.delete(filePath);
					}
				}
			}			

			if(multiFile != null) { //사진 업로드가 있는 경우		
				long fileSize = multiFile.getSize(); //파일 사이즈
				long maxSize = 10 * 1024 * 1024; // 10MB
				
				if(fileSize > maxSize) throw new ServletException("File Size Max Error"); //파일 사이즈 체크
				
				String originalName = multiFile.getOriginalFilename(); //원본 파일명
				String uuid = UUID.randomUUID().toString(); //파일명 암호화
				String format = originalName.substring(originalName.lastIndexOf(".") + 1); //파일 확장자
				String uniqueName = uuid + "." + format;
				String resizeName = uuid + "_" + "resize." + format;

				File saveFile = new File(path + folderName[1], uniqueName);
				multiFile.transferTo(saveFile); //원본 파일 저장

				ImageResize.resize(saveFile, pathTmp + resizeName, format, 100, 100); //리사이즈 이미지 저장
				
				//파일 정보 DB 저장			
				param.put("f_original", originalName);
				param.put("f_unique", uniqueName);
				param.put("f_resize", resizeName);
				param.put("f_code", 1);
				param.put("f_path", path + folderName[1]);
				param.put("f_repath", pathTmp);
				param.put("f_size", fileSize);
				
				userDAO.insert_file(param);
				userDAO.update_file(param);
			}else { //사진 업로드가 없는 경우
				if(data.size() > 0) {
					param.put("f_seq", data.get(0).get("f_seq"));
					userDAO.delete_file(param);
					
					param.put("f_seq", null);
					userDAO.update_file(param);
				}
			}
			
			pathTmp = path;
						
			for(String str : folderName) {
				pathTmp += str;					
				File file = new File(pathTmp);	
				
				//권한 닫기
				file.setReadable(true, false);
				file.setWritable(false, false);
				file.setExecutable(false, false);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//파일 삭제
	private void fileDeleteManager(Map<String, Object> param) {
		try {
			String path = fileUploadProperty.getProperty("file.uploadfile.path"); //파일 경로
			
			String[] folderName = {"profile/", "resize/"};
			String[] paramName = {"f_unique", "f_resize"};
			String pathTmp = path;
			
			int idx = 0;
			
			List<Map<String, Object>> data = userDAO.select_file(param); //파일 유무 확인	
			
			if(data.size() > 0) {
				data = NullToString.nulltostring(data);
				
				for(String str : folderName) { //폴더 존재 여부 체크
					pathTmp += str;					
					File file = new File(pathTmp);	
					
					//권한 열기
					file.setReadable(true, false);
					file.setWritable(true, false);
					file.setExecutable(true, false);
					
					if(!file.exists()) file.mkdir();				
					
					if(data.size() > 0) { //파일이 있는 경우 삭제
						Path filePath = Paths.get(pathTmp + data.get(idx).get(paramName[idx++]));
						Files.delete(filePath);
					}					
				}
				
				param.put("f_seq", data.get(0).get("f_seq"));
				userDAO.delete_file(param);
				
				pathTmp = path;
				
				for(String str : folderName) {
					pathTmp += str;					
					File file = new File(pathTmp);	
					
					//권한 닫기
					file.setReadable(true, false);
					file.setWritable(false, false);
					file.setExecutable(false, false);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String, Object>> org_group(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_org_group(param);
	}

	@Override
	public List<Map<String, Object>> job_grade_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_job_grade(param);
	}
	
	@Override
	public List<Map<String, Object>> select_member_list_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_member_list_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_profile_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.select_profile_excel(param);
	}
	
	@Override
	public List<Map<String, Object>> select_level(Map<String, Object> param) throws Exception{
		return userDAO.select_level(param);
	}
}
