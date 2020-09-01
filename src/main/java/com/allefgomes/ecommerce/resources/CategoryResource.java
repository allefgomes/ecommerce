package com.allefgomes.ecommerce.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.allefgomes.ecommerce.domain.Category;
import com.allefgomes.ecommerce.dto.CategoryDTO;
import com.allefgomes.ecommerce.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> index(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,  
			@RequestParam(value="direction", defaultValue="ASC") String direction
		) {
		Page<Category> categories = categoryService.findAll(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> categoriesDTO = categories.map(category -> new CategoryDTO(category));
		
		return ResponseEntity.ok().body(categoriesDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> show(@PathVariable Integer id) {
		Category category = categoryService.find(id);
		
		return ResponseEntity.ok().body(category);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Category> create(@RequestBody Category category) {
		category = categoryService.insert(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(category.getId()).toUri();
			
		return ResponseEntity.created(uri). body(category);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Integer id) {
		category.setId(id);
		category = categoryService.update(category);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
