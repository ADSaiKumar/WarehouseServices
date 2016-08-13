package com.alacriti.warehouseservices.vo;

import java.sql.Date;



public class CheckOutVo {
	private int checkOutId;
	private Date checkOutDate;
	private ItemVo item;
	
	public CheckOutVo() {
	}
	public CheckOutVo(int checkOutId, Date checkOutDate, ItemVo item) {
		super();
		this.checkOutId = checkOutId;
		this.checkOutDate = checkOutDate;
		this.item = item;
	}
	public int getCheckOutId() {
		return checkOutId;
	}
	public void setCheckOutId(int checkOutId) {
		this.checkOutId = checkOutId;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public ItemVo getItem() {
		return item;
	}
	public void setItem(ItemVo item) {
		this.item = item;
	}
	
}
