package com.Yashwanth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.Yashwanth.Repo.ProductRepository;
import com.Yashwanth.Entity.Productentity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate; 

    private static final String FAKE_STORE_API_URL = "https://fakestoreapi.com/products";

    public Page<Productentity> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Productentity createProduct(Productentity product) {
        return productRepository.save(product);
    }

    public Optional<Productentity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Productentity updateProduct(Long id, Productentity productDetails) {
        Productentity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setCategory(productDetails.getCategory());
        product.setImage(productDetails.getImage());
        product.setTags(productDetails.getTags());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // New method to fetch products from Fake Store API and save to database
    public void fetchAndPopulateProducts() {
        try {
            Productentity[] products = restTemplate.getForObject(FAKE_STORE_API_URL, Productentity[].class);
            if (products != null) {
                List<Productentity> productList = Arrays.asList(products);
                productRepository.saveAll(productList); // Save all fetched products to the database
                logger.info("Successfully fetched and saved {} products from Fake Store API", productList.size());
            } else {
                logger.warn("No products found from the Fake Store API.");
            }
        } catch (RestClientException e) {
            logger.error("Error occurred while fetching products from the Fake Store API: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
        }
    }
}
