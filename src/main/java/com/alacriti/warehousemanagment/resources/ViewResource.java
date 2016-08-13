package com.alacriti.warehousemanagment.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.alacriti.warehousemanagment.bussinesslogics.ServiceMediator;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.PageVo;

@Path("/")
public class ViewResource {
	@Context HttpServletRequest httpServletRequest;
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
	public String searchFloorDetails(@PathParam("floorId") int floorId,@QueryParam("uniqueId") int uniqueId,
			@QueryParam("offset") int offset,
			@QueryParam("limit") int limit){
		PageVo page=new PageVo();
		page.setLimit(limit);
		page.setOffset(offset);
		page.setUniqueId(uniqueId);
		String s;
		s=serviceMediator.searchByFloor(floorId,page);
		return s;
	}
	@GET
	@Path("/orders")
	public String getOrders(){
		return serviceMediator.getOrders();
	}
	@GET
	@Path("/logout")
	public Response logOut(){
		HttpSession session=httpServletRequest.getSession(false);
		session.invalidate();
		URI url=null;
		try {
			url=new URI("http://localhost:8080/WarehouseServices/");
		} catch (URISyntaxException e) {
			LoggerObject.errorLog(e);
		}
		return Response.seeOther(url).build();
	}
}
