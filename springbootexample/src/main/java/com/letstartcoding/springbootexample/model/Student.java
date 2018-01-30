package com.letstartcoding.springbootexample.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Email @NotEmpty
	private String email;
	
	@NotEmpty
	private String country;

	@DateTimeFormat(pattern="yyyy-MM-dd")
    @Past @NotNull
	private Date dob;

	private boolean firstAttempt;

	@Size(min=3, max=30)
	private String firstName;

	@Id
	private int id;

	@Size(min=3, max=30)
	private String lastName;
	@NotEmpty
	private String section;

	@NotEmpty
	private String sex;
	
	   @NotEmpty
	    private List<String> subjects = new ArrayList<String>();

	

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	 public boolean isFirstAttempt() {
	        return firstAttempt;
	    }
	 
	    public void setFirstAttempt(boolean firstAttempt) {
	        this.firstAttempt = firstAttempt;
	    }

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<String> getSubjects() {
        return subjects;
    }
 
    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
	
	

}