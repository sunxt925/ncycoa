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
@Table(name="NCYCOA_SUPPLIER_EXIT")
public class SupplierExit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SUPPLIER_ID")
	private Long id;
	
	@Column(name="SUPPLIER_CODE")
	private String code;   // 供货商编码
	
	@Column(name="SUPPLIER_NAME")
	private String name;   // 供货商名称
	
	@Column(name="FOBBIDEN_TIME")
	private Integer fobbidenTime;   // 禁入年限
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXIT_TIME")
	private Date exitTime;     // 退出时间
	
	@Column(name="EXIT_REASON")
	private Short reason; //退出原因 0自行退出 1违规退出 2评价退出


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


	public Date getExitTime() {
		return exitTime;
	}


	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}


	public Short getReason() {
		return reason;
	}


	public void setReason(Short reason) {
		this.reason = reason;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Integer getFobbidenTime() {
		return fobbidenTime;
	}


	public void setFobbidenTime(Integer fobbidenTime) {
		this.fobbidenTime = fobbidenTime;
	}
	

	
}
