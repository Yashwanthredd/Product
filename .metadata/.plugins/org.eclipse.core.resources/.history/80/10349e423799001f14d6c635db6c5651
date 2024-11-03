package com.Yashwanth.Controller;

import com.Yashwanth.Entity.Productentity;
import com.Yashwanth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Product Listing with Pagination
    @GetMapping
    public ResponseEntity<Page<Productentity>> getAllProducts(Pageable pageable) {
        Page<Productentity> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    // Product Creation
    @PostMapping
    public ResponseEntity<Productentity> createProduct(@Validated @RequestBody Productentity product) {
        Productentity createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Get a Single Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Productentity> getProductById(@PathVariable Long id) {
        Optional<Productentity> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Product
    @PutMapping("/{id}")
    public ResponseEntity<Productentity> updateProduct(@PathVariable Long id, @Validated @RequestBody Productentity productDetails) {
        try {
            Productentity updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Handles "Product not found" case
        }
    }

    // Delete a Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Handles "Product not found" case
        }
    }
}
