package edu.cqu.ncycoa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="NCYCOA_REFORM")
public class Reform implements Cloneable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFORM_ID")
	private Long id;
	
	@Column(name="REFORM_NAME")
	private String name;//整改项目名称
	
	@Column(name="REFORM_XDZGORGCODE")
	private String xdzgOrgcode;//下达整改命令部门
	
	@Column(name="REFORM_HANDLER")
	private String handler;//下达整改人
	
	@Column(name="REFORM_CLORGCODE")
	private String clOrgcode;//处理部门
	
	@Column(name="REFORM_XDDATE")
	private Date xdDate;//下达日期
	
	@Column(name="REFORM_FILENAME")
	private String fileName;
	
	@Column(name="REFORM_MEMO")
	private String memo;//说明
	
	@Column(name="REFORM_FLAG")
	private String flag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXdzgOrgcode() {
		return xdzgOrgcode;
	}

	public void setXdzgOrgcode(String xdzgOrgcode) {
		this.xdzgOrgcode = xdzgOrgcode;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getClOrgcode() {
		return clOrgcode;
	}

	public void setClOrgcode(String clOrgcode) {
		this.clOrgcode = clOrgcode;
	}

	public Date getXdDate() {
		return xdDate;
	}

	public void setXdDate(Date xdDate) {
		this.xdDate = xdDate;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
	
	
}
