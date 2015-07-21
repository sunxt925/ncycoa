package edu.cqu.ncycoa.safety.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SAFE_ACCIDENT")
public class Accident {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCIDENT_ID")
	private Long id;//���
	
	@Column(name="ACCIDENT_DETIAL")
	private String detial;//�¹ʸ���
	
	@Column(name="ACCIDENT_ANALYZE")
	private String analyze;//�¹ʷ���
	
	@Column(name="ACCIDENT_PUNISH")
	private String punish;//�¹�����
	
	@Column(name="ACCIDENT_CUREINFO")
	private String cureInfo;//������Ա������Ϣ
	
	@Column(name="ACCIDENT_MEMO")
	private String memo;//��ע

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetial() {
		return detial;
	}

	public void setDetial(String detial) {
		this.detial = detial;
	}

	public String getAnalyze() {
		return analyze;
	}

	public void setAnalyze(String analyze) {
		this.analyze = analyze;
	}



	public String getPunish() {
		return punish;
	}

	public void setPunish(String punish) {
		this.punish = punish;
	}

	public String getCureInfo() {
		return cureInfo;
	}

	public void setCureInfo(String cureInfo) {
		this.cureInfo = cureInfo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
