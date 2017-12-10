package com.noshow.vo;

public class Holiday {

	private String holidayName;
	private String holidayVal;

	public Holiday() {
	}

	public Holiday(String holidayName, String holidayVal) {
		this.holidayName = holidayName;
		this.holidayVal = holidayVal;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getHolidayVal() {
		return holidayVal;
	}

	public void setHolidayVal(String holidayVal) {
		this.holidayVal = holidayVal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Holiday [holidayName=").append(holidayName).append(", holidayVal=").append(holidayVal)
				.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((holidayName == null) ? 0 : holidayName.hashCode());
		result = prime * result + ((holidayVal == null) ? 0 : holidayVal.hashCode());
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
		Holiday other = (Holiday) obj;
		if (holidayName == null) {
			if (other.holidayName != null)
				return false;
		} else if (!holidayName.equals(other.holidayName))
			return false;
		if (holidayVal == null) {
			if (other.holidayVal != null)
				return false;
		} else if (!holidayVal.equals(other.holidayVal))
			return false;
		return true;
	}

}
