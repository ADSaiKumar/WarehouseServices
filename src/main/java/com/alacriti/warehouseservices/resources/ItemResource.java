package com.alacriti.warehouseservices.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alacriti.warehouseservices.bo.ItemBo;
import com.alacriti.warehouseservices.bo.impl.ItemBoImpl;
import com.alacriti.warehouseservices.vo.ItemVo;
import com.alacriti.warehouseservices.vo.PlaceholderVo;

@Path("/")
@Produces(MediaType.TEXT_XML)
public class ItemResource {
	private ItemBo itemBo=new ItemBoImpl();
	@GET
	public List<ItemVo> getItems(){
		return itemBo.enlistItem();
	}
	@POST
	public List<ItemVo> addItem(ItemVo item){
		return itemBo.addItem(item);
	}
	
	@GET
	@Path("/{itemId}")
	public PlaceholderVo getService(@PathParam("itemId") int itemId) {
		return itemBo.getItem(itemId);
	}
}