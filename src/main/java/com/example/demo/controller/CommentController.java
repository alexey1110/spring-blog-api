package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "tag_for_comments")
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Tag(name = "ge_all_comments")
    @Operation(summary = "get all comments")
    @GetMapping
    public void getComments() {
        commentRepository.findAll();
    }

    @Tag(name = "add_comment")
    @Operation(summary = "add comment")
    @PostMapping
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Tag(name = "delete_comment")
    @Operation(summary = "delete comment")
    @PostMapping("/delete")
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Tag(name = "get_comment_by_id")
    @Operation(summary = "get comment by id")
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return commentRepository.findById(id).orElse(null);
    }
}
