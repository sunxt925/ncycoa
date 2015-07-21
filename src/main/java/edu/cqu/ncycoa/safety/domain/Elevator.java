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
	private String model;   //�ͺ�
	
	@Column(name="EQUIPMENT_NUM")
	private String equipmentNum;   // ��ҵ�豸���
	
	@Column(name="RATED_LOAD")
	private String ratedLoad;  	 // �����
	
	@Column(name="RATED_PASSENGER")
	private String ratedPassenger;  // ��ؿ�
	
	@Column(name="RATED_SPEED")
	private String ratedSpeed;     //��ٶ�
	
	@Column(name="FREQUENCY")
	private String frequency;     // ʹ��Ƶ��
	
	@Column(name="PILES")
	private String piles;   // ����
	
	@Column(name="STATIONS")
	private String stations;    // վ������
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FACTORY_DATE")
	private Date factoryDate;    // ��������
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CHECK_DATE")
	private Date checkDATE;     // ��������
	
	@Column(name="STATUS")
	private String status;     // ʹ��״��

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
