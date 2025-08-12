// SchoolConfig.java
package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.service.*;

@Configuration
public class SchoolConfig {

    // 1. Service Bean들 등록
    @Bean
    public StudentService studentService() {
        System.out.println("🔧 StudentService Bean 생성 중...");
        return new StudentService();
    }

    @Bean
    public GradeService gradeService() {
        System.out.println("🔧 GradeService Bean 생성 중...");
        return new GradeService();
    }

    @Bean
    public AttendanceService attendanceService() {
        System.out.println("🔧 AttendanceService Bean 생성 중...");
        return new AttendanceService();
    }

}
