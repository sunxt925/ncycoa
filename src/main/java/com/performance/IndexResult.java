package com.performance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.entity.index.Indexitem;

public class IndexResult {
	private Indexitem index;
	private String inputStr;
	public double value;
	public double score;
	private List<IndexResult> children = new ArrayList<IndexResult>();

	public IndexResult(Indexitem index) {
		this.index = index;
	}

	public Indexitem getIndex() {
		return index;
	}

	public void setIndex(Indexitem index) {
		this.index = index;
	}

	public String getInputStr() {
		return inputStr;
	}

	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}

	public List<IndexResult> getChildren() {
		return children;
	}

	public void setChildren(List<IndexResult> children) {
		this.children = children;
	}

	private double truncate(double low, double high, double cur){
		return cur < low ? low : (cur > high ? high : cur); 
	}
	
	public Double getValue(JSONObject para) throws ReviewException {
		double value = 0;
		if ("���������".equals(index.getValueComputingType())) {
			for (IndexResult item : children) {
				value += item.getScore(item.getValue(para)) * item.index.getUpperSumWeight();
			}
		} else {
			String p = null;
			for (Object indexcode : para.keySet()) {
				if (index.getIndexCode().equals(indexcode)) {
					p = para.getString(indexcode.toString());
					break;
				}
			}
			if (p == null) {
				throw new ReviewException("δ�ҵ�ָ����[ " + index.getIndexName() + " ]�Ĳ�������");
			}

			inputStr = p;
			String[] ps = p.split(",");
			boolean flag = false;
			for(int i=0; i<ps.length; i++){
				if(!StringUtils.isBlank(ps[i])){
					flag = true;
				}
			}
			
			if(!flag){
				return null;
			}
			
			if ("���㺯����".equals(index.getValueComputingType())) {
				if (index.getNumPara() != ps.length) {
					throw new ReviewException("ָ����[ " + index.getIndexName() + " ]�Ĳ������� " + index.getNumPara() + "������ĸ��� " + ps.length + "��ƥ��");
				}
				String func = index.getValueFunc();
				for (int i = 1; i <= ps.length; i++) {
					if(StringUtils.isBlank(ps[i - 1])){
						throw new ReviewException("����ָ����[ " + index.getIndexName() + " ]ʱ�쳣������"+ "p" + i + "����Ϊ��");
					}
					func = func.replaceAll("p" + i, "("+ps[i - 1]+")");
				}
				ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
				try {
					Object eval = se.eval(func);
					value = Double.parseDouble( eval.toString() );
				} catch (Exception e) {
					throw new ReviewException("����ָ����[ " + index.getIndexName() + " ]ʱ�쳣��ֵ���㹫ʽ:\n" + index.getValueFunc());
				}
			} else {
				String func = index.getValueFunc();
				func = (func.split("="))[1];
				func = func.substring(1, func.length() - 1);
				String[] tmps = func.split(",");
				if (tmps.length != ps.length) {
					throw new ReviewException("ָ����[ " + index.getIndexName() + " ]��ö�������� " + tmps.length + " �����������" + ps.length + "��ƥ��");
				}

				ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
				try {
					double eval = 0;
					for (int i = 0; i < ps.length; i++) {
						Object tmpEval = se.eval( (StringUtils.isBlank(ps[i]) ? "0" : ps[i]) + "*" +  tmps[i] );
						eval += Double.parseDouble( tmpEval.toString() );
					}
					value = (Double) eval;
				} catch (Exception e) {
					throw new ReviewException("����ָ����[ " + index.getIndexName() + " ]ʱ�쳣��ֵ���㹫ʽ:\n" + index.getValueFunc());
				}
			}
		} 
		this.value = value;
		return this.value;
	}

	public double getScore(Double value) throws ReviewException {
		if(value == null){
			this.score = index.getStandardscore();
			return this.score;
		}
		
		double score = 0.0;
		if ("һ�㺯����".equals(index.getSCoreFuncType())) {
			ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
			try {
				String func = index.getScoreFunc();
				func = func.replaceAll("x", "("+Double.toString(value)+")");
				Object eval = se.eval(func);
				score = Double.parseDouble( eval.toString() );
			} catch (Exception e) {
				throw new ReviewException("����ָ����[ " + index.getIndexName() + " ]ʱ�쳣���÷ּ��㹫ʽ:\n" + index.getScoreFunc());
			}
		} else {
			Map<String, String> map = new HashMap<String, String>();
			String funcfake = index.getScoreFunc();//[1,10]=x*2;[11,20]=x*4
			String[] tmps = funcfake.split(";");//func[0]="[1,10]=x*2";func[1]="[11,20]=x*4"
			for(int i = 0; i<tmps.length; i++){
				String[] d = tmps[i].split("=");//tmp[0]="[1,10]" tmp[1]=x*2
				String condition = d[0].substring(1, d[0].length() - 1);//tmp1="1,10"
				map.put(condition, d[1]);
			}
			
			String func = null;
			for(String key : map.keySet()){
				String[] tmp = key.split(",");
				if(new Double(tmp[0]) <= value && value <= new Double(tmp[1])){
					func = map.get(key);
				}
			}
			if(func == null){
				score = 0;
			}else{
				ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
				try {
					func = func.replaceAll("x", "("+Double.toString(value)+")");
					Object eval = se.eval(func);
					score = Double.parseDouble( eval.toString() );
				} catch (Exception e) {
					throw new ReviewException("����ָ����[ " + index.getIndexName() + " ]ʱ�쳣���÷ּ��㹫ʽ:\n" + index.getScoreFunc());
				}
			}
		}
		
		score += index.getScoreDefault();
		score = truncate(index.getScoreSumLow(), index.getScoreSumMax(), score);
		
		BigDecimal bd = new BigDecimal(score);
		bd = bd.setScale(4, java.math.RoundingMode.HALF_UP);
		this.score = bd.doubleValue();
		return this.score;
	}
}
