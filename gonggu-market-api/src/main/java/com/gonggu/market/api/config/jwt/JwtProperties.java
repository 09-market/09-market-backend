package com.gonggu.market.api.config.jwt;

public interface JwtProperties {
    String SECRET = "zeronine";
    int EXPIRATION_TIME = 1000 * 60 * 60;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
