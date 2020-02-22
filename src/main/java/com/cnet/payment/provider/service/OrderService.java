package com.cnet.payment.provider.service;

import java.util.List;

import com.cnet.payment.provider.bean.ArgumentBean;
import com.cnet.payment.provider.bean.ReplyBean;
import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.TransactionType;

public interface OrderService {
	
	void createOrder(Order order , TransactionType transactionType);
	void createTransaction(Order order , TransactionType transactionType);
	
}
