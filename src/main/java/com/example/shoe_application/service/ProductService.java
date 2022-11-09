package com.example.shoe_application.service;

import com.example.shoe_application.data.models.Product;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.data.payloads.request.ProductRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component // shorthand for beam registers interface as bean
public interface ProductService {
    MessageResponse createProduct(ProductRequest shoeRequest);
    Optional<Product> updateProduct(Integer shoeId, ProductRequest shoeRequest);
    void deleteProduct(Integer shoeId);
    Product getASingleProduct(Integer shoeId);
    List<Product> getAllProducts();

}
