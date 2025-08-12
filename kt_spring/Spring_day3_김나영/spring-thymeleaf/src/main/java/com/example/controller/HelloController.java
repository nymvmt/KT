package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    // 🏠 기본 페이지
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Thymeleaf 데모");
        model.addAttribute("message", "Spring + Thymeleaf 연동 성공!");
        model.addAttribute("currentTime", LocalDateTime.now());

        return "hello";  // hello.html 파일 찾기
    }

    // 👤 프로필 페이지
    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("name", "김나영");
        model.addAttribute("age", 25);
        model.addAttribute("job", "IT 사업개발/컨설팅");

        // 🎯 기술 스택 리스트
        List<String> skills = Arrays.asList("Java", "Spring", "Thymeleaf", "MySQL");
        model.addAttribute("skills", skills);

        return "profile";  // profile.html 파일 찾기
    }

    @GetMapping("/about")
    public String about(Model model) {
        // 개인 정보
        model.addAttribute("myName", "김나영");
        model.addAttribute("myHobby", "독서");
        model.addAttribute("myDream", "퀸이 되는 것");

        // 좋아하는 것들 리스트
        List<String> favorites = Arrays.asList("그림", "독서", "영화", "여행", "카페탐방");
        model.addAttribute("favorites", favorites);

        return "about";
    }

}
