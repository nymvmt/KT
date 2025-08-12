package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProductById(Long id) {
		    // TODO : 특정 아이디를 기반으로 조회하려면 어떤 함수를 호출해야 할까요?
		    // return 문을 완성해주세요.
        return productDao.findById(id);
    }

    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }

    public boolean existsById(Long id) {
        return productDao.existsById(id);
    }

    // 이름으로 상품 검색
    public List<Product> searchProductsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllProducts();
        }
        return productDao.findByNameContaining(name.trim());
    }

    // 가격 범위로 상품 필터링
    public List<Product> filterProductsByPriceRange(Integer minPrice, Integer maxPrice) {
        return productDao.findByPriceRange(minPrice, maxPrice);
    }

    // 이름과 가격 범위로 상품 검색 (통합 검색)
    public List<Product> searchProducts(String name, Integer minPrice, Integer maxPrice) {
        // 모든 조건이 비어있으면 전체 상품 반환
        if ((name == null || name.trim().isEmpty()) && minPrice == null && maxPrice == null) {
            return getAllProducts();
        }
        
        // 이름만 검색하는 경우
        if (name != null && !name.trim().isEmpty() && minPrice == null && maxPrice == null) {
            return productDao.findByNameContaining(name);
        }
        
        // 가격만 필터링하는 경우
        if ((name == null || name.trim().isEmpty()) && (minPrice != null || maxPrice != null)) {
            return productDao.findByPriceRange(minPrice, maxPrice);
        }
        
        // 복합 검색
        return productDao.findByNameAndPriceRange(name, minPrice, maxPrice);
    }
}
