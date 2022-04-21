package com.gonggu.market.api.domain.comment;

import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.domain.post.VerifiedPost;

import javax.persistence.*;

@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String content;

    @ManyToOne
    @JoinColumn(name = "verified_post_id")
    private VerifiedPost verifiedPost;

    public Comment() {
    }

    public Comment(Long id, String nickname, String content, VerifiedPost verifiedPost) {
        this.id = id;
        this.nickname = nickname;
        this.content = content;
        this.verifiedPost = verifiedPost;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }

    public VerifiedPost getVerifiedPost() {
        return verifiedPost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setVerifiedPost(VerifiedPost verifiedPost) {
        this.verifiedPost = verifiedPost;
    }
}
