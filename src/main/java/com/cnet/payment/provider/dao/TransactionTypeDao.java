package com.cnet.payment.provider.dao;

import java.util.List;

import com.cnet.payment.provider.entity.TransactionType;

public interface TransactionTypeDao {
	
	List<TransactionType> listTransactionTypes();
	TransactionType getTransactionType(String transactionType);

}
