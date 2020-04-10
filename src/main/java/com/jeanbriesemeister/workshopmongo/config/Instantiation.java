package com.jeanbriesemeister.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeanbriesemeister.workshopmongo.domain.Post;
import com.jeanbriesemeister.workshopmongo.domain.User;
import com.jeanbriesemeister.workshopmongo.dto.AuthorDTO;
import com.jeanbriesemeister.workshopmongo.dto.CommentDTO;
import com.jeanbriesemeister.workshopmongo.repository.PostRepository;
import com.jeanbriesemeister.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User mary = new User(null, "Mary Jane", "mary@gmail.com");
		User john = new User(null, "John Jones", "john@gmail.com");
		User carl = new User(null, "Carl Seagan", "carl@gmail.com");

		userRepository.saveAll(Arrays.asList(mary, john, carl));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Nous voyagerons demain",
				"Je vais voyager à São Paulo. Des câlins!", new AuthorDTO(mary));

		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bonjour", "Je me suis réveillé heureux aujourd'hui",
				new AuthorDTO(mary));

		CommentDTO c1 = new CommentDTO("Bon voyage", sdf.parse("21/03/2018"), new AuthorDTO(john));
		CommentDTO c2 = new CommentDTO("Profitez", sdf.parse("22/03/2018"), new AuthorDTO(carl));
		CommentDTO c3 = new CommentDTO("Passez une bonne journée", sdf.parse("23/03/2018"), new AuthorDTO(john));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);

		postRepository.saveAll(Arrays.asList(post1, post2));

		mary.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(mary);
	}

}
