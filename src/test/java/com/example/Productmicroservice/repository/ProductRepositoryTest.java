package com.example.Productmicroservice.repository;

import com.example.Productmicroservice.entity.Product;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    Product product;
    Product product1;

    @BeforeEach
    public void setup(){
        product= Product.builder().productName("Realme 11 pro plus 5g ")
                .productId(11)
                .price(25000.0)
                .category("Mobile")
                .quantity(40)
                .build();
        product1= Product.builder()
                .productId(8)
                .productName("Mi Note 13 pro plus 5g")
                .price(30000)
                .category("Mobile")
                .quantity(40)
                .build();

        productRepository.save(product);
        productRepository.save(product1);
    }

    @AfterEach
    public void tearDown(){
        product=null;
        productRepository.deleteAll();
    }

    @Test
    public void getByCategoryTest(){
        List<Product> productList= productRepository.findByCategory("Mobile");
        //assertThat(productList.get(0).getProductName()).isEqualTo(product.getProductName());
        Assertions.assertEquals(productList.get(0).getProductName(),product.getProductName());
    }

    @Test
    public void testFindById() {
        Product foundProduct = productRepository.findById(product.getProductId()).orElse(null);

        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals(product.getProductId(), foundProduct.getProductId());
        Assertions.assertEquals(product.getProductName(), foundProduct.getProductName());
        Assertions.assertEquals(product.getPrice(), foundProduct.getPrice());
    }

}
