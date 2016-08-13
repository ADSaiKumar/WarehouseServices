package com.alacriti.warehousemanagment.bussinesslogics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.warehousemanagment.dataobjects.ItemObject;
import com.alacriti.warehousemanagment.delegates.ServiceRequestFilter;
import com.alacriti.warehouseservices.vo.FloorVo;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.LoggerObject;
import com.alacriti.warehouseservices.vo.OrderVo;
import com.alacriti.warehouseservices.vo.PageVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

public class ServiceMediator {
	PageSetter pageSetter=new PageSetter();
	public String getDashboardPage() {
		Client client=ClientBuilder.newClient();
		client.register(new ServiceRequestFilter());
		List<FloorVo> response=client.target("http://localhost:8080/WarehouseServices/services/warehouse")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<FloorVo>>(){});
		Client client2=ClientBuilder.newClient();
		client2.register(new ServiceRequestFilter());
		List<ItemVo> response2=client2.target("http://localhost:8080/WarehouseServices/services/warehouse/storage")
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<ItemVo>>(){});
		try {
			if(!response.isEmpty()){
			Map input = new HashMap();
			input.put("floors",response);
			input.put("items",response2);
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
		client.register(new ServiceRequestFilter());
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
		client.register(new ServiceRequestFilter());
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
		client.register(new ServiceRequestFilter());
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

	public String searchByFloor(int floorId,PageVo page) {
		Client client=ClientBuilder.newClient();
		client.register(new ServiceRequestFilter());
		WebTarget webTarget;
		if(page.getUniqueId()!=0 && page.getLimit()!=0){
			webTarget=client.target("http://localhost:8080/WarehouseServices/services/warehouse/"+floorId+"?uniqueId="+page.getUniqueId()+"&offset="+page.getOffset()+"&limit="+page.getLimit());
		}else{
			webTarget=client.target("http://localhost:8080/WarehouseServices/services/warehouse/"+floorId);
		}
		Response response=webTarget.request().get();
		PageVo responsePage=response.readEntity(PageVo.class);
		List<PlaceholderVo> placeholders=responsePage.getPlaceholders();
		List<ItemObject> items=new ArrayList<ItemObject>();
		try{
			for(PlaceholderVo placeholder:placeholders){
				items.add(ItemObject.getItem(placeholder));
			}
		}
		catch(NullPointerException ne){
			LoggerObject.errorLog(ne);
		}
		List<PageVo> pages=new ArrayList<PageVo>();
		int totalPages=responsePage.getTotalPages();
		LoggerObject.infoLog(totalPages);
		for(int i=1;i<=totalPages;i++){
			PageVo tmp=new PageVo();
			tmp.setPageNo(i);
			tmp.setUniqueId(responsePage.getUniqueId());
			tmp.setOffset((i-1)*responsePage.getLimit());
			tmp.setLimit(responsePage.getLimit());
			pages.add(tmp);
		}
		Map dataModel=new HashMap();
		dataModel.put("items",items);
		dataModel.put("hidden"," ");
		dataModel.put("pages", pages);
		dataModel.put("activepage",responsePage.getPageNo());
		int pageNo=responsePage.getPageNo();
		if(pageNo==1){
			dataModel.put("left","disabled");
			dataModel.put("right","waves-effect");	
		}
		else if(pageNo==totalPages){
			dataModel.put("left","waves-effect");
			dataModel.put("right","disabled");	
		}else{
			dataModel.put("left","waves-effect");
			dataModel.put("right","waves-effect");
	}
		return pageSetter.getRawTempleteString("searchresults.ftl", dataModel);
	}

	public String searchByItem(int itemId) {
		Client client=ClientBuilder.newClient();
		client.register(new ServiceRequestFilter());
		Response response=client.target("http://localhost:8080/WarehouseServices/services/warehouse/items/"+itemId)
				.request().get();
		PlaceholderVo placeholder=response.readEntity(PlaceholderVo.class);
		ItemObject item=ItemObject.getItem(placeholder);
		List<ItemObject> items=new ArrayList<ItemObject>();
		items.add(item);
		PageVo page=new PageVo();
		page.setPageNo(1);
		Map dataModel=new HashMap();
		dataModel.put("items",items);
		dataModel.put("pages",Arrays.asList(page));
		dataModel.put("hidden","hidden");
		dataModel.put("left","disabled");
		dataModel.put("right","disabled");
		dataModel.put("activepage",1);
		return pageSetter.getRawTempleteString("searchresults.ftl", dataModel);
	}

	public String getOrders() {
		Client client=ClientBuilder.newClient();
		client.register(new ServiceRequestFilter());
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
