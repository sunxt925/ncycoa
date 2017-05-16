package com.entity.system;

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
@Table(name="BASE_STAFF")
public class BaseStaff {	
				
		@Column(name="STAFFCODE")
		private String staffcode;   //员工代码
		
		@Column(name="STAFFNAME")
		private String staffname;   // 员工姓名
		
		@Id
		@Column(name="IDCARD")
		private String idcard;  	 // 身份证号
		
		@Column(name="GENDER")
		private String gender;  //性别
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="BIRTHDAY")
		private Date birthday;     // 生日
		
		@Column(name="NATIONALITYCODE")
		private String nationalitycode;     
		
		@Column(name="NATIONALITY")
		private String nationality;   // 
		
		@Column(name="NATIVEPLACE")
		private String nativeplace;  //
		
		@Column(name="MARRIAGE")
		private String marriage;   //
		
		@Column(name="SALARYLEVEL")
		private String salarylevel;   // 
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="BEGINCAREERDATE")
		private Date begincareerdate;     // 入职时间
		
		@Column(name="EMAIL")
		private String email;   // 
		
		@Column(name="QQ")
		private String qq;   // 
		
		@Column(name="MOBILEPHONE")
		private String mobilephone;  //
		
		@Column(name="OFFICEPHONE")
		private String officephone;   // 电话
		
		@Column(name="HOMEPHONE")
		private String homephone;   // 电话
		
		@Column(name="HOMEADDRESS")
		private String homeaddress;   // 
		
		@Column(name="PHOTOFILE")
		private String photofile;   // 
		
		@Column(name="RESUMES")
		private String resumes;   // 
		
		@Column(name="SYSTEMUSERFLAG")
		private Integer systemuserflag;   // 
		
		@Column(name="VALIDFLAG")
		private Integer validflag;   // 
		
		@Column(name="MEMO")
		private String memo;   //

		public String getStaffcode() {
			return staffcode;
		}

		public void setStaffcode(String staffcode) {
			this.staffcode = staffcode;
		}

		public String getStaffname() {
			return staffname;
		}

		public void setStaffname(String staffname) {
			this.staffname = staffname;
		}

		public String getIdcard() {
			return idcard;
		}

		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public String getNationalitycode() {
			return nationalitycode;
		}

		public void setNationalitycode(String nationalitycode) {
			this.nationalitycode = nationalitycode;
		}

		public String getNationality() {
			return nationality;
		}

		public void setNationality(String nationality) {
			this.nationality = nationality;
		}

		public String getNativeplace() {
			return nativeplace;
		}

		public void setNativeplace(String nativeplace) {
			this.nativeplace = nativeplace;
		}

		public String getMarriage() {
			return marriage;
		}

		public void setMarriage(String marriage) {
			this.marriage = marriage;
		}

		public String getSalarylevel() {
			return salarylevel;
		}

		public void setSalarylevel(String salarylevel) {
			this.salarylevel = salarylevel;
		}

		public Date getBegincareerdate() {
			return begincareerdate;
		}

		public void setBegincareerdate(Date begincareerdate) {
			this.begincareerdate = begincareerdate;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getQq() {
			return qq;
		}

		public void setQq(String qq) {
			this.qq = qq;
		}

		public String getMobilephone() {
			return mobilephone;
		}

		public void setMobilephone(String mobilephone) {
			this.mobilephone = mobilephone;
		}

		public String getOfficephone() {
			return officephone;
		}

		public void setOfficephone(String officephone) {
			this.officephone = officephone;
		}

		public String getHomephone() {
			return homephone;
		}

		public void setHomephone(String homephone) {
			this.homephone = homephone;
		}

		public String getHomeaddress() {
			return homeaddress;
		}

		public void setHomeaddress(String homeaddress) {
			this.homeaddress = homeaddress;
		}

		public String getPhotofile() {
			return photofile;
		}

		public void setPhotofile(String photofile) {
			this.photofile = photofile;
		}

		public String getResumes() {
			return resumes;
		}

		public void setResumes(String resumes) {
			this.resumes = resumes;
		}

		public Integer getSystemuserflag() {
			return systemuserflag;
		}

		public void setSystemuserflag(Integer systemuserflag) {
			this.systemuserflag = systemuserflag;
		}

		public Integer getValidflag() {
			return validflag;
		}

		public void setValidflag(Integer validflag) {
			this.validflag = validflag;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}
		
		
}
