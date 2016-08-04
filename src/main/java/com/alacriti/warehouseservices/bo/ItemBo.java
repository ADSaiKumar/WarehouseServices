package com.alacriti.warehouseservices.bo;

import java.sql.ResultSet;
import java.util.List;

import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public interface ItemBo {

	public PlaceholderVo getItem(int itemId);

	public int insertStock(PlaceholderVo placeholder);

	public int updateStock(PlaceholderVo placeholder);

	public List<ItemVo> enlistItem();

	public List<ItemVo> addItem(ItemVo item);

	public int addToStock(ItemVo item);

	public ItemVo removeStock(ItemVo item);

}