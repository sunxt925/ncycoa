package edu.cqu.ncycoa.domain;

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
@Table(name="NCYCOA_NOTICE")
public class Notice {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NOTICE_ID")
	private Long id;  
	
	@Column(name="TITLE")
	private String title;   // 标题
	
	@Column(name="DEPART")
	private String depart;   // 部门
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PUBLISH_TIME")
	private Date publishTime; //发布时间
	
	@Column(name="AUTHOR")
	private String author;   // 发布人
	
	@Column(name="CONTENT", length=4000)
	private String content;   // 内容

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
