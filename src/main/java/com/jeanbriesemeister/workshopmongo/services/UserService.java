package com.jeanbriesemeister.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanbriesemeister.workshopmongo.domain.User;
import com.jeanbriesemeister.workshopmongo.dto.UserDTO;
import com.jeanbriesemeister.workshopmongo.repository.UserRepository;
import com.jeanbriesemeister.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	public User insert(User user) {
		return userRepository.insert(user);
	}

	public void delete(String id) {
		userRepository.deleteById(id);
	}

	public User update(User obj) {
		User user = this.findById(obj.getId());
		this.updateData(user, obj);
		return userRepository.save(user);
	}

	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}
