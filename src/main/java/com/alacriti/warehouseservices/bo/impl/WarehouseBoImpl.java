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
		
		Map<Integer,FloorVo> warehouse=new HashMap<Integer,FloorVo>();
		ResultSet resultSet=warehouseDao.getDetails(connection);
			int floorId;
			try {
				while(resultSet.next()){
					floorId=resultSet.getInt("floor_id");
					ItemVo item=new ItemVo(resultSet.getInt("item_id"),resultSet.getString("item_name"),(resultSet.getInt("stock")+resultSet.getInt("storage")));
					logger.info(item);
					PlaceholderVo placeholder=new PlaceholderVo(item,resultSet.getString("ph_id"),resultSet.getInt("stock"),resultSet.getInt("capacity"));
					logger.info(placeholder);
					if(warehouse.containsKey(floorId)){
						warehouse.get(floorId).addPlaceholder(placeholder);
						logger.info(warehouse.get(floorId));
					}
					else{
						warehouse.put(floorId, new FloorVo(floorId,resultSet.getString("floor_name"),new ArrayList<PlaceholderVo>()));
						warehouse.get(floorId).addPlaceholder(placeholder);
					}
				}
			} catch (SQLException e) {
				LoggerObject.errorLog(e.getStackTrace());
			}
			LoggerObject.infoLog(warehouse.values());
			return new ArrayList<FloorVo>(warehouse.values());
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
		return itemBo.getItem(item.getItemId());
	}

	private PlaceholderVo getAvailability() {
		return warehouseDao.getAvailability(connection);
	}
	public PlaceholderVo checkOutItem(ItemVo item) {
		PlaceholderVo placeholder=itemBo.getItem(item.getItemId());
		if(placeholder!=null){
			int totalStock=placeholder.getItem().getQuantity();
			int availableStock=placeholder.getStock();
			int checkOutStock=item.getQuantity();
			int capacity=placeholder.getCapacity();
			if(checkOutStock<=availableStock){
				if(totalStock==availableStock){
					availableStock-=checkOutStock;
					placeholder.getItem().setQuantity(availableStock);
					placeholder.setStock(availableStock);
				}else{
					int storage=totalStock-availableStock;
					availableStock-=checkOutStock;
					int newStock=availableStock+storage;
					placeholder.getItem().setQuantity(newStock);
					if(newStock>capacity){
						placeholder.setStock(capacity);
					}else{
						placeholder.setStock(newStock);
					}
				}
			int i=itemBo.updateStock(placeholder);
			return itemBo.getItem(item.getItemId());
			}else{
				//return Extra Exception
			}
		}else{
			//return Item Not Available Exception
		}
		return null;
	}

}