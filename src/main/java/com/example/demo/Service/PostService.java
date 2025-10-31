package com.example.demo.Service;

import com.example.demo.DTO.PostDTO;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.DTO.PostDTO.fromEntity;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostDTO create(PostDTO postDTO) {
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        postRepository.save(post);
        return fromEntity(post);
    }

    public List<PostDTO> getAll() {
        return postRepository.findAll().stream()
                .map(PostDTO::fromEntity)
                .toList();
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Пост с ID " + id + " не найден."));
        postRepository.delete(post);
    }

    public PostDTO getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Пост с ID " + id + " не найден."));
        return fromEntity(post);
    }
}
