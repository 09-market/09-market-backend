package com.gonggu.market.api.service;

import com.auth0.jwt.JWT;
import com.gonggu.market.api.config.jwt.JwtProperties;
import com.gonggu.market.api.domain.post.InstagramFeed;
import com.gonggu.market.api.domain.post.InstagramFeedRepository;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import com.gonggu.market.api.dto.post.InstagramFeedUploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class FeedService {
    private final InstagramFeedRepository instagramFeedRepository;
    private final UserRepository userRepository;
    private final FeedTagService feedTagService;

    public FeedService(@Autowired InstagramFeedRepository instagramFeedRepository,
                       @Autowired UserRepository userRepository,
                       FeedTagService feedTagService){
        this.instagramFeedRepository = instagramFeedRepository;
        this.userRepository = userRepository;
        this.feedTagService = feedTagService;
    }

    public InstagramFeedUploadDto feedUpload(InstagramFeedUploadDto dto, String token) {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        Long userIdFromToken = JWT.decode(token).getClaim("id").asLong();
        Optional<User> userEntity = userRepository.findById(userIdFromToken);
        if (userEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        InstagramFeed instagramFeed = new InstagramFeed();
        instagramFeed.setUrl(dto.getInstagramUrl());
        instagramFeed.setTag(dto.getHashtag());
        instagramFeed.setUser(userEntity.get());
        instagramFeed = instagramFeedRepository.save(instagramFeed);

        feedTagService.saveTagInFeed(dto.getHashtag(), instagramFeed);

        return new InstagramFeedUploadDto(
                instagramFeed.getUrl(),
                instagramFeed.getTag()
        );
    }
}
