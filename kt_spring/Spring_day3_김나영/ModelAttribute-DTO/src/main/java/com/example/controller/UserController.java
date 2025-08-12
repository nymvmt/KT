package com.example.controller;

import com.example.dto.UserCreateDto;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. 사용자 목록 페이지
    @GetMapping
    public String listUsers(Model model) {
        // userService에서 모든 사용자 데이터를 가져와서 모델에 추가
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    // 2. 사용자 상세 페이지
    @GetMapping("/{id}")
    public String userDetail(@PathVariable Long id, Model model) {
        // URL에서 받은 id로 특정 사용자 정보를 가져와서 모델에 추가
        model.addAttribute("user", userService.getUserById(id));
        return "user/detail";
    }

    // 3. 사용자 생성 폼 페이지
    @GetMapping("/create")
    public String createUserForm(Model model) {
        // 빈 UserCreateDto 객체를 생성해서 폼 바인딩용으로 모델에 추가
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "user/create";
    }

    // 4. 사용자 생성 처리 ⭐ ModelAttribute의 핵심!
    @PostMapping("/create")
    public String createUser(@ModelAttribute UserCreateDto userCreateDto) {
        // 1. 받은 데이터 확인용 로그 출력
        System.out.println("받은 사용자 생성 데이터: " + userCreateDto);
        
        // 2. UserService를 통해 새 사용자 생성
        userService.createUser(userCreateDto);
        
        // 3. 생성 완료 후 사용자 목록 페이지로 리다이렉트
        return "redirect:/users";
    }
}
