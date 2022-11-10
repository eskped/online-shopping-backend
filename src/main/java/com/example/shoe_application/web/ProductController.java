package com.example.shoe_application.web;

import com.example.shoe_application.data.models.Product;
import com.example.shoe_application.data.models.Sale;
import com.example.shoe_application.data.payloads.request.ProductRequest;
import com.example.shoe_application.data.payloads.request.SaleRequest;
import com.example.shoe_application.data.payloads.response.MessageResponse;
import com.example.shoe_application.service.ProductService;
import com.example.shoe_application.service.SaleService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public CollectionModel<EntityModel<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return getProductsWithHyperlinks(products.stream(), products);
    }
    @GetMapping("/sales/{saleId}/products")
    public CollectionModel<EntityModel<Product>> getAllProductsBySaleId(@PathVariable(value = "saleId")
                                                                Integer saleId) {
        List<Product> products = productService.getAllProductsBySaleId(saleId);
        return getProductsWithHyperlinks(products.stream(), products);
    }

    private CollectionModel<EntityModel<Product>> getProductsWithHyperlinks(Stream<Product> stream, List<Product> products) {
        List<EntityModel<Product>> productsWithLinks = stream
                .map(product -> EntityModel.of(product,
                        linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel(),
                        linkTo(methodOn(ProductController.class).getAllProducts()).withRel(("all_products"))))
                .toList();
        return CollectionModel.of(productsWithLinks, linkTo(methodOn(ProductController.class)
                .getAllProducts()).withSelfRel());
    }

    @GetMapping("/products/{id}")
    public EntityModel<Product> getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getASingleProduct(id);
        return EntityModel.of(product, //
                linkTo(methodOn(ProductController.class).getProductById(id)).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("all_products"));
    }

    @GetMapping("/product/{productId}/sales")
    public CollectionModel<EntityModel<Sale>>getAllSalesByProductId(@PathVariable(value = "productId") Integer productId) {
        List<Sale> sales = productService.getAllSalesByProductId(productId);
        List<EntityModel<Sale>> salesWithHyperlinks = sales.stream()
                .map(sale -> EntityModel.of(sale,
                        linkTo(methodOn(SaleController.class).getSaleById(sale.getId())).withSelfRel(),
                        linkTo(methodOn(SaleController.class).getAllSales()).withRel(("all_sales"))))
                .toList();
        return CollectionModel.of(salesWithHyperlinks, linkTo(methodOn(ProductController.class)
                .getAllProducts()).withSelfRel());
    }

    @PostMapping("/products/{productId}/sale")
    public EntityModel<Product> addProductToSale(@PathVariable(value = "productId") Integer productId, @RequestBody SaleRequest saleRequest) {
        Product product = productService.addProductToSale(productId, saleRequest);
        return EntityModel.of(product, //
                linkTo(methodOn(ProductController.class).getProductById(productId)).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("all_products"));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<MessageResponse> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.updateProduct(id, productRequest), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
