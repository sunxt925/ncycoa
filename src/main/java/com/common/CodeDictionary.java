package com.common;

import java.util.List;

import com.dao.system.LoadCode;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.query.QueryGroup;

import edu.cqu.ncycoa.domain.DataDictionary;
import edu.cqu.ncycoa.domain.DataDictionaryItem;

public class CodeDictionary {

	public static String getselectItem(String code_entity) throws Exception {
		LoadCode en = new LoadCode();
		String code_class = CodeDictionary.getCodeclass(code_entity);
		StringBuilder res = new StringBuilder();
		res.append("");
		DataTable dt = en.getSelectItem(code_entity);

		if (dt != null && dt.getRowsCount() > 0) {
			res.append("<option size='30' value=''>--请选择--</option>");
			int fnn = 1;
			if (code_class.equals("00020003"))
				fnn = 0;
			for (int i = fnn; i < dt.getRowsCount(); i++) {
				DataRow r = dt.get(i);
				try {
					res.append("<option size='30' value='" + r.getString("code_id") + "'>" + r.getString("code_name") + "</option>");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return res.toString();
	}

	public static String getselectoption(String code_entity) throws Exception {
		LoadCode en = new LoadCode();
		String code_class = CodeDictionary.getCodeclass(code_entity);
		StringBuilder res = new StringBuilder();
		res.append("");
		DataTable dt = en.getSelectItem(code_entity);

		if (dt != null && dt.getRowsCount() > 0) {

			int fnn = 1;
			if (code_class.equals("00020003"))
				fnn = 0;
			for (int i = fnn; i < dt.getRowsCount(); i++) {
				DataRow r = dt.get(i);
				try {
					res.append("<option  value='" + r.getString("code_id") + "'>" + r.getString("code_name") + "</option>");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return res.toString();
	}

	public static String getselectItem(String code_entity, String old_value) {

		LoadCode en = new LoadCode();
		String res = "";
		res = "<select align=\"center\" name=" + "\"" + code_entity + "\" " + "id=" + "\"" + code_entity + "\" " + ">";
		if (old_value.equals("")) {
			res += "<option value=" + "\'\'" + ">--请选择--</option>";
		} else {

			res += "<option value=" + "\'" + old_value + "\'" + ">" + old_value + "</option>";
			res += "<option value=" + "\'\'" + ">--请选择--</option>";
		}

		DataTable dt = en.getSelectItem();

		if (dt != null && dt.getRowsCount() > 0) {
			for (int i = 1; i < dt.getRowsCount(); i++) {
				DataRow r = dt.get(i);
				try {

					res += "<option value='" + r.getString("table_name") + "'>" + r.getString("table_name") + "</option>";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			res += "</select>";
		}

		return res;

	}

	// 获取实体表SYSTEM_ENTITY数据
	public static String getselectItem(DataTable dt, String para) {
		String res = "";
		if (dt != null && dt.getRowsCount() > 0) {
			res += "<option value=''></option>";
			if (para.equals("entity_name")) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					try {
						res += "<option value='" + r.getString("entity_code") + "'>" + r.getString("entity_code") + "</option>";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if (para.equals("entity_col")) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					try {
						res += "<option value='" + r.getString("entity_name") + "'>" + r.getString("entity_name") + "</option>";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {

			}
		}
		return res;
	}

	public static String getselectItem(String item, String itemvlaue, String itemmetaval) {

		String res = "";
		String sql = "select * from system_tablecodemeta_col where code_id='" + itemmetaval + "'";
		try {
			DBObject db = new DBObject();
			DataTable dt2 = db.runSelectQuery(sql);
			if (itemmetaval.equals("")) {
				res = "<select align=\"center\" style='width:200px'name=" + "\"" + itemvlaue + "\" " + "id=" + "\"" + itemvlaue + "\" "
						+ ">" + "<option value=" + "\'" + itemmetaval + "\'" + ">--请选择--</option>";

			} else {
				res = "<select align=\"center\" style='width:200px' name=" + "\"" + itemvlaue + "\" " + "id=" + "\"" + itemvlaue + "\" "
						+ ">" + "<option value=" + "\'" + itemmetaval + "\'" + ">" + dt2.get(0).getString("code_name") + "</option>";

			}
			res += getselectItem(item);

			res += "</select>";

		} catch (Exception e) {
			// TODO: handle exception
		}

		return res;

	}

	// 编码翻译
	public static String code_traslate(String code, String code_column) {

		LoadCode en = new LoadCode();
		DataTable dt = en.getSelectItem(code);
		String code_name = "";

		for (int i = 0; i < dt.getRowsCount(); i++) {
			try {
				if (dt.get(i).getString("code_id").equals(code_column)) {
					code_name = dt.get(i).getString("code_name");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		return code_name;
	}

	// 编码翻译
	public static String syscode_traslate(String code, String code_column, String code_column_des, String code_value) {

		try {
			String sql = "select " + code_column_des + " from " + code + " where " + code_column + "='" + code_value + "'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);

			return dt.get(0).getString(code_column_des);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public String choose(String group_code) throws Exception {

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("");
		QueryGroup queryGroup = new QueryGroup();
		// DataTable dt_group=db.runSelectQuery(sql_group);
		DataTable dt_group = queryGroup.getAllgroupList();
		if (dt_group != null && dt_group.getRowsCount() > 0) {
			for (int j = 0; j < dt_group.getRowsCount(); j++) {
				DataRow r_group = dt_group.get(j);
				if (group_code.equals(r_group.getString("group_code"))) {
					sBuilder.append("<option value='querymanage.jsp?group_code=" + r_group.getString("group_code") + "' selected>"
							+ Coder.getCdfromccm(r_group.getString("group_code")) + r_group.getString("group_name") + "</option>");
					// out.print("<option value='querymanage.jsp?group_code="+r_group.getString("group_code")+"' selected>"+Coder.getCdfromccm(r_group.getString("group_code"))+r_group.getString("group_name")+"</option>");
				} else {
					sBuilder.append("<option value='querymanage.jsp?group_code=" + r_group.getString("group_code") + "'>"
							+ Coder.getCdfromccm(r_group.getString("group_code")) + r_group.getString("group_name") + "</option>");
					// out.print("<option value='querymanage.jsp?group_code="+r_group.getString("group_code")+"'>"+Coder.getCdfromccm(r_group.getString("group_code"))+r_group.getString("group_name")+"</option>");
				}
			}
			return sBuilder.toString();
		} else {
			return null;
		}

	}

	public String RadioItem(String code_entity, String item_name, String default_value) {
		String code_class = CodeDictionary.getCodeclass(code_entity);
		LoadCode en = new LoadCode();
		StringBuilder res = new StringBuilder();
		res.append("");
		DataTable dt = en.getSelectItem(code_entity);

		if (dt != null && dt.getRowsCount() > 0) {
			int fnn = 1;
			if (code_class.equals("00020003"))
				fnn = 0;
			for (int i = fnn; i < dt.getRowsCount(); i++) {
				DataRow r = dt.get(i);
				try {

					if (default_value.equals("")) {

						res.append("<input type='radio' name='" + item_name + "' id='" + item_name + "' value='" + r.getString("code_id")
								+ "'/><span style='font-size:12px'>" + r.getString("code_name") + "</span>");
					} else {
						if (default_value.equals(r.getString("code_id"))) {
							res.append("<input type='radio' name='" + item_name + "' id='" + item_name + "' value='"
									+ r.getString("code_id") + "' checked/><span style='font-size:12px'>" + r.getString("code_name")
									+ "</span>");
						} else {
							res.append("<input type='radio' name='" + item_name + "' id='" + item_name + "' value='"
									+ r.getString("code_id") + "'/><span style='font-size:12px'>" + r.getString("code_name") + "</span>");
						}

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return res.toString();
	}

	// 构造复选框
	public String Checkbox(String code_entity, String item_name, String default_value) {
		String code_class = CodeDictionary.getCodeclass(code_entity);
		LoadCode en = new LoadCode();
		StringBuilder res = new StringBuilder();
		res.append("");
		DataTable dt = en.getSelectItem(code_entity);

		if (default_value.equals("")) {
			if (dt != null && dt.getRowsCount() > 0) {
				int fnn = 1;
				if (code_class.equals("00020003"))
					fnn = 0;
				for (int i = fnn; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					try {

						res.append("<input type='checkbox' name='" + item_name + "' id='" + item_name + "' value='"
								+ r.getString("code_id") + "'/>" + r.getString("code_name"));

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} else {

			if (default_value.indexOf(",") == -1) {
				if (dt != null && dt.getRowsCount() > 0) {
					int fnn = 1;
					if (code_class.equals("00020003"))
						fnn = 0;
					for (int i = fnn; i < dt.getRowsCount(); i++) {
						DataRow r = dt.get(i);
						try {

							if (default_value.equals(r.getString("code_id"))) {

								res.append("<input type='checkbox' name='" + item_name + "' id='" + item_name + "' value='"
										+ r.getString("code_id") + "' checked/>" + r.getString("code_name"));
							} else {
								res.append("<input type='checkbox' name='" + item_name + "' id='" + item_name + "' value='"
										+ r.getString("code_id") + "' />" + r.getString("code_name"));
							}

						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			} else {
				String[] defaultvalue = default_value.split(",");

				if (dt != null && dt.getRowsCount() > 0) {
					for (int i = 1; i < dt.getRowsCount(); i++) {
						DataRow r = dt.get(i);
						boolean flag = true;
						try {
							for (int j = 0; j < defaultvalue.length; j++) {

								if (defaultvalue[j].equals(r.getString("code_id"))) {
									res.append("<input type='checkbox' name='" + item_name + "' id='" + item_name + "' value='"
											+ r.getString("code_id") + "' checked/>" + r.getString("code_name"));
									flag = false;
									continue;
								}

							}

							if (!flag) {
								res.append("<input type='checkbox' name='" + item_name + "' id='" + item_name + "' value='"
										+ r.getString("code_id") + "' />" + r.getString("code_name"));

							}

						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}

			}
		}

		return res.toString();
	}

	public static String getCodeclass(String table_name) {

		try {

			String sql = "select code_class from system_tablecodemeta where table_name='" + table_name + "'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			return dt.get(0).getString("code_class");
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
