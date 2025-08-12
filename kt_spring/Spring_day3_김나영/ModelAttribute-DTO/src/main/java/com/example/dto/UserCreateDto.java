package com.example.dto;

/**
 * UserCreateDto - 새 사용자 생성 시 사용하는 DTO
 * ID는 서버에서 자동 생성되므로 포함하지 않음
 */
public class UserCreateDto {
    
    // 필드 선언 - ID는 필요없음! (서버에서 자동 생성)
    private String name;
    private String email;
    private int age;
    private String phone;
    
    // 기본 생성자 - 필수!
    public UserCreateDto() {
    }
    
    // 모든 필드를 받는 생성자 (편의용)
    public UserCreateDto(String name, String email, int age, String phone) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
    }
    
    // Getter/Setter 메서드들
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
        return "UserCreateDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}
