package com.gonggu.market.api.domain.post;

import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.domain.tag.Tag;
import com.gonggu.market.api.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Feed extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            targetEntity = Tag.class,
            fetch = FetchType.LAZY
    )
    private List<Tag> tagList = new ArrayList<>();

    public Feed() {
    }

    public Feed(Long id, String url, User user, List<Tag> tagList) {
        this.id = id;
        this.url = url;
        this.user = user;
        this.tagList = tagList;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
