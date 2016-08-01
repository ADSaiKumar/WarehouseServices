package com.alacriti.warehouseservices.vo;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderVo {
	private Date orderDate;
	private int orderId;
	private ItemVo item;
	public OrderVo(){
		
	}
	
	public OrderVo(Date orderDate, int orderId, ItemVo item) {
		this.orderDate = orderDate;
		this.orderId = orderId;
		this.item = item;
	}

	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public ItemVo getItem() {
		return item;
	}
	public void setItem(ItemVo item) {
		this.item = item;
	}
	
}