package com.noshow.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Review implements Serializable{
	private int reviewNum;
	private String reviewText;
	private String reviewTime;
	private String reviewImageName;
	private MultipartFile reviewImage;
	private int reviewGrade;
	private String memberId;
	private String businessId;
	private int resNum;
	
	private Member member;
	private Restaurant restaurant;
	private Reservation reservation;
	
	public Review() {}
	
	public Review(String reviewText, int reviewGrade, String memberId, String businessId, int resNum) {
		this.reviewText = reviewText;
		this.reviewGrade = reviewGrade;
		this.memberId = memberId;
		this.businessId = businessId;
		this.resNum = resNum;
	}

	public Review(String reviewText, String reviewImageName, int reviewGrade, String memberId, String businessId, int resNum) {
		this.reviewText = reviewText;
		this.reviewImageName = reviewImageName;
		this.reviewGrade = reviewGrade;
		this.memberId = memberId;
		this.businessId = businessId;
		this.resNum = resNum;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewImageName() {
		return reviewImageName;
	}

	public void setReviewImageName(String reviewImageName) {
		this.reviewImageName = reviewImageName;
	}

	public MultipartFile getReviewImage() {
		return reviewImage;
	}

	public void setReviewImage(MultipartFile reviewImage) {
		this.reviewImage = reviewImage;
	}

	public int getReviewGrade() {
		return reviewGrade;
	}

	public void setReviewGrade(int reviewGrade) {
		this.reviewGrade = reviewGrade;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public int getResNum() {
		return resNum;
	}

	public void setResNum(int resNum) {
		this.resNum = resNum;
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

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Review [reviewNum=" + reviewNum + ", reviewText=" + reviewText + ", reviewTime=" + reviewTime
				+ ", reviewImageName=" + reviewImageName + ", reviewImage=" + reviewImage + ", reviewGrade="
				+ reviewGrade + ", memberId=" + memberId + ", businessId=" + businessId + ", resNum=" + resNum
				+ ", member=" + member + ", restaurant=" + restaurant + ", reservation=" + reservation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + resNum;
		result = prime * result + ((reservation == null) ? 0 : reservation.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
		result = prime * result + reviewGrade;
		result = prime * result + ((reviewImage == null) ? 0 : reviewImage.hashCode());
		result = prime * result + ((reviewImageName == null) ? 0 : reviewImageName.hashCode());
		result = prime * result + reviewNum;
		result = prime * result + ((reviewText == null) ? 0 : reviewText.hashCode());
		result = prime * result + ((reviewTime == null) ? 0 : reviewTime.hashCode());
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
		Review other = (Review) obj;
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
		if (resNum != other.resNum)
			return false;
		if (reservation == null) {
			if (other.reservation != null)
				return false;
		} else if (!reservation.equals(other.reservation))
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		if (reviewGrade != other.reviewGrade)
			return false;
		if (reviewImage == null) {
			if (other.reviewImage != null)
				return false;
		} else if (!reviewImage.equals(other.reviewImage))
			return false;
		if (reviewImageName == null) {
			if (other.reviewImageName != null)
				return false;
		} else if (!reviewImageName.equals(other.reviewImageName))
			return false;
		if (reviewNum != other.reviewNum)
			return false;
		if (reviewText == null) {
			if (other.reviewText != null)
				return false;
		} else if (!reviewText.equals(other.reviewText))
			return false;
		if (reviewTime == null) {
			if (other.reviewTime != null)
				return false;
		} else if (!reviewTime.equals(other.reviewTime))
			return false;
		return true;
	}
	
}
