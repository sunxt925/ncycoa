package com.common;

import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;

public class ComponentUtil {
	public String Width = "150px";// 组件宽度属性
	public String Height_normal = "20px";// 组件高度属性
	public String Height_text = "200px";// text框高度属性
	public String Input_Class = "input1";// input组件样式
	public String Textarea_class = "input1";// text框的样式
	public String Select_class = "input1";// select组件的默认样式
	public String CheckScript = "var check_res='';";// 校验的SCRIPT代码
	public String display_state = "";// 组件显示状态（只读、隐藏。。。）

	public ComponentUtil() {

	}

	public ComponentUtil(String style) {
		// 两列输入的情况
		if (style != null && style.equals("2")) {
			this.Width = "150px";
		}
	}

	public String print(String entity_code, String column_code) {
		return print(entity_code, column_code, "");
	}

	public String print(String entity_code, String column_code, String default_value) {
		return print(entity_code, column_code, default_value, false);
	}

	public String print(String entity_code, String column_code, String default_value, String display_state) {
		return print(entity_code, column_code, default_value, null, null, null, display_state, null, false);
	}

	public String print(String entity_code, String column_code, String default_value, boolean isPkmodify) {
		return print(entity_code, column_code, default_value, "", isPkmodify);
	}

	public String print(String entity_code, String column_code, String default_value, String parameter, boolean isPkmodify) {
		return print(entity_code, column_code, default_value, null, null, null, null, parameter, isPkmodify);
	}

