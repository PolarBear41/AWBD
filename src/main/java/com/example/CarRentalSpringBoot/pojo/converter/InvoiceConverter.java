package com.example.CarRentalSpringBoot.pojo.converter;

import com.example.CarRentalSpringBoot.entity.Invoice;
import com.example.CarRentalSpringBoot.pojo.dto.InvoiceDto;

public class InvoiceConverter {
    public static InvoiceDto invoiceConvertToDto(Invoice invoice) {
        return InvoiceDto.builder()
                .rentalStartDate(invoice.getRentalStartDate())
                .rentalEndDate(invoice.getRentalEndDate())
                .totalAmount(invoice.getTotalAmount())
                .paymentStatus(invoice.getPaymentStatus())
                .build();
    }
    public static Invoice dtoConvertToInvoice(InvoiceDto invoiceDto) {
        return Invoice.builder()
                .rentalStartDate(invoiceDto.getRentalStartDate())
                .rentalEndDate(invoiceDto.getRentalEndDate())
                .totalAmount(invoiceDto.getTotalAmount())
                .paymentStatus(invoiceDto.getPaymentStatus())
                .build();
    }
}
