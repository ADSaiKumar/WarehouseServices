package com.alacriti.warehouseservices.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("users")
public class UserResource {
	
	@GET
	@Path("/{userId}")
	public String getService(@PathParam("userId") String userId) {
		return this.getClass()+userId;
	}
}