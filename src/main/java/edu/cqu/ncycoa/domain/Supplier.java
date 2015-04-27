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
@Table(name="NCYCOA_SUPPLIER")
public class Supplier {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SUPPLIER_ID")
	private Long id;   
	
	@Column(name="SUPPLIER_CODE")
	private String code;   //��������
	
	@Column(name="SUPPLIER_NAME")
	private String name;   // ����������
	
	@Column(name="GOODS_TYPE")
	private Short goodsType;  	 // �������  0�칫���� 1��Ϣ���� 2�������� 3�������� 4�濾���� 5��Ҷ����
	
	@Column(name="MANAGE_DEPART")
	private String manageDepart;  // ��ڹ�����
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INPUT_TIME")
	private Date inputTime;     // ���ʱ��
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OUTPUT_TIME")
	private Date outputTime;     // ����ʱ��  
	//�������룬��Ӫ��Χ��ע���ʽ𣬷��˴�����˾��ַ����ϵ�绰
	
	@Column(name="SUPPLIER_RANGE")
	private String range;   // ��Ӫ��Χ  ִ����Ч��
	
	@Column(name="SUPPLIER_VALID")
	private String valid;  //ִ����Ч��
	
	@Column(name="REGIST_MONEY")
	private String registMoney;   // ע���ʽ�
	
	@Column(name="ADDRESS")
	private String address;   // ��ַ
	
	@Column(name="SCALE")
	private String scale;   // �Żݱ���
	
	@Column(name="MANAGER")
	private String manager;   // ���˴���
	
	@Column(name="AGENT")
	private String agent;  //ί�д�����
	
	@Column(name="TEL")
	private String tel;   // �绰
	

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

	public Short getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Short goodsType) {
		this.goodsType = goodsType;
	}

	public String getManageDepart() {
		return manageDepart;
	}

	public void setManageDepart(String manageDepart) {
		this.manageDepart = manageDepart;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Date getOutputTime() {
		return outputTime;
	}

	public void setOutputTime(Date outputTime) {
		this.outputTime = outputTime;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getRegistMoney() {
		return registMoney;
	}

	public void setRegistMoney(String registMoney) {
		this.registMoney = registMoney;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	
}
