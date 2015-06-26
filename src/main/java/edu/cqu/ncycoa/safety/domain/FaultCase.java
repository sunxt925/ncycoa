package edu.cqu.ncycoa.safety.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="SAFE_FAULTCASE")
public class FaultCase {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="FAULTCASE_ID")
	private Long id;//编号
	
	@Column(name="FAULTCASE_CONTENT")
	private String content;//案例名称
	
	@Column(name="FAULTCASE_FILENAME")
	private String filePath;//文件路径名称
	
	@Column(name="FAULTCASE_MEMO")
	private String memo;//备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
