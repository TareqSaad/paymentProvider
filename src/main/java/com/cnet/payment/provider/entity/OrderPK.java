package com.cnet.payment.provider.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the order database table.
 * 
 */
@Embeddable
public class OrderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="order_id")
	private String orderId;

	@Column(name="client_id")
	private String clientId;

	public OrderPK() {
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getClientId() {
		return this.clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderPK)) {
			return false;
		}
		OrderPK castOther = (OrderPK)other;
		return 
			this.orderId.equals(castOther.orderId)
			&& this.clientId.equals(castOther.clientId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId.hashCode();
		hash = hash * prime + this.clientId.hashCode();
		
		return hash;
	}
}