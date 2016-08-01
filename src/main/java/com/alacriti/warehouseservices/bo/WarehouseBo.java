package com.alacriti.warehouseservices.bo;

import java.util.List;

import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public interface WarehouseBo {
	public List<FloorVo> getDetails();
	public FloorVo getDetails(int FloorId);
	public PlaceholderVo addStock(ItemVo item);
	public PlaceholderVo checkOutItem(ItemVo item);
}