package com.egov.namul.service.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.egov.namul.mapper.FileMapper;
import com.egov.namul.service.FileService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("FileService")
public class FileServiceImpl extends EgovAbstractServiceImpl implements FileService {

	@Resource(name="FileMapper")
	private FileMapper fileDAO;
	
	@Resource(name = "fileUploadProperty")
	private Properties fileUploadProperty;	 

	@Override
	public void multi_upload(Map<String, Object> param, List<MultipartFile> multiFiles, String[] fileSeq, String folderPath) throws Exception {
		// TODO Auto-generated method stub		
		String path = fileUploadProperty.getProperty("file.uploadfile.path");
		String filePath = path + folderPath;
		
		int code = 0;
		
		if(fileSeq != null) { // 유지할 파일 시퀀스 값 담기
			int idx = 0;				
			for(String file : fileSeq) if(!file.equals("0")) idx++;		
			
			if(idx > 0) {
				int[] tmpSeq = new int[idx];
				code = idx;
				idx = 0;
				
				for(String file : fileSeq) if(!file.equals("0")) tmpSeq[idx++] = Integer.parseInt(file);					
				param.put("file_seq", tmpSeq);				
				param.put("type", 1);
				fileDAO.insert_file(param); //파일 순서 바꾸기
			}	
		}
		
		List<Map<String, Object>> files = fileDAO.select_delete_file(param);
		
		permission_open(filePath); //권한 열기
		
		if(files.size() > 0) { //유지할 파일 제외하고 삭제하기
			file_delete(filePath, files);
			fileDAO.delete_file(param);
		}
		
		if(multiFiles.size() > 0) { //추가 또는 수정할 업로드가 있는 경우
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			
			for(MultipartFile upload : multiFiles) {
				code++;
				Map<String, Object> map = file_upload(upload, filePath, "/uploadfile/" + folderPath, code);
				data.add(map);
				
				permission_close(filePath + map.get("f_unique").toString()); //권한 처리
			}
			
			param.put("new_file", data);
			param.put("type", 2);			
			fileDAO.insert_file(param); 
		}
		
		permission_close(filePath); //권한 처리
	}
	
	@Override
	public void multi_resize_upload(Map<String, Object> param, List<MultipartFile> multiFiles, String folderPath,
			String[] fileSeq, String[] fileDel, int width, int height) throws Exception {
		// TODO Auto-generated method stub
		String path = fileUploadProperty.getProperty("file.uploadfile.path");
		String filePath = path + folderPath;
		
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> saveData = new ArrayList<Map<String, Object>>();
		
		int idx = 0;
		
		permission_open(filePath); //권한 열기
		permission_open(filePath + "resize/"); //권한 열기
		
		for(int i = 0; i < fileSeq.length; i++) { 
			Map<String, Object> map = new HashMap<String, Object>();
			int status = 1;
			if(!fileDel[i].isEmpty()) status = Integer.parseInt(fileDel[i]);
			
			switch(status) {
				case 3 : //추가&수정
					if(!fileSeq[i].isEmpty()) map.put("seq", Integer.parseInt(fileSeq[i]));
					break;
				case 2 : //삭제
					if(!fileSeq[i].isEmpty()) map.put("seq", Integer.parseInt(fileSeq[i]));
					break;
				default : break; //유지
			}
			
			if(map.size() > 0) data.add(map);
		}
		
		if(data.size() > 0) { //삭제할 파일 정보불러온 후 삭제 처리하기
			param.put("delete_data", data);
			List<Map<String, Object>> deleteFiles = fileDAO.select_delete_file(param);
			resize_file_delete(filePath, deleteFiles);			
		}
		
		List<Map<String, Object>> fileSeqs = fileDAO.select_file(param); //시퀀스 가져오기
		
		for(int i = 0; i < fileSeq.length; i++) { //업로드 처리하기
			int status = 2;
			if(!fileDel[i].isEmpty()) status = Integer.parseInt(fileDel[i]);
			
			switch(status) {
				case 3 : //추가&수정
					Map<String, Object> map = resize_file_upload(multiFiles.get(idx++), filePath, "/uploadfile/" + folderPath, i, width, height);
					
					if(fileSeqs.size() > 0) map.put("f_seq", fileSeqs.get(i).get("f_seq"));
					else map.put("f_seq", 0);				
						
					saveData.add(map);
					
					permission_close(filePath + "resize/" + map.get("f_resize").toString()); //권한 처리
					permission_close(filePath + map.get("f_unique").toString()); //권한 처리	
					
					break;
				case 2 : //삭제				
					Map<String, Object> emptyData = new HashMap<String, Object>();
					
					if(fileSeqs.size() > 0) emptyData.put("f_seq", fileSeqs.get(i).get("f_seq"));
					else emptyData.put("f_seq", 0);	
						
					emptyData.put("f_code", i);
					emptyData.put("f_original", "");
					emptyData.put("f_unique", "");
					emptyData.put("f_resize", "");									
					emptyData.put("f_path", "/uploadfile/" + folderPath);
					emptyData.put("f_repath", "/uploadfile/" + folderPath+ "resize/");
					emptyData.put("f_size", 0);
						
					saveData.add(emptyData);
						
					break;
				default : break; //유지
			}
		}	
				
		if(saveData.size() > 0) {
			param.put("new_file", saveData);
			param.put("type", 5);	
			fileDAO.insert_file(param);
		}
		
		permission_close(filePath + "resize/"); //권한 닫기
		permission_close(filePath); //권한 닫기
	}

