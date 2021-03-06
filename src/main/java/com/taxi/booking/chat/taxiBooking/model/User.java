package com.taxi.booking.chat.taxiBooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

  // An autogenerated id (unique for each user in the db)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  
  private String email;
  
  
  private String name;

  // Public methods
  
  public User() { }

  public User(long id) { 
    this.id = id;
  }
  
  public User(String email, String name) {
    this.email = email;
    this.name = name;
  }

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

  // Getter and setter methods
  // ...
  
  

}
