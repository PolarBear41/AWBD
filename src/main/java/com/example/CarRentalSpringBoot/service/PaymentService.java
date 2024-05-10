package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.pojo.dto.PaymentDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    void makePayment(PaymentDto paymentDto);
    PaymentDto getPaymentBasedOnId(Long paymentId);
    List<PaymentDto> getAllPayments(Pageable pageable);
}
