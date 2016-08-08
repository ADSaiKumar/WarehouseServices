package com.alacriti.warehouseservices.bo.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.alacriti.warehouseservices.bo.ItemBo;
import com.alacriti.warehouseservices.dao.ItemDao;
import com.alacriti.warehouseservices.dao.impl.DataBaseAgent;
import com.alacriti.warehouseservices.dao.impl.ItemDaoImpl;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class ItemBoImpl implements ItemBo {
	private Connection connection;
	private ItemDao itemDao;
	public ItemBoImpl(){
		connection=DataBaseAgent.getConnection();
		itemDao=new ItemDaoImpl();
	}
	
	public PlaceholderVo getItem(int itemId) {
		PlaceholderVo placeholder= itemDao.getItem(connection,itemId);
		return  placeholder;
	}

	public int insertStock(PlaceholderVo placeholder) {
		int result= itemDao.insertStock(connection,placeholder);
		return result;
	}

	public int updateStock(PlaceholderVo placeholder) {
		int result=itemDao.updateStock(connection,placeholder);
		return result;
	}

	public List<ItemVo> enlistItem() {
		List<ItemVo> items=itemDao.enlisItem(connection);
		return items;
	}

	public List<ItemVo> addItem(ItemVo item) {
		itemDao.addItem(connection,item);
		List<ItemVo> items=itemDao.enlisItem(connection);
		return items;
	}

	public int addToStock(ItemVo item) {
		int result=itemDao.addToStock(connection,item);
		List<ItemVo> items=itemDao.enlisItem(connection);
		return result;
	}

	public ItemVo removeStock(ItemVo item) {
		itemDao.deleteStock(connection,item);
		ItemVo storageItem=checkStorageOnly();
		List<ItemVo> items=itemDao.enlisItem(connection);
		return storageItem;
	}

	private ItemVo checkStorageOnly() {
		return itemDao.checkStorageOnly(connection);
	}

}