package com.noshow.vo;

import java.io.Serializable;

public class RtCode implements Serializable{
	private int codeNum; /* 코드번호 */
	private String codeVal; /* 코드 값 */
	private String codePart; /* 코드분류 */
	
	public RtCode() {}
	
	public RtCode(int codeNum, String codeVal, String codePart) {
		this.codeNum = codeNum;
		this.codeVal = codeVal;
		this.codePart = codePart;
	}
	
	public int getCodeNum() {
		return codeNum;
	}
	public void setCodeNum(int codeNum) {
		this.codeNum = codeNum;
	}
	public String getCodeVal() {
		return codeVal;
	}
	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}
	public String getCodePart() {
		return codePart;
	}
	public void setCodePart(String codePart) {
		this.codePart = codePart;
	}
	
	@Override
	public String toString() {
		return "RtCode [codeNum=" + codeNum + ", codeVal=" + codeVal + ", codePart=" + codePart + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codeNum;
		result = prime * result + ((codePart == null) ? 0 : codePart.hashCode());
		result = prime * result + ((codeVal == null) ? 0 : codeVal.hashCode());
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
		RtCode other = (RtCode) obj;
		if (codeNum != other.codeNum)
			return false;
		if (codePart == null) {
			if (other.codePart != null)
				return false;
		} else if (!codePart.equals(other.codePart))
			return false;
		if (codeVal == null) {
			if (other.codeVal != null)
				return false;
		} else if (!codeVal.equals(other.codeVal))
			return false;
		return true;
	}
	
	
}
