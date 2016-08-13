package com.alacriti.warehouseservices.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PageVo {
	private int uniqueId;
	private int offset;
	private int totalPages;
	private int pageNo;
	private int limit;
	private List<PlaceholderVo> placeholders;
	private List<CheckOutVo> checkOuts;
	
	public PageVo(int uniqueId, int offset, int totalPages, int pageNo,
			int limit, List<PlaceholderVo> placeholders,
			List<CheckOutVo> checkOuts) {
		super();
		this.uniqueId = uniqueId;
		this.offset = offset;
		this.totalPages = totalPages;
		this.pageNo = pageNo;
		this.limit = limit;
		this.placeholders = placeholders;
		this.checkOuts = checkOuts;
	}

	public List<CheckOutVo> getCheckOuts() {
		return checkOuts;
	}

	public void setCheckOuts(List<CheckOutVo> checkOuts) {
		this.checkOuts = checkOuts;
	}

	public PageVo() {
	}
	
	public PageVo(int uniqueId, int offset, int totalPages, int pageNo,
			int startIndex) {
		this.uniqueId = uniqueId;
		this.offset = offset;
		this.totalPages = totalPages;
		this.pageNo = pageNo;
		this.limit = startIndex;
	}

	public PageVo(int uniqueId, int offset, int totalPages, int pageNo,
			int startIndex, List<PlaceholderVo> placeholders) {
		this.uniqueId = uniqueId;
		this.offset = offset;
		this.totalPages = totalPages;
		this.pageNo = pageNo;
		this.limit = startIndex;
		this.placeholders = placeholders;
	}
	public void add(PlaceholderVo placeholder){
		if(placeholders==null){
			placeholders=new ArrayList<PlaceholderVo>();
		}
		placeholders.add(placeholder);
	}
	public int getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<PlaceholderVo> getPlaceholders() {
		return placeholders;
	}
	public void setPlaceholders(List<PlaceholderVo> placeholders) {
		this.placeholders = placeholders;
	}
	
}
