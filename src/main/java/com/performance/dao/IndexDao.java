package com.performance.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.index.Indexitem;
import com.entity.index.ReferObjectIndex;
import com.performance.IndexBizPara;

public class IndexDao
{
	public static Indexitem getIndex(String indexcode){
		Indexitem indexitem = null;
		try {
			String sql = "select * from tbm_indexitem where indexcode='" + indexcode + "'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					indexitem = new Indexitem();
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return indexitem;
	}
	
	public static Indexitem getIndexTree(String indexcode){
		List<Indexitem> indexlist = new ArrayList<Indexitem>();
		Indexitem arch = null;
		try {
			String sql = "select * from tbm_indexitem where indexcode like '" + indexcode + "%' order by indexcode";
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

					if (indexitem.getIndexCode().equals(indexcode)) {
						arch = indexitem;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(arch == null) return null;

		List<Indexitem> indexItems = new ArrayList<Indexitem>();
		Queue<Indexitem> q = new LinkedList<Indexitem>();
		q.offer(arch);
		while (q.peek() != null) {
			Indexitem parent = q.remove();
			for (int i = 0; i < indexlist.size(); i++) {
				Indexitem item = indexlist.get(i);
				if (item.getParentIndexCode().equals(parent.getIndexCode())) {
					parent.children.add(item);
					item.parent = parent;
					if (item.getIsParent().equals("1")) {
						q.offer(item);
					} else {
						indexItems.add(item);
					}
				}
			}
		}
		
		java.util.Collections.sort(indexItems, new Comparator<Indexitem>(){
			public int compare(Indexitem o1, Indexitem o2) {
				return o1.getIndexCode().compareTo(o2.getIndexCode());
			}
		});
		
		return arch;
	}
	
	public static List<Indexitem> getIndexList(String indexcode){
		List<Indexitem> indexlist = new ArrayList<Indexitem>();
		try {
			String sql = "select * from tbm_indexitem where indexcode like '" + indexcode + "%' order by indexcode";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return indexlist;
	}
	
	public static List<Indexitem> getIndexLeafList(String indexcode){
		List<Indexitem> indexlist = new ArrayList<Indexitem>();
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return indexlist;
	}

	public static List<Indexitem> getIndexLeafAndTree(String indexcode){
		List<Indexitem> indexlist = new ArrayList<Indexitem>();
		Indexitem arch = null;
		try {
			String sql = "select * from tbm_indexitem where indexcode like '" + indexcode + "%' order by indexcode";
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

					if (indexitem.getIndexCode().equals(indexcode)) {
						arch = indexitem;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Indexitem> indexItems = new ArrayList<Indexitem>();
		if(arch == null) return indexItems;

		Queue<Indexitem> q = new LinkedList<Indexitem>();
		q.offer(arch);
		while (q.peek() != null) {
			Indexitem parent = q.remove();
			for (int i = 0; i < indexlist.size(); i++) {
				Indexitem item = indexlist.get(i);
				if (item.getParentIndexCode().equals(parent.getIndexCode())) {
					parent.children.add(item);
					item.parent = parent;
					if (item.getIsParent().equals("1")) {
						q.offer(item);
					} else {
						indexItems.add(item);
					}
				}
			}
		}
		
		java.util.Collections.sort(indexItems, new Comparator<Indexitem>(){
			public int compare(Indexitem o1, Indexitem o2) {
				return o1.getIndexCode().compareTo(o2.getIndexCode());
			}
		});
		
		return indexItems;
	}

	public static IndexBizPara getIndexBizPara(String paracode){
		IndexBizPara bizpara = null;
		try {
			String sql = "select * from tbm_referpara where paracode='" + paracode + "'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					bizpara = new IndexBizPara();
					bizpara.setCode(r.getString("paracode"));
					bizpara.setName(r.getString("paraname"));
					bizpara.setPeriod(r.getString("paraperiod"));
					bizpara.setDefaultValue(Integer.parseInt(r.getString("defaultvalue")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bizpara;
	}

	public static ReferObjectIndex getReferObjectIndex(String paracode, String objectcode, String indexcode){
		ReferObjectIndex refer = null;
		try {
			String sql = "select * from tbm_referobjectindex where paracode='" + paracode + "' and objectcode='" + objectcode + "' and indexcode='" + indexcode + "'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					refer = new ReferObjectIndex();
					refer.setIndexcode(indexcode);
					refer.setObjectcode(objectcode);
					refer.setParacode(paracode);
					refer.setRefindexcode(r.getString("refindexcode"));
					refer.setRefmode(r.getString("refmode"));
					refer.setRefobjectcode(r.getString("refobjectcode"));
					refer.setRefobjecttype(r.getString("refobjecttype"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return refer;
	}
	
	public static String getIndexValue(String obj, String indexcode, String year, String period){
		String value = null;
		try {
			String sql = "select * from tbm_indexscoredetail where objectcode='" + obj + "' and indexcode='" + indexcode + "' and scoreyear='" + year + "' and scoreperiod='" + period +"'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					
					value = r.getString("indexvalue");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static String getIndexScore(String obj, String indexcode, String year, String period){
		String score = null;
		try {
			String sql = "select * from tbm_indexscoredetail where objectcode='" + obj + "' and indexcode='" + indexcode + "' and scoreyear='" + year + "' and scoreperiod='" + period +"'";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					
					score = r.getString("scorevalue");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}
}
