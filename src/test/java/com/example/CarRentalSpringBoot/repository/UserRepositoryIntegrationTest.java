package com.example.CarRentalSpringBoot.repository;

import com.example.CarRentalSpringBoot.entity.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@EnabledIf(value = "#{'${spring.profiles.active}' == 'integration'}")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class UserRepositoryIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    public void shouldSaveUser() {
        User user = createUser();

        user = userRepository.save(user);

        assertNotNull(user);
    }

    @Test
    @Order(2)
    public void shouldReturnUsers() {
        List users = userRepository.findAll();

        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    @Order(3)
    public void shouldReturnUserById() {
        User user = userRepository.findById(1L).orElse(null);

        assertNotNull(user);
        assertEquals(1, user.getUserId());
    }

    @Test
    @Order(4)
    public void shouldThrowDataIntegrityViolationExceptionWhenRequiredFieldsNotProvided() {
        User user = createUser();
        user.setEmail(null);

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    private User createUser() {
        return User.builder()
                .firstName("Anthony")
                .lastName("Kampa")
                .username("anthony.kampa")
                .password("123")
                .email("anthony.kampa@email.com")
                .build();
    }

}