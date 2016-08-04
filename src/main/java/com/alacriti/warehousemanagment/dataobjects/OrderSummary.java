package com.alacriti.warehousemanagment.dataobjects;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;

import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.OrderVo;

public class OrderSummary {
	@FormParam("itemId1")
	String itemId1;
	@FormParam("orderQuantity1")
	String orderQuantity1;
	@FormParam("itemId2")
	String itemId2;
	@FormParam("orderQuantity2")
	String orderQuantity2;
	@FormParam("itemId3")
	String itemId3;
	@FormParam("orderQuantity3")
	String orderQuantity3;
	@FormParam("itemId4")
	String itemId4;
	@FormParam("orderQuantity4")
	String orderQuantity4;
	@FormParam("itemId5")
	String itemId5;
	@FormParam("orderQuantity5")
	String orderQuantity5;
	public List<OrderVo> getOrderSummary(){
		List<OrderVo> orderSummary=new ArrayList<OrderVo>();
		if(!itemId1.equals("")){
			ItemVo item=new ItemVo();
			item.setItemId(Integer.parseInt(itemId1));
			OrderVo order=new OrderVo();
			order.setItem(item);
			order.setOrderAmount(Integer.parseInt(orderQuantity1));
			orderSummary.add(order);
		}
		if(!itemId2.equals("")){
			ItemVo item=new ItemVo();
			item.setItemId(Integer.parseInt(itemId2));
			OrderVo order=new OrderVo();
			order.setItem(item);
			order.setOrderAmount(Integer.parseInt(orderQuantity2));
			orderSummary.add(order);
		}
		if(!itemId3.equals("")){
			ItemVo item=new ItemVo();
			item.setItemId(Integer.parseInt(itemId3));
			OrderVo order=new OrderVo();
			order.setItem(item);
			order.setOrderAmount(Integer.parseInt(orderQuantity3));
			orderSummary.add(order);
		}
		if(!itemId4.equals("")){
			ItemVo item=new ItemVo();
			item.setItemId(Integer.parseInt(itemId4));
			OrderVo order=new OrderVo();
			order.setItem(item);
			order.setOrderAmount(Integer.parseInt(orderQuantity4));
			orderSummary.add(order);
		}
		if(!itemId5.equals("")){
			ItemVo item=new ItemVo();
			item.setItemId(Integer.parseInt(itemId5));
			OrderVo order=new OrderVo();
			order.setItem(item);
			order.setOrderAmount(Integer.parseInt(orderQuantity5));
			orderSummary.add(order);
		}
		return orderSummary;
	}
}
