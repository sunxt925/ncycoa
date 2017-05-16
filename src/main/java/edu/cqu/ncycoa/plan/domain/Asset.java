package edu.cqu.ncycoa.plan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="NCYCOA_ASSET")
public class Asset {

	@Id
	@Column(name="ASSET_ID")
	private String id;
	
	@Column(name="FRIENDLY_NAME")
	private String friendlyName;
	
	@Column(name="REAL_NAME")
	private String realName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="EXT_NAME")
	private String extName;
	
	@ManyToOne
	@JoinColumn(name = "TASK_ID")
	private PlanTask task;

	public PlanTask getTask() {
		return task;
	}

	public void setTask(PlanTask task) {
		this.task = task;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}
		
}
