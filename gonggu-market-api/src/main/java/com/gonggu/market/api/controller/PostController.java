package com.gonggu.market.api.controller;

import com.gonggu.market.api.config.auth.PrincipalDetails;
import com.gonggu.market.api.domain.post.VerifiedPost;
import com.gonggu.market.api.dto.post.VerifiedPostDto;
import com.gonggu.market.api.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;


@RestController
@RequestMapping("/api/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final PostService postService;

    public PostController(@Autowired PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<VerifiedPost> createPost(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestPart(value = "file", required = true) MultipartFile file,
            @RequestPart(value = "postDto") VerifiedPostDto dto
    ) {
//        if (dto.getFile().isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
        logger.info("세션 정보 : " + principalDetails.getUser().toString());
        VerifiedPost result = postService.create(principalDetails, file, dto);
        return ResponseEntity.ok(result);
    }

//    @GetMapping
//    public ResponseEntity<Collection<VerifiedPostDto>> readPostAll() {
//        Collection<VerifiedPostDto> postList = postService.readAll();
//        if (postList == null) {
//            ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(postList);
//    }
//
//    @PutMapping("/{postId}")
//    public ResponseEntity<?> updatePost(@PathVariable("postId") Long postId,
//                                        @RequestBody VerifiedPostDto dto) {
//        if (!postService.update(postId, dto)) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("{postId}")
//    public ResponseEntity<?> deletePost(@PathVariable("postId") Long postId) {
//        if (!postService.delete(postId)) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.noContent().build();
//    }

}
