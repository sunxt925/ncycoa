package com.entity.system;

import java.util.ArrayList;
import java.util.List;

import com.common.CodeDictionary;

public class GoodsOutQuery {
	private String outNo;
	private String goodsName;
	private String goodsStyle;
	private String goodsNumber;
	private String remainNumber;
	private String inputdate;
	private String outDate;
	private String handler;
	private String memo;
	
	public static List<GoodsOutQuery> getGoodsOutQuerys(List<Goodsoutstoreitem> goodsoutstoreitems){
		List<GoodsOutQuery> goodsOutQuerys = new ArrayList<GoodsOutQuery>();
		int i=1;
		for(Goodsoutstoreitem goodsoutstoreitem : goodsoutstoreitems){
			GoodsOutQuery goodsOutQuery = new GoodsOutQuery();
			goodsOutQuery.setOutNo(i+"");
			goodsOutQuery.setGoodsName(goodsoutstoreitem.getGOODSNAME());
			goodsOutQuery.setGoodsStyle(goodsoutstoreitem.getGOODSSTYLE());
			goodsOutQuery.setGoodsNumber(goodsoutstoreitem.getGOODSNUMBER());
			goodsOutQuery.setRemainNumber(new GoodsStoreInfo(goodsoutstoreitem.getGOODSCODE()).getAvailableNumber());
			goodsOutQuery.setOutDate(goodsoutstoreitem.getOUTDATE());
			goodsOutQuery.setInputdate(goodsoutstoreitem.getINPUTDATE());
			goodsOutQuery.setMemo(goodsoutstoreitem.getMEMO());
			goodsOutQuery.setHandler(CodeDictionary.syscode_traslate("base_staff", "staffcode", "staffname", goodsoutstoreitem.getHANDLER()));
			goodsOutQuerys.add(goodsOutQuery);
			i++;
		}
		return goodsOutQuerys;
	}

	public String getOutNo() {
		return outNo;
	}

	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsStyle() {
		return goodsStyle;
	}

	public void setGoodsStyle(String goodsStyle) {
		this.goodsStyle = goodsStyle;
	}

	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public String getRemainNumber() {
		return remainNumber;
	}

	public void setRemainNumber(String remainNumber) {
		this.remainNumber = remainNumber;
	}

	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
	
}
