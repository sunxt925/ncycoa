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
	private String name; //����
	
	@Column(name="TYPE")
	private Short type;   //�豸���  0����/1ѹ������/2���ڻ�����/3������/4�����豸/5�����豸/6Ѳ���豸/7�����豸
	
	@Column(name="MODEL")
	private String model;   // �豸�ͺ�
	
	@Column(name="SIGN")
	private String sign;   // �豸�ƺ�
	
	@Column(name="INSTALL_POSITION")
	private String installPosition;     // ��װλ��  
	
	@Column(name="USE_DEPART")
	private String useDepart;  	 // ʹ�ò��ţ����룩
	
	@Column(name="MAINTEN_DEPART")
	private String maintenDepart;  // ά����λ
	
	@Column(name="CHECK_CYCLE")
	private int checkCycle;     // �������ڣ��£�
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CHECK_DATE")
	private Date checkDate;     // ���ڼ�������
	
	@Column(name="CHECK_STATUS")
	private String checkStatus;   // ��ǰ�������
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="NEXTCHECK_DATE")
	private Date nextCheckDate;     // �´μ�������
	
	@Column(name="MANAGER")
	private String manager;  //������Ա�����룩
	
	@Column(name="MEMO")
	private String memo;  //��ע
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="USE_TIME")
	private Date useTime;     // ��������
	
	@Column(name="REMIND")
	private Short remind;  //��ʾ δ����ʾ0 ����ʾΪ1

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstallPosition() {
		return installPosition;
	}

	public void setInstallPosition(String installPosition) {
		this.installPosition = installPosition;
	}

	public String getUseDepart() {
		return useDepart;
	}

	public void setUseDepart(String useDepart) {
		this.useDepart = useDepart;
	}

	public String getMaintenDepart() {
		return maintenDepart;
	}

	public void setMaintenDepart(String maintenDepart) {
		this.maintenDepart = maintenDepart;
	}

	public int getCheckCycle() {
		return checkCycle;
	}

	public void setCheckCycle(int checkCycle) {
		this.checkCycle = checkCycle;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getNextCheckDate() {
		return nextCheckDate;
	}

	public void setNextCheckDate(Date nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public Short getRemind() {
		return remind;
	}

	public void setRemind(Short remind) {
		this.remind = remind;
	}
	
	
	

}
