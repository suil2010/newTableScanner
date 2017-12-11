package com.noshow.vo;

import java.io.Serializable;

public class Field implements Serializable{

	private String fieldName;
	private String fieldVal;
	
	public Field() {}
	
	public Field(String fieldName, String fieldVal) {
		this.fieldName = fieldName;
		this.fieldVal = fieldVal;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldVal() {
		return fieldVal;
	}

	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trem [fieldName=").append(fieldName).append(", fieldVal=").append(fieldVal).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((fieldVal == null) ? 0 : fieldVal.hashCode());
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
		Field other = (Field) obj;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (fieldVal == null) {
			if (other.fieldVal != null)
				return false;
		} else if (!fieldVal.equals(other.fieldVal))
			return false;
		return true;
	}

	
}
