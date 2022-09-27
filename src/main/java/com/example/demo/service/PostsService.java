package com.example.demo.service;

import com.example.demo.domain.posts.Posts;
import com.example.demo.domain.posts.PostsRepository;
import com.example.demo.web.dto.PostsListResDto;
import com.example.demo.web.dto.PostsResDto;
import com.example.demo.web.dto.PostsSaveReqDto;
import com.example.demo.web.dto.PostsUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveReqDto reqDto) {
        postsRepository.save(reqDto.toEntity());
        return reqDto.toEntity().getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateReqDto reqDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " +id));
        posts.update(reqDto.getTitle(), reqDto.getContent());

        return id;
    }
    public PostsResDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " +id));

        return new PostsResDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResDto> findAll(){
        return postsRepository.findAll().stream()
                .map(PostsListResDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void delete(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postsRepository.delete(post);
    }

}
