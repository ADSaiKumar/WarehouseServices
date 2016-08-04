package com.alacriti.warehousemanagment.resources;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.alacriti.warehousemanagment.bussinesslogics.ServiceMediator;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.LoggerObject;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Path("/")
public class ViewResource {
	private ServiceMediator serviceMediator=new ServiceMediator();
	@GET
	@Path("/dashboard")
	public String getDasboardDetails(){
		
		return serviceMediator.getDashboardPage();
	}
	@GET
	@Path("/addstock")
	public String addItemDetails(){
		
		return serviceMediator.getAddStockPage();
	}
	@GET
	@Path("/checkoutstock")
	public String checkOutDetails(){
		
		return serviceMediator.getCheckOutStockPage();
	}
	@GET
	@Path("/search")
	public String searchDetails(){
		
		return serviceMediator.searchStockPage();
	}
	@GET
	@Path("/search/item/{itemId}")
	public String searchItemDetails(@PathParam("itemId") int itemId){
		
		return serviceMediator.searchByItem(itemId);
	}
	@GET
	@Path("/search/floor/{floorId}")
	public String searchFloorDetails(@PathParam("floorId") int floorId){
		return serviceMediator.searchByFloor(floorId);
	}
	@GET
	@Path("/orders")
	public String getOrders(){
		return serviceMediator.getOrders();
	}
}
