package com.edutilos.domain;

import java.io.Serializable;

public class Person implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int age; 
  private String fname, 
  lname, 
   email;
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
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
public Person(int age, String fname, String lname, String email) {
	super();
	this.age = age;
	this.fname = fname;
	this.lname = lname;
	this.email = email;
}
public Person() {
	super();
}
@Override
public String toString() {
	return "Person [age=" + age + ", fname=" + fname + ", lname=" + lname
			+ ", email=" + email + "]";
} 
  
  
}
