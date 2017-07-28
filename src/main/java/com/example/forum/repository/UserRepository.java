package com.example.forum.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.forum.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
