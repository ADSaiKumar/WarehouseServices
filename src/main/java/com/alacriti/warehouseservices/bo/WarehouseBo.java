package com.alacriti.warehouseservices.bo;

import java.util.List;

import com.alacriti.warehouseservices.utilities.ExtraRemovalException;
import com.alacriti.warehouseservices.vo.CheckOutVo;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.PageVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public interface WarehouseBo {
	public List<FloorVo> getDetails();
	public PageVo getDetails(int FloorId);
	public PlaceholderVo addStock(ItemVo item);
	public PlaceholderVo checkOutItem(ItemVo item) throws ExtraRemovalException;
	public List<ItemVo> getStorageDetails();
	public PageVo getPage(PageVo page);
	public List<CheckOutVo> getCheckOutDetails();
}