	@Override
	public void single_upload(Map<String, Object> param, MultipartFile multiFile, String folderPath, int fileSeq) throws Exception {
		// TODO Auto-generated method stub
		String path = fileUploadProperty.getProperty("file.uploadfile.path");
		String filePath = path + folderPath;
		
		if(fileSeq > 0) param.put("file_seq2", fileSeq);		
		List<Map<String, Object>> file = fileDAO.select_delete_file(param);
		
		permission_open(filePath); //권한 열기
		
		if(file.size() > 0) { //기존 파일 삭제하기
			file_delete(filePath, file);
			fileDAO.delete_file(param);
		}
		
		if(multiFile != null) { //추가 또는 수정할 업로드가 있는 경우
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();			
			Map<String, Object> map = file_upload(multiFile, filePath, "/uploadfile/" + folderPath, 1);
			data.add(map);
			
			param.put("new_file", data);
			param.put("type", 2);			
			fileDAO.insert_file(param);
			permission_close(filePath + map.get("f_unique").toString()); //권한 처리
		}
		
		permission_close(filePath); //권한 닫기
	}

	@Override
	public void single_resize_upload(Map<String, Object> param, MultipartFile multiFile, String folderPath, int fileSeq,
			int width, int height) throws Exception {
		// TODO Auto-generated method stub
		String path = fileUploadProperty.getProperty("file.uploadfile.path");
		String filePath = path + folderPath;
		
		if(fileSeq > 0) param.put("file_seq2", fileSeq);		
		List<Map<String, Object>> file = fileDAO.select_delete_file(param);
		
		permission_open(filePath); //권한 열기
		permission_open(filePath + "resize/"); //권한 열기
		
		if(file.size() > 0) { //기존 파일 삭제하기
			resize_file_delete(filePath, file);
			fileDAO.delete_file(param);
		}
		
		if(multiFile != null) { //추가 또는 수정할 업로드가 있는 경우
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();			
			Map<String, Object> map = resize_file_upload(multiFile, filePath, "/uploadfile/" + folderPath, 1, width, height);
			data.add(map);
			
			param.put("new_file", data);
			param.put("type", 3);			
			fileDAO.insert_file(param);			
			
			permission_close(filePath + "resize/" + map.get("f_resize").toString()); //권한 처리
			permission_close(filePath + map.get("f_unique").toString()); //권한 처리			
		}
		
		permission_close(filePath + "resize/"); //권한 닫기
		permission_close(filePath); //권한 닫기
		
	}

	@Override
	public void single_resize_upload(Map<String, Object> param, MultipartFile multiFile, String folderPath, int fileSeq,
			int width, int height, int code) throws Exception {
		// TODO Auto-generated method stub
		String path = fileUploadProperty.getProperty("file.uploadfile.path");
		String filePath = path + folderPath;
		
		if(fileSeq > 0) param.put("file_seq2", fileSeq);		
		List<Map<String, Object>> file = fileDAO.select_delete_file(param);
		
		permission_open(filePath); //권한 열기
		permission_open(filePath + "resize/"); //권한 열기
		
		if(file.size() > 0) { //기존 파일 삭제하기
			resize_file_delete(filePath, file);
			fileDAO.delete_file(param);
		}
		
		if(multiFile != null) { //추가 또는 수정할 업로드가 있는 경우
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();			
			Map<String, Object> map = resize_file_upload(multiFile, filePath, "/uploadfile/" + folderPath, code, width, height);
			
			if(map.size() > 0) {
				data.add(map);
				
				param.put("new_file", data);
				param.put("type", 3);			
				fileDAO.insert_file(param);			
				
				permission_close(filePath + "resize/" + map.get("f_resize").toString()); //권한 처리
				permission_close(filePath + map.get("f_unique").toString()); //권한 처리		
			}else {
				param.put("type", 4);
				param.put("f_code", code);
				param.put("f_path", "/uploadfile/" + folderPath);
				param.put("f_repath", "/uploadfile/" + folderPath+ "resize/");
				
				fileDAO.insert_file(param);	
			}
		}
		
		permission_close(filePath + "resize/"); //권한 닫기
		permission_close(filePath); //권한 닫기
	}

