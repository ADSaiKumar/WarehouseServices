package com.alacriti.warehouseservices.delegates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.codec.binary.Base64;

import com.alacriti.warehouseservices.bo.impl.UserBoImpl;
import com.alacriti.warehouseservices.vo.LoggerObject;

@Provider
public class ServiceSecurityFilter implements ContainerRequestFilter{
	private static final String LOGIN_PATH="/users";
	private static final String AUTHORIZATION_HEADER="Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX="Basic ";
	private UserBoImpl userBo=new UserBoImpl();

	public void filter(ContainerRequestContext context) throws IOException {
		if(context.getUriInfo().getPath().contains(LOGIN_PATH)){
			if(context.getMethod().equals("POST")){
				return ;
			}
			List<String> authTokens=context.getHeaders().get(AUTHORIZATION_HEADER);
			if(authTokens!=null){
				String token=authTokens.get(0);
				token=token.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				byte[] decodedBytes=Base64.decodeBase64(token.getBytes());
				String decodedString=new String(decodedBytes);
				try{
					StringTokenizer stringTokenizer=new StringTokenizer(decodedString,":");
					String userName=stringTokenizer.nextToken();
					List<String> email=new ArrayList<String>();
					email.add(userName);
					String password=stringTokenizer.nextToken();
					boolean validity=userBo.validate(userName,password);
					if(validity){
						context.getHeaders().put("UserEmail",email);
						return ;
					}
				}catch(NoSuchElementException nse){
					LoggerObject.errorLog(nse);
				}
			}
			Response unAuthourized=Response.status(Response.Status.UNAUTHORIZED)
					.entity("Authourization Failed")
					.build();
			context.abortWith(unAuthourized);
		}
		
	}

}
