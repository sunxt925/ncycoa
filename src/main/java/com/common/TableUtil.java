package com.common;

import com.db.DataRow;
import com.db.DataTable;
import com.entity.std.DocMetaVersionInfo;
import com.entity.std.DocStoreFile;

import java.util.*;




public class TableUtil
{
	public TableUtil()
	{

	}

	public String DisplayCol = "序号,";// 设置哪些列不显示，逗号隔开，行号默认是不显示的是否打印行号
	public DataTable dt = null;// 用于打印的DataTable
	public String TableWidth = "100%";// 表格宽度
	public String tablestyle="";
	public String TableBorder = "1";// 表格边框框度
	public String TableCellpadding = "5";
	public String TableCellspacing = "0";
	public String TableClass = "table_list";// 表格适用的CSS
	public String HeadHeight = "22";// 表头的行高度
	public String HeadDefAlign = "nowrap align='center'";// 表头的对齐方式
	public String RowDefAlign = "nowrap align='center'";// 内容的对齐方式
	public String HeadStyle = "onMouseOver=\"this.style.backgroundColor='#D0E9ED'\" onMouseOut=\"this.style.backgroundColor=''\"";
	public String LineAction = "";// 按行的其它事件,比如onclick等，通过@xxx@进行输入结果的替换（输出字段xxx的值）
	public String CheckBoxName = "";
	public String CheckBoxValue = "";
	public String checkBoxState = "";
	public String checkBoxState_value="";
	public Map RowWidth=new HashMap();
	public Map HeadAlign = new HashMap();
	public Map HeadWidth = new HashMap();
	public Map ColumnAlign = new HashMap();
	public Map RowColor = new HashMap();
	public Map RowLink = new HashMap();
	public Map RowValue = new HashMap();
	public Map RowCode = new HashMap();// 需要进行翻译的列
    public Map RowreadLink=new HashMap();//阅读
    public Map RowStateOp=new HashMap();//根据条件改变操作
    public Map HaveAttach=new HashMap();//阅读
    public String sort1="";
    public String sortlink1="";
    public String sort2="";
    public String sortlink2="";
	public String getSortlink1() {
		return sortlink1;
	}
	public void setSortlink1(String sortlink1) {
		this.sortlink1 = sortlink1;
	}
	public String getSortlink2() {
		return sortlink2;
	}
	public void setSortlink2(String sortlink2) {
		this.sortlink2 = sortlink2;
	}
	public String getSort1() {
		return sort1;
	}
	public void setSort1(String sort1) {
		this.sort1 = sort1;
	}
	public String getSort2() {
		return sort2;
	}
	public void setSort2(String sort2) {
		this.sort2 = sort2;
	}
	// public Map RowCheckBoxDisabled=new HashMap();//根据字段值设置checkbox不可用
	public void setRowreadLink(String colname, String codename) {
		RowreadLink .put(colname, codename);
	}
	public void setHaveAttach(String colname, String codename) {
		HaveAttach.put(colname, codename);
	}
	public void setRowCode(String colname, String codename)
	{
		RowCode.put(colname, codename);
	}

	public void setHeadAlign(String colname, String align)
	{
		HeadAlign.put(colname, align);
	}

	public void setHeadWidth(String colname, String width)
	{
		HeadWidth.put(colname, width);
	}
	public void setRowWidth(String colname, String width)
	{
		RowWidth.put(colname, width);
	}
	public void setColumnAlign(String colname, String align)
	{
		ColumnAlign.put(colname, align);
	}

	public void setRowColor(String colname, String color)
	{
		RowColor.put(colname, color);
	}

	public void setRowLink(String colname, String link)
	{
		RowLink.put(colname, link);
	}

	
	public void setRowValue(String colname, String value)
	{
		RowValue.put(colname, value);
	}
    
    public void setRowStateOp(String colname, String value) {
		RowStateOp.put(colname, value);
	}
    
