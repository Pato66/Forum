package com.example.forum.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


public class UserStat  implements java.io.Serializable {
	
    public String login;
	public long posts;
	
	public UserStat(String login, long posts) {
		this.login = login;
		this.posts = posts;
	}
	
	public long GetPosts() {
		return this.posts;
	}
	
	public void setPosts(long posts) {
		this.posts = posts;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
		 
}

