package com.alacriti.warehouseservices.bo.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.alacriti.warehouseservices.bo.ItemBo;
import com.alacriti.warehouseservices.dao.ItemDao;
import com.alacriti.warehouseservices.dao.impl.DataBaseAgent;
import com.alacriti.warehouseservices.dao.impl.ItemDaoImpl;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class ItemBoImpl implements ItemBo {
	private Connection connection;
	private ItemDao itemDao;
	public ItemBoImpl(){
		connection=DataBaseAgent.getConnection();
		itemDao=new ItemDaoImpl();
	}
	
	public PlaceholderVo getItem(int itemId) {
		return itemDao.getItem(connection,itemId);
	}

	public int insertStock(PlaceholderVo placeholder) {
		return itemDao.insertStock(connection,placeholder);
		
	}

	public int updateStock(PlaceholderVo placeholder) {
		return itemDao.updateStock(connection,placeholder);
	}

	public List<ItemVo> enlistItem() {
		return itemDao.enlisItem(connection);
	}

	public List<ItemVo> addItem(ItemVo item) {
		itemDao.addItem(connection,item);
		return itemDao.enlisItem(connection);
	}

	public int addToStock(ItemVo item) {
		return itemDao.addToStock(connection,item);
	}

}