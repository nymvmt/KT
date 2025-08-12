package com.example.dto;

/**
 * UserUpdateDto - 사용자 정보 수정 시 사용하는 DTO
 * ID가 필요함 - 어떤 사용자를 수정할지 식별하기 위해!
 */
public class UserUpdateDto {
    
    // 필드 선언 - 수정 시에는 ID가 필요해요!
    private Long id;        // 어떤 사용자를 수정할지 식별
    private String name;
    private String email;
    private int age;
    private String phone;
    
    // 기본 생성자 - 필수!
    public UserUpdateDto() {
    }
    
    // 모든 필드를 받는 생성자 (편의용)
    public UserUpdateDto(Long id, String name, String email, int age, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
    }
    
    // Getter/Setter 메서드들
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    // toString() 메서드 - 디버깅에 유용!
    @Override
    public String toString() {
        return "UserUpdateDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
} 