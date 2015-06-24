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
@Table(name="SAFE_LIFTINGEQUIPMENT")
public class LiftingEquipment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;   
	
	@Column(name="MAIN_WEIGHT")
	private String mainWeight;   //����������
	
	@Column(name="MAIN_SPAN")
	private String mainSpan;   // �������
	
	@Column(name="MAIN_HEIGHT")
	private String mainHeight;  	 // ���������߶�
	
	@Column(name="VICE_WEIGHT")
	private String viceWeight;  // ����������
	
	@Column(name="VICE_SPAN")
	private String viceSpan;     // �������
	
	@Column(name="VICE_HEIGHT")
	private String viceHeight;     // ���������߶�  
	
	@Column(name="MAIN_PURPOSE")
	private String mainPurpose;   // ��Ҫ��;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FACTORY_DATE")
	private Date factoryDate;     // ��������
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INSTALL_DATE")
	private Date installDate;     // ��װ�깤����
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="USE_DATE")
	private Date useDATE;     // �״�Ͷ������
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CERT_DATE")
	private Date certDate;     // ��֤����
	
	@Column(name="USE_LOCATION")
	private String useLocation;  //ʹ�õص�
	
	@Column(name="MANAGE_DEPART")
	private String manageDepart;  //�豸������
	
	@Column(name="MANAGER")
	private String manager;  //�豸������

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMainWeight() {
		return mainWeight;
	}

	public void setMainWeight(String mainWeight) {
		this.mainWeight = mainWeight;
	}

	public String getMainSpan() {
		return mainSpan;
	}

	public void setMainSpan(String mainSpan) {
		this.mainSpan = mainSpan;
	}

	public String getMainHeight() {
		return mainHeight;
	}

	public void setMainHeight(String mainHeight) {
		this.mainHeight = mainHeight;
	}

	public String getViceWeight() {
		return viceWeight;
	}

	public void setViceWeight(String viceWeight) {
		this.viceWeight = viceWeight;
	}

	public String getViceSpan() {
		return viceSpan;
	}

	public void setViceSpan(String viceSpan) {
		this.viceSpan = viceSpan;
	}

	public String getViceHeight() {
		return viceHeight;
	}

	public void setViceHeight(String viceHeight) {
		this.viceHeight = viceHeight;
	}

	public String getMainPurpose() {
		return mainPurpose;
	}

	public void setMainPurpose(String mainPurpose) {
		this.mainPurpose = mainPurpose;
	}

	public Date getFactoryDate() {
		return factoryDate;
	}

	public void setFactoryDate(Date factoryDate) {
		this.factoryDate = factoryDate;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public Date getUseDATE() {
		return useDATE;
	}

	public void setUseDATE(Date useDATE) {
		this.useDATE = useDATE;
	}

	public Date getCertDate() {
		return certDate;
	}

	public void setCertDate(Date certDate) {
		this.certDate = certDate;
	}

	public String getUseLocation() {
		return useLocation;
	}

	public void setUseLocation(String useLocation) {
		this.useLocation = useLocation;
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
