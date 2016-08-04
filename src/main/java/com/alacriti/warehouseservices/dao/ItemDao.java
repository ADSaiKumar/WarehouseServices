package com.alacriti.warehouseservices.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public interface ItemDao {

	public PlaceholderVo getItem(Connection connectionVo, int itemId);

	public int insertStock(Connection connection, PlaceholderVo placeholder);

	public int updateStock(Connection connection, PlaceholderVo placeholder);

	public List<ItemVo> enlisItem(Connection connection);

	public void addItem(Connection connection, ItemVo item);

	public int addToStock(Connection connection,ItemVo item);

	public void deleteStock(Connection connection, ItemVo item);

	public ItemVo checkStorageOnly(Connection connection);

}