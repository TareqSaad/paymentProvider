package com.cnet.payment.provider.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.TransactionType;

@Repository
public class TransactionTypeDaoImp implements TransactionTypeDao {
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<TransactionType> listTransactionTypes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TransactionType getTransactionType(String transactionType) {
		CriteriaQuery<TransactionType> query = em.getCriteriaBuilder().createQuery(TransactionType.class);
		Root<TransactionType> root = query.from(TransactionType.class);
		query.where(em.getCriteriaBuilder().equal(root.get("transactionTypeDescription"), transactionType));
		if(em.createQuery(query).getResultList().size() == 0)
			return null;
		else
		return em.createQuery(query).getSingleResult();
	}

}
