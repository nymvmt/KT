package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.example.model.Product;
import com.example.validator.ProductValidator;
import com.example.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductValidator productValidator;
    
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home() {
        return "redirect:/home.html";
    }

    @GetMapping("/product/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "redirect:/add.html";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute Product product,
                           BindingResult bindingResult,
                           Model model) {

        // 기본 검증
        // 상품명 검증
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            bindingResult.rejectValue("name", "required.product.name");
        }

        // 가격 검증
        if (product.getPrice() == null) {
            bindingResult.rejectValue("price", "required.product.price");
        } else if (product.getPrice() <= 0) {
            bindingResult.rejectValue("price", "min.product.price");
        }

        // 설명 검증
        if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
            bindingResult.rejectValue("description", "required.product.description");
        }

        // 카테고리 검증
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            bindingResult.rejectValue("category", "required.product.category");
        }

        // 비즈니스 로직 검증 (글로벌 오류)
        if (product.getPrice() != null && product.getPrice() > 1000000) {
            bindingResult.reject("product.price.tooHigh");
        }

        // 커스텀 Validator 사용
        productValidator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/add.html";
        }

        // 상품 저장
        productService.saveProduct(product);
        
        // 저장 성공
        model.addAttribute("message", "상품이 성공적으로 등록되었습니다!");
        return "redirect:/success.html";
    }
    
    @GetMapping("/home.html")
    public void homeHtml(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // 상품 목록 조회
        List<Product> products = productService.getAllProducts();
        
        // HTML 템플릿 읽기
        String content = new String(Files.readAllBytes(Paths.get("src/main/webapp/home.html")));
        
        // 상품 목록 HTML 생성
        StringBuilder productListHtml = new StringBuilder();
        if (products.isEmpty()) {
            productListHtml.append("<p class='no-products'>등록된 상품이 없습니다.</p>");
        } else {
            productListHtml.append("<div class='product-grid'>");
            for (Product product : products) {
                productListHtml.append(String.format(
                    "<div class='product-card'>" +
                        "<h3>%s</h3>" +
                        "<p class='price'>%,d원</p>" +
                        "<p class='description'>%s</p>" +
                        "<span class='category'>%s</span>" +
                    "</div>", 
                    product.getName(), 
                    product.getPrice(), 
                    product.getDescription(), 
                    getCategoryDisplayName(product.getCategory())
                ));
            }
            productListHtml.append("</div>");
        }
        
        // HTML에 상품 목록 삽입
        content = content.replace("{{PRODUCT_LIST}}", productListHtml.toString());
        content = content.replace("{{PRODUCT_COUNT}}", String.valueOf(products.size()));
        
        PrintWriter out = response.getWriter();
        out.print(content);
        out.flush();
    }
    
    private String getCategoryDisplayName(String category) {
        switch (category) {
            case "electronics": return "전자제품";
            case "clothing": return "의류";
            case "books": return "도서";
            case "home": return "생활용품";
            case "sports": return "스포츠";
            default: return category;
        }
    }
    
    @GetMapping("/add.html")
    public void addHtml(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String content = new String(Files.readAllBytes(Paths.get("src/main/webapp/add.html")));
        PrintWriter out = response.getWriter();
        out.print(content);
        out.flush();
    }
    
    @GetMapping("/success.html")
    public void successHtml(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String content = new String(Files.readAllBytes(Paths.get("src/main/webapp/success.html")));
        PrintWriter out = response.getWriter();
        out.print(content);
        out.flush();
    }
}
