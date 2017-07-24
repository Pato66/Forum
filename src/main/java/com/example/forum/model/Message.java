package com.example.forum.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="message", schema = "public")
public class Message  implements java.io.Serializable {

	 @Id 
	 @GeneratedValue(strategy=GenerationType.AUTO)
     private long messageId;
	 
	 @NotNull
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "topic_id")
     private Topic topic;
     
     @NotNull
     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "user_id")
     private User user;
     
     private String contents;
     private Date dateOfPublish;
     private int thumbsUp;
     private int thumbsDown;

    public Message() {
    }

    public Message(long messageId, Topic topic, User user, String contents, Date dateOfPublish, int thumbsUp, int thumbsDown) {
       this.messageId = messageId;
       this.topic = topic;
       this.user = user;
       this.contents = contents;
       this.dateOfPublish = dateOfPublish;
       this.thumbsUp = thumbsUp;
       this.thumbsDown = thumbsDown;
    }
   
    public long getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
    public Topic getTopic() {
        return this.topic;
    }
    
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getContents() {
        return this.contents;
    }
    
    public void setContents(String contents) {
        this.contents = contents;
    }
    public Date getDateOfPublish() {
        return this.dateOfPublish;
    }
    
    public void setDateOfPublish(Date dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }
    public int getThumbsUp() {
        return this.thumbsUp;
    }
    
    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }
    public int getThumbsDown() {
        return this.thumbsDown;
    }
    
    public void setThumbsDown(int thumbsDown) {
        this.thumbsDown = thumbsDown;
    }
}

