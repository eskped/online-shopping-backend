package com.example.shoe_application.service;

import com.example.shoe_application.data.models.Product;
import com.example.shoe_application.data.models.Sale;
import com.example.shoe_application.data.payloads.request.ProductRequest;
import com.example.shoe_application.data.payloads.request.SaleRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.data.repository.ProductRepository;
import com.example.shoe_application.data.repository.SaleRepository;
import com.example.shoe_application.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SaleRepository saleRepository;

    @Override
    public MessageResponse createProduct(ProductRequest productRequest) {
        Product newProduct = new Product();
        newProduct.setBrand(productRequest.getBrand());
        newProduct.setPrice(productRequest.getPrice());
        newProduct.setUnitsInStock(productRequest.getUnitsInStock());
        productRepository.save(newProduct);
        return new MessageResponse("New shoe created successfully");

    }

    @Override
    public MessageResponse updateProduct(Integer productId, ProductRequest productRequest) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        product.setBrand(productRequest.getBrand());
        product.setPrice(productRequest.getPrice());
        product.setUnitsInStock(productRequest.getUnitsInStock());
        productRepository.save(product);

        return new MessageResponse("Updated successfully");
    }

    @Override
    public void deleteProduct(Integer productId) throws ResourceNotFoundException {
        if (productRepository.getReferenceById(productId).getId().equals(productId)){
            productRepository.deleteById(productId);
        }
        else throw new ResourceNotFoundException("Product", "id", productId);
    }

    @Override
    public Product getASingleProduct(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsBySaleId(Integer saleId) {
        return new ArrayList<>(saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("sale", "id", saleId))
                .getProducts());
    }

    @Override
    public List<Sale> getAllSalesByProductId(Integer productId) {
        return new ArrayList<>(saleRepository.findAll().stream()
                .filter(sale -> sale.getProducts()
                        .stream().allMatch(p -> p.getId().equals(productId))).toList());
    }



    @Override
    public Product addProductToSale(Integer productId, SaleRequest saleRequest) throws  ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", productId));
        if (saleRepository.findById(saleRequest.getId()).isPresent()) {
            Sale newSale = saleRepository.findById(saleRequest.getId()).orElseThrow();
            product.addToSale(newSale);
            newSale.addProduct(product);
            product.setUnitsInStock(product.getUnitsInStock() - 1);
            saleRepository.save(newSale);
            productRepository.save(product);
        } else {
            Sale newSale = new Sale();
            newSale.setTotalPrice(saleRequest.getTotalPrice());
            newSale.setDateCreated(LocalDateTime.now());
            newSale.addProduct(product);
            product.setUnitsInStock(product.getUnitsInStock() - 1);
            saleRepository.save(newSale);
            productRepository.save(product);
        }
        return product;
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }


}
