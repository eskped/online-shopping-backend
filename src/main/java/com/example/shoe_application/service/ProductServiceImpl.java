package com.example.shoe_application.service;

import com.example.shoe_application.data.models.Product;
import com.example.shoe_application.data.payloads.request.ProductRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.data.repository.ProductRepository;
import com.example.shoe_application.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

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
    public Optional<Product> updateProduct(Integer productId, ProductRequest productRequest) throws ResourceNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()){
            throw new ResourceNotFoundException("product", "id", productId);
        }
        else {
            product.get().setBrand(productRequest.getBrand());
            product.get().setPrice(productRequest.getPrice());
            product.get().setUnitsInStock(productRequest.getUnitsInStock());
            productRepository.save(product.get());
        }
        return product;
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
}
