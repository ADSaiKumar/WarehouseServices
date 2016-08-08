package com.alacriti.warehouseservices.bo.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alacriti.warehouseservices.bo.ItemBo;
import com.alacriti.warehouseservices.bo.WarehouseBo;
import com.alacriti.warehouseservices.dao.WarehouseDao;
import com.alacriti.warehouseservices.dao.impl.DataBaseAgent;
import com.alacriti.warehouseservices.dao.impl.WarehouseDaoImpl;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class WarehouseBoImpl implements WarehouseBo{
	private Connection connection;
	private WarehouseDao warehouseDao;
	private ItemBo itemBo;
	private Logger logger=Logger.getLogger(WarehouseDaoImpl.class);
	public WarehouseBoImpl(){
		connection=DataBaseAgent.getConnection();
		warehouseDao=new WarehouseDaoImpl();
		itemBo=new ItemBoImpl();
	}
	public List<FloorVo> getDetails() {
		List<FloorVo> warehouse = warehouseDao.getDetails(connection);
	
		return warehouse;
	}

	public FloorVo getDetails(int floorId) {
		FloorVo floor=new FloorVo();
		floor.setFloorId(floorId);
		List<PlaceholderVo> placeholders=warehouseDao.getDetails(connection,floorId);
		floor.setPlaceholders(placeholders);
	
		return floor;
	}

	public PlaceholderVo addStock(ItemVo item) {
		PlaceholderVo placeholder=itemBo.getItem(item.getItemId());
		PlaceholderVo newPlaceholder=new PlaceholderVo();
		if(placeholder!=null){
			int stock=placeholder.getItem().getQuantity()+item.getQuantity();
			int capacity=placeholder.getCapacity();
			if(stock<=capacity){
				placeholder.setStock(stock);
			}else{
				placeholder.setStock(capacity);
			}
			item.setQuantity(stock);
			placeholder.setItem(item);
			int i=itemBo.updateStock(placeholder);
		}else{
			placeholder=getAvailability();
			if(placeholder!=null){
				if(item.getQuantity()>placeholder.getCapacity()){
					placeholder.setStock(placeholder.getCapacity());
				}else{
					placeholder.setStock(item.getQuantity());
				}
				placeholder.setItem(item);
				int i=itemBo.insertStock(placeholder);
			}else{
				int i=itemBo.addToStock(item);
			}
		}		
		newPlaceholder=itemBo.getItem(item.getItemId());
		return newPlaceholder;
	}

	private PlaceholderVo getAvailability() {
		return warehouseDao.getAvailability(connection);
	}
	public PlaceholderVo checkOutItem(ItemVo item) {
		PlaceholderVo placeholder=itemBo.getItem(item.getItemId());
		PlaceholderVo placeholde2=new PlaceholderVo();
		if(placeholder!=null){
			int totalStock=placeholder.getItem().getQuantity();
			int availableStock=placeholder.getStock();
			int checkOutStock=item.getQuantity();
			int capacity=placeholder.getCapacity();
			if(checkOutStock<=availableStock){
				int storage=totalStock-availableStock;
				availableStock-=checkOutStock;
				int newStock=availableStock+storage;
				placeholder.getItem().setQuantity(newStock);
				if(newStock>capacity){
					placeholder.setStock(capacity);
				}else{
					placeholder.setStock(newStock);
				}
				if(newStock==0){
					ItemVo newItem=itemBo.removeStock(item);
					if(newItem!=null){
						newStock=newItem.getQuantity();
						if(newStock>capacity){
							placeholder.setStock(capacity);
						}else{
							placeholder.setStock(newStock);
						}
						placeholder.setItem(newItem);
						int i=itemBo.updateStock(placeholder);
					}
				}else{
					int i=itemBo.updateStock(placeholder);
				}
			placeholde2= itemBo.getItem(item.getItemId());
			}else{
				//return Extra Exception
			}
		}else{
			//return Item Not Available Exception
		}
		
		return placeholder;
	}

}