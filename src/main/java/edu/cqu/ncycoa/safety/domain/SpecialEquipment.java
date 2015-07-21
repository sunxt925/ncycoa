package edu.cqu.ncycoa.safety.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SAFE_SPECIALEQUIPMENT")
@SuppressWarnings("serial")
public class SpecialEquipment implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="SNAME")
	private String name;   //设备名称
	
	@Column(name="MODEL")
	private String model;   // 设备型号
	
	@Column(name="MANUFACTURER")
	private String manufacturer;  	 // 生产厂家
	
	@Column(name="SERIAL_NUM")
	private String serialNum;  // 出厂编号
	
	@Column(name="SWEIGHT")
	private String weight;     // 设备重量
	
	@Column(name="INSTALL_POSITION")
	private String installPosition;     // 安装位置  
	
	@Column(name="FILE_NUM")
	private String fileNum;   // 档案编号
	
	@Column(name="SSIZE")
	private String size;  //外形尺寸
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MADE_TIME")
	private Date madeTime;     // 制造日期
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="USE_TIME")
	private Date useTime;     // 启用时间
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specialEquipment")
	private List<SubSpecialEquipment> subEquipments; // 附属设备

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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
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

	public List<SubSpecialEquipment> getSubEquipments() {
		return subEquipments;
	}

	public void setSubEquipments(List<SubSpecialEquipment> subEquipments) {
		this.subEquipments = subEquipments;
	}
	
	public void addSub(SubSpecialEquipment item) {
        if (!this.subEquipments.contains(item)) {
            this.subEquipments.add(item);
            item.setSpecialEquipment(this);
        }
    }
	public void removeOrderItem(SubSpecialEquipment item) {
        if (this.subEquipments.contains(item)) {
            item.setSpecialEquipment(null);
            this.subEquipments.remove(item);
        }
    }
}
