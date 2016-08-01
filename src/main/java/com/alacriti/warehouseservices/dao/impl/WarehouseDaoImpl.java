package com.alacriti.warehouseservices.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alacriti.warehouseservices.dao.WarehouseDao;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class WarehouseDaoImpl implements WarehouseDao{
	//private Logger logger=Logger.getLogger(WarehouseDaoImpl.class);
	private Statement statement;
	public ResultSet getDetails(Connection connection) {
		Map<Integer,FloorVo> warehouse=new HashMap<Integer,FloorVo>();
		StringBuilder detailsQuery=new StringBuilder();
		detailsQuery.append("select floor_id,floor_name,a.ph_id,d.item_id,item_name,stock,capacity,storage from ")
			.append("warehouse_placeholder_tbl a left outer join ")
			.append("warehouse_stock_tbl b on b.ph_id=a.ph_id left outer join ")
			.append("warehouse_floor_tbl c on c.floor_id=a.floor_no left outer join ")
			.append("warehouse_item_tbl d on d.item_id=b.item_id");
		return DataBaseAgent.getData(connection,detailsQuery.toString().trim());
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

}