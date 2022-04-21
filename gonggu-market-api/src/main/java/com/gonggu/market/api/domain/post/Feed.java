package com.gonggu.market.api.domain.post;

import com.gonggu.market.api.domain.tag.Tag;
import com.gonggu.market.api.domain.user.User;

import javax.persistence.*;

@Entity
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public Feed() {
    }

    public Feed(Long id, String url, User user, Tag tag) {
        this.id = id;
        this.url = url;
        this.user = user;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public User getUser() {
        return user;
    }

    public Tag getTag() {
        return tag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
