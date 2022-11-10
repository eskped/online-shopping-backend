package com.example.shoe_application.service;

import com.example.shoe_application.data.models.Product;
import com.example.shoe_application.data.models.Sale;
import com.example.shoe_application.data.payloads.request.SaleRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.data.payloads.request.ProductRequest;
import com.example.shoe_application.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component // shorthand for bean registers interface as bean
public interface ProductService {
    MessageResponse createProduct(ProductRequest shoeRequest);
    MessageResponse updateProduct(Integer shoeId, ProductRequest shoeRequest);
    void deleteProduct(Integer shoeId);
    Product getASingleProduct(Integer shoeId);
    List<Product> getAllProducts();

    List<Product> getAllProductsBySaleId(Integer saleId);

    List<Sale> getAllSalesByProductId(Integer productId);

    Product addProductToSale(Integer productId, SaleRequest saleRequest) throws ResourceNotFoundException;

    List<Sale> getAllSales();
}
