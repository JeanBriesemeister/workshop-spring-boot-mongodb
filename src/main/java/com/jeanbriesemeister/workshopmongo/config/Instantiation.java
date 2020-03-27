package com.jeanbriesemeister.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeanbriesemeister.workshopmongo.domain.User;
import com.jeanbriesemeister.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();

		User mary = new User(null, "Mary Jane", "mary@gmail.com");
		User john = new User(null, "John Jones", "john@gmail.com");
		User carl = new User(null, "Carl Seagan", "carl@gmail.com");

		userRepository.saveAll(Arrays.asList(mary, john, carl));
	}

}
