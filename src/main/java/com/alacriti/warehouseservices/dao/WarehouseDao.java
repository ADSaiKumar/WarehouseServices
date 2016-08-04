package com.alacriti.warehouseservices.dao;

import java.sql.Connection;
import java.util.List;

import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public interface WarehouseDao {
	public List<FloorVo> getDetails(Connection connection);

	public List<PlaceholderVo> getDetails(Connection connection, int floorId);

	public PlaceholderVo getAvailability(Connection connection);
}