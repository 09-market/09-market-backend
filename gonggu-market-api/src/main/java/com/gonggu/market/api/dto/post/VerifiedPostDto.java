package com.gonggu.market.api.dto.post;

public class VerifiedPostDto {
    private String title;
    private String nickname;
    private String content;

    public VerifiedPostDto() {
    }

    public VerifiedPostDto(String title, String nickname, String content) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
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
}
