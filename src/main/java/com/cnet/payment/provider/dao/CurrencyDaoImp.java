package com.cnet.payment.provider.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cnet.payment.provider.entity.Currency;

@Repository
public class CurrencyDaoImp implements CurrencyDao {
	
	private static final Logger logger = Logger.getLogger(CurrencyDaoImp.class);

	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Currency> listCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Currency getCurrency(String currencyCode) throws Exception {
		CriteriaQuery<Currency> query = em.getCriteriaBuilder().createQuery(Currency.class);
		Root<Currency> root = query.from(Currency.class);
		query.where(em.getCriteriaBuilder().equal(root.get("currencyCode"), currencyCode));
		if(em.createQuery(query).getResultList().size() == 0)
			throw new Exception("ERROR_INVALID_CURRENCY");
		else
		return em.createQuery(query).getSingleResult();
	}

}
