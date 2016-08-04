package com.alacriti.warehousemanagment.resources;

import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.alacriti.warehousemanagment.bussinesslogics.PostDataMediator;
import com.alacriti.warehousemanagment.dataobjects.OrderSummary;

@Path("/post")
public class PostResource {
	@FormParam("itemId") int itemId;
	@FormParam("itemQuantity") int itemQuantity;
	private PostDataMediator postDataMediator=new PostDataMediator();
	
	@GET
	public String getStockToWarehouse(){
		return "This is fucking working man..";
	}
	
	@Path("/additem")
	@POST
	public String addStockToWarehouse(){
		return postDataMediator.addStockToWarehouse(itemId,itemQuantity);
	}
	
	@Path("/checkoutitem")
	@POST
	public String checkOutItem(){
		return postDataMediator.removeStockFromWarehouse(itemId,itemQuantity);
	}
	
	@Path("/orders")
	@POST
	public String placeOrders(@BeanParam OrderSummary orderSummary){
		return postDataMediator.placeOrders(orderSummary.getOrderSummary());
	}
}
