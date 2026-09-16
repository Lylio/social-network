package com.lylechristine.socialnetwork.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String sender;
    @Column(nullable=false) private String recipient;
    @Column(nullable=false,length=2000) private String content;
    @Column(nullable=false,updatable=false) private LocalDateTime sentAt=LocalDateTime.now();
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getSender(){return sender;} public void setSender(String sender){this.sender=sender;}
    public String getRecipient(){return recipient;} public void setRecipient(String recipient){this.recipient=recipient;}
    public String getContent(){return content;} public void setContent(String content){this.content=content;}
    public LocalDateTime getSentAt(){return sentAt;} public void setSentAt(LocalDateTime sentAt){this.sentAt=sentAt;}
}
