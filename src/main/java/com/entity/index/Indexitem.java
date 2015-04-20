package com.entity.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

public class Indexitem implements Comparable<Indexitem> {

	private String IndexCode = "";
	private String UniIndexCode = "";
	private String IndexName = "";
	private String IndexDesc = "";
	private int IndexCountLimit;
	private String ArchCode = "";
	private String IsParent = "";
	private String ParentIndexCode = "";
	private String ValueComputingType = "";
	private String ValueScoreSourceType = "";
	private int NumPara;
	private String ValueFunc = "";
	private String ValueUnit = "";
	private String SCoreFuncType = "";
	private String ScoreFunc = "";
	private int IsRequired;
	private String ScorePeriod = "";
	private double ScoreDefault;
	private double ScoreSumLow;
	private double ScoreSumMax;
	private double UpperSumWeight;
	private double standardscore;
	private Date ValidBeginDate;
	private Date ValidEndDate;
	private int Enabled;
	private String indexorder;
	private String Memo = "";

	public Indexitem parent;
	public List<Indexitem> children = new ArrayList<Indexitem>();

	public int compareTo(Indexitem o) {
		if (StringUtils.isBlank(this.indexorder) || StringUtils.isBlank(o.indexorder)) {
			return this.IndexCode.compareTo(o.IndexCode);
		} else {
			return this.indexorder.compareTo(o.indexorder);
		}
	}

	public Indexitem() {
	}

	public Indexitem(DataRow r) {
		init(r);
	}

