package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.ItemMapper;
import com.egov.namul.service.ItemService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ItemService")
public class ItemServiceImpl extends EgovAbstractServiceImpl implements ItemService {

	@Resource(name="ItemMapper")
	private ItemMapper itemDAO;

	@Override
	public int item_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return itemDAO.select_item_cnt(param);
	}

	@Override
	public List<Map<String, Object>> item(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return itemDAO.select_item(param);
	}

	@Override
	public void item_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		itemDAO.delete_item(param);
		itemDAO.update_auto_increament(param);
		if(param.get("size") != null) itemDAO.insert_item(param);
	}
}
