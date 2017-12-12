package com.noshow.vo;

import java.io.Serializable;

public class Question implements Serializable{
	private int questionNum;
	private String memberId;
	private String questionText;
	private String questionTime;
	private String businessId;
	
	private Member member;
	private Restaurant restaurant;
	private Answer answer;
	
	public Question() {}
	public Question(String memberId, String questionText) {
		super();
		this.memberId = memberId;
		this.questionText = questionText;
	}
	public Question(int questionNum, String memberId, String questionText, String questionTime, String businessId) {
		super();
		this.questionNum = questionNum;
		this.memberId = memberId;
		this.questionText = questionText;
		this.questionTime = questionTime;
		this.businessId = businessId;
	}

	public Question(int questionNum, String memberId, String questionText, String questionTime, String businessId,
			Member member, Restaurant restaurant, Answer answer) {
		super();
		this.questionNum = questionNum;
		this.memberId = memberId;
		this.questionText = questionText;
		this.questionTime = questionTime;
		this.businessId = businessId;
		this.member = member;
		this.restaurant = restaurant;
		this.answer = answer;
	}
	public int getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getQuestionTime() {
		return questionTime;
	}
	public void setQuestionTime(String questionTime) {
		this.questionTime = questionTime;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Question [questionNum=" + questionNum + ", memberId=" + memberId + ", questionText=" + questionText
				+ ", questionTime=" + questionTime + ", businessId=" + businessId + ", member=" + member
				+ ", restaurant=" + restaurant + ", answer=" + answer + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + questionNum;
		result = prime * result + ((questionText == null) ? 0 : questionText.hashCode());
		result = prime * result + ((questionTime == null) ? 0 : questionTime.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
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
		Question other = (Question) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
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
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (questionNum != other.questionNum)
			return false;
		if (questionText == null) {
			if (other.questionText != null)
				return false;
		} else if (!questionText.equals(other.questionText))
			return false;
		if (questionTime == null) {
			if (other.questionTime != null)
				return false;
		} else if (!questionTime.equals(other.questionTime))
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		return true;
	}

}
