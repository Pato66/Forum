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
@Table(name="user_roles", schema = "public")
public class UserRoles  implements java.io.Serializable {

	 @Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name = "user_role_id")
     private long userRoleId;
	 
	 private String login;
	 private String role;
	 
	 public long getUserRoleId() {
	 	 return userRoleId;
	 }
	
	 public void setUserRoleId(long userRoleId) {
		 this.userRoleId = userRoleId;
	 }
	
	 public String getLogin() {
		 return login;
	 }
	
	 public void setLogin(String login) {
		 this.login = login;
	 }
	
	 public String getRole() {
		 return role;
	 }
	
	 public void setRole(String role) {
		 this.role = role;
	 }
	 
}
