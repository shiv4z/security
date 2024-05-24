package com.shiv.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.entity.User;


public interface UserRepo extends JpaRepository<User, Integer>{
       User findByUsername(String username);
}
