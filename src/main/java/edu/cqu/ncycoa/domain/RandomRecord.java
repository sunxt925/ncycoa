package edu.cqu.ncycoa.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="NCYCOA_RANDOM_RECORD")
public class RandomRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RANDOM_ID")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RANDOM_TIME")
	private Date randomTime; //抽取时间
	
	@Column(name="PURPOSE")
	private String purpose;//抽取目的
	
	@ElementCollection
	@CollectionTable(name="NCYCOA_RANDOM_DEPARTSLIST", joinColumns=@JoinColumn(name="RANDOM_ID"))
	private List<String> departsList; //候选机构
	
	
	@Column(name="RESULT")
	private String result; //抽取结果 供应商名称
	
	@Column(name="DEPART")
	private String depart; //抽取部门


	public Long getID() {
		return id;
	}


	public void setID(Long iD) {
		id = iD;
	}


	public Date getRandomTime() {
		return randomTime;
	}


	public void setRandomTime(Date randomTime) {
		this.randomTime = randomTime;
	}


	public String getPurpose() {
		return purpose;
	}


	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}


	public List<String> getDepartsList() {
		return departsList;
	}


	public void setDepartsList(List<String> departsList) {
		this.departsList = departsList;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDepart() {
		return depart;
	}


	public void setDepart(String depart) {
		this.depart = depart;
	}
	

	
}
