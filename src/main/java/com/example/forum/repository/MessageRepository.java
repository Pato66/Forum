package com.example.forum.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.forum.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

}
