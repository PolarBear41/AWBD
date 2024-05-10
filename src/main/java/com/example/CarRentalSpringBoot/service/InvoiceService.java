package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.pojo.dto.InvoiceDto;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface InvoiceService {

   List<InvoiceDto> getAllInvoices(Pageable pageable);

   List<InvoiceDto> getAllInvoicesByUserId(Long userId, Pageable pageable);

   void createInvoice(InvoiceDto invoiceDto);

}
