package com.alacriti.warehouseservices.dao;

import java.sql.Connection;
import java.util.List;

import com.alacriti.warehouseservices.vo.CheckOutVo;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.PageVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public interface WarehouseDao {
	public List<FloorVo> getDetails(Connection connection);

	public List<PlaceholderVo> getDetails(Connection connection, int floorId);

	public PlaceholderVo getAvailability(Connection connection);

	public List<ItemVo> getStorageDetails(Connection connection);

	public int getUniquePageId(Connection connection);

	public int loadPaginationTable(Connection connection, PageVo page,
			List<PlaceholderVo> placeholders);

	public PageVo loadPage(Connection connection, PageVo page);

	public List<CheckOutVo> getCheckOutDetails(Connection connection);

	public int getTmpUniquePageId(Connection connection);

	public int loadTmpCheckOuts(Connection connection, PageVo page,
			List<CheckOutVo> checkouts);

	public PageVo loadPageCheckOuts(Connection connection, PageVo page);
}