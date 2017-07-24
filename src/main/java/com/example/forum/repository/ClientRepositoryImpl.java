package com.example.forum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.example.forum.model.Topic;
import com.example.forum.model.User;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Topic> showTopics(String category) {
		String query= "select t from Topic t";
		TypedQuery<Topic> q = entityManager.createQuery(query, Topic.class);
        return q.getResultList();
		
	}

	@Override
	public List<User> getUser(int id) {
		String query= "select u from User u";
		TypedQuery<User> q = entityManager.createQuery(query, User.class);
        return q.getResultList();
	}

}
