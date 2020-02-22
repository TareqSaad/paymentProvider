package com.cnet.payment.provider.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cnet.payment.provider.entity.PaymentMethod;
import com.cnet.payment.provider.entity.Status;

@Repository
public class StatusDaoImp implements StatusDao {
	
	
	@PersistenceContext
	private EntityManager em;

	public Status getStatusById(String statusId) {
		
		CriteriaQuery<Status> query = em.getCriteriaBuilder().createQuery(Status.class);
		Root<Status> root = query.from(Status.class);
		query.where(em.getCriteriaBuilder().equal(root.get("statusId"), statusId));
		if(em.createQuery(query).getResultList().size() == 0)
			return null;
		else
		return em.createQuery(query).getSingleResult();
		
	}
	
}
