package com.example.model;

import java.util.List;

/**
 * 👨‍🎓 학생 정보 클래스 - 바인딩 학습용
 */
public class Student {

    // 아래 내용을 직접 작성해보세요.
    private String name;        // 이름
    private int age;           // 나이
    private String email;      // 이메일
    private boolean scholarship; // 장학금 여부
    private List<String> hobbies; // 취미 목록

    // 기본 생성자 (Spring 필수!)
    public Student() {
        System.out.println("👨‍🎓 Student 객체 생성됨!");
    }

    // Getter & Setter (바인딩을 위해 필수!)
    // 여기를 꼭 작성해주세요!
    public String getName() { return name; }
    public void setName(String name) {
        System.out.println("👤 이름 설정: " + name);
        this.name = name;
    }

    public int getAge() { return age; }
    public void setAge(int age) {
        System.out.println("🎂 나이 설정: " + age);
        this.age = age;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        System.out.println("📧 이메일 설정: " + email);
        this.email = email;
    }
    
    public boolean isScholarship() { return scholarship; }
    public void setScholarship(boolean scholarship) {
        System.out.println("🏆 장학금 설정: " + scholarship);
        this.scholarship = scholarship;
    }

    public List<String> getHobbies() { return hobbies; }
    public void setHobbies(List<String> hobbies) {
        // 여기를 꼭 작성해주세요!
        System.out.println("🎯 취미 설정: " + hobbies);
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age +
               ", email='" + email + "', scholarship=" + scholarship +
               ", hobbies=" + hobbies + "}";
    }
}
