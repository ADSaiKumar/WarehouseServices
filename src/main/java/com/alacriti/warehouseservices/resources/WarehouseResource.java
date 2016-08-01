package com.alacriti.warehouseservices.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alacriti.warehouseservices.bo.WarehouseBo;
import com.alacriti.warehouseservices.bo.impl.WarehouseBoImpl;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

@Path("/warehouse")
@Produces({MediaType.TEXT_XML,MediaType.APPLICATION_JSON})
@Consumes(MediaType.TEXT_XML)
public class WarehouseResource {
	public WarehouseBo warehouseBo=new WarehouseBoImpl();
	
	@GET
	public List<FloorVo> getService() {
		return warehouseBo.getDetails();
	}
	
	@POST
	public PlaceholderVo addService(ItemVo item){
		return warehouseBo.addStock(item);
	}
	
	@PUT
	public PlaceholderVo updateService(ItemVo item){
		return warehouseBo.checkOutItem(item);
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
	@Path("/{floorId}")
	
	public FloorVo getService(@PathParam("floorId") int floorId) {
		return warehouseBo.getDetails(floorId);
	}
}