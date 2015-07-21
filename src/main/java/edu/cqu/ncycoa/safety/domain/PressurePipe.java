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
@Table(name="SAFE_PRESSUREPIPE")
public class PressurePipe {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="DIAMETER")
	private String diameter;   //����ֱ��
	
	@Column(name="PIPE_MATERIAL")
	private String pipeMaterial;   //�ܵ�����
	
	@Column(name="THICKNESS")
	private String thickness;  	 // �ܵ��ں�
	
	@Column(name="LENGTH")
	private String length;  // �ܵ�����
	
	@Column(name="WORK_PRESSURE")
	private String workPressure;     // �ܵ�����ѹ��
	
	@Column(name="STRENGTH_PRESSURE")
	private String strengthPressure;     // �ܵ�ǿ������ѹ��
	
	@Column(name="RIGOROUS_PRESSURE")
	private String rigorousPressure ;   // �ܵ�����������ѹ��
	
	@Column(name="WORK_TEMPERATURE")
	private String workTemperature;   //�ܵ������¶�
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="USE_DATE")
	private Date useDate;    //�ܵ�Ͷ������
	
	@Column(name="LAYING_METHOD")
	private String layingMethod;   //�ܵ����跽ʽ
	
	@Column(name="ANTICORROSION_METHOD")
	private String anticorrosionMethod;   //�ܵ�������ʽ
	
	@Column(name="ADIABATIC_METHOD")
	private String adiabaticMethod;   //�ܵ����ȷ�ʽ 
	
	@Column(name="DESIGN_CODE")
	private String designCode;   //�ܵ���ƹ淶

	@Column(name="MANAGE_DEPART")
	private String manageDepart;  //��ز���
	
	@Column(name="MANAGER")
	private String manager;  //��ظ�����

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public String getPipeMaterial() {
		return pipeMaterial;
	}

	public void setPipeMaterial(String pipeMaterial) {
		this.pipeMaterial = pipeMaterial;
	}

	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWorkPressure() {
		return workPressure;
	}

	public void setWorkPressure(String workPressure) {
		this.workPressure = workPressure;
	}

	public String getStrengthPressure() {
		return strengthPressure;
	}

	public void setStrengthPressure(String strengthPressure) {
		this.strengthPressure = strengthPressure;
	}

	public String getRigorousPressure() {
		return rigorousPressure;
	}

	public void setRigorousPressure(String rigorousPressure) {
		this.rigorousPressure = rigorousPressure;
	}

	public String getWorkTemperature() {
		return workTemperature;
	}

	public void setWorkTemperature(String workTemperature) {
		this.workTemperature = workTemperature;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getLayingMethod() {
		return layingMethod;
	}

	public void setLayingMethod(String layingMethod) {
		this.layingMethod = layingMethod;
	}

	public String getAnticorrosionMethod() {
		return anticorrosionMethod;
	}

	public void setAnticorrosionMethod(String anticorrosionMethod) {
		this.anticorrosionMethod = anticorrosionMethod;
	}

	public String getAdiabaticMethod() {
		return adiabaticMethod;
	}

	public void setAdiabaticMethod(String adiabaticMethod) {
		this.adiabaticMethod = adiabaticMethod;
	}

	public String getDesignCode() {
		return designCode;
	}

	public void setDesignCode(String designCode) {
		this.designCode = designCode;
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
