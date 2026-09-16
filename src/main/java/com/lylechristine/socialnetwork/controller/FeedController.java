package com.lylechristine.socialnetwork.controller;
import com.lylechristine.socialnetwork.model.Post;
import com.lylechristine.socialnetwork.repository.*;
import com.lylechristine.socialnetwork.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.security.Principal;
import java.util.UUID;
@Controller
public class FeedController {
 private final PostRepository posts;private final UserService users;private final NotificationRepository notifications;
 public FeedController(PostRepository posts,UserService users,NotificationRepository notifications){this.posts=posts;this.users=users;this.notifications=notifications;}
 @GetMapping("/feed") String feed(Model model,Principal principal){model.addAttribute("posts",posts.findAllByOrderByCreatedAtDesc());model.addAttribute("unread",notifications.countByRecipientUsernameAndReadFlagFalse(principal.getName()));return "feed";}
 @PostMapping("/posts") String create(@RequestParam String content,@RequestParam(required=false) MultipartFile photo,Principal principal) throws IOException{
   Post p=new Post();p.setContent(content);p.setAuthor(users.require(principal.getName()));
   if(photo!=null&&!photo.isEmpty()){Path dir=Paths.get("uploads");Files.createDirectories(dir);String safe=UUID.randomUUID()+"-"+Paths.get(photo.getOriginalFilename()).getFileName();Files.copy(photo.getInputStream(),dir.resolve(safe),StandardCopyOption.REPLACE_EXISTING);p.setPhotoUrl("/uploads/"+safe);}
   posts.save(p);return "redirect:/feed";
 }
 @PostMapping("/posts/{id}/delete") String delete(@PathVariable Long id,Principal principal){Post p=posts.findById(id).orElseThrow();if(p.getAuthor().getUsername().equals(principal.getName())) posts.delete(p);return "redirect:/feed";}
}
