package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get all posts", description = "Returns list of posts")
    @GetMapping
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @Tag(name = "create_post")
    @Operation(summary = "Create a new post")
    @ApiResponse(responseCode = "201", description = "Post created")
    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }

    @Tag(name = "delete_post")
    @Operation(summary = "Delete a post")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
        }
    }

    @Tag(name = "get_post_by_id")
    @Operation(summary = "Get post by id")
    @GetMapping("/{id}")
    public Post getPostById(Long id){
        return postRepository.findById(id).orElse(null);
    }
}
