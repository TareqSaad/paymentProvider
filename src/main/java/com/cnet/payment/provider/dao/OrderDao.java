package com.cnet.payment.provider.dao;

import java.math.BigDecimal;
import java.util.List;

import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.OrderStatus;

public interface OrderDao {
	
	void add(Order order);
	List<Order> listOrders();
	Order getOrderByOrderId(String clientId , String orderId);
	void update(Order order);
	List<Order> listOrdersByStatus(List<OrderStatus> orderStatusList);
	public BigDecimal getTotalAmount(List<OrderStatus> orderStatusList, String clientId);
}
