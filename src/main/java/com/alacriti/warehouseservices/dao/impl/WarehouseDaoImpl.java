package com.alacriti.warehouseservices.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alacriti.warehouseservices.dao.WarehouseDao;
import com.alacriti.warehouseservices.vo.CheckOutVo;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.PageVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class WarehouseDaoImpl implements WarehouseDao{
	//private Logger logger=Logger.getLogger(WarehouseDaoImpl.class);
	private Statement statement;
	public List<FloorVo> getDetails(Connection connection) {
		Map<Integer,FloorVo> warehouse=new HashMap<Integer,FloorVo>();
		StringBuilder detailsQuery=new StringBuilder();
		detailsQuery.append("select floor_id,floor_name,a.ph_id,d.item_id,item_name,stock,capacity,storage from ")
			.append("warehouse_placeholder_tbl a left outer join ")
			.append("warehouse_stock_tbl b on b.ph_id=a.ph_id left outer join ")
			.append("warehouse_floor_tbl c on c.floor_id=a.floor_no left outer join ")
			.append("warehouse_item_tbl d on d.item_id=b.item_id");
		ResultSet resultSet=DataBaseAgent.getData(connection,detailsQuery.toString().trim());
			int floorId;
			try {
				while(resultSet.next()){
					floorId=resultSet.getInt("floor_id");
					ItemVo item=new ItemVo(resultSet.getInt("item_id"),resultSet.getString("item_name"),(resultSet.getInt("stock")+resultSet.getInt("storage")));
					PlaceholderVo placeholder=new PlaceholderVo(item,resultSet.getString("ph_id"),resultSet.getInt("stock"),resultSet.getInt("capacity"));
					if(warehouse.containsKey(floorId)){
						warehouse.get(floorId).addPlaceholder(placeholder);
					}
					else{
						warehouse.put(floorId, new FloorVo(floorId,resultSet.getString("floor_name"),new ArrayList<PlaceholderVo>()));
						warehouse.get(floorId).addPlaceholder(placeholder);
					}
				}
			} catch (SQLException e) {
				LoggerObject.errorLog(e.getStackTrace());
			}
			LoggerObject.infoLog(warehouse.values());
			return new ArrayList<FloorVo>(warehouse.values());
	}
	public List<PlaceholderVo> getDetails(Connection connection, int floorId) {
		List<PlaceholderVo> placehlders=new ArrayList<PlaceholderVo>();
		StringBuilder query=new StringBuilder();
		query.append("select floor_no,a.item_id,item_name,a.ph_id,stock,storage,capacity from ")
		.append("warehouse_stock_tbl a left outer join ")
		.append("warehouse_placeholder_tbl b on b.ph_id=a.ph_id left outer join ")
		.append("warehouse_item_tbl c on c.item_id=a.item_id ")
		.append("where floor_no=")
		.append(floorId);
		try {
			ResultSet resultSet=DataBaseAgent.getData(connection, query.toString().trim());
			while(resultSet.next()){
				ItemVo item=new ItemVo(resultSet.getInt("item_id"),resultSet.getString("item_name"),(resultSet.getInt("stock")+resultSet.getInt("storage")));
				PlaceholderVo placeholder=new PlaceholderVo(item,resultSet.getString("ph_id"),resultSet.getInt("stock"),resultSet.getInt("capacity"));
				placehlders.add(placeholder);
			
		}
		return placehlders;
		}catch (SQLException e) {
		}
	return null;
	}
	public PlaceholderVo getAvailability(Connection connection) {
		String query="select ph_id,capacity from warehouse_placeholder_tbl where ph_id not in (select ph_id from warehouse_stock_tbl)";
		ResultSet resultSet=DataBaseAgent.getData(connection, query);
		try {
			if(resultSet.next()){
			PlaceholderVo placeholderVo=new PlaceholderVo(null,resultSet.getString("ph_id"), 0,resultSet.getInt("capacity"));
			return placeholderVo;
			}else{
				return null;
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}catch(NullPointerException e){
			LoggerObject.errorLog(e.getStackTrace());
		}		
		return null;
	}
	public List<ItemVo> getStorageDetails(Connection connection) {
		String query="select a.item_id,storage,item_name from warehouse_stock_tbl a join warehouse_item_tbl b on a.item_id=b.item_id where storage>0";
		ResultSet resultSet=DataBaseAgent.getData(connection, query);
		try {
			List<ItemVo> items=new ArrayList<ItemVo>();
			while(resultSet.next()){
			items.add(new ItemVo(resultSet.getInt("item_id"),resultSet.getString("item_name"),resultSet.getInt("storage")));
			}
			return items;
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}catch(NullPointerException e){
			LoggerObject.errorLog(e.getStackTrace());
		}		
		return null;
	}
	public int getUniquePageId(Connection connection) {
		String query="select max(unique_id) as id from warehouse_pagination_tbl";
		ResultSet resultSet=DataBaseAgent.getData(connection, query);
		try {
			resultSet.next();
			if(resultSet.getInt("id")!=0){
				return resultSet.getInt("id");
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}catch(NullPointerException e){
			LoggerObject.errorLog(e);
		}
		return 1;
	}
	public int loadPaginationTable(Connection connection, PageVo page,
			List<PlaceholderVo> placeholders) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("insert into warehouse_pagination_tbl(unique_id,record_id,item_name,placeholder_id,stock,storage) ")
					.append("values(")
					.append(page.getUniqueId())
					.append(",?,?,?,?,?)");
		int recordId=0;
		try {
			PreparedStatement statement=connection.prepareStatement(queryBuilder.toString().trim());
			for(PlaceholderVo placeholder:placeholders){
				int storage=placeholder.getItem().getQuantity()-placeholder.getStock();
				statement.setInt(1,recordId);
				statement.setString(2,placeholder.getItem().getItemName());
				statement.setString(3,placeholder.getPlaceholderId());
				statement.setInt(4,placeholder.getStock());
				statement.setInt(5,storage);
				statement.execute();
				recordId++;
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return recordId;
	}
	public PageVo loadPage(Connection connection, PageVo page) {
		List<PlaceholderVo> placeholders=new ArrayList<PlaceholderVo>();
		int uniqueId=page.getUniqueId();
		int offset=page.getOffset();
		int limit=page.getLimit();
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("select item_name,placeholder_id,stock,storage from warehouse_pagination_tbl where unique_id=")
					.append(uniqueId)
					.append(" and record_id>=")
					.append(offset)
					.append(" order by record_id limit ")
					.append(limit);
		String query=queryBuilder.toString().trim();
		ResultSet resultSet=DataBaseAgent.getData(connection, query);
		try {
			while(resultSet.next()){
				PlaceholderVo placeholder=new PlaceholderVo();
				placeholder.setPlaceholderId(resultSet.getString("placeholder_id"));
				placeholder.setStock(resultSet.getInt("stock"));
				ItemVo item=new ItemVo();
				item.setItemName(resultSet.getString("item_name"));
				item.setQuantity(resultSet.getInt("stock")+resultSet.getInt("storage"));
				placeholder.setItem(item);
				placeholders.add(placeholder);
			}
			resultSet.close();
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		page.setPlaceholders(placeholders);
		String sql="select count(unique_id) as count from warehouse_pagination_tbl where unique_id="+page.getUniqueId()+";";
		resultSet=DataBaseAgent.getData(connection, sql);
		try {
			resultSet.next();
			int records=resultSet.getInt("count");
			LoggerObject.infoLog(records);
			int totalpages=0;
			if(records==0){
				totalpages=0;
			}else if(records/limit == 0){
				totalpages=1;
			}else{
				if(records%limit==0){
					totalpages=records/limit;
					LoggerObject.infoLog(totalpages);
				}else{
					totalpages=(records/limit)+1;
					LoggerObject.infoLog(totalpages);
				}
			}
			page.setTotalPages(totalpages);
			page.setPageNo(page.getOffset()/page.getLimit()+1);
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return page;
	}
	public List<CheckOutVo> getCheckOutDetails(Connection connection) {
		String sql="select check_id,checkout_date,item_name,quantity from warehouse_checkout_tbl a join warehouse_item_tbl b on a.item_id=b.item_id";
		List<CheckOutVo> list=new ArrayList<CheckOutVo>();
		ResultSet resultSet=DataBaseAgent.getData(connection, sql);
		try {
			while(resultSet.next()){
				CheckOutVo checkOut=new CheckOutVo();
				checkOut.setCheckOutId(resultSet.getInt("check_id"));
				checkOut.setCheckOutDate(resultSet.getDate("checkout_date"));
				checkOut.setItem(new ItemVo(0,resultSet.getString("item_name"),resultSet.getInt("quantity")));
				list.add(checkOut);
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return list;
	}
	public int getTmpUniquePageId(Connection connection) {
		String query="select max(unique_id) as id from warehouse_tmp_checkout_tbl";
		ResultSet resultSet=DataBaseAgent.getData(connection, query);
		try {
			resultSet.next();
			if(resultSet.getInt("id")!=0){
				return resultSet.getInt("id");
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}catch(NullPointerException e){
			LoggerObject.errorLog(e);
		}
		return 1;
	}
	public int loadTmpCheckOuts(Connection connection, PageVo page,
			List<CheckOutVo> checkouts) {
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("insert into warehouse_pagination_tbl(unique_id,record_id,check_id,checkout_date,item_name,quantity) ")
					.append("values(")
					.append(page.getUniqueId())
					.append(",?,?,?,?,?)");
		int recordId=0;
		try {
			PreparedStatement statement=connection.prepareStatement(queryBuilder.toString().trim());
			for(CheckOutVo checkOut:checkouts){
				statement.setInt(1,recordId);
				statement.setInt(2,checkOut.getCheckOutId());
				statement.setDate(3,checkOut.getCheckOutDate());
				statement.setString(4,checkOut.getItem().getItemName());
				statement.setInt(5,checkOut.getItem().getQuantity());
				statement.execute();
				recordId++;
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return recordId;
	}
	public PageVo loadPageCheckOuts(Connection connection, PageVo page) {
		return null;
	}

}