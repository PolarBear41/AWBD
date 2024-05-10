package com.example.CarRentalSpringBoot.repository;

import com.example.CarRentalSpringBoot.entity.Car;
import com.example.CarRentalSpringBoot.entity.Feedback;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@EnabledIf(value = "#{'${spring.profiles.active}' == 'integration'}")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class FeedbackRepositoryIntegrationTest {

    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    public void shouldSaveFeedback() {
        Car car = carRepository.save(createCar());
        User user = userRepository.save(createUser());
        Feedback feedback = createFeedback(car, user);

        feedback = feedbackRepository.save(feedback);

        assertNotNull(feedback);
    }

    @Test
    @Order(2)
    public void shouldReturnFeedback() {
        List feedbackList = feedbackRepository.findAll();

        assertNotNull(feedbackList);
        assertEquals(1, feedbackList.size());
    }

    @Test
    @Order(3)
    public void shouldReturnFeedbackById() {
        Feedback feedback = feedbackRepository.findById(1L).orElse(null);

        assertNotNull(feedback);
        assertEquals(1, feedback.getFeedbackId());
    }

    @Test
    @Order(4)
    public void shouldThrowDataIntegrityViolationExceptionWhenRequiredFieldsNotProvided() {
        Feedback feedback = createFeedback(null, null);

        assertThrows(DataIntegrityViolationException.class, () -> feedbackRepository.save(feedback));
    }

    private Feedback createFeedback(Car car, User user) {
        return Feedback.builder()
                .car(car)
                .user(user)
                .feedbackText("Good feedback")
                .feedbackDate(new Date())
                .build();
    }

    private Car createCar() {
        return Car.builder()
                .make("Ford")
                .licensePlate("123-456")
                .model("Escape")
                .rentalRate(BigDecimal.valueOf(125))
                .year(2024)
                .build();
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