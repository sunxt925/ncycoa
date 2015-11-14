package com.performance;

import java.math.BigDecimal;
import java.util.Map;

public class IndexParameterProvider {
	
	private Map<String, BigDecimal> data;
	
	public boolean containsParameter(String para) {
		return data.containsKey(para);
	}
	
	public BigDecimal getValue(String paraName) {
		return data.get(paraName);
	}
	
}
