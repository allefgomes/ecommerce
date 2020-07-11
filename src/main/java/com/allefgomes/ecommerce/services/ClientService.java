package com.allefgomes.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allefgomes.ecommerce.domain.Client;
import com.allefgomes.ecommerce.repositories.ClientRepository;
import com.allefgomes.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	public Client findOne(Integer id) {
		Optional<Client> client = repo.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException("Object not Found! Id: " + id));
	}
}
