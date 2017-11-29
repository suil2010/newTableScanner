package com.noshow.vo;

import java.io.Serializable;

public class Classification implements Serializable{
	private int classificationNum; /* 대분류리스트번호 */
	private String businessId; /* 점주회원아이디 */
	private String classificationName; /* 대분류명 */
	
	public Classification() {}
	
	public Classification(int classificationNum, String businessId, String classificationName) {
		this.classificationNum = classificationNum;
		this.businessId = businessId;
		this.classificationName = classificationName;
	}

	public int getClassificationNum() {
		return classificationNum;
	}

	public void setClassificationNum(int classificationNum) {
		this.classificationNum = classificationNum;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getClassificationName() {
		return classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	@Override
	public String toString() {
		return "Classification [classificationNum=" + classificationNum + ", businessId=" + businessId
				+ ", classificationName=" + classificationName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((classificationName == null) ? 0 : classificationName.hashCode());
		result = prime * result + classificationNum;
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
		Classification other = (Classification) obj;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		if (classificationName == null) {
			if (other.classificationName != null)
				return false;
		} else if (!classificationName.equals(other.classificationName))
			return false;
		if (classificationNum != other.classificationNum)
			return false;
		return true;
	}
	
}
