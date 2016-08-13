package com.alacriti.warehousemanagment.delegates;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

import com.alacriti.warehouseservices.vo.UserVo;

public class LoginDelegate {
	
	
	public Response validate(String loginId, String loginKey) {
		String authouraizatioString=loginId+":"+loginKey;
		byte[] encodedBytes = Base64.encodeBase64(authouraizatioString.getBytes());
		String encodedString=new String(encodedBytes);
		Client client=ClientBuilder.newClient();
		client.register(new ServiceRequestFilter());
		Response response=client.target("http://localhost:8080/WarehouseServices/services/users")
				.request()
				.header("Authorization", "Basic "+encodedString)
				.get();
		return response;
	}

	public Response gValidate(String tokenId) {
		Client client=ClientBuilder.newClient();
		client.register(new ServiceRequestFilter());
		String data="tokenId="+tokenId;
		Response response=client.target("http://localhost:8080/WarehouseServices/services/users/google")
				.request()
				.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));
		return response;
	}

	public boolean checkPasswordIntegrity(String userConfirmPassword,
			String userPassword) {
		if(userPassword.equals(userConfirmPassword)){
			return true;
		}else if(userPassword!=null || userPassword.equals("")){
			return false;
		}
		return false;
	}

	public Response register(UserVo user) {
		Client client=ClientBuilder.newClient();
		client.register(new ServiceRequestFilter());
		Response response=client.target("http://localhost:8080/WarehouseServices/services/users")
				.request()
				.post(Entity.xml(user));
		return response;
	}
	
}
