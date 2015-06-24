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
@Table(name="SAFE_SPECIALEQUIPMENT")
public class SpecialEquipment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="NAME")
	private String name;   //�豸����
	
	@Column(name="MODEL")
	private String model;   // �豸�ͺ�
	
	@Column(name="MANUFACTURER")
	private String manufacturer;  	 // ��������
	
	@Column(name="SERIAL_NUM")
	private String serialNum;  // �������
	
	@Column(name="WEIGHT")
	private Double weight;     // �豸����
	
	@Column(name="INSTALL_POSITION")
	private String installPosition;     // ��װλ��  
	
	@Column(name="FILE_NUM")
	private String fileNum;   // �������
	
	@Column(name="SIZE")
	private Double size;  //���γߴ�
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MADE_TIME")
	private Date madeTime;     // ��������
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="USE_TIME")
	private Date useTime;     // ����ʱ��

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

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getInstallPosition() {
		return installPosition;
	}

	public void setInstallPosition(String installPosition) {
		this.installPosition = installPosition;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Date getMadeTime() {
		return madeTime;
	}

	public void setMadeTime(Date madeTime) {
		this.madeTime = madeTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	
}
