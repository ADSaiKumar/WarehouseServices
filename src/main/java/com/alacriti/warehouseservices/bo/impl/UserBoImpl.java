package com.alacriti.warehouseservices.bo.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.sql.Connection;

import com.alacriti.warehouseservices.bo.UserBo;
import com.alacriti.warehouseservices.dao.UserDao;
import com.alacriti.warehouseservices.dao.impl.DataBaseAgent;
import com.alacriti.warehouseservices.dao.impl.UserDaoImpl;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.UserVo;

public class UserBoImpl implements UserBo {
	private Connection connection;
	private UserDao userDao;
	
	public UserBoImpl() {
		connection=DataBaseAgent.getConnection();
		userDao=new UserDaoImpl();
	}

	public UserVo getUserDetails(String userEmail) {
		return userDao.getUserDetail(connection,userEmail);
	}

	public String getToken(UserVo userVo) {
		Key key = MacProvider.generateKey();
		try
		{
			ObjectInputStream object=new ObjectInputStream(new FileInputStream("key.bin"));
			key=(Key)object.readObject();
			object.close();
		}
		catch(FileNotFoundException e){
			LoggerObject.errorLog(e);
			try
			{
				ObjectOutputStream object=new ObjectOutputStream(new FileOutputStream("key.bin"));
				object.writeObject(key);
				object.close();
			}
			catch(IOException ie)
			{
				LoggerObject.errorLog(ie);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String compactJws = Jwts.builder()
		  .setSubject(userVo.getUserName())
		  .signWith(SignatureAlgorithm.HS256, key)
		  .compact();
		return compactJws;
	}

	public boolean validate(String userName, String password) {
		boolean validity=userDao.validate(connection,userName,password);
		return validity;
	}

	public boolean checkIfExists(Connection connection, String email) {
		boolean exists=userDao.checkAvailability(connection,email);
		return exists;
	}

	public UserVo registerUser(Connection connection, UserVo user) {
		int result=userDao.registerUser(connection,user);
		if(result>0){
			return userDao.getUserDetail(connection,user.getEmail());
		}
		return null;
	}

}
