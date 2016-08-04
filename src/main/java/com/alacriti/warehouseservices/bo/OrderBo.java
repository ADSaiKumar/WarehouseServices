package com.alacriti.warehouseservices.bo;

import java.util.List;

import com.alacriti.warehouseservices.vo.OrderVo;

public interface OrderBo {

	public List<OrderVo> checkOrder();

	public List<OrderVo> setOrder(List<OrderVo> orderSummary);

}
