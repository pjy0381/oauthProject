package com.example.demo.dto;

import com.example.demo.dto.Posts;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
        this.content = entity.getContent();
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
