package com.alacriti.warehouseservices.dao;

import java.sql.Connection;

import com.alacriti.warehouseservices.vo.UserVo;

public interface UserDao {

	public UserVo getUserDetail(Connection connection, String userEmail);

	public boolean validate(Connection connection, String userName,
			String password);
	public boolean checkAvailability(Connection connection, String email);

	public int registerUser(Connection connection, UserVo user);

}
