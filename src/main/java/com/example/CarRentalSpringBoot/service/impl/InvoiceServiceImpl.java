package com.example.CarRentalSpringBoot.service.impl;

import com.example.CarRentalSpringBoot.pojo.converter.InvoiceConverter;
import com.example.CarRentalSpringBoot.pojo.dto.InvoiceDto;
import com.example.CarRentalSpringBoot.repository.InvoiceRepository;
import com.example.CarRentalSpringBoot.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDto> getAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable).stream()
                .map(InvoiceConverter::invoiceConvertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<InvoiceDto> getAllInvoicesByUserId(Long userId, Pageable pageable) {

        return invoiceRepository.findByUserId(userId).stream()
                .map(InvoiceConverter::invoiceConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createInvoice(InvoiceDto invoiceDto) {
        invoiceRepository.save(InvoiceConverter.dtoConvertToInvoice(invoiceDto));
    }


}
