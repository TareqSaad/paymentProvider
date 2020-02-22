package com.cnet.payment.provider.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cnet.payment.provider.entity.PaymentMethod;

/**
 * @author TAREQSAAD
 *
 */
@Repository
public class PaymentMethodDaoImp implements PaymentMethodDao {

	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.cnet.payment.provider.dao.PaymentMethodDao#getPaymentMethod(java.lang.String)
	 */
	@Override
	public PaymentMethod getPaymentMethod(String paymentMethod) throws Exception {

		CriteriaQuery<PaymentMethod> query = em.getCriteriaBuilder().createQuery(PaymentMethod.class);
		Root<PaymentMethod> root = query.from(PaymentMethod.class);
		query.where(em.getCriteriaBuilder().equal(root.get("paymentMethodDescription"), paymentMethod));
		if (em.createQuery(query).getResultList().size() == 0)
			throw new Exception("ERROR_VALID_PAYMENT_METHOD");
		else
			return em.createQuery(query).getSingleResult();

	}

}
