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
	private String name; //名称
	
	@Column(name="TYPE")
	private Short type;   //设备类别  0电梯/1压力容器/2场内机动车/3机动车/4消防设备/5报警设备/6巡逻设备/7其他设备
	
	@Column(name="MODEL")
	private String model;   // 设备型号
	
	@Column(name="SIGN")
	private String sign;   // 设备牌号
	
	@Column(name="INSTALL_POSITION")
	private String installPosition;     // 安装位置  
	
	@Column(name="USE_DEPART")
	private String useDepart;  	 // 使用部门（代码）
	
	@Column(name="MAINTEN_DEPART")
	private String maintenDepart;  // 维保单位
	
	@Column(name="CHECK_CYCLE")
	private int checkCycle;     // 检验周期（月）
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CHECK_DATE")
	private Date checkDate;     // 当期检验日期
	
	@Column(name="CHECK_STATUS")
	private String checkStatus;   // 当前检验情况
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="NEXTCHECK_DATE")
	private Date nextCheckDate;     // 下次检验日期
	
	@Column(name="MANAGER")
	private String manager;  //管理人员（代码）
	
	@Column(name="MEMO")
	private String memo;  //备注
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="USE_TIME")
	private Date useTime;     // 启用日期
	
	@Column(name="REMIND")
	private Short remind;  //提示 未到提示0 该提示为1

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
