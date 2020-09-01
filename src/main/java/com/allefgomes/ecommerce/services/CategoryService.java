package com.allefgomes.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.allefgomes.ecommerce.domain.Category;
import com.allefgomes.ecommerce.repositories.CategoryRepository;
import com.allefgomes.ecommerce.services.exceptions.DataIntegrityException;
import com.allefgomes.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Page<Category> findAll(Integer page, Integer linesPerPage, String orderBy,  String direction) {
		 PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		 
		 return repo.findAll(pageRequest); 
	}
	
	public Category find(Integer id) {
		Optional<Category> category = repo.findById(id);
		
		return category.orElseThrow(() -> new ObjectNotFoundException("Object not Found! Id: " + id));
	}
	
	public Category insert(Category category) {
		category.setId(null);
		
		return repo.save(category); 
	}
	
	public Category update(Category category) {
		find(category.getId());
		
		return repo.save(category);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Not is possible exclude this category because has products");
		}
	} 
}
