package com.noshow.vo;

import java.io.Serializable;

public class Answer implements Serializable{
	
	private int answerNum;
	private String answerDate;
	private String answerText;
	private int questionNum;
	private String businessId;
	
	public Answer() {}

	public Answer(String answerText, int questionNum, String businessId) {
		super();
		this.answerText = answerText;
		this.questionNum = questionNum;
		this.businessId = businessId;
	}

	public Answer(int answerNum, String answerDate, String answerText, int questionNum, String businessId) {
		super();
		this.answerNum = answerNum;
		this.answerDate = answerDate;
		this.answerText = answerText;
		this.questionNum = questionNum;
		this.businessId = businessId;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	public String getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public int getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Override
	public String toString() {
		return "Answer [answerNum=" + answerNum + ", answerDate=" + answerDate + ", answerText=" + answerText
				+ ", questionNum=" + questionNum + ", businessId=" + businessId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerDate == null) ? 0 : answerDate.hashCode());
		result = prime * result + answerNum;
		result = prime * result + ((answerText == null) ? 0 : answerText.hashCode());
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + questionNum;
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
		Answer other = (Answer) obj;
		if (answerDate == null) {
			if (other.answerDate != null)
				return false;
		} else if (!answerDate.equals(other.answerDate))
			return false;
		if (answerNum != other.answerNum)
			return false;
		if (answerText == null) {
			if (other.answerText != null)
				return false;
		} else if (!answerText.equals(other.answerText))
			return false;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		if (questionNum != other.questionNum)
			return false;
		return true;
	}
	
}
