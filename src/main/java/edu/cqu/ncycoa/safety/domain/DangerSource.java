package edu.cqu.ncycoa.safety.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SAFE_DANGERSOURCE")
public class DangerSource {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DANGERSOURCE_ID")
	private Long id;//���
	
	@Column(name="ACTICITYTYPE")
	private String activityType;//��ڱ�ͳ���
	
	@Column(name="JOBACTIVITY")
	private String jobActivity;//��ҵ�
	
	@Column(name="MAINDANGERSOURCE")
	private String mainDangerSource;//�ص�Σ��Դ
	
	@Column(name="DANGER")
	private String danger;//���ܵ��µ��¹�
	
	@Column(name="DANGERLEVEL")
	private String dangerLevel;//���ռ���
	
	@Column(name="MEASURE_A")
	private String measureA;//���ƴ�ʩA
	
	@Column(name="MEASURE_B")
	private String measureB;//���ƴ�ʩB
	
	@Column(name="MEASURE_C")
	private String measureC;//���ƴ�ʩC

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getJobActivity() {
		return jobActivity;
	}

	public void setJobActivity(String jobActivity) {
		this.jobActivity = jobActivity;
	}

	public String getMainDangerSource() {
		return mainDangerSource;
	}

	public void setMainDangerSource(String mainDangerSource) {
		this.mainDangerSource = mainDangerSource;
	}

	public String getDanger() {
		return danger;
	}

	public void setDanger(String danger) {
		this.danger = danger;
	}

	public String getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(String dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public String getMeasureA() {
		return measureA;
	}

	public void setMeasureA(String measureA) {
		this.measureA = measureA;
	}

	public String getMeasureB() {
		return measureB;
	}

	public void setMeasureB(String measureB) {
		this.measureB = measureB;
	}

	public String getMeasureC() {
		return measureC;
	}

	public void setMeasureC(String measureC) {
		this.measureC = measureC;
	}
	
	
}
