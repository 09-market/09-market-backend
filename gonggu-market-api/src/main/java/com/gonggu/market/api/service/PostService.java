package com.gonggu.market.api.service;

import com.gonggu.market.api.domain.post.VerifiedPost;
import com.gonggu.market.api.domain.post.VerifiedPostRepository;
import com.gonggu.market.api.dto.post.VerifiedPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PostService {
    private final VerifiedPostRepository verifiedPostRepository;

    public PostService(@Autowired VerifiedPostRepository verifiedPostRepository) {
        this.verifiedPostRepository = verifiedPostRepository;
    }

    public VerifiedPost create(VerifiedPostDto dto) {
        VerifiedPost verifiedPostEntity = new VerifiedPost();
        verifiedPostEntity.setTitle(dto.getTitle());
        verifiedPostEntity.setNickname(dto.getNickname());
        verifiedPostEntity.setContent(dto.getContent());
        verifiedPostEntity = verifiedPostRepository.save(verifiedPostEntity);

        return verifiedPostEntity;
    }

    public Collection<VerifiedPostDto> readAll() {
        List<VerifiedPostDto> dtoList = new ArrayList<>();
        this.verifiedPostRepository.findAll().forEach(verifiedPost -> dtoList.add(
                new VerifiedPostDto(
                        verifiedPost.getTitle(),
                        verifiedPost.getNickname(),
                        verifiedPost.getContent(),
                        verifiedPost.getCreatedAt()
                )
        ));
        return dtoList;
    }

    public boolean update(Long postId, VerifiedPostDto dto) {
        if (!verifiedPostRepository.existsById(postId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        VerifiedPost verifiedPost = verifiedPostRepository.findById(postId).get();
        verifiedPost.setTitle(dto.getTitle() == null ? verifiedPost.getTitle() : dto.getTitle());
        verifiedPost.setContent(dto.getContent() == null ? verifiedPost.getContent() : dto.getContent());
        verifiedPostRepository.save(verifiedPost);
        return true;
    }

    public boolean delete(Long postId) {
        if (!verifiedPostRepository.existsById(postId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        VerifiedPost verifiedPost = verifiedPostRepository.findById(postId).get();
        verifiedPostRepository.delete(verifiedPost);
        return true;
    }
}
