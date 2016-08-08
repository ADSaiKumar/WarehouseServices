package com.alacriti.warehouseservices.bo;

import java.sql.Connection;

import com.alacriti.warehouseservices.vo.UserVo;

public interface UserBo {

	public UserVo getUserDetails(String userEmail);

	public String getToken(UserVo userVo);

	public boolean checkIfExists(Connection connection, String email);

	public UserVo registerUser(Connection connection, UserVo user);

}
