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
@Table(name="SAFE_ELEVATOR")
public class Elevator {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="MODEL")
	private String model;   //型号
	
	@Column(name="EQUIPMENT_NUM")
	private String equipmentNum;   // 企业设备编号
	
	@Column(name="RATED_LOAD")
	private String ratedLoad;  	 // 额定载重
	
	@Column(name="RATED_PASSENGER")
	private String ratedPassenger;  // 额定载客
	
	@Column(name="RATED_SPEED")
	private String ratedSpeed;     //额定速度
	
	@Column(name="FREQUENCY")
	private String frequency;     // 使用频率
	
	@Column(name="PILES")
	private String piles;   // 层数
	
	@Column(name="STATIONS")
	private String stations;    // 站数门数
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FACTORY_DATE")
	private Date factoryDate;    // 出厂日期
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CHECK_DATE")
	private Date checkDATE;     // 检验日期
	
	@Column(name="STATUS")
	private String status;     // 使用状况

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEquipmentNum() {
		return equipmentNum;
	}

	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}

	public String getRatedLoad() {
		return ratedLoad;
	}

	public void setRatedLoad(String ratedLoad) {
		this.ratedLoad = ratedLoad;
	}

	public String getRatedPassenger() {
		return ratedPassenger;
	}

	public void setRatedPassenger(String ratedPassenger) {
		this.ratedPassenger = ratedPassenger;
	}

	public String getRatedSpeed() {
		return ratedSpeed;
	}

	public void setRatedSpeed(String ratedSpeed) {
		this.ratedSpeed = ratedSpeed;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getPiles() {
		return piles;
	}

	public void setPiles(String piles) {
		this.piles = piles;
	}

	public String getStations() {
		return stations;
	}

	public void setStations(String stations) {
		this.stations = stations;
	}

	public Date getFactoryDate() {
		return factoryDate;
	}

	public void setFactoryDate(Date factoryDate) {
		this.factoryDate = factoryDate;
	}

	public Date getCheckDATE() {
		return checkDATE;
	}

	public void setCheckDATE(Date checkDATE) {
		this.checkDATE = checkDATE;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
