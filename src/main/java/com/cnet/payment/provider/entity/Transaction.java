package com.cnet.payment.provider.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name="transaction_id" , updatable=false)
	private String transactionId;

	@Column(name="created_on")
	@CreationTimestamp
	private Timestamp createdOn;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumns(value = {
	@JoinColumn(name="order_id" , referencedColumnName="order_id"),
	@JoinColumn(name="client_id" , referencedColumnName="client_id")})
	private Order order;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
	

	//bi-directional many-to-one association to TransactionType
	@ManyToOne
	@JoinColumn(name="transaction_type_id")
	private TransactionType transactionType;

	public Transaction() {
	}
	
	

	public Transaction(Order order, Status status, TransactionType transactionType) {
		super();
		this.order = order;
		this.status = status;
		this.transactionType = transactionType;
	}



	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

}