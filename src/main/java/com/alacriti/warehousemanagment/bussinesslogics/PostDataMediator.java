package com.alacriti.warehousemanagment.bussinesslogics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.warehousemanagment.dataobjects.ItemObject;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.OrderVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class PostDataMediator {
		private PageSetter pageSetter=new PageSetter();
	public String addStockToWarehouse(int itemId, int itemQuantity) {
		ItemVo item=new ItemVo();
		item.setItemId(itemId);
		item.setItemName("");
		item.setQuantity(itemQuantity);
		LoggerObject.infoLog(item);
		LoggerObject.infoLog(Entity.xml(item).toString());
		Client client=ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/WarehouseServices/services/warehouse")
				.request()
				.post(Entity.xml(item));
		PlaceholderVo placeholder=response.readEntity(PlaceholderVo.class);
		ItemObject item2=ItemObject.getItem(placeholder);
		List<ItemObject> items=new ArrayList<ItemObject>();
		items.add(item2);
		Map<String,List<ItemObject>> dataModel=new HashMap<String,List<ItemObject>>();
		dataModel.put("items",items);
		return pageSetter.getRawTempleteString("searchresults.ftl", dataModel);
	}
	public String removeStockFromWarehouse(int itemId, int itemQuantity) {
		ItemVo item=new ItemVo();
		item.setItemId(itemId);
		item.setItemName("");
		item.setQuantity(itemQuantity);
		Client client=ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/WarehouseServices/services/warehouse")
				.request()
				.put(Entity.xml(item));
		PlaceholderVo placeholder=response.readEntity(PlaceholderVo.class);
		ItemObject item2=ItemObject.getItem(placeholder);
		List<ItemObject> items=new ArrayList<ItemObject>();
		items.add(item2);
		Map<String,List<ItemObject>> dataModel=new HashMap<String,List<ItemObject>>();
		dataModel.put("items",items);
		return pageSetter.getRawTempleteString("searchresults.ftl", dataModel);
	}
	public String placeOrders(List<OrderVo> orderSummary) {
		Client client=ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/WarehouseServices/services/warehouse/orders")
				.request()
				.post(Entity.json(orderSummary));
		LoggerObject.infoLog(response.getStatus());
		List<OrderVo> orderDetails=response.readEntity(new GenericType<List<OrderVo>>(){});
		LoggerObject.infoLog(orderDetails.toString());
		Map<String,List<OrderVo>> dataModel=new HashMap<String,List<OrderVo>>();
		dataModel.put("orders",orderDetails);
		return pageSetter.getRawTempleteString("orderstable.ftl", dataModel);
	}
	public String addItemToList(ItemVo item) {
		Client client=ClientBuilder.newClient();
		List<ItemVo> response=client.target("http://localhost:8080/WarehouseServices/services/warehouse/items")
				.request()
				.post(Entity.xml(item))
				.readEntity(new GenericType<List<ItemVo>>(){});
		LoggerObject.infoLog("\n New "+response);
		String data=null;
		try {
			Map<String,List<ItemVo>> dataModel = new HashMap<String,List<ItemVo>>();
			dataModel.put("items",response);
			data=pageSetter.getRawTempleteString("itemslist.ftl", dataModel);
			} catch (Exception e){
				LoggerObject.errorLog(e);
			}
		return data;
	}

}
