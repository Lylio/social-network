package com.lylechristine.socialnetwork.controller;
import com.lylechristine.socialnetwork.model.Notification;
import com.lylechristine.socialnetwork.repository.NotificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
@Controller
public class NotificationController {
 private final NotificationRepository notifications;public NotificationController(NotificationRepository notifications){this.notifications=notifications;}
 @GetMapping("/notifications") String list(Model model,Principal principal){model.addAttribute("notifications",notifications.findByRecipientUsernameOrderByCreatedAtDesc(principal.getName()));return "notifications";}
 @PostMapping("/notifications/{id}/read") String read(@PathVariable Long id,Principal principal){Notification n=notifications.findById(id).orElseThrow();if(n.getRecipient().getUsername().equals(principal.getName())){n.setReadFlag(true);notifications.save(n);}return "redirect:/notifications";}
}
