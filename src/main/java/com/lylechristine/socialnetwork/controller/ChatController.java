package com.lylechristine.socialnetwork.controller;
import com.lylechristine.socialnetwork.model.ChatMessage;
import com.lylechristine.socialnetwork.repository.ChatMessageRepository;
import com.lylechristine.socialnetwork.repository.UserRepository;
import com.lylechristine.socialnetwork.service.NotificationService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
@Controller
public class ChatController {
 private final SimpMessagingTemplate messaging;private final ChatMessageRepository messages;private final UserRepository users;private final NotificationService notificationService;
 public ChatController(SimpMessagingTemplate messaging,ChatMessageRepository messages,UserRepository users,NotificationService notificationService){this.messaging=messaging;this.messages=messages;this.users=users;this.notificationService=notificationService;}
 @GetMapping("/messages") String page(@RequestParam(required=false) String with,Model model,Principal principal){model.addAttribute("people",users.findAll().stream().filter(u->!u.getUsername().equals(principal.getName())).toList());model.addAttribute("with",with);model.addAttribute("history",with==null?java.util.List.of():messages.conversation(principal.getName(),with));return "messages";}
 public record OutgoingMessage(String recipient,String content){}
 @MessageMapping("/chat.send") public void send(OutgoingMessage incoming,Principal principal){
   if(incoming.recipient()==null||incoming.content()==null||incoming.content().isBlank()) return;
   ChatMessage m=new ChatMessage();m.setSender(principal.getName());m.setRecipient(incoming.recipient());m.setContent(incoming.content().trim());messages.save(m);
   messaging.convertAndSendToUser(incoming.recipient(),"/queue/messages",m);messaging.convertAndSendToUser(principal.getName(),"/queue/messages",m);
   notificationService.notifyUser(incoming.recipient(), principal.getName()+" sent you a message", "/messages?with="+principal.getName());
 }
}
