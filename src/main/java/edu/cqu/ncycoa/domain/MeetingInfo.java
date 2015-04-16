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
	private String MeetingNo;
	
	@Column(name="MEETING_NAME")
    private String MeetingName;
	
	@Column(name="MEETING_TOPICS")
    private String MeetingTopics;
	
	@Column(name="MEETING_BEGINDATE")
    private Date MeetingBeginDate;
	
	@Column(name="MEETING_ENDDATE")
	private Date MeetingEndDate;
	
	@Column(name="MEETING_ROOM")
    private String MeetingRoom;
	
	@Column(name="MEETING_NUMATTENDEE")
    private long NumAttendee;
	
	
	@ElementCollection
	@CollectionTable(name="NCYCOA_MEETING_ATTENDEE", joinColumns=@JoinColumn(name="MEETING_ID"))
	private List<String> participants; // 参会人员
	
	
	@Column(name="MEETING_REQUESTDESC")
    private String RequestDesc;
	
	@Column(name="MEETING_APPLUORGCODE")
    private String ApplyOrgCode;
	
	@Column(name="MEETING_HANLER")
    private String Handler;
	
	@Column(name="MEETING_FLAG")
	private String MeetingFlag;
	
	@Column(name="MEETING_REPORT")
    private String MeetingReport;
	
	@Column(name="MEETING_AUDITORGCODE")
    private String AuditOrgCode;
	
	@Column(name="MEETING_AUDITOR")
    private String Auditor;
	
	@Column(name="MEETING_AUDITOPINION")
    private String AuditOpinion;
	
	@Column(name="MEETING_AUDITDATE")
    private Date AuditDate;
	
	@Column(name="MEETING_AUDITFLAG")
    private String AuditFlag;
	
	@Column(name="MEETING_MEMO")
    private String Memo;

	public String getMeetingNo() {
		return MeetingNo;
	}

	public void setMeetingNo(String meetingNo) {
		MeetingNo = meetingNo;
	}

	public String getMeetingName() {
		return MeetingName;
	}

	public void setMeetingName(String meetingName) {
		MeetingName = meetingName;
	}

	public String getMeetingTopics() {
		return MeetingTopics;
	}

	public void setMeetingTopics(String meetingTopics) {
		MeetingTopics = meetingTopics;
	}

	public Date getMeetingBeginDate() {
		return MeetingBeginDate;
	}

	public void setMeetingBeginDate(Date meetingBeginDate) {
		MeetingBeginDate = meetingBeginDate;
	}

	public Date getMeetingEndDate() {
		return MeetingEndDate;
	}

	public void setMeetingEndDate(Date meetingEndDate) {
		MeetingEndDate = meetingEndDate;
	}

	public String getMeetingRoom() {
		return MeetingRoom;
	}

	public void setMeetingRoom(String meetingRoom) {
		MeetingRoom = meetingRoom;
	}

	public long getNumAttendee() {
		return NumAttendee;
	}

	public void setNumAttendee(long numAttendee) {
		NumAttendee = numAttendee;
	}

	public List<String> getParticipants() {
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public String getRequestDesc() {
		return RequestDesc;
	}

	public void setRequestDesc(String requestDesc) {
		RequestDesc = requestDesc;
	}

	public String getApplyOrgCode() {
		return ApplyOrgCode;
	}

	public void setApplyOrgCode(String applyOrgCode) {
		ApplyOrgCode = applyOrgCode;
	}

	public String getHandler() {
		return Handler;
	}

	public void setHandler(String handler) {
		Handler = handler;
	}

	public String getMeetingFlag() {
		return MeetingFlag;
	}

	public void setMeetingFlag(String meetingFlag) {
		MeetingFlag = meetingFlag;
	}

	public String getMeetingReport() {
		return MeetingReport;
	}

	public void setMeetingReport(String meetingReport) {
		MeetingReport = meetingReport;
	}

	public String getAuditOrgCode() {
		return AuditOrgCode;
	}

	public void setAuditOrgCode(String auditOrgCode) {
		AuditOrgCode = auditOrgCode;
	}

	public String getAuditor() {
		return Auditor;
	}

	public void setAuditor(String auditor) {
		Auditor = auditor;
	}

	public String getAuditOpinion() {
		return AuditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		AuditOpinion = auditOpinion;
	}

	public Date getAuditDate() {
		return AuditDate;
	}

	public void setAuditDate(Date auditDate) {
		AuditDate = auditDate;
	}

	public String getAuditFlag() {
		return AuditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		AuditFlag = auditFlag;
	}

	public String getMemo() {
		return Memo;
	}

	public void setMemo(String memo) {
		Memo = memo;
	}
	
	
}
