package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.pojo.dto.FeedbackDto;
import com.example.CarRentalSpringBoot.pojo.dto.InvoiceDto;
import com.example.CarRentalSpringBoot.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@AllArgsConstructor
@Controller
public class InvoiceController {

    private InvoiceService invoiceService;

    @GetMapping(value = {"/invoices"})
    public ModelAndView displayInvoicesPage() {
        ModelAndView mav = new ModelAndView("invoices");
        mav.addObject("invoices", invoiceService.getAllInvoices(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/invoiceSearch")
    public ModelAndView getInvoicesByUserId(@RequestParam Long id) {
        List<InvoiceDto> invoices = invoiceService.getAllInvoicesByUserId(id, PageRequest.of(0, 10));
        ModelAndView mav = new ModelAndView("invoices");
        mav.addObject("invoices", invoiceService.getAllInvoices(PageRequest.of(0, 10)));

        if (invoices != null && !invoices.isEmpty()) {
            mav.addObject("invoiceSearch", invoices);
        }

        return mav;
    }

}
