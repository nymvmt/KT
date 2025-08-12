package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.service.*;

/**
 * School Admin System Application
 */
public class App {
    public static void main(String[] args) {
        System.out.println("🏫 학교 관리 시스템 시작!");

        // Spring Container 시작 (try-with-resources로 자동 닫기)
        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SchoolConfig.class)) {

            System.out.println("✅ 모든 Bean 생성 완료!");

            // Service Bean들 가져오기
            StudentService studentService = context.getBean(StudentService.class);
            GradeService gradeService = context.getBean(GradeService.class);
            AttendanceService attendanceService = context.getBean(AttendanceService.class);

            // 학교 관리 시스템         
            studentService.registerStudent("김철수");
            // studentService.registerStudent("이영희");
            
            gradeService.recordGrade("수학", 95);
            // gradeService.recordGrade("영어", 88);
            
            attendanceService.markAttendance("김철수");
            // attendanceService.markAttendance("이영희");

            System.out.println("\n🏫 학교 관리 시스템 종료!");
        }
    }
}

