package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.entity.Car;
import com.example.CarRentalSpringBoot.entity.Reservation;
import com.example.CarRentalSpringBoot.entity.User;
import com.example.CarRentalSpringBoot.pojo.dto.ReservationDto;
import com.example.CarRentalSpringBoot.repository.CarRepository;
import com.example.CarRentalSpringBoot.repository.ReservationRepository;
import com.example.CarRentalSpringBoot.repository.UserRepository;
import com.example.CarRentalSpringBoot.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class ReservationServiceTest {

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pageable = PageRequest.of(0, 5);
    }

    @Test
    void testGetAllReservations() {
        List<Reservation> reservations = createReservationList();
        Mockito.when(reservationRepository.findAll(pageable)).thenReturn(new PageImpl(reservations));

        List<ReservationDto> result = reservationService.getAllReservations(pageable);

        assertNotNull(result);
        assertEquals(reservations.size(), result.size());
    }

    @Test
    void testGetAllReservationsEmptyList() {
        List<Reservation> reservations = new ArrayList<>();
        Mockito.when(reservationRepository.findAll(pageable)).thenReturn(new PageImpl(reservations));

        assertThrows(RuntimeException.class, () -> reservationService.getAllReservations(pageable));
    }

    @Test
    void testAddReservation() {
        ReservationDto reservationDto = createReservationDto();
        User user = createUser();
        Car car = createCar();
        Mockito.when(userRepository.findByUserId(anyLong())).thenReturn(user);
        Mockito.when(carRepository.findByCarId(anyLong())).thenReturn(car);
        Mockito.when(reservationRepository.save(any(Reservation.class))).thenReturn(createReservation());

        assertDoesNotThrow(() -> reservationService.addReservation(reservationDto));
    }

    @Test
    void testAddReservationUserNotFound() {
        ReservationDto reservationDto = createReservationDto();
        Mockito.when(userRepository.findByUserId(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> reservationService.addReservation(reservationDto));
    }

    @Test
    void testAddReservationCarNotFound() {
        ReservationDto reservationDto = createReservationDto();
        User user = createUser();
        Mockito.when(userRepository.findByUserId(anyLong())).thenReturn(user);
        Mockito.when(carRepository.findByCarId(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> reservationService.addReservation(reservationDto));
    }

    @Test
    void testGetAllReservationsByCarId() {
        List<Reservation> reservations = createReservationList();
        Mockito.when(reservationRepository.findByCarId(anyLong())).thenReturn(reservations);

        List<ReservationDto> result = reservationService.getAllReservationsByCarId(1L);

        assertNotNull(result);
        assertEquals(reservations.size(), result.size());
    }

    @Test
    void testGetAllReservationsByCarIdNotFound() {
        List<Reservation> reservations = new ArrayList<>();
        Mockito.when(reservationRepository.findByCarId(anyLong())).thenReturn(reservations);

        assertThrows(RuntimeException.class, () -> reservationService.getAllReservationsByCarId(1L));
    }

    @Test
    void testGetAllReservationsByUserId() {
        List<Reservation> reservations = createReservationList();
        Mockito.when(reservationRepository.findByUserId(anyLong())).thenReturn(reservations);

        List<ReservationDto> result = reservationService.getAllReservationsByUserId(1L);

        assertNotNull(result);
        assertEquals(reservations.size(), result.size());
    }

    @Test
    void testGetAllReservationsByUserIdNotFound() {
        List<Reservation> reservations = new ArrayList<>();
        Mockito.when(reservationRepository.findByUserId(anyLong())).thenReturn(reservations);

        assertThrows(RuntimeException.class, () -> reservationService.getAllReservationsByUserId(1L));
    }

    private ReservationDto createReservationDto() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setUserId(1L);
        reservationDto.setCarId(1L);
        return reservationDto;
    }

    private User createUser() {
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        return user;
    }

    private Car createCar() {
        Car car = new Car();
        car.setCarId(1L);
        car.setMake("Honda");
        car.setModel("Civic");
        car.setYear(2021);
        return car;
    }

    private Reservation createReservation() {
        Reservation reservation = new Reservation();
        reservation.setReservationId(1L);
        reservation.setReservationDate(Date.valueOf("2021-01-01"));
        reservation.setUser(createUser());
        reservation.setCar(createCar());
        return reservation;
    }

    private List<Reservation> createReservationList() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(createReservation());
        reservations.add(createReservation());
        return reservations;
    }
}
