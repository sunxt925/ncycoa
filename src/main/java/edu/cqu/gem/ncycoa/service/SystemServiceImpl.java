package edu.cqu.gem.ncycoa.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.system.UserInfo;

import edu.cqu.gem.ncycoa.dao.CommonDao;
import edu.cqu.gem.ncycoa.domain.DataDictionary;
import edu.cqu.gem.ncycoa.domain.DataDictionaryItem;
import edu.cqu.gem.ncycoa.domain.Log;
import edu.cqu.gem.ncycoa.util.BrowserUtils;
import edu.cqu.gem.ncycoa.util.ConverterUtils;
import edu.cqu.gem.ncycoa.util.SystemUtils;

@Service("systemService")
@Transactional
public class SystemServiceImpl implements SystemService {

	@Resource(name="commonDao")
	private CommonDao commonDao;

	@Override
	public void addLog(String logContent, Short loglevel, Short operatetype) {
		HttpServletRequest request = SystemUtils.getRequest();
		String broswer = BrowserUtils.checkBrowse(request);
		Log log = new Log();
		log.setLogContent(logContent);
		log.setLogLevel(loglevel);
		log.setOperateType(operatetype);
		log.setNote(ConverterUtils.getIpAddrByRequest(request));
		log.setBroswer(broswer);
		log.setOperateTime(new Date());
		UserInfo user = SystemUtils.getSessionUser();
		log.setOperatorName(user.getUsername());
		log.setOperatorName(user.getStaffcode());
		
		commonDao.saveEntity(log);
	}

	@Override
	public void initAllDataDictionarys() {
		DataDictionary.getAllDictionarys().clear();
		List<DataDictionary> dicts = commonDao.readAllEntities(DataDictionary.class);
		for (DataDictionary dict : dicts) {
			DataDictionary.getAllDictionarys().put(dict.getCode().toLowerCase(), dict);
			List<DataDictionaryItem> dictItems = commonDao.readEntitiesByProperty("dictionary.id", dict.getId(), DataDictionaryItem.class);
			Collections.sort(dictItems, new Comparator<DataDictionaryItem>() {
				@Override
				public int compare(DataDictionaryItem o1, DataDictionaryItem o2) {
					return o1.getCode().compareTo(o2.getCode());
				}
			});
			DataDictionary.getAllDictionaryItems().put(dict.getCode().toLowerCase(), dictItems);
		}
	}
	
	@Override
	public void refleshDataDictionaryItemCach(DataDictionary dict) {
		List<DataDictionaryItem> dictItems = commonDao.readEntitiesByProperty("dictionary.id", dict.getId(), DataDictionaryItem.class);
		Collections.sort(dictItems, new Comparator<DataDictionaryItem>() {
			@Override
			public int compare(DataDictionaryItem o1, DataDictionaryItem o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
		});
		DataDictionary.getAllDictionaryItems().put(dict.getCode().toLowerCase(), dictItems);
	}

	@Override
	public void refleshDataDictionaryCach() {
		initAllDataDictionarys();
	}

	@Override
	public DataDictionaryItem getDataDictionaryItem(String code, String name, DataDictionary dict) {
		DataDictionaryItem item = commonDao.readEntityByProperty("code", code, DataDictionaryItem.class);
		if (item == null) {
			item = new DataDictionaryItem();
			item.setCode(code);
			item.setName(name);
			item.setDictionary(dict);
			commonDao.saveEntity(item);
		}
		return item;
	}

	@Override
	public DataDictionary getDataDictionary(String code, String name) {
		DataDictionary dict = commonDao.readEntityByProperty("code", code, DataDictionary.class);
		if (dict == null) {
			dict = new DataDictionary();
			dict.setCode(code);
			dict.setName(name);
			commonDao.saveEntity(dict);
		}
		return dict;
	}

	@Override
	public DataDictionary getDataDictionaryByCode(String code) {
		DataDictionary dict = commonDao.readEntityByProperty("code", code, DataDictionary.class);
		return dict;
	}
	
}
