package com.example.demo.DTO;

import com.example.demo.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
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
