package com.alacriti.warehouseservices.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemVo {
	private int itemId;
	private String itemName;
	private int quantity;
	public ItemVo(){
		
	}
	
	public ItemVo(int itemId, String itemName, int quantity) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ItemVo [itemId=" + itemId + ", itemName=" + itemName
				+ ", quantity=" + quantity + "]";
	}
}