	public String DrawTable()
	{
		String res = "";
		// DisplayCol = DisplayCol + ",";
		try
		{
			res = res + "<table width='" + TableWidth + "' style='"+tablestyle+"'  border='"
					+ this.TableBorder + "' cellpadding='"
					+ this.TableCellpadding + "' cellspacing='"
					+ this.TableCellspacing + "' class='" + this.TableClass
					+ "'>";
			// 开始打印表头
			res = res + "<tr height='" + this.HeadHeight
					+ "' bgcolor='D0E9ED' style=\"border-color: #ece9d8\">";
			for (int k = 0; k < dt.getColumnsCount(); k++)
			{
				if (DisplayCol.indexOf(dt.getColumnNames()[k] + ",") == -1)
				{
					String colwidth = "";
					String headalign = "align='center'";
					if (HeadWidth.get(dt.getColumnNames()[k]) != null)
					{
						colwidth = "width='"
								+ HeadWidth.get(dt.getColumnNames()[k])
										.toString() + "'";
					}
					 //System.out.println(dt.getColumnNames()[k]+"=="+HeadAlign.get(dt.getColumnNames()[k]));
					if (HeadAlign.get(dt.getColumnNames()[k]) != null)
					{
						headalign = "align='"
								+ HeadAlign.get(dt.getColumnNames()[k])
										.toString() + "'";
					}
					String columnname = dt.getColumnNames()[k];
					if(columnname.equals(sort1)){
						columnname=sortlink1;
					}
					if(columnname.equals(sort2)){
						columnname=sortlink2;
					}
					if (columnname.equals(CheckBoxName))
					{
						columnname = "<input type='checkbox' name='allitems' id='allitems' onclick='allitems_click()'>";
					}
					res = res + "<td nowrap " + colwidth + " " + headalign
							+ " style=\"border-color: #ece9d8;font-size:12px;\">" + columnname + "</td>";
				}
			}
			res = res + "</tr>";
			if (dt != null && dt.getRowsCount() >= 0)
			{
				// 开始打印内容
				String[] LineAction_rep = StringUtil
						.getRepPara(this.LineAction);
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					String LineAction_current = this.LineAction;
					DataRow r = dt.get(i);
					if (LineAction_rep != null)
					{
						for (int a = 0; a < LineAction_rep.length; a++)
						{
							LineAction_current = LineAction_current.replaceAll(
									"@" + LineAction_rep[a] + "@", r
											.getString(LineAction_rep[a]
													.toString()));
						}
					}
					res = res + "<tr height=\"" + this.HeadHeight + "\" "
							+ this.HeadStyle + " " + LineAction_current + " style=\"border-color: #ece9d8\">";
					
					
					String disable_colum="";
					if(!checkBoxState.equals("")){
						disable_colum=dt.get(i).getString(checkBoxState);
					}
					
					
					for (int j = 0; j < dt.getColumnsCount(); j++)
					{
						if (DisplayCol.indexOf(dt.getColumnNames()[j] + ",") == -1)
						{
							// 该列的对齐
							String col_align = "align='center'";
							if (ColumnAlign.get(dt.getColumnNames()[j]) != null)
							{
								col_align = "align='"
										+ ColumnAlign.get(
												dt.getColumnNames()[j])
												.toString() + "'";
							}
							Object o_col_value = r.get(j);
							String col_value = "";
							if (o_col_value != null)
							{
								col_value = o_col_value.toString();
							}
							// 值替换
							if (RowValue.get(dt.getColumnNames()[j]) != null)
							{
								String col_value_rep = RowValue.get(
										dt.getColumnNames()[j]).toString();
								// 变量替换
								String[] colvalue_rep = StringUtil
										.getRepPara(col_value_rep);
								if (colvalue_rep != null)
								{
									for (int a = 0; a < colvalue_rep.length; a++)
									{
										col_value_rep = col_value_rep
												.replaceAll(
														"@" + colvalue_rep[a]
																+ "@",
														r
																.getString(colvalue_rep[a]
																		.toString()));
									}
								}
								col_value = col_value_rep;
							}
							//文档阅读设置
							if (RowreadLink.get(dt.getColumnNames()[j]) != null)
							{
								DocStoreFile docStoreFile= new DocStoreFile();
								String ss=docStoreFile.TranslateLink(col_value);
								col_value=ss;
							
							}
							//有无附件
							if(HaveAttach.get(dt.getColumnNames()[j]) != null)
							{
								DocMetaVersionInfo docmetaversioninfo=new DocMetaVersionInfo();
								DataTable attachDataTable=docmetaversioninfo.HaveAttach(col_value);
								int num=attachDataTable.getRowsCount();
								if(num>0)
									col_value="有/"+num;
								else
									col_value="无";
								
							}
							
							// 编码翻译
							if (RowCode.get(dt.getColumnNames()[j]) != null)
							{
								String[] ss=RowCode.get(dt.getColumnNames()[j]).toString().split(",");
								CodeDictionary co = new CodeDictionary();
								//翻译公共编码
								if(ss.length==2){
									col_value=co.code_traslate(ss[1],col_value);
								}
								//翻译独立编码
								if(ss.length==4){
									col_value=co.syscode_traslate(ss[1], ss[2],ss[3],col_value);
								}
							
							}
							// 操作方式
							if (RowStateOp.get(dt.getColumnNames()[j]) != null)
							{
								 String[] s=RowStateOp.get(dt.getColumnNames()[j]).toString().split(",");
								 int n=Integer.parseInt(s[0]);
								
								 Object oo=r.get(n);
								 String old=oo.toString();
								 
								 if(old.equals(s[1])){
									 col_value="<a id=\"btn_save\" href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add',plain:true\">临时汇总</a><a id=\"btn_save\" href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add',plain:true\">最后汇总</a>";
								 }
								 else if(old.equals(s[2])){
									 col_value="<a id=\"btn_save\" href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add',plain:true\">临时汇总</a><a id=\"btn_save\" href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add',plain:true\">最后汇总</a>";
								 }
								 else{
									 col_value="<a id=\"btn_save\" href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add',plain:true\">重新汇总</a>";
								 }
								
							
							}
							// 颜色判断
							if (RowColor.get(dt.getColumnNames()[j]) != null)
							{
								col_value = "<font color='"
										+ RowColor.get(dt.getColumnNames()[j])
												.toString() + "'>" + col_value
										+ "</font>";
							/*	col_value ="<span style='color:"+RowColor.get(dt.getColumnNames()[j])
										    .toString()+";font-size:12px'>"+col_value+"</span>";*/
							}
							// 链接判断
							if (RowLink.get(dt.getColumnNames()[j]) != null)
							{
								String link_url = RowLink.get(
										dt.getColumnNames()[j]).toString();
								// 变量替换
								String[] link_url_rep = StringUtil
										.getRepPara(link_url);
								if (link_url_rep != null)
								{
									for (int a = 0; a < link_url_rep.length; a++)
									{
										link_url = link_url.replaceAll("@"
												+ link_url_rep[a] + "@", r
												.getString(link_url_rep[a]
														.toString()));
									}
								}
								col_value = "<a href='" + link_url + "'>"
										+ col_value + "</a>";
							}
							// 判断是否是复选框
							if (dt.getColumnNames()[j].equals(CheckBoxName))
							{
								String[] checkv=CheckBoxValue.split(",");
								StringBuilder checkvalues=new StringBuilder();
								checkvalues.append("");
								for(int checkn=0;checkn<checkv.length-1;checkn++){
									checkvalues.append(r.getString(checkv[checkn])+",");
								}
								checkvalues.append(r.getString(checkv[checkv.length-1]));
								if(!checkBoxState.equals("")&&!checkBoxState_value.equals(""))
								{
									
									if(disable_colum.equals(checkBoxState_value))
									{
										
										res = res
										+ "<td nowrap "
										+ col_align
										+ " style=\"border-color: #ece9d8\"><input type=\"checkbox\"  disabled  id=\"items\" name=\"items\" value=\""
										
										//+ r.getString(this.CheckBoxValue)
										+checkvalues.toString()
										+ "\"></td>";
									}else {
										res = res
										+ "<td nowrap "
										+ col_align
										+ " style=\"border-color: #ece9d8\"><input type=\"checkbox\"    id=\"items\" name=\"items\" value=\""
										//+ r.getString(this.CheckBoxValue)
										+checkvalues.toString()
										+ "\"></td>";
										
									}
								}else {
									res = res
									+ "<td nowrap "
									+ col_align
									+ " style=\"border-color: #ece9d8\"><input type=\"checkbox\"    id=\"items\" name=\"items\" value=\""
									//+ r.getString(this.CheckBoxValue)
									+checkvalues.toString()
									+ "\"></td>";
									
								}
								
								
								
								
								
								
							}
							else
							{
								
								
								if(RowWidth.get(dt.getColumnNames()[j]) != null){
									
									res = res + "<td width='"+RowWidth.get(dt.getColumnNames()[j]).toString()+";' style=\"border-color: #ece9d8\">"
									+ "<div style='width:"+RowWidth.get(dt.getColumnNames()[j]).toString()+";overflow:hidden;white-space:nowrap;text-overflow:ellipsis;-o-text-overflow:ellipsis;-icab-text-overflow: ellipsis;-khtml-text-overflow: ellipsis;-moz-text-overflow: ellipsis;-webkit-text-overflow: ellipsis;'>"+"<a title='"+col_value+"'>"+ "<span style=\"font-size:12px\">"+col_value+"</span>"+"</a>"+"</div>" + "</td>";
									
									
									
								}else{
									res = res + "<td nowrap " + col_align + " style=\"border-color: #ece9d8\">"
									+ "<span style=\"font-size:12px\">"+col_value +"</span>"+ "</td>";
								}
								
							}
						}
					}
					res = res + "</tr>";
				}
			}
			res = res + "</table>";
			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public String getDisplayCol()
	{
		return DisplayCol;
	}

	public void setDisplayCol(String displayCol)
	{
		if (displayCol.endsWith(","))
		{
			DisplayCol = displayCol;
		}
		else
		{
			DisplayCol = displayCol + ",";
		}
	}

	public DataTable getDt()
	{
		return dt;
	}

	public void setDt(DataTable dt)
	{
		this.dt = dt;
	}

	public String getTableWidth()
	{
		return TableWidth;
	}

	public void setTableWidth(String tableWidth)
	{
		TableWidth = tableWidth;
	}

	public String getTableBorder()
	{
		return TableBorder;
	}

	public void setTableBorder(String tableBorder)
	{
		TableBorder = tableBorder;
	}

	public String getTableCellpadding()
	{
		return TableCellpadding;
	}

	public void setTableCellpadding(String tableCellpadding)
	{
		TableCellpadding = tableCellpadding;
	}

	public String getTableCellspacing()
	{
		return TableCellspacing;
	}

	public void setTableCellspacing(String tableCellspacing)
	{
		TableCellspacing = tableCellspacing;
	}

	public String getTableClass()
	{
		return TableClass;
	}

	public void setTableClass(String tableClass)
	{
		TableClass = tableClass;
	}

	public String getHeadHeight()
	{
		return HeadHeight;
	}

	public void setHeadHeight(String headHeight)
	{
		HeadHeight = headHeight;
	}

	public String getHeadDefAlign()
	{
		return HeadDefAlign;
	}

	public void setHeadDefAlign(String headDefAlign)
	{
		HeadDefAlign = headDefAlign;
	}

	public String getRowDefAlign()
	{
		return RowDefAlign;
	}

	public void setRowDefAlign(String rowDefAlign)
	{
		RowDefAlign = rowDefAlign;
	}

	public String getCheckBoxName()
	{
		return CheckBoxName;
	}

	public void setCheckBoxName(String checkBoxName)
	{
		CheckBoxName = checkBoxName;
	}

	public String getCheckBoxValue()
	{
		return CheckBoxValue;
	}

	public void setCheckBoxValue(String checkBoxValue)
	{
		CheckBoxValue = checkBoxValue;
	}

	public String getHeadStyle()
	{
		return HeadStyle;
	}

	public void setHeadStyle(String headStyle)
	{
		HeadStyle = headStyle;
	}

	public String getLineAction()
	{
		return LineAction;
	}

	public void setLineAction(String lineAction)
	{
		LineAction = lineAction;
	}

	public String getCheckBoxState() {
		return checkBoxState;
	}

	public void setCheckBoxState(String checkBoxState) {
		this.checkBoxState = checkBoxState;
	}

	public String getCheckBoxState_value() {
		return checkBoxState_value;
	}

	public void setCheckBoxState_value(String checkBoxStateValue) {
		checkBoxState_value = checkBoxStateValue;
	}

	public void setTablestyle(String tablestyle) {
		this.tablestyle = tablestyle;
	}
	
}
