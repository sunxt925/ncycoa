package edu.cqu.ncycoa.safety.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SAFE_RELEVANTPARTY")
public class RelevantParty {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RELEVANTPARTY_ID")
	private Long id;//编号
	
	@Column(name="RELEVANTPARTY_NAME")
	private String relevantPartyName;//相关方(单位)
	
	@Column(name="RESPONSIBELPERSON")
	private String responsiblePerson;//相关方负责人
	
	@Column(name="CONTENT")
	private String content;//从事内容
	
	@Column(name="TELEPHONE")
	private String telephone;//相关方联系电话
	
	@Column(name="ADDRESS")
	private String address;//相关方地址
	
	@Column(name="APTITUDE")
	private String aptitude;//具备的资质
	
	@Column(name="PROJECTNAME")
	private String projectname;//作业项目
	
	@Column(name="JOBPLACE")
	private String jobplace;//作业区域
	
	@Column(name="JOBPERSONCOUNT")
	private int jobpersoncount;//在本单位作业人员数
	
	@Column(name="GKORGCODE")
	private String gkorgcode;//本单位归口管理部门
	
	@Column(name="FILENAME")
	private String fileName;//交底记录文件

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRelevantPartyName() {
		return relevantPartyName;
	}

	public void setRelevantPartyName(String relevantPartyName) {
		this.relevantPartyName = relevantPartyName;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAptitude() {
		return aptitude;
	}

	public void setAptitude(String aptitude) {
		this.aptitude = aptitude;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getJobplace() {
		return jobplace;
	}

	public void setJobplace(String jobplace) {
		this.jobplace = jobplace;
	}

	public int getJobpersoncount() {
		return jobpersoncount;
	}

	public void setJobpersoncount(int jobpersoncount) {
		this.jobpersoncount = jobpersoncount;
	}

	public String getGkorgcode() {
		return gkorgcode;
	}

	public void setGkorgcode(String gkorgcode) {
		this.gkorgcode = gkorgcode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
