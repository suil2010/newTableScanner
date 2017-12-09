package com.noshow.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Restaurant implements Serializable {
	private String businessId; /* 회원 번호 */
	private int rtField; /* 업종 - code table */
	private int rtHoliday; /* 휴무일 - code table */
	private int rtTerm; /* 테이블 이용시간 - code table */
	private int rtNum; /* 사업자 번호 */
	private String rtName; /* 음식점 명 */
	private String rtTel; /* 음식점 전화번호 */
	private String rtOpen; /* 음식점 Open 시간 */
	private String rtClose; /* 음식점 Close 시간 */
	private MultipartFile rtImg; /* 음식점 사진 파일 이름 */
	private String rtPicture; /* 저장 이름 */
	private String rtAddress; /* 음식점 위치 */
	private int rtCapacity; /* 수용가능인원 */
	private int rtDeposit; /* 1인 금액 */

	private List<Table> table;
	private int bookmarkCheck; /* 2017.12.08 현준 추가 */
	private List<Table> usableTable;

	public Restaurant() {}
	
	// 사진 파일 없는 생성자
	public Restaurant(String businessId, int rtField, int rtHoliday, int rtTerm, int rtNum, String rtName, String rtTel,
			String rtOpen, String rtClose, String rtPicture, String rtAddress, int rtCapacity, int rtDeposit) {
		this.businessId = businessId;
		this.rtField = rtField;
		this.rtHoliday = rtHoliday;
		this.rtTerm = rtTerm;
		this.rtNum = rtNum;
		this.rtName = rtName;
		this.rtTel = rtTel;
		this.rtOpen = rtOpen;
		this.rtClose = rtClose;
		this.rtPicture = rtPicture;
		this.rtAddress = rtAddress;
		this.rtCapacity = rtCapacity;
		this.rtDeposit = rtDeposit;
	}

	// 파일 있는 생성자
	public Restaurant(String businessId, int rtField, int rtHoliday, int rtTerm, int rtNum, String rtName, String rtTel,
			String rtOpen, String rtClose, MultipartFile rtImg, String rtPicture, String rtAddress, int rtCapacity,
			int rtDeposit) {
		this.businessId = businessId;
		this.rtField = rtField;
		this.rtHoliday = rtHoliday;
		this.rtTerm = rtTerm;
		this.rtNum = rtNum;
		this.rtName = rtName;
		this.rtTel = rtTel;
		this.rtOpen = rtOpen;
		this.rtClose = rtClose;
		this.rtImg = rtImg;
		this.rtPicture = rtPicture;
		this.rtAddress = rtAddress;
		this.rtCapacity = rtCapacity;
		this.rtDeposit = rtDeposit;
	}

	public Restaurant(String businessId, int rtField, int rtHoliday, int rtTerm, int rtNum, String rtName, String rtTel,
			String rtOpen, String rtClose, String rtPicture, String rtAddress, int rtCapacity, int rtDeposit,
			List<Table> table, int bookmarkCheck, List<Table> usableTable) {
		this.businessId = businessId;
		this.rtField = rtField;
		this.rtHoliday = rtHoliday;
		this.rtTerm = rtTerm;
		this.rtNum = rtNum;
		this.rtName = rtName;
		this.rtTel = rtTel;
		this.rtOpen = rtOpen;
		this.rtClose = rtClose;
		this.rtPicture = rtPicture;
		this.rtAddress = rtAddress;
		this.rtCapacity = rtCapacity;
		this.rtDeposit = rtDeposit;
		this.table = table;
		this.bookmarkCheck = bookmarkCheck;
		this.usableTable = usableTable;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public int getRtField() {
		return rtField;
	}

	public void setRtField(int rtField) {
		this.rtField = rtField;
	}

	public int getRtHoliday() {
		return rtHoliday;
	}

	public void setRtHoliday(int rtHoliday) {
		this.rtHoliday = rtHoliday;
	}

	public int getRtTerm() {
		return rtTerm;
	}

	public void setRtTerm(int rtTerm) {
		this.rtTerm = rtTerm;
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

	public String getRtOpen() {
		return rtOpen;
	}

	public void setRtOpen(String rtOpen) {
		this.rtOpen = rtOpen;
	}

	public String getRtClose() {
		return rtClose;
	}

	public void setRtClose(String rtClose) {
		this.rtClose = rtClose;
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

	public List<Table> getTable() {
		return table;
	}

	public void setTable(List<Table> table) {
		this.table = table;
	}

	public int getBookmarkCheck() {
		return bookmarkCheck;
	}

	public void setBookmarkCheck(int bookmarkCheck) {
		this.bookmarkCheck = bookmarkCheck;
	}

	public List<Table> getUsableTable() {
		return usableTable;
	}

	public void setUsableTable(List<Table> usableTable) {
		this.usableTable = usableTable;
	}

	@Override
	public String toString() {
		return "Restaurant [businessId=" + businessId + ", rtField=" + rtField + ", rtHoliday=" + rtHoliday
				+ ", rtTerm=" + rtTerm + ", rtNum=" + rtNum + ", rtName=" + rtName + ", rtTel=" + rtTel + ", rtOpen="
				+ rtOpen + ", rtClose=" + rtClose + ", rtImg=" + rtImg + ", rtPicture=" + rtPicture + ", rtAddress="
				+ rtAddress + ", rtCapacity=" + rtCapacity + ", rtDeposit=" + rtDeposit + ", table=" + table
				+ ", bookmarkCheck=" + bookmarkCheck + ", usableTable=" + usableTable + "]";
	}

	

}
