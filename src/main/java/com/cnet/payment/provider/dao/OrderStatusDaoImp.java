package com.cnet.payment.provider.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cnet.payment.provider.entity.OrderStatus;

@Repository
public class OrderStatusDaoImp implements OrderStatusDao {
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List<OrderStatus> listOrderStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OrderStatus getOrderStatusById(String orderStatusId) {
		
		CriteriaQuery<OrderStatus> query = em.getCriteriaBuilder().createQuery(OrderStatus.class);
		Root<OrderStatus> root = query.from(OrderStatus.class);
		query.where(em.getCriteriaBuilder().equal(root.get("orderStatusId"), orderStatusId));
		return em.createQuery(query).getSingleResult();
		
	}
	
	@Override
	public List<OrderStatus> getPendingOrderStatus() {
		
		CriteriaQuery<OrderStatus> query = em.getCriteriaBuilder().createQuery(OrderStatus.class);
		Root<OrderStatus> root = query.from(OrderStatus.class);
		query.where(em.getCriteriaBuilder().equal(root.get("pending"), true));
		return em.createQuery(query).getResultList();
		
	}

}
