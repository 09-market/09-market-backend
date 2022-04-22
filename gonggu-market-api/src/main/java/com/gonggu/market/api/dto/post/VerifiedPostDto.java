package com.gonggu.market.api.dto.post;

import java.time.Instant;

public class VerifiedPostDto {
    private String title;
    private String nickname;
    private String content;
    private Instant createdAt;

    public VerifiedPostDto() {
    }

    public VerifiedPostDto(String title, String nickname, String content, Instant createdAt) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
