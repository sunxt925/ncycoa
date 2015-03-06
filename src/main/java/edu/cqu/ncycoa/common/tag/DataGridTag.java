package edu.cqu.ncycoa.common.tag;

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

import edu.cqu.ncycoa.common.dto.DataGridColumn;
import edu.cqu.ncycoa.common.dto.DataGridOperation;
import edu.cqu.ncycoa.common.dto.DataGridOperationType;
import edu.cqu.ncycoa.common.dto.ValueMapper;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.domain.DataDictionary;
import edu.cqu.ncycoa.domain.DataDictionaryItem;
import edu.cqu.ncycoa.util.SystemUtils;

/**
 * DATAGRID标签处理类
 */
@SuppressWarnings("serial")
public class DataGridTag extends TagSupport {

	/* **********************标签属性*********************************** */
	protected String name; 					// 表格标识，同一个页面中保持唯一
	protected String title; 				// 表格标题
	protected String width; 				// 宽
	protected String height; 				// 高
	protected String idField = "id"; 		// 主键字段
	protected boolean isTree = false; 		// 是否是树形列表
	protected String actionUrl; 			// 数据查询url
	protected boolean pagination = true; 	// 是否显示分页
	protected int pageSize = 10;			// 每页记录数
	protected boolean showPageList = true;  // 定义是否显示页面列表
	protected boolean showText = true; 		// 定义是否显示分页信息
	protected boolean showRefresh = true; 	// 定义是否显示刷新按钮
	protected boolean checkbox = false; 	// 是否显示复选框
	protected boolean openFirstNode = false;// 是不是展开第一个节点
	protected boolean fit = true; 			// 是否允许表格自动缩放，以适应父容器
	protected boolean fitColumns = true; 	// 当为true时，自动展开/合同列的大小，以适应的宽度，防止横向滚动.
	protected String sortName; 				// 定义的列进行排序
	protected String sortOrder = "asc"; 	// 定义列的排序顺序，只能是"递增"或"降序".
	protected String onLoadSuccess; 		// 数据加载完成调用方法
	protected String onClick; 				// 单击事件调用方法
	protected String onDblClick; 			// 双击事件调用方法
	protected String queryMode = "single"; 	// 查询模式
	protected String entityName; 			// 对应的实体对象
	protected String rowStyler; 			// rowStyler函数
	protected String extendParams; 			// 扩展参数,easyui有但此处没有
	protected boolean autoLoadData = true; 	// 列表是否自动加载数据
	/* ******************************************************************* */

	protected String fields = ""; 		// 所有待显示的字段，以逗号分隔
	protected String searchFields = ""; // 所有支持查询的字段，以逗号分隔
	protected List<DataGridOperation> operationList = new ArrayList<DataGridOperation>(); // 列表操作显示
	protected List<DataGridOperation> toolBarList = new ArrayList<DataGridOperation>();   // 工具条列表
	protected List<DataGridColumn> allColumns = new ArrayList<DataGridColumn>(); 		  // 列表操作显示
	protected List<ValueMapper> colValueMapper = new ArrayList<ValueMapper>(); 		      // 值替换集合
	protected List<ValueMapper> colStyleMapper = new ArrayList<ValueMapper>(); 		      // 颜色替换集合
	public Map<String, Object> map; 	// 封装查询条件

