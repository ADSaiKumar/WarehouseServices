package com.alacriti.warehouseservices.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.warehouseservices.bo.WarehouseBo;
import com.alacriti.warehouseservices.bo.impl.WarehouseBoImpl;
import com.alacriti.warehouseservices.utilities.ExtraRemovalException;
import com.alacriti.warehouseservices.vo.CheckOutVo;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.PageVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

@Path("/warehouse")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class WarehouseResource {
	public WarehouseBo warehouseBo=new WarehouseBoImpl();
	@GET
	public List<FloorVo> getService() {
		return warehouseBo.getDetails();
	}
	@GET
	@Path("/storage")
	public List<ItemVo> getStorage() {
		return warehouseBo.getStorageDetails();
	}
	@POST
	public PlaceholderVo addService(ItemVo item){
		return warehouseBo.addStock(item);
	}
	
	@PUT
	public Response updateService(ItemVo item){
		PlaceholderVo placeholder;
		try{
			placeholder=warehouseBo.checkOutItem(item);
		}catch(ExtraRemovalException ere){
			return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(ere.getMessage()).build();
		}
		return  Response.status(Response.Status.OK).entity(placeholder).build();
	}
	
	@Path("/items")
	public ItemResource getItemsService() {
		return new ItemResource();
	}
	
	@Path("/orders")
	public OrdersResource getOrderService() {
		return new OrdersResource();
	}
	@GET
	@Path("/checkouts")
	public List<CheckOutVo> getCheckouts() {
		return warehouseBo.getCheckOutDetails();
	}
	@GET
	@Path("/{floorId}")
	public PageVo getService(@PathParam("floorId") int floorId,@QueryParam("uniqueId") int uniqueId,
			@QueryParam("offset") int offset,
			@QueryParam("limit") int limit) {
		PageVo page=new PageVo();
		if(uniqueId!=0 && limit !=0){
			page.setLimit(limit);
			page.setOffset(offset);
			page.setUniqueId(uniqueId);
			page=warehouseBo.getPage(page);
		}else{
		page=warehouseBo.getDetails(floorId);
		}
		return page;
	}
}