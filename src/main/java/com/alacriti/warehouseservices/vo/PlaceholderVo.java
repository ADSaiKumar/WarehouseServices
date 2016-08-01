package com.alacriti.warehouseservices.vo;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlaceholderVo {
	private ItemVo item;
	private String placeholderId;
	private int stock;
	private int capacity;

	public PlaceholderVo(){
		
	}

	public PlaceholderVo(ItemVo item, String placeholderId, int stock,
			int capacity) {
		this.item = item;
		this.placeholderId = placeholderId;
		this.stock = stock;
		this.capacity = capacity;
	}

	public ItemVo getItem() {
		return item;
	}

	public void setItem(ItemVo item) {
		this.item = item;
	}

	public String getPlaceholderId() {
		return placeholderId;
	}

	public void setPlaceholderId(String placeholderId) {
		this.placeholderId = placeholderId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "PlaceholderVo [item=" + item + ", placeholderId="
				+ placeholderId + ", stock=" + stock + ", capacity=" + capacity
				+ "]";
	}
	
}
