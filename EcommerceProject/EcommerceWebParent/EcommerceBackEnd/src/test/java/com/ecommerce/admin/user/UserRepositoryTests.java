package com.ecommerce.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.common.entity.Role;
import com.ecommerce.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User user = new User("Filan@Fisteku.com", "filan", "Filan", "Fisteku");
		user.addRole(roleAdmin);

		User savedUser = repo.save(user);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User user = new User("diox@diox.com", "diox", "diox", "diox");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);

		user.addRole(roleEditor);
		user.addRole(roleAssistant);

		User savedUser = repo.save(user);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	@Test
	public void testGetUserById() {
		User user = repo.findById(1).get();
		System.out.println(user);
		assertThat(user).isNotNull();
	}
	@Test
	public void testUpdateUserDetails() {
		User user = repo.findById(1).get();
		user.setEnabled(true);
		user.setEmail("filani@gmail.com");

		repo.save(user);
	}
	@Test
	public void testUpdateUserRoles() {
		User userDiox = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);

		userDiox.getRoles().remove(roleEditor);
		userDiox.addRole(roleSalesperson);

		repo.save(userDiox);
	}
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);

	}
	
}

	