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
import com.allefgomes.ecommerce.domain.State;
import com.allefgomes.ecommerce.domain.enums.ClientType;
import com.allefgomes.ecommerce.domain.enums.StatePayment;
import com.allefgomes.ecommerce.repositories.AddressRepository;
import com.allefgomes.ecommerce.repositories.CategoryRepository;
import com.allefgomes.ecommerce.repositories.CityRepository;
import com.allefgomes.ecommerce.repositories.ClientRepository;
import com.allefgomes.ecommerce.repositories.PaymentRepository;
import com.allefgomes.ecommerce.repositories.ProductRepository;
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
		
		State state = new State(null, "Ceará");
		City fortaleza = new City(null, "Fortaleza", state);
		City caucaia = new City(null, "Caucaia", state);
		
		state.getCities().add(fortaleza);
		
		stateRepository.save(state);
		cityRepository.saveAll(Arrays.asList(fortaleza, caucaia));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PHYSICALPERSON);

		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, fortaleza);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, caucaia);

		cli1.getAdresses().addAll(Arrays.asList(e1, e2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Request ped1 = new Request(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Request ped2 = new Request(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pagto1 = new PaymentByCard(null, StatePayment.OK, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentBySlip(null, StatePayment.PENDING, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getRequests().addAll(Arrays.asList(ped1, ped2));

		requestRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}
}
