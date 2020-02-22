package com.cnet.payment.provider.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import com.cnet.payment.provider.bean.ArgumentBean;
import com.cnet.payment.provider.bean.ReplyBean;
import com.cnet.payment.provider.config.AppConfig;
import com.cnet.payment.provider.constant.ErrorConstants;
import com.cnet.payment.provider.constant.PaymentProviderConstants;
import com.cnet.payment.provider.entity.Currency;
import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.OrderPK;
import com.cnet.payment.provider.entity.PaymentMethod;
import com.cnet.payment.provider.entity.TransactionType;
import com.cnet.payment.provider.service.InquiryService;
import com.cnet.payment.provider.service.OrderService;

/**
 * 
 * @author TAREQ SAAD
 *
 */

@Controller
public class TransactionController {

	private static final Logger logger = Logger.getLogger(TransactionController.class);

	@Autowired
	OrderService orderService;
	@Autowired
	InquiryService inquiryService;

	/**
	 * Method to get transaction type details and throw error if transaction isn't
	 * valid
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */
	public TransactionType getTransactionType(String command) throws Exception {
		logger.debug("getTransactiontype start");
		TransactionType transactionType = inquiryService.getTransactionType(command.toUpperCase());
		if (transactionType == null) {
			throw new Exception(ErrorConstants.ERROR_TRANSACTION_NOT_FOUND);
		} else {
			logger.debug(
					"getTransactiontype returned transaction type: " + transactionType.getTransactionTypeDescription());
			return transactionType;
		}
	}

	/**
	 * Method to process order ,Register new order , Authorise order , capture Order
	 * and cancel Order
	 * 
	 * @param transactionType
	 * @param argumentBean
	 * @return {@link ReplyBean}
	 */
	public ReplyBean processTransaction(TransactionType transactionType, ArgumentBean argumentBean) {
		logger.debug("Process transaction start");
		logger.debug(transactionType.getTransactionTypeDescription());
		ReplyBean replyBean = new ReplyBean();
		if (argumentBean.getClientId() == null) {
			replyBean.setSuccess(false);
			replyBean.setMessage(ErrorConstants.ERROR_CLIENT_ID);
			return replyBean;
		}
		if (argumentBean.getOrderId() == null) {
			replyBean.setSuccess(false);
			replyBean.setMessage(ErrorConstants.ERROR_ORDER_ID);
			return replyBean;
		}

		Order order = inquiryService.getOrderByOrderId(argumentBean.getClientId(), argumentBean.getOrderId());
		// Register new Order
		if (transactionType.isInitatorType()) {
			// Order can't be duplicated order identified by Order_id and Order_client
			if (order != null) {
				replyBean.setSuccess(false);
				replyBean.setMessage(ErrorConstants.ERROR_DUPLICATE_ORDER);
				return replyBean;
			} else
				return RegisterOrder(transactionType, argumentBean, replyBean, order);
		}

		// new order status can't be the same to order status
		if (order.getOrderStatus().getOrderStatusId()
				.equals(transactionType.getTransactionTypeStatus().getOrderStatusId())) {
			logger.info("Order Current status :" + transactionType.getTransactionTypeStatus().getStatusDescription());
			replyBean.setSuccess(false);
			replyBean.setMessage(ErrorConstants.ERROR_TRANSACTION_WRONG_STATUS);
			return replyBean;
		}

		// check on the order workflow if the new status is applicable or not
		if (transactionType.getPreviousStatus() == null||transactionType.getPreviousStatus().getOrderStatusId().equals(order.getOrderStatus().getOrderStatusId())
				) {
			order.setOrderStatus(transactionType.getTransactionTypeStatus());
			orderService.createTransaction(order, transactionType);
			replyBean.setSuccess(true);
			replyBean.setMessage(
					transactionType.getTransactionTypeDescription() + ":" + PaymentProviderConstants.SUCCESS);
			return replyBean;
		} else {
			logger.info("Order Current status :" + transactionType.getTransactionTypeStatus().getStatusDescription());
			replyBean.setSuccess(false);
			replyBean.setMessage(ErrorConstants.ERROR_TRANSACTION_WRONG_STATUS);
			return replyBean;
		}

	}

	/**
	 * 
	 * @param transactionType
	 * @param argumentBean
	 * @param replyBean
	 * @param order
	 * @return {@link ReplyBean}
	 */
	private ReplyBean RegisterOrder(TransactionType transactionType, ArgumentBean argumentBean, ReplyBean replyBean,
			Order order) {

		if (argumentBean.getAmount() == null) {
			replyBean.setSuccess(false);
			replyBean.setMessage(ErrorConstants.ERROR_AMOUNT_REQUIRED);
			return replyBean;
		}
		if (argumentBean.getCurrencyCode() == null) {
			replyBean.setSuccess(false);
			replyBean.setMessage(ErrorConstants.ERROR_CURRENCY_REQUIRED);
			return replyBean;
		}
		if (argumentBean.getPaymentMethod() == null) {
			replyBean.setSuccess(false);
			replyBean.setMessage(ErrorConstants.ERROR_PAYMETHOD_REQUIRED);
			return replyBean;
		}
		if (argumentBean.getPayTokenId() == null) {
			replyBean.setSuccess(false);
			replyBean.setMessage(ErrorConstants.ERROR_PAYTOKEN_REQUIRED);
			return replyBean;
		}
		double amount;
		try {
			amount = Double.parseDouble(argumentBean.getAmount());
		} catch (Exception e) {
			replyBean.setSuccess(false);
			replyBean.setMessage(ErrorConstants.ERROR_AMOUNT_NOT_VALID_FORMAT);
			return replyBean;
		}

		PaymentMethod payment = new PaymentMethod();

		logger.debug(payment.getPaymentMethodDescription());

		Currency currency = new Currency();

		try {

			payment = inquiryService.getPaymentMethod(argumentBean.getPaymentMethod());

			currency = inquiryService.getCurrency(argumentBean.getCurrencyCode());
		} catch (Exception e) {
			replyBean.setSuccess(false);
			replyBean.setMessage(e.getMessage());
			return replyBean;
		}

		OrderPK pk = new OrderPK();
		pk.setClientId(argumentBean.getClientId());
		pk.setOrderId(argumentBean.getOrderId());

		orderService.createOrder(new Order(pk, currency, transactionType.getTransactionTypeStatus(), payment,
				argumentBean.getPayTokenId(), amount), transactionType);

		replyBean.setSuccess(true);
		replyBean.setMessage(transactionType.getTransactionTypeDescription() + ":" + PaymentProviderConstants.SUCCESS);
		return replyBean;
	}

}
