package com.alacriti.warehouseservices.dao;

import java.sql.Connection;
import java.util.List;

import com.alacriti.warehouseservices.vo.OrderVo;
import com.alacriti.warehouseservices.vo.PercentageVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public interface OrderDao {

	public List<PlaceholderVo> getItemsSummary(Connection connection);

	public List<PercentageVo> loadPercentages(Connection connection);

	public List<OrderVo> setOrder(Connection connection,
			List<OrderVo> orderSummary);

}
