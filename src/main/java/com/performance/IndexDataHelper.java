package com.performance;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.alibaba.fastjson.JSONObject;
import com.common.CodeDictionary;
import com.common.Format;
import com.db.ConnectionPool;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.index.ChildIndex;
import com.entity.index.Indexitem;
import com.entity.index.ReferObjectIndex;
import com.entity.system.Org;
import com.entity.system.StaffInfo;
import com.performance.dao.IndexDao;
import com.performance.poi.excel.ExcelExportUtil;
import com.performance.poi.excel.entity.ExcelTitle;

public class IndexDataHelper {
	public static String getYearJson() {
		StringBuilder sb = new StringBuilder();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		if (month == 0 || month == 1) {
			sb.append("[{text:'").append(year - 1).append("',value:").append(year - 1).append("}");
			sb.append(",{text:'").append(year).append("',value:").append(year).append(",selected:true").append("}]");
		} else {
			sb.append("[{text:'").append(year).append("',value:").append(year).append(",selected:true").append("}]");
		}

		return sb.toString();
	}
	
	public static String getPeriodJson(){
		Map<Integer, String> maps = new TreeMap<Integer, String>();
		for (int i = 1; i <= 9; i++) {
			maps.put(i - 1, "M0" + i);
		}
		for (int i = 10; i <= 12; i++) {
			maps.put(i - 1, "M" + i);
		}
		
		StringBuilder sb = new StringBuilder();
		Calendar now = Calendar.getInstance();
		int month = now.get(Calendar.MONTH);
		sb.append("[");
		for (Integer tmp : maps.keySet()) {
			sb.append("{value:'").append(maps.get(tmp)).append("',text:'");
			sb.append(ParaDataHelper.code2Name(maps.get(tmp))).append("'");
			if(tmp.equals(month)){
				sb.append(",selected:true");
			}
			sb.append("},");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	public static Map<String, String> getObjName(List<String> objCodes, String objtype){
		Map<String, String> objs = new HashMap<String, String>();
		if ("staff".equals(objtype)) {
			for(String code : objCodes)
				objs.put(code, new StaffInfo(code).getName());
		} else {
			for(String code : objCodes)
				objs.put(code, new Org(code).getName());
		}
		return objs;
	}
	
	public static Map<String, String> getBizParaValue(List<ReviewableObj> objs, String period, String year, String paracode){
		HashMap<String, String> result = new HashMap<String, String>();
		if(objs.size()==0){
			return result;
		}
		try {
			DBObject db = new DBObject();
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			Iterator<ReviewableObj> iter = objs.iterator();
			while(iter.hasNext()){
				sb.append("'").append(iter.next().getCode()).append("',");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append(")");
			String sql = "select * from tbm_referparavalue where referparacode='" + paracode +"' and belongperiod='" + period +"' and belongyear='" + year + "' and objectcode in " + sb.toString();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					result.put(r.getString("objectcode"), r.getString("value"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Map<String, String> getBizParaValue(Set<String> objs, String period, String year, String paracode){
		HashMap<String, String> result = new HashMap<String, String>();
		if(objs.size()==0){
			return result;
		}
		try {
			DBObject db = new DBObject();
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for(String code : objs){
				sb.append("'").append(code).append("',");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append(")");
			String sql = "select * from tbm_referparavalue where referparacode='" + paracode +"' and belongperiod='" + period +"' and belongyear='" + year + "' and objectcode in " + sb.toString();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					result.put(r.getString("objectcode"), r.getString("value"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String periodConverter(String preperiod, String desc){
		int month = Integer.parseInt(preperiod.charAt(0) == '0' ? preperiod.substring(2) : preperiod.substring(1));
		if("D00.H00".equals(desc)){
			return "H0" + ((month - 1) / 6 + 1);
		}else if("D00.Y00".equals(desc)){
			return "Y01";
		}else if("D00.S00".equals(desc)){
			return "S0" + ((month - 1) / 3 + 1);
		}else if("D00.M00".equals(desc)){
			return preperiod;
		}
		
		return preperiod;
	}
	
	public static String getDataGrid(ReviewTask task, Map<String, HashMap<String, String>> data) throws ReviewException{
		if(task == ReviewTask.INVALID_TASK){
			throw new ReviewException("选定的时间内不存在考核对象");
		}
		// 生成指标树
		task.parseIndexTree();
		List<Indexitem> leafIndices = task.getLeafIndices();
		HashMap<String, HashMap<String, List<String>>> inputstr = new HashMap<String, HashMap<String, List<String>>>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tbm_indexscoredetail where objecttype='" + task.getTaskType() +"' and indexcode like '" + 
					task.getIndexArch().getIndexCode() + "%' and scoreyear='" + task.getDate().getYear() + "' and scoreperiod='" + task.getDate().getPeriodCode() +"'";
			conn = ConnectionPool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				HashMap<String, List<String>> tmp = inputstr.get(rs.getString("objectcode"));
				if(tmp == null){
					tmp = new HashMap<String, List<String>>();
					inputstr.put(rs.getString("objectcode"), tmp);
				}
				String inputindexstr = rs.getString("inputindexstr");
				if(inputindexstr == null){
					inputindexstr = "";
				}
				tmp.put(rs.getString("indexcode"), Arrays.asList(inputindexstr.split(",")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, st, conn);
		}
		
		HashMap<String, HashMap<String, HashMap<String, String>>> paravalue = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		HashMap<String, HashMap<String, IndexPara>> paraname = new HashMap<String, HashMap<String, IndexPara>>();
		try {
			String sql = "select * from tbm_indexitempara where indexcode like '" + task.getIndexArch().getIndexCode() + "%'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					IndexPara para = new IndexPara();
					para.setIndexcode(r.getString("indexcode"));
					para.setParacode(r.getString("paracode"));
					para.setParaid(r.getString("paraid"));
					para.setParavaluemode(r.getString("paravaluemode"));
					
					HashMap<String, IndexPara> tmp = paraname.get(para.getIndexcode());
					if(tmp == null){
						tmp = new HashMap<String, IndexPara>();
						paraname.put(r.getString("indexcode"), tmp);
					}
					tmp.put(r.getString("paraid"), para);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 生成数据表列头, frozen列
		StringBuffer sb = new StringBuffer();
		sb.append("<table id=\"index_tb\" class=\"easyui-datagrid\" data-options=\"fit:true,singleSelect:true,onClickRow:onClickRow,toolbar:'#tb'\">");
		sb.append("<thead data-options=\"frozen:true\"><tr>");
		sb.append("<th data-options=\"field:'pindexcode'\">上级指标</th>");
		sb.append("<th data-options=\"field:'indexcode'\">采集指标</th>");
		sb.append("<th data-options=\"field:'indextype'\">指标类别</th>");
		sb.append("<th data-options=\"field:'scorefunc',width:200\">计分方法</th>");
		sb.append("<th data-options=\"field:'para'\">参数</th>");
		sb.append("</tr></thead>");
		// 生成数据表列头, 活动列
		sb.append("<thead><tr>");
		Iterator<ReviewableObj> iter = task.getReviewees().iterator();
		while(iter.hasNext()){
			ReviewableObj obj = iter.next();
			sb.append("<th data-options=\"field:'obj_").append(obj.getCode()).append("',align:'right',editor:'text'\">");
			sb.append(obj.getName()).append("</th>");
		}
		sb.append("</tr></thead><tbody>");

		// 开始填充数据
		for (Indexitem item : leafIndices) {
			List<ChildIndex> clist = getchildindex(item);
			boolean flag = true;
			int count = 0;
			for (ChildIndex c : clist) {
				sb.append("<tr>");
				if (flag) {
					StringBuilder tip = new StringBuilder();
					Indexitem tmpParent = item;
					while (tmpParent.parent != null) {
						tip.insert(0, "," + tmpParent.parent.getIndexName());
						tmpParent = tmpParent.parent;
					}
					tip.delete(0, tip.indexOf(",", 1) + 1);
					sb.append("<td>").append(tip.toString()).append("</td>");
					sb.append("<td>").append(CodeDictionary.syscode_traslate("tbm_indexitem", "indexcode", "indexname", c.getIndexcode())).append("</td>");
					sb.append("<td>").append(c.getIndextype()).append("</td>");
				} else {
					sb.append("<td>").append("</td>");
					sb.append("<td>").append("</td>");
					sb.append("<td>").append("</td>");
				}

				sb.append("<td>").append(c.getScorcefunc()).append("</td>");
				if(c.getIndextype().equals("计算函数型")){
					IndexPara para = paraname.get(c.getIndexcode()).get(c.getPara());
					String name = paraname.get(c.getIndexcode()).get(c.getPara()).getParacode();
					if("业务数据".equals(para.getParavaluemode())){
						IndexBizPara bizpara = IndexDao.getIndexBizPara(para.getParacode());
						if(bizpara == null){
							throw new ReviewException("指标项 [" + item.getIndexName() +"] 引用的参数 [" + para.getParacode() + "] 未定义");
						}
						
						Map<String, String> values = getBizParaValue(task.getReviewees(), task.getDate().getPeriodCode(bizpara.getPeriod()), ""+task.getDate().getYear(), bizpara.getCode());
						iter = task.getReviewees().iterator();
						while(iter.hasNext()){
							ReviewableObj obj = iter.next();
							
							HashMap<String, HashMap<String, String>> tmp = paravalue.get(obj.getCode());
							if(tmp == null){
								tmp = new HashMap<String, HashMap<String, String>>();
								tmp.put(c.getIndexcode(), new HashMap<String, String>());
								paravalue.put(obj.getCode(), tmp);
							}
							if(values.get(obj.getCode()) == null){
								throw new ReviewException("尚未给 [" + obj.getName() + "] 引用的参数 [" + bizpara.getName() +"] 赋值", ReviewException.PARA_VALUE_NOT_EXIST);
							}
							
							if(tmp.get(c.getIndexcode()) == null){
								tmp.put(c.getIndexcode(), new HashMap<String, String>());
							}
							tmp.get(c.getIndexcode()).put(c.getPara(), values.get(obj.getCode()).toString());
						}
						name = bizpara.getName();
					}else if("指标得分引用".equals(para.getParavaluemode())){
						iter = task.getReviewees().iterator();
						while(iter.hasNext()){
							ReviewableObj obj = iter.next();
							ReferObjectIndex refer = IndexDao.getReferObjectIndex(para.getParacode(), obj.getCode() , c.getIndexcode());
							String value = IndexDao.getIndexValue(obj.getCode(), c.getIndexcode(), "" + task.getDate().getYear(), task.getDate().getPeriodCode());
							if(value == null){
								//value = "1.0";
								throw new ReviewException("值引用错误，引用对象尚未计分");
							}
							
							HashMap<String, HashMap<String, String>> tmp = paravalue.get(obj.getCode());
							if(tmp == null){
								tmp = new HashMap<String,  HashMap<String, String>>();
								tmp.put(c.getIndexcode(), new HashMap<String, String>());
								paravalue.put(obj.getCode(), tmp);
							}
							
							if(tmp.get(c.getIndexcode()) == null){
								tmp.put(c.getIndexcode(), new HashMap<String, String>());
							}
							tmp.get(c.getIndexcode()).put(c.getPara(), value);
						}
					}else if("指标值引用".equals(para.getParavaluemode())){
						iter = task.getReviewees().iterator();
						while(iter.hasNext()){
							ReviewableObj obj = iter.next();
							
							ReferObjectIndex refer = IndexDao.getReferObjectIndex(para.getParacode(), obj.getCode() , c.getIndexcode());
							String score = IndexDao.getIndexScore(obj.getCode(), c.getIndexcode(), ""+task.getDate().getYear(), task.getDate().getPeriodCode());
							if(score == null){
								//score = "2.0";
								throw new ReviewException("得分引用错误，引用对象尚未计分");
							}
							HashMap<String, HashMap<String, String>> tmp = paravalue.get(obj.getCode());
							if(tmp == null){
								tmp = new HashMap<String,  HashMap<String, String>>();
								tmp.put(c.getIndexcode(), new HashMap<String, String>());
								paravalue.put(obj.getCode(), tmp);
							}
							if(tmp.get(c.getIndexcode()) == null){
								tmp.put(c.getIndexcode(), new HashMap<String, String>());
							}
							tmp.get(c.getIndexcode()).put(c.getPara(), score);
						}
					}
					
					sb.append("<td>").append(name).append("</td>");
				}else{
					if (!flag) {
						sb.append("<td>").append("</td>");
					} else {
						sb.append("<td>").append(c.getPara()).append("</td>");
					}
				}

				iter = task.getReviewees().iterator();
				while(iter.hasNext()){
					ReviewableObj obj = iter.next();
					
					boolean isValued = false;
					if(data!=null && !data.isEmpty()){
						HashMap<String, String> rowdt = data.get(obj.getCode());
						if(rowdt!=null){
							sb.append("<td>").append(rowdt.get(c.getIndexcode()).split(",")[count]).append("</td>");
							isValued = true;
						}
					}
					if(!isValued){
						if (inputstr.get(obj.getCode()) != null) {
							HashMap<String, List<String>> rowdt = inputstr.get(obj.getCode());
							sb.append("<td>").append(rowdt.get(c.getIndexcode()).get(count)).append("</td>");
						} else {
							if(paravalue.get(obj.getCode()) == null || paravalue.get(obj.getCode()).get(c.getIndexcode()) == null){
								sb.append("<td>").append("").append("</td>");
							}else{
								sb.append("<td>").append(paravalue.get(obj.getCode()).get(c.getIndexcode()).get(c.getPara())).append("</td>");
							}
						}
					}
				}
				flag = false;
				count++;
				sb.append("</tr>");
			}
		}

		sb.append("</tbody></table>");
		return sb.toString();
	}
	
	private static void close(ResultSet rs, Statement st, Connection conn){
		try{
			if(rs != null){
				rs.close();
			}
			
			if(st != null){
				st.close();
			}
			
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e){
		}
	}
	
	
	public static String getResultsTreeGrid(ReviewTask task){
		// 获得与该指标体系关联的所有考核对象
		List<ReviewableObj> objs = task.getReviewees();
		// 生成指标树
		task.parseIndexTree();
		Indexitem arch = task.getIndexArch();
		
		HashMap<String, HashMap<String, String>> scores = new HashMap<String, HashMap<String, String>>();
		try {
			String sql = "select * from tbm_indexscoredetail where objecttype='" + task.getTaskType() +"' and indexcode like '" + 
					arch.getIndexCode() + "%' and scoreyear='" + task.getDate().getYear() + "' and scoreperiod='" + task.getDate().getPeriodCode() +"'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					
					HashMap<String, String> tmp = scores.get(r.getString("objectcode"));
					if(tmp == null){
						tmp = new HashMap<String, String>();
						scores.put(r.getString("objectcode"), tmp);
					}
					tmp.put(r.getString("indexcode"), r.getString("scorevalue"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("<script>$('#tt').treegrid({idField:'code',fit:true,fitColumns:true,treeField:'name',columns:[[");
		sb.append("{field:'name',title:'指标名称'}");
		Iterator<ReviewableObj> iter = objs.iterator();
		while(iter.hasNext()){
			ReviewableObj obj = iter.next();
			sb.append(",{field:'obj_").append(obj.getCode()).append("',title:'").append(obj.getName()).append("'}");
		}
		sb.append("]],data:[");
		build(arch, sb, objs, scores);
		sb.append("]})</script>");
		return sb.toString();
	}
	
	private static void build(Indexitem item, StringBuilder sb, List<ReviewableObj> objs, HashMap<String, HashMap<String, String>> scores){
		sb.append("{").append("\"code\":").append("\"").append(item.getIndexCode().replace('.', '_')).append("\"");
		sb.append(",").append("\"name\":").append("\"").append(item.getIndexName()).append("\"");
		Iterator<ReviewableObj> iter = objs.iterator();
		while(iter.hasNext()){
			ReviewableObj obj = iter.next();
			sb.append(",").append("\"obj_").append(obj.getCode()).append("\":").append(scores.get(obj.getCode()).get(item.getIndexCode()));
		}
		sb.append(",\"children\":").append("[");
		for(int i=0; i<item.children.size(); i++){
			if(i > 0){
				sb.append(",");
			}
			build(item.children.get(i), sb, objs, scores);
		}
		sb.append("]}");
	}
	
	public static List<Indexitem> getindexlist(String indexcode) {
		try {
			List<Indexitem> indexlist = new ArrayList<Indexitem>();
			String sql = "select * from tbm_indexitem where indexcode like '" + indexcode + "%' and isparent=0 order by indexcode";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					Indexitem indexitem = new Indexitem();
					indexitem.setIndexCode(r.getString("indexcode"));
					indexitem.setUniIndexCode(r.getString("uniindexcode"));
					indexitem.setIndexName(r.getString("indexname"));
					indexitem.setIndexDesc(r.getString("indexdesc"));
					indexitem.setIndexCountLimit(Integer.parseInt(Format.NullToZero(r.getString("indexcountlimit"))));
					indexitem.setArchCode(r.getString("archcode"));
					indexitem.setIsParent(r.getString("isparent"));
					indexitem.setParentIndexCode(r.getString("parentindexcode"));
					indexitem.setValueComputingType(r.getString("valuecomputingtype"));
					indexitem.setValueScoreSourceType(r.getString("valuescoresourcetype"));
					indexitem.setNumPara(Integer.parseInt(Format.NullToZero(r.getString("numpara"))));
					indexitem.setValueFunc(r.getString("valuefunc"));
					indexitem.setValueUnit(r.getString("valueunit"));
					indexitem.setSCoreFuncType(r.getString("scorefunctype"));
					indexitem.setScoreFunc(r.getString("scorefunc"));
					indexitem.setIsRequired(Integer.parseInt(Format.NullToZero(r.getString("isrequired"))));
					indexitem.setScorePeriod(r.getString("scoreperiod"));
					indexitem.setScoreDefault(Double.parseDouble(Format.NullToZero(r.getString("scoredefault"))));
					indexitem.setScoreSumLow(Double.parseDouble(Format.NullToZero(r.getString("scoresumLow"))));
					indexitem.setScoreSumMax(Double.parseDouble(Format.NullToZero(r.getString("scoresummax"))));
					indexitem.setUpperSumWeight(Double.parseDouble(Format.NullToZero(r.getString("uppersumweight"))));
					indexitem.setValidBeginDate(Format.strToDate(r.getString("validbegindate")));
					indexitem.setValidEndDate(Format.strToDate(r.getString("validenddate")));
					indexitem.setEnabled(Integer.parseInt(Format.NullToZero(r.getString("enabled"))));
					indexitem.setMemo(r.getString("memo"));
					indexlist.add(indexitem);
				}

			}
			return indexlist;
		} catch (Exception e) {
			return null;
		}
	}

	public static List<ChildIndex> getchildindex(Indexitem indexitem) {
		try {
			String indexcode = indexitem.getIndexCode();
			String valuecomputingtype = indexitem.getValueComputingType();
			DBObject dbObject = new DBObject();
			List<ChildIndex> childs = new ArrayList<ChildIndex>();
			String sql = "select * from tbm_indexitempara where indexcode='" + indexcode + "' order by paraid";
			if (valuecomputingtype.equals("计算函数型")) {
				DataTable dt = dbObject.runSelectQuery(sql);
				if (dt != null && dt.getRowsCount() >= 0) {
					for (int i = 0; i < dt.getRowsCount(); i++) {
						ChildIndex childIndex = new ChildIndex(indexcode.substring(0, 3), indexcode, indexitem.getScoreFunc() + "; x="
								+ indexitem.getValueFunc(), valuecomputingtype, dt.get(i).getString("paraid"));
						childs.add(childIndex);
					}

				}

			} else {
				// 解析枚举类得分函数(未按时参加培训及活动,推诿不团结)=(-0.2,-0.2)
				String sorcefun = indexitem.getValueFunc();
				String[] s1 = sorcefun.split("=");
				String item_name = (String) s1[0].subSequence(1, s1[0].length() - 1);
				String item_value = (String) s1[1].subSequence(1, s1[1].length() - 1);
				String[] itemnames = item_name.split(",");
				String[] itemvalues = item_value.split(",");
				String para;
				if (valuecomputingtype.equals("枚举单选型")) {
					para = "单选";
				} else {
					para = "多选";
				}
				for (int i = 0; i < itemnames.length; i++) {
					ChildIndex childIndex = new ChildIndex(indexcode.substring(0, 3), indexcode, itemnames[i] + "," + itemvalues[i], valuecomputingtype, para);
					childs.add(childIndex);
				}
			}
			return childs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static HSSFWorkbook exportXls(JSONObject obj){
		String archcode = obj.getString("indexcode");
		String year = obj.getString("relateyear");
		String period = obj.getString("periodcode");
		String objType = obj.getString("objtype");
		int objCount = 0;
		List<String> objCodes = new ArrayList<String>();
		for(Object key : obj.keySet()){
			if(((String)key).startsWith("obj_")){
				objCount++;
				String tmp = ((String)key).substring(4);
				objCodes.add(tmp);
			}
		}
		Map<String, String> objs = getObjName(objCodes, objType);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		Indexitem arch = IndexDao.getIndex(archcode);
		ExcelTitle title = new ExcelTitle();
		title.setSheetName(arch.getIndexName());
		title.setTitle(arch.getIndexName());
		title.setSubTitle(year + "年, " + ParaDataHelper.code2Name(period));
		
		// 创建工作表
		Sheet sheet = workbook.createSheet(title.getSheetName());
		// 创建表格属性
		Map<String, HSSFCellStyle> styles = ExcelExportUtil.createStyles(workbook);
		// 表格字段数, 从0开始计数
		int filedWidth = objCount - 1 + 6;
		int index = 0, i;
		
		// 创建第0行, 行高650
		Row row = sheet.createRow(0);
		row.setHeight((short) 650);
		ExcelExportUtil.createStringCell(row, 0, title.getTitle(), ExcelExportUtil.getHeaderStyle(workbook, title), null);
		// 合并单元格, 0行到0行, 0列到filedWidth列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, filedWidth));

		// 有副标题的话
		if (title.getSubTitle() != null) {
			row = sheet.createRow(1);
			row.setHeight((short) 350);
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			ExcelExportUtil.createStringCell(row, 0, title.getSubTitle(), style, null);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, filedWidth));
			i = 2;
		} else {
			i = 1;
		}
		
		// 创建freeze区域, i是标题行的行数, 2是数据表列头占的行数
		sheet.createFreezePane(0, 2 + i, 0, 2 + i);
		index += i;
		// ================================创建数据表列头这一行====================================
		row = sheet.createRow(index);
		row.setHeight((short) 450);
		CellStyle titleStyle = ExcelExportUtil.getTitleStyle(workbook, title);

		// 上级指标
		int cellIndex = 0;
		Cell cell = row.createCell(cellIndex);
		RichTextString Rtext = new HSSFRichTextString("上级指标");
		cell.setCellValue(Rtext);
		cell.setCellStyle(titleStyle);
		sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
		cellIndex++;
		
		// 采集指标(code)
		cell = row.createCell(cellIndex);
		Rtext = new HSSFRichTextString("code");
		cell.setCellValue(Rtext);
		cell.setCellStyle(titleStyle);
		sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
		cellIndex++;

		// 采集指标(name)
		cell = row.createCell(cellIndex);
		Rtext = new HSSFRichTextString("采集指标");
		cell.setCellValue(Rtext);
		cell.setCellStyle(titleStyle);
		sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
		cellIndex++;

		// 指标类别
		cell = row.createCell(cellIndex);
		Rtext = new HSSFRichTextString("指标类别");
		cell.setCellValue(Rtext);
		cell.setCellStyle(titleStyle);
		sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
		cellIndex++;

		// 计分方法
		cell = row.createCell(cellIndex);
		Rtext = new HSSFRichTextString("计分方法");
		cell.setCellValue(Rtext);
		cell.setCellStyle(titleStyle);
		sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
		cellIndex++;

		// 参数
		cell = row.createCell(cellIndex);
		Rtext = new HSSFRichTextString("参数");
		cell.setCellValue(Rtext);
		cell.setCellStyle(titleStyle);
		sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
		cellIndex++;

		// 考核对象
		for (String code : objs.keySet()) {
			cell = row.createCell(cellIndex);
			Rtext = new HSSFRichTextString(objs.get(code));
			cell.setCellValue(Rtext);
			cell.setCellStyle(titleStyle);
			sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
			cellIndex++;
		}
		// =================================列头创建完毕=============================================
		index += 1;
		
		row = sheet.createRow(index++);
		row.setZeroHeight(true);
		cellIndex = 6;
		for (String code : objs.keySet()) {
			cell = row.createCell(cellIndex++);
			Rtext = new HSSFRichTextString(code);
			cell.setCellValue(Rtext);
		}
		
		// 调整列宽
		sheet.setColumnWidth(0, 256 * 10 * 2); // 10个字符 每个汉字2个字符宽度
		sheet.setColumnHidden(1, true);
		sheet.setColumnWidth(2, 256 * 10 * 2);
		sheet.setColumnWidth(3, 256 * 6 * 2);
		sheet.setColumnWidth(4, 256 * 20 * 2);
		sheet.setColumnWidth(5, 256 * 20 * 2);
		for (int j = 6; j < objCount + 6; j++) {
			sheet.setColumnWidth(j, 256 * 6 * 2); // 6个字符 每个汉字2个字符宽度
		}
		
		HashMap<String, HashMap<String, IndexPara>> paraname = new HashMap<String, HashMap<String, IndexPara>>();
		try {
			String sql = "select * from tbm_indexitempara where indexcode like '" + arch.getIndexCode() + "%'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int k = 0; k < dt.getRowsCount(); k++) {
					DataRow r = dt.get(k);
					IndexPara para = new IndexPara();
					para.setIndexcode(r.getString("indexcode"));
					para.setParacode(r.getString("paracode"));
					para.setParaid(r.getString("paraid"));
					para.setParavaluemode(r.getString("paravaluemode"));
					
					HashMap<String, IndexPara> tmp = paraname.get(para.getIndexcode());
					if(tmp == null){
						tmp = new HashMap<String, IndexPara>();
						paraname.put(r.getString("indexcode"), tmp);
					}
					tmp.put(r.getString("paraid"), para);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Indexitem> indexItems = IndexDao.getIndexLeafAndTree(arch.getIndexCode());
		// 开始填充数据
		int itemIndex = index;
		for (Indexitem item : indexItems) {
			List<ChildIndex> clist = getchildindex(item);

			row = sheet.createRow(index);
			row.setHeight((short) 350);
			CellStyle styleTmp = itemIndex++ % 2 == 0 ? ExcelExportUtil.getStyles(styles, false, false) : ExcelExportUtil.getStyles(styles, true, false);

			int rowCount = index;
			for (ChildIndex c : clist) {
				if (rowCount == index) {
					StringBuilder tip = new StringBuilder();
					Indexitem tmpParent = item;
					while (tmpParent.parent != null) {
						tip.insert(0, "," + tmpParent.parent.getIndexName());
						tmpParent = tmpParent.parent;
					}
					tip.delete(0, tip.indexOf(",", 1) + 1);
					// 上级指标
					cell = row.createCell(0);
					Rtext = new HSSFRichTextString(tip.toString());
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);
					
					// 采集指标(code)
					cell = row.createCell(1);
					Rtext = new HSSFRichTextString(c.getIndexcode());
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);

					// 采集指标(name)
					cell = row.createCell(2);
					Rtext = new HSSFRichTextString(CodeDictionary.syscode_traslate("tbm_indexitem", "indexcode", "indexname", c.getIndexcode()));
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);

					// 指标类别
					cell = row.createCell(3);
					Rtext = new HSSFRichTextString(c.getIndextype());
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);

					// 计分方法
					cell = row.createCell(4);
					Rtext = new HSSFRichTextString(c.getScorcefunc());
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);

					String text  =  "";
					if(c.getIndextype().equals("计算函数型")){
						IndexPara para = paraname.get(c.getIndexcode()).get(c.getPara());
						text = paraname.get(c.getIndexcode()).get(c.getPara()).getParacode();
						if("业务数据".equals(para.getParavaluemode())){
							IndexBizPara bizpara = IndexDao.getIndexBizPara(para.getParacode());
							if(bizpara == null){
								//throw new ReviewException("指标项" + item.getIndexName() +"引用的业务数据参数" + para.getParacode() + "不存在");
							}
							text = bizpara.getName();
						}
					}else{
						text = c.getPara();
					}
					// 参数
					cell = row.createCell(5);
					Rtext = new HSSFRichTextString(text);
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);

					// 考核对象
					cellIndex = 6;
					for (String code : objs.keySet()) {
						JSONObject indexdata = obj.getJSONObject("obj_" + code);
						String inputStr = indexdata.getString(item.getIndexName());
						cell = row.createCell(cellIndex++);
						Rtext = new HSSFRichTextString(inputStr.split(",")[0]);
						cell.setCellValue(Rtext);
						cell.setCellStyle(styleTmp);
					}
				} else if (rowCount > index) {
					Row rowtmp = sheet.createRow(rowCount);
					rowtmp.setHeight((short) 350);

					// 上级指标
					cell = rowtmp.createCell(0);
					Rtext = new HSSFRichTextString("");
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);
					
					// 采集指标(code)
					cell = rowtmp.createCell(1);
					Rtext = new HSSFRichTextString("");
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);

					// 采集指标(name)
					cell = rowtmp.createCell(2);
					Rtext = new HSSFRichTextString("");
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);

					// 指标类别
					cell = rowtmp.createCell(3);
					Rtext = new HSSFRichTextString("");
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);

					// 计分方法
					cell = rowtmp.createCell(4);
					Rtext = new HSSFRichTextString(c.getScorcefunc());
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);
					
					String text  =  "";
					if(c.getIndextype().equals("计算函数型")){
						IndexPara para = paraname.get(c.getIndexcode()).get(c.getPara());
						text = paraname.get(c.getIndexcode()).get(c.getPara()).getParacode();
						if("业务数据".equals(para.getParavaluemode())){
							IndexBizPara bizpara = IndexDao.getIndexBizPara(para.getParacode());
							if(bizpara == null){
								//throw new ReviewException("指标项" + item.getIndexName() +"引用的业务数据参数" + para.getParacode() + "不存在");
							}
							text = bizpara.getName();
						}
					}
					// 参数
					cell = rowtmp.createCell(5);
					Rtext = new HSSFRichTextString(text);
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);
					
					// 考核对象
					cellIndex = 6;
					for (String code : objs.keySet()) {
						JSONObject indexdata = obj.getJSONObject("obj_" + code);
						String inputStr = indexdata.getString(item.getIndexName());
						cell = rowtmp.createCell(cellIndex++);
						Rtext = new HSSFRichTextString(inputStr.split(",")[rowCount-index]);
						cell.setCellValue(Rtext);
						cell.setCellStyle(styleTmp);
					}
				}
				rowCount++;
			}
			sheet.addMergedRegion(new CellRangeAddress(index, rowCount - 1, 0, 0));
			sheet.addMergedRegion(new CellRangeAddress(index, rowCount - 1, 1, 1));
			sheet.addMergedRegion(new CellRangeAddress(index, rowCount - 1, 2, 2));
			sheet.addMergedRegion(new CellRangeAddress(index, rowCount - 1, 3, 3));
			index = rowCount;
		}
		return workbook;
	}
	
	public static void initImport(InputStream is, String type){
		Connection conn = ConnectionPool.getConnection();
		try {
			DBObject db = new DBObject();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("insert into tbm_indexitem(indexcode,indexname,isparent,parentindexcode,valuefunc,scoreperiod) values(?,?,?,?,?,'D00.M00')");
			
			int countd = Integer.valueOf(db.executeOneValue("select count(*) from tbm_indexitem where indexcode like 'D%' and length(indexcode)=3"));
			int counts = Integer.valueOf(db.executeOneValue("select count(*) from tbm_indexitem where indexcode like 'S%' and length(indexcode)=3"));
			int countc = Integer.valueOf(db.executeOneValue("select count(*) from tbm_indexitem where indexcode like 'C%' and length(indexcode)=3"));
			
			//hack by hui 
			counts += 10;
			countd += 5;
			
            HWPFDocument hwpf = new HWPFDocument(is);
            Range range = hwpf.getRange();// 得到文档的读取范围
            TableIterator it = new TableIterator(range);
	          // 迭代文档中的表格
	          while (it.hasNext()) {
	                Table tb = (Table) it.next();
	                String archName = getCellContent(tb.getRow(0).getCell(2));
	                String archCode = "";
	                if(archName.startsWith("部门")){
	                	countd++;
	    				archCode = "D" + (countd < 10 ? ("0"+countd) : countd);
	                } else if(archName.startsWith("个人")) {
	                	counts++;
	                	archCode = "S" + (counts < 10 ? ("0"+counts) : counts);
	                } else if(archName.startsWith("单位")){
	                	countc++;
	                	archCode = "C" + (countc < 10 ? ("0"+countc) : countc);
	                }
	                
	                ps.setString(1, archCode);
	                ps.setString(2, archName.substring(2));
	                ps.setInt(3, 1);
	                ps.setString(4, "-1");
	                ps.setString(5, "");
	                ps.execute();
	                
	                int indexCount0 = 0;
	                int indexCount1 = 0;
	                int indexCount2 = 0;

	                String currIndexCode0 = "";
	                String currIndexCode1 = "";
	                
	                for (int i = 1; i < tb.numRows(); i++) {
	                         TableRow tr = tb.getRow(i);
	                         String cell0 = getCellContent(tr.getCell(0));
	                         String cell1 = getCellContent(tr.getCell(1));
	                         String cell2 = getCellContent(tr.getCell(2));
	                         
	                         if(StringUtils.isNotBlank(cell0)){
	                        	 indexCount0++;indexCount1=1;
	                        	 currIndexCode0 = archCode + "." + String.format("%03d", indexCount0);
	                        	 ps.setString(1, currIndexCode0);
	                        	 if(cell0.startsWith("通用指标")){
	                        		 ps.setString(2, cell0.substring(0, cell0.lastIndexOf("（")));
	                        	 }else{
	                        		 ps.setString(2, cell0.substring(0, cell0.indexOf("指标")));
	                        	 }
	                 			 ps.setInt(3, 1);
	                 			 ps.setString(4, archCode);
	                 			 ps.setString(5, "");
	                 			 ps.execute();
	                        	 
	                 			 currIndexCode1 = currIndexCode0 + "." + String.format("%03d", indexCount1);
	                 			 ps.setString(1, currIndexCode1);
	                 			 ps.setString(2, cell1);
	                 			 ps.setString(4, currIndexCode0);
	                 			 
	                 			 if(i < tb.numRows() - 1){
	                 				if(StringUtils.isNotBlank(getCellContent(tb.getRow(i + 1).getCell(1)))){
	                 					ps.setInt(3, 0);
	                 					ps.setString(5, cell2);
	                 					ps.execute();
	                 				}else{
	                 					ps.setInt(3, 1);
	                 					ps.setString(5, "");
	                 					ps.execute();
	                 					
	                 					indexCount2 = 1;
	                 					ps.setString(1, currIndexCode1 + "." + String.format("%03d", indexCount2));
	    	                 			ps.setString(2, cell2.substring(0, 10));
	    	                 			ps.setInt(3, 0);
	    	                 			ps.setString(4, currIndexCode1);
	    	                 			ps.setString(5, cell2);
	    	                 			ps.execute();
	                 				}
	                 			 } else {
	                 				ps.setInt(3, 0);
	                 				ps.setString(5, cell2);
                 					ps.execute();
	                 			 }
	                         } else {
	                        	 if(StringUtils.isNotBlank(cell1)){
	                        		 indexCount1++;
	                        		 currIndexCode1 = currIndexCode0 + "." + String.format("%03d", indexCount1);
	                        		 ps.setString(1, currIndexCode1);
		                 			 ps.setString(2, cell1);
		                 			 ps.setString(4, currIndexCode0);
	                        		 
	                        		 if(i < tb.numRows() - 1){
	                        			System.out.println(getCellContent(tb.getRow(0).getCell(2))+":"+cell0 +":"+cell1+":"+cell2);
	 	                 				if(StringUtils.isNotBlank(getCellContent(tb.getRow(i + 1).getCell(1)))){
	 	                 					ps.setInt(3, 0);
	 	                 					ps.setString(5, cell2);
	 	                 					ps.execute();
	 	                 				}else{
	 	                 					ps.setInt(3, 1);
	 	                 					ps.setString(5, "");
	 	                 					ps.execute();
	 	                 					
	 	                 					indexCount2 = 1;
	 	                 					ps.setString(1, currIndexCode1 + "." + String.format("%03d", indexCount2));
	 	    	                 			ps.setString(2, cell2.substring(0, 10));
	 	    	                 			ps.setInt(3, 0);
	 	    	                 			ps.setString(4, currIndexCode1);
	 	    	                 			ps.setString(5, cell2);
	 	    	                 			ps.execute();
	 	                 				}
	 	                 			 } else {
	 	                 				ps.setInt(3, 0);
	                  					ps.execute();
	 	                 			 }
	                        	 } else {
	                        		 indexCount2++;
	                        		 ps.setString(1, currIndexCode1 + "." + String.format("%03d", indexCount2));
    	                 			 ps.setString(2, cell2.substring(0, 10));
    	                 			 ps.setInt(3, 0);
    	                 			 ps.setString(4, currIndexCode1);
    	                 			 ps.setString(5, cell2);
    	                 			 ps.execute();
	                        	 }
	                         }
	                  }
	            } // end while
	          conn.commit();
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  try {
				conn.rollback();
	    	  } catch (SQLException e1) {
				e1.printStackTrace();
			}
	      }
	}
	
	private static String getCellContent(TableCell cell){
		StringBuilder sb = new StringBuilder();
        for (int k = 0; k < cell.numParagraphs(); k++) {
               sb.append(cell.getParagraph(k).text().trim());
        }
        return sb.toString();
	}

	public static Map<String, HashMap<String, String>> importXls(InputStream is, String indexcode, String objType, String year, String period) throws ReviewImportException{
		if (!(is.markSupported())) {
			is = new PushbackInputStream(is, 8);
		}
		HSSFWorkbook book = null;
		try {
			book = new HSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (book == null) {
			return null;
		}
		
		// 取第一个工作表
		Sheet sheet = book.getSheetAt(0);
		// 行遍历器
		Iterator<Row> rowIterator = sheet.rowIterator();

		Row row = null;
		Cell cell = null;
	    
	    Map<String, Integer> captionMap = new HashMap<String, Integer>();
		Map<String, HashMap<String, String>> hashtb = new HashMap<String, HashMap<String, String>>();
		row = rowIterator.next();
		row = rowIterator.next();
		row = rowIterator.next();
		row = rowIterator.next();  // 跳过标题行（主标题 副标题共两行）,列头占两行
		for(int j=6; j<row.getLastCellNum(); j++){
			cell = row.getCell(j);
			String value = cell.getStringCellValue();
			captionMap.put(value, j);
			hashtb.put(value, new HashMap<String, String>());
		}
		
		String curIndexCode = null;
		while(rowIterator.hasNext()){
			row = rowIterator.next();
			cell = row.getCell(1);
			String code = cell.getStringCellValue();
			if(StringUtils.isNotBlank(code)){
				curIndexCode = code;
				for(String objcode : hashtb.keySet()){
					cell = row.getCell(captionMap.get(objcode));
					Double value = null;
					try{
						value = getDoubleValue(cell);
					}catch(NumberFormatException e){
						throw new ReviewImportException("对象 "+sheet.getRow(2).getCell(captionMap.get(objcode)).getStringCellValue()+
								" 在指标 "+row.getCell(2).getStringCellValue() + " 处的输入 "+ cell.getStringCellValue() + " 格式错误");
					}
					
					if(value != null){
						hashtb.get(objcode).put(curIndexCode, String.valueOf(value));
					}else{
						hashtb.get(objcode).put(curIndexCode, " ");
					}
				}
			}else{
				for(String objcode : hashtb.keySet()){
					cell = row.getCell(captionMap.get(objcode));
					Double value = null;
					try{
						value = getDoubleValue(cell);
					}catch(NumberFormatException e){
						throw new ReviewImportException("对象 "+sheet.getRow(2).getCell(captionMap.get(objcode)).getStringCellValue()+
								" 在指标 "+row.getCell(2).getStringCellValue() + " 处的输入 "+ cell.getStringCellValue() + " 格式错误");
					}
					if(value != null){
						String tmp = hashtb.get(objcode).get(curIndexCode) + "," + String.valueOf(value);
						hashtb.get(objcode).put(curIndexCode, tmp);
					}else{
						String tmp = hashtb.get(objcode).get(curIndexCode) + ", ";
						hashtb.get(objcode).put(curIndexCode, tmp);
					}
				}
			}
		}
		return hashtb;
	}
	
	private static Double getDoubleValue(Cell aCell) throws NumberFormatException{
		Double ret = null;
		switch(aCell.getCellType()){
		case Cell.CELL_TYPE_NUMERIC: 
			ret = aCell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING: 
			String tmp = aCell.getStringCellValue();
			if(StringUtils.isNotBlank(tmp)){
				ret = Double.valueOf(tmp);
			}
		}
		
		return ret;
	}
}
