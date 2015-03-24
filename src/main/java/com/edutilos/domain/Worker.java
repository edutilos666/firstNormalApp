package com.edutilos.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Worker", uniqueConstraints= {
		@UniqueConstraint(columnNames={"fname", "lname", "email"})
})
public class Worker implements Serializable {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id")
  private Long id; 
   @Column(name="fname", unique=false, nullable=false)
   private String fname; 
   @Column(name="lname", unique=false, nullable=false)
   private String lname; 
   @Column(name="email", unique=false, nullable=false)
   private String email;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Worker(Long id, String fname, String lname, String email) {
	super();
	this.id = id;
	this.fname = fname;
	this.lname = lname;
	this.email = email;
}
public Worker() {
	super();
}
@Override
public String toString() {
	return "Worker [id=" + id + ", fname=" + fname + ", lname=" + lname
			+ ", email=" + email + "]";
} 
   
   
   
}
