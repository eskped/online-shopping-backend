package com.example.shoe_application.web;

import com.example.shoe_application.data.models.Product;
import com.example.shoe_application.data.payloads.request.ProductRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.service.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<EntityModel<Product>> produs = products.stream()
                .map(product -> EntityModel.of(product,
                    linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel(),
                    linkTo(methodOn(ProductController.class).getAllProducts()).withRel(("all_products"))))
                .toList();
        return CollectionModel.of(produs, linkTo(methodOn(ProductController.class)
                .getAllProducts()).withSelfRel());
    }
    @GetMapping("/find/{id}")
    public EntityModel<Product> getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getASingleProduct(id);
        return EntityModel.of(product, //
                linkTo(methodOn(ProductController.class).getProductById(id)).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("all_products"));
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addProduct( @RequestBody ProductRequest product) {
        MessageResponse newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public Optional<Product> updateProject(@PathVariable Integer id, @RequestBody ProductRequest product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
