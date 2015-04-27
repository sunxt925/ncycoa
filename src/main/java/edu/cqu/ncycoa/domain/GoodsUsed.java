package edu.cqu.ncycoa.domain;

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
@Table(name="NCYCOA_GOODSUSED")
public class GoodsUsed {
  //物资使用台帐记录表
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USED_ID")  //使用编号
	private Long id;
	
	@Column(name="USED_SUPPLIER")
	private String supplier;  	 // 供应商名称
	
	
	@Column(name="USED_GOODSTYPE")
	private String usedGoods;  	 // 采购物资种类
	
	@Column(name="USED_DEPART")
	private String usedDepart;  // 归口部门
	
	@Column(name="USED_MONEY")
	private String usedMoney;  // 采购金额
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="USED_TIME")
	private Date usedTime;     // 使用时间
	
	@Column(name="USED_COUNT")
	private String usedCount;     // 采购次数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsedGoods() {
		return usedGoods;
	}

	public void setUsedGoods(String usedGoods) {
		this.usedGoods = usedGoods;
	}

	public String getUsedDepart() {
		return usedDepart;
	}

	public void setUsedDepart(String usedDepart) {
		this.usedDepart = usedDepart;
	}

	public Date getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getUsedMoney() {
		return usedMoney;
	}

	public void setUsedMoney(String usedMoney) {
		this.usedMoney = usedMoney;
	}

	public String getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(String usedCount) {
		this.usedCount = usedCount;
	}

	
	
}
