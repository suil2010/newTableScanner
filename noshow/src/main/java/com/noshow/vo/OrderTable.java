package com.noshow.vo;

import java.io.Serializable;

import com.noshow.vo.OrderTable;
import com.noshow.vo.Reservation;
import com.noshow.vo.Table;

public class OrderTable implements Serializable{
	private int tableSeq; /* 테이블리스트번호 */
	private int resNum; /* 예약리스트번호 */
	
	private Table table;
	private Reservation reservation;
	
	public OrderTable() {}

	public OrderTable(int tableSeq, int resNum) {
		this.tableSeq = tableSeq;
		this.resNum = resNum;
	}

	public int getTableSeq() {
		return tableSeq;
	}

	public void setTableSeq(int tableSeq) {
		this.tableSeq = tableSeq;
	}

	public int getResNum() {
		return resNum;
	}

	public void setResNum(int resNum) {
		this.resNum = resNum;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "OrderTable [tableSeq=" + tableSeq + ", resNum=" + resNum + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + resNum;
		result = prime * result + ((table == null) ? 0 : table.hashCode());
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
		OrderTable other = (OrderTable) obj;
		if (resNum != other.resNum)
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		} else if (!table.equals(other.table))
			return false;
		return true;
	}
}
