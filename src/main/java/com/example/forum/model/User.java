package com.example.forum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="users", schema = "public")
public class User  implements java.io.Serializable {

	 @Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name = "user_id")
     private long userId;
	 
     private String firstName;
     private String lastName;
     private String city;
     private String email;
     private boolean enabled; 

	 @NotEmpty(message = "Please enter your login.")
     @Size(min=3, max=30, message = "Length of login should be between 3 and 30 characters.")
     private String login;
     
     @NotEmpty(message = "Please enter your password.")
     @Size(min=3, max=30, message = "Length of password should be between 3 and 30 characters.")
     private String password;

     
     public boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

    public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public User() {
    }

	
    public User(long userId, String login, String password, String firstName, String lastName, String city, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
        this.login = login;
        this.password = password;
    }
    
    public User(String login, String password, String firstName, String lastName, String city, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
        this.login = login;
        this.password = password;
    }
  
   
    public long getUserId() {
        return this.userId;
    }
    
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}