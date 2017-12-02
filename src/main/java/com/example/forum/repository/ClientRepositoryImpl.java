package com.example.forum.repository;

import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.forum.model.Message;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.model.UserStat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class ClientRepositoryImpl implements ClientRepository {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Topic> showTopics(String category, int limit, int start) {
		String query= "select t from Topic t order by dateOfCreation desc";
		TypedQuery<Topic> q = entityManager.createQuery(query, Topic.class).setFirstResult(start).setMaxResults(limit);
        return q.getResultList();
		
	}

	@Override
	public List<User> getUser(int id) {
		String query= "select u from User u";
		TypedQuery<User> q = entityManager.createQuery(query, User.class);
        return q.getResultList();
	}
	

	@Override
	public boolean checkUserInDatabase(User user) {
		String query= "select u from User u WHERE login='" + user.getLogin() + "' and password='" + user.getPassword() + "'";
		TypedQuery<User> q = entityManager.createQuery(query, User.class);
		if(q.getResultList().size() != 0){
			return true;
		}
		else{
			return false;
		}
	}
	//...
	@Override
	public boolean ifUserExistInDatabase(String login) {
		String query= "select u from User u WHERE login='" + login + "'";
		TypedQuery<User> q = entityManager.createQuery(query, User.class);
		logger.info("-------------------------------------------------------------------------------------");
		logger.info("Query returned:" + q.getFirstResult()+" | size" + q.getResultList().size() );
		logger.info("------------------------------------------------------------------------------------");
		if(q.getResultList().size() != 0){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public List<Message> findAllMessagesByTopicId(Long topicId, int limit, int start) {
		String query= "select m from Message m WHERE topic=" + topicId+"";// + " order by dateOfPublish desc";
		TypedQuery<Message> q = entityManager.createQuery(query, Message.class).setFirstResult(start).setMaxResults(limit);
		logger.info("-------------------------------------------------------------------------------------");
		logger.info("MESSAGE TABLE : Size: " + q.getResultList().size() + " | limit:"+limit+" | start:"+start);
		logger.info("------------------------------------------------------------------------------------");
		return q.getResultList();
	}

	@Override
	public User findByUsername(String username) {
		String query= "select u from User u WHERE login='" + username + "'";
		TypedQuery<User> q = entityManager.createQuery(query, User.class);
		logger.info("User : Size: " + q.getSingleResult().getLogin() );
		return q.getSingleResult();
	}

	@Override
	public int getTopiscForCateroryQuantuty(String category) {
		String query= "select t from Topic t";// WHERE category='" + category + "'";
		TypedQuery<Topic> q = entityManager.createQuery(query, Topic.class);
		/*logger.info("-------------------------------------------------------------------------------------");
		logger.info("TOPIC TABLE : Size: " + q.getResultList().size() );
		logger.info("------------------------------------------------------------------------------------"); */
		return q.getResultList().size();
	}

	@Override
	public int getQuantityOfMessagesFromTopic(Long topicId) {
		String query= "select m from Message m WHERE topic=" + topicId+"";
		TypedQuery<Message> q = entityManager.createQuery(query, Message.class);
		logger.info("-------------------------------------------------------------------------------------");
		logger.info("MESSAGE TABLE : Size: " + q.getResultList().size());
		logger.info("------------------------------------------------------------------------------------");
		return q.getResultList().size();
	}

	@Override
	public List<User> getUsers(List<Integer> identifiers) {
		
		StringBuilder sb= new StringBuilder();
		String filter = "";
		for(int i=0; i<identifiers.size(); i++){  
			sb.append( "'" + identifiers.get(i) +"'," );
		}

		filter = sb.toString();
		filter = filter.substring(0, filter.length()-1);
		
		String query= "select u from User u where id IN("+filter+")";
		TypedQuery<User> q = entityManager.createQuery(query, User.class);
        return q.getResultList();
	}

	@Override
	public List<UserStat> getUsersStatistics() {
		String query= "select NEW com.example.forum.model.UserStat(m.user.login, count(m.user.login)) from Message m group by m.user.login order by 2 desc";
		TypedQuery<UserStat> q = entityManager.createQuery(query, UserStat.class);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@ SIZE:"+q.getResultList().size());
		return q.getResultList();
	}

}
