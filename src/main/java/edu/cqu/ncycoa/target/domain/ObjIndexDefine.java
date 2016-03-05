package edu.cqu.ncycoa.target.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBM_ObjIndexDefine")
public class ObjIndexDefine {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="StdIndexCode")
	private String StdIndexCode = "";
	
	@Column(name="StdItemName")
	private String StdItemName = "";
	
	@Column(name="AppSystem")  //Ŀ��ϵͳ/��Чϵͳ
	private String AppSystem = "";
	
	@Column(name="PublicFlag")  //����Ϊ1��˽��Ϊ0 
	private String PublicFlag = "";
	
	@Column(name="BelongOrgcode")  //�й�˾	���ع�˾BelongOrgcode
	private String BelongOrgcode = "";
	
	@Column(name="BelongOrgname")  //�й�˾	���ع�˾BelongOrgcode
	private String BelongOrgname = "";
	
	@Column(name="memo")
	private String memo = "";

	public String getStdIndexCode() {
		return StdIndexCode;
	}

	public void setStdIndexCode(String stdIndexCode) {
		StdIndexCode = stdIndexCode;
	}

	public String getStdItemName() {
		return StdItemName;
	}

	public void setStdItemName(String stdItemName) {
		StdItemName = stdItemName;
	}

	public String getAppSystem() {
		return AppSystem;
	}

	public void setAppSystem(String appSystem) {
		AppSystem = appSystem;
	}

	public String getPublicFlag() {
		return PublicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		PublicFlag = publicFlag;
	}

	public String getBelongOrgcode() {
		return BelongOrgcode;
	}

	public void setBelongOrgcode(String belongOrgcode) {
		BelongOrgcode = belongOrgcode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBelongOrgname() {
		return BelongOrgname;
	}

	public void setBelongOrgname(String belongOrgname) {
		BelongOrgname = belongOrgname;
	}
	
	
}
