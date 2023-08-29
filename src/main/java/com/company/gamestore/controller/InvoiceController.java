package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.service.InvoiceService;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping("/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoice(){
        return invoiceRepository.findAll();
    }

    @GetMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int id){
        return invoiceRepository.findById(id).orElse(null);
    }

    @GetMapping("/invoices/customers/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoiceByCustomerName(@PathVariable String name){
        return invoiceRepository.findByName(name);
    }



    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody InvoiceViewModel invoiceViewModel){
        return invoiceService.saveInvoice(invoiceViewModel);
    }

    @PutMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody Invoice invoice, @PathVariable int id){
        Optional<Invoice> invoice1 = invoiceRepository.findById(id);
        if(invoice1.isPresent())
            invoiceRepository.save(invoice);
    }

    @DeleteMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id){
        invoiceRepository.deleteById(id);
    }
}
