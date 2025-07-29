package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }
}
