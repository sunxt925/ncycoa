package edu.cqu.ncycoa.safety.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SAFE_EMERGENCYPLAN")
public class EmergencyPlan {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EID")
	private Long id;//流水号
	
	@Column(name="ENUMBER")
	private String no;//编号
	
	@Column(name="ENAME")
	private String name;//预案名称
	
	@Column(name="TYPE")
	private Short type;//预案类别  综合_0,专项_1,现场处置_2
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MAKE_TIME")
	private Date makeTime;  // 发布修订时间 预案编制部门
	
	@Column(name="MANAGE_DEPART")
	private String manageDepart;//预案编制部门
	
	@Column(name="FILENAME")
	private String filePath;//文件
	
	@Column(name="MEMO")
	private String memo;//备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getManageDepart() {
		return manageDepart;
	}

	public void setManageDepart(String manageDepart) {
		this.manageDepart = manageDepart;
	}

	
}
