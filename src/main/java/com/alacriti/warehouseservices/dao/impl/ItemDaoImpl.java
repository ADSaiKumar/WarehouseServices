package com.alacriti.warehouseservices.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.warehouseservices.dao.ItemDao;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class ItemDaoImpl implements ItemDao {

	public PlaceholderVo getItem(Connection connection, int itemId) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("select a.item_id,item_name,stock,a.ph_id,capacity,storage from ")
			.append("warehouse_stock_tbl a left outer join ")
			.append("warehouse_item_tbl b on a.item_id=b.item_id left outer join ")
			.append("warehouse_placeholder_tbl c on c.ph_id=a.ph_id ")
			.append("where a.item_id=")
			.append(itemId);
		ResultSet resultSet=DataBaseAgent.getData(connection, queryBuilder.toString().trim());
		try {
			resultSet.first();
			PlaceholderVo placeholderVo=new PlaceholderVo();
			placeholderVo.setItem(new ItemVo(resultSet.getInt("item_id"),resultSet.getString("item_name"),(resultSet.getInt("stock")+resultSet.getInt("storage"))));
			placeholderVo.setStock(resultSet.getInt("stock"));
			placeholderVo.setPlaceholderId(resultSet.getString("ph_id"));
			placeholderVo.setCapacity(resultSet.getInt("capacity"));
			return placeholderVo;
		} catch (SQLException e) {
			LoggerObject.errorLog(e.getStackTrace());
		}
		return null;
	}

	public int insertStock(Connection connection, PlaceholderVo placeholder) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("insert into warehouse_stock_tbl(item_id,ph_id,stock,storage) ")
			.append("values(")
			.append(placeholder.getItem().getItemId())
			.append(",'")
			.append(placeholder.getPlaceholderId())
			.append("',")
			.append(placeholder.getStock())
			.append(",")
			.append(placeholder.getItem().getQuantity()-placeholder.getStock())
			.append(")");
		LoggerObject.infoLog(queryBuilder.toString());
		int i=DataBaseAgent.updateData(connection, queryBuilder.toString().trim());
		return i;
	}

	public int updateStock(Connection connection, PlaceholderVo placeholder) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("update warehouse_stock_tbl ")
			.append("set stock=")
			.append(placeholder.getStock())
			.append(" , storage=")
			.append(placeholder.getItem().getQuantity()-placeholder.getStock())
			.append(" where item_id=")
			.append(placeholder.getItem().getItemId());
		LoggerObject.infoLog(queryBuilder.toString());
		int i=DataBaseAgent.updateData(connection, queryBuilder.toString().trim());
		return i;
	}

	public List<ItemVo> enlisItem(Connection connection) {
		String query="select a.item_id,item_name,(stock+storage) as quantity from warehouse_item_tbl a left outer join warehouse_stock_tbl b on a.item_id=b.item_id";
		ResultSet resultSet=DataBaseAgent.getData(connection, query);
		List<ItemVo> listItems=new ArrayList<ItemVo>();
		try {
			while(resultSet.next()){
				listItems.add(new ItemVo(resultSet.getInt("item_id"),resultSet.getString("item_name"),resultSet.getInt("quantity")));
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(this.getClass());
			LoggerObject.errorLog(e);
		}
		return listItems;
	}

	public void addItem(Connection connection, ItemVo item) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("insert into warehouse_item_tbl(item_name) values ('")
			.append(item.getItemName())
			.append("')");
		LoggerObject.infoLog(queryBuilder.toString());
		int i=DataBaseAgent.updateData(connection, queryBuilder.toString().trim());
	}

	public int addToStock(Connection connection, ItemVo item) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("insert into warehouse_stock_tbl(item_id,storage) values (")
			.append(item.getItemId())
			.append(",")
			.append(item.getQuantity())
			.append(")");
		LoggerObject.infoLog(queryBuilder.toString());
		int i=DataBaseAgent.updateData(connection, queryBuilder.toString().trim());
		return i;
	}

	public void deleteStock(Connection connection, ItemVo item) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("delete from warehouse_stock_tbl ")
			.append("where item_id=")
			.append(item.getItemId());
		LoggerObject.infoLog(queryBuilder.toString());
		int i=DataBaseAgent.updateData(connection, queryBuilder.toString().trim());
	}

	public ItemVo checkStorageOnly(Connection connection) {
		String query="select item_id,storage from warehouse_stock_tbl where ph_id is null";
		ResultSet resultSet=DataBaseAgent.getData(connection, query);
		try {
			if(resultSet.next()){
				return new ItemVo(resultSet.getInt("item_id"),"",resultSet.getInt("storage"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void checkStorageOnly(Connection connection, ItemVo item) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("insert into warehouse_checkout_tbl(checkout_date,item_id,quantity) values(now(),")
			.append(item.getItemId())
			.append(" , ")
			.append(item.getQuantity())
			.append(")");
		LoggerObject.infoLog(queryBuilder.toString());
		int i=DataBaseAgent.updateData(connection, queryBuilder.toString().trim());
	}
}