package com.cnet.payment.provider.service;

import java.math.BigDecimal;
import java.util.List;

import com.cnet.payment.provider.entity.Currency;
import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.OrderStatus;
import com.cnet.payment.provider.entity.PaymentMethod;
import com.cnet.payment.provider.entity.TransactionType;

/**
 * @author TAREQSAAD
 *
 */
public interface InquiryService {
	
	/**
	 * @param currencyCode
	 * @return
	 * @throws Exception
	 */
	Currency getCurrency(String currencyCode) throws Exception;
	/**
	 * @param paymentMethod
	 * @return
	 * @throws Exception
	 */
	PaymentMethod getPaymentMethod(String paymentMethod) throws Exception;
	/**
	 * @param transactionType
	 * @return
	 */
	TransactionType getTransactionType(String transactionType);
	/**
	 * @param clientId
	 * @param orderId
	 * @return
	 */
	Order getOrderByOrderId(String clientId, String orderId);
	
	/**
	 * 
	 * @return {@link OrderStatus}
	 */
	public OrderStatus getCapturedOrderStatus();
	
	/**
	 * 
	 * @return
	 */
	public List<Order> getPendingOrders();
	
	/**
	 * 
	 * @param orderStatusList
	 * @return
	 */
	public BigDecimal getOrderTotalAmount(List<OrderStatus>  orderStatusList , String clientId);

}
