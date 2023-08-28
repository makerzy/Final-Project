package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {



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

    @GetMapping("/invoices/items/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoiceByItemId(@PathVariable int itemId){
        return invoiceRepository.findByItemId(itemId);
    }



    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice){
        return invoiceRepository.save(invoice);
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
