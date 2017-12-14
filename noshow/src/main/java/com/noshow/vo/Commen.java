package com.noshow.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.noshow.util.DateJsonSerializer;

public class Commen implements Serializable{
	private int commenNum; /*댓글일련번호*/
	private int boardNum; /*글번호*/
	private String writerId; /*작성자 아이디*/
	private String commenText; /*댓글내용*/
	
	@JsonSerialize(using=DateJsonSerializer.class)
	private Date commenWritingTime; /*작성시간*/
	
	public Commen() {}

	
	public Commen(String commenText) {
		this.commenText = commenText;
	}


	public Commen(int commenNum, int boardNum, String writerId, String commenText, Date commenWritingTime) {
		this.commenNum = commenNum;
		this.boardNum = boardNum;
		this.writerId = writerId;
		this.commenText = commenText;
		this.commenWritingTime = commenWritingTime;
	}

	public int getCommenNum() {
		return commenNum;
	}

	public void setCommenNum(int commenNum) {
		this.commenNum = commenNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getCommenText() {
		return commenText;
	}

	public void setCommenText(String commenText) {
		this.commenText = commenText;
	}

	public Date getCommenWritingTime() {
		return commenWritingTime;
	}

	public void setCommenWritingTime(Date commenWritingTime) {
		this.commenWritingTime = commenWritingTime;
	}

	@Override
	public String toString() {
		return "Commen [commenNum=" + commenNum + ", boardNum=" + boardNum + ", writerId=" + writerId + ", commenText="
				+ commenText + ", commenWritingTime=" + commenWritingTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardNum;
		result = prime * result + commenNum;
		result = prime * result + ((commenText == null) ? 0 : commenText.hashCode());
		result = prime * result + ((commenWritingTime == null) ? 0 : commenWritingTime.hashCode());
		result = prime * result + ((writerId == null) ? 0 : writerId.hashCode());
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
		Commen other = (Commen) obj;
		if (boardNum != other.boardNum)
			return false;
		if (commenNum != other.commenNum)
			return false;
		if (commenText == null) {
			if (other.commenText != null)
				return false;
		} else if (!commenText.equals(other.commenText))
			return false;
		if (commenWritingTime == null) {
			if (other.commenWritingTime != null)
				return false;
		} else if (!commenWritingTime.equals(other.commenWritingTime))
			return false;
		if (writerId == null) {
			if (other.writerId != null)
				return false;
		} else if (!writerId.equals(other.writerId))
			return false;
		return true;
	}

	

}
