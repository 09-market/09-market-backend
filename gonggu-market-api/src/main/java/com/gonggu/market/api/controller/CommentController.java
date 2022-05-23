package com.gonggu.market.api.controller;

import com.gonggu.market.api.controller.dto.comment.CommentDto;
import com.gonggu.market.api.domain.comment.Comment;
import com.gonggu.market.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(@Autowired CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto,
                                           @RequestHeader("Authorization") String token) {
        Comment result = commentService.create(commentDto, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