	@Override
	public List<Map<String, Object>> file(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return fileDAO.select_file(param);
	}

	@Override
	public void delete(Map<String, Object> param, String folderPath) throws Exception {
		// TODO Auto-generated method stub
		String path = fileUploadProperty.getProperty("file.uploadfile.path");
		String filePath = path + folderPath;
		
		List<Map<String, Object>> files = fileDAO.select_delete_file(param);
		permission_open(filePath); //권한 열기
		
		if(files.size() > 0) { //파일 삭제하기
			file_delete(filePath, files);
			fileDAO.delete_file(param);
		}
		
		permission_close(filePath); //권한 닫기
	}

	private void permission_open(String folderPath) { //권한 열기
		File file = new File(folderPath);
		
		if(!file.exists()) file.mkdir();
		
		file.setReadable(true, false);
		file.setWritable(true, false);
		file.setExecutable(true, false);
	}
	
	private void permission_close(String folderPath) { //권한 닫기
		File file = new File(folderPath);

		file.setReadable(true, false);
		file.setWritable(false, false);
		file.setExecutable(true, false);
	}

	private void file_delete(String folderPath, List<Map<String, Object>> data) { //필요없는 파일 삭제
		try {
			for(Map<String, Object> list : data) {
				Path filePath = Paths.get(folderPath + list.get("f_unique"));
				Files.delete(filePath);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void resize_file_delete(String folderPath, List<Map<String, Object>> data) { //필요없는 파일 삭제
		try {
			for(Map<String, Object> list : data) {
				Path filePath = Paths.get(folderPath + list.get("f_unique"));
				Files.delete(filePath);
				
				Path filePath2= Paths.get(folderPath + "resize/" + list.get("f_resize"));
				Files.delete(filePath2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Map<String, Object> file_upload(MultipartFile multiFile, String path, String folder, int code){ //파일 업로드
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {			
			long fileSize = multiFile.getSize(); //파일 사이즈
			long maxSize = 10 * 1024 * 1024; // 10MB
			
			if(fileSize > maxSize) throw new ServletException("File Size Max Error"); //파일 사이즈 체크
			
			String originalName = multiFile.getOriginalFilename(); //원본 파일명
			String uuid = UUID.randomUUID().toString(); //파일명 암호화
			String format = originalName.substring(originalName.lastIndexOf(".") + 1); //파일 확장자
			String uniqueName = uuid + "." + format;

			File saveFile = new File(path, uniqueName);
			multiFile.transferTo(saveFile); //파일 저장

			//파일 정보 DB 저장			
			map.put("f_original", originalName);
			map.put("f_unique", uniqueName);
			map.put("f_code", code);
			map.put("f_path", folder);
			map.put("f_size", Long.valueOf(fileSize).intValue());			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	private Map<String, Object> resize_file_upload(MultipartFile multiFile, String path, String folder, int code, int width, int height){ //파일 업로드
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if(!multiFile.isEmpty()) {
				long fileSize = multiFile.getSize(); //파일 사이즈	
				
				if(fileSize > 0) {
					long maxSize = 10 * 1024 * 1024; // 10MB
					
					if(fileSize > maxSize) throw new ServletException("File Size Max Error"); //파일 사이즈 체크
					
					String originalName = multiFile.getOriginalFilename(); //원본 파일명
					String uuid = UUID.randomUUID().toString(); //파일명 암호화
					String format = originalName.substring(originalName.lastIndexOf(".") + 1); //파일 확장자
					String uniqueName = uuid + "." + format;
					String resizeUniqueName = uuid + "_" + width + "x" + height + "." + format;
					File saveFile = new File(path, uniqueName);
					multiFile.transferTo(saveFile); //파일 저장

					resize(saveFile, path + "resize/" + resizeUniqueName, format, width, height);
					
					//파일 정보 DB 저장			
					map.put("f_original", originalName);
					map.put("f_unique", uniqueName);
					map.put("f_resize", resizeUniqueName);
					map.put("f_code", code);
					map.put("f_path", folder);
					map.put("f_repath", folder + "resize/");
					map.put("f_size", Long.valueOf(fileSize).intValue());	
				}	
			}						
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return map;
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
}
