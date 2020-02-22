package com.cnet.payment.provider.entity;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.cnet.payment.provider.converter.CryptoConverter;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name="payment_provider.order")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderPK id;

	private double amount;

	@Column(name="created_on")
	@CreationTimestamp
	private Timestamp createdOn;

	@ManyToOne
	@JoinColumn(name="currency_id")
	private Currency currency;
;

	@Column(name="modified_on")
	@UpdateTimestamp
	private Timestamp modifiedOn;

	@ManyToOne
	@JoinColumn(name="order_status_id")
	private OrderStatus orderStatus;
	
	@Convert(converter=CryptoConverter.class)
	@Column(name="pay_token_id")
	private String payTokenId;

	@ManyToOne
	@JoinColumn(name="payment_method_id")
	private PaymentMethod paymentMethod;


	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="order")
	private List<Transaction> transactions;

	public Order() {
	}
	
	public Order(OrderPK id, Currency currency,
			OrderStatus orderStatus, PaymentMethod paymentMethod,String payTokenId , double amount ) {		
		this.id = id;
		
		this.currency = currency;
		this.orderStatus = orderStatus;
		this.paymentMethod = paymentMethod;
		this.payTokenId = payTokenId;
		this.amount = amount;
	}

	public OrderPK getId() {
		return this.id;
	}

	public void setId(OrderPK id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	
	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	

	public String getPayTokenId() {
		return this.payTokenId;
	}

	public void setPayTokenId(String payTokenId) {
		this.payTokenId = payTokenId;
	}

	

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * @return the orderStatus
	 */
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return the paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setOrder(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setOrder(null);

		return transaction;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + id.getOrderId() + ", clientId=" + id.getClientId() + ", currency=" + currency + ", orderStatus="
				+ orderStatus + ", paymentMethod=" + paymentMethod + ", payTokenId=" + payTokenId + ", amount=" + amount +"]";
	}

}