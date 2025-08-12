package com.example.controller;

import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // 🎭 "나는 웹 요청을 처리하는 Controller야!"
public class HelloController {

    // 🔌 MessageService를 자동으로 연결 (의존성 주입)
    @Autowired
    private MessageService messageService;

    // 🏠 기본 페이지 ("/") 요청 처리
    @GetMapping("/")
    public String home(Model model) {
        // Model: JSP에 데이터를 전달하는 택배 상자 같은 것
        model.addAttribute("title", "Spring MVC 홈페이지");
        model.addAttribute("message", "환영합니다! Spring MVC 세계에 오신 것을 환영합니다!");

        return "home";  // home.jsp 파일을 찾아서 실행
    }

    // 👋 "/hello" 요청 처리
    @GetMapping("/hello")
    public String hello(Model model) {
        // Service에서 메시지 가져오기
        String message = messageService.getWelcomeMessage("김코딩");

        model.addAttribute("message", message);
        model.addAttribute("currentTime", java.time.LocalDateTime.now());

        return "hello";  // hello.jsp 파일 실행
    }

    // 🎯 "/about" 요청 처리
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "소개 페이지");
        model.addAttribute("description", "이곳은 Spring MVC 학습 페이지입니다.");

        return "about";  // about.jsp 파일 실행
    }

      // 🥳 "/welcome" 요청 처리
      @GetMapping("/welcome")
      public String welcome(Model model) {
          model.addAttribute("title", "welcome 페이지");
          model.addAttribute("description", "이곳은 당신을 환영하는 페이지입니다. -나영-");
          
          // 🌟 새로운 기능: 오늘 요일 메시지 추가!
          String todayMessage = messageService.getTodayMessage();
          model.addAttribute("todayMessage", todayMessage);
  
          return "welcome";  // welcome.jsp 파일 실행
      }
}
