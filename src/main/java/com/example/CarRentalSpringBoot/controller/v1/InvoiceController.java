package com.example.CarRentalSpringBoot.controller.v1;


import com.example.CarRentalSpringBoot.pojo.dto.InvoiceDto;
import com.example.CarRentalSpringBoot.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("InvoiceControllerV1")
@RequestMapping("/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    @Operation(summary = "Get All Invoices", description = "Retrieve a list of all invoices.")
    public List<InvoiceDto> getAllInvoices(Pageable pageable) {
        return invoiceService.getAllInvoices(pageable);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get Invoices by User ID", description = "Retrieve invoices for a specific user by their ID.")
    public List<InvoiceDto> getAllInvoicesByUserId(@PathVariable Long userId, Pageable pageable) {
        return invoiceService.getAllInvoicesByUserId(userId, pageable);
    }
}
