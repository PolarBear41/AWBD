package com.example.CarRentalSpringBoot.repository;

import com.example.CarRentalSpringBoot.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.invoice.invoiceId = :invoiceId")
    Payment findByInvoiceId(Long invoiceId);
    Payment findByPaymentId(Long paymentId);
}