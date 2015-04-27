package com.performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.system.SystemModule;
import com.entity.system.UserInfo;

public class MenuHelper {

	private static List<SystemModule> getUserFunction(UserInfo user) {
		List<SystemModule> loginActionlist = new ArrayList<SystemModule>();
		String sql = "select * from SYSTEM_MENU_PRIVILLEGE where role_id='" + user.getDefaultid() + "'";
		DBObject db = new DBObject();
		DataTable dt = null;
		try {
			dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					String ccm = r.getString("LEVEL_CODE");
					SystemModule systemm = new SystemModule(ccm);
					loginActionlist.add(systemm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i<loginActionlist.size(); i++){
			SystemModule parent = loginActionlist.get(i);
			for(int j = 0; j<loginActionlist.size(); j++){
				SystemModule child = loginActionlist.get(j);
				String tmp = child.getLevel_code().substring(0, child.getLevel_code().length() - 3);
				if(tmp.equals(parent.getLevel_code())){
					parent.getChildren().add(child);
				}
			}
		}
		
		return loginActionlist;
	}
	
	
	public static String getSystemMenu(UserInfo user){
		return getEasyuiMultistageTree(getFunctionMap(user));
	}
	

	public static Map<Integer, List<SystemModule>> getFunctionMap(UserInfo user) {
		Map<Integer, List<SystemModule>> functionMap = new HashMap<Integer, List<SystemModule>>();
		List<SystemModule> loginActionlist = getUserFunction(user);
		for (SystemModule function : loginActionlist) {
			if (!functionMap.containsKey(function.level_code.length() / 3 - 1)) {
				functionMap.put(function.level_code.length() / 3 - 1, new ArrayList<SystemModule>());
			}
			functionMap.get(function.level_code.length() / 3 - 1).add(function);
		}
		// 菜单栏排序
//		Collection<List<SystemModule>> c = functionMap.values();
//		for (List<SystemModule> list : c) {
//			Collections.sort(list, new NumberComparator());
//		}
		return functionMap;
	}

	public static String getEasyuiMultistageTree(Map<Integer, List<SystemModule>> map) {
		if (map == null || map.size() == 0 || !map.containsKey(0)) {
			return "不具有任何权限,\n请找管理员分配权限";
		}
		StringBuffer menuString = new StringBuffer();
		List<SystemModule> list = map.get(0);
		for (SystemModule function : list) {
			menuString.append("<div title=\"" + function.getMenu_name() + "\">");
			int submenusize = function.getChildren().size();
			if (submenusize == 0) {
				menuString.append("</div>");
				continue;
			}
			
			menuString.append("<ul class=\"easyui-tree\"  fit=\"false\" border=\"false\">");
			menuString.append(getChildOfTree(function, 1, map));
			menuString.append("</ul></div>");
		}
		return menuString.toString();
	}

	private static String getChildOfTree(SystemModule parent, int level, Map<Integer, List<SystemModule>> map) {
		StringBuffer menuString = new StringBuffer();
		List<SystemModule> list = map.get(level);
		for (SystemModule function : list) {
			String tmp = function.getLevel_code().substring(0, function.getLevel_code().length() - 3);
			if(tmp.equals(parent.getLevel_code())){
				if (function.getChildren().size() == 0 || !map.containsKey(level + 1)) {
					menuString.append(getLeafOfTree(function));
				} else if (map.containsKey(level + 1)) {
					menuString.append("<li state=\"closed\"><span>" + function.getMenu_name() + "</span>");
					menuString.append("<ul >");
					menuString.append(getChildOfTree(function, level + 1, map));
					menuString.append("</ul></li>");
				}
			}
		}
		return menuString.toString();
	}

	private static String getLeafOfTree(SystemModule function) {
		StringBuffer menuString = new StringBuffer();
		String icon = "folder";
		
		menuString.append("<li iconCls=\"");
		menuString.append(icon);
		menuString.append("\"> <a onclick=\"addTab(\'");
		menuString.append(function.getMenu_name());
		menuString.append("\',\'");
		menuString.append(function.getMenu_url());
		if(function.getMenu_url().contains("?")){
			menuString.append("&isIframe");
		}else{
			menuString.append("?isIframe");
		}
		menuString.append("&clickFunctionId=");
		menuString.append(function.getLevel_code());
		menuString.append("\',\'");
		menuString.append(icon);
		menuString.append("\')\" title=\"");
		menuString.append(function.getMenu_name());
		menuString.append("\" url=\"");
		menuString.append(function.getMenu_url());
		menuString.append("\" href=\"#\" ><span class=\"nav\" >");
		menuString.append(function.getMenu_name());
		menuString.append("</span></a></li>");
		return menuString.toString();
	}
}
