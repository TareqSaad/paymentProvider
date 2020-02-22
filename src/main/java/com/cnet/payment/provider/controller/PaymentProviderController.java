package com.cnet.payment.provider.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cnet.payment.provider.bean.ArgumentBean;
import com.cnet.payment.provider.bean.ReplyBean;
import com.cnet.payment.provider.bean.ReportBean;
import com.cnet.payment.provider.config.AppConfig;
import com.cnet.payment.provider.entity.TransactionType;
import com.cnet.payment.provider.service.InquiryService;
import com.cnet.payment.provider.utils.ApplicationUtility;

/**
 * @author TAREQ SAAD
 *
 */
public class PaymentProviderController {

	private static final Logger logger = Logger.getLogger(PaymentProviderController.class);

	/**
	 * @param args
	 * @throws Exception
	 */
	public void processArguments(String[] args) throws Exception {
		
		logger.debug("ProcessArguments start");
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		TransactionController transactionController = context.getBean(TransactionController.class);
		
		String inputTransactionType = args[0];
		TransactionType transactionType = transactionController.getTransactionType(inputTransactionType);
		ArgumentBean argumentBean = new ArgumentBean();
		argumentBean = new ApplicationUtility().extractArguments(args);
		if (transactionType.isReport()) {
			ReportController reportController = context.getBean(ReportController.class);
			logger.debug("Transaction type is report");
			ReportBean reportBean = new ReportBean();
			reportBean=reportController.getReport(transactionType, argumentBean);
			if(!reportBean.isError()) {
				logger.info(transactionType.getTransactionTypeDescription() + " :");
				reportBean.getReportList().forEach(report -> logger.info(report.toString()));
			}
		} else {
			ReplyBean replyBean = transactionController.processTransaction(transactionType, argumentBean);
			if(replyBean.isSuccess()) {
				logger.info(replyBean.getMessage());
			}
			else
				throw new Exception(replyBean.getMessage());
		}
	}

}
