package com.gonggu.market.api.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.domain.item.Item;
import com.gonggu.market.api.domain.post.VerifiedPost;
import com.gonggu.market.api.domain.user.User;

import javax.persistence.*;

@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @JsonIgnoreProperties({"items"})
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    @JsonIgnoreProperties({"user"})
    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;

    public Comment() {
    }

    public Comment(Long id, String content, User user, Item item) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
