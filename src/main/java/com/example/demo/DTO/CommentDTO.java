package com.example.demo.DTO;

import com.example.demo.model.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 1000, message = "Title must be between 3 and 100 characters")
    private String text;

    public static CommentDTO fromEntity(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        return commentDTO;
    }

    public static Comment toEntity(CommentDTO commentDTO) {
        if (commentDTO == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        return comment;
    }
}
