package com.allefgomes.ecommerce.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date instantAt;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
	private Payment payment;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address addressToDeliver;
	
	@OneToMany(mappedBy = "id.request")
	private Set<RequestItem> items = new HashSet<>();
	
	public Request() {}

	public Request(Integer id, Date instantAt, Client client, Address addressToDeliver) {
		super();
		this.id = id;
		this.instantAt = instantAt;
		this.client = client;
		this.addressToDeliver = addressToDeliver;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstantAt() {
		return instantAt;
	}

	public void setInstantAt(Date instantAt) {
		this.instantAt = instantAt;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAddressToDeliver() {
		return addressToDeliver;
	}

	public void setAddressToDeliver(Address addressToDeliver) {
		this.addressToDeliver = addressToDeliver;
	}
	
	public Set<RequestItem> getItems() {
		return items;
	}

	public void setItems(Set<RequestItem> items) {
		this.items = items;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
