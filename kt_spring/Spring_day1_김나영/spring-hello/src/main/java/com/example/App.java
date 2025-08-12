package com.example;

import com.example.controller.Studentcontroller;

public class App {
    public static void main(String[] args) {
        System.out.println("=== 학생 관리 시스템 ===");

        Studentcontroller controller = new Studentcontroller();

        // 학생 추가
        controller.addStudent("홍길동", 20);
        controller.addStudent("김철수", 22);
        controller.addStudent("이영희", 19);

        // 전체 학생 목록 출력
        controller.showAllStudents();

        // 특정 학생 찾기
        controller.findStudent("김철수");
        controller.findStudent("박민수"); // 없는 학생
    }
}
