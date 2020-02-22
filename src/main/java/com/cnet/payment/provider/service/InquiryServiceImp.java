package com.cnet.payment.provider.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnet.payment.provider.bean.ArgumentBean;
import com.cnet.payment.provider.bean.ReplyBean;
import com.cnet.payment.provider.constant.PaymentProviderConstants;
import com.cnet.payment.provider.controller.TransactionController;
import com.cnet.payment.provider.dao.CurrencyDao;
import com.cnet.payment.provider.dao.OrderDao;
import com.cnet.payment.provider.dao.OrderStatusDao;
import com.cnet.payment.provider.dao.PaymentMethodDao;
import com.cnet.payment.provider.dao.TransactionTypeDao;
import com.cnet.payment.provider.entity.Currency;
import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.OrderStatus;
import com.cnet.payment.provider.entity.PaymentMethod;
import com.cnet.payment.provider.entity.TransactionType;

/**
 * @author TAREQSAAD
 *
 */
@Service
public class InquiryServiceImp implements InquiryService {
	
	private static final Logger logger = Logger.getLogger(InquiryServiceImp.class);


	@Autowired
	private CurrencyDao currencyDao;

	@Autowired
	private PaymentMethodDao paymentMethodDao;

	@Autowired
	private TransactionTypeDao transactionTypeDao;

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderStatusDao orderStatusDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnet.payment.provider.service.InquiryService#getCurrency(java.lang.
	 * String)
	 */
	@Transactional
	@Override
	public Currency getCurrency(String currencyCode) throws Exception {
		return currencyDao.getCurrency(currencyCode);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cnet.payment.provider.service.InquiryService#getPaymentMethod(java.lang.
	 * String)
	 */
	@Transactional
	@Override
	public PaymentMethod getPaymentMethod(String paymentMethod) throws Exception {
		return paymentMethodDao.getPaymentMethod(paymentMethod);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cnet.payment.provider.service.InquiryService#getTransactionType(java.lang
	 * .String)
	 */
	@Transactional
	@Override
	public TransactionType getTransactionType(String transactionType) {
		return transactionTypeDao.getTransactionType(transactionType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cnet.payment.provider.service.InquiryService#getOrderByOrderId(java.lang.
	 * String, java.lang.String)
	 */
	@Transactional
	@Override
	public Order getOrderByOrderId(String clientId, String orderId) {
		return orderDao.getOrderByOrderId(clientId, orderId);
	}
	
	@Transactional
	@Override
	public OrderStatus getCapturedOrderStatus() {
		return orderStatusDao.getOrderStatusById(PaymentProviderConstants.CAPTERED_STATUS_ID);
	}
	
	@Transactional
	@Override
	public List<Order> getPendingOrders(){
		
		ArrayList<OrderStatus> list = new ArrayList<OrderStatus>();
		
		return orderDao.listOrdersByStatus(orderStatusDao.getPendingOrderStatus());
	}
	
	@Transactional
	@Override
	public BigDecimal getOrderTotalAmount(List<OrderStatus>  orderStatusList , String clientId){
		
		
		BigDecimal totalAmount = orderDao.getTotalAmount(orderStatusList , clientId);
		if(totalAmount!=null)
			return orderDao.getTotalAmount(orderStatusList , clientId);
		else
			return new BigDecimal(0);
				
	}
	
	

}
