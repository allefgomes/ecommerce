package com.allefgomes.ecommerce.domain.enums;

public enum StatePayment {
	PENDING(1, "Pendente"),
	OK(2, "Quitado"),
	CANCELED(3, "Cancelado");
	
	private int id;
	private String name;
	
	private StatePayment(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static StatePayment toEnum(Integer id) {
		if (id == null) { return null; };
		
		for (StatePayment x: StatePayment.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid Id.");
	}
}
