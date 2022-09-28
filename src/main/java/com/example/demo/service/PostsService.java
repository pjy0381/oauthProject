package com.example.demo.service;

import com.example.demo.dto.Posts;
import com.example.demo.repository.PostsRepository;
import com.example.demo.dto.PostsDto;
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
    public Long save(PostsDto reqDto) {
        postsRepository.save(reqDto.toEntity());
        return reqDto.toEntity().getId();
    }

    @Transactional
    public Long update(Long id, PostsDto reqDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " +id));
        posts.update(reqDto.getTitle(), reqDto.getContent());

        return id;
    }
    public PostsDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " +id));

        return new PostsDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsDto> findAll(){
        return postsRepository.findAll().stream()
                .map(PostsDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void delete(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postsRepository.delete(post);
    }

}
