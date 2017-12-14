package com.noshow.vo;

import java.io.Serializable;

public class Ranking implements Serializable{
	private int countRes;
	private double avgGrade;
	private String businessId;
	
	private Restaurant restaurant;
	
	public Ranking() {}

	public Ranking(int countRes, String businessId) {
		super();
		this.countRes = countRes;
		this.businessId = businessId;
	}

	public Ranking(double avgGrade, String businessId) {
		super();
		this.avgGrade = avgGrade;
		this.businessId = businessId;
	}

	public int getCountRes() {
		return countRes;
	}

	public void setCountRes(int countRes) {
		this.countRes = countRes;
	}

	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
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
		return "Ranking [countRes=" + countRes + ", avgGrade=" + avgGrade + ", businessId=" + businessId
				+ ", restaurant=" + restaurant + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(avgGrade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + countRes;
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
		Ranking other = (Ranking) obj;
		if (Double.doubleToLongBits(avgGrade) != Double.doubleToLongBits(other.avgGrade))
			return false;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		if (countRes != other.countRes)
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		return true;
	}

}
