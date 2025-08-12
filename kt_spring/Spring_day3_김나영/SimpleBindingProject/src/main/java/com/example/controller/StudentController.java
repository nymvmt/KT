package com.example.controller;

import com.example.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {


    /**
     * 홈페이지
     */
    // 여기를 꼭 작성해주세요!
    @GetMapping("/")
    public String home() {
        System.out.println("🏠 홈페이지 요청");
        return "index";  // index.html 템플릿으로 이동
    }


    /**
     * 학생 등록 폼 보여주기
     */
    // 여기를 꼭 작성해주세요!
    @GetMapping("/student/form")
    public String studentForm(Model model) {
        System.out.println("📝 학생 등록 폼 요청");
        model.addAttribute("student", new Student());  // 빈 Student 객체 전달
        return "student-form";  // student-form.html 템플릿으로 이동
    }
    

    /**
     * 🎯 기본 바인딩 - @ModelAttribute 사용
     */
    @PostMapping("/student/save")
    public String saveStudent(@ModelAttribute Student student, Model model) {

        System.out.println("🎉 기본 바인딩 완료!");
        System.out.println("받은 학생 정보: " + student);

        // student, message 값을 전달해서 출력해보세요!!
        model.addAttribute("student", student);
        model.addAttribute("message", "학생 정보가 성공적으로 등록되었습니다!");

        return "student-result";
    }

    /**
     * 🛠️ 에러 처리 바인딩 - BindingResult 사용
     */
    @PostMapping("/student/save-with-error")
    public String saveStudentWithError(@ModelAttribute Student student,
                                      BindingResult bindingResult,
                                      Model model) {

        System.out.println("🔍 에러 처리 바인딩 시작!");
        System.out.println("받은 학생 정보: " + student);

        // 바인딩 에러 확인
        if (bindingResult.hasErrors()) {
            System.out.println("❌ 바인딩 에러 발생! 에러 개수: " + bindingResult.getErrorCount());

            bindingResult.getFieldErrors().forEach(error -> {
                System.out.println("- 필드: " + error.getField());
                System.out.println("- 입력값: " + error.getRejectedValue());
                System.out.println("- 에러: " + error.getDefaultMessage());
            });

            model.addAttribute("errors", bindingResult.getAllErrors());
            return "student-form";  // 에러시 다시 폼으로
        }

        System.out.println("✅ 바인딩 성공!");
        model.addAttribute("student", student);
        model.addAttribute("message", "학생 정보가 성공적으로 등록되었습니다! (에러 처리 포함)");

        return "student-result";
    }
}
