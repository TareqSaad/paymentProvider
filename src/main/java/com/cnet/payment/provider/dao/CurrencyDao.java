package com.cnet.payment.provider.dao;

import java.util.List;

import com.cnet.payment.provider.entity.Currency;

public interface CurrencyDao {

	
	List<Currency> listCurrency ();
	Currency getCurrency(String CurrencyCode)  throws Exception;
}
