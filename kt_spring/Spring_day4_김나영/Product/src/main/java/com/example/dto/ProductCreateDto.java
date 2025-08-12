package com.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

public class ProductCreateDto {
    
    @NotBlank(message = "상품명을 입력해주세요")
    private String name;
    
    @NotNull(message = "가격을 입력해주세요")
    @Min(value = 1, message = "가격은 0보다 커야 합니다")
    private Integer price;
    
    @NotBlank(message = "상품 설명을 입력해주세요")
    private String description;
    
    @NotBlank(message = "카테고리를 선택해주세요")
    private String category;
    
    // 기본 생성자
    public ProductCreateDto() {}
    
    // 전체 필드 생성자
    public ProductCreateDto(String name, Integer price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }
    
    // Getter/Setter
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getPrice() {
        return price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
}
