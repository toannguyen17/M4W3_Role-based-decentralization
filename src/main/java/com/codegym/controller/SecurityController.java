package com.codegym.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {

    @ModelAttribute("user")
    private String getPrincipal(){
        String username = null;

        Object principal = SecurityContextHolder.getContext().getAuthentication();

        if (principal instanceof UserDetails){
            username  = ((UserDetails) principal).getUsername();
        } else {
            username = principal != null ? principal.toString() : null;
        }
        return username;
    }

    @GetMapping(value = {"/", "/home"})
    public String doGet(Model model){
        return "welcome";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model) {
        return "admin";
    }

    @GetMapping(value = "/Access_Denied")
    public String accessDeniedPage(Model model) {
        return "accessDenied";
    }
    @GetMapping(value = "/dba")
    public String dbaPage(Model model) {
        return "dba";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }
}
