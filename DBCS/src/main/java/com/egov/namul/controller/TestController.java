package com.egov.namul.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egov.namul.service.FileService;
import com.egov.namul.service.TestService;
import com.egov.namul.service.ImageService;
import com.egov.namul.util.SHAUtil;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	
	@Resource(name = "ImageService")
	private ImageService ImageService;
	
	@Resource(name = "TestService")
	private TestService TestService;
	
	@Resource(name = "fileUploadProperty")
	private Properties fileUploadProperty;	 

	@RequestMapping(value="pass")
	public void pass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			List<Map<String, Object>> insertData = new ArrayList<Map<String, Object>>();
			
			int page = 1;
			int row = 1000;			
			
			while (true) {
				int start = (page - 1) * row;
				
				param.put("start", start);
				param.put("row", row);
								
				List<Map<String, Object>> data = TestService.select_password(param);
				page++;
				
				if(data.size() > 0) {
					for(Map<String, Object> list : data) {	
						list.put("pass", SHAUtil.SHA512Encrypt(list.get("OLDPASS").toString()));
						insertData.add(list);
					}
					
					if(insertData.size() > 0) {
						param.put("insert_data", insertData);
						TestService.insert_password(param);
					}					
				}else break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/img")
	public void img( HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String[] imgTypes = {"jpg", "jpeg", "png", "gif", "svg"};			
			String path = fileUploadProperty.getProperty("file.uploadfile.path");			
			
			Map<String, Object> param = new HashMap<String, Object>();
			List<Map<String, Object>> insertData = new ArrayList<Map<String, Object>>();
			
			int page = 1;
			int row = 1000;			
			
			while (true) {
				int start = (page - 1) * row;
				
				param.put("start", start);
				param.put("row", row);
								
				List<Map<String, Object>> data = ImageService.select_person_image(param);
				page++;
				
				if(data.size() > 0) {
					for(Map<String, Object> list : data) {	
						Map<String, Object> map = new HashMap<String, Object>();
						
						String pathOriginal = path + "emp/" + list.get("IMG_NAME").toString();
						
						File file = new File(pathOriginal);
						
						String originalName = file.getName();
						String uuid = UUID.randomUUID().toString();
						String format = originalName.substring(originalName.lastIndexOf(".") + 1); //파일 확장자
						String uniqueName = uuid + "." + format;	
						String pathCopy = path + "personnel_file/" + uniqueName;
						
						long fileSize = file.length();						
						
						if(isImage(imgTypes, format)) {
							int width = 0;
							int height = 0;
							
							String resizeUniqueName = uuid + "_" + width + "x" + height + "." + format;
							String pathResizeCopy = path + "personnel_file/resize/" + resizeUniqueName;
							
							BufferedImage bi = ImageIO.read(new File(pathOriginal));
							ImageIO.write(bi, format, new File(pathCopy));
							
							resize(file, pathResizeCopy, format, width, height);							
							
							map.put("f_tb", "personnel");
							map.put("tb_seq", list.get("per_seq"));
							map.put("f_original", originalName);
							map.put("f_unique", uniqueName);
							map.put("f_resize", resizeUniqueName);
							map.put("f_code", 1);
							map.put("f_path", path + "personnel_file/");
							map.put("f_repath", path + "personnel_file/resize/");
							map.put("f_size", Long.valueOf(fileSize).intValue());							
						}else {
							
						}
						
						insertData.add(map);
					}
					
					if(insertData.size() > 0) {
						param.put("insert_data", insertData);
						
					}					
				}else break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void resize(File saveFile, String targetPath, String format, int width, int height) {
		try {
			String mainPosition = "W"; // W:넓이중심, H:높이중심, X:설정한 수치로(비율무시)
			
	        Image image;
	        int imageWidth;
	        int imageHeight;
	        double ratio;
	        int w;
	        int h;			
			
            image = ImageIO.read(saveFile); // 원본 이미지 가져오기
 
            // 원본 이미지 사이즈 가져오기
            imageWidth = image.getWidth(null);
            imageHeight = image.getHeight(null);
 
            if(mainPosition.equals("W")){ // 넓이기준 
                ratio = (double)width/(double)imageWidth;
                w = (int)(imageWidth * ratio);
                h = (int)(imageHeight * ratio);
 
            }else if(mainPosition.equals("H")){ // 높이기준
 
                ratio = (double)height/(double)imageHeight;
                w = (int)(imageWidth * ratio);
                h = (int)(imageHeight * ratio);
 
            }else{ //설정값 (비율무시) 
                w = width;
                h = height;
            }
 
            // 이미지 리사이즈
            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
            Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
 
            // 새 이미지  저장하기
            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();

            ImageIO.write(newImage, format, new File(targetPath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isImage(String[] imageTypes, String format) {
		for(String type : imageTypes) {
			if(type.equals(format)) return true;
		}	
		return false;
	}
}
