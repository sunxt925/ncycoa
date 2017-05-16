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
	private String code;   // �����̱���
	
	@Column(name="SUPPLIER_NAME")
	private String name;   // ����������
	
	@Column(name="FOBBIDEN_TIME")
	private Integer fobbidenTime;   // ��������
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXIT_TIME")
	private Date exitTime;     // �˳�ʱ��
	
	@Column(name="EXIT_REASON")
	private Short reason; //�˳�ԭ�� 0�����˳� 1Υ���˳� 2�����˳�


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
