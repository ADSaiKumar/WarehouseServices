package com.alacriti.warehousemanagment.bussinesslogics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.warehousemanagment.dataobjects.ItemObject;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.OrderVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class ServiceMediator {
	PageSetter pageSetter=new PageSetter();
	public String getDashboardPage() {
		Client client=ClientBuilder.newClient();
		List<FloorVo> response=client.target("http://localhost:8080/WarehouseServices/services/warehouse")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<FloorVo>>(){});
		try {
			if(!response.isEmpty()){
			Map<String,List<FloorVo>> input = new HashMap<String,List<FloorVo>>();
			input.put("floors",response);
			String page=pageSetter.templateLoader(PageSetter.DASHBOARD,"dashboard.ftl", input);
			return page;
			}
			} catch (Exception e){
				LoggerObject.errorLog(e);
			}
		return null;
	}

	public String getAddStockPage() {
		Client client=ClientBuilder.newClient();
		List<ItemVo> response=client.target("http://localhost:8080/WarehouseServices/services/warehouse/items")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<ItemVo>>(){});
		try {
			if(!response.isEmpty()){
			Map<String,List<ItemVo>> input = new HashMap<String,List<ItemVo>>();
			input.put("items",response);
			String page=pageSetter.templateLoader(PageSetter.ADDSTOCK,"addstock.ftl", input);
			return page;
			}
			} catch (Exception e){
				LoggerObject.errorLog(e);
			}
		return null;
	}

	public String getCheckOutStockPage() {
		Client client=ClientBuilder.newClient();
		List<ItemVo> response=client.target("http://localhost:8080/WarehouseServices/services/warehouse/items")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<ItemVo>>(){});
		try {
			if(!response.isEmpty()){
			Map<String,List<ItemVo>> input = new HashMap<String,List<ItemVo>>();
			input.put("items",response);
			String page=pageSetter.templateLoader(PageSetter.CHECKOUTSTOCK,"checkoutstock.ftl", input);
			return page;
			}
			} catch (Exception e){
				LoggerObject.errorLog(e);
			}
		return null;
	}

	public String searchStockPage() {
		Client client=ClientBuilder.newClient();
		List<ItemVo> response=client.target("http://localhost:8080/WarehouseServices/services/warehouse/items")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<ItemVo>>(){});
		try {
			if(!response.isEmpty()){
			Map<String,List<ItemVo>> input = new HashMap<String,List<ItemVo>>();
			input.put("items",response);
			String page=pageSetter.templateLoader(PageSetter.SEARCH,"search.ftl", input);
			return page;
			}
			} catch (Exception e){
				LoggerObject.errorLog(e);
			}
		return null;
	}

	public String searchByFloor(int floorId) {
		Client client=ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/WarehouseServices/services/warehouse/"+floorId)
				.request().get();
		FloorVo floor=response.readEntity(FloorVo.class);
		LoggerObject.infoLog("floor");
		LoggerObject.errorLog("Floor");
		LoggerObject.infoLog(floor);
		List<PlaceholderVo> placeholders=floor.getPlaceholders();
		LoggerObject.infoLog(placeholders);
		LoggerObject.errorLog(placeholders);
		List<ItemObject> items=new ArrayList<ItemObject>();
		for(PlaceholderVo placeholder:placeholders){
			items.add(ItemObject.getItem(placeholder));
		}
		Map<String,List<ItemObject>> dataModel=new HashMap<String,List<ItemObject>>();
		dataModel.put("items",items);
		return pageSetter.getRawTempleteString("searchresults.ftl", dataModel);
	}

	public String searchByItem(int itemId) {
		Client client=ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/WarehouseServices/services/warehouse/items/"+itemId)
				.request().get();
		PlaceholderVo placeholder=response.readEntity(PlaceholderVo.class);
		ItemObject item=ItemObject.getItem(placeholder);
		List<ItemObject> items=new ArrayList<ItemObject>();
		items.add(item);
		Map<String,List<ItemObject>> dataModel=new HashMap<String,List<ItemObject>>();
		dataModel.put("items",items);
		return pageSetter.getRawTempleteString("searchresults.ftl", dataModel);
	}

	public String getOrders() {
		Client client=ClientBuilder.newClient();
		List<OrderVo> response=client.target("http://localhost:8080/WarehouseServices/services/warehouse/orders")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<OrderVo>>(){});
		try {
			if(!response.isEmpty()){
			Map<String,List<OrderVo>> input = new HashMap<String,List<OrderVo>>();
			input.put("orders",response);
			String page=pageSetter.templateLoader(PageSetter.ORDER,"orders.ftl", input);
			return page;
			}
			} catch (Exception e){
				LoggerObject.errorLog(e);
			}
		return null;
	}

}
