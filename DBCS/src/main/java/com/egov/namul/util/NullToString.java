package com.egov.namul.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NullToString {
	public static List<Map<String, Object>> nulltostring(List<Map<String, Object>> data){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if(data.size() > 0){
			for(int i=0; i<data.size(); i++){
				HashMap<String, Object> list = new LinkedHashMap<String, Object>();
				for(String key : data.get(i).keySet()){
					String value = String.valueOf(data.get(i).get(key));
					if(value.equals("") | value.equals("null")){
						value = "";
						list.put(key, value);
					}else{
						list.put(key, data.get(i).get(key));
					}	
				}
				result.add(list);
			}
		}
		return result;
	}
	
	public static HashMap<String, Object> ntshash(HashMap<String, Object> data){
		HashMap<String, Object> list = new HashMap<String, Object>();
		for(String key : data.keySet()){
			String value = String.valueOf(data.get(key));
			if(value.equals("") | value.equals("null")) value = "";
			list.put(key, value);
		}
		return list;
	}
}
