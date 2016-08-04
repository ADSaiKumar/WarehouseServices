package com.alacriti.warehousemanagment.dataobjects;

import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class ItemObject {
	private String itemName;
	private String placeholder;
	private int stock;
	private int storage;
	
	public ItemObject() {
	}
	public ItemObject(String itemName, String placeholder, int stock,
			int storage) {
		this.itemName = itemName;
		this.placeholder = placeholder;
		this.stock = stock;
		this.storage = storage;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}
	public static ItemObject getItem(PlaceholderVo placeholder2) {
		String itemName=placeholder2.getItem().getItemName();
		String placeholder=(placeholder2.getPlaceholderId()==null)?"NILL":placeholder2.getPlaceholderId();
		int stock=placeholder2.getStock();
		int storage=placeholder2.getItem().getQuantity()-stock;
		return new ItemObject(itemName,placeholder,stock,storage);
	}
	
}
