package com.example.demo.domain.posts;

import lombok.Getter;

@Getter
public class PostsResDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
