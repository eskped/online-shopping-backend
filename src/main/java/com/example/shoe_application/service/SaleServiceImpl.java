package com.example.shoe_application.service;

import com.example.shoe_application.data.models.Product;
import com.example.shoe_application.data.models.Sale;
import com.example.shoe_application.data.payloads.request.SaleRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.data.repository.SaleRepository;
import com.example.shoe_application.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleRepository saleRepository;


    @Override
    public MessageResponse createSale(SaleRequest saleRequest) {
        Sale newSale = new Sale();
        newSale.setTotalPrice(saleRequest.getTotalPrice());
        newSale.setDateCreated(LocalDateTime.now());
        newSale.setProducts(saleRequest.getProducts());
        // saleRequest.getProducts().forEach(p -> p.addToSale(newSale));
        saleRepository.save(newSale);
        return new MessageResponse("New sale created");
    }


    @Override
    public ResponseEntity<Sale> updateSale(Integer saleId, SaleRequest saleRequest)
            throws ResourceNotFoundException {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", "id", saleId));
        sale.setTotalPrice(saleRequest.getTotalPrice());
        sale.setDateUpdated(LocalDateTime.now());
        sale.setProducts(saleRequest.getProducts());
        saleRepository.save(sale);

        return new ResponseEntity<Sale>(sale, HttpStatus.OK);
    }

    @Override
    public void deleteSale(Integer saleId) throws ResourceNotFoundException {
        if (saleRepository.getReferenceById(saleId).getId().equals(saleId)) {
            // could also add the stockpile for different products
            saleRepository.deleteById(saleId);
        } else throw new ResourceNotFoundException("Sale", "id", saleId);
    }

    @Override
    public Sale getASingleSale(Integer saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", "id", saleId));

    }


    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public MessageResponse getTodaysReport() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().minusDays(1).with(TemporalAdjusters.lastDayOfMonth());
        List<LocalDateTime> dates = Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end)).toList();

        List <Sale> sales = saleRepository.findAll().stream()
                .filter(sale -> dates.contains(sale.getDateCreated()))
                .toList();
        double totalSum = sales.stream().mapToDouble(Sale::getTotalPrice).sum();
        Set<Product> products = sales.stream()
                .map(Sale::getProducts)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        Set<Product> lowStockProducts = products.stream()
                .filter(p -> p.getUnitsInStock() < 10)
                .collect(Collectors.toSet());
        Set<Product> nonStockProducts = products.stream()
                .filter(p -> p.getUnitsInStock() <= 0)
                .collect(Collectors.toSet());

        return new MessageResponse(
                "Todays sales: " + totalSum + "\n" +
                "Low stock products id's: " + lowStockProducts +
                "Non stock products are : " + nonStockProducts
        );
    }

}
