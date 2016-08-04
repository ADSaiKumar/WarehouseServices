package com.alacriti.warehouseservices.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.warehouseservices.dao.OrderDao;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.OrderVo;
import com.alacriti.warehouseservices.vo.PercentageVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class OrderDaoImpl implements OrderDao {

	public List<PlaceholderVo> getItemsSummary(Connection connection) {
		List<PlaceholderVo> placeholders=new ArrayList<PlaceholderVo>();
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("select a.ph_id,c.item_id,item_name,stock,capacity,storage from ")
				.append("warehouse_stock_tbl a join ")			
				.append("warehouse_placeholder_tbl b on a.ph_id=b.ph_id join ")
				.append("warehouse_item_tbl c on c.item_id=a.item_id");
		ResultSet resultSet=DataBaseAgent.getData(connection, queryBuilder.toString().trim());
		try {
			while(resultSet.next()){
				ItemVo item=new ItemVo(resultSet.getInt("item_id"),resultSet.getString("item_name"),(resultSet.getInt("stock")+resultSet.getInt("storage")));
				PlaceholderVo placeholder=new PlaceholderVo(item,resultSet.getString("ph_id"),resultSet.getInt("stock"),resultSet.getInt("capacity"));
				placeholders.add(placeholder);
			}
		
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return placeholders;
	}

	public List<PercentageVo> loadPercentages(Connection connection) {
		String query="select minimum,maximum,quantity from warehouse_percent_tbl";
		ResultSet resultSet=DataBaseAgent.getData(connection, query);
		List<PercentageVo> percentages=new ArrayList<PercentageVo>();
		try {
			while(resultSet.next()){
				percentages.add(new PercentageVo(resultSet.getInt("minimum"),resultSet.getInt("maximum"),resultSet.getInt("quantity")));
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return percentages;
	}

	public List<OrderVo> setOrder(Connection connection,
			List<OrderVo> orderSummary) {
		List<OrderVo> orders;
		String query="insert into warehouse_order_tbl(order_date,order_item_id,order_quantity) values(now(),?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			for(OrderVo order:orderSummary){
				preparedStatement.setInt(1, order.getItem().getItemId());
				preparedStatement.setInt(2, order.getOrderAmount());
				preparedStatement.execute();
			}
			Calendar calender=Calendar.getInstance();
			StringBuilder dateBuilder=new StringBuilder();
			dateBuilder.append(calender.get(Calendar.YEAR))
				.append("-")
				.append(calender.get(Calendar.MONTH)+1)
				.append("-")
				.append(calender.get(Calendar.DAY_OF_MONTH));
			String date=dateBuilder.toString();
			orders=getOrders(connection, date);
			return orders;
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return null;
	}
	public List<OrderVo> getOrders(Connection connection,String date){
		List<OrderVo> orders=new ArrayList<OrderVo>();
		StringBuilder queryBuilder=new StringBuilder();
		queryBuilder.append("select order_id,order_date,order_item_id,order_quantity,item_name from warehouse_order_tbl ")
				.append("join warehouse_item_tbl on item_id=order_item_id where date(order_date)='")
				.append(date)
				.append("'");
		LoggerObject.infoLog(queryBuilder.toString());
		ResultSet resultSet=DataBaseAgent.getData(connection, queryBuilder.toString());
		try {
			while(resultSet.next()){
				ItemVo item=new ItemVo(resultSet.getInt("order_item_id"),resultSet.getString("item_name"),resultSet.getInt("order_quantity"));
				OrderVo order=new OrderVo(resultSet.getDate("order_date"),resultSet.getInt("order_id"),item,item.getQuantity());
				orders.add(order);
			}
		} catch (SQLException e) {
			LoggerObject.errorLog(e);
		}
		return orders;
	}
}
