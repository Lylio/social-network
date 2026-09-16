package com.lylechristine.socialnetwork.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) private User recipient;
    @Column(nullable=false,length=500) private String message;
    private String link;
    @Column(nullable=false) private boolean readFlag=false;
    @Column(nullable=false,updatable=false) private LocalDateTime createdAt=LocalDateTime.now();
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public User getRecipient(){return recipient;} public void setRecipient(User recipient){this.recipient=recipient;}
    public String getMessage(){return message;} public void setMessage(String message){this.message=message;}
    public String getLink(){return link;} public void setLink(String link){this.link=link;}
    public boolean isReadFlag(){return readFlag;} public void setReadFlag(boolean readFlag){this.readFlag=readFlag;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}
