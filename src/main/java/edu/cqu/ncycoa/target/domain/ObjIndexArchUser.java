package edu.cqu.ncycoa.target.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TBM_ObjIndexArchUser")
public class ObjIndexArchUser {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="INDEXARCH_CODE")
	private String IndexArchCode;//	指标分类编码
	@Column(name="UNIINDEX_CODE")
	private String UniIndexCode;//	指标统一编码
	@Column(name="OBJECT_CODE")
	private String objectcode;
	@Column(name="OBJECT_TYPE")
	private String objecttype;
	@Column(name="STAFFORG")
	private String stafforg;
	@Column(name="MULTI_INDEX_ORDER")
	private int multiindexorder;
	@Column(name="START_DATE")
	private Date startDate;
	@Column(name="END_DATE")
	private Date endDate;
	@Column(name="MEMO")
	private String memo;//备注
}
