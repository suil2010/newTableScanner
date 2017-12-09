package com.noshow.vo;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable{
	private int boardNum; /*글 번호*/
	private String boardSubject; /*글 제목*/
	private String boardText; /*글 내용*/
	private int boardViews; /*조회수*/
	private Date BoardTime; /*작성시간*/
	private Member MemberId; /*작성자아이디*/
	
	public Board() {}

	public Board(int boardNum, String boardSubject, String boardText, int boardViews, Date boardTime, Member memberId) {
		super();
		this.boardNum = boardNum;
		this.boardSubject = boardSubject;
		this.boardText = boardText;
		this.boardViews = boardViews;
		BoardTime = boardTime;
		MemberId = memberId;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardSubject() {
		return boardSubject;
	}

	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}

	public String getBoardText() {
		return boardText;
	}

	public void setBoardText(String boardText) {
		this.boardText = boardText;
	}

	public int getBoardViews() {
		return boardViews;
	}

	public void setBoardViews(int boardViews) {
		this.boardViews = boardViews;
	}

	public Date getBoardTime() {
		return BoardTime;
	}

	public void setBoardTime(Date boardTime) {
		BoardTime = boardTime;
	}

	public Member getMemberId() {
		return MemberId;
	}

	public void setMemberId(Member memberId) {
		MemberId = memberId;
	}

	@Override
	public String toString() {
		return "Board [boardNum=" + boardNum + ", boardSubject=" + boardSubject + ", boardText=" + boardText
				+ ", boardViews=" + boardViews + ", BoardTime=" + BoardTime + ", MemberId=" + MemberId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BoardTime == null) ? 0 : BoardTime.hashCode());
		result = prime * result + ((MemberId == null) ? 0 : MemberId.hashCode());
		result = prime * result + boardNum;
		result = prime * result + ((boardSubject == null) ? 0 : boardSubject.hashCode());
		result = prime * result + ((boardText == null) ? 0 : boardText.hashCode());
		result = prime * result + boardViews;
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
		Board other = (Board) obj;
		if (BoardTime == null) {
			if (other.BoardTime != null)
				return false;
		} else if (!BoardTime.equals(other.BoardTime))
			return false;
		if (MemberId == null) {
			if (other.MemberId != null)
				return false;
		} else if (!MemberId.equals(other.MemberId))
			return false;
		if (boardNum != other.boardNum)
			return false;
		if (boardSubject == null) {
			if (other.boardSubject != null)
				return false;
		} else if (!boardSubject.equals(other.boardSubject))
			return false;
		if (boardText == null) {
			if (other.boardText != null)
				return false;
		} else if (!boardText.equals(other.boardText))
			return false;
		if (boardViews != other.boardViews)
			return false;
		return true;
	}
	
	
}
