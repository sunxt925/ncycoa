package com.workflow.orm;

public class FlowInfo {

	private String flowid;
	private String flowname;
	private String defineid;
	private String publicstate;
	private String zippath;
	public FlowInfo(){}
	public String getFlowid() {
		return flowid;
	}
	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}
	public String getFlowname() {
		return flowname;
	}
	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}
	public String getDefineid() {
		return defineid;
	}
	public void setDefineid(String defineid) {
		this.defineid = defineid;
	}
	public String getPublicstate() {
		return publicstate;
	}
	public void setPublicstate(String publicstate) {
		this.publicstate = publicstate;
	}
	public String getZippath() {
		return zippath;
	}
	public void setZippath(String zippath) {
		this.zippath = zippath;
	}
}
