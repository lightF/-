package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.ImageMapper;
import com.egov.namul.mapper.ItemMapper;
import com.egov.namul.service.ImageService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ImageService")
public class ImageServiceImpl extends EgovAbstractServiceImpl implements ImageService {

	@Resource(name="ImageMapper")
	private ImageMapper imageDAO;
	
	@Override
	public List<Map<String, Object>> select_person_image(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return imageDAO.select_person_image(param);
	}

}
