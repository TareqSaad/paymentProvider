package com.cnet.payment.provider.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the transaction_type database table.
 * 
 */
@Entity
@Table(name="payment_provider.transaction_type")
@NamedQuery(name="TransactionType.findAll", query="SELECT t FROM TransactionType t")
public class TransactionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="transaction_type_id")
	private String transactionTypeId;

	@Column(name="transaction_type_description")
	private String transactionTypeDescription;
	
	@Column(name="initator_type")
	private boolean initatorType;
	
	@Column(name="report")
	private boolean report;
	
	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="transactionType")
	private List<Transaction> transactions;

	//bi-directional many-to-one association to OrderStatus
	@ManyToOne
	@JoinColumn(name="transaction_type_status")
	private OrderStatus transactionTypeStatus;

	//bi-directional many-to-one association to OrderStatus
	@ManyToOne
	@JoinColumn(name="previous_status")
	private OrderStatus previousStatus;

	public TransactionType() {
	}

	public String getTransactionTypeId() {
		return this.transactionTypeId;
	}

	public void setTransactionTypeId(String transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public String getTransactionTypeDescription() {
		return this.transactionTypeDescription;
	}

	public void setTransactionTypeDescription(String transactionTypeDescription) {
		this.transactionTypeDescription = transactionTypeDescription;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setTransactionType(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setTransactionType(null);

		return transaction;
	}

	public OrderStatus getTransactionTypeStatus() {
		return this.transactionTypeStatus;
	}

	public void setTransactionTypeStatus(OrderStatus transactionTypeStatus) {
		this.transactionTypeStatus = transactionTypeStatus;
	}

	public OrderStatus getPreviousStatus() {
		return this.previousStatus;
	}

	public void setPreviousStatus(OrderStatus previousStatus) {
		this.previousStatus = previousStatus;
	}

	public boolean isInitatorType() {
		return initatorType;
	}

	public void setInitatorType(boolean initatorType) {
		this.initatorType = initatorType;
	}

	public boolean isReport() {
		return report;
	}

	public void setReport(boolean report) {
		this.report = report;
	}


}