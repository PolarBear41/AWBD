package com.example.CarRentalSpringBoot.repository;

import com.example.CarRentalSpringBoot.entity.Car;
import com.example.CarRentalSpringBoot.entity.Invoice;
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
class InvoiceRepositoryIntegrationTest {

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;

    @Test
    @Order(1)
    public void shouldSaveInvoice() {
        Car car = carRepository.save(createCar());
        User user = userRepository.save(createUser());
        Invoice invoice = createInvoice(car, user);

        invoice = invoiceRepository.save(invoice);

        assertNotNull(invoice);
    }

    @Test
    @Order(2)
    public void shouldReturnInvoices() {
        List invoices = invoiceRepository.findAll();

        assertNotNull(invoices);
        assertEquals(1, invoices.size());
    }

    @Test
    @Order(3)
    public void shouldReturnInvoiceById() {
        Invoice invoice = invoiceRepository.findById(1L).orElse(null);

        assertNotNull(invoice);
        assertEquals(1, invoice.getInvoiceId());
    }

    @Test
    @Order(4)
    public void shouldReturnInvoicesByUserId() {
        List invoices = invoiceRepository.findByUserId(1L);

        assertNotNull(invoices);
        assertEquals(1, invoices.size());
    }

    @Test
    @Order(5)
    public void shouldThrowDataIntegrityViolationExceptionWhenRequiredFieldsNotProvided() {
        Invoice invoice = createInvoice(null, null);

        assertThrows(DataIntegrityViolationException.class, () -> invoiceRepository.save(invoice));
    }

    private Invoice createInvoice(Car car, User user) {
        return Invoice.builder()
                .user(user)
                .car(car)
                .rentalStartDate(new Date())
                .rentalEndDate(new Date())
                .totalAmount(BigDecimal.valueOf(350L))
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

    private Car createCar() {
        return Car.builder()
                .make("Ford")
                .licensePlate("123-456")
                .model("Escape")
                .rentalRate(BigDecimal.valueOf(125))
                .year(2024)
                .build();
    }

}