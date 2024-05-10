package com.example.CarRentalSpringBoot.repository;

import com.example.CarRentalSpringBoot.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i WHERE i.user.userId = :userId")
    List<Invoice> findByUserId(@Param("userId") Long userId);
    Invoice findByInvoiceId(Long invoiceId);
}