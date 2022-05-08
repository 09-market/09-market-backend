package com.gonggu.market.api.service;

import com.auth0.jwt.JWT;
import com.gonggu.market.api.config.jwt.JwtProperties;
import com.gonggu.market.api.domain.post.Feed;
import com.gonggu.market.api.domain.post.FeedRepository;
import com.gonggu.market.api.domain.tag.Tag;
import com.gonggu.market.api.domain.tag.TagRepository;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import com.gonggu.market.api.dto.feed.FeedUploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public FeedService(@Autowired FeedRepository feedRepository,
                       @Autowired UserRepository userRepository,
                       @Autowired TagRepository tagRepository) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public FeedUploadDto feedUpload(FeedUploadDto dto, String token) {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        Long userIdFromToken = JWT.decode(token).getClaim("id").asLong();
        Optional<User> userEntity = userRepository.findById(userIdFromToken);
        if (userEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Feed feed = new Feed();
        feed.setUrl(dto.getUrl());
        feed.setUser(userEntity.get());
        String[] tags = dto.getTags().split(",");
        List<Tag> tagList = new ArrayList<>();
        for (String tag : tags) {
            tagList.add(new Tag(tag));
        }
        tagRepository.saveAll(tagList);
        feed.setTagList(tagList);
        feed = feedRepository.save(feed);
        return new FeedUploadDto(
                feed.getUrl(),
                feed.getTagList().toString());
    }
}
