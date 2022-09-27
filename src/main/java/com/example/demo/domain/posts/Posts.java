package com.example.demo.domain.posts;


import com.example.demo.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "Posts")
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
