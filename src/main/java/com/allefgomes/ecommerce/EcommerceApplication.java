package com.allefgomes.ecommerce;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.allefgomes.ecommerce.domain.Address;
import com.allefgomes.ecommerce.domain.Category;
import com.allefgomes.ecommerce.domain.City;
import com.allefgomes.ecommerce.domain.Client;
import com.allefgomes.ecommerce.domain.Payment;
import com.allefgomes.ecommerce.domain.PaymentByCard;
import com.allefgomes.ecommerce.domain.PaymentBySlip;
import com.allefgomes.ecommerce.domain.Product;
import com.allefgomes.ecommerce.domain.Request;
import com.allefgomes.ecommerce.domain.RequestItem;
import com.allefgomes.ecommerce.domain.State;
import com.allefgomes.ecommerce.domain.enums.ClientType;
import com.allefgomes.ecommerce.domain.enums.StatePayment;
import com.allefgomes.ecommerce.repositories.AddressRepository;
import com.allefgomes.ecommerce.repositories.CategoryRepository;
import com.allefgomes.ecommerce.repositories.CityRepository;
import com.allefgomes.ecommerce.repositories.ClientRepository;
import com.allefgomes.ecommerce.repositories.PaymentRepository;
import com.allefgomes.ecommerce.repositories.ProductRepository;
import com.allefgomes.ecommerce.repositories.RequestItemRepository;
import com.allefgomes.ecommerce.repositories.RequestRepository;
import com.allefgomes.ecommerce.repositories.StateRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private RequestItemRepository requestItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category c1 = new Category(null, "Tech");
		Category c2 = new Category(null, "Cookie");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 2000.00);
		Product p3 = new Product(null, "CookieTop", 1000.00);
		Product p4 = new Product(null, "Broom", .00);
		
		c1.getProducts().addAll(Arrays.asList(p1, p2));
		c2.getProducts().addAll(Arrays.asList(p4, p3));
		
		p1.getCategories().addAll(Arrays.asList(c1));
		p2.getCategories().addAll(Arrays.asList(c1));
		p3.getCategories().addAll(Arrays.asList(c2));
		p4.getCategories().addAll(Arrays.asList(c2));
		
		categoryRepository.saveAll(Arrays.asList(c1, c2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
		State s1 = new State(null, "Ceará");
		City city1 = new City(null, "Fortaleza", s1);
		City city2 = new City(null, "Caucaia", s1);
		
		s1.getCities().add(city1);
		
		stateRepository.save(s1);
		cityRepository.saveAll(Arrays.asList(city1, city2));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PHYSICALPERSON);

		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, city1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, city2);

		cli1.getAdresses().addAll(Arrays.asList(e1, e2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Request req1 = new Request(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Request req2 = new Request(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pay1 = new PaymentByCard(null, StatePayment.OK, req1, 6);
		req1.setPayment(pay1);

		Payment pay2 = new PaymentBySlip(null, StatePayment.PENDING, req2, sdf.parse("20/10/2017 00:00"), null);
		req2.setPayment(pay2);

		cli1.getRequests().addAll(Arrays.asList(req1, req2));

		requestRepository.saveAll(Arrays.asList(req1, req2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		RequestItem reqi1 = new RequestItem(req1, p1, 90.00, 0.0, 2);	
		RequestItem reqi2 = new RequestItem(req1, p3, 80.00, 0.0, 2);	
		RequestItem reqi3 = new RequestItem(req2, p2, 100.00, 10.00, 6);	

		req1.getItems().addAll(Arrays.asList(reqi1, reqi2));	
		req2.getItems().addAll(Arrays.asList(reqi3));	

		p1.getItems().addAll(Arrays.asList(reqi1));	
		p2.getItems().addAll(Arrays.asList(reqi3));	
		p3.getItems().addAll(Arrays.asList(reqi2));	

		requestItemRepository.saveAll(Arrays.asList(reqi1, reqi2, reqi3));
	}
}
