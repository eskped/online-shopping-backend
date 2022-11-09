package com.example.shoe_application.web;

import com.example.shoe_application.data.models.Product;
import com.example.shoe_application.data.payloads.request.ProductRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.service.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ShoeController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllShoes() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getShoeById(@PathVariable("id") Integer id) {
        Product product = productService.getASingleProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addShoe( @RequestBody ProductRequest shoe) {
        MessageResponse newShoe = productService.createProduct(shoe);
        return new ResponseEntity<>(newShoe, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public Optional<Product> updateShoe(@PathVariable Integer id, @RequestBody ProductRequest shoe) {
        return productService.updateProduct(id, shoe);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShoe(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
