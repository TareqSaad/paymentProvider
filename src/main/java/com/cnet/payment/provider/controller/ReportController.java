package com.cnet.payment.provider.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.cnet.payment.provider.bean.ArgumentBean;
import com.cnet.payment.provider.bean.ReportBean;
import com.cnet.payment.provider.config.AppConfig;
import com.cnet.payment.provider.constant.ErrorConstants;
import com.cnet.payment.provider.constant.PaymentProviderConstants;
import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.OrderStatus;
import com.cnet.payment.provider.entity.TransactionType;
import com.cnet.payment.provider.service.InquiryService;

/**
 * @author TAREQ SAAD
 *
 */
@Controller
public class ReportController {

	private static final Logger logger = Logger.getLogger(ReportController.class);

	@Autowired
	InquiryService inquiryService;

	/**
	 * @param transactionType
	 * @param argumentBean
	 * @throws Exception
	 */
	@Transactional
	public ReportBean getReport(TransactionType transactionType, ArgumentBean argumentBean) throws Exception {
		logger.debug("getReport start");
		ReportBean reportBean = new ReportBean();
		reportBean.setReportList(new ArrayList<String>());
		if (transactionType.getTransactionTypeDescription().equalsIgnoreCase(PaymentProviderConstants.FIND_BY_ORDER)) {

			if (argumentBean.getClientId() == null) {
				reportBean.setError(true);
				reportBean.setErrorMessage(ErrorConstants.ERROR_CLIENT_ID);
				return reportBean;
			}
			if (argumentBean.getOrderId() == null) {
				reportBean.setError(true);
				reportBean.setErrorMessage(ErrorConstants.ERROR_ORDER_ID);
				return reportBean;
			}
			Order order = inquiryService.getOrderByOrderId(argumentBean.getClientId(), argumentBean.getOrderId());
			if (order != null) {
				reportBean.getReportList().add(order.toString());
				return reportBean;
			}
			else{
				reportBean.setError(true);
				reportBean.setErrorMessage(ErrorConstants.ERROR_INVALID_ORDER_ID);
				return reportBean;
			}
		} 
		
		if (transactionType.getTransactionTypeDescription().equalsIgnoreCase(PaymentProviderConstants.FIND_TOTAL)) {
			List<OrderStatus> orderStatusList = new ArrayList<OrderStatus>();
			orderStatusList.add(inquiryService.getCapturedOrderStatus());
			reportBean.getReportList().add(inquiryService.getOrderTotalAmount(orderStatusList, argumentBean.getClientId()).toString());
			return reportBean;
		} 
		if (transactionType.getTransactionTypeDescription().equalsIgnoreCase(PaymentProviderConstants.FIND_PENDING)) {
			for (Order order : inquiryService.getPendingOrders()) {
				reportBean.getReportList().add(order.toString());
			}
			return reportBean;
		}

		reportBean.setError(true);
		reportBean.setErrorMessage(ErrorConstants.ERROR_INVALID_REPORT);
		return reportBean;
	

	}

}
