package com.alacriti.warehouseservices.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FloorVo {
	private int floorId;
	private String floorName;
	private List<PlaceholderVo> placeholders;
	public FloorVo(){
		
	}
	
	public FloorVo(int floorId, String floorName,
			List<PlaceholderVo> placeholders) {
		this.floorId = floorId;
		this.floorName = floorName;
		this.placeholders = placeholders;
	}
	public void addPlaceholder(PlaceholderVo placeholder){
		if(placeholders==null){
			placeholders=new ArrayList<PlaceholderVo>();
		}
		placeholders.add(placeholder);
	}
	public int getFloorId() {
		return floorId;
	}
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public List<PlaceholderVo> getPlaceholders() {
		return placeholders;
	}
	public void setPlaceholders(List<PlaceholderVo> placeholders) {
		this.placeholders = placeholders;
	}

	@Override
	public String toString() {
		return "FloorVo [floorId=" + floorId + ", floorName=" + floorName
				+ ", placeholders=" + placeholders + "]";
	}
	
}