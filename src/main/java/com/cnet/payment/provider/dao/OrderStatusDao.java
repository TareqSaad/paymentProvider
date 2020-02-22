package com.cnet.payment.provider.dao;

import java.util.List;

import com.cnet.payment.provider.entity.OrderStatus;

public interface OrderStatusDao {

	List<OrderStatus> listOrderStatus ();
	public OrderStatus getOrderStatusById(String orderStatusId);
	public List<OrderStatus> getPendingOrderStatus();
}
