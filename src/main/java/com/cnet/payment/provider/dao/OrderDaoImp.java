package com.cnet.payment.provider.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cnet.payment.provider.entity.Order;
import com.cnet.payment.provider.entity.OrderPK;
import com.cnet.payment.provider.entity.OrderStatus;
import com.cnet.payment.provider.entity.TransactionType;

@Repository
public class OrderDaoImp implements OrderDao {

	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public void add(Order order) {
		em.persist(order);
		
	}
	
	@Override
	public void update(Order order) {
		em.merge(order);
	}
	
	
	@Override
	public List<Order> listOrders() {
		
		CriteriaQuery<Order> query = em.getCriteriaBuilder().createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
		return em.createQuery(query).getResultList();
	}
	
	@Override
	public Order getOrderByOrderId(String clientId, String orderId) {

		CriteriaQuery<Order> query = em.getCriteriaBuilder().createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
		OrderPK orderPK = new OrderPK();
		orderPK.setOrderId(orderId);
		orderPK.setClientId(clientId);
		query.where(em.getCriteriaBuilder().equal(root.get("id"), orderPK));
		if(em.createQuery(query).getResultList().size() == 0)
			return null;
		else
		return em.createQuery(query).getSingleResult();

	}
	
	@Override
	public List<Order> listOrdersByStatus(List<OrderStatus> orderStatusList){
		
		CriteriaQuery<Order> query = em.getCriteriaBuilder().createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
		query.where(root.get("orderStatus").in(orderStatusList) );
		return em.createQuery(query).getResultList();
	}
	
	@Override
	public BigDecimal getTotalAmount(List<OrderStatus> orderStatusList , String clientId){
		
		CriteriaQuery<BigDecimal> query = em.getCriteriaBuilder().createQuery(BigDecimal.class);
		Root<Order> root = query.from(Order.class);
		query.where(root.get("orderStatus").in(orderStatusList) );
		if(clientId!=null) {
			query.where(em.getCriteriaBuilder().and(em.getCriteriaBuilder().equal(root.get("id").get("clientId"), clientId), 
					root.get("orderStatus").in(orderStatusList)) );
		}
			
		query.select(em.getCriteriaBuilder().sum(root.get("amount").as(BigDecimal.class)));
		 BigDecimal sum = em.createQuery(query).getSingleResult();
		return sum;
	}
	
}
