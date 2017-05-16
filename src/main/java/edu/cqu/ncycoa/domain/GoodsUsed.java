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
  //����ʹ��̨�ʼ�¼��
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USED_ID")  //ʹ�ñ��
	private Long id;
	
	@Column(name="USED_SUPPLIER")
	private String supplier;  	 // ��Ӧ������
	
	
	@Column(name="USED_GOODSTYPE")
	private String usedGoods;  	 // �ɹ���������
	
	@Column(name="USED_DEPART")
	private String usedDepart;  // ��ڲ���
	
	@Column(name="USED_MONEY")
	private String usedMoney;  // �ɹ����
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="USED_TIME")
	private Date usedTime;     // ʹ��ʱ��
	
	@Column(name="USED_COUNT")
	private String usedCount;     // �ɹ�����

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
