package com.allefgomes.ecommerce;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.allefgomes.ecommerce.domain.Category;
import com.allefgomes.ecommerce.domain.Product;
import com.allefgomes.ecommerce.repositories.CategoryRepository;
import com.allefgomes.ecommerce.repositories.ProductRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category techCategory = new Category(null, "Tech");
		Category cookieCategory = new Category(null, "Cookie");
		
		Product computerProduct = new Product(null, "Computer", 2000.00);
		Product printerProduct = new Product(null, "Printer", 2000.00);
		Product cookieTopProduct = new Product(null, "CookieTop", 1000.00);
		Product broomProduct = new Product(null, "Broom", .00);
		
		techCategory.getProducts().addAll(Arrays.asList(computerProduct, printerProduct));
		cookieCategory.getProducts().addAll(Arrays.asList(broomProduct, cookieTopProduct));
		
		computerProduct.getCategories().addAll(Arrays.asList(techCategory));
		printerProduct.getCategories().addAll(Arrays.asList(techCategory));
		cookieTopProduct.getCategories().addAll(Arrays.asList(cookieCategory));
		broomProduct.getCategories().addAll(Arrays.asList(cookieCategory));
		
		categoryRepository.saveAll(Arrays.asList(techCategory, cookieCategory));
		productRepository.saveAll(Arrays.asList(computerProduct, printerProduct, cookieTopProduct, broomProduct));
		
	}
}
