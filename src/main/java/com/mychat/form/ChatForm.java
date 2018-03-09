package com.mychat.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChatForm implements Serializable{

	
private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min = 1, max = 512) 
	private String message;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
