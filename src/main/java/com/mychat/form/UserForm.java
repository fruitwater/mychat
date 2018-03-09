package com.mychat.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min = 1, max = 8) 
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
