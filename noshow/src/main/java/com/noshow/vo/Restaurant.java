package com.noshow.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Restaurant implements Serializable{
	private String businessId;
	private int rtNum; /* 사업자 번호 */
	private String rtName; /* 음식점 명 */
	private String rtTel; /* 음식점 전화번호 */
	private String rtField; /* 업종 */
	private String rtHoliday; /* 휴무일 */
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rtOpen; /* 오픈시간 */
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rtClose; /* close 시간 */
	
	
	private int rtTerm;  /* 테이블 이용시간 */
	private MultipartFile rtImg; /* 음식점 사진 파일 이름*/
	private String rtPicture; /* 저장 이름 */
	private String rtAddress; /* 음식점 위치*/
	private int rtCapacity; /* 수용가능인원 */
	
	private int rtDeposit; /* 1인 금액 */
	
	private Member member; /* 점주 회원 아이디 */
	private Table table;
	
	public Restaurant() {}

	public Restaurant(String businessId, int rtNum, String rtName, String rtTel, String rtField, String rtHoliday,
			Date rtOpen, Date rtClose, int rtTerm, String rtAddress, int rtCapacity, int rtDeposit) {
		this.businessId = businessId;
		this.rtNum = rtNum;
		this.rtName = rtName;
		this.rtTel = rtTel;
		this.rtField = rtField;
		this.rtHoliday = rtHoliday;
		this.rtOpen = rtOpen;
		this.rtClose = rtClose;
		this.rtTerm = rtTerm;
		this.rtAddress = rtAddress;
		this.rtCapacity = rtCapacity;
		this.rtDeposit = rtDeposit;
	}

	public Restaurant(String businessId, int rtNum, String rtName, String rtTel, String rtField, String rtHoliday,
			Date rtOpen, Date rtClose, int rtTerm, String rtPicture, String rtAddress, int rtCapacity, int rtDeposit) {
		this.businessId = businessId;
		this.rtNum = rtNum;
		this.rtName = rtName;
		this.rtTel = rtTel;
		this.rtField = rtField;
		this.rtHoliday = rtHoliday;
		this.rtOpen = rtOpen;
		this.rtClose = rtClose;
		this.rtTerm = rtTerm;
		this.rtPicture = rtPicture;
		this.rtAddress = rtAddress;
		this.rtCapacity = rtCapacity;
		this.rtDeposit = rtDeposit;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public int getRtNum() {
		return rtNum;
	}

	public void setRtNum(int rtNum) {
		this.rtNum = rtNum;
	}

	public String getRtName() {
		return rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public String getRtTel() {
		return rtTel;
	}

	public void setRtTel(String rtTel) {
		this.rtTel = rtTel;
	}

	public String getRtField() {
		return rtField;
	}

	public void setRtField(String rtField) {
		this.rtField = rtField;
	}

	public String getRtHoliday() {
		return rtHoliday;
	}

	public void setRtHoliday(String rtHoliday) {
		this.rtHoliday = rtHoliday;
	}

	public Date getRtOpen() {
		return rtOpen;
	}

	public void setRtOpen(Date rtOpen) {
		this.rtOpen = rtOpen;
	}

	public Date getRtClose() {
		return rtClose;
	}

	public void setRtClose(Date rtClose) {
		this.rtClose = rtClose;
	}

	public int getRtTerm() {
		return rtTerm;
	}

	public void setRtTerm(int rtTerm) {
		this.rtTerm = rtTerm;
	}

	public MultipartFile getRtImg() {
		return rtImg;
	}

	public void setRtImg(MultipartFile rtImg) {
		this.rtImg = rtImg;
	}

	public String getRtPicture() {
		return rtPicture;
	}

	public void setRtPicture(String rtPicture) {
		this.rtPicture = rtPicture;
	}

	public String getRtAddress() {
		return rtAddress;
	}

	public void setRtAddress(String rtAddress) {
		this.rtAddress = rtAddress;
	}

	public int getRtCapacity() {
		return rtCapacity;
	}

	public void setRtCapacity(int rtCapacity) {
		this.rtCapacity = rtCapacity;
	}

	public int getRtDeposit() {
		return rtDeposit;
	}

	public void setRtDeposit(int rtDeposit) {
		this.rtDeposit = rtDeposit;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	@Override
	public String toString() {
		return "Restaurant [businessId=" + businessId + ", rtNum=" + rtNum + ", rtName=" + rtName + ", rtTel=" + rtTel
				+ ", rtField=" + rtField + ", rtHoliday=" + rtHoliday + ", rtOpen=" + rtOpen + ", rtClose=" + rtClose
				+ ", rtTerm=" + rtTerm + ", rtImg=" + rtImg + ", rtPicture=" + rtPicture + ", rtAddress=" + rtAddress
				+ ", rtCapacity=" + rtCapacity + ", rtDeposit=" + rtDeposit + ", member=" + member + ", table=" + table
				+ "]";
	}

	
	
}