	public String print(String entity_code, String column_code, String default_value, String display_width, String display_height,
			String display_style, String display_state, String parameter, boolean isPkmodify) {
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("");
		String sql = "select * from system_entity_column where entity_code=? and column_code=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(entity_code.toUpperCase()),
				new Parameter.String(column_code.toUpperCase()) };
		DBObject db = new DBObject();
		try {
			String item_name = entity_code + "." + column_code;
			DataTable dt = db.runSelectQuery(sql, pp);
			String column_name = dt.get(0).getString("column_name").toLowerCase();
			String is_primarykey = dt.get(0).getString("is_primarykey").toLowerCase();
			String foreign_entity = Format.NullToBlank(dt.get(0).getString("foreign_entity")).toLowerCase();
			String column_type = dt.get(0).getString("column_type").toLowerCase();
			String column_length = dt.get(0).getString("column_length").toLowerCase();
			String code_entity = Format.NullToBlank(dt.get(0).getString("code_entity"));
			String edit_code = Format.NullToBlank(dt.get(0).getString("edit_code")).toLowerCase();
			// 画组件
			if (edit_code.equals("") || edit_code.equals("00010001")) {
				// 没有配置或文本框，直接画一个录入框
				if (display_style == null)
					display_style = this.Input_Class;
				if (display_width == null)
					display_width = this.Width;
				if (display_height == null)
					display_height = this.Height_normal;
				if (display_state == null)
					display_state = this.display_state;
				sBuilder.append("<input class=\"easyui-textbox\" type=\"text\" id=\"" + item_name + "\"   name=\"" + item_name + "\" "
						+ display_state + " data-options=\"required:true\" value=\"" + default_value + "\" />");
			}
			if (edit_code.equals("00010002")) {
				// 文本框，只能录入数字
				if (display_style == null)
					display_style = this.Input_Class;
				if (display_width == null)
					display_width = this.Width;
				if (display_height == null)
					display_height = this.Height_normal;
				if (display_state == null)
					display_state = this.display_state;
				if (default_value.equals("")) {
					sBuilder.append("<input class=\"easyui-textbox\" type=\"text\"  id=\""
							+ item_name
							+ "\"    name=\""
							+ item_name
							+ "\" "
							+ display_state
							+ " data-options=\"required:true\"  onkeyup=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\" "
							+ "onafterpaste=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\" />");
				} else {
					sBuilder.append("<input class=\"easyui-textbox\" type=\"text\"   id=\""
							+ item_name
							+ "\"   name=\""
							+ item_name
							+ "\" "
							+ display_state
							+ " data-options=\"required:true\"  onkeyup=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\" "
							+ "onafterpaste=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\D/g,'')}\" value="
							+ Integer.parseInt(default_value) + " />");
				}

			}
			if (edit_code.equals("00010003")) {
				// 文本框，日期选择
				if (display_style == null)
					display_style = this.Input_Class;
				if (display_width == null)
					display_width = this.Width;
				if (display_height == null)
					display_height = this.Height_normal;

				sBuilder.append("<input type='Wdate' onfocus=\"new WdatePicker(this,null,false,'whyGreen')\"     name=\"" + item_name
						+ "\" id=\"" + item_name + "\" class=\"" + display_style + "\" style=\"width:" + display_width + ";height="
						+ display_height + "\" value=\"" + default_value + "\"");
				sBuilder.append(">");

			}
			
			if (edit_code.equals("00010004")) {
				// 下拉框
				CodeDictionary cd = new CodeDictionary();
				if (display_style == null)
					display_style = this.Select_class;
				if (display_width == null)
					display_width = this.Width;
				if (display_height == null)
					display_height = this.Height_normal;
				sBuilder.append("<select    id=\"" + item_name + "\"  name=\"" + item_name + "\">");
				if (default_value.equals("")) {
					sBuilder.append("<option value=''>--请选择--</option>");
				} else {
					sBuilder.append("<option value='" + default_value + "'>" + cd.code_traslate(code_entity, default_value) + "</option>");
				}

				sBuilder.append(cd.getselectItem(code_entity));
				sBuilder.append("</select>");
			}
			if (edit_code.equals("00010005")) {
				// 从编码表构造单选框
				CodeDictionary cd = new CodeDictionary();
				sBuilder.append(cd.RadioItem(code_entity, item_name, default_value));
			}
			if (edit_code.equals("00010006")) {
				// 从编码表构造复选框
				CodeDictionary cd = new CodeDictionary();
				sBuilder.append(cd.Checkbox(code_entity, item_name, default_value));
			}
			if (edit_code.equals("00010007")) {
				// 从弹出框选择编码
			}
			if (edit_code.equals("00010008")) {
				// 从弹出框选择值（需要指定路径）
			}
			if (edit_code.equals("00010009")) {
				// 大文本框TEXT

				if (display_style == null)
					display_style = this.Textarea_class;
				if (display_width == null)
					display_width = this.Width;
				if (display_height == null)
					display_height = "80px";
				// sBuilder.append("<input class=\"easyui-textbox\"  id=\""+item_name+"\"   name=\""+item_name+"\" data-options=\"multiline:true\" style=\"height:60px\" value=\""+default_value+"\"></input>");
				sBuilder.append("<textarea name=\"" + item_name + "\" id=\"" + item_name + "\" class=\"" + display_style
						+ "\" style=\"width:" + display_width + ";height:" + display_height + " \" value=\"" + default_value + "\">"
						+ default_value + "</textarea>");

			}
			if (edit_code.equals("00010010")) {
				// 文件上传
				if (display_style == null)
					display_style = this.Textarea_class;
				if (display_width == null)
					display_width = this.Width;
				if (display_height == null)
					display_height = this.Height_normal;
				sBuilder.append("<input type=\"file\" name=\"" + item_name + "\" id=\"" + item_name + "\"+class=\"" + display_style
						+ "\" style=\"width:" + display_width + "\";height=\"" + display_height + "\" />");
			}
			// 如果是主键又需要修改

			// 构造校验SCRIPT

			if (column_type.equals("DATE")) {
				column_length = "10";
			}
			if (!(edit_code.equals("00010005")) && !(edit_code.equals("00010006"))) {
				// 长度检查
				CheckScript = CheckScript + "if (document.all('" + item_name + "').value.length>" + column_length
						+ ") check_res=check_res+'数据项：" + column_name + "长度超过系统允许的值（" + column_length + "）\\n';\n";
				// 数字的校验
				// 日期型数据的校验
				CheckScript = CheckScript + "if ('" + column_type + "'=='date')" + "if(!isDate(document.all('" + item_name + "').value))"
						+ "check_res=check_res+'数据项：" + column_name + "必须为日期型\\n';\n";
			}

			// 主键的非空检查
			if (is_primarykey.equals("y")) {
				CheckScript = CheckScript + "if (document.all('" + item_name + "').value==null||document.all('" + item_name
						+ "').value=='') check_res=check_res+'数据项：" + column_name + "为表单主键不能为空\\n';\n";
			}
			// 其它的校验

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sBuilder.toString();
	}

	public String getCheckstr() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("");
		sBuilder.append("<script language=\"javascript\">");
		sBuilder.append("function sumbit_check(){");
		sBuilder.append(CheckScript);
		sBuilder.append("if (check_res!='') {");
		sBuilder.append("alert (check_res);return false;");
		sBuilder.append("} else {return true;}");
		sBuilder.append("}</script>");
		return sBuilder.toString();
	}
}
