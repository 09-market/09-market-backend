package com.gonggu.market.api.service;

import com.gonggu.market.api.config.auth.PrincipalDetails;
import com.gonggu.market.api.domain.post.VerifiedPost;
import com.gonggu.market.api.domain.post.VerifiedPostRepository;
import com.gonggu.market.api.controller.dto.post.VerifiedPostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    private final VerifiedPostRepository verifiedPostRepository;

    public PostService(@Autowired VerifiedPostRepository verifiedPostRepository) {
        this.verifiedPostRepository = verifiedPostRepository;
    }

    @Value("${file.path}")
    private String uploadFolder;

    public VerifiedPost create(PrincipalDetails principalDetails, MultipartFile file, VerifiedPostDto dto) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();
        logger.info("image name : " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try {
            Files.write(imageFilePath, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        VerifiedPost post = new VerifiedPost();
        post.setTitle(dto.getTitle());
        post.setNickname(principalDetails.getUser().getNickname());
        post.setContent(imageFileName);
        post.setUser(principalDetails.getUser());

        post = verifiedPostRepository.save(post);
        return post;
    }

//    public Collection<VerifiedPostDto> readAll() {
//        List<VerifiedPostDto> dtoList = new ArrayList<>();
//        this.verifiedPostRepository.findAll().forEach(verifiedPost -> dtoList.add(
//                new VerifiedPostDto(
//                        verifiedPost.getTitle(),
//                        verifiedPost.getNickname(),
//                        verifiedPost.getContent(),
//                        verifiedPost.getCreatedAt()
//                )
//        ));
//        return dtoList;
//    }
//
//    public boolean update(Long postId, VerifiedPostDto dto) {
//        if (!verifiedPostRepository.existsById(postId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        VerifiedPost verifiedPost = verifiedPostRepository.findById(postId).get();
//        verifiedPost.setTitle(dto.getTitle() == null ? verifiedPost.getTitle() : dto.getTitle());
//        verifiedPost.setContent(dto.getContent() == null ? verifiedPost.getContent() : dto.getContent());
//        verifiedPostRepository.save(verifiedPost);
//        return true;
//    }
//
//    public boolean delete(Long postId) {
//        if (!verifiedPostRepository.existsById(postId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        VerifiedPost verifiedPost = verifiedPostRepository.findById(postId).get();
//        verifiedPostRepository.delete(verifiedPost);
//        return true;
//    }
}
