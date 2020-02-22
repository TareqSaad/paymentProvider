package com.cnet.payment.provider.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cnet.payment.provider.entity.Transaction;

@Repository
public class TransactionDaoImp implements TransactionDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(Transaction transaction) {
		em.persist(transaction);

	}

	@Override
	public List<Transaction> listTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

}
