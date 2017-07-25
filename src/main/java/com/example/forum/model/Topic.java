package com.example.forum.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="topic", schema = "public")
public class Topic  implements java.io.Serializable {

	 @Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
     private long topicId;
	 

     @ManyToOne(cascade = CascadeType.MERGE )
     @JoinColumn(name = "user_id")
     private User user;
     private Date dateOfCreation;
     private String category;
     private String description;

    public Topic() {
    }

    public Topic(long topicId, User user, Date dateOfCreation, String category, String description) {
        this.topicId = topicId;
        this.user = user;
        this.dateOfCreation = dateOfCreation;
        this.category = category;
        this.description = description;
    }
   
    public long getTopicId() {
        return this.topicId;
    }
    
    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getDateOfCreation() {
        return this.dateOfCreation;
    }
    
    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
   
}