package com.alacriti.warehousemanagment.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ManagerSecurityFilter implements ContainerRequestFilter{
	public static final CharSequence LOGIN_PATH = "/post/login";
	public static final CharSequence GLOGIN_PATH = "/post/glogin";
	public static final CharSequence REGISTER_PATH = "/post/register";
	@Context HttpServletRequest httpServletRequest;
	public void filter(ContainerRequestContext context) throws IOException {
		HttpSession session=httpServletRequest.getSession(false);
		if(context.getUriInfo().getPath().contains(LOGIN_PATH)){
			return ;
		}
		if(context.getUriInfo().getPath().contains(GLOGIN_PATH)){
			return ;
		}
		if(context.getUriInfo().getPath().contains(REGISTER_PATH)){
			return ;
		}
		if(session!=null){
			return ;
		}else{
			Response forbidden=Response.status(Response.Status.FORBIDDEN)
					.entity("Access Forbidden")
					.build();
			context.abortWith(forbidden);
		}
	}

}
