package com.lylechristine.socialnetwork.controller;
import com.lylechristine.socialnetwork.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class AuthController {
 private final UserService users; public AuthController(UserService users){this.users=users;}
 @GetMapping("/login") String login(){return "login";}
 @GetMapping("/register") String register(){return "register";}
 @PostMapping("/register") String register(@RequestParam String username,@RequestParam String email,@RequestParam String password,Model model){
   try{users.register(username,email,password);return "redirect:/login?registered";}catch(IllegalArgumentException ex){model.addAttribute("error",ex.getMessage());return "register";}
 }
 @GetMapping("/") String root(){return "redirect:/feed";}
}
