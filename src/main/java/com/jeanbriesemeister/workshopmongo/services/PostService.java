package com.jeanbriesemeister.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanbriesemeister.workshopmongo.domain.Post;
import com.jeanbriesemeister.workshopmongo.repository.PostRepository;
import com.jeanbriesemeister.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	public List<Post> findByTitle(String text) {
		return postRepository.findByTitleContainingIgnoreCase(text);
	}

}
