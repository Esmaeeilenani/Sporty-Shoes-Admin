package com.SportyShoes.service;

import com.SportyShoes.model.Product;
import com.SportyShoes.repository.ProductRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public Product getProductId(long id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> allProducts() {
        return productRepo.findAll();
    }

    public void deleteProductId(long id) {
        productRepo.deleteById(id);
    }

    public void saveAllProducts(List<Product> products) {
        productRepo.saveAll(products);
    }

    public long count() {

        return productRepo.count();
    }

    public boolean existsById(long id) {
        return productRepo.existsById(id);
    }

}
