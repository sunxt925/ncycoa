package com.performance;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.alibaba.fastjson.JSONObject;
import com.common.CodeDictionary;
import com.common.IndexCode;
import com.db.ConnectionPool;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.index.ReferPara;
import com.performance.poi.excel.ExcelExportUtil;
import com.performance.poi.excel.entity.ExcelTitle;

public class ParaDataHelper {
	private static Map<String, String> maps = new HashMap<String, String>();
	static {
		// 生成周期代码到周期名映射
		maps.clear();
		final String[] tmp = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
		for (int i = 1; i <= 9; i++) {
			maps.put("M0" + i, tmp[i - 1] + "月份");
		}
		for(int i=10; i<=12; i++){
			maps.put("M" + i, tmp[i - 1] + "月份");
		}
		for (int i = 1; i <= 4; i++) {
			maps.put("S0" + i, tmp[i - 1] + "季度");
		}
		maps.put("H01", "上半年");
		maps.put("H02", "下半年");
		maps.put("Y01", "全年");
	}
	
	public static String code2Name(String code){
		return maps.get(code);
	}

	// 生成参数数据表
	public static String getDataGrid(ReviewTask task, Map<String, HashMap<String, String>> data) throws ReviewException {
		if(task == ReviewTask.INVALID_TASK){
			throw new ReviewException("选定的时间内不存在考核对象");
		}
		// 获得该指标体系下的所用“业务数据”类型的参数
		String period = task.getDate().getPeriodCode();
		
		String tmpPeriod = "D00."+task.getDate().getPeriodtype()+"00";
		List<ReferPara> paras = getParasByIndexCode(task.getIndexArch().getIndexCode(), tmpPeriod);
		
		// 获得与该指标体系关联的所有考核对象
		List<ReviewableObj> objs = task.getReviewees();
		// 生成数据表列头
		StringBuffer sb = new StringBuffer();
		sb.append("<table id=\"para_tb\" class=\"easyui-datagrid\" data-options=\"singleSelect:true,onClickRow:onClickRow,toolbar:'#tb'\">");
		sb.append("<thead data-options=\"frozen:true\"><tr>");
		sb.append("<th data-options=\"field:'objname'\">考核对象</th></tr></thead>");
		sb.append("<thead><tr>");
		sb.append("<th data-options=\"field:'objcode',hidden:true\"></th>");
		
		for (ReferPara para : paras) {
			sb.append("<th data-options=\"field:'para_").append(para.getParacode()).append("',width:180,align:'right',editor:{type:'numberbox',options:{precision:2}}\">");
			sb.append(para.getParaname()).append("</th>");
		}
		sb.append("</tr></thead><tbody>");

		// 开始填充数据
		Iterator<ReviewableObj> iter = objs.iterator();
		while(iter.hasNext()){
			ReviewableObj obj = iter.next();
			sb.append("<tr><td>").append(obj.getName()).append("</td>");
			sb.append("<td>").append("obj_" + obj.getCode()).append("</td>");
			
			Map<String, String> value = null;
			if(data != null && !data.isEmpty()){
				value = data.get(obj.getCode());
			} else {
				value = getValuesByObj(obj.getCode(), period);
			}
			// 注意此处用于遍历的keys
			for (ReferPara para : paras) {
				sb.append("<td>");
				if (value.get(para.getParacode()) != null) {
					sb.append(value.get(para.getParacode()));
				}
				sb.append("</td>");
			}
			sb.append("</tr>");
		}
		sb.append("</tbody></table>");
		return sb.toString();
	}

