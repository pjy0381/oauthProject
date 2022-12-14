package com.example.demo.controller;

import com.example.demo.security.oauth.LoginUser;
import com.example.demo.security.oauth.SessionUser;
import com.example.demo.service.PostsService;
import com.example.demo.dto.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAll());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        System.out.println(dto.toString()+"zzzzzzzzzzzzzzz");
        return "posts-update";
    }
}
