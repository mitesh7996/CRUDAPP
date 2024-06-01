package com.example.session.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.session.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
}
