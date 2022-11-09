package com.example.shoe_application.web;

import com.example.shoe_application.data.models.Sale;
import com.example.shoe_application.data.payloads.request.SaleRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.service.SaleService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/all")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable("id") Integer id) {
        Sale sale = saleService.getASingleSale(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addSale(@io.swagger.v3.oas.annotations.parameters.RequestBody SaleRequest sale) {
        MessageResponse newSale = saleService.createSale(sale);
        return new ResponseEntity<>(newSale, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public Optional<Sale> updateSale(@PathVariable Integer id, @RequestBody SaleRequest sale) {
        return saleService.updateSale(id, sale);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable("id") Integer id) {
        saleService.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
