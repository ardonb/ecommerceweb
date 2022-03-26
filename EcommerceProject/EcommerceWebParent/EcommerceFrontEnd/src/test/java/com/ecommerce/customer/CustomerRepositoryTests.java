package com.ecommerce.customer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.common.entity.AuthenticationType;
import com.ecommerce.common.entity.Country;
import com.ecommerce.common.entity.Customer;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTests {

	@Autowired private CustomerRepository repo;
	@Autowired private TestEntityManager entityManager;

	@Test
	public void testCreateCustomer1() {
		Integer countryId = 234; // USA
		Country country = entityManager.find(Country.class, countryId);

		Customer customer = new Customer();
		customer.setCountry(country);
		customer.setFirstName("Demo10");
		customer.setLastName("Demo10");
		customer.setPassword("demodemo");
		customer.setEmail("demo10@gmail.com");
		customer.setPhoneNumber("312-462-7518");
		customer.setAddressLine1("1927  West Drive");
		customer.setCity("Sacramento");
		customer.setState("California");
		customer.setPostalCode("95867");
		customer.setCreatedTime(new Date());

		Customer savedCustomer = repo.save(customer);

		assertThat(savedCustomer).isNotNull();
		assertThat(savedCustomer.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateCustomer2() {
		Integer countryId = 6; // India
		Country country = entityManager.find(Country.class, countryId);

		Customer customer = new Customer();
		customer.setCountry(country);
		customer.setFirstName("DemoAL");
		customer.setLastName("DemoAL");
		customer.setPassword("demodemo");
		customer.setEmail("demoAL@gmail.com");
		customer.setPhoneNumber("02224928052");
		customer.setAddressLine1("asdf");
		customer.setAddressLine2("asdf");
		customer.setCity("Tirana");
		customer.setState("Albania");
		customer.setPostalCode("400013");
		customer.setCreatedTime(new Date());

		Customer savedCustomer = repo.save(customer);

		assertThat(savedCustomer).isNotNull();
		assertThat(savedCustomer.getId()).isGreaterThan(0);
	}	

	@Test
	public void testListCustomers() {
		Iterable<Customer> customers = repo.findAll();
		customers.forEach(System.out::println);

		assertThat(customers).hasSizeGreaterThan(1);
	}

	@Test
	public void testGetCustomer() {
		Integer customerId = 2;
		Optional<Customer> findById = repo.findById(customerId);

		assertThat(findById).isPresent();

		Customer customer = findById.get();
		System.out.println(customer);
	}

	@Test
	public void testDeleteCustomer() {
		Integer customerId = 2;
		repo.deleteById(customerId);

		Optional<Customer> findById = repo.findById(customerId);		
		assertThat(findById).isNotPresent();		
	}

	@Test
	public void testFindByEmail() {
		String email = "demoAL@gmail.com";
		Customer customer = repo.findByEmail(email);

		assertThat(customer).isNotNull();
		System.out.println(customer);		
	}

	/*
	 * @Test public void testFindByVerificationCode() { String code = "code_123";
	 * Customer customer = repo.findByVerificationCode(code);
	 * 
	 * assertThat(customer).isNotNull(); System.out.println(customer); }
	 */
	@Test
	public void testEnableCustomer() {
		Integer customerId = 1;
		repo.enable(customerId);

		Customer customer = repo.findById(customerId).get();
		assertThat(customer.isEnabled()).isTrue();
	}
	@Test
	public void testUpdateAuthenticationType() {
		Integer id = 1;
		repo.updateAuthenticationType(id, AuthenticationType.DATABASE);

		Customer customer = repo.findById(id).get();

		assertThat(customer.getAuthenticationType()).isEqualTo(AuthenticationType.DATABASE);
	}
}