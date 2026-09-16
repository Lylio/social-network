package com.lylechristine.socialnetwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable=false,length=2000)
    private String content;
    private String photoUrl;
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    private User author;
    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt=LocalDateTime.now();

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getContent(){return content;} public void setContent(String content){this.content=content;}
    public String getPhotoUrl(){return photoUrl;} public void setPhotoUrl(String photoUrl){this.photoUrl=photoUrl;}
    public User getAuthor(){return author;} public void setAuthor(User author){this.author=author;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}
