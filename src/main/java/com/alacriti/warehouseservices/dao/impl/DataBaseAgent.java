package com.alacriti.warehouseservices.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.alacriti.warehouseservices.vo.LoggerObject;

public class DataBaseAgent {
	private static final String URL="jdbc:mysql://192.168.35.70:3306/warehousemng_dev";
	private static final String USERNAME="warehousemng_dev";
	private static final String PASSWORD="warehousemng_dev";
	public static Connection connection; 
	public static Connection getConnection(){
		if(connection==null){
			try {
				DataSource datasource;
				InitialContext context= new InitialContext();
				datasource = (DataSource) context.lookup("java:/MySqlDatabase");
				connection=datasource.getConnection();
			} catch (SQLException e) {
				LoggerObject.errorLog(e);
			} catch (NamingException e) {
				LoggerObject.errorLog(e);
			}
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