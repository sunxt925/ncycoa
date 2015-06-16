package edu.cqu.ncycoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NCYCOA_MEETINGROOM")
public class MeetingRoom {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROOM_ID")
	private Long id;
	
	@Column(name="ROOM_NO")
	private String roomNo;//会议室编号
	
	@Column(name="ROOM_NAME")
	private String roomName;//会议室名称
	
	@Column(name="ROOM_ORG")
	private String belongOrg;//会议室所属单位
	
	@Column(name="ROOM_GALLERYFUL")
	private int galleryful;//会议室容纳人数

	@Column(name="ROOM_MEMO")
	private String roomMemo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getBelongOrg() {
		return belongOrg;
	}

	public void setBelongOrg(String belongOrg) {
		this.belongOrg = belongOrg;
	}

	public int getGalleryful() {
		return galleryful;
	}

	public void setGalleryful(int galleryful) {
		this.galleryful = galleryful;
	}

	public String getRoomMemo() {
		return roomMemo;
	}

	public void setRoomMemo(String roomMemo) {
		this.roomMemo = roomMemo;
	}
	
	
	
	
}
