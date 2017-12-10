package com.noshow.vo;

import java.io.Serializable;

public class Term implements Serializable {

	private String termName;
	private String termVal;

	public Term() {
	}

	public Term(String termName, String termVal) {
		this.termName = termName;
		this.termVal = termVal;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getTermVal() {
		return termVal;
	}

	public void setTermVal(String termVal) {
		this.termVal = termVal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Term [termName=").append(termName).append(", termVal=").append(termVal).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((termName == null) ? 0 : termName.hashCode());
		result = prime * result + ((termVal == null) ? 0 : termVal.hashCode());
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
		Term other = (Term) obj;
		if (termName == null) {
			if (other.termName != null)
				return false;
		} else if (!termName.equals(other.termName))
			return false;
		if (termVal == null) {
			if (other.termVal != null)
				return false;
		} else if (!termVal.equals(other.termVal))
			return false;
		return true;
	}

}
