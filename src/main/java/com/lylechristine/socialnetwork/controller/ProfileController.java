package com.lylechristine.socialnetwork.controller;
import com.lylechristine.socialnetwork.model.User;
import com.lylechristine.socialnetwork.repository.*;
import com.lylechristine.socialnetwork.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
@Controller
public class ProfileController {
 private final UserService users;private final UserRepository userRepo;private final PostRepository posts;
 public ProfileController(UserService users,UserRepository userRepo,PostRepository posts){this.users=users;this.userRepo=userRepo;this.posts=posts;}
 @GetMapping("/profile/{username}") String profile(@PathVariable String username,Model model,Principal principal){User u=users.require(username);model.addAttribute("profile",u);model.addAttribute("posts",posts.findByAuthorUsernameOrderByCreatedAtDesc(username));model.addAttribute("ownProfile",principal.getName().equals(username));return "profile";}
 @PostMapping("/profile") String update(@RequestParam String bio,@RequestParam String avatarUrl,Principal principal){User u=users.require(principal.getName());u.setBio(bio);u.setAvatarUrl(avatarUrl);userRepo.save(u);return "redirect:/profile/"+principal.getName();}
}
