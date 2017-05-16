package edu.cqu.ncycoa.domain;

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

@Entity
@Table(name="NCYCOA_MEETING")
public class MeetingInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MEETING_ID")
	private Long id;
	
	@Column(name="MEETING_NAME")
    private String meetingName;
	
	@Column(name="MEETING_TOPICS")
    private String meetingTopics;
	
	@Column(name="MEETING_BEGINDATE")
    private Date meetingBeginDate;
	
	@Column(name="MEETING_ENDDATE")
	private Date meetingEndDate;
	
	@Column(name="MEETING_ROOM")
    private String meetingRoom;
	
	@Column(name="MEETING_NUMATTENDEE")
    private long numAttendee;
	
	
	@ElementCollection
	@CollectionTable(name="NCYCOA_MEETING_ATTENDEE", joinColumns=@JoinColumn(name="MEETING_ID"))
	private List<String> participants; // 参会人员
	
	
	@Column(name="MEETING_REQUESTDESC")
    private String requestDesc;
	
	@Column(name="MEETING_APPLUORGCODE")
    private String applyOrgCode;
	
	@Column(name="MEETING_HANLER")
    private String handler;
	
	@Column(name="MEETING_FLAG")
	private String meetingFlag;
	
	@Column(name="MEETING_REPORT")
    private String meetingReport;
	
	@Column(name="MEETING_AUDITORGCODE")
    private String auditOrgCode;
	
	@Column(name="MEETING_AUDITOR")
    private String auditor;
	
	@Column(name="MEETING_AUDITOPINION")
    private String auditOpinion;
	
	@Column(name="MEETING_AUDITDATE")
    private Date auditDate;
	
	@Column(name="MEETING_AUDITFLAG")
    private String auditFlag;
	
	@Column(name="MEETING_EATTYPE")
	private String eatType; //就餐类型
	
	@Column(name="MEETING_EATPNUM")
	private int eatpnum; //就餐人数
	
	@Column(name="MEETING_ACCOMMODATIONNUM")
	private int accommodationnum;//住宿人数
	
	@Column(name="MEETING_MEMO")
    private String memo;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getMeetingTopics() {
		return meetingTopics;
	}

	public void setMeetingTopics(String meetingTopics) {
		this.meetingTopics = meetingTopics;
	}

	public Date getMeetingBeginDate() {
		return meetingBeginDate;
	}

	public void setMeetingBeginDate(Date meetingBeginDate) {
		this.meetingBeginDate = meetingBeginDate;
	}

	public Date getMeetingEndDate() {
		return meetingEndDate;
	}

	public void setMeetingEndDate(Date meetingEndDate) {
		this.meetingEndDate = meetingEndDate;
	}

	public String getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(String meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	

	public long getNumAttendee() {
		return numAttendee;
	}

	public void setNumAttendee(long numAttendee) {
		this.numAttendee = numAttendee;
	}

	public List<String> getParticipants() {
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public String getRequestDesc() {
		return requestDesc;
	}

	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}

	public String getApplyOrgCode() {
		return applyOrgCode;
	}

	public void setApplyOrgCode(String applyOrgCode) {
		this.applyOrgCode = applyOrgCode;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getMeetingFlag() {
		return meetingFlag;
	}

	public void setMeetingFlag(String meetingFlag) {
		this.meetingFlag = meetingFlag;
	}

	public String getMeetingReport() {
		return meetingReport;
	}

	public void setMeetingReport(String meetingReport) {
		this.meetingReport = meetingReport;
	}

	public String getAuditOrgCode() {
		return auditOrgCode;
	}

	public void setAuditOrgCode(String auditOrgCode) {
		this.auditOrgCode = auditOrgCode;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getEatType() {
		return eatType;
	}

	public void setEatType(String eatType) {
		this.eatType = eatType;
	}

	public int getEatpnum() {
		return eatpnum;
	}

	public void setEatpnum(int eatpnum) {
		this.eatpnum = eatpnum;
	}

	public int getAccommodationnum() {
		return accommodationnum;
	}

	public void setAccommodationnum(int accommodationnum) {
		this.accommodationnum = accommodationnum;
	}

	
	
}
