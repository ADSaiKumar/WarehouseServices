package com.alacriti.warehouseservices.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class OrdersResource {
	
	@GET
	public String getService() {
		return this.getClass()+" Orders ";
	}
}