	@Override
	public int doStartTag() throws JspTagException {
		operationList.clear();
		toolBarList.clear();
		colValueMapper.clear();
		colStyleMapper.clear();
		allColumns.clear();
		fields = "";
		searchFields = "";
		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	/**
	 * easyui构造方法
	 */
	public StringBuilder end() {
		String gridType = "";    //treegrid还是datagrid
		StringBuilder dgBuilder = new StringBuilder();
		
		width = StringUtils.isEmpty(width) ? "auto" : width;
		height = StringUtils.isEmpty(height) ? "auto" : height;
		fields = fields.length() == 0 ? fields : fields.substring(0, fields.length() - 1);
		searchFields = searchFields.length() == 0 ? searchFields : searchFields.substring(0, searchFields.length() - 1);
		
		dgBuilder.append("<script type=\"text/javascript\">");
		dgBuilder.append("$(function(){");
		if (isTree) {
			gridType = "treegrid";
			dgBuilder.append("$(\'#" + name + "\').treegrid({");
			dgBuilder.append("idField:'id',");
			dgBuilder.append("treeField:'text',");
		} else {
			gridType = "datagrid";
			dgBuilder.append("$(\'#" + name + "\').datagrid({");
			dgBuilder.append("idField: '" + idField + "',");
		}
		if (title != null) {
			dgBuilder.append("title: \'" + title + "\',");
		}
		dgBuilder.append("border: false,");
		if (autoLoadData) {
			if (actionUrl.indexOf("?") != -1) {
				dgBuilder.append("url:\'" + actionUrl + "&field=" + fields + "\',");
			} else {
				dgBuilder.append("url:\'" + actionUrl + "?field=" + fields + "\',");
			}
		} else {
			dgBuilder.append("url:\'',");
		}

		if (StringUtils.isNotEmpty(rowStyler)) {
			dgBuilder.append("rowStyler: function(index,row){ return " + rowStyler + "(index,row);},");
		}
		if (StringUtils.isNotEmpty(extendParams)) {
			dgBuilder.append(extendParams).append(",");
		}
		if (fit) {
			dgBuilder.append("fit:true,");
		} else {
			dgBuilder.append("fit:false,");
		}
		dgBuilder.append("loadMsg: \'数据加载中...\',");
		dgBuilder.append("pagination:" + pagination + ",");
		dgBuilder.append("pageSize: " + pageSize + ",");
		dgBuilder.append("pageList:[" + pageSize * 1 + "," + pageSize * 2 + "," + pageSize * 3 + "],");
		if (StringUtils.isNotEmpty(sortName)) {
			dgBuilder.append("sortName:'" + sortName + "',");
		}
		dgBuilder.append("sortOrder:'" + sortOrder + "',");
		dgBuilder.append("rownumbers:true,");
		dgBuilder.append("singleSelect:" + !checkbox + ",");
		if (fitColumns) {
			dgBuilder.append("fitColumns:true,");
		} else {
			dgBuilder.append("fitColumns:false,");
		}
		dgBuilder.append("showFooter:true,");
		
		//==== build frozen column
		dgBuilder.append("frozenColumns:[[");
		if (checkbox) {
			dgBuilder.append("{field:\'ck\',checkbox:\'true\'},");
		}
		final int dgBuilderLength = dgBuilder.length();
		buildColumns(dgBuilder, 0);
		if (checkbox && dgBuilder.length() == dgBuilderLength) {
			dgBuilder.delete(dgBuilderLength - 1, dgBuilderLength); //去掉添加ck列引入的','
		}
		dgBuilder.append("]],");
		//==== build frozen column end
		
		//==== build column
		dgBuilder.append("columns:[[");
		buildColumns(dgBuilder, 1);
		dgBuilder.append("]],");
		//==== build column end
		
		/* ************************************数据加载完成时****************************************** */
		dgBuilder.append("onLoadSuccess:function(data){$(\"#" + name + "\")." + gridType + "(\"clearSelections\");");
		if (openFirstNode && isTree) {
			dgBuilder.append(" if(data==null){");
			dgBuilder.append(" var firstNode = $(\'#" + name + "\').treegrid('getRoots')[0];");
			dgBuilder.append(" $(\'#" + name + "\').treegrid('expand',firstNode.id)}");
		}
		if (StringUtils.isNotEmpty(onLoadSuccess)) {
			dgBuilder.append(onLoadSuccess + "(data);");
		}
		dgBuilder.append("},");
		/* ***************************************************************************** */

		/* ***************************** 双击行时  ************************************** */
		if (StringUtils.isNotEmpty(onDblClick)) {
			dgBuilder.append("onDblClickRow:function(rowIndex,rowData){" + onDblClick + "(rowIndex,rowData);},");
		}
		/* ***************************************************************************** */

		/* ***************************** 单击行时  *************************************** */
		if (isTree) {
			dgBuilder.append("onClickRow:function(rowData){");
		} else {
			dgBuilder.append("onClickRow:function(rowIndex,rowData){");
		}
		/** 行记录赋值 */
		dgBuilder.append("rowid=rowData.id;");
		dgBuilder.append("gridname=\'" + name + "\';");
		if (StringUtils.isNotEmpty(onClick)) {
			if (isTree) {
				dgBuilder.append("" + onClick + "(rowData);");
			} else {
				dgBuilder.append("" + onClick + "(rowIndex,rowData);");
			}
		}
		dgBuilder.append("}");
		/* ****************************************************************************** */
		dgBuilder.append("});"); // grid 完成
		this.setPager(dgBuilder, gridType);
		dgBuilder.append("});"); // $(function(){})完成

//		// reloadTable, gridname
//		dgBuilder.append("function reloadTable(){");
//		dgBuilder.append("  try{$(\'#\'+gridname).datagrid(\'reload\');}catch(ex){}");
//		dgBuilder.append("  try{$(\'#\'+gridname).treegrid(\'reload\');}catch(ex){}");
//		dgBuilder.append("}");
		
//		//getSelectRows
//		dgBuilder.append("function getSelectRows(){");
//		dgBuilder.append("  var rows;");
//		dgBuilder.append("  try{rows=$(\'#\'+gridname).datagrid(\'getChecked\');}catch(ex){}");
//		dgBuilder.append("  try{rows=$(\'#\'+gridname).treegrid(\'getChecked\');}catch(ex){}");
//		dgBuilder.append("  return rows;");
//		dgBuilder.append("}");
//		
//		//getSelected
//		dgBuilder.append("function getSelected(field){");
//		dgBuilder.append("  var value='';var row;");
//		dgBuilder.append("  try{");
//		dgBuilder.append("	   row=$(\'#\'+gridname).datagrid(\'getSelected\');");
//		dgBuilder.append("	   row=$(\'#\'+gridname).treegrid(\'getSelected\');");
//		dgBuilder.append("  }catch(ex){}");
//		dgBuilder.append("  if(row!=null){value=row[field];}");
//		dgBuilder.append("  return value;");
//		dgBuilder.append("}");

		// reload{GridName}
		dgBuilder.append("function reload" + name + "(){");
		dgBuilder.append("  $(\'#" + name + "\')." + gridType + "(\'reload\');");
		dgBuilder.append("}");
		
		// get{name}Selected
		dgBuilder.append("function get" + name + "Selected(field){");
		dgBuilder.append("  var value='';");
		dgBuilder.append("  var row=$('#" + name + "')." + gridType + "('getSelected');");
		dgBuilder.append("  if(row!=null){value= row[field];}");
		dgBuilder.append("  return value;");
		dgBuilder.append("}");
		
		// get{name}Selections
		dgBuilder.append("function get" + name + "Selections(field){");
		dgBuilder.append("  var values = [];");
		dgBuilder.append("  var rows = $(\'#" + name + "\')." + gridType + "(\'getSelections\');");
		dgBuilder.append("  for(var i=0;i<rows.length;i++){");
		dgBuilder.append("    values.push(rows[i][field]);");
		dgBuilder.append("  }");
		dgBuilder.append("  values.join(\',\');");
		dgBuilder.append("  return values;");
		dgBuilder.append("}");
		
		if (allColumns.size() > 0) {
			// {name}search
			dgBuilder.append("function " + name + "search(){");
			dgBuilder.append("  var queryParams=$(\'#" + name + "\')." + gridType + "('options').queryParams;");
			dgBuilder.append("  $(\'#" + name + "tb\').find(':input').each(function(){");
			dgBuilder.append("    queryParams[$(this).attr('name')]=$(this).val();");
			dgBuilder.append("  });");
			dgBuilder.append("  $(\'#" + name + "\')." + gridType + "({url:'" + actionUrl + "&field=" + fields + "',pageNumber:1});");
			dgBuilder.append("}");

			// dosearch
			dgBuilder.append("function "+name+"dosearch(params){");
			dgBuilder.append("  var jsonparams=$.parseJSON(params);");
			dgBuilder.append("  $(\'#" + name + "\')." + gridType + "({url:'" + actionUrl + "&field=" + fields +  "',queryParams:jsonparams});");
			dgBuilder.append("}");

			if (toolBarList.size() > 0) {
				// searchbox框执行方法
				dgBuilder.append("function " + name + "searchbox(value,name){");
				dgBuilder.append("  var queryParams=$(\'#" + name + "\').datagrid('options').queryParams;");
				dgBuilder.append("  queryParams[name]=value;queryParams.searchfield=name;");
				dgBuilder.append("  $(\'#" + name + "\')." + gridType + "(\'reload\');}");
				dgBuilder.append("  $(\'#" + name + "searchbox\').searchbox({");
				dgBuilder.append("    searcher:function(value,name){");
				dgBuilder.append(     	name + "searchbox(value,name);");
				dgBuilder.append("    },");
				dgBuilder.append("  menu:\'#" + name + "mm\',");
				dgBuilder.append("  prompt:\'请输入查询关键字\'");
				dgBuilder.append("});");
			}
			
			// {name}searchReset
			dgBuilder.append("function " + name + "searchReset(){");
			dgBuilder.append("  $(\'#" + name + "tb\').find(\':input\').val(\'\');");
			dgBuilder.append(   name + "search();");
			dgBuilder.append("}");
		}
		dgBuilder.append("</script>");

		dgBuilder.append("<table width=\"100%\" id=\"" + name + "\" toolbar=\"#" + name + "tb\"></table>");
		dgBuilder.append("<div id=\"" + name + "tb\" style=\"padding:3px;height:auto\">");
		if (hasQueryColum(allColumns)) {
			dgBuilder.append("<div name=\"searchColums\">");
			// 如果表单是组合查询
			if ("group".equals(getQueryMode())) {
				for (DataGridColumn col : allColumns) {
					if (col.isQuery()) {
						dgBuilder.append("<span style=\"display:-moz-inline-box;display:inline-block;\">");
						dgBuilder.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;"
								+ "-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap;\" title=\""
								+ col.getTitle() + "\">" + col.getTitle() + "：</span>");
						if ("single".equals(col.getQueryMode())) {
							if (StringUtils.isNotEmpty(col.getReplace())) {
								dgBuilder.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
								dgBuilder.append("<option value =\"\" >---请选择---</option>");
								String[] test = col.getReplace().split(",");
								String text = "";
								String value = "";
								for (String string : test) {
									text = string.split("_")[0];
									value = string.split("_")[1];
									dgBuilder.append("<option value =\"" + value + "\">" + text + "</option>");
								}
								dgBuilder.append("</select>");
							} else if (StringUtils.isNotEmpty(col.getDictionary())) {
								if (col.getDictionary().contains(",")) {
									String[] dic = col.getDictionary().split(",");
									String sql = "select " + dic[1] + " as field," + dic[2] + " as text from " + dic[0];
									CommonService commonService = SystemUtils.getCommonService();
									List<Map<String, Object>> list = commonService.findSetBySql(sql);
									dgBuilder.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
									dgBuilder.append("<option value =\"\" >---请选择---</option>");
									for (Map<String, Object> map : list) {
										dgBuilder.append(" <option value=\"" + map.get("field") + "\">");
										dgBuilder.append(map.get("text"));
										dgBuilder.append(" </option>");
									}
									dgBuilder.append("</select>");
								} else {
									Map<String, List<DataDictionaryItem>> typedatas = DataDictionary.getAllDictionaryItems();
									List<DataDictionaryItem> types = typedatas.get(col.getDictionary().toLowerCase());
									dgBuilder.append("<select name=\"" + col.getField().replaceAll("_", "\\.")
											+ "\" WIDTH=\"100\" style=\"width: 104px\"> ");
									dgBuilder.append("<option value =\"\" >---请选择---</option>");
									for (DataDictionaryItem type : types) {
										dgBuilder.append(" <option value=\"" + type.getCode() + "\">");
										dgBuilder.append(type.getName());
										dgBuilder.append(" </option>");
									}
									dgBuilder.append("</select>");
								}
							} else if (col.isAutocomplete()) {
								dgBuilder.append(getAutoSpan(col.getField().replaceAll("_", "\\."), extendAttribute(col.getExtend())));
							} else {
								dgBuilder.append("<input type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\"  "
										+ extendAttribute(col.getExtend()) + " style=\"width: 100px\" />");
							}
						} else if ("scope".equals(col.getQueryMode())) {
							dgBuilder.append("<input type=\"text\" name=\"" + col.getField() + "_begin\"  style=\"width: 94px\" "
									+ extendAttribute(col.getExtend()) + "/>");
							dgBuilder.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
							dgBuilder.append("<input type=\"text\" name=\"" + col.getField() + "_end\"  style=\"width: 94px\" "
									+ extendAttribute(col.getExtend()) + "/>");
						}
						dgBuilder.append("</span>");
					}
				}
			}
			dgBuilder.append("</div>");
		}
		if (toolBarList.size() == 0 && !hasQueryColum(allColumns)) {
			dgBuilder.append("<div style=\"height:0px;\" >");
		} else {
			dgBuilder.append("<div style=\"height:30px;\" class=\"datagrid-toolbar\">");
		}
		dgBuilder.append("<span style=\"float:left;\" >");
		if (toolBarList.size() > 0) {
			for (DataGridOperation toolBar : toolBarList) {
				dgBuilder.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + toolBar.getIcon() + "\" ");
				if (StringUtils.isNotEmpty(toolBar.getOnclick())) {
					dgBuilder.append("onclick=" + toolBar.getOnclick() + "");
				} else {
					dgBuilder.append("onclick=\"" + toolBar.getFunname() + "(");
					if (!toolBar.getFunname().equals("doSubmit")) {
						dgBuilder.append("\'" + toolBar.getTitle() + "\',");
					}
					String width = toolBar.getWidth().contains("%") ? "'" + toolBar.getWidth() + "'" : toolBar.getWidth();
					String height = toolBar.getHeight().contains("%") ? "'" + toolBar.getHeight() + "'" : toolBar.getHeight();
					dgBuilder.append("\'" + toolBar.getUrl() + "\',\'" + name + "\'," + width + "," + height + ")\"");
				}
				dgBuilder.append(">" + toolBar.getTitle() + "</a>");
			}
		}
		dgBuilder.append("</span>");
		if ("group".equals(getQueryMode()) && hasQueryColum(allColumns)) {// 如果表单是组合查询
			dgBuilder.append("<span style=\"float:right\">");
			dgBuilder.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"" + name + "search()\">查询</a>");
			dgBuilder.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-reload\" onclick=\"" + name + "searchReset()\">重置</a>");
			dgBuilder.append("</span>");
		} else if ("single".equals(getQueryMode()) && hasQueryColum(allColumns)) {// 如果表单是单查询
			dgBuilder.append("<span style=\"float:right\">");
			dgBuilder.append("<input id=\"" + name + "searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:" + name
					+ "searchbox,prompt:\'请输入关键字\',menu:\'#" + name + "mm\'\"></input>");
			dgBuilder.append("<div id=\"" + name + "mm\" style=\"width:120px\">");
			for (DataGridColumn col : allColumns) {
				if (col.isQuery()) {
					dgBuilder.append("<div data-options=\"name:\'" + col.getField().replaceAll("_", "\\.") + "\',iconCls:\'icon-ok\' "
							+ extendAttribute(col.getExtend()) + " \">" + col.getTitle() + "</div>  ");
				}
			}
			dgBuilder.append("</div>");
			dgBuilder.append("</span>");
		}
		dgBuilder.append("</div>");
		return dgBuilder;
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
	public void addColumn(DataGridColumn dgColumn) {
		final String field = dgColumn.getField();
		if (!StringUtils.equals("opt", field)) {
			fields += field + ",";
			if (StringUtils.equals("scope", dgColumn.getQueryMode())) {
				searchFields += field + "," + field + "_begin," + field + "_end,";
			} else {
				searchFields += field + ",";
			}
		}

		// 将value替换显示成text 格式为(text_value,text1_value1)
		if (StringUtils.isNotEmpty(dgColumn.getReplace())) {
			String[] rpls = dgColumn.getReplace().split(",");
			String text = "";
			String value = "";
			for (String rpl : rpls) {
				int pos = rpl.indexOf("_");
				text += rpl.substring(0, pos) + ",";
				value += rpl.substring(pos + 1) + ",";
			}
			setColumn(field, text, value);
		}

		//格式1:tablename,value字段,text字段----格式2:字典名
		if (StringUtils.isNotEmpty(dgColumn.getDictionary())) {
			String dictionary = dgColumn.getDictionary();
			if (dictionary.contains(",")) {
				String[] dic = dictionary.split(",");
				String text = "";
				String value = "";
				String sql = "select distinct " + dic[1] + " as value," + dic[2] + " as text from " + dic[0];
				CommonService commonService = SystemUtils.getCommonService();
				List<Map<String, Object>> list = commonService.findSetBySql(sql);
				for (Map<String, Object> map : list) {
					text += map.get("text") + ",";
					value += map.get("value") + ",";
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

		// 列显示设置
		if (StringUtils.isNotEmpty(dgColumn.getStyle())) {
			String[] temp = dgColumn.getStyle().split(",");
			String text = "";
			String value = "";
			if (temp.length == 1 && temp[0].indexOf("_") == -1) { //一种颜色
				text = temp[0];
			} else {
				for (String string : temp) { //多种颜色，格式red_0,blue_1
					text += string.substring(0, string.indexOf("_")) + ",";
					value += string.substring(string.indexOf("_") + 1) + ",";
				}
			}
			setStyleColumn(field, text, value);
		}

		allColumns.add(dgColumn);
	}

	/**
	 * 设置 颜色替换值
	 */
	private void setStyleColumn(String field, String text, String value) {
		ValueMapper columnValue = new ValueMapper();
		columnValue.setName(field);
		columnValue.setText(text);
		columnValue.setValue(value);
		colStyleMapper.add(columnValue);
	}

	/**
	 * 将value替换显示成text
	 */
	public void setColumn(String name, String text, String value) {
		ValueMapper columnValue = new ValueMapper();
		columnValue.setName(name);
		columnValue.setText(text);
		columnValue.setValue(value);
		colValueMapper.add(columnValue);
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
			Iterator<String> it = obj.keySet().iterator();
			while (it.hasNext()) {
				String key = String.valueOf(it.next());
				JSONObject nextObj = ((JSONObject) obj.get(key));
				Iterator<String> itvalue = nextObj.keySet().iterator();
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
		Iterator<String> it = syscode.keySet().iterator();
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
		return this.searchFields.length() > 0; 
//		boolean hasQuery = false;
//		for (DataGridColumn col : columnList) {
//			if (col.isQuery()) {
//				hasQuery = true;
//			}
//		}
//		return hasQuery;
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
		String param = "";
		if (index != -1) {
			String testparam = functionname.substring(functionname.indexOf("(") + 1, functionname.length() - 1);
			if (StringUtils.isNotEmpty(testparam)) {
				String[] params = testparam.split(",");
				for (String string : params) {
					if (string.indexOf("{") != -1) {
						param += "'\"+rec." + string.substring(1, string.length() - 1) + "+\"',";
					} else {
						param += "'\"+" + string + "+\"',";
					}
				}
			}
		}
		param += "'\"+index+\"'";// 传出行索引号参数
		return param;
	}

	/**
	 * 拼装操作项
	 */
	protected void buildOperationColumn(StringBuilder sb) {
		// 防止操作列在 合计行 中生成操作项（合计行没有id项）
		sb.append("if(!rec." + idField + "){return '';}");
		List<DataGridOperation> list = operationList;
		sb.append("var href='';");
		for (DataGridOperation dgOperation : list) {
			String url = dgOperation.getUrl();
			if (url != null) {
				url = formatUrl(url);
			}
			// "field#[in,out]#value1,value2,value3&&field1#[empty]#true"
			String exp = dgOperation.getExp(); // 判断显示表达式
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
				sb.append("href+=\"[<a href=\'#\' onclick=confirm(\'" + url + "\',\'" + dgOperation.getMessage() + "\',\'" + name
						+ "\')> \";");
			}
			if (DataGridOperationType.Del.equals(dgOperation.getType())) {
				sb.append("href+=\"[<a href=\'#\' onclick=remove(\'" + url + "\',\'" + name + "\')>\";");
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

			if (StringUtils.isNotEmpty(exp)) {
				for (int i = 0; i < exp.split("&&").length; i++) {
					sb.append("}");
				}
			}
		}
		sb.append("return href;");
	}

	/**
	 * 替换url中携带的参数 
	 * 对于/context/.../res?id={id},name={name}这样一条url，格式化后{id}和{name}会用对应行的真实值替换
	 */
	protected String formatUrl(String url) {
		if (url.indexOf("?") != -1) {
			String beforeurl = url.substring(0, url.indexOf("?"));// 截取请求地址
			String parurl = url.substring(url.indexOf("?") + 1, url.length());// 截取参数
			String[] params = parurl.split("&");
			List<String> value = new ArrayList<String>();
			
			StringBuilder pBuilder = new StringBuilder();
			pBuilder.append("?");
			int j = 0;
			for (int i = 0; i < params.length; i++) {
				if (params[i].indexOf("{") != -1) {
					String field = params[i].substring(params[i].indexOf("{") + 1, params[i].lastIndexOf("}"));
					pBuilder.append(params[i].replace("{" + field + "}", "{" + j + "}")).append("&");
					value.add("\"+rec." + field + " +\"");
					j++;
				} else {
					pBuilder.append(params[i]).append("&");
				}
			}
			parurl = pBuilder.delete(pBuilder.length() - 1, pBuilder.length()).toString();
			url = MessageFormat.format(beforeurl+parurl, value.toArray());
		}

		return url;
	}

	/**
	 * 拼接字段 普通列
	 */
	protected void getField(StringBuilder sb) {
		buildColumns(sb, 1);
	}

	/**
	 * 拼接字段
	 * 
	 * @frozen 0 冰冻列 1 普通列
	 */
	protected void buildColumns(StringBuilder sb, int frozen) {
		int i = 0;
		for (DataGridColumn column : allColumns) {
			i++;
			if ((column.isFrozen() && frozen == 0) || (!column.isFrozen() && frozen == 1)) {
				String field = column.getField();
				sb.append("{field:\'" + field + "\',title:\'" + column.getTitle() + "\'");
				if (column.getWidth() != null) {
					sb.append(",width:" + column.getWidth());
				}
				if (StringUtils.isNotEmpty(column.getAlign())) {
					sb.append(",align:\'" + column.getAlign() + "\'");
				}
				if (StringUtils.isNotEmpty(column.getExtendParams())) {
					sb.append("," + column.getExtendParams());
				}
				// 隐藏字段
				if (column.isHidden()) {
					sb.append(",hidden:true");
				}
				// 字段排序
				if (column.isSortable() && (field.indexOf("_") <= 0 && field != "opt") && !isTree) {
					sb.append(",sortable:" + column.isSortable());
				}
				// 显示图片
				if (column.isImage()) {
					sb.append(",formatter:function(value,rec,index){");
					sb.append("    return '<img border=\"0\" src=\"'+value+'\"/>';");
					sb.append(" }");
				}
				// 自定义显示图片
				if (column.getImageSize() != null) {
					String[] tld = column.getImageSize().split(",");
					sb.append(",formatter:function(value,rec,index){");
					sb.append(" 	return '<img width=\"" + tld[0] + "\" height=\"" + tld[1] + "\" border=\"0\" src=\"'+value+'\"/>';");
					sb.append(" }");
					tld = null;
				}
				if (column.getDownloadName() != null) {
					sb.append(",formatter:function(value,rec,index){");
					sb.append(" return '<a target=\"_blank\" href=\"'+value+'\">" + column.getDownloadName() + "</a>';}");
				}
				// 自定义链接
				if (column.getUrl() != null) {
					sb.append(",formatter:function(value,rec,index){");
					sb.append("		var href=\"<a style=\'color:red\' href=\'#\' onclick=" + column.getFunname() + "('" + column.getTitle() + "','" + formatUrl(column.getUrl()) + "')>\";");
					sb.append("     return href+value+\'</a>\';");
					sb.append(" }");
				}
				// 日期格式化
				if (column.getDateFormatter() != null) {
					sb.append(",formatter:function(value,rec,index){");
					sb.append(" 	return new Date().format('" + column.getDateFormatter() + "', value);");
					sb.append(" }");
				}
				// 加入操作
				if (column.getField().equals("opt")) {
					sb.append(",formatter:function(value,rec,index){");
					buildOperationColumn(sb);
					sb.append("}");
				}
				// 值替換
				if (colValueMapper.size() > 0 && !StringUtils.equals(column.getField(), "opt")) {
					String testString = "";
					for (ValueMapper columnValue : colValueMapper) {
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
				if (colStyleMapper.size() > 0 && !column.getField().equals("opt")) {
					String testString = "";
					for (ValueMapper columnValue : colStyleMapper) {
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
				if (i < allColumns.size()) {
					sb.append(",");
				}
			}
		}
	}

	/**
	 * 设置分页条信息
	 */
	protected void setPager(StringBuilder sb, String grid) {
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

	// json转换中的系统保留字
	protected static Map<String, String> syscode = new HashMap<String, String>();
	static {
		syscode.put("class", "clazz");
	}

	/**
	 * 组装菜单按钮操作权限 dateGridUrl：url operationCode：操作码 optList： 操作列表
	 */
	@SuppressWarnings("unchecked")
	private void installOperation(DataGridOperation dataGridUrl, String operationCode, @SuppressWarnings("rawtypes") List optList) {
		optList.add(dataGridUrl);
	}

	/**
	 * 获取自动补全的panel
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

	public void setIsTree(boolean treegrid) {
		this.isTree = treegrid;
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
