package com.noshow.vo;

import java.io.Serializable;

public class Menu implements Serializable{
	private int menuNum; /* 메뉴번호 */
	private String menuName; /* 메뉴이름 */
	private String menuComment; /* 메뉴설명 */
	private int menuPrice; /* 메뉴가격 */
	private String menuPicture; /* 메뉴사진이름 */
	private int classificationNum;
	private String businessId;
	
	private Classification classification; /* 대분류리스트번호 */
	private Restaurant business; /* 점주회원아이디 */
	
	public Menu() {}

	public Menu(int menuNum, String menuName, String menuComment, int menuPrice, String menuPicture,
			int classificationNum, String businessId) {
		this.menuNum = menuNum;
		this.menuName = menuName;
		this.menuComment = menuComment;
		this.menuPrice = menuPrice;
		this.menuPicture = menuPicture;
		this.classificationNum = classificationNum;
		this.businessId = businessId;
	}

	public int getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuComment() {
		return menuComment;
	}

	public void setMenuComment(String menuComment) {
		this.menuComment = menuComment;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuPicture() {
		return menuPicture;
	}

	public void setMenuPicture(String menuPicture) {
		this.menuPicture = menuPicture;
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

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public Restaurant getBusiness() {
		return business;
	}

	public void setBusiness(Restaurant business) {
		this.business = business;
	}

	@Override
	public String toString() {
		return "Menu [menuNum=" + menuNum + ", menuName=" + menuName + ", menuComment=" + menuComment + ", menuPrice="
				+ menuPrice + ", menuPicture=" + menuPicture + ", classificationNum=" + classificationNum
				+ ", businessId=" + businessId + ", classification=" + classification + ", business=" + business + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((business == null) ? 0 : business.hashCode());
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((classification == null) ? 0 : classification.hashCode());
		result = prime * result + classificationNum;
		result = prime * result + ((menuComment == null) ? 0 : menuComment.hashCode());
		result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + menuNum;
		result = prime * result + ((menuPicture == null) ? 0 : menuPicture.hashCode());
		result = prime * result + menuPrice;
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
		Menu other = (Menu) obj;
		if (business == null) {
			if (other.business != null)
				return false;
		} else if (!business.equals(other.business))
			return false;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		if (classification == null) {
			if (other.classification != null)
				return false;
		} else if (!classification.equals(other.classification))
			return false;
		if (classificationNum != other.classificationNum)
			return false;
		if (menuComment == null) {
			if (other.menuComment != null)
				return false;
		} else if (!menuComment.equals(other.menuComment))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (menuNum != other.menuNum)
			return false;
		if (menuPicture == null) {
			if (other.menuPicture != null)
				return false;
		} else if (!menuPicture.equals(other.menuPicture))
			return false;
		if (menuPrice != other.menuPrice)
			return false;
		return true;
	}

	
	
}
