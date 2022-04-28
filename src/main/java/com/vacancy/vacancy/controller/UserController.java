package com.vacancy.vacancy.controller;

import com.vacancy.vacancy.auth.AuthUserConfig;
import com.vacancy.vacancy.entity.User;
import com.vacancy.vacancy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController extends AuthUserConfig {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;
    @GetMapping("/user/signIn")
    public String signIn(Model model) {
        return "/user/signIn";
    }

    @GetMapping("/user/profile")
    @PreAuthorize("isAuthenticated()")
    public String getProfile(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        return "user/profile";
    }


    @GetMapping("/user/signUp")
    public String signUp(){
        return "/user/signUp";
    }

    @GetMapping("/user/accessError")
    public String accessError(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        return "/user/accessDenied";
    }

    @PostMapping("/user/signUp")
    public String signUp(@RequestParam(name = "user_email") String email,
                          @RequestParam(name = "user_password") String password,
                          @RequestParam(name = "user_re_password") String re_password,
                          @RequestParam(name="phone") String phone,
                          @RequestParam(name = "fullName") String fullName, Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        if (password.equals(re_password)) {
            User user = new User();
            user.setEmail(email);
            user.setPhoneNumber(phone);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setFullName(fullName);
            if (userService.saveUser(user) != null) {
                return "redirect:/user/signUp?success";
            }

        }
        return "redirect:/user/signUp?error";
    }
    @GetMapping("/admin/adminPanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public String adminPanel(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        return "/user/adminPanel";
    }

}
