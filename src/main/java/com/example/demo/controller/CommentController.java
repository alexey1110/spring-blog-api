package com.example.demo.controller;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.Service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "tag_for_comments")
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Tag(name = "ge_all_comments")
    @Operation(summary = "get all comments")
    @GetMapping
    public List<CommentDTO> getComments() {
        return commentService.getAll();
    }

    @Tag(name = "add_comment")
    @Operation(summary = "add comment")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post/{id}")
    public void createComment(@Valid @RequestBody CommentDTO commentDTO,
                              @PathVariable Long id) {
        commentService.create(commentDTO, id);
    }

    @Tag(name = "delete_comment")
    @Operation(summary = "delete comment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.delete(id);
    }

    @Tag(name = "get_comment_by_id")
    @Operation(summary = "get comment by id")
    @GetMapping("/{id}")
    public CommentDTO getCommentById(@PathVariable Long id) {
        return commentService.getById(id);
    }

    @Tag(name = "get_comments_by_post_id")
    @Operation(summary = "get comments by post id")
    @GetMapping("/post/{id}")
    public List<CommentDTO> getCommentsByPostId(@PathVariable Long id) {
        return commentService.getByPostId(id);
    }
}
