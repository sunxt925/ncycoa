package com.workflow.orm;

import java.util.Date;

public class InstanceInfo {

	private String instanceid;
	private String instancename;
	private String initstaffcode;
	private String initstaffname;
	private String initdate;
	private String png;
	public InstanceInfo(){
		
	}
	public InstanceInfo(String instanceid,String initstaffcode,String instancename,String initstaffname,String initdate,String png){
		this.instanceid=instanceid;
		this.setInstancename(instancename);
		this.initstaffcode=initstaffcode;
		this.initstaffname=initstaffname;
		this.initdate=initdate;
		this.png=png;
	}
	public String getInstanceid() {
		return instanceid;
	}
	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid;
	}
	public String getInitstaffcode() {
		return initstaffcode;
	}
	public void setInitstaffcode(String initstaffcode) {
		this.initstaffcode = initstaffcode;
	}
	public String getInitstaffname() {
		return initstaffname;
	}
	public void setInitstaffname(String initstaffname) {
		this.initstaffname = initstaffname;
	}
	public String getInitdate() {
		return initdate;
	}
	public void setInitdate(String initdate) {
		this.initdate = initdate;
	}
	public void setPng(String png) {
		this.png = png;
	}
	public String getPng() {
		return png;
	}
	public void setInstancename(String instancename) {
		this.instancename = instancename;
	}
	public String getInstancename() {
		return instancename;
	}
}
