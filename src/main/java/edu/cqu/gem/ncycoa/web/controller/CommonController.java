package edu.cqu.gem.ncycoa.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.cqu.gem.ncycoa.domain.DataDictionary;
import edu.cqu.gem.ncycoa.domain.DataDictionaryItem;
import edu.cqu.gem.ncycoa.service.CommonService;
import edu.cqu.gem.ncycoa.util.ConverterUtils;
import edu.cqu.gem.ncycoa.util.SystemUtils;

@Controller
public class CommonController {
	
	@RequestMapping("/**")
	public String dispatcher(HttpServletRequest request) {
		String url = request.getRequestURI();
		url = url.substring(SystemUtils.getContextPath().length(), url.lastIndexOf("."));
		return url;
	}
	
	@RequestMapping("/init")
	@ResponseBody
	public void init(HttpServletRequest request) {
		
		CommonService commonService = SystemUtils.getCommonService(request);
		
		List<Map<String, Object>> oldDicts = commonService.findSetBySql("select * from system_tablecodemeta");
		
		List<DataDictionaryItem> dictItems = new ArrayList<DataDictionaryItem>();
		for(Map<String, Object> oldDict : oldDicts) {
			DataDictionary dict = new DataDictionary();
			dict.setCode(oldDict.get("table_name").toString());
			dict.setName(oldDict.get("table_title").toString());
			dict.setType(oldDict.get("code_class").toString());
			dict.setIsLoad(ConverterUtils.getInt(oldDict.get("isload"), 0) == 1 ? true : false);
			
			dictItems.clear();
			List<Map<String, Object>> oldDictItems = commonService.findSetBySql("select * from system_tablecodemeta_col where table_name='" + oldDict.get("table_name").toString() + "'");
			
			for(Map<String, Object> oldDictItem : oldDictItems) {
				DataDictionaryItem item = new DataDictionaryItem();
				item.setCode(oldDictItem.get("code_id").toString());
				item.setName(oldDictItem.get("code_name").toString());
				item.setDictionary(dict);
				
				dictItems.add(item);
			}
			dict.setDictItems(dictItems);
			commonService.addEntity(dict);
		}
		
		List<Map<String, Object>> oldDictItems = commonService.findSetBySql("select * from system_tablecodemeta_col");
		for(Map<String, Object> oldDictItem : oldDictItems) {
			if(oldDictItem.get("pcode_id") != null && !oldDictItem.get("pcode_id").equals("")) {
				DataDictionaryItem item = commonService.findEntityByProperty("code", oldDictItem.get("pcode_id"), DataDictionaryItem.class);
				commonService.executeSql("update ncycoa_data_dict_itm set parent_id=? where item_code=?", item.getId(), oldDictItem.get("code_id"));
			}
		}
		
	}
	
}
