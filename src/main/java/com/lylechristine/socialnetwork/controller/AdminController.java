package com.lylechristine.socialnetwork.controller;
import com.lylechristine.socialnetwork.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/admin")
public class AdminController {
 private final UserRepository users;private final PostRepository posts;
 public AdminController(UserRepository users,PostRepository posts){this.users=users;this.posts=posts;}
 @GetMapping String dashboard(Model model){model.addAttribute("users",users.findAll());model.addAttribute("posts",posts.findAllByOrderByCreatedAtDesc());return "admin";}
 @PostMapping("/users/{id}/toggle") String toggle(@PathVariable Long id){users.findById(id).ifPresent(u->{u.setEnabled(!u.isEnabled());users.save(u);});return "redirect:/admin";}
 @PostMapping("/posts/{id}/delete") String deletePost(@PathVariable Long id){posts.deleteById(id);return "redirect:/admin";}
}
