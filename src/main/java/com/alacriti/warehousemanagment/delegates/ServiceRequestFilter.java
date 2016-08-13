package com.alacriti.warehousemanagment.delegates;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;

import com.alacriti.warehouseservices.vo.LoggerObject;

@Provider
public class ServiceRequestFilter implements ClientRequestFilter{
	private static final String USER_PATH="/users";
	public void filter(ClientRequestContext context) throws IOException {
		if(context.getUri().getPath().contains(USER_PATH)){
			LoggerObject.infoLog("Filter is gone through");
			return ;
		}
		String tokenId=ManagerSecurityFilter.sessionUser.getToken();
		LoggerObject.infoLog("Filter "+tokenId);
		context.getHeaders().add("Authorization", "Bearer "+tokenId);
	}

}
