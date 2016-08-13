package com.alacriti.warehousemanagment.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.alacriti.warehousemanagment.bussinesslogics.PageSetter;
import com.alacriti.warehouseservices.vo.UserVo;

@Provider
public class ManagerSecurityFilter implements ContainerRequestFilter{
	public static final CharSequence LOGIN_PATH = "/post/login";
	public static final CharSequence GLOGIN_PATH = "/post/glogin";
	public static final CharSequence REGISTER_PATH = "/post/register";
	public static UserVo sessionUser;
	PageSetter pageSetter=new PageSetter();
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
			sessionUser=(UserVo)session.getAttribute("userSession");
			return ;
		}else{
			String response=pageSetter.getRawTempleteString("forbidden.ftl",null);
			Response forbidden=Response.status(Response.Status.FORBIDDEN)
					.entity(response)
					.build();
			context.abortWith(forbidden);
		}
	}

}
