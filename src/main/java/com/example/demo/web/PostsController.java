package com.example.demo.web;

import com.example.demo.domain.posts.PostsResDto;
import com.example.demo.service.PostsService;
import com.example.demo.web.dto.PostsSaveReqDto;
import com.example.demo.web.dto.PostsUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody  PostsSaveReqDto reqDto) {
        System.out.println(reqDto.toString());
        return postsService.save(reqDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateReqDto reqDto) {
        return postsService.update(id, reqDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

}
