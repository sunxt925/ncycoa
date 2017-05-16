package com.performance;

import java.util.Map;

public class IndexScoreCalculatorImpl extends IndexScoreCalculator {
	
	private IndexDefinition index;
	private Map<IndexWithParameterProvider, IndexScore> scoreCache;
	private Map<IndexWithParameterProvider, IndexValue> valueCache;
	
	private static class IndexWithParameterProvider {
		
		public IndexDefinition getIndex() {
			return index;
		}
		
		public void setIndex(IndexDefinition index) {
			this.index = index;
		}
		
		public IndexParameterProvider getProvider() {
			return provider;
		}
		
		public void setProvider(IndexParameterProvider provider) {
			this.provider = provider;
		}
		
		private IndexDefinition index;
		private IndexParameterProvider provider;
		
	}
	
	public IndexScoreCalculatorImpl(IndexDefinition index) {
		this.index = index;
	}

	public IndexDefinition getIndex() {
		return index;
	}
	
	public void setIndex(IndexDefinition index) {
		this.index = index;
	}

	private double truncate(double low, double high, double cur){
		return cur < low ? low : (cur > high ? high : cur); 
	}

	public IndexValue getValue(IndexParameterProvider para) {
//		double value = 0;
//		IndexValueFuncType valueFuncType = index.getValueFuncType();
//		if(valueFuncType == IndexValueFuncType.INVALID) {
//			throw new ReviewException("指标项 [" + index.getIndexName() + "], 编码 [" + index.getIndexCode() + "] : 无效的值函数类型定义");
//		}
//		//子项汇总型
//		if(valueFuncType == IndexValueFuncType.SUM) {
//			for (IndexResult item : children) {
//				value += item.getScore(item.getValue(para)) * item.index.getUpperSumWeight();
//			}
//		} else {
//			String p = null;
//			for (Object indexname : para.keySet()) {
//				if (index.getIndexName().equals(indexname)) {
//					p = para.getString(indexname.toString());
//					break;
//				}
//			}
//			if (p == null) {
//				throw new ReviewException("未找到指标项[ " + index.getIndexName() + " ]的参数输入");
//			}
//
//			inputStr = p;
//			String[] ps = p.split(",");
//			boolean flag = false;
//			for(int i=0; i<ps.length; i++){
//				if(!StringUtils.isBlank(ps[i])){
//					flag = true;
//				}
//			}
//			
//			if(!flag){
//				return null;
//			}
//			
//			if ("计算函数型".equals(index.getValueComputingType())) {
//				if (index.getNumPara() != ps.length) {
//					throw new ReviewException("指标项[ " + index.getIndexName() + " ]的参数个数 " + index.getNumPara() + "与输入的个数 " + ps.length + "不匹配");
//				}
//				String func = index.getValueFunc();
//				for (int i = 1; i <= ps.length; i++) {
//					if(StringUtils.isBlank(ps[i - 1])){
//						throw new ReviewException("计算指标项[ " + index.getIndexName() + " ]时异常，参数"+ "p" + i + "不能为空");
//					}
//					func = func.replaceAll("p" + i, ps[i - 1]);
//				}
//				ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
//				try {
//					Object eval = se.eval(func);
//					value = Double.parseDouble( eval.toString() );
//				} catch (Exception e) {
//					throw new ReviewException("计算指标项[ " + index.getIndexName() + " ]时异常，值计算公式:\n" + index.getValueFunc());
//				}
//			} else {
//				String func = index.getValueFunc();
//				func = (func.split("="))[1];
//				func = func.substring(1, func.length() - 1);
//				String[] tmps = func.split(",");
//				if (tmps.length != ps.length) {
//					throw new ReviewException("指标项[ " + index.getIndexName() + " ]的枚举项项数 " + tmps.length + " 与输入的项数" + ps.length + "不匹配");
//				}
//
//				ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
//				try {
//					double eval = 0;
//					for (int i = 0; i < ps.length; i++) {
//						Object tmpEval = se.eval( (StringUtils.isBlank(ps[i]) ? "0" : ps[i]) + "*" +  tmps[i] );
//						eval += Double.parseDouble( tmpEval.toString() );
//					}
//					value = (Double) eval;
//				} catch (Exception e) {
//					throw new ReviewException("计算指标项[ " + index.getIndexName() + " ]时异常，值计算公式:\n" + index.getValueFunc());
//				}
//			}
//		} 
//		this.value = value;
//		return this.value;
		
		return null;
	}
	
	public IndexScore getScore(IndexValue value) {
//		if(value == null){
//			this.score = index.getStandardscore();
//			return this.score;
//		}
//		
//		double score = 0.0;
//		if ("一般函数型".equals(index.getSCoreFuncType())) {
//			ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
//			try {
//				String func = index.getScoreFunc();
//				func = func.replaceAll("x", Double.toString(value));
//				Object eval = se.eval(func);
//				score = Double.parseDouble( eval.toString() );
//			} catch (Exception e) {
//				throw new ReviewException("计算指标项[ " + index.getIndexName() + " ]时异常，得分计算公式:\n" + index.getScoreFunc());
//			}
//		} else {
//			Map<String, String> map = new HashMap<String, String>();
//			String funcfake = index.getScoreFunc();//[1,10]=x*2;[11,20]=x*4
//			String[] tmps = funcfake.split(";");//func[0]="[1,10]=x*2";func[1]="[11,20]=x*4"
//			for(int i = 0; i<tmps.length; i++){
//				String[] d = tmps[i].split("=");//tmp[0]="[1,10]" tmp[1]=x*2
//				String condition = d[0].substring(1, d[0].length() - 1);//tmp1="1,10"
//				map.put(condition, d[1]);
//			}
//			
//			String func = null;
//			for(String key : map.keySet()){
//				String[] tmp = key.split(",");
//				if(new Double(tmp[0]) <= value && value <= new Double(tmp[1])){
//					func = map.get(key);
//				}
//			}
//			if(func == null){
//				score = 0;
//			}else{
//				ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
//				try {
//					func = func.replaceAll("x", Double.toString(value));
//					Object eval = se.eval(func);
//					score = Double.parseDouble( eval.toString() );
//				} catch (Exception e) {
//					throw new ReviewException("计算指标项[ " + index.getIndexName() + " ]时异常，得分计算公式:\n" + index.getScoreFunc());
//				}
//			}
//		}
//		
//		score += index.getScoreDefault();
//		score = truncate(index.getScoreSumLow(), index.getScoreSumMax(), score);
//		
//		BigDecimal bd = new BigDecimal(score);
//		bd = bd.setScale(4, java.math.RoundingMode.HALF_UP);
//		this.score = bd.doubleValue();
//		return this.score;
		
		return null;
	}
}
