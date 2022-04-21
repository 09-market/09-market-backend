package com.gonggu.market.api.domain.post;

import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.domain.tag.Tag;
import com.gonggu.market.api.domain.user.User;

import javax.persistence.*;

@Entity
public class VerifiedPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String nickname;
    private String content;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public VerifiedPost() {
    }

    public VerifiedPost(Long id, String title, String nickname, String content, String status, User user) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
