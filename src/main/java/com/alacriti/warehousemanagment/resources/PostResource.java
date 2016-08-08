package com.alacriti.warehousemanagment.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.warehousemanagment.bussinesslogics.PostDataMediator;
import com.alacriti.warehousemanagment.dataobjects.OrderSummary;
import com.alacriti.warehousemanagment.delegates.LoginDelegate;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.UserVo;

@Path("/post")
public class PostResource {
	@FormParam("itemId") int itemId;
	@FormParam("itemQuantity") int itemQuantity;
	@Context HttpServletRequest httpServletRequest;
	private PostDataMediator postDataMediator=new PostDataMediator();
	private LoginDelegate loginDelegate=new LoginDelegate();
	
	@Path("/addstock")
	@POST
	public String addStockToWarehouse(){
		return postDataMediator.addStockToWarehouse(itemId,itemQuantity);
	}
	@Path("/item")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String addItemtoList(ItemVo item){
		return postDataMediator.addItemToList(item);
	}
	@Path("/checkoutitem")
	@POST
	public String checkOutItem(){
		return postDataMediator.removeStockFromWarehouse(itemId,itemQuantity);
	}
	
	@Path("/orders")
	@POST
	public String placeOrders(@BeanParam OrderSummary orderSummary){
		return postDataMediator.placeOrders(orderSummary.getOrderSummary());
	}
	@Path("/glogin")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response gLoginService(@FormParam("tokenId") String tokenId){
		LoggerObject.infoLog(tokenId);
		Response response=loginDelegate.gValidate(tokenId);
		if(response.getStatus()==Response.Status.OK.getStatusCode()){
			HttpSession session=httpServletRequest.getSession(true);
			UserVo user=(UserVo)session.getAttribute("userSession");
			if(user==null){
				user=response.readEntity(UserVo.class);
				session.setAttribute("userSession", user);
			}
			try {
				return Response.seeOther(new URI("http://localhost:8080/WarehouseServices/manager/dashboard")).build();
			} catch (URISyntaxException e) {
				LoggerObject.errorLog(e);
			}
		}else{
			LoggerObject.infoLog("Enter UnAuthorised");
			return null;
		}
		return null;
	}
	@Path("/login")
	@POST
	public Response loginService(@FormParam("loginId") String loginId,@FormParam("loginKey") String loginKey){
		Response response=loginDelegate.validate(loginId,loginKey);
		if(response.getStatus()==Response.Status.OK.getStatusCode()){
			HttpSession session=httpServletRequest.getSession(true);
			UserVo user=(UserVo)session.getAttribute("userSession");
			if(user==null){
				user=response.readEntity(UserVo.class);
				session.setAttribute("userSession", user);
			}
			try {
				return Response.seeOther(new URI("http://localhost:8080/WarehouseServices/manager/dashboard")).build();
			} catch (URISyntaxException e) {
				LoggerObject.errorLog(e);
			}
		}else{
			LoggerObject.infoLog("Enter UnAuthorised");
			return null;
		}
		return null;
	}
	@Path("/register")
	@POST
	public Response registerService(@FormParam("userName") String userName,
			@FormParam("userEmail") String userEmail,
			@FormParam("userContact") long userContact,
			@FormParam("userPassword") String userPassword,
			@FormParam("userConfirmPassword") String userConfirmPassword){
		LoggerObject.infoLog("Entered Register");
		boolean passwordValidity=loginDelegate.checkPasswordIntegrity(userConfirmPassword,userPassword);
		LoggerObject.infoLog(passwordValidity);
		if(passwordValidity){
			LoggerObject.infoLog("Password Is Valid");
			UserVo user=new UserVo();
			user.setContact(userContact);
			user.setUserName(userName);
			user.setEmail(userEmail);
			user.setPassword(userPassword);
			LoggerObject.infoLog(user);
			Response response=loginDelegate.register(user);
			if(response.getStatus()==Response.Status.OK.getStatusCode()){
				LoggerObject.infoLog("Registered");
				response=loginDelegate.validate(userEmail, userPassword);
				if(response.getStatus()==Response.Status.OK.getStatusCode()){
					LoggerObject.infoLog("LoginValidated");
					HttpSession session=httpServletRequest.getSession(true);
					UserVo sessionUser=(UserVo)session.getAttribute("userSession");
					if(sessionUser==null){
						LoggerObject.infoLog(sessionUser);
						sessionUser=response.readEntity(UserVo.class);
						session.setAttribute("userSession", sessionUser);
					}
					try {
						return Response.seeOther(new URI("http://localhost:8080/WarehouseServices/manager/dashboard")).build();
					} catch (URISyntaxException e) {
						LoggerObject.errorLog(e);
					}
				}
			}
		}
		return null;
	}
}
