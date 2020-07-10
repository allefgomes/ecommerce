package com.allefgomes.ecommerce;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.allefgomes.ecommerce.domain.Category;
import com.allefgomes.ecommerce.repositories.CategoryRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category techCategory = new Category(null, "Tech");
		Category cookieCategory = new Category(null, "Cookie");
		
		categoryRepository.saveAll(Arrays.asList(techCategory, cookieCategory));
	}
}
