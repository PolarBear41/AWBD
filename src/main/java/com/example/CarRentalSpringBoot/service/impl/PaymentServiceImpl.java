package com.example.CarRentalSpringBoot.service.impl;

import com.example.CarRentalSpringBoot.entity.Invoice;
import com.example.CarRentalSpringBoot.entity.Payment;
import com.example.CarRentalSpringBoot.pojo.converter.PaymentConverter;
import com.example.CarRentalSpringBoot.pojo.dto.PaymentDto;
import com.example.CarRentalSpringBoot.repository.InvoiceRepository;
import com.example.CarRentalSpringBoot.repository.PaymentRepository;
import com.example.CarRentalSpringBoot.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public void makePayment(PaymentDto paymentDto) {
        Invoice invoice = invoiceRepository.findByInvoiceId(paymentDto.getInvoiceId());
        if(invoice == null) {
            throw new RuntimeException("Invoice not found");
        }

        paymentRepository.save(PaymentConverter.dtoConvertToPayment(paymentDto, invoice));
    }

    @Override
    public PaymentDto getPaymentBasedOnId(Long paymentId) {
        Payment payment = paymentRepository.findByPaymentId(paymentId);
        if(payment == null) {
            throw new RuntimeException("No payment found based on paymentId");
        }
        return PaymentConverter.paymentConvertToDto(payment);
    }


    @Override
    public List<PaymentDto> getAllPayments(Pageable pageable) {
        var payments = paymentRepository.findAll(pageable);
        if(payments.isEmpty()) {
            throw new RuntimeException("No payments found");
        }
        return payments.stream()
                .map(PaymentConverter::paymentConvertToDto)
                .toList();
    }
}
