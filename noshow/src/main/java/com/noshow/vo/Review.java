package com.noshow.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Review implements Serializable{
	private int reviewNum;		/* 후기 글 번호 */
	private String reviewText; /* 후기 글 내용 */
	private String reviewTime; /* 후기 작성 시간 */
	private String reviewImg;  /* 사진 저장 이름 */
	private MultipartFile reviewPicture; /* 사진 */
	private float reviewGrade;	/* 후기 평점 */
	private String memberId;	/* 회원ID */
	private String businessId;	/* 사업자ID */
	private int resNum;			/* 예약 번호 */
	
	private Member member;
	private Restaurant restaurant;
	private Reservation reservation;
	
	public Review() {}

	public Review(String reviewText, MultipartFile reviewPicture, float reviewGrade, String memberId,
			String businessId) {
		super();
		this.reviewText = reviewText;
		this.reviewPicture = reviewPicture;
		this.reviewGrade = reviewGrade;
		this.memberId = memberId;
		this.businessId = businessId;
	}

	public Review(String reviewText, String reviewImg, float reviewGrade, String memberId, String businessId) {
		super();
		this.reviewText = reviewText;
		this.reviewImg = reviewImg;
		this.reviewGrade = reviewGrade;
		this.memberId = memberId;
		this.businessId = businessId;
	}

	public Review(int reviewNum, String reviewText, String reviewTime, String reviewImg, MultipartFile reviewPicture,
			float reviewGrade, String memberId, String businessId, int resNum, Member member, Restaurant restaurant,
			Reservation reservation) {
		super();
		this.reviewNum = reviewNum;
		this.reviewText = reviewText;
		this.reviewTime = reviewTime;
		this.reviewImg = reviewImg;
		this.reviewPicture = reviewPicture;
		this.reviewGrade = reviewGrade;
		this.memberId = memberId;
		this.businessId = businessId;
		this.resNum = resNum;
		this.member = member;
		this.restaurant = restaurant;
		this.reservation = reservation;
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

	public String getReviewImg() {
		return reviewImg;
	}

	public void setReviewImg(String reviewImg) {
		this.reviewImg = reviewImg;
	}

	public MultipartFile getReviewPicture() {
		return reviewPicture;
	}

	public void setReviewPicture(MultipartFile reviewPicture) {
		this.reviewPicture = reviewPicture;
	}

	public float getReviewGrade() {
		return reviewGrade;
	}

	public void setReviewGrade(float reviewGrade) {
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
				+ ", reviewImg=" + reviewImg + ", reviewPicture=" + reviewPicture + ", reviewGrade=" + reviewGrade
				+ ", memberId=" + memberId + ", businessId=" + businessId + ", resNum=" + resNum + ", member=" + member
				+ ", restaurant=" + restaurant + ", reservation=" + reservation + "]";
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
		result = prime * result + Float.floatToIntBits(reviewGrade);
		result = prime * result + ((reviewImg == null) ? 0 : reviewImg.hashCode());
		result = prime * result + reviewNum;
		result = prime * result + ((reviewPicture == null) ? 0 : reviewPicture.hashCode());
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
		if (Float.floatToIntBits(reviewGrade) != Float.floatToIntBits(other.reviewGrade))
			return false;
		if (reviewImg == null) {
			if (other.reviewImg != null)
				return false;
		} else if (!reviewImg.equals(other.reviewImg))
			return false;
		if (reviewNum != other.reviewNum)
			return false;
		if (reviewPicture == null) {
			if (other.reviewPicture != null)
				return false;
		} else if (!reviewPicture.equals(other.reviewPicture))
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
