package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "tag_for_posts")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Tag(name = "get_all_posts")
    @GetMapping
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @Tag(name = "create_post")
    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }
}
