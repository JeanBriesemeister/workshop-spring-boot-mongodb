package com.jeanbriesemeister.workshopmongo.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeanbriesemeister.workshopmongo.domain.Post;
import com.jeanbriesemeister.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(postService.findById(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		try {
			text = URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			text = "";
		}

		return ResponseEntity.ok().body(postService.findByTitle(text));
	}

}
