package com.ideage.ams.controller;

import com.ideage.ams.entity.LoginUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.util.StringUtils;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }

    @GetMapping("/IdeageSys")
    public String showHome(@AuthenticationPrincipal LoginUserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getDisplayName());
        model.addAttribute("email", userDetails.getEmail());
        model.addAttribute("department", userDetails.getDepartment());
        model.addAttribute("icnoUrl", StringUtils.isEmptyOrWhitespace(userDetails.getIconUrl()) ?
                "dist/img/logo_2.jpg" : userDetails.getIconUrl());
        return "Home";
    }
}