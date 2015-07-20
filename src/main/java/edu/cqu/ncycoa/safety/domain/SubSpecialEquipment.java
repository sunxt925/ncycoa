package edu.cqu.ncycoa.safety.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SAFE_SUBSPECIALEQUIPMENT")
@SuppressWarnings("serial")
public class SubSpecialEquipment implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SERIAL_NUM")
	private Long serialNum; //���   
	
	@Column(name="EQUIPMENT_NUM")
	private String equipmentNum;   //�豸λ��
	
	@Column(name="EQUIPMENT_NAME")
	private String equipmentName;   // �豸����
	
	@Column(name="MODEL")
	private String model;  	 // ����ͺ�	
	
	@Column(name="MANUFACTURER")
	private String manufacturer;     // ��������
	
	@Column(name="MAINTENCYCLE")
	private String maintenCycle;     // ά������ 
	
	@Column(name="MEMO")
	private String memo;   // ��ע
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "ID")
    private SpecialEquipment specialEquipment;
	
	public Long getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}
	public String getEquipmentNum() {
		return equipmentNum;
	}
	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getMaintenCycle() {
		return maintenCycle;
	}
	public void setMaintenCycle(String maintenCycle) {
		this.maintenCycle = maintenCycle;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public SpecialEquipment getSpecialEquipment() {
		return specialEquipment;
	}
	public void setSpecialEquipment(SpecialEquipment specialEquipment) {
		this.specialEquipment = specialEquipment;
	}
	
}
