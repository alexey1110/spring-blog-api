package com.example.demo.Service;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.DTO.CommentDTO.fromEntity;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<CommentDTO> getAll() {
        return commentRepository.findAll().stream()
                .map(CommentDTO::fromEntity)
                .toList();
    }

    public CommentDTO create(CommentDTO commentDTO, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Пост с ID " + id + " не найден."));
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setPost(post);
        commentRepository.save(comment);
        return fromEntity(comment);
    }

    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Комментарий с ID " + id + " не найден."));
        commentRepository.delete(comment);
    }

    public CommentDTO getById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Комментарий с ID " + id + " не найден."));
        return fromEntity(comment);
    }

    public List<CommentDTO> getByPostId(Long id) {
        return commentRepository.findAllByPostId(id).stream()
                .map(CommentDTO::fromEntity)
                .toList();
    }
}
