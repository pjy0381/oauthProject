package com.example.demo.web.dto;

import com.example.demo.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostsDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;

    public PostsDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate();

    }
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
