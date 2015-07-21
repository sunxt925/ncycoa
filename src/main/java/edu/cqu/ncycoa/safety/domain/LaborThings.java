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
@Table(name="SAFE_LABORTHINGS")
public class LaborThings {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="TYPE")
	private String type;   //劳动保护用品类别
	
	@Column(name="PERFORMANCE_REQUIRE")
	private String performanceRequire ;   // 性能要求
	
	@Column(name="STATUS")
	private String status;  	 // 用品状态
	
	@Column(name="GIVENER")
	private String givener;  // 发放的人员
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GIVEN_TIME")
	private Date givenTime;    // 发放时间
	
	@Column(name="AMOUNT")
	private String amount;     //每人发放的套数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPerformanceRequire() {
		return performanceRequire;
	}

	public void setPerformanceRequire(String performanceRequire) {
		this.performanceRequire = performanceRequire;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGivener() {
		return givener;
	}

	public void setGivener(String givener) {
		this.givener = givener;
	}

	public Date getGivenTime() {
		return givenTime;
	}

	public void setGivenTime(Date givenTime) {
		this.givenTime = givenTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
