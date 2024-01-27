package com.example.Productmicroservice.service;

import com.example.Productmicroservice.entity.Product;
import com.example.Productmicroservice.exceptions.ProductNotFoundException;
import com.example.Productmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ProductServiceImpl implements ProductService{


    private final ProductRepository productRepository;

    @Autowired
    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository= productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    @Cacheable(cacheNames = "GetAllValuesCache")
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "GetOneValueCache")
    public Product getSingle(long productId) {
        return repository.findById(productId).orElseThrow(
                ()-> new ProductNotFoundException("Product not found with given id : "+productId));
    }

    @Override
    public Product updatePriceUsingPatch(long productId, double price) {
        Product newProduct= repository.findById(productId).orElseThrow(ProductNotFoundException::new);
        newProduct.setPrice(price);

        repository.save(newProduct);

        return newProduct;
    }

    @Override
    public String deleteProduct(long productId) {
        repository.deleteById(productId);

        return "Deleted Successfully !";
    }

    @Override
    @CacheEvict(key = "productId")
    public String deleteCache(long productId) {
        return "Cache deleted successfully for product : "+productId;
    }
}
