package com.noshow.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Restaurant implements Serializable{
	private String businessId;
	private int rtNum; /* 사업자 번호 */
	private String rtName; /* 음식점 명 */
	private String rtTel; /* 음식점 전화번호 */
	private String rtField; /* 업종 */
	private String rtHoliday; /* 휴무일 */
	
	private String rtOpen;
	private String rtClose;
	/*@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rtOpen;  오픈시간 
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rtClose;  close 시간 */
	
	
	private int rtTerm;  /* 테이블 이용시간 */
	private MultipartFile rtImg; /* 음식점 사진 파일 이름*/
	private String rtPicture; /* 저장 이름 */
	private String rtAddress; /* 음식점 위치*/
	private int rtCapacity; /* 수용가능인원 */
	
	private int rtDeposit; /* 1인 금액 */
	
	private Member member; /* 점주 회원 아이디 */
	private List<Table> table;
	private int bookmarkCheck; /* 2017.12.08 현준 추가 */
	private List<Table> usableTable;
	
	public Restaurant() {}

	public Restaurant(String businessId, int rtNum, String rtName, String rtTel, String rtField, String rtHoliday,
			String rtOpen, String rtClose, int rtTerm, MultipartFile rtImg, String rtPicture, String rtAddress,
			int rtCapacity, int rtDeposit, Member member, List<Table> table) {
		super();
		this.businessId = businessId;
		this.rtNum = rtNum;
		this.rtName = rtName;
		this.rtTel = rtTel;
		this.rtField = rtField;
		this.rtHoliday = rtHoliday;
		this.rtOpen = rtOpen;
		this.rtClose = rtClose;
		this.rtTerm = rtTerm;
		this.rtImg = rtImg;
		this.rtPicture = rtPicture;
		this.rtAddress = rtAddress;
		this.rtCapacity = rtCapacity;
		this.rtDeposit = rtDeposit;
		this.member = member;
		this.table = table;
	}
	
	public Restaurant(String businessId, int rtNum, String rtName, String rtTel, String rtField, String rtHoliday,
			String rtOpen, String rtClose, int rtTerm, MultipartFile rtImg, String rtPicture, String rtAddress,
			int rtCapacity, int rtDeposit, Member member, List<Table> table, int bookmarkCheck, List<Table> usableTable) {
		super();
		this.businessId = businessId;
		this.rtNum = rtNum;
		this.rtName = rtName;
		this.rtTel = rtTel;
		this.rtField = rtField;
		this.rtHoliday = rtHoliday;
		this.rtOpen = rtOpen;
		this.rtClose = rtClose;
		this.rtTerm = rtTerm;
		this.rtImg = rtImg;
		this.rtPicture = rtPicture;
		this.rtAddress = rtAddress;
		this.rtCapacity = rtCapacity;
		this.rtDeposit = rtDeposit;
		this.member = member;
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
		return "Restaurant [businessId=" + businessId + ", rtNum=" + rtNum + ", rtName=" + rtName + ", rtTel=" + rtTel
				+ ", rtField=" + rtField + ", rtHoliday=" + rtHoliday + ", rtOpen=" + rtOpen + ", rtClose=" + rtClose
				+ ", rtTerm=" + rtTerm + ", rtImg=" + rtImg + ", rtPicture=" + rtPicture + ", rtAddress=" + rtAddress
				+ ", rtCapacity=" + rtCapacity + ", rtDeposit=" + rtDeposit + ", member=" + member + ", table=" + table
				+ ", bookmarkCheck=" + bookmarkCheck + ", usableTable=" + usableTable + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookmarkCheck;
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		result = prime * result + ((rtAddress == null) ? 0 : rtAddress.hashCode());
		result = prime * result + rtCapacity;
		result = prime * result + ((rtClose == null) ? 0 : rtClose.hashCode());
		result = prime * result + rtDeposit;
		result = prime * result + ((rtField == null) ? 0 : rtField.hashCode());
		result = prime * result + ((rtHoliday == null) ? 0 : rtHoliday.hashCode());
		result = prime * result + ((rtImg == null) ? 0 : rtImg.hashCode());
		result = prime * result + ((rtName == null) ? 0 : rtName.hashCode());
		result = prime * result + rtNum;
		result = prime * result + ((rtOpen == null) ? 0 : rtOpen.hashCode());
		result = prime * result + ((rtPicture == null) ? 0 : rtPicture.hashCode());
		result = prime * result + ((rtTel == null) ? 0 : rtTel.hashCode());
		result = prime * result + rtTerm;
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		result = prime * result + ((usableTable == null) ? 0 : usableTable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (bookmarkCheck != other.bookmarkCheck)
			return false;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		if (rtAddress == null) {
			if (other.rtAddress != null)
				return false;
		} else if (!rtAddress.equals(other.rtAddress))
			return false;
		if (rtCapacity != other.rtCapacity)
			return false;
		if (rtClose == null) {
			if (other.rtClose != null)
				return false;
		} else if (!rtClose.equals(other.rtClose))
			return false;
		if (rtDeposit != other.rtDeposit)
			return false;
		if (rtField == null) {
			if (other.rtField != null)
				return false;
		} else if (!rtField.equals(other.rtField))
			return false;
		if (rtHoliday == null) {
			if (other.rtHoliday != null)
				return false;
		} else if (!rtHoliday.equals(other.rtHoliday))
			return false;
		if (rtImg == null) {
			if (other.rtImg != null)
				return false;
		} else if (!rtImg.equals(other.rtImg))
			return false;
		if (rtName == null) {
			if (other.rtName != null)
				return false;
		} else if (!rtName.equals(other.rtName))
			return false;
		if (rtNum != other.rtNum)
			return false;
		if (rtOpen == null) {
			if (other.rtOpen != null)
				return false;
		} else if (!rtOpen.equals(other.rtOpen))
			return false;
		if (rtPicture == null) {
			if (other.rtPicture != null)
				return false;
		} else if (!rtPicture.equals(other.rtPicture))
			return false;
		if (rtTel == null) {
			if (other.rtTel != null)
				return false;
		} else if (!rtTel.equals(other.rtTel))
			return false;
		if (rtTerm != other.rtTerm)
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		} else if (!table.equals(other.table))
			return false;
		if (usableTable == null) {
			if (other.usableTable != null)
				return false;
		} else if (!usableTable.equals(other.usableTable))
			return false;
		return true;
	}


}
