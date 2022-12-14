package com.example.shoe_application.service;

import com.example.shoe_application.data.models.Product;
import com.example.shoe_application.data.models.Sale;
import com.example.shoe_application.data.payloads.request.ProductRequest;
import com.example.shoe_application.data.payloads.request.SaleRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface SaleService {
    MessageResponse createSale(SaleRequest saleRequest);
    ResponseEntity<Sale> updateSale(Integer saleId, SaleRequest saleRequest);
    void deleteSale(Integer saleId);
    Sale getASingleSale(Integer saleId);
    List<Sale> getAllSales();

    MessageResponse getTodaysReport();
}
