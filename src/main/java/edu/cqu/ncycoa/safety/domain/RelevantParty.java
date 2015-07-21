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
	private Long id;//���
	
	@Column(name="RELEVANTPARTY_NAME")
	private String relevantPartyName;//��ط�(��λ)
	
	@Column(name="RESPONSIBELPERSON")
	private String responsiblePerson;//��ط�������
	
	@Column(name="CONTENT")
	private String content;//��������
	
	@Column(name="TELEPHONE")
	private String telephone;//��ط���ϵ�绰
	
	@Column(name="ADDRESS")
	private String address;//��ط���ַ
	
	@Column(name="APTITUDE")
	private String aptitude;//�߱�������
	
	@Column(name="PROJECTNAME")
	private String projectname;//��ҵ��Ŀ
	
	@Column(name="JOBPLACE")
	private String jobplace;//��ҵ����
	
	@Column(name="JOBPERSONCOUNT")
	private int jobpersoncount;//�ڱ���λ��ҵ��Ա��
	
	@Column(name="GKORGCODE")
	private String gkorgcode;//����λ��ڹ�����
	
	@Column(name="FILENAME")
	private String fileName;//���׼�¼�ļ�

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
