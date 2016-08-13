package com.alacriti.warehouseservices.utilities;

public class ExtraRemovalException extends Exception {
	private static final String MESSAGE="STOCK IS NOT AVAILABLE IN PLACEHOLDER";
	public ExtraRemovalException(){
		super(MESSAGE);
	}
	public ExtraRemovalException(String message){
		super(message);
	}
	
}
