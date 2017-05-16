package edu.cqu.ncycoa.domain;
// 采标实体
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="STD_GET")
public class GetStd {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="GST_ID")
	private Long id;   
	
	@Column(name="GST_FILECODE")
	private String gstFilecode; 
	
	@Column(name="GST_FILENAME")
	private String gstFilename;  
	
	@Column(name="GST_FILETYPE")
	private String gstFiletype;  	
	
	@Column(name="GST_FILELEVEL")
	private String gstFilelevel;  	
	
	@Column(name="GST_FILEPATH")
	private String gstFilepath;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GST_PUBLICTIME")
	private Date gstPublictime;
	
	@Column(name="GST_STAFFCODE")
	private String gstStaffcode;
	
	@Column(name="GST_STAFFNAME")
	private String gstStaffname; 
	
	@Column(name="GST_STAFFORG")
	private String gstStafforg;
	
	@Column(name="GST_OPERATE")
	private String gstOperate;

	@Column(name="GST_STDNAME")
	private String gstStdname;
	
	@Column(name="GST_STDTYPE")
	private String gstStdtype;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GST_TURNTIME")
	private Date gstTurntime;

	@Column(name="GST_YEAR")
	private String gstYear;
	
	public String getGstYear() {
		return gstYear;
	}

	public void setGstYear(String gstYear) {
		this.gstYear = gstYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGstFilecode() {
		return gstFilecode;
	}

	public void setGstFilecode(String gstFilecode) {
		this.gstFilecode = gstFilecode;
	}

	public String getGstFilename() {
		return gstFilename;
	}

	public void setGstFilename(String gstFilename) {
		this.gstFilename = gstFilename;
	}

	public String getGstFiletype() {
		return gstFiletype;
	}

	public void setGstFiletype(String gstFiletype) {
		this.gstFiletype = gstFiletype;
	}

	public String getGstFilelevel() {
		return gstFilelevel;
	}

	public void setGstFilelevel(String gstFilelevel) {
		this.gstFilelevel = gstFilelevel;
	}

	public String getGstFilepath() {
		return gstFilepath;
	}

	public void setGstFilepath(String gstFilePATH) {
		this.gstFilepath = gstFilePATH;
	}

	public Date getGstPublictime() {
		return gstPublictime;
	}

	public void setGstPublictime(Date gstPublictime) {
		this.gstPublictime = gstPublictime;
	}

	public String getGstStaffcode() {
		return gstStaffcode;
	}

	public void setGstStaffcode(String gstStaffcode) {
		this.gstStaffcode = gstStaffcode;
	}

	public String getGstStaffname() {
		return gstStaffname;
	}

	public void setGstStaffname(String gstStaffname) {
		this.gstStaffname = gstStaffname;
	}

	public String getGstStafforg() {
		return gstStafforg;
	}

	public void setGstStafforg(String gstStafforg) {
		this.gstStafforg = gstStafforg;
	}

	public String getGstOperate() {
		return gstOperate;
	}

	public void setGstOperate(String gstOperate) {
		this.gstOperate = gstOperate;
	}

	public String getGstStdname() {
		return gstStdname;
	}

	public void setGstStdname(String gstStdname) {
		this.gstStdname = gstStdname;
	}

	public String getGstStdtype() {
		return gstStdtype;
	}

	public void setGstStdtype(String gstStdtype) {
		this.gstStdtype = gstStdtype;
	}

	public Date getGstTurntime() {
		return gstTurntime;
	}

	public void setGstTurntime(Date gstTurntime) {
		this.gstTurntime = gstTurntime;
	}

	

	
}
