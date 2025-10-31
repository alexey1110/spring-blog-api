package com.example.demo.controller;

import com.example.demo.DTO.PostDTO;
import com.example.demo.Service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "tag_for_posts")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Tag(name = "get_all_posts")
    @Operation(summary = "Get all posts", description = "Returns list of posts")
    @GetMapping
    public List<PostDTO> getPosts(){
        return postService.getAll();
    }

    @Tag(name = "create_post")
    @Operation(summary = "Create a new post")
    @ApiResponse(responseCode = "201", description = "Post created")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        return postService.create(postDTO);
    }

    @Tag(name = "delete_post")
    @Operation(summary = "Delete a post")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postService.delete(id);
    }

    @Tag(name = "get_post_by_id")
    @Operation(summary = "Get post by id")
    @GetMapping("/{id}")
    public PostDTO getPost(Long id){
        return postService.getById(id);
    }
}
