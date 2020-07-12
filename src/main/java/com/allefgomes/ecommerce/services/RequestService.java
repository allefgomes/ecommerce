package com.allefgomes.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allefgomes.ecommerce.domain.Request;
import com.allefgomes.ecommerce.repositories.RequestRepository;
import com.allefgomes.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository repo;
	
	public Request findOne(Integer id) {
		Optional<Request> request = repo.findById(id);
		
		return request.orElseThrow(() -> new ObjectNotFoundException("Object not Found! Id: " + id));
	}
}
