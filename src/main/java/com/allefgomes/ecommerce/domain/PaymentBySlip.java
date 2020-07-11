package com.allefgomes.ecommerce.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.allefgomes.ecommerce.domain.enums.StatePayment;

@Entity
public class PaymentBySlip extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Date dueDate;
	private Date paidDate;
	
	public PaymentBySlip() {}

	public PaymentBySlip(Integer id, StatePayment statePayment, Request request, Date dueDate, Date paidDate) {
		super(id, statePayment, request);
		this.dueDate = dueDate;
		this.paidDate = paidDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
}
