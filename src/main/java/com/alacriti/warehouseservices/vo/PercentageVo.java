package com.alacriti.warehouseservices.vo;

public class PercentageVo {
	private int min;
	private int max;
	private int quantity;
	public PercentageVo(int min, int max, int quantity) {
		this.min = min;
		this.max = max;
		this.quantity = quantity;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public boolean checkRange(double percent){
		if(percent>min && percent<=max){
			return true;
		}else{
			return false; 
		}
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
