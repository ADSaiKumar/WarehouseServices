package com.alacriti.warehouseservices.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alacriti.warehouseservices.dao.UserDao;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.UserVo;

public class UserDaoImpl implements UserDao {

	public UserVo getUserDetail(Connection connection, String userEmail) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("select user_mail,user_name,user_id,user_contact from warehouse_user_tbl where user_mail='")
					.append(userEmail)
					.append("'");
		UserVo userVo = null;
		ResultSet resultSet=DataBaseAgent.getData(connection, queryBuilder.toString());
		try {
			while(resultSet.next()){
				long contact=resultSet.getLong("user_contact");
				int id=resultSet.getInt("user_id");
				String email=resultSet.getString("user_mail");
				String name=resultSet.getString("user_name");
				userVo=new UserVo(id,name,email,contact);
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return userVo;
	}

	public boolean validate(Connection connection, String userName,
			String password) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("select user_id from warehouse_user_tbl where user_mail='")
				.append(userName)
				.append("' and user_pwd='")
				.append(password)
				.append("'");
		ResultSet resultSet=DataBaseAgent.getData(connection, queryBuilder.toString().trim());
		try {
			resultSet.beforeFirst();
			if (resultSet.next()) {    
			   return true; 
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return false;
	}

	public boolean checkAvailability(Connection connection, String email) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("select count(user_id) from warehouse_user_tbl where user_mail='")
				.append(email)
				.append("'");
		ResultSet resultSet=DataBaseAgent.getData(connection, queryBuilder.toString().trim());
		try {
			while(resultSet.next()) {
				if(resultSet.getInt("count(user_id)")>0){
					return true;
				}
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return false;
	}

	public int registerUser(Connection connection, UserVo user) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("insert into warehouse_user_tbl(user_mail,user_name,user_contact,user_pwd) values('")
				.append(user.getEmail())
				.append("','")
				.append(user.getUserName())
				.append("',")
				.append(user.getContact())
				.append(",'")
				.append(user.getPassword())
				.append("')");
		LoggerObject.infoLog(queryBuilder.toString());
		int result=DataBaseAgent.updateData(connection, queryBuilder.toString().trim());
		return result;
	}
}
