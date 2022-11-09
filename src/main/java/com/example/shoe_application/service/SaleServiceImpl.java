package com.example.shoe_application.service;

import com.example.shoe_application.data.models.Sale;
import com.example.shoe_application.data.payloads.request.SaleRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.data.repository.SaleRepository;
import com.example.shoe_application.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        saleRequest.getProducts().keySet()
                .forEach(k ->
                    k.setUnitsInStock(k.getUnitsInStock() - saleRequest.getProducts().get(k)));
        saleRepository.save(newSale);
        return new MessageResponse("New sale created");
    }


    @Override
    public Optional<Sale> updateSale(Integer saleId, SaleRequest saleRequest)
            throws ResourceNotFoundException {
        Optional<Sale> sale = saleRepository.findById(saleId);
        if (sale.isEmpty()) {
            throw new ResourceNotFoundException("Sale", "id", saleId);
        } else {
            sale.get().setTotalPrice(saleRequest.getTotalPrice());
            sale.get().setDateUpdated(LocalDateTime.now());
            sale.get().setProducts(saleRequest.getProducts());
            saleRepository.save(sale.get());
        }
        return sale;
    }

    @Override
    public void deleteSale(Integer saleId) throws ResourceNotFoundException {
        if (saleRepository.getReferenceById(saleId).getId().equals(saleId)) {
            saleRepository.deleteById(saleId);
        }
        else throw new ResourceNotFoundException("Sale", "id", saleId);
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
}
