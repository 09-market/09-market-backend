package com.gonggu.market.api.domain.post;

import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.domain.user.User;

import javax.persistence.*;

@Entity
public class InstagramFeed extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String tag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public InstagramFeed() {
    }

    public InstagramFeed(Long id, String url, String tag, User user) {
        this.id = id;
        this.url = url;
        this.tag = tag;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
