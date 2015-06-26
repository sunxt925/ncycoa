package edu.cqu.ncycoa.safety.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SAFE_SAFECONDUCTMATERIAL")
public class SafeConductMaterial {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MATERIAL_ID")
	private Long id;//���
	
	@Column(name="MATERIAL_CONTENT")
	private String content;//����
	
	@Column(name="MATERIAL_FILENAME")
	private String filePath;//�ļ�
	
	@Column(name="MATERIAL_MEMO")
	private String memo;//��ע

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