	// 与考核对象关联的参数的值， 为提高性能后期考虑可以批量查询
	public static Map<String, String> getValuesByObj(String code, String period) {
		Map<String, String> data = new HashMap<String, String>();
		try {
			DBObject db = new DBObject();
			String sql1 = "select * from tbm_referparavalue where objectcode='" + code + "' and belongperiod='" + period + "'";
			DataTable dt1 = db.runSelectQuery(sql1);
			if (dt1 != null) {
				for (int j = 0; j < dt1.getRowsCount(); j++) {
					DataRow r1 = dt1.get(j);
					data.put(r1.getString("referparacode"), r1.getString("value"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 返回indexcode所指代的指标体系下所涉及到的所有参数的时间周期类型（半年、月度、季度等）
	 * 
	 * @param indexcode 指标体系编码
	 * @return
	 */
	public static String getAllPeriodCode(String indexcode) {
		List<String> periods = new ArrayList<String>();
		try {
			DBObject db = new DBObject();
			String sql1 = "select a.indexcode,a.indexname,b.paracode, b.paravaluemode from tbm_indexitem a,tbm_indexitempara b where a.indexcode like '"
					+ indexcode + "%' and a.isparent=0 and a.indexcode=b.indexcode";
			DataTable dt1 = db.runSelectQuery(sql1);
			if (dt1 != null) {
				for (int j = 0; j < dt1.getRowsCount(); j++) {
					DataRow r1 = dt1.get(j);
					String paraValueMode = r1.getString("paravaluemode");
					if ("业务数据".equals(paraValueMode)) {
						String sql2 = "select * from tbm_referpara where paracode='" + r1.getString("paracode") + "'";
						DataTable dt2 = db.runSelectQuery(sql2);
						if (dt2 != null && dt2.getRowsCount() >= 1)
							if (!periods.contains(dt2.get(0).getString("paraperiod")))
								periods.add(dt2.get(0).getString("paraperiod"));
					}
				}
			}

			StringBuffer sb = new StringBuffer();
			boolean flag = true;
			sb.append("[");
			for (String period : periods) {
				sb.append("{\"periodcode\":\"").append(period).append("\"");
				sb.append(",\"periodname\":\"").append(CodeDictionary.code_traslate("ScorePeriod", period)).append("\"");
				if (flag) {
					sb.append(",\"selected\":true");
					flag = false;
				}
				sb.append("},");
			}
			if (sb.length() > 1) {
				sb.delete(sb.length() - 1, sb.length());
			}
			sb.append("]");

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getPeriodDetail(String indexcode, String period, String year) {
		List<String> periodDetail = new ArrayList<String>();
		if ("D00.M00".equals(period)) {
			for (int i = 1; i <= 9; i++) {
				periodDetail.add("M0" + i);
			}
			periodDetail.add("M10");
			periodDetail.add("M11");
			periodDetail.add("M12");
		} else if ("D00.S00".equals(period)) {
			for (int i = 1; i <= 4; i++) {
				periodDetail.add("S0" + i);
			}
		} else if ("D00.H00".equals(period)) {
			periodDetail.add("H01");
			periodDetail.add("H02");
		} else if ("D00.Y00".equals(period)) {
			periodDetail.add("Y01");
		}

		try {
//			DBObject db = new DBObject();
//			StringBuilder sb = new StringBuilder();
//			sb.append("select relateperiod from tbm_refparavupdlog where relateyear='").append(year).append("' and indexarch='").append(indexcode).append("' and relateperiod in (");
//			boolean flag = true;
//			for (String item : periodDetail) {
//				if (flag) {
//					sb.append("'").append(item).append("'");
//				} else {
//					sb.append(",'").append(item).append("'");
//				}
//			}
//			sb.append(")");
//
//			List<String> strs = new ArrayList<String>();
//			DataTable dt1 = db.runSelectQuery(sb.toString());
//			if (dt1 != null) {
//				for (int j = 0; j < dt1.getRowsCount(); j++) {
//					String paraValueMode = dt1.get(j).getString("relateperiod");
//					if (!strs.contains(paraValueMode)) {
//						strs.add(paraValueMode);
//					}
//				}
//			}

			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (String p : periodDetail) {
				sb.append("{\"code\":\"").append(p).append("\"");
				sb.append(",\"name\":\"").append(maps.get(p)).append("\"");
//				if (strs.contains(p)) {
//					sb.append(",\"isExisted\":\"是\"");
//				} else {
//					sb.append(",\"isExisted\":\"否\"");
//				}
				sb.append("},");
			}
			if (sb.length() > 1)
				sb.delete(sb.length() - 1, sb.length());
			sb.append("]");

			return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static Map<String, String> getParasName(List<String> paracode){
		Map<String, String> paras = new HashMap<String, String>();
		for(String code : paracode){
			paras.put(code, new ReferPara(code).getParaname());
		}
		
		return paras;
	}
	
	public static List<ReferPara> getParasByIndexCode(String indexcode, String periodcode) {
		List<ReferPara> paras = new ArrayList<ReferPara>();
		try {
			DBObject db = new DBObject();
			String sql1 = "select a.indexcode,a.indexname,b.paracode, b.paravaluemode from tbm_indexitem a,tbm_indexitempara b where a.indexcode like '"
					+ indexcode + "%' and a.isparent=0 and a.indexcode=b.indexcode";
			DataTable dt1 = db.runSelectQuery(sql1);
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			if (dt1 != null) {
				for (int j = 0; j < dt1.getRowsCount(); j++) {
					DataRow r1 = dt1.get(j);
					String paraValueMode = r1.getString("paravaluemode");
					if ("业务数据".equals(paraValueMode)) {
						sb.append("'").append(r1.getString("paracode")).append("',");
					}
				}
			}
			
			if(sb.length() == 1){
				return java.util.Collections.emptyList();
			}
			
			sb.delete(sb.length() - 1, sb.length());
			sb.append(")");
			String sql2 = "select * from tbm_referpara where paraperiod='" + periodcode +"' and paracode in " + sb.toString();
			DataTable dt2 = db.runSelectQuery(sql2);
			if (dt2 != null) {
				for(int i=0; i<dt2.getRowsCount(); i++){
						DataRow r2 = dt2.get(i);
						ReferPara para = new ReferPara();
						para.setParacode(r2.getString("paracode"));
						para.setParaname(r2.getString("paraname"));
						para.setParaperiod(r2.getString("paraperiod"));
						para.setMemo(r2.getString("memo"));
						para.setGetparavaluefunc(r2.getString("getparavaluefunc"));
						if(r2.getString("defaultvalue") == null || "".equals(r2.getString("defaultvalue").trim())) {
							para.setDefaultvalue(null);
						} else {
							para.setDefaultvalue(Integer.parseInt(r2.getString("defaultvalue")));
						}
						if(r2.getString("usingflag") == null || "".equals(r2.getString("usingflag").trim())) {
							para.setUsingflag(null);
						} else {
							para.setUsingflag(Integer.parseInt(r2.getString("usingflag")));
						}
						paras.add(para);
				}
			}
			
			java.util.Collections.sort(paras, new Comparator<ReferPara>(){
				public int compare(ReferPara o1, ReferPara o2) {
					return o1.getParaorder() < o2.getParaorder() ? -1 :(o1.getParaorder() > o2.getParaorder() ? 1 : 0) ;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return paras;
	}

	public static Map<String, HashMap<String, String>> importXls(InputStream inputstream) throws Exception {
		try {
			HSSFWorkbook book = new HSSFWorkbook(inputstream);
			if (!(inputstream.markSupported())) {
				inputstream = new PushbackInputStream(inputstream, 8);
			}
			Sheet sheet = book.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			
			Row row = null;
			Cell cell = null;
			
			Map<String, Integer> captionMap = new HashMap<String, Integer>();
			row = rows.next();
			row = rows.next();
			row = rows.next();
			row = rows.next();  // 跳过标题行（主标题 副标题共两行）,列头占两行
			
			for(int j=2; j<row.getLastCellNum(); j++){
				cell = row.getCell(j);
				String value = cell.getStringCellValue();
				captionMap.put(value, j);
			}

			// 解析数据
			Map<String, HashMap<String, String>> hashtb = new HashMap<String, HashMap<String, String>>();
			while (rows.hasNext()) {
				row = rows.next();
				cell = row.getCell(1);
				String objcode = cell.getStringCellValue();
				if(StringUtils.isNotBlank(objcode)){
					HashMap<String, String> value = new HashMap<String, String>(); 
					for(String caption : captionMap.keySet()){
						cell = row.getCell(captionMap.get(caption));
						Double d = null;
						try{
							d = getDoubleValue(cell);
						}catch(NumberFormatException e){
						}
						if(d != null){
							value.put(caption, String.valueOf(d));
						} else {
							value.put(caption, "");
						}
					}
					hashtb.put(objcode, value);
				}
			}

			return hashtb;

		} catch (IOException e) {
			throw new Exception("数据解析错误");
		}
	}
	
	private static Double getDoubleValue(Cell aCell){
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

	public static HSSFWorkbook exportXls(JSONObject obj){
		String archcode = obj.getString("indexcode");
		String year = obj.getString("relateyear");
		String periodcode = obj.getString("periodcode");
		
		List<String> objCodes = new ArrayList<String>();
		List<String> paraCodes = new ArrayList<String>();
		boolean flag = true;
		for(Object key : obj.keySet()){
			if(((String)key).startsWith("obj_")){
				String tmp = ((String)key).substring(4);
				objCodes.add(tmp);
				
				if(flag){
					@SuppressWarnings("rawtypes")
					Set kset = obj.getJSONObject((String)key).keySet();
					for(Object k : kset){
						paraCodes.add(((String)k).substring(5));
					}
					flag = false;
				}
			}
		}
		String objType = "";
		if(archcode.startsWith("S")){
			objType = "staff";
		} else if(archcode.startsWith("C")){
			objType = "company";
		} else if(archcode.startsWith("D")){
			objType = "depart";
		}
			
		Map<String, String> objs = IndexDataHelper.getObjName(objCodes, objType);
		Map<String, String> paras = getParasName(paraCodes);
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			ExcelTitle title = new ExcelTitle();
			title.setSheetName("参数导入表");
			title.setTitle("参数导入表");
			title.setSubTitle(year + "年, " + code2Name(periodcode));

			// 创建工作表
			Sheet sheet = workbook.createSheet(title.getSheetName());
			// 创建表格属性
			Map<String, HSSFCellStyle> styles = ExcelExportUtil.createStyles(workbook);
			// 表格字段数, 从0开始计数
			int filedWidth = paras.size();
			int index = 0, i;
			
			// 创建第0行, 行高650
			Row row = sheet.createRow(0);
			row.setHeight((short) 650);
			ExcelExportUtil.createStringCell(row, 0, title.getTitle(), ExcelExportUtil.getHeaderStyle(workbook, title), null);
			// 合并单元格, 0行到0行, 0列到filedWidth列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, filedWidth+1));

			// 有副标题的话
			if (title.getSubTitle() != null) {
				row = sheet.createRow(1);
				row.setHeight((short) 350);
				HSSFCellStyle style = workbook.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				ExcelExportUtil.createStringCell(row, 0, title.getSubTitle(), style, null);
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, filedWidth+1));
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

			int cellIndex = 0;
			Cell cell = row.createCell(cellIndex);
			RichTextString Rtext = new HSSFRichTextString("考核对象");
			cell.setCellValue(Rtext);
			cell.setCellStyle(titleStyle);
			sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
			cellIndex++;
			
			cell = row.createCell(cellIndex);
			Rtext = new HSSFRichTextString("code");
			cell.setCellValue(Rtext);
			cell.setCellStyle(titleStyle);
			sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
			cellIndex++;
			
			for (String paracode : paraCodes) {
				cell = row.createCell(cellIndex);
				Rtext = new HSSFRichTextString(paras.get(paracode));
				cell.setCellValue(Rtext);
				cell.setCellStyle(titleStyle);
				sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex));
				cellIndex++;
			}
			// =================================列头创建完毕=============================================
			index += 1;
			
			row = sheet.createRow(index++);
			row.setZeroHeight(true);
			cellIndex = 2;
			for (String code : paraCodes) {
				cell = row.createCell(cellIndex++);
				Rtext = new HSSFRichTextString(code);
				cell.setCellValue(Rtext);
			}
			
			// 调整列宽
			sheet.setColumnWidth(0, 256 * 10 * 2); 
			sheet.setColumnHidden(1, true);
			for (int j = 2; j < paraCodes.size() + 2; j++) {
				sheet.setColumnWidth(j, 256 * 15 * 2); // 20个字符 每个汉字2个字符宽度
			}

			for (String objcode : objCodes) {
				row = sheet.createRow(index++);
				row.setHeight((short) 350);
				CellStyle styleTmp = index % 2 == 0 ? ExcelExportUtil.getStyles(styles, false, false) : ExcelExportUtil.getStyles(styles, true, false);

				cell = row.createCell(0);
				Rtext = new HSSFRichTextString(objs.get(objcode));
				cell.setCellValue(Rtext);
				cell.setCellStyle(styleTmp);
				
				cell = row.createCell(1);
				Rtext = new HSSFRichTextString(objcode);
				cell.setCellValue(Rtext);
				cell.setCellStyle(styleTmp);

				JSONObject jsonRow = obj.getJSONObject("obj_" + objcode);
				//Map<String, Double> data = getValuesByObj(objcode, periodcode);
				int k = 2;
				for (String paracode : paraCodes) {
					cell = row.createCell(k);
					Rtext = new HSSFRichTextString(jsonRow.getString("para_" + paracode));
					cell.setCellValue(Rtext);
					cell.setCellStyle(styleTmp);
					k++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workbook;
	}
	
	public static int insertData(JSONObject obj){
		String year = obj.getString("relateyear");
		String periodcode = obj.getString("periodcode");
		String indexcode = obj.getString("indexcode");

		int objcount = 0,paracount=0;
		StringBuilder objlist = new StringBuilder();
		StringBuilder paralist = new StringBuilder();
		for(Object objcode : obj.keySet()){
			if(objcode.toString().startsWith("obj_")){
				objlist.append(objcode.toString().substring(4)).append(",");
				objcount++;
				
				if(objcount == 1){
					JSONObject paraobj = obj.getJSONObject(objcode.toString());
					for(Object paracode : paraobj.keySet()){
						if(paracode.toString().startsWith("para_")){
							paralist.append(paracode.toString().substring(5)).append(",");
							paracount++;
						}
					}
				}
			}
		}
		if(objlist.length() > 0){
			objlist.delete(objlist.length() - 1, objlist.length());
		}
		if(paralist.length() > 0){
			paralist.delete(paralist.length() - 1, paralist.length());
		}
		int status = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psscore = null;
		try{
			conn = ConnectionPool.getConnection();
			if(conn == null){
				//返回错误
				status = 1; //获取数据库连接失败
				return status;
			}
			
			//考虑到event和eventdetail及scoredetail数据的一致性，将他们做成一个事务
			conn.setAutoCommit(false);
			
			
			ps = conn.prepareStatement("insert into tbm_refparavupdlog(tname,indexarch,relateyear,relateperiod,paralist,paranum,objectlist,objectnum) values(?,?,?,?,?,?,?,?)");
			psscore = conn.prepareStatement("insert into tbm_referparavalue(recno,objectcode,referparacode,value,belongyear,belongperiod) values(?,?,?,?,?,?)");

			String eventno = IndexCode.getRecno("rpl");
			conn.createStatement().execute("delete from tbm_refparavupdlog where relateyear='" + year + "' and relateperiod='" + periodcode +"' and indexarch='" + indexcode +"'");
			ps.setString(1, eventno);
			ps.setString(2, indexcode);
			ps.setString(3, year);
			ps.setString(4, periodcode);
			ps.setString(5, paralist.toString());
			ps.setInt(6, paracount);
			ps.setString(7, objlist.toString());
			ps.setInt(8, objcount);
			ps.execute();
			
			
			StringBuilder sb = new StringBuilder();
			for(Object objcode : obj.keySet()){
				if(objcode.toString().startsWith("obj_")){
					sb.append("'").append(objcode.toString().substring(4)).append("',");
				}
			}
			if(sb.length() > 0){
				sb.delete(sb.length() - 1, sb.length());
			}
			conn.createStatement().execute("delete from tbm_referparavalue where belongyear='" + year + "' and belongperiod='" + periodcode + "' and objectcode in (" + sb.toString() + ")");
			
			for(Object objcode : obj.keySet()){
				if(objcode.toString().startsWith("obj_")){
					JSONObject paraobj = obj.getJSONObject(objcode.toString());
					for(Object paracode : paraobj.keySet()){
						if(paracode.toString().startsWith("para_")){
							String tmp = paraobj.getString(paracode.toString());
							if(StringUtils.isBlank(tmp)){
								continue;
							}
							psscore.setString(1, IndexCode.getRecno("rpv"));
							psscore.setString(2, objcode.toString().substring(4));
							psscore.setString(3, paracode.toString().substring(5));
							psscore.setDouble(4, Double.valueOf(tmp));
							psscore.setString(5, year);
							psscore.setString(6, periodcode);
							psscore.execute();
						}
					}
				}
			}
			
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			try{
				status = 3; //插入数据失败
				conn.rollback(); //回滚事务
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		}finally{
			try{
				if(ps != null){
					ps.close();
				}
				if(psscore != null){
					psscore.close();
				}
				if(conn != null){
					conn.close();
				}
			}
			catch (Exception e){
				ps = null;
				psscore = null;
				conn = null;
			}
		}
		
		return status;
	}
}
