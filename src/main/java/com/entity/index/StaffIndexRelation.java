package com.entity.index;


public class StaffIndexRelation implements Comparable<StaffIndexRelation>{

	private String staffcode;
	
	private String staffname;
	
	private String orgcode;
	
	private String orgname;
	
	private String indexcode;

	public String getStaffcode() {
		return staffcode;
	}

	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getIndexcode() {
		return indexcode;
	}

	public void setIndexcode(String indexcode) {
		this.indexcode = indexcode;
	}

	@Override
	public int compareTo(StaffIndexRelation o) {
		return this.orgcode.compareTo(o.orgcode);
	}
	
	
}