	private void init(DataRow r) {
		try {
			IndexCode = r.getString("indexcode");
			UniIndexCode = r.getString("uniindexcode");
			IndexName = r.getString("indexname");
			IndexDesc = r.getString("indexdesc");
			IndexCountLimit = Integer.parseInt(Format.NullToZero(r.getString("indexcountlimit")));
			ArchCode = r.getString("archcode");
			IsParent = r.getString("isparent");
			ParentIndexCode = r.getString("parentindexcode");
			ValueComputingType = r.getString("valuecomputingtype");
			ValueScoreSourceType = r.getString("valuescoresourcetype");
			NumPara = Integer.parseInt(Format.NullToZero(r.getString("numpara")));
			ValueFunc = r.getString("valuefunc");
			ValueUnit = r.getString("valueunit");
			SCoreFuncType = r.getString("scorefunctype");
			ScoreFunc = r.getString("scorefunc");
			IsRequired = Integer.parseInt(Format.NullToZero(r.getString("isrequired")));
			ScorePeriod = r.getString("scoreperiod");
			ScoreDefault = Double.parseDouble(Format.NullToZero(r.getString("scoredefault")));
			ScoreSumLow = Double.parseDouble(Format.NullToZero(r.getString("scoresumLow")));
			ScoreSumMax = Double.parseDouble(Format.NullToZero(r.getString("scoresummax")));
			UpperSumWeight = Double.parseDouble(Format.NullToZero(r.getString("uppersumweight")));
			standardscore = Double.parseDouble(Format.NullToZero(r.getString("standardscore")));
			ValidBeginDate = Format.strToDate(r.getString("validbegindate"));
			ValidEndDate = Format.strToDate(r.getString("validenddate"));
			Enabled = Integer.parseInt(Format.NullToZero(r.getString("enabled")));
			indexorder = r.getString("indexorder");
			Memo = r.getString("memo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Indexitem(String indexcode) {
		try {
			String sql = "select * from tbm_indexitem where indexcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(indexcode) };
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1) {
				DataRow r = dt.get(0);
				init(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean delete(String indexcode) {
		try {
			String sql = "delete from tbm_indexitem where indexcode like '" + indexcode + "%'";
			DBObject db = new DBObject();
			return db.run(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean update(String indexcode, String startDate, String endDate) {
		try {
			String sql = "update tbm_indexitem set  validbegindate=" + "to_date('" + startDate + "','yyyy-mm-dd')" + ",validenddate=" + "to_date('" + endDate
					+ "','yyyy-mm-dd')" + " where indexcode like '" + indexcode + "%'";
			DBObject db = new DBObject();
			return db.run(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public DataTable getIndexList(String para1, String para2, int pageno, int perpage) {
		try {
			DBObject db = new DBObject();
			if (para1.equals("A")) {
				para1 = "";
			}
			String orderway = "";
			if (para2.equals("-1")) {
				orderway = "indexorder";
			} else {
				orderway = "indexcode";
			}
			String base_sql = "select  '选择' as 选择 ,indexcode, uniindexcode, indexname as 编码名称, indexdesc as 编码描述, indexcountlimit  as 指标使用限制,"
					+ "archcode as 指标类,isparent as 是否父指标 , parentindexcode as 父指标编码, valuecomputingtype as 编码值计算类型,"
					+ "numpara as 参数个数, valuefunc as 指标值计算公式,  valueunit as 指标值度量单位,scorefunctype,  scorefunc as 得分函数,  isrequired,"
					+ "scoreperiod as 计分周期,  scoredefault,  scoresumLow, scoresumMax,  uppersumweight,  validbegindate as 指标开始使用日期,"
					+ "validenddate as 指标结束使用日期,  enabled, standardscore as 标准分值,indexorder as 排序, memo, '操作' as 操作    "
					+ "from (select * from tbm_indexitem) where indexcode like '" + para1 + "%'  and parentindexcode='" + para2 + "'  order by " + orderway
					+ "";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);

			return db.runSelectQuery(sql_run);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public DataTable getIndexAllList(String para1, String para2) {
		try {
			DBObject db = new DBObject();
			if (para1.equals("A")) {
				para1 = "";
			}
			String orderway = "";
			if (para2.equals("-1")) {
				orderway = "indexorder";
			} else {
				orderway = "indexcode";
			}
			String base_sql = "select * from tbm_indexitem where indexcode like '" + para1 + "%'  and parentindexcode='" + para2 + "' order by " + orderway
					+ "";

			return db.runSelectQuery(base_sql);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getIndexList2(String para1, String para2, int pageno, int perpage) {
		try {
			DBObject db = new DBObject();

			String base_sql = "select  '选择' as 选择 ,indexcode, uniindexcode, indexname as 编码名称, indexdesc as 编码描述, indexcountlimit  as 指标使用限制,"
					+ "archcode as 指标类,isparent as 是否父指标 , parentindexcode as 父指标编码, valuecomputingtype as 编码值计算类型,"
					+ "numpara as 参数个数, valuefunc as 指标值计算公式,  valueunit as 指标值度量单位,"
					+ "scorefunctype,  scorefunc as 得分函数,  isrequired,scoreperiod as 计分周期,  scoredefault,  scoresumLow, scoresumMax,  uppersumweight,  validbegindate as 指标开始使用日期,"
					+ "validenddate as 指标结束使用日期,  enabled,  memo, '操作' as 操作    from (select * from tbm_indexitem order by indexcode) where indexcode like '"
					+ para1 + ".%.%'  and isparent='" + para2 + "'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);

			return db.runSelectQuery(sql_run);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public DataTable getIndexAllList2(String para1, String para2) {
		try {
			DBObject db = new DBObject();
			if (para1.equals("A")) {
				para1 = "";
			}
			String base_sql = "select * from tbm_indexitem where indexcode like '" + para1 + ".%.%'  and isparent='" + para2 + "'";

			return db.runSelectQuery(base_sql);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getrootList(String index_class) {
		try {
			DBObject db = new DBObject();

			String base_sql = "select * from tbm_indexitem where indexcode like '" + index_class + "%'  and parentindexcode='-1' order by indexorder";

			return db.runSelectQuery(base_sql);

		} catch (Exception e) {
			return null;
		}
	}

	public DataTable getReferIndexList(String indexclass) {
		try {
			// Map<String, String> map=new HashMap<String, String>();
			DBObject dbObject = new DBObject();

			String sql = "select  distinct a.* from  (select * from tbm_indexitem where indexcode like '" + indexclass
					+ "%' and length(indexcode)=3 ) a,tbm_indexitempara b where substr(b.indexcode,1,3)=a.indexcode and substr(b.paravaluemode,-2,2)='引用'";
			return dbObject.runSelectQuery(sql);
		} catch (Exception e) {
			return null;
		}
	}

	public DataTable getIndexitemList(String index_class, int pageno, int perpage) {
		try {
			DBObject db = new DBObject();
			String base_sql = "select  '选择' as 选择 ,indexcode, indexname as 编码名称, paracode as 参数编码,paravaluemode,paravaluemode as 引用模式 ,paraperiod as 参数周期,memo, '操作' as 操作    "
					+ "from (select a.indexname,b.* from tbm_indexitem a,(select * from tbm_indexitempara where indexcode like '"
					+ index_class
					+ "%' and substr(paravaluemode,-2,2)='引用') b where a.indexcode=b.indexcode)";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);

			return db.runSelectQuery(sql_run);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public DataTable getIndexAllitemList(String index_class) {
		try {
			DBObject db = new DBObject();

			String base_sql = "select a.indexname,b.* from tbm_indexitem a,(select * from tbm_indexitempara where indexcode like '" + index_class
					+ "%' and substr(paravaluemode,-2,2)='引用') b where a.indexcode=b.indexcode";

			return db.runSelectQuery(base_sql);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private int count = 0;

	public String getrefindexjson(String objectcode) {
		try {
			IndexArchUser indexArchUser = new IndexArchUser();
			List<String> indexcodes = indexArchUser.getIndexcodeByObjectcode(objectcode);
			if (indexcodes.size() == 0) {
				return "[]";
			}
			DBObject db = new DBObject();
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("[");
			for (String indexcode : indexcodes) {
				String sql = "select * from tbm_indexitem where indexcode like '" + indexcode + "%' order by indexcode";
				DataTable dt = db.runSelectQuery(sql);
				if (dt != null && dt.getRowsCount() >= 0) {
					for (int i = 0; i < dt.getRowsCount(); i++) {
						DataRow r = dt.get(i);
						String ccm = r.getString("indexcode");
						String index_name = r.getString("indexname");
						String childindexString = getRefChild(dt, ccm);
						sBuilder.append("{");
						sBuilder.append(" \"indexcode\":").append("\"" + Format.replacePointtoOth(ccm, "n") + "\"").append(",");
						if (childindexString.length() != 0) {
							sBuilder.append(" \"indexname\":").append("\"" + index_name + "\"").append(",");
							sBuilder.append(" \"children\":").append("[");
							sBuilder.append(childindexString);
							sBuilder.append("]");
						} else {
							sBuilder.append(" \"indexname\":").append("\"" + index_name + "\"");
						}

						sBuilder.append("}").append(",");
						i = count;
					}
					sBuilder.delete(sBuilder.length() - 1, sBuilder.length());

				}
				sBuilder.append(",");
			}

			sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
			sBuilder.append("]");
			return sBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getRefChild(DataTable dt, String pccm) {
		try {
			StringBuilder sbBuilder = new StringBuilder();

			if (dt != null && dt.getRowsCount() > 0) {

				for (int i = count; i < dt.getRowsCount(); i++) {

					if (dt.get(i).getString("parentindexcode").equals(pccm)) {
						count++;
						DataRow r = dt.get(i);
						String ccm = r.getString("indexcode");
						String index_name = r.getString("indexname");
						sbBuilder.append("{");
						sbBuilder.append(" \"indexcode\":").append("\"" + Format.replacePointtoOth(ccm, "n") + "\"").append(",");
						String childindexString = getRefChild(dt, ccm);
						if (childindexString.length() != 0) {
							sbBuilder.append(" \"indexname\":").append("\"" + index_name + "\"").append(",");
							sbBuilder.append(" \"children\":").append("[");
							sbBuilder.append(childindexString);
							sbBuilder.append("]");
						} else {
							sbBuilder.append(" \"indexname\":").append("\"" + index_name + "\"");
						}
						sbBuilder.append("}").append(",");
					}

				}
				sbBuilder.delete(sbBuilder.length() - 1, sbBuilder.length());

			}
			return sbBuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public String getindexjson() {
		try {

			StringBuilder sBuilder = new StringBuilder();

			String[] strings = { "C", "D", "S" };
			Map<String, String> map = new HashMap<String, String>();
			map.put("C", "公司");
			map.put("D", "部门");
			map.put("S", "个人");
			sBuilder.append("[");

			for (String s : strings) {
				sBuilder.append("{");
				sBuilder.append("\"indexcode\":").append("\"" + "" + "\"").append(",");
				sBuilder.append("\"indexname\":").append("\"" + map.get(s) + "\"").append(",");
				sBuilder.append("\"children\":[");
				sBuilder.append(getChildindex(s));
				sBuilder.append("]},");
			}
			sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
			sBuilder.append("]");
			return sBuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public String getChildindex(String c) {
		try {
			DBObject db = new DBObject();

			String sql = "select  distinct a.* from  (select * from tbm_indexitem where length(indexcode)=3 and indexcode like '" + c
					+ "%' order by indexcode ) a,tbm_indexitempara b where substr(b.indexcode,1,3)=a.indexcode and b.paravaluemode='业务数据'";
			DataTable dt = db.runSelectQuery(sql);
			StringBuilder sBuilder = new StringBuilder();
			if (dt != null && dt.getRowsCount() >= 0) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					sBuilder.append("{");
					sBuilder.append("\"indexcode\":").append("\"" + r.getString("indexcode") + "\"").append(",");
					sBuilder.append("\"indexname\":").append("\"" + r.getString("indexname") + "\"");
					sBuilder.append("},");
				}
				sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
			}
			return sBuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public String getindexjsonBytype(String index_class) {
		try {
			DBObject db = new DBObject();

			String sql = "select * from tbm_indexitem where length(indexcode)=3 and indexcode like '" + index_class + "%' order by indexorder ";
			DataTable dt = db.runSelectQuery(sql);
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("[");
			if (dt != null && dt.getRowsCount() >= 0) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					sBuilder.append("{");
					sBuilder.append("\"indexcode\":").append("\"" + r.getString("indexcode") + "\"").append(",");
					sBuilder.append("\"indexname\":").append("\"" + r.getString("indexname") + "\"");
					sBuilder.append("},");
				}
				sBuilder.delete(sBuilder.length() - 1, sBuilder.length());

			}
			sBuilder.append("]");
			return sBuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static List<IndexitemPara> getparainfo(String index_code) {
		try {
			DBObject db = new DBObject();
			String sql = "select * from tbm_indexitempara where indexcode='" + index_code + "' order by paraid";
			DataTable dt = db.runSelectQuery(sql);
			List<IndexitemPara> indexitemParas = new ArrayList<IndexitemPara>();
			if (dt != null && dt.getRowsCount() >= 0) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					IndexitemPara indexitemPara = new IndexitemPara();
					indexitemPara.setIndexcode(r.getString("indexcode"));
					indexitemPara.setParacode(r.getString("paracode"));
					indexitemPara.setParaid(r.getString("paraid"));
					indexitemPara.setParavaluemode(r.getString("paravaluemode"));
					indexitemParas.add(indexitemPara);
				}

			}
			return indexitemParas;
		} catch (Exception e) {
			return null;
		}
	}

	public String getIndexCode() {
		return IndexCode;
	}

	public void setIndexCode(String indexCode) {
		IndexCode = indexCode;
	}

	public String getUniIndexCode() {
		return UniIndexCode;
	}

	public void setUniIndexCode(String uniIndexCode) {
		UniIndexCode = uniIndexCode;
	}

	public String getIndexName() {
		return IndexName;
	}

	public void setIndexName(String indexName) {
		IndexName = indexName;
	}

	public String getIndexDesc() {
		return IndexDesc;
	}

	public void setIndexDesc(String indexDesc) {
		IndexDesc = indexDesc;
	}

	public int getIndexCountLimit() {
		return IndexCountLimit;
	}

	public void setIndexCountLimit(int indexCountLimit) {
		IndexCountLimit = indexCountLimit;
	}

	public String getArchCode() {
		return ArchCode;
	}

	public void setArchCode(String archCode) {
		ArchCode = archCode;
	}

	public String getIsParent() {
		return IsParent;
	}

	public void setIsParent(String isParent) {
		IsParent = isParent;
	}

	public String getParentIndexCode() {
		return ParentIndexCode;
	}

	public void setParentIndexCode(String parentIndexCode) {
		ParentIndexCode = parentIndexCode;
	}

	public String getValueComputingType() {
		return ValueComputingType;
	}

	public void setValueComputingType(String valueComputingType) {
		ValueComputingType = valueComputingType;
	}

	public String getValueScoreSourceType() {
		return ValueScoreSourceType;
	}

	public void setValueScoreSourceType(String valueScoreSourceType) {
		ValueScoreSourceType = valueScoreSourceType;
	}

	public int getNumPara() {
		return NumPara;
	}

	public void setNumPara(int numPara) {
		NumPara = numPara;
	}

	public String getValueFunc() {
		return ValueFunc;
	}

	public void setValueFunc(String valueFunc) {
		ValueFunc = valueFunc;
	}

	public String getValueUnit() {
		return ValueUnit;
	}

	public void setValueUnit(String valueUnit) {
		ValueUnit = valueUnit;
	}

	public String getSCoreFuncType() {
		return SCoreFuncType;
	}

	public void setSCoreFuncType(String sCoreFuncType) {
		SCoreFuncType = sCoreFuncType;
	}

	public String getScoreFunc() {
		return ScoreFunc;
	}

	public void setScoreFunc(String scoreFunc) {
		ScoreFunc = scoreFunc;
	}

	public int getIsRequired() {
		return IsRequired;
	}

	public void setIsRequired(int isRequired) {
		IsRequired = isRequired;
	}

	public String getScorePeriod() {
		return ScorePeriod;
	}

	public void setScorePeriod(String scorePeriod) {
		ScorePeriod = scorePeriod;
	}

	public double getScoreDefault() {
		return ScoreDefault;
	}

	public void setScoreDefault(double scoreDefault) {
		ScoreDefault = scoreDefault;
	}

	public double getScoreSumLow() {
		return ScoreSumLow;
	}

	public void setScoreSumLow(double scoreSumLow) {
		ScoreSumLow = scoreSumLow;
	}

	public double getScoreSumMax() {
		return ScoreSumMax;
	}

	public void setScoreSumMax(double scoreSumMax) {
		ScoreSumMax = scoreSumMax;
	}

	public double getUpperSumWeight() {
		return UpperSumWeight;
	}

	public void setUpperSumWeight(double upperSumWeight) {
		UpperSumWeight = upperSumWeight;
	}

	public double getStandardscore() {
		return standardscore;
	}

	public void setStandardscore(double standardscore) {
		this.standardscore = standardscore;
	}

	public Date getValidBeginDate() {
		return ValidBeginDate;
	}

	public void setValidBeginDate(Date validBeginDate) {
		ValidBeginDate = validBeginDate;
	}

	public Date getValidEndDate() {
		return ValidEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		ValidEndDate = validEndDate;
	}

	public int getEnabled() {
		return Enabled;
	}

	public void setEnabled(int enabled) {
		Enabled = enabled;
	}

	public String getIndexorder() {
		return indexorder;
	}

	public void setIndexorder(String indexorder) {
		this.indexorder = indexorder;
	}

	public String getMemo() {
		return Memo;
	}

	public void setMemo(String memo) {
		Memo = memo;
	}

}
