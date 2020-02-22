package com.cnet.payment.provider.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * @author TAREQ SAAD
 * 
 * The persistent class for the currency database table.
 * 
 */
@Entity
@NamedQuery(name="Currency.findAll", query="SELECT c FROM Currency c")
public class Currency implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="currency_id")
	private String currencyId;

	@Column(name="currency_code")
	private String currencyCode;

	@Column(name="currency_description")
	private String currencyDescription;

	

	public Currency() {
	}

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyDescription() {
		return this.currencyDescription;
	}

	public void setCurrencyDescription(String currencyDescription) {
		this.currencyDescription = currencyDescription;
	}

	@Override
	public String toString() {
		return  currencyCode ;
	}


}