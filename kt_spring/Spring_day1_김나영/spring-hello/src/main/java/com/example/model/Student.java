package com.example.model;

public class Student { //저장
    private String name; //필드값을 private로 선언해서, 밖에서 직접 수정 불가하도록 설정
    private int age;

    // 기본 생성자
    public Student() {
    }

    // 매개변수가 있는 생성자
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter 메서드
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setter 메서드
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // // toString 메서드 (디버깅과 출력에 유용)
    // @Override
    // public String toString() {
    //     return "Student{" +
    //             "name='" + name + '\'' +
    //             ", age=" + age +
    //             '}';
    // }
}
