package com.allefgomes.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allefgomes.ecommerce.domain.Category;
import com.allefgomes.ecommerce.repositories.CategoryRepository;
import com.allefgomes.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> category = repo.findById(id);
		
		return category.orElseThrow(() -> new ObjectNotFoundException("Object not Found! Id: " + id));
	}
}
