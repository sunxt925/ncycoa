package edu.cqu.gem.common.tag;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

import edu.cqu.gem.common.dto.ColumnValue;
import edu.cqu.gem.common.dto.DataGridColumn;
import edu.cqu.gem.common.dto.DataGridOperation;
import edu.cqu.gem.common.dto.DataGridOperationType;
import edu.cqu.gem.ncycoa.domain.DataDictionary;
import edu.cqu.gem.ncycoa.domain.DataDictionaryItem;
import edu.cqu.gem.ncycoa.service.CommonService;
import edu.cqu.gem.ncycoa.util.SystemUtils;

/**
 * DATAGRID标签处理类
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked", "static-access" })
public class DataGridTag extends TagSupport {
	protected String fields = "";				// 所有待显示的字段，以逗号分隔
	protected String searchFields = "";			// 查询字段 
												// for：添加对区间查询的支持
	private String name;						// 表格标识
	private String title;						// 表格标题
	private String width;
	private String height;
	protected String idField = "id";			// 主键字段
	protected boolean treegrid = false;			// 是否是树形列表
	protected List<DataGridOperation> operationList = new ArrayList<DataGridOperation>();	// 列表操作显示
	protected List<DataGridOperation> toolBarList = new ArrayList<DataGridOperation>();		// 工具条列表
	protected List<DataGridColumn> columnList = new ArrayList<DataGridColumn>();	// 列表操作显示
	protected List<ColumnValue> columnValueList = new ArrayList<ColumnValue>();	// 值替换集合
	protected List<ColumnValue> columnStyleList = new ArrayList<ColumnValue>();	// 颜色替换集合
	public Map<String, Object> map;				// 封装查询条件
	
	/* ******************************************************************* */
	private String actionUrl;					// 数据查询url
	public int allCount;
	public int curPageNo;
	public int pageSize = 10;
	public boolean pagination = true;			// 是否显示分页
	private boolean showPageList = true;		// 定义是否显示页面列表
	private boolean showText = true;			// 定义是否显示分页信息
	private boolean showRefresh = true;			// 定义是否显示刷新按钮
	/* ******************************************************************* */

	private boolean checkbox = false;			// 是否显示复选框
	private boolean openFirstNode = false;		// 是不是展开第一个节点
	private boolean fit = true;					// 是否允许表格自动缩放，以适应父容器
	private boolean fitColumns = true;			// 当为true时，自动展开/合同列的大小，以适应的宽度，防止横向滚动.
	private String sortName;					// 定义的列进行排序
	private String sortOrder = "asc";			// 定义列的排序顺序，只能是"递增"或"降序".
	private String style = "easyui";			// 列表样式easyui,datatables
	private String onLoadSuccess;				// 数据加载完成调用方法
	private String onClick;						// 单击事件调用方法
	private String onDblClick;					// 双击事件调用方法
	private String queryMode = "single";		// 查询模式
	private String entityName;					// 对应的实体对象
	private String rowStyler;					// rowStyler函数
	private String extendParams;				// 扩展参数,easyui有但此处没有
	private boolean autoLoadData = true; 		// 列表是否自动加载数据
	
	// json转换中的系统保留字
	protected static Map<String, String> syscode = new HashMap<String, String>();
	static {
		syscode.put("class", "clazz");
	}
	
	@Override
	public int doStartTag() throws JspTagException {
		operationList.clear();
		toolBarList.clear();
		columnValueList.clear();
		columnStyleList.clear();
		columnList.clear();
		fields = "";
		searchFields = "";
		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			if (style.equals("easyui")) {
				out.print(end().toString());
			} else {
				out.print(datatables().toString());
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 设置自定义操作
	 */
	public void setOperationUrl(DataGridOperation dataGridUrl, String operationCode) {
		installOperation(dataGridUrl, operationCode, operationList);
	}

	/**
	 * 设置工具条
	 */
	public void setToolbar(DataGridOperation dataGridUrl, String operationCode) {
		installOperation(dataGridUrl, operationCode, toolBarList);
	}

	/**
	 * 设置字段
	 */
	public void setColumn(DataGridColumn dgColumn) {
		String field = dgColumn.getField();
		if (!StringUtils.equals("opt", field)) {
			fields += field + ",";
			if (StringUtils.equals("group", dgColumn.getQueryMode())) {
				searchFields += field + "," + field + "_begin," + field + "_end,";
			} else {
				searchFields += field + ",";
			}
		}
		
		// 将value替换显示成text
		if (StringUtils.isNotBlank(dgColumn.getReplace())) {
			String[] replaceUnits = dgColumn.getReplace().split(",");
			String text = "";
			String value = "";
			for (String replaceUnit : replaceUnits) {
				int pos = replaceUnit.indexOf("_");
				text += replaceUnit.substring(0, pos) + ",";
				value += replaceUnit.substring(pos + 1) + ",";
			}
			setColumn(field, text, value);
		}
		
		if (StringUtils.isNotBlank(dgColumn.getDictionary())) {
			String dictionary = dgColumn.getDictionary();
			if (dictionary.contains(",")) {
				String[] dic = dictionary.split(",");
				String text = "";
				String value = "";
				String sql = "select " + dic[1] + " as field," + dic[2] + " as text from " + dic[0];
				CommonService commonService = SystemUtils.getCommonService();
				List<Map<String, Object>> list = commonService.findSetBySql(sql);
				for (Map<String, Object> map : list) {
					text += map.get("text") + ",";
					value += map.get("field") + ",";
				}
				if (list.size() > 0)
					setColumn(field, text, value);
			} else {
				String text = "";
				String value = "";
				List<DataDictionaryItem> typeList = DataDictionary.getAllDictionaryItems().get(dictionary.toLowerCase());
				if (typeList != null && !typeList.isEmpty()) {
					for (DataDictionaryItem type : typeList) {
						text += type.getName() + ",";
						value += type.getCode() + ",";
					}
					setColumn(field, text, value);
				}
			}
		}
		
		if (StringUtils.isNotBlank(dgColumn.getStyle())) {
			String[] temp = dgColumn.getStyle().split(",");
			String text = "";
			String value = "";
			if (temp.length == 1 && temp[0].indexOf("_") == -1) {
				text = temp[0];
			} else {
				for (String string : temp) {
					text += string.substring(0, string.indexOf("_")) + ",";
					value += string.substring(string.indexOf("_") + 1) + ",";
				}
			}
			setStyleColumn(field, text, value);
		}
		
		columnList.add(dgColumn);
	}

	/**
	 * 设置 颜色替换值
	 */
	private void setStyleColumn(String field, String text, String value) {
		ColumnValue columnValue = new ColumnValue();
		columnValue.setName(field);
		columnValue.setText(text);
		columnValue.setValue(value);
		columnStyleList.add(columnValue);
	}

	/**
	 * 将value替换显示成text
	 */
	public void setColumn(String name, String text, String value) {
		ColumnValue columnValue = new ColumnValue();
		columnValue.setName(name);
		columnValue.setText(text);
		columnValue.setValue(value);
		columnValueList.add(columnValue);
	}

	/**
	 * datatables构造方法
	 */
	public StringBuffer datatables() {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("$(document).ready(function() {");
		sb.append("var oTable = $(\'#userList\').dataTable({");
		// sb.append(
		// "\"sDom\" : \"<\'row\'<\'span6\'l><\'span6\'f>r>t<\'row\'<\'span6\'i><\'span6\'p>>\",");
		sb.append("\"bProcessing\" : true,");// 当datatable获取数据时候是否显示正在处理提示信息"
		sb.append("\"bPaginate\" : true,"); // 是否分页"
		sb.append("\"sPaginationType\" : \"full_numbers\",");// 分页样式full_numbers,"
		sb.append("\"bFilter\" : true,");// 是否使用内置的过滤功能"
		sb.append("\"bSort\" : true, ");// 排序功能"
		sb.append("\"bAutoWidth\" : true,");// 自动宽度"
		sb.append("\"bLengthChange\" : true,");// 是否允许用户自定义每页显示条数"
		sb.append("\"bInfo\" : true,");// 页脚信息"
		sb.append("\"sAjaxSource\" : \"userController.do?test\",");
		sb.append("\"bServerSide\" : true,");// 指定从服务器端获取数据
		sb.append("\"oLanguage\" : {" + "\"sLengthMenu\" : \" _MENU_ 条记录\"," + "\"sZeroRecords\" : \"没有检索到数据\","
				+ "\"sInfo\" : \"第 _START_ 至 _END_ 条数据 共 _TOTAL_ 条\"," + "\"sInfoEmtpy\" : \"没有数据\"," + "\"sProcessing\" : \"正在加载数据...\","
				+ "\"sSearch\" : \"搜索\"," + "\"oPaginate\" : {" + "\"sFirst\" : \"首页\"," + "\"sPrevious\" : \"前页\", "
				+ "\"sNext\" : \"后页\"," + "\"sLast\" : \"尾页\"" + "}" + "},"); // 汉化
		// 获取数据的处理函数 \"data\" : {_dt_json : JSON.stringify(aoData)},
		sb.append("\"fnServerData\" : function(sSource, aoData, fnCallback, oSettings) {");
		// + "\"data\" : {_dt_json : JSON.stringify(aoData)},"
		sb.append("oSettings.jqXHR = $.ajax({" + "\"dataType\" : \'json\'," + "\"type\" : \"POST\"," + "\"url\" : sSource,"
				+ "\"data\" : aoData," + "\"success\" : fnCallback" + "});},");
		sb.append("\"aoColumns\" : [ ");
		int i = 0;
		for (DataGridColumn column : columnList) {
			i++;
			sb.append("{");
			sb.append("\"sTitle\":\"" + column.getTitle() + "\"");
			if (column.getField().equals("opt")) {
				sb.append(",\"mData\":\"" + idField + "\"");
				sb.append(",\"sWidth\":\"20%\"");
				sb.append(",\"bSortable\":false");
				sb.append(",\"bSearchable\":false");
				sb.append(",\"mRender\" : function(data, type, rec) {");
				this.getOptUrl(sb);
				sb.append("}");
			} else {
				int colwidth = (column.getWidth() == null) ? column.getTitle().length() * 15 : column.getWidth();
				sb.append(",\"sName\":\"" + column.getField() + "\"");
				sb.append(",\"mDataProp\":\"" + column.getField() + "\"");
				sb.append(",\"mData\":\"" + column.getField() + "\"");
				sb.append(",\"sWidth\":\"" + colwidth + "\"");
				sb.append(",\"bSortable\":" + column.isSortable() + "");
				sb.append(",\"bVisible\":" + column.isHidden() + "");
				sb.append(",\"bSearchable\":" + column.isQuery() + "");
			}
			sb.append("}");
			if (i < columnList.size())
				sb.append(",");
		}

		sb.append("]" + "});" + "});" + "</script>");
		sb.append("<table width=\"100%\"  class=\"" + style + "\" id=\"" + name + "\" toolbar=\"#" + name + "tb\"></table>");
		return sb;

	}
	
	/**
	 * easyui构造方法
	 */
	public StringBuffer end() {
		String grid = "";
		StringBuffer sb = new StringBuffer();
		width = (width == null) ? "auto" : width;
		height = (height == null) ? "auto" : height;
		sb.append("<script type=\"text/javascript\">");
		sb.append("$(function(){");
		sb.append(this.getNoAuthOperButton());
		if (treegrid) {
			grid = "treegrid";
			sb.append("$(\'#" + name + "\').treegrid({");
			sb.append("idField:'id',");
			sb.append("treeField:'text',");
		} else {
			grid = "datagrid";
			sb.append("$(\'#" + name + "\').datagrid({");
			sb.append("idField: '" + idField + "',");
		}

		if (title != null) {
			sb.append("title: \'" + title + "\',");
		}

		sb.append("border: false,");
		if (autoLoadData){
			if(actionUrl.indexOf("?") != -1) {
				sb.append("url:\'" + actionUrl + "&field=" + fields + "\',");
			} else {
				sb.append("url:\'" + actionUrl + "?field=" + fields + "\',");
			}
		} else {
			sb.append("url:\'',");
		}

		if (StringUtils.isNotEmpty(rowStyler)) {
			sb.append("rowStyler: function(index,row){ return " + rowStyler + "(index,row);},");
		}
		if (StringUtils.isNotEmpty(extendParams)) {
			sb.append(extendParams);
		}
		if (fit) {
			sb.append("fit:true,");
		} else {
			sb.append("fit:false,");
		}
		sb.append("loadMsg: \'数据加载中...\',");
		sb.append("pageSize: " + pageSize + ",");
		sb.append("pagination:" + pagination + ",");
		sb.append("pageList:[" + pageSize * 1 + "," + pageSize * 2 + "," + pageSize * 3 + "],");
		if (StringUtils.isNotBlank(sortName)) {
			sb.append("sortName:'" + sortName + "',");
		}
		sb.append("sortOrder:'" + sortOrder + "',");
		sb.append("rownumbers:true,");
		sb.append("singleSelect:" + !checkbox + ",");
		if (fitColumns) {
			sb.append("fitColumns:true,");
		} else {
			sb.append("fitColumns:false,");
		}
		sb.append("showFooter:true,");
		sb.append("frozenColumns:[[");
		this.getField(sb, 0);
		sb.append("]],");

		sb.append("columns:[[");
		this.getField(sb, 1);
		sb.append("]],");
		
		/* ************************************数据加载完成时***************************************************** */
			sb.append("onLoadSuccess:function(data){$(\"#" + name + "\")." + grid + "(\"clearSelections\");");
			if (openFirstNode && treegrid) {
				sb.append(" if(data==null){");
				sb.append(" var firstNode = $(\'#" + name + "\').treegrid('getRoots')[0];");
				sb.append(" $(\'#" + name + "\').treegrid('expand',firstNode.id)}");
			}
			if (StringUtils.isNotEmpty(onLoadSuccess)) {
				sb.append(onLoadSuccess + "(data);");
			}
			sb.append("},");
		/* ******************************************************************************************************* */
		
		/* *****************************************双击行时****************************************************** */
			if (StringUtils.isNotEmpty(onDblClick)) {
				sb.append("onDblClickRow:function(rowIndex,rowData){" + onDblClick + "(rowIndex,rowData);},");
			}
		/* ******************************************************************************************************* */
		
		/* *****************************************单击行时****************************************************** */
			if (treegrid) {
				sb.append("onClickRow:function(rowData){");
			} else {
				sb.append("onClickRow:function(rowIndex,rowData){");
			}
			/** 行记录赋值 */
			sb.append("rowid=rowData.id;");
			sb.append("gridname=\'" + name + "\';");
			if (StringUtils.isNotEmpty(onClick)) {
				if (treegrid) {
					sb.append("" + onClick + "(rowData);");
				} else {
					sb.append("" + onClick + "(rowIndex,rowData);");
				}
			}
			sb.append("}");
		/* ******************************************************************************************************* */
			
		sb.append("});"); //grid 完成
		
		this.setPager(sb, grid);
		
		sb.append("});"); //$(function(){})完成
		
		// reloadTable
		sb.append("function reloadTable(){");
		sb.append("try{");
		sb.append("	$(\'#\'+gridname).datagrid(\'reload\');");
		sb.append("	$(\'#\'+gridname).treegrid(\'reload\');");
		sb.append("}catch(ex){}");
		sb.append("}");
		
		// reload{GridName}, get{GridName}Selected, getSelected, get{GridName}Selections，getSelectRows
		sb.append("function reload" + name + "(){" + "$(\'#" + name + "\')." + grid + "(\'reload\');" + "}");
		sb.append("function get" + name + "Selected(field){return getSelected(field);}");
		sb.append("function getSelected(field){" + "var row = $(\'#\'+gridname)." + grid + "(\'getSelected\');" + "if(row!=null)" + "{"
				+ "value= row[field];" + "}" + "else" + "{" + "value=\'\';" + "}" + "return value;" + "}");
		sb.append("function get" + name + "Selections(field){" + "var ids = [];" + "var rows = $(\'#" + name + "\')." + grid
				+ "(\'getSelections\');" + "for(var i=0;i<rows.length;i++){" + "ids.push(rows[i][field]);" + "}" + "ids.join(\',\');"
				+ "return ids" + "};");
		sb.append("function getSelectRows(){");
		sb.append("	return $(\'#\' + gridname)."+ grid + "('getChecked');");
		sb.append("}");
		
		if (columnList.size() > 0) {
			sb.append("function " + name + "search(){");
			sb.append("var queryParams=$(\'#" + name + "\')." +grid + "('options').queryParams;");
			sb.append("$(\'#" + name + "tb\').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
			sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',pageNumber:1});" + "}");

			// 高级查询执行方法
			sb.append("function dosearch(params){");
			sb.append("var jsonparams=$.parseJSON(params);");
			sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl + "&field=" + searchFields + "',queryParams:jsonparams});"
					+ "}");

			if (toolBarList.size() > 0) {
				// searchbox框执行方法
				searchboxFun(sb, grid);
			}
			// 生成重置按钮功能js
			sb.append("function " + name + "searchReset(name){");
			sb.append(" $(\"#\"+name+\"tb\").find(\":input\").val(\"\");");
			String func = name.trim() + "search();";
			sb.append(func);
			sb.append("}");
		}
		sb.append("</script>");
		
		sb.append("<table width=\"100%\" id=\"" + name + "\" toolbar=\"#" + name + "tb\"></table>");
		
		sb.append("<div id=\"" + name + "tb\" style=\"padding:3px; height: auto\">");
		if (hasQueryColum(columnList)) {
			sb.append("<div name=\"searchColums\">");
			// 如果表单是组合查询
			if ("group".equals(getQueryMode())) {
				for (DataGridColumn col : columnList) {
					if (col.isQuery()) {
						sb.append("<span style=\"display:-moz-inline-box;display:inline-block;\">");
						sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; \" title=\""
								+ col.getTitle() + "\">" + col.getTitle() + "：</span>");
						if ("single".equals(col.getQueryMode())) {
							if (!StringUtils.isEmpty(col.getReplace())) {
								sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.")
										+ "\" WIDTH=\"100\" style=\"width: 104px\"> ");
								sb.append("<option value =\"\" >---请选择---</option>");
								String[] test = col.getReplace().split(",");
								String text = "";
								String value = "";
								for (String string : test) {
									text = string.split("_")[0];
									value = string.split("_")[1];
									sb.append("<option value =\"" + value + "\">" + text + "</option>");
								}
								sb.append("</select>");
							} else if (!StringUtils.isEmpty(col.getDictionary())) {
								if (col.getDictionary().contains(",")) {
									String[] dic = col.getDictionary().split(",");
									String sql = "select " + dic[1] + " as field," + dic[2] + " as text from " + dic[0];
									CommonService commonService = SystemUtils.getCommonService();
									List<Map<String, Object>> list = commonService.findSetBySql(sql);
									sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.")
											+ "\" WIDTH=\"100\" style=\"width: 104px\"> ");
									sb.append("<option value =\"\" >---请选择---</option>");
									for (Map<String, Object> map : list) {
										sb.append(" <option value=\"" + map.get("field") + "\">");
										sb.append(map.get("text"));
										sb.append(" </option>");
									}
									sb.append("</select>");
								} else {
									Map<String, List<DataDictionaryItem>> typedatas = DataDictionary.getAllDictionaryItems();
									List<DataDictionaryItem> types = typedatas.get(col.getDictionary().toLowerCase());
									sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.")
											+ "\" WIDTH=\"100\" style=\"width: 104px\"> ");
									sb.append("<option value =\"\" >---请选择---</option>");
									for (DataDictionaryItem type : types) {
										sb.append(" <option value=\"" + type.getCode() + "\">");
										sb.append(type.getName());
										sb.append(" </option>");
									}
									sb.append("</select>");
								}
							} else if (col.isAutocomplete()) {
								sb.append(getAutoSpan(col.getField().replaceAll("_", "\\."), extendAttribute(col.getExtend())));
							} else {
								sb.append("<input type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\"  "
										+ extendAttribute(col.getExtend()) + " style=\"width: 100px\" />");
							}
						} else if ("group".equals(col.getQueryMode())) {
							sb.append("<input type=\"text\" name=\"" + col.getField() + "_begin\"  style=\"width: 94px\" "
									+ extendAttribute(col.getExtend()) + "/>");
							sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
							sb.append("<input type=\"text\" name=\"" + col.getField() + "_end\"  style=\"width: 94px\" "
									+ extendAttribute(col.getExtend()) + "/>");
						}
						sb.append("</span>");
					}
				}
			}
			sb.append("</div>");
		}
		if (toolBarList.size() == 0 && !hasQueryColum(columnList)) {
			sb.append("<div style=\"height:0px;\" >");
		} else {
			sb.append("<div style=\"height:30px;\" class=\"datagrid-toolbar\">");
		}
		sb.append("<span style=\"float:left;\" >");
		if (toolBarList.size() > 0) {
			for (DataGridOperation toolBar : toolBarList) {
				sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + toolBar.getIcon() + "\" ");
				if (StringUtils.isNotEmpty(toolBar.getOnclick())) {
					sb.append("onclick=" + toolBar.getOnclick() + "");
				} else {
					sb.append("onclick=\"" + toolBar.getFunname() + "(");
					if (!toolBar.getFunname().equals("doSubmit")) {
						sb.append("\'" + toolBar.getTitle() + "\',");
					}
					String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
					String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
					sb.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height + ")\"");
				}
				sb.append(">" + toolBar.getTitle() + "</a>");
			}
		}
		sb.append("</span>");
		if ("group".equals(getQueryMode()) && hasQueryColum(columnList)) {// 如果表单是组合查询
			sb.append("<span style=\"float:right\">");
			sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"" + name + "search()\">查询</a>");
			sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-reload\" onclick=\"" + name + "searchReset('" + name
					+ "')\">重置</a>");
			sb.append("</span>");
		} else if ("single".equals(getQueryMode()) && hasQueryColum(columnList)) {// 如果表单是单查询
			sb.append("<span style=\"float:right\">");
			sb.append("<input id=\"" + name + "searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:" + name
					+ "searchbox,prompt:\'请输入关键字\',menu:\'#" + name + "mm\'\"></input>");
			sb.append("<div id=\"" + name + "mm\" style=\"width:120px\">");
			for (DataGridColumn col : columnList) {
				if (col.isQuery()) {
					sb.append("<div data-options=\"name:\'" + col.getField().replaceAll("_", "\\.") + "\',iconCls:\'icon-ok\' "
							+ extendAttribute(col.getExtend()) + " \">" + col.getTitle() + "</div>  ");
				}
			}
			sb.append("</div>");
			sb.append("</span>");
		}
		sb.append("</div>");
		return sb;
	}

	/**
	 * 生成扩展属性
	 */
	private String extendAttribute(String field) {
		if (StringUtils.isEmpty(field)) {
			return "";
		}
		field = dealSyscode(field, 1);
		StringBuilder re = new StringBuilder();
		try {
			JSONObject obj = JSONObject.parseObject(field);
			Iterator it = obj.keySet().iterator();
			while (it.hasNext()) {
				String key = String.valueOf(it.next());
				JSONObject nextObj = ((JSONObject) obj.get(key));
				Iterator itvalue = nextObj.keySet().iterator();
				re.append(key + "=" + "\"");
				if (nextObj.size() <= 1) {
					String onlykey = String.valueOf(itvalue.next());
					if ("value".equals(onlykey)) {
						re.append(nextObj.get(onlykey) + "");
					} else {
						re.append(onlykey + ":" + nextObj.get(onlykey) + "");
					}
				} else {
					while (itvalue.hasNext()) {
						String multkey = String.valueOf(itvalue.next());
						String multvalue = nextObj.getString(multkey);
						re.append(multkey + ":" + multvalue + ",");
					}
					re.deleteCharAt(re.length() - 1);
				}
				re.append("\" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return dealSyscode(re.toString(), 2);
	}

	/**
	 * 处理否含有json转换中的保留字
	 * 
	 * @param field
	 * @param flag
	 *            1:转换 2:还原
	 */
	private String dealSyscode(String field, int flag) {
		String change = field;
		Iterator it = syscode.keySet().iterator();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String value = String.valueOf(syscode.get(key));
			if (flag == 1) {
				change = field.replaceAll(key, value);
			} else if (flag == 2) {
				change = field.replaceAll(value, key);
			}
		}
		return change;
	}

	/**
	 * 判断是否存在查询字段
	 * 
	 * @return hasQuery true表示有查询字段,false表示没有
	 */
	protected boolean hasQueryColum(List<DataGridColumn> columnList) {
		boolean hasQuery = false;
		for (DataGridColumn col : columnList) {
			if (col.isQuery()) {
				hasQuery = true;
			}
		}
		return hasQuery;
	}
	
	private static String getFunction(String functionname) {
		int index = functionname.indexOf("(");
		if (index == -1) {
			return functionname;
		} else {
			return functionname.substring(0, functionname.indexOf("("));
		}
	}
	
	private static String getFunParams(String functionname) {
		int index = functionname.indexOf("(");
		String param="";
		if (index != -1) {
			String testparam = functionname.substring(functionname.indexOf("(") + 1, functionname.length() - 1);
			if(StringUtils.isNotEmpty(testparam)) {
				String[] params = testparam.split(",");
				for (String string : params) {
					if(string.indexOf("{") != -1) {
						param += "'\"+rec."+ string.substring(1,string.length()-1) + "+\"',";
					} else {
						param += "'\"+"+ string + "+\"',";
					}
				}
			}
		} 
		param+="'\"+index+\"'";//传出行索引号参数
		return param;
	}

	/**
	 * 拼装操作地址
	 */
	protected void getOptUrl(StringBuffer sb) {
		// 注：操作列表会带入合计列中去，故加此判断
		sb.append("if(!rec.id){return '';}");
		List<DataGridOperation> list = operationList;
		sb.append("var href='';");
		for (DataGridOperation dgOperation : list) {
			String url = dgOperation.getUrl();
			MessageFormat formatter = new MessageFormat("");
			
			if (dgOperation.getValue() != null) {
				String[] testvalue = dgOperation.getValue().split(",");
				List value = new ArrayList<Object>();
				for (String string : testvalue) {
					value.add("\"+rec." + string + " +\"");
				}
				url = formatter.format(url, value.toArray());
			}
			if (url != null && dgOperation.getValue() == null) {
				url = formatUrl(url);
			}
			
			//"field#[in,out]#value1,value2,value3&&field1#[empty]#true"
			String exp = dgOperation.getExp();		// 判断显示表达式
			if (StringUtils.isNotEmpty(exp)) {
				String[] showbyFields = exp.split("&&");
				for (String showbyField : showbyFields) {
					int beginIndex = showbyField.indexOf("#");
					int endIndex = showbyField.lastIndexOf("#");
					String exptype = showbyField.substring(beginIndex + 1, endIndex);// 表达式类型
					String field = showbyField.substring(0, beginIndex);// 判断显示依据字段
					String[] values = showbyField.substring(endIndex + 1, showbyField.length()).split(",");// 传入字段值
					String value = "";
					for (int i = 0; i < values.length; i++) {
						value += "'" + values[i] + "'";
						if (i < values.length - 1) {
							value += ",";
						}
					}
					if ("in".equals(exptype)) {
						sb.append("if($.inArray(rec." + field + ",[" + value + "])>=0){");
					}
					if ("out".equals(exptype)) {
						sb.append("if($.inArray(rec." + field + ",[" + value + "])<0){");
					}
					if ("empty".equals(exptype) && value.equals("'true'")) {
						sb.append("if(rec." + field + "==''){");
					}
					if ("empty".equals(exptype) && value.equals("'false'")) {
						sb.append("if(rec." + field + "!=''){");
					}
				}
			}

			if (DataGridOperationType.Confirm.equals(dgOperation.getType())) {
				sb.append("href+=\"[<a href=\'#\' onclick=confirm(\'" + url + "\',\'" + dgOperation.getMessage() + "\',\'" + name + "\')> \";");
			}
			if (DataGridOperationType.Del.equals(dgOperation.getType())) {
				sb.append("href+=\"[<a href=\'#\' onclick=delObj(\'" + url + "\',\'" + name + "\')>\";");
			}
			if (DataGridOperationType.Fun.equals(dgOperation.getType())) {
				String name = getFunction(dgOperation.getFunname());
				String parmars = getFunParams(dgOperation.getFunname());
				sb.append("href+=\"[<a href=\'#\' onclick=" + name + "(" + parmars + ")>\";");
			}
			if (DataGridOperationType.OpenWin.equals(dgOperation.getType())) {
				sb.append("href+=\"[<a href=\'#\' onclick=openwindow('" + dgOperation.getTitle() + "','" + url + "','" + name + "',"
						+ dgOperation.getWidth() + "," + dgOperation.getHeight() + ")>\";");
			} 
			if (DataGridOperationType.Deff.equals(dgOperation.getType())) {
				sb.append("href+=\"[<a href=\'" + url + "' title=\'" + dgOperation.getTitle() + "\'>\";");
			}
			if (DataGridOperationType.OpenTab.equals(dgOperation.getType())) {
				sb.append("href+=\"[<a href=\'#\' onclick=addOneTab('" + dgOperation.getTitle() + "','" + url + "')>\";");
			}
			sb.append("href+=\"" + dgOperation.getTitle() + "</a>]\";");

			if (StringUtils.isNotBlank(exp)) {
				for (int i = 0; i < exp.split("&&").length; i++) {
					sb.append("}");
				}
			}
		}
		sb.append("return href;");
	}

	/**
	 * 列自定义函数
	 */
	protected void getFun(StringBuffer sb, DataGridColumn column) {
		String url = column.getUrl();
		url = formatUrl(url);
		sb.append("var href=\"<a style=\'color:red\' href=\'#\' onclick=" + column.getFunname() + "('" + column.getTitle() + "','" + url + "')>\";");
		sb.append("return href+value+\'</a>\';");
	}

	/**
	 * 格式化URL
	 */
	protected String formatUrl(String url) {
		MessageFormat formatter = new MessageFormat("");
		if (url.indexOf("?") >= 0) {
			String beforeurl = url.substring(0, url.indexOf("?"));// 截取请求地址
			String parurl = url.substring(url.indexOf("?") + 1, url.length());// 截取参数
			String[] pras = parurl.split("&");
			List<String> value = new ArrayList<String>();
			String parurlvalue = "?";
			int j = 0;
			for (int i = 0; i < pras.length; i++) {
				if (pras[i].indexOf("{") >= 0 || pras[i].indexOf("#") >= 0) {
					String field = pras[i].substring(pras[i].indexOf("{") + 1, pras[i].lastIndexOf("}"));
					parurlvalue += pras[i].replace("{" + field + "}", "{" + j + "}") + "&";
					value.add("\"+rec." + field + " +\"");
					j++;
				} else {
					parurlvalue += pras[i] + "&";
				}
			}
			
			url = formatter.format(beforeurl + parurlvalue.substring(0, parurlvalue.length() - 1), value.toArray());
		}
		
		return url;
	}

	/**
	 * 拼接字段 普通列
	 */
	protected void getField(StringBuffer sb) {
		getField(sb, 1);
	}

	/**
	 * 拼接字段
	 * 
	 * @param sb
	 * @frozen 0 冰冻列 1 普通列
	 */
	protected void getField(StringBuffer sb, int frozen) {
		// 复选框
		if (checkbox && frozen == 0) {
			sb.append("{field:\'ck\',checkbox:\'true\'},");
		}
		int i = 0;
		for (DataGridColumn column : columnList) {
			i++;
			if ((column.isFrozenColumn() && frozen == 0) || (!column.isFrozenColumn() && frozen == 1)) {
				String field;
				if (treegrid) {
					field = column.getTreefield();
				} else {
					field = column.getField();
				}
				sb.append("{field:\'" + field + "\',title:\'" + column.getTitle() + "\'");
				if (column.getWidth() != null) {
					sb.append(",width:" + column.getWidth());
				}
				if (column.getAlign() != null) {
					sb.append(",align:\'" + column.getAlign() + "\'");
				}
				if (StringUtils.isNotEmpty(column.getExtendParams())) {
					sb.append("," + column.getExtendParams().substring(0, column.getExtendParams().length() - 1));
				}
				// 隐藏字段
				if (column.isHidden()) {
					sb.append(",hidden:true");
				}
				if (!treegrid) {
					// 字段排序
					if (column.isSortable() && (field.indexOf("_") <= 0 && field != "opt")) {
						sb.append(",sortable:" + column.isSortable());
					}
				}
				// 显示图片
				if (column.isImage()) {
					sb.append(",formatter:function(value,rec,index){");
					sb.append(" return '<img border=\"0\" src=\"'+value+'\"/>';}");
				}
				// 自定义显示图片
				if (column.getImageSize() != null) {
					String[] tld = column.getImageSize().split(",");
					sb.append(",formatter:function(value,rec,index){");
					sb.append(" return '<img width=\"" + tld[0] + "\" height=\"" + tld[1] + "\" border=\"0\" src=\"'+value+'\"/>';}");
					tld = null;
				}
				if (column.getDownloadName() != null) {
					sb.append(",formatter:function(value,rec,index){");
					sb.append(" return '<a target=\"_blank\" href=\"'+value+'\">" + column.getDownloadName() + "</a>';}");
				}
				// 自定义链接
				if (column.getUrl() != null) {
					sb.append(",formatter:function(value,rec,index){");
					this.getFun(sb, column);
					sb.append("}");
				}
				//日期格式化
				if (column.getDateFormatter() != null) {
					sb.append(",formatter:function(value,rec,index){");
					sb.append(" return new Date().format('" + column.getDateFormatter() + "',value);}");
				}
				// 加入操作
				if (column.getField().equals("opt")) {
					sb.append(",formatter:function(value,rec,index){");
					// sb.append("return \"");
					this.getOptUrl(sb);
					sb.append("}");
				}
				// 值替換
				if (columnValueList.size() > 0 && !StringUtils.equals(column.getField(), "opt")) {
					String testString = "";
					for (ColumnValue columnValue : columnValueList) {
						if (columnValue.getName().equals(column.getField())) {
							String[] value = columnValue.getValue().split(",");
							String[] text = columnValue.getText().split(",");
							sb.append(",formatter:function(value,rec,index){");
							for (int j = 0; j < value.length; j++) {
								testString += "if(value=='" + value[j] + "'){return \'" + text[j] + "\'}";
							}
							sb.append(testString);
							sb.append("else{return value}");
							sb.append("}");
						}
					}
				}
				// 背景设置
				if (columnStyleList.size() > 0 && !column.getField().equals("opt")) {
					String testString = "";
					for (ColumnValue columnValue : columnStyleList) {
						if (columnValue.getName().equals(column.getField())) {
							String[] value = columnValue.getValue().split(",");
							String[] text = columnValue.getText().split(",");
							sb.append(",styler:function(value,rec,index){");
							if ((value.length == 0 || StringUtils.isEmpty(value[0])) && text.length == 1) {
								if (text[0].indexOf("(") > -1) {
									testString = " return \'" + text[0].replace("(", "(value,rec,index") + "\'";
								} else {
									testString = " return \'" + text[0] + "\'";
								}
							} else {
								for (int j = 0; j < value.length; j++) {
									testString += "if(value=='" + value[j] + "'){return \'" + text[j] + "\'}";
								}
							}
							sb.append(testString);
							sb.append("}");
						}
					}

				}
				sb.append("}");
				// 去除末尾,
				if (i < columnList.size()) {
					sb.append(",");
				}
			}
		}
	}

	/**
	 * 设置分页条信息
	 */
	protected void setPager(StringBuffer sb, String grid) {
		sb.append("$(\'#" + name + "\')." + grid + "(\'getPager\').pagination({");
		sb.append("beforePageText:\'\'," + "afterPageText:\'/{pages}\',");
		if (showText) {
			sb.append("displayMsg:\'{from}-{to}共{total}条\',");
		} else {
			sb.append("displayMsg:\'\',");
		}
		if (showPageList == true) {
			sb.append("showPageList:true,");
		} else {
			sb.append("showPageList:false,");
		}
		sb.append("showRefresh:" + showRefresh + "");
		sb.append("});");// end getPager
		sb.append("$(\'#" + name + "\')." + grid + "(\'getPager\').pagination({");
		sb.append("onBeforeRefresh:function(pageNumber, pageSize){ $(this).pagination(\'loading\');$(this).pagination(\'loaded\'); }");
		sb.append("});");
	}

	// 列表查询框函数
	protected void searchboxFun(StringBuffer sb, String grid) {
		sb.append("function " + name + "searchbox(value,name){");
		sb.append("var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
		sb.append("queryParams[name]=value;queryParams.searchfield=name;$(\'#" + name + "\')." + grid + "(\'reload\');}");
		sb.append("$(\'#" + name + "searchbox\').searchbox({");
		sb.append("searcher:function(value,name){");
		sb.append("" + name + "searchbox(value,name);");
		sb.append("},");
		sb.append("menu:\'#" + name + "mm\',");
		sb.append("prompt:\'请输入查询关键字\'");
		sb.append("});");
	}

	public String getNoAuthOperButton() {
//		List<String> nolist = (List<String>) super.pageContext.getRequest().getAttribute("noauto_operationCodes");
		StringBuffer sb = new StringBuffer();
//		if (SystemUtils.getSessionUser().getUsername().equals("admin") || !SystemUtils.BUTTON_AUTHORITY_CHECK) {
//			
//		} else {
//			if (nolist != null && nolist.size() > 0) {
//				for (String s : nolist) {
//					sb.append("$('#" + name + "tb\').find(\"" + s.replaceAll(" ", "") + "\").hide();");
//				}
//			}
//		}
		
		return sb.toString();
	}

	/**
	 * 组装菜单按钮操作权限 dateGridUrl：url operationCode：操作码 optList： 操作列表
	 */
	private void installOperation(DataGridOperation dataGridUrl, String operationCode, List optList) {
		if (SystemUtils.getSessionUser().getUsername().equals("admin") || !SystemUtils.BUTTON_AUTHORITY_CHECK) {
			optList.add(dataGridUrl);
		} 
//		else if (StringUtils.isNotBlank(operationCode)) {
//			Set<String> operationCodes = (Set<String>) super.pageContext.getRequest().getAttribute("operationCodes");
//			if (null != operationCodes) {
//				for (String MyoperationCode : operationCodes) {
//					if (MyoperationCode.equals(operationCode)) {
//						optList.add(dataGridUrl);
//					}
//				}
//			}
//		} else {
//			optList.add(dataGridUrl);
//		}
	}

	/**
	 * 获取自动补全的panel
	 * 
	 * @param filed
	 * @author JueYue
	 * @return
	 */
	private String getAutoSpan(String filed, String extend) {
		String id = filed.replaceAll("\\.", "_");
		StringBuffer nsb = new StringBuffer();
		nsb.append("<script type=\"text/javascript\">");
		nsb.append("$(document).ready(function() {")
				.append("$(\"#" + getEntityName() + "_" + id + "\").autocomplete(\"commonController.do?getAutoList\",{")
				.append("max: 5,minChars: 2,width: 200,scrollHeight: 100,matchContains: true,autoFill: false,extraParams:{")
				.append("featureClass : \"P\",style : \"full\",	maxRows : 10,labelField : \"" + filed + "\",valueField : \"" + filed
						+ "\",")
				.append("searchField : \"" + filed + "\",entityName : \"" + getEntityName() + "\",trem: function(){return $(\"#"
						+ getEntityName() + "_" + id + "\").val();}}");
		nsb.append(",parse:function(data){return jeecgAutoParse.call(this,data);}");
		nsb.append(",formatItem:function(row, i, max){return row['" + filed + "'];} ");
		nsb.append("}).result(function (event, row, formatted) {");
		nsb.append("$(\"#" + getEntityName() + "_" + id + "\").val(row['" + filed + "']);}); });")
				.append("</script>")
				.append("<input type=\"text\" id=\"" + getEntityName() + "_" + id + "\" name=\"" + filed + "\" datatype=\"*\" " + extend
						+ " nullmsg=\"\" errormsg=\"输入错误\"/>");
		return nsb.toString();
	}

	/**
	 * 获取实体类名称,没有这根据规则设置
	 * 
	 * @return
	 */
	private String getEntityName() {
		if (StringUtils.isEmpty(entityName)) {
			entityName = actionUrl.substring(0, actionUrl.indexOf("Controller"));
			entityName = (entityName.charAt(0) + "").toUpperCase() + entityName.substring(1) + "Entity";
		}
		return entityName;
	}

	public void setOnLoadSuccess(String onLoadSuccess) {
		this.onLoadSuccess = onLoadSuccess;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public void setOnDblClick(String onDblClick) {
		this.onDblClick = onDblClick;
	}

	public void setShowText(boolean showText) {
		this.showText = showText;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTreegrid(boolean treegrid) {
		this.treegrid = treegrid;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFit(boolean fit) {
		this.fit = fit;
	}

	public void setShowPageList(boolean showPageList) {
		this.showPageList = showPageList;
	}

	public void setShowRefresh(boolean showRefresh) {
		this.showRefresh = showRefresh;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public boolean isFitColumns() {
		return fitColumns;
	}

	public void setFitColumns(boolean fitColumns) {
		this.fitColumns = fitColumns;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}

	public boolean isAutoLoadData() {
		return autoLoadData;
	}

	public void setAutoLoadData(boolean autoLoadData) {
		this.autoLoadData = autoLoadData;
	}

	public void setOpenFirstNode(boolean openFirstNode) {
		this.openFirstNode = openFirstNode;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void setRowStyler(String rowStyler) {
		this.rowStyler = rowStyler;
	}

	public void setExtendParams(String extendParams) {
		this.extendParams = extendParams;
	}

}
