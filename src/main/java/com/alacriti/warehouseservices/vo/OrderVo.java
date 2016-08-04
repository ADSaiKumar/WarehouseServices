package com.alacriti.warehouseservices.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderVo {
	private Date orderDate;
	private int orderId;
	private ItemVo item;
	private int orderAmount;
	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public OrderVo(){
		
	}
	
	public OrderVo(Date orderDate, int orderId, ItemVo item,int orderAmount) {
		this.orderDate = orderDate;
		this.orderId = orderId;
		this.item = item;
		this.orderAmount=orderAmount;
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

	@Override
	public String toString() {
		return "OrderVo [orderDate=" + orderDate + ", orderId=" + orderId
				+ ", item=" + item + ", orderAmount=" + orderAmount + "]";
	}
	
}