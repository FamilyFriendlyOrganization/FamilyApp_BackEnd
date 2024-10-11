package com.familyapp.application.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/profile")
    public String profile(OAuth2AuthenticationToken token, Model model){
        model.addAttribute("account", token.getPrincipal().getAttributes().get("account"));
        model.addAttribute("email", token.getPrincipal().getAttributes().get("email"));
        return "account-profile";
    }

    @GetMapping("/login")
    public String login(){
        return "custom_login";
    }
}