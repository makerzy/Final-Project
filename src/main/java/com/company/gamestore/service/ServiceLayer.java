package com.company.gamestore.service;


import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private final double EXTRA_FEE= 15.49;

    private InvoiceRepository invoiceRepository;
    private TaxRepository taxRepository;
    private FeeRepository feeRepository;

    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private TShirtRepository tShirtRepository;


    @Autowired
    public ServiceLayer(
            InvoiceRepository invoiceRepository,
            TaxRepository taxRepository,
            FeeRepository feeRepository,
            ConsoleRepository consoleRepository,
            GameRepository gameRepository,
            TShirtRepository tShirtRepository
    ) {
        this.invoiceRepository = invoiceRepository;
        this.taxRepository = taxRepository;
        this.feeRepository = feeRepository;
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.tShirtRepository = tShirtRepository;
    }




    @Transactional
    public Invoice saveInvoice(InvoiceViewModel ivModel)  {
        Invoice invoice  = new Invoice();
        Object item;
        try {
            if (ivModel.getQuantity() < 1) {
                throw new Exception("Order quantity must be greater than or equal to 1");
            }

            invoice.setName(ivModel.getName());
            invoice.setStreet(ivModel.getStreet());
            invoice.setCity(ivModel.getCity());
            invoice.setState(ivModel.getState());
            invoice.setZipCode(ivModel.getZipCode());
            invoice.setItemType(ivModel.getItemType());
            invoice.setItemId(ivModel.getItemId());
            invoice.setQuantity(ivModel.getQuantity());
            double unitPrice = 0.0;
            switch (ivModel.getItemType()) {
                case "Game":
                    Optional<Game> game = gameRepository.findById(ivModel.getItemId());
                    if (game.isPresent()) {
                        if (game.get().getQuantity() < ivModel.getQuantity()) {
                            throw new Exception("Order quantity must be less than or equal available stock");
                        }
                        unitPrice = game.get().getPrice();
                    }

                    break;
                case "T-Shirt":
                    Optional<TShirt> tShirt = tShirtRepository.findById(ivModel.getItemId());
                    if (tShirt.isPresent()) {
                        if (tShirt.get().getQuantity() < ivModel.getQuantity()) {
                            throw new Exception("Order quantity must be less than or equal available stock");
                        }
                        unitPrice = tShirt.get().getPrice();
                    }

                    break;
                case "Console":
                    Optional<Console> console = consoleRepository.findById(ivModel.getItemId());
                    if (console.isPresent()) {
                        if (console.get().getQuantity() < ivModel.getQuantity()) {
                            throw new Exception("Order quantity must be less than or equal available stock");
                        }
                        unitPrice = console.get().getPrice();
                    }
                    break;
            }

            invoice.setUnitPrice(unitPrice);
            double subtotal = unitPrice * ivModel.getQuantity();
            invoice.setSubtotal(subtotal);
            double rate = 0.0;
            List<Tax> taxes = taxRepository.findByState(ivModel.getState());
            if (taxes.size() > 0) {
                rate = taxes.get(0).getRate();
            }else{
                // size ==0 if the state code is invalid,
                // since we have all the supported states in the DB
                // Hence, we throw an exception
                throw new Exception("Cannot process order for unknown state code");
            }
            double taxValue = subtotal * rate;
            invoice.setTax(taxValue);
            List<Fee> fees = feeRepository.findByProductType(ivModel.getItemType());
            double processingFee = 0.0;
            if (fees.size() > 0) {
                processingFee = fees.get(0).getFee();
            }
            if (ivModel.getQuantity() > 10) {
                processingFee = processingFee + EXTRA_FEE;
            }
            invoice.setProcessingFee(processingFee);

            double total = subtotal + taxValue + processingFee;
            invoice.setTotal(total);

            invoice = invoiceRepository.save(invoice);

        }catch (Exception e){
            ;
        }
        return invoice;
    }

}
