package com.joao.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joao.exceptions.UserNotFoundException;
import com.joao.model.Users;
import com.joao.repository.UsersRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersController {
	
	private final UsersRepository usersRepository;
	
	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users newUser) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usersRepository.save(newUser));
	}
	
	@GetMapping
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Users getUser(@PathVariable Long id) {
		return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		if (!usersRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		usersRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("User ID " + id + " deleted successfully.");
	}
	
	@PutMapping("/{id}")
	public Users updateUser(@PathVariable Long id, @RequestBody Users newUser) {
		return usersRepository.findById(id).map(user -> {
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			user.setUsername(newUser.getUsername());
			return usersRepository.save(user);
		}).orElseThrow( () -> new UserNotFoundException(id));
	}

}
