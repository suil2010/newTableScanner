package com.noshow.vo;

import java.io.Serializable;

public class Table implements Serializable {
	private int tableSeq; /* 테이블리스트 번호 */
	private int tableNum; /* 테이블 번호 */
	private int tablePeople; /* 인원 */
	private String xLocation; /* x좌표 */
	private String yLocation; /* y좌표 */
	private String businessId; /* 점주 회원 아이디 */
	
	private Restaurant restaurant;
	
	public Table() {
	}
	
	public Table(int tableSeq, int tableNum, int tablePeople, String xLocation, String yLocation, String businessId) {
		this.tableSeq = tableSeq;
		this.tableNum = tableNum;
		this.tablePeople = tablePeople;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.businessId = businessId;
	}
	
	public int getTableSeq() {
		return tableSeq;
	}
	
	public void setTableSeq(int tableSeq) {
		this.tableSeq = tableSeq;
	}
	
	public int getTableNum() {
		return tableNum;
	}
	
	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}
	
	public int getTablePeople() {
		return tablePeople;
	}
	
	public void setTablePeople(int tablePeople) {
		this.tablePeople = tablePeople;
	}
	
	public String getxLocation() {
		return xLocation;
	}
	
	public void setxLocation(String xLocation) {
		this.xLocation = xLocation;
	}
	
	public String getyLocation() {
		return yLocation;
	}
	
	public void setyLocation(String yLocation) {
		this.yLocation = yLocation;
	}
	
	public String getBusinessId() {
		return businessId;
	}
	
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	@Override
	public String toString() {
		return "Table [tableSeq=" + tableSeq + ", tableNum=" + tableNum + ", tablePeople=" + tablePeople + ", xLocation=" + xLocation + ", yLocation="
				+ yLocation + ", businessId=" + businessId + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
		result = prime * result + tableNum;
		result = prime * result + tablePeople;
		result = prime * result + tableSeq;
		result = prime * result + ((xLocation == null) ? 0 : xLocation.hashCode());
		result = prime * result + ((yLocation == null) ? 0 : yLocation.hashCode());
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
		Table other = (Table) obj;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		if (tableNum != other.tableNum)
			return false;
		if (tablePeople != other.tablePeople)
			return false;
		if (tableSeq != other.tableSeq)
			return false;
		if (xLocation == null) {
			if (other.xLocation != null)
				return false;
		} else if (!xLocation.equals(other.xLocation))
			return false;
		if (yLocation == null) {
			if (other.yLocation != null)
				return false;
		} else if (!yLocation.equals(other.yLocation))
			return false;
		return true;
	}
	
}