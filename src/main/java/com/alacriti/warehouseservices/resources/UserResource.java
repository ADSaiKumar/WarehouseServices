package com.alacriti.warehouseservices.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.warehouseservices.bo.UserBo;
import com.alacriti.warehouseservices.bo.impl.UserBoImpl;
import com.alacriti.warehouseservices.delegates.UserDelegate;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.UserVo;
	
@Path("users")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class UserResource {
	private UserBo userBo=new UserBoImpl();
	private UserDelegate userDelegate=new UserDelegate();
	@GET
	public UserVo getUser(@HeaderParam("userEmail") String userEmail) {
		UserVo userVo= userBo.getUserDetails(userEmail);
		if(userVo!=null){
			String token=userBo.getToken(userVo);
			userVo.setToken(token);
			}
		return userVo;
	}
	@Path("/google")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response gLoginService(@FormParam("tokenId") String tokenId){
		LoggerObject.infoLog(tokenId);
		Response response=userDelegate.gValidate(tokenId);
		return response;
	}
	@POST
	public UserVo registerUser(UserVo user) {
		UserVo userVo= userDelegate.registerUser(user);
		return userVo;
	}
}