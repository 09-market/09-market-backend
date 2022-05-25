package com.gonggu.market.api.service;

import com.auth0.jwt.JWT;
import com.gonggu.market.api.config.jwt.JwtProperties;
import com.gonggu.market.api.controller.dto.comment.CommentDto;
import com.gonggu.market.api.domain.comment.Comment;
import com.gonggu.market.api.domain.comment.CommentRepository;
import com.gonggu.market.api.domain.item.Item;
import com.gonggu.market.api.domain.item.ItemRepository;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public CommentService(@Autowired  CommentRepository commentRepository,
                          @Autowired ItemRepository itemRepository,
                          @Autowired UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommentDto create(CommentDto commentDto, String token) {
        Item itemEntity = itemRepository.findById(commentDto.getItemId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        Long userIdFromToken = JWT.decode(token).getClaim("id").asLong();
        User userEntity = userRepository.findById(userIdFromToken).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setItem(itemEntity);
        comment.setUser(userEntity);
        comment = commentRepository.save(comment);
        return new CommentDto(
                comment.getContent(),
                comment.getItem().getId(),
                comment.getUser().getNickname()
        );
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        commentRepository.delete(comment);
    }
}
