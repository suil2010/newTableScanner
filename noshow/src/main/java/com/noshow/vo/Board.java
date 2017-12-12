package com.noshow.vo;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable{
	private int boardNum; /*글 번호*/
	private String boardSubject; /*글 제목*/
	private String boardText; /*글 내용*/
	private int boardViews; /*조회수*/
	private Date boardTime; /*작성시간*/
	private String memberId; /*작성자아이디*/
	
	public Board() {}

	public Board(String boardSubject, String boardText, String memberId) {
		this.boardSubject = boardSubject;
		this.boardText = boardText;
		this.memberId = memberId;
	}

	public Board(int boardNum, String boardSubject, String boardText, int boardViews, Date boardTime, String memberId) {
		this.boardNum = boardNum;
		this.boardSubject = boardSubject;
		this.boardText = boardText;
		this.boardViews = boardViews;
		this.boardTime = boardTime;
		this.memberId = memberId;
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
		return boardTime;
	}

	public void setBoardTime(Date boardTime) {
		this.boardTime = boardTime;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Board [boardNum=").append(boardNum).append(", boardSubject=").append(boardSubject)
				.append(", boardText=").append(boardText).append(", boardViews=").append(boardViews)
				.append(", boardTime=").append(boardTime).append(", memberId=").append(memberId).append("]");
		return builder.toString();
	}
	

	
}
