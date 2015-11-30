package com.entity.system;

import java.util.ArrayList;
import java.util.List;

public class GoodsInQuery {
	private String inNo;
	private String goodsName;
	private String goodsStyle;
	private String goodsNumber;
	private String remainNumber;
	private String inputdate;
	private String inDate;
	private String auditorCode;
	private String memo;
	

	public static List<GoodsInQuery> getGoodsInQuerys(List<Goodsinstoreitem> goodsinstoreitems){
		List<GoodsInQuery> goodsInQuerys = new ArrayList<GoodsInQuery>();
		int i=1;
		for(Goodsinstoreitem goodsinstoreitem : goodsinstoreitems){
			GoodsInQuery goodsInQuery = new GoodsInQuery();
			goodsInQuery.setInNo(i+"");
			goodsInQuery.setGoodsName(goodsinstoreitem.getGOODSNAME());
			goodsInQuery.setGoodsStyle(goodsinstoreitem.getGOODSSTYLE());
			goodsInQuery.setGoodsNumber(goodsinstoreitem.getGOODSNUMBER());
			goodsInQuery.setRemainNumber(new GoodsStoreInfo(goodsinstoreitem.getGOODSCODE()).getAvailableNumber());
			goodsInQuery.setInDate(goodsinstoreitem.getINDATE());
			goodsInQuery.setInputdate(goodsinstoreitem.getINPUTDATE());
			goodsInQuery.setMemo(goodsinstoreitem.getMEMO());
			goodsInQuery.setAuditorCode(goodsinstoreitem.getAUDITORCODE());
			goodsInQuerys.add(goodsInQuery);
			i++;
		}
		return goodsInQuerys;
	}
	
	public String getInNo() {
		return inNo;
	}
	public void setInNo(String inNo) {
		this.inNo = inNo;
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
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getAuditorCode() {
		return auditorCode;
	}
	public void setAuditorCode(String auditorCode) {
		this.auditorCode = auditorCode;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
}
