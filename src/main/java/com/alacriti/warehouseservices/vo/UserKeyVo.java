package com.alacriti.warehouseservices.vo;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserKeyVo {
	private String userId;
	private String password;
	private Date lastActivityTime;
	
	public UserKeyVo(){
		
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLoginTime() {
		return lastActivityTime;
	}
	public void setLoginTime(Date lastActivityTime) {
		this.lastActivityTime = lastActivityTime;
	}
	
}
