package com.alacriti.warehouseservices.delegates;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import com.alacriti.warehouseservices.bo.UserBo;
import com.alacriti.warehouseservices.bo.impl.UserBoImpl;
import com.alacriti.warehouseservices.dao.impl.DataBaseAgent;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.UserVo;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class UserDelegate {
	private Connection connection;
	private UserBo userBo;
	private static final String CLIENT_ID = "908359182448-573caargeg5u67la3b8f4aokbt5ubk3a.apps.googleusercontent.com";
	private static final String APPLICATION_NAME = "Google+ Java Token Verification";
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
	public UserDelegate(){
		connection=DataBaseAgent.getConnection();
		userBo=new UserBoImpl();
	}
	public UserVo registerUser(UserVo user) {
		boolean ifExists=userBo.checkIfExists(connection,user.getEmail());
		UserVo newUser=null;
		if(!ifExists){
			newUser=userBo.registerUser(connection,user);
		}
		return newUser;
	}
	public Response gValidate(String tokenId) {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT,JSON_FACTORY)
	    .setAudience(Arrays.asList(CLIENT_ID))
	   .setIssuer("accounts.google.com")
	    .build();
		GoogleIdToken idToken;
		try {
			idToken = verifier.verify(tokenId);
			if (idToken != null) {
				  Payload payload = idToken.getPayload();
				  String userId = payload.getSubject();
				  String email = payload.getEmail();
				  String name = (String) payload.get("name");
				  UserVo user=new UserVo();
				  user.setEmail(email);
				  user.setUserName(name);
				  registerUser(user);
				  UserVo newUser=userBo.getUserDetails(user.getEmail());
				  String token=userBo.getToken(newUser);
				  newUser.setToken(token);
				  Response response=Response.status(Response.Status.OK)
						  					.entity(newUser)
						  					.build();
				  LoggerObject.infoLog(newUser);
				  return response;
				} else {
				  LoggerObject.infoLog("Invalid ID token.");
				}
		} catch (GeneralSecurityException e) {
			LoggerObject.errorLog(e);
		} catch (IOException e) {
			LoggerObject.errorLog(e);
		}
		return null;
	}
}
