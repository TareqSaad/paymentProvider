package com.cnet.payment.provider.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cnet.payment.provider.bean.ArgumentBean;
import com.cnet.payment.provider.bean.ReplyBean;
import com.cnet.payment.provider.dao.OrderDao;
import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.OrderStatus;
import com.cnet.payment.provider.entity.TransactionType;
import com.cnet.payment.provider.service.InquiryService;
import com.cnet.payment.provider.service.InquiryServiceImp;
import com.cnet.payment.provider.service.OrderService;
import com.cnet.payment.provider.service.OrderServiceImp;

public class ApplicationControllerTest {

	@Mock
	InquiryService inquiryService;

	@Mock
	OrderService orderService;

	@InjectMocks
	TransactionController transactionController;

	@Spy
	TransactionType transactionType;

	@Spy
	ArgumentBean argumentBean;

	@Spy
	OrderStatus transactionTypeStatus;

	@Spy
	ReplyBean replyBean;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initialize();

	}

	/**
	 * 
	 */
	private void initialize() {
		transactionType.setInitatorType(true);
		transactionTypeStatus.setOrderStatusId("001");
		transactionTypeStatus.setStatusDescription("INITIATED");

		transactionType.setTransactionTypeStatus(transactionTypeStatus);
		transactionType.setTransactionTypeId("001");
		transactionType.setTransactionTypeDescription("REGISTER");

		argumentBean.setOrderId("book-37833319");
		argumentBean.setClientId("IBE");
		argumentBean.setAmount("400");
		argumentBean.setCurrencyCode("USD");
		argumentBean.setPaymentMethod("CARD");
		argumentBean.setPayTokenId("cccccc");

		replyBean.setMessage("REGISTER:SUCCESS");
		replyBean.setSuccess(true);
	}

	@Test
	public void registerSuccessOrder() {

		doNothing().when(orderService).createOrder(any(Order.class), any(TransactionType.class));
		when(inquiryService.getOrderByOrderId(argumentBean.getClientId(), argumentBean.getOrderId())).thenReturn(null);
		Assert.assertEquals(transactionController.processTransaction(transactionType, argumentBean).getMessage(),
				replyBean.getMessage());
	}

}
