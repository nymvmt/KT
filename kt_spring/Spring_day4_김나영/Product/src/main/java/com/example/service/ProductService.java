package com.example.service;

import org.springframework.stereotype.Service;
import com.example.model.Product;
import com.example.dto.ProductCreateDto;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    
    // 메모리에 상품 저장 (실제 프로젝트에서는 데이터베이스 사용)
    private List<Product> products = new ArrayList<>();
    
    /**
     * 상품 저장
     */
    public Product saveProduct(ProductCreateDto dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), 
                                    dto.getDescription(), dto.getCategory());
        products.add(product);
        return product;
    }
    
    /**
     * Product 객체로 저장
     */
    public Product saveProduct(Product product) {
        products.add(product);
        return product;
    }
    
    /**
     * 모든 상품 조회
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
    
    /**
     * 카테고리별 상품 조회
     */
    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                      .filter(p -> p.getCategory().equals(category))
                      .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    /**
     * 상품 수 조회
     */
    public int getProductCount() {
        return products.size();
    }
}
