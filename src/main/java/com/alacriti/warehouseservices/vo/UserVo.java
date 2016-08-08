package com.alacriti.warehouseservices.vo;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserVo {
	private int userId;
	private String userName;
	private String token;
	private String email;
	private String password;
	private long contact;
	
	public UserVo(){
		
	}

	

	public UserVo(int userId, String userName, String email, long contact) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.contact = contact;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", userName=" + userName
				+ ", token=" + token + ", email=" + email + ", contact="
				+ contact + "]";
	}
		
}
