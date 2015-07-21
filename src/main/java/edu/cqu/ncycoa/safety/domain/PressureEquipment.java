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
@Table(name="SAFE_PRESSUREEQUIPMENT")
public class PressureEquipment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="DESIGN_PRESSURE")
	private String designPressure;   //设计压力一
	
	@Column(name="DESIGN_PRESSURES")
	private String designPressure2;   // 设计压力二
	
	@Column(name="MAX_PRESSURE")
	private String maxPressure;  	 // 最高工作压力一
	
	@Column(name="MAX_PRESSURES")
	private String maxPressure2;  // 最高工作压力二
	
	@Column(name="DESIGN_TEMPERATURE")
	private String designTemperature;     // 设计温度一
	
	@Column(name="DESIGN_TEMPERATURES")
	private String designTemperature2;     // 设计温度二
	
	@Column(name="MAX_TEMPERATURE")
	private String maxTemperature;   // 最高工作温度一
	
	@Column(name="MAX_TEMPERATURES")
	private String maxTemperature2;   //最高工作温度二

	@Column(name="MANAGE_DEPART")
	private String manageDepart;  //监控部门
	
	@Column(name="MANAGER")
	private String manager;  //监控负责人

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignPressure() {
		return designPressure;
	}

	public void setDesignPressure(String designPressure) {
		this.designPressure = designPressure;
	}

	public String getDesignPressure2() {
		return designPressure2;
	}

	public void setDesignPressure2(String designPressure2) {
		this.designPressure2 = designPressure2;
	}

	public String getMaxPressure() {
		return maxPressure;
	}

	public void setMaxPressure(String maxPressure) {
		this.maxPressure = maxPressure;
	}

	public String getMaxPressure2() {
		return maxPressure2;
	}

	public void setMaxPressure2(String maxPressure2) {
		this.maxPressure2 = maxPressure2;
	}

	public String getDesignTemperature() {
		return designTemperature;
	}

	public void setDesignTemperature(String designTemperature) {
		this.designTemperature = designTemperature;
	}

	public String getDesignTemperature2() {
		return designTemperature2;
	}

	public void setDesignTemperature2(String designTemperature2) {
		this.designTemperature2 = designTemperature2;
	}

	public String getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(String maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public String getMaxTemperature2() {
		return maxTemperature2;
	}

	public void setMaxTemperature2(String maxTemperature2) {
		this.maxTemperature2 = maxTemperature2;
	}

	public String getManageDepart() {
		return manageDepart;
	}

	public void setManageDepart(String manageDepart) {
		this.manageDepart = manageDepart;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
}
