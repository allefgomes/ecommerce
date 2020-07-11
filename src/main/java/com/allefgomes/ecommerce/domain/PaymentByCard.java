package com.allefgomes.ecommerce.domain;

import javax.persistence.Entity;

import com.allefgomes.ecommerce.domain.enums.StatePayment;

@Entity
public class PaymentByCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;
	
	public PaymentByCard() {}

	public PaymentByCard(Integer id, StatePayment statePayment, Request request, Integer numberOfInstallments) {
		super(id, statePayment, request);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
	
}
