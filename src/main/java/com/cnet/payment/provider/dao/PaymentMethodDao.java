package com.cnet.payment.provider.dao;

import com.cnet.payment.provider.entity.PaymentMethod;

/**
 * @author TAREQSAAD
 *
 */
public interface PaymentMethodDao {

	/**
	 * @param paymentMethod
	 * @return
	 * @throws Exception
	 */
	PaymentMethod getPaymentMethod(String paymentMethod) throws Exception;

}
