package com.gonggu.market.api.controller.dto.post;

import java.time.Instant;

public class VerifiedPostDto {
    private String title;
    private String nickname;
//    private MultipartFile file;
    private Instant createdAt;


    public VerifiedPostDto() {
    }

    public VerifiedPostDto(String title, String nickname, Instant createdAt) {
        this.title = title;
        this.nickname = nickname;
//        this.file = file;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getNickname() {
        return nickname;
    }

//    public MultipartFile getFile() {
//        return file;
//    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

//    public void setFile(MultipartFile file) {
//        this.file = file;
//    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
