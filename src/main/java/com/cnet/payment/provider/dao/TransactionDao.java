package com.cnet.payment.provider.dao;

import java.util.List;

import com.cnet.payment.provider.entity.Transaction;

public interface TransactionDao {

	
	void add(Transaction transaction);
	List<Transaction> listTransactions();
	
}
