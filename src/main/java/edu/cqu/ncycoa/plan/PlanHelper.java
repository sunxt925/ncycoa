package edu.cqu.ncycoa.plan;

import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

import com.db.DBObject;
import com.db.Parameter;
import com.performance.ParaDataHelper;

public class PlanHelper {
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
				sb.append("},");
				break;
			}
			sb.append("},");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("]");
		return sb.toString();
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
	
	public static void insertTask(String staffcode){
		try {
			DBObject db=new DBObject();
			String value = db.executeOneValue("select count(*) from tbm_admindpt where staffcode='" + staffcode + "'");
			if("0".equals(value)){
				String sql="insert into tbm_admindpt(staffcode) values(?)";
				Parameter.SqlParameter[] paras = new Parameter.SqlParameter[]{new Parameter.String(staffcode)};
				db.run(sql, paras);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertTask(String staffcode, String orgcode){
		try {
			DBObject db=new DBObject();
			db.run("delete from tbm_admindpt where staffcode='" + staffcode + "' and (orgcode is null or orgcode='"+orgcode+"'");
			
			String sql="insert into tbm_admindpt(staffcode,orgcode) values(?,?,)";
			Parameter.SqlParameter[] paras = new Parameter.SqlParameter[]{new Parameter.String(staffcode), new Parameter.String(orgcode)};
			db.run(sql, paras);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delTask(String staffcode){
		try {
			DBObject db=new DBObject();
			db.run("delete from tbm_admindpt where staffcode='" + staffcode + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delTask(String staffcode, String orgcode){
		try {
			DBObject db=new DBObject();
			db.run("delete from tbm_admindpt where staffcode='" + staffcode + "' and orgcode='" + orgcode + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
//	public static String getDataGrid(ReviewTask task, Map<String, HashMap<String, String>> data) throws ReviewException{
//		if(task == ReviewTask.INVALID_TASK){
//			throw new ReviewException("选定的时间内不存在考核对象");
//		}
//		// 生成指标树
//		task.parseIndexTree();
//		List<Indexitem> leafIndices = task.getLeafIndices();
//		HashMap<String, HashMap<String, List<String>>> inputstr = new HashMap<String, HashMap<String, List<String>>>();
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			String sql = "select * from tbm_indexscoredetail where objecttype='" + task.getTaskType() +"' and indexcode like '" + 
//					task.getIndexArch().getIndexCode() + "%' and scoreyear='" + task.getDate().getYear() + "' and scoreperiod='" + task.getDate().getPeriodCode() +"'";
//			conn = ConnectionPool.getConnection();
//			st = conn.createStatement();
//			rs = st.executeQuery(sql);
//			while(rs.next()){
//				HashMap<String, List<String>> tmp = inputstr.get(rs.getString("objectcode"));
//				if(tmp == null){
//					tmp = new HashMap<String, List<String>>();
//					inputstr.put(rs.getString("objectcode"), tmp);
//				}
//				String inputindexstr = rs.getString("inputindexstr");
//				if(inputindexstr == null){
//					inputindexstr = "";
//				}
//				tmp.put(rs.getString("indexcode"), Arrays.asList(inputindexstr.split(",")));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(rs, st, conn);
//		}
//		
//		HashMap<String, HashMap<String, HashMap<String, String>>> paravalue = new HashMap<String, HashMap<String, HashMap<String, String>>>();
//		HashMap<String, HashMap<String, IndexPara>> paraname = new HashMap<String, HashMap<String, IndexPara>>();
//		try {
//			String sql = "select * from tbm_indexitempara where indexcode like '" + task.getIndexArch().getIndexCode() + "%'";
//			DBObject db = new DBObject();
//			DataTable dt = db.runSelectQuery(sql);
//			if (dt != null) {
//				for (int i = 0; i < dt.getRowsCount(); i++) {
//					DataRow r = dt.get(i);
//					IndexPara para = new IndexPara();
//					para.setIndexcode(r.getString("indexcode"));
//					para.setParacode(r.getString("paracode"));
//					para.setParaid(r.getString("paraid"));
//					para.setParavaluemode(r.getString("paravaluemode"));
//					
//					HashMap<String, IndexPara> tmp = paraname.get(para.getIndexcode());
//					if(tmp == null){
//						tmp = new HashMap<String, IndexPara>();
//						paraname.put(r.getString("indexcode"), tmp);
//					}
//					tmp.put(r.getString("paraid"), para);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		// 生成数据表列头, frozen列
//		StringBuffer sb = new StringBuffer();
//		sb.append("<table id=\"index_tb\" class=\"easyui-datagrid\" data-options=\"fit:true,singleSelect:true,onClickRow:onClickRow,toolbar:'#tb'\">");
//		sb.append("<thead data-options=\"frozen:true\"><tr>");
//		sb.append("<th data-options=\"field:'pindexcode'\">上级指标</th>");
//		sb.append("<th data-options=\"field:'indexcode'\">采集指标</th>");
//		sb.append("<th data-options=\"field:'indextype'\">指标类别</th>");
//		sb.append("<th data-options=\"field:'scorefunc',width:200\">计分方法</th>");
//		sb.append("<th data-options=\"field:'para'\">参数</th>");
//		sb.append("</tr></thead>");
//		// 生成数据表列头, 活动列
//		sb.append("<thead><tr>");
//		Iterator<ReviewableObj> iter = task.getReviewees().iterator();
//		while(iter.hasNext()){
//			ReviewableObj obj = iter.next();
//			sb.append("<th data-options=\"field:'obj_").append(obj.getCode()).append("',align:'right',editor:'text'\">");
//			sb.append(obj.getName()).append("</th>");
//		}
//		sb.append("</tr></thead><tbody>");
//
//		// 开始填充数据
//		for (Indexitem item : leafIndices) {
//			List<ChildIndex> clist = getchildindex(item);
//			boolean flag = true;
//			int count = 0;
//			for (ChildIndex c : clist) {
//				sb.append("<tr>");
//				if (flag) {
//					StringBuilder tip = new StringBuilder();
//					Indexitem tmpParent = item;
//					while (tmpParent.parent != null) {
//						tip.insert(0, "," + tmpParent.parent.getIndexName());
//						tmpParent = tmpParent.parent;
//					}
//					tip.delete(0, tip.indexOf(",", 1) + 1);
//					sb.append("<td>").append(tip.toString()).append("</td>");
//					sb.append("<td>").append(CodeDictionary.syscode_traslate("tbm_indexitem", "indexcode", "indexname", c.getIndexcode())).append("</td>");
//					sb.append("<td>").append(c.getIndextype()).append("</td>");
//				} else {
//					sb.append("<td>").append("</td>");
//					sb.append("<td>").append("</td>");
//					sb.append("<td>").append("</td>");
//				}
//
//				sb.append("<td>").append(c.getScorcefunc()).append("</td>");
//				if(c.getIndextype().equals("计算函数型")){
//					IndexPara para = paraname.get(c.getIndexcode()).get(c.getPara());
//					String name = paraname.get(c.getIndexcode()).get(c.getPara()).getParacode();
//					if("业务数据".equals(para.getParavaluemode())){
//						IndexBizPara bizpara = IndexDao.getIndexBizPara(para.getParacode());
//						if(bizpara == null){
//							throw new ReviewException("指标项 [" + item.getIndexName() +"] 引用的参数 [" + para.getParacode() + "] 未定义");
//						}
//						
//						Map<String, String> values = getBizParaValue(task.getReviewees(), task.getDate().getPeriodCode(bizpara.getPeriod()), ""+task.getDate().getYear(), bizpara.getCode());
//						iter = task.getReviewees().iterator();
//						while(iter.hasNext()){
//							ReviewableObj obj = iter.next();
//							
//							HashMap<String, HashMap<String, String>> tmp = paravalue.get(obj.getCode());
//							if(tmp == null){
//								tmp = new HashMap<String, HashMap<String, String>>();
//								tmp.put(c.getIndexcode(), new HashMap<String, String>());
//								paravalue.put(obj.getCode(), tmp);
//							}
//							if(values.get(obj.getCode()) == null){
//								throw new ReviewException("尚未给 [" + obj.getName() + "] 引用的参数 [" + bizpara.getName() +"] 赋值", ReviewException.PARA_VALUE_NOT_EXIST);
//							}
//							
//							if(tmp.get(c.getIndexcode()) == null){
//								tmp.put(c.getIndexcode(), new HashMap<String, String>());
//							}
//							tmp.get(c.getIndexcode()).put(c.getPara(), values.get(obj.getCode()).toString());
//						}
//						name = bizpara.getName();
//					}else if("指标得分引用".equals(para.getParavaluemode())){
//						iter = task.getReviewees().iterator();
//						while(iter.hasNext()){
//							ReviewableObj obj = iter.next();
//							ReferObjectIndex refer = IndexDao.getReferObjectIndex(para.getParacode(), obj.getCode() , c.getIndexcode());
//							String value = IndexDao.getIndexValue(obj.getCode(), c.getIndexcode(), "" + task.getDate().getYear(), task.getDate().getPeriodCode());
//							if(value == null){
//								//value = "1.0";
//								throw new ReviewException("值引用错误，引用对象尚未计分");
//							}
//							
//							HashMap<String, HashMap<String, String>> tmp = paravalue.get(obj.getCode());
//							if(tmp == null){
//								tmp = new HashMap<String,  HashMap<String, String>>();
//								tmp.put(c.getIndexcode(), new HashMap<String, String>());
//								paravalue.put(obj.getCode(), tmp);
//							}
//							
//							if(tmp.get(c.getIndexcode()) == null){
//								tmp.put(c.getIndexcode(), new HashMap<String, String>());
//							}
//							tmp.get(c.getIndexcode()).put(c.getPara(), value);
//						}
//					}else if("指标值引用".equals(para.getParavaluemode())){
//						iter = task.getReviewees().iterator();
//						while(iter.hasNext()){
//							ReviewableObj obj = iter.next();
//							
//							ReferObjectIndex refer = IndexDao.getReferObjectIndex(para.getParacode(), obj.getCode() , c.getIndexcode());
//							String score = IndexDao.getIndexScore(obj.getCode(), c.getIndexcode(), ""+task.getDate().getYear(), task.getDate().getPeriodCode());
//							if(score == null){
//								//score = "2.0";
//								throw new ReviewException("得分引用错误，引用对象尚未计分");
//							}
//							HashMap<String, HashMap<String, String>> tmp = paravalue.get(obj.getCode());
//							if(tmp == null){
//								tmp = new HashMap<String,  HashMap<String, String>>();
//								tmp.put(c.getIndexcode(), new HashMap<String, String>());
//								paravalue.put(obj.getCode(), tmp);
//							}
//							if(tmp.get(c.getIndexcode()) == null){
//								tmp.put(c.getIndexcode(), new HashMap<String, String>());
//							}
//							tmp.get(c.getIndexcode()).put(c.getPara(), score);
//						}
//					}
//					
//					sb.append("<td>").append(name).append("</td>");
//				}else{
//					if (!flag) {
//						sb.append("<td>").append("</td>");
//					} else {
//						sb.append("<td>").append(c.getPara()).append("</td>");
//					}
//				}
//
//				iter = task.getReviewees().iterator();
//				while(iter.hasNext()){
//					ReviewableObj obj = iter.next();
//					
//					boolean isValued = false;
//					if(data!=null && !data.isEmpty()){
//						HashMap<String, String> rowdt = data.get(obj.getCode());
//						if(rowdt!=null){
//							sb.append("<td>").append(rowdt.get(c.getIndexcode()).split(",")[count]).append("</td>");
//							isValued = true;
//						}
//					}
//					if(!isValued){
//						if (inputstr.get(obj.getCode()) != null) {
//							HashMap<String, List<String>> rowdt = inputstr.get(obj.getCode());
//							sb.append("<td>").append(rowdt.get(c.getIndexcode()).get(count)).append("</td>");
//						} else {
//							if(paravalue.get(obj.getCode()) == null || paravalue.get(obj.getCode()).get(c.getIndexcode()) == null){
//								sb.append("<td>").append("").append("</td>");
//							}else{
//								sb.append("<td>").append(paravalue.get(obj.getCode()).get(c.getIndexcode()).get(c.getPara())).append("</td>");
//							}
//						}
//					}
//				}
//				flag = false;
//				count++;
//				sb.append("</tr>");
//			}
//		}
//
//		sb.append("</tbody></table>");
//		return sb.toString();
//	}
	
}
