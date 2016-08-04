package com.alacriti.warehouseservices.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.warehouseservices.bo.OrderBo;
import com.alacriti.warehouseservices.dao.OrderDao;
import com.alacriti.warehouseservices.dao.impl.DataBaseAgent;
import com.alacriti.warehouseservices.dao.impl.OrderDaoImpl;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.OrderVo;
import com.alacriti.warehouseservices.vo.PercentageVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class OrderBoImpl implements OrderBo {
	private Connection connection;
	private OrderDao orderDao;
	private List<PercentageVo> percentages;
	public OrderBoImpl(){
		connection=DataBaseAgent.getConnection();
		orderDao=new OrderDaoImpl();
	}
	public List<OrderVo> checkOrder() {
		List<PlaceholderVo> stockList=orderDao.getItemsSummary(connection);
		List<OrderVo> orders=new ArrayList<OrderVo>();
		percentages=orderDao.loadPercentages(connection);
		for(PlaceholderVo placeholder:stockList){
			double stockPercent=((double)placeholder.getStock()/(double)placeholder.getCapacity())*100;
			LoggerObject.infoLog(stockPercent);
			for(PercentageVo percentage:percentages){
				if(percentage.checkRange(stockPercent)){
					orders.add(new OrderVo(null,0,placeholder.getItem(),percentage.getQuantity()));
				}
			}
		}
		return orders;
	}
	public List<OrderVo> setOrder(List<OrderVo> orderSummary) {
		List<OrderVo> orders;
		if(orderSummary.size()<=5){
			orders=orderDao.setOrder(connection,orderSummary);
			return orders;
		}
		else{
			//Too many orders error
		}
		return null;
	}

}
