package com.cnet.payment.provider.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnet.payment.provider.constant.PaymentProviderConstants;
import com.cnet.payment.provider.dao.OrderDao;
import com.cnet.payment.provider.dao.StatusDao;
import com.cnet.payment.provider.dao.TransactionDao;
import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.Transaction;
import com.cnet.payment.provider.entity.TransactionType;

@Service
public class OrderServiceImp implements OrderService {
	
	private static final Logger logger = Logger.getLogger(OrderServiceImp.class);

	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private StatusDao statusDao;
	




	@Transactional
	@Override
	public void createOrder(Order order, TransactionType transactionType) {
		
		orderDao.add(order);
		transactionDao.add(new Transaction(order, statusDao.getStatusById(PaymentProviderConstants.SUCCESS_STATUS_ID), transactionType));
		
	}

	@Transactional
	@Override
	public void createTransaction(Order order, TransactionType transactionType) {
		orderDao.update(order);
		transactionDao.add(new Transaction(order, statusDao.getStatusById(PaymentProviderConstants.SUCCESS_STATUS_ID), transactionType));
		
	}
	


}
