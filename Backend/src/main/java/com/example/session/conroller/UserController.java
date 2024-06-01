package com.example.session.conroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.session.entities.User;
import com.example.session.exception.UserNotFoundException;
import com.example.session.repo.UserRepo;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/employees")
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	@PostMapping("/employees")
	public User createUser(@RequestBody User user) {
		return userRepo.save(user);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id)
	{
		User user=userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/employees/{id}")
	User updateUser(@RequestBody User newUser,@PathVariable int id) {
		return userRepo.findById(id).map(user->{
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			user.setContact(newUser.getContact());
			return userRepo.save(user);
		}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/employees/{id}")
	String deleteUser(@PathVariable int id) {
		if(!userRepo.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepo.deleteById(id);
		return "user with id"+id+"has been deleted success.";
	}
}
