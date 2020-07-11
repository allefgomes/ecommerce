package com.allefgomes.ecommerce.domain.enums;

public enum ClientType {
	PHYSICALPERSON(1, "Pessoa Física"),
	LEGALPERSON(2, "Pessoa Jurídica");
	
	private int id;
	private String name;
	
	private ClientType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static ClientType toEnum(Integer id) {
		if (id == null) { return null; };
		
		for (ClientType x: ClientType.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid Id.");
	}
}
