package com.alacriti.warehouseservices.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alacriti.warehouseservices.vo.LoggerObject;

public class DataBaseAgent {
	private static final String URL="jdbc:mysql://192.168.35.70:3306/warehousemng_dev";
	private static final String USERNAME="warehousemng_dev";
	private static final String PASSWORD="warehousemng_dev";
	//public static Connection connection; 
	public static Connection getConnection(){
		Connection connection=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
				return connection;
			} catch (SQLException e) {
				LoggerObject.errorLog(e);
			} catch (ClassNotFoundException e) {
				LoggerObject.errorLog(e);
			}
		return connection;
	}
	public static ResultSet getData(Connection connection,String query){
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}catch(NullPointerException e){
			LoggerObject.errorLog(e.getStackTrace());
		}
		return null;
	}
	public static int updateData(Connection connection,String query){
		try {
			Statement statement=connection.createStatement();
			int success=statement.executeUpdate(query);
			return success;
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}catch(NullPointerException e){
			LoggerObject.errorLog(e.getStackTrace());
		}
		return -1;
	}
	
}