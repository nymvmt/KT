package com.example.controller;

import com.example.model.User;
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

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            User savedUser = userService.registerUser(user);
            model.addAttribute("message", "회원가입이 완료되었습니다! 사용자 ID: " + savedUser.getId());
            return "user/success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            return "user/register";
        }
    }

    @PostMapping("/register-with-error")
    public String registerUserWithError(@ModelAttribute User user, Model model) {
        try {
            userService.registerUserWithError(user);
            return "user/success";
        } catch (Exception e) {
            model.addAttribute("message", "예상된 에러 발생: " + e.getMessage());
            model.addAttribute("rollbackTest", true);
            return "user/success";
        }
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }
}
