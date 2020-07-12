package com.allefgomes.ecommerce.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allefgomes.ecommerce.domain.Request;
import com.allefgomes.ecommerce.services.RequestService;

@RestController
@RequestMapping(value="/requests")
public class RequestResource {

	@Autowired
	private RequestService requestService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Request request = requestService.findOne(id);
		
		return ResponseEntity.ok().body(request);
	}
}
