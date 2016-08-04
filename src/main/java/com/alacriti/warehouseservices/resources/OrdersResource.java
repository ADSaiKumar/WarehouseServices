package com.alacriti.warehouseservices.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alacriti.warehouseservices.bo.OrderBo;
import com.alacriti.warehouseservices.bo.impl.OrderBoImpl;
import com.alacriti.warehouseservices.vo.OrderVo;

@Path("/")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class OrdersResource {
	OrderBo orderBo=new OrderBoImpl();
	@GET
	public List<OrderVo> getService() {
		return orderBo.checkOrder();
	}
	@POST
	public List<OrderVo> setService(List<OrderVo> orderSummary) {
		return orderBo.setOrder(orderSummary);
	}
}