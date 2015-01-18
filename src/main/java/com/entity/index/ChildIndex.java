package com.entity.index;

public class ChildIndex {

	private String p_indexcode;
	private String indexcode;
	private String scorcefunc;
	private String indextype;
	private String para;
	
	public ChildIndex(String p_indexcode, String indexcode, String scorcefunc,
			String indextype, String para) {
		this.p_indexcode = p_indexcode;
		this.indexcode = indexcode;
		this.scorcefunc = scorcefunc;
		this.indextype = indextype;
		this.para = para;
	}
	public String getP_indexcode() {
		return p_indexcode;
	}
	public void setP_indexcode(String p_indexcode) {
		this.p_indexcode = p_indexcode;
	}
	public String getIndexcode() {
		return indexcode;
	}
	public void setIndexcode(String indexcode) {
		this.indexcode = indexcode;
	}
	public String getScorcefunc() {
		return scorcefunc;
	}
	public void setScorcefunc(String scorcefunc) {
		this.scorcefunc = scorcefunc;
	}
	public String getIndextype() {
		return indextype;
	}
	public void setIndextype(String indextype) {
		this.indextype = indextype;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	
}
