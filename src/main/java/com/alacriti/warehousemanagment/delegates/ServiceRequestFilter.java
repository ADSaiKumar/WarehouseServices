package com.alacriti.warehousemanagment.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceRequestFilter implements ClientRequestFilter{
	
	@Context HttpServletRequest httpServletRequest;
	public void filter(ClientRequestContext